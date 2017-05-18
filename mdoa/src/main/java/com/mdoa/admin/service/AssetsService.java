package com.mdoa.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdoa.admin.dao.AssetsDao;
import com.mdoa.admin.model.AssetsType;
import com.mdoa.admin.model.AssetsModel;
import com.mdoa.constant.TreeModelConstant;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class AssetsService {
	@Autowired
	private AssetsDao assetsDao;
	
	/**
	 * 综合类
	 * @param assetsType
	 */
	public void allTreeMethod(AssetsType assetsType){
		synchronized (this) {
			
			if (assetsType.getAction().equals("添加")) {
				addAssetsType(assetsType);
			}
			if (assetsType.getAction().equals("删除")) {
				removeAssetsType(assetsType);
			}
			if (assetsType.getAction().equals("修改")) {
				editAssetsType(assetsType);
			}
			if (assetsType.getAction().equals("拖动")) {
				dragAssetsType(assetsType);
			}
		}
	}
	
	/**
	 * 修改
	 * @param assetsType
	 * @param list
	 */
	public void updateTreeState(AssetsType assetsType,List<AssetsModel> list){
		for(int i = 0; i < list.size(); i++ ){
			if (list.get(i).getId().equals(assetsType.getAssetsTypeUrl()) && assetsType.getState().equals("onCollapse"))
				list.get(i).setState("closed");
			if (list.get(i).getId().equals(assetsType.getAssetsTypeUrl()) && assetsType.getState().equals("onExpand"))
				list.get(i).setState("open");
			if (list.get(i).getChildren() != null)
				updateTreeState(assetsType, list.get(i).getChildren());
		}			
		
	}
	/**
	 * 根据父ID实现树结构
	 * @param pid
	 * @return
	 */
	public List<AssetsModel> selectTree(String pid) {
		//根据父ID获得子类集合
		List<AssetsModel> treeModels=assetsDao.list(pid);
		for (AssetsModel treeModel : treeModels) {
			treeModel.setChildren(selectTree(treeModel.getThisId()));//递归实现树结构
		}
		return treeModels;
	}
	
	
	/**
	 *在当前父id下添加一个资产类
	 * @param assetsType
	 */
	public void addAssetsType(AssetsType assetsType){
		if (assetsType.getAssetsTypeName() == null || assetsType.getAssetsTypeName() == "")
		 throw new RuntimeException("添加的资产类名不能为空");
		assetsType.setParentTypeId(StringUtil.getIdFromUrl(assetsType.getAssetsTypeUrl()));
		String uuid = assetsDao.getuuid();
		assetsType.setAssetsTypeId(uuid);
		assetsType.setAssetsTypeUrl(assetsType.getAssetsTypeUrl()+"_"+uuid);
		if (!assetsDao.addAssetsType(assetsType)) {
			throw new RuntimeException("添加资产失败");
		}else{
			updateDocument(assetsType, TreeModelConstant.assetslist, uuid);//递归把添加的类设置进去
		}
	}
	/**
	 * 添加后递归设置数结构
	 * @param assetsType
	 * @param list
	 * @param uuid
	 * @return
	 */
	public List<AssetsModel> updateDocument(AssetsType assetsType,List<AssetsModel> list,String uuid){
		if (assetsType.getParentTypeId() == null)
			throw new RuntimeException("ID不能为空");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(assetsType.getParentTypeId())) {
				AssetsModel treeModel = new AssetsModel();
				treeModel.setId(assetsType.getAssetsTypeUrl());
				treeModel.setThisId(uuid);
				treeModel.setText(assetsType.getAssetsTypeName());
				if(list.get(i).getChildren() == null){
					List<AssetsModel> list2 = new ArrayList<>();
					list.get(i).setChildren(list2);
				}
				list.get(i).getChildren().add(treeModel);
				continue;
			}
			if(list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0){
				continue;
			}
			updateDocument(assetsType, list.get(i).getChildren(), uuid);
		}
		return list;
	}
	
	/**
	 * 根据当前父ID删除下面的所有资产类
	 * @param assetsType
	 */
	public void removeAssetsType(AssetsType assetsType){
		if (StringUtil.getIdFromUrl(assetsType.getAssetsTypeUrl()) == "0")
			throw new RuntimeException("无法删除根部目录");
		assetsType.setAssetsTypeId(StringUtil.getIdFromUrl(assetsType.getAssetsTypeUrl()));
		System.out.println(StringUtil.getIdFromUrl(assetsType.getAssetsTypeUrl()));
		assetsType.setAssetsTypeUrl("'"+assetsType.getAssetsTypeUrl()+"%"+"'");
		List<AssetsType> list = assetsDao.isAssetsBelowType(assetsType);
		if(list.size() != 0)
			throw new RuntimeException("物品类下物品为空");
		if(!assetsDao.removeAssetsType(assetsType))
			throw new RuntimeException("删除数据库类别失败");
		removeDocument(assetsType,TreeModelConstant.assetslist.get(0).getChildren());
	}
	/**
	 * 删除后递归树结构
	 * @param assetsType
	 * @param list
	 * @return
	 */
	private boolean removeDocument(AssetsType assetsType,List<AssetsModel> list){
		if(assetsType.getAssetsTypeId() == null || assetsType.getAssetsTypeId() == "")
			throw new RuntimeException("删除的资产类id不能为空");
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getThisId().equals(assetsType.getAssetsTypeId())){
				list.remove(i);
				return true;
			}
			if(list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0){
				continue;
			}
			removeDocument(assetsType, list.get(i).getChildren());
		}
		return true;
	}
	/**
	 * 根据前台传过来的ID和名字，进行修改
	 * @param assetsType
	 */
	public void editAssetsType(AssetsType assetsType){
		if(assetsType.getAssetsTypeName() == null || assetsType.getAssetsTypeName() == "")
			throw new RuntimeException("修改的资产类名字不能为空");
		assetsType.setAssetsTypeId(StringUtil.getIdFromUrl(assetsType.getAssetsTypeUrl()));
		if(!assetsDao.editAssetsType(assetsType))
			throw new RuntimeException();
		editDocument(assetsType, TreeModelConstant.assetslist);
	}
	/**
	 * 修改数据库后对树节点进行相应的修改
	 * @param assetsType
	 * @param list
	 * @return
	 */
	private boolean editDocument(AssetsType assetsType,List<AssetsModel> list){
		if(assetsType.getAssetsTypeId() == null || assetsType.getAssetsTypeId() == "")
			throw new RuntimeException("修改的资产类ID不能空");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(assetsType.getAssetsTypeId())) {
				list.get(i).setText(assetsType.getAssetsTypeName());
				return true;
			}
			if (list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0) {
				continue;
			}
			editDocument(assetsType, list.get(i).getChildren());
		}
		return true;
	}
	/**
	 * (拖动)
	 * @param dragModel
	 */
	public void dragAssetsType(AssetsType dragModel){
		
		if(dragModel.getPoint().equals("append")){
			dragModel.setTargetId(StringUtil.getIdFromUrl(dragModel.getTargetUrl()));
			dragModel.setSourceId(StringUtil.getIdFromUrl(dragModel.getSourceUrl()));
			dragModel.setSourceUrlo(dragModel.getTargetUrl()+"_"+dragModel.getSourceId());
			if(!assetsDao.updateDragSourceAssetsUrl(dragModel))
				throw new RuntimeException("数据库移动失败append");
			dragModel.setReplaceUrl("'"+dragModel.getSourceUrlo()+"_"+"'");
			dragModel.setPreviousUrl("'"+dragModel.getSourceUrl()+"_"+"'");
			dragModel.setQueryUrl("'"+dragModel.getSourceUrl()+"%"+"'");
			List<AssetsModel> list = assetsDao.list(dragModel.getSourceId());
			if (list.size()!=0) {
				if(!assetsDao.updateDragLaterUrl(dragModel))
					throw new RuntimeException("下属资产类url修改失败append");
			}
			if (dragAppendDocumentRemove(dragModel,TreeModelConstant.assetslist)) {
				dragAppendDocument(dragModel,TreeModelConstant.assetslist);
			}
		}
		
		if (dragModel.getPoint().equals("bottom") || dragModel.getPoint().equals("top")) {
			dragModel.setTargetId(StringUtil.getIdFromUrl(dragModel.getTargetUrl()));
			dragModel.setSourceId(StringUtil.getIdFromUrl(dragModel.getSourceUrl()));
			dragModel.setSourceParentId(StringUtil.getParentIdFromUrl(dragModel.getSourceUrl()));
			dragModel.setTargetParentId(StringUtil.getParentIdFromUrl(dragModel.getTargetUrl()));
			if (!dragModel.getSourceParentId().equals(dragModel.getTargetParentId())) {
				dragModel.setSourceUrlo(dragModel.getTargetUrl()+"_"+dragModel.getSourceId());
				int lastIndexOf = dragModel.getTargetUrl().lastIndexOf("_");
				String endTargetUrl = dragModel.getTargetUrl().substring(0,lastIndexOf);//据子类的url截取出父类的url
				dragModel.setSourceUrlo(endTargetUrl+"_"+dragModel.getSourceId());
				if (!assetsDao.updateDragSourceAssetsUrlBt(dragModel))
					throw new RuntimeException("数据库移动失败bottom,top");
				dragModel.setReplaceUrl("'"+dragModel.getSourceUrlo()+"_"+"'");
				dragModel.setPreviousUrl("'"+dragModel.getSourceUrl()+"_"+"'");
				dragModel.setQueryUrl("'"+dragModel.getSourceUrl()+"%"+"'");//此步操作可以去数据库一次性
				List<AssetsModel> list=assetsDao.list(dragModel.getSourceId());//判断拖动的那个节点是不是叶子节点
				if (list.size()!=0) {
					if (!assetsDao.updateDragLaterUrl(dragModel)) 
						throw new RuntimeException("下属物品类Url修改失败botom,top");
				}
				if (dragAppendDocumentRemove(dragModel,TreeModelConstant.assetslist.get(0).getChildren())) {
					dragAppendDocumentBt(dragModel,TreeModelConstant.assetslist.get(0).getChildren());
				}
				
			}else {
				dragPaiXuSameParent(TreeModelConstant.assetslist.get(0).getChildren(),dragModel);
			}
		}
	}
	
	public void dragPaiXuSameParent(List<AssetsModel> list, AssetsType dragModel){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(dragModel.getSourceId())) {
				List<AssetsModel> ll=new ArrayList<>();
				ll.addAll(list);
				dragModel.setTreeList(ll);
				list.removeAll(list);
				dragModel.setFlag(true);
				dragModel.setMflag(false);
				dragModel.setSflag(true);
				String sourStr = dragModel.getSourceId();
				String tarStr = dragModel.getTargetId();
				AssetsModel lalaTreeModel = null;
				for (int j = 0; j < dragModel.getTreeList().size(); j++) {
					if (dragModel.getTreeList().get(j).getThisId().equals(sourStr)) {
						lalaTreeModel = dragModel.getTreeList().remove(j);
					}
				}
				//处理位置顺序
				if (dragModel.getPoint().equals("top")) {
					System.out.println("size="+dragModel.getTreeList().size()+1);
					for (int j = 0; j < dragModel.getTreeList().size()+1; j++) {
						if (dragModel.isSflag()) {
							if (dragModel.getTreeList().get(j).getThisId().equals(tarStr)) {
								dragModel.setFlag(false);
								dragModel.setMflag(true);
								dragModel.setSflag(false);
							}
						}
						if (dragModel.isFlag()) {
							if (dragModel.isMflag()) {
								j -= 1;
							}
							list.add(dragModel.getTreeList().get(j));
						}else{
							list.add(lalaTreeModel);
							dragModel.setFlag(true);
						}
					}
				}
				if (dragModel.getPoint().equals("bottom")) {
					for (int j = 0; j < dragModel.getTreeList().size()+1; j++) {
					if (dragModel.isSflag()) {
						if (dragModel.getTreeList().get(j).getThisId().equals(tarStr)) {
							list.add(dragModel.getTreeList().get(j));
							dragModel.setFlag(false);
							dragModel.setMflag(true);
							dragModel.setSflag(false);
							continue;
						}
					}
						if (dragModel.isFlag()) {
							if (dragModel.isMflag()) {
								j -= 1;
							}
							list.add(dragModel.getTreeList().get(j));
						}else{
							list.add(lalaTreeModel);
							dragModel.setFlag(true);
						}
						
					}
					
				}
				for (int j = 0; j < list.size(); j++) {
					
					System.out.println("====最后的="+list.get(i).getText());
				}
			}
			if (list.get(i).getChildren() != null) {
				dragPaiXuSameParent(list.get(i).getChildren(), dragModel);
			}
		}
	}
	
	private boolean dragAppendDocumentRemove(AssetsType dragModel,List<AssetsModel> list){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(dragModel.getSourceId())) {
				AssetsModel removeTreeModel=list.remove(i);
				dragModel.setTreeModel(removeTreeModel);
				return true;
			}
			if (list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0 ) {
				continue;
			}
			if (list.get(i).getChildren() != null) {
				
				dragAppendDocumentRemove(dragModel,list.get(i).getChildren());
			}
		}
		return true;
	}
	
	/**
	 * 拖动修改到数据库后，修改树结构部分
	 * @param dragModel
	 * @param list
	 */
	private void dragAppendDocument(AssetsType dragModel,List<AssetsModel> list){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(dragModel.getTargetId())) {
				System.out.println("soururlO="+dragModel.getSourceUrlo()+",replace="+dragModel.getReplaceUrl()+",previous="+dragModel.getPreviousUrl());
				dragModel.getTreeModel().setId(dragModel.getSourceUrlo());
				dragModel.getTreeModel().setThisId(dragModel.getSourceId());
				if (dragModel.getTreeModel().getChildren() != null ) {
					lala(dragModel.getTreeModel().getChildren(),dragModel);
				}
				if (list.get(i).getChildren() == null) {
					List<AssetsModel> list2 = new ArrayList<>();
					list.get(i).setChildren(list2);
				}
				list.get(i).getChildren().add(dragModel.getTreeModel());
 			}
			if (list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0 ) {
				continue;
			}
			dragAppendDocument(dragModel,list.get(i).getChildren());
		}
	}
	
	
	private void lala(List<AssetsModel> list,AssetsType dragModel){
		for (int i = 0; i < list.size(); i++) {
			System.out.println("原来的每个Url="+list.get(i).getId()+",previousUrl="+dragModel.getPreviousUrl().replace("'", "")+",replaceUrl="+dragModel.getReplaceUrl().replace("'", "")+",改后的Url="+list.get(i).getId().replace(dragModel.getPreviousUrl(), dragModel.getReplaceUrl()));
			list.get(i).setId(list.get(i).getId().replace(dragModel.getPreviousUrl().replace("'", ""), dragModel.getReplaceUrl().replace("'", "")));
			if (list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0) {
				continue;
			}
			lala(list.get(i).getChildren(),dragModel);
		}	
	}
	
	private void dragAppendDocumentBt(AssetsType dragModel,List<AssetsModel> list){
		dragModel.getTreeModel().setId(dragModel.getSourceUrlo());
		dragModel.getTreeModel().setThisId(dragModel.getSourceId());
		if (dragModel.getTreeModel().getChildren() != null) {
			lala(dragModel.getTreeModel().getChildren(),dragModel);
		}
		label2:for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(dragModel.getTargetId())) {
				List<AssetsModel> ll=new ArrayList<>();
				ll.addAll(list);
				dragModel.setTreeList(ll);
				
				list.removeAll(list);
				System.out.println("list.size="+dragModel.getTreeList().size());
				if (dragModel.getPoint().equals("bottom")) {
					dragModel.setFlagStr("1");
					dragModel.setMflag(true);
					label:for (int j = 0; j < dragModel.getTreeList().size()+1; j++) {
						if (dragModel.getFlagStr()=="2") {
							System.out.println("近来啦");
							list.add(dragModel.getTreeModel());
							dragModel.setFlagStr("3");
							continue label;
						}
						if (dragModel.isMflag()) {
							if (dragModel.getTargetId().equals(dragModel.getTreeList().get(j).getThisId())) {
								System.out.println("jjj="+j);
								dragModel.setMflag(false);
								dragModel.setFlagStr("2");
							}
						}
						if (dragModel.getFlagStr()=="1" || dragModel.getFlagStr()=="2") {
							list.add(dragModel.getTreeList().get(j));
						}else{
							list.add(dragModel.getTreeList().get(j-1));
						}
					}
					break label2;
				}
				if (dragModel.getPoint().equals("top")) {
					dragModel.setMflag(true);
					label1:for (int k = 0; k < dragModel.getTreeList().size()+1; k++) {
						if (dragModel.isMflag()) {
							System.out.println("k="+k+",value="+dragModel.getTreeList().get(k).getThisId());
							if (dragModel.getTargetId().equals(dragModel.getTreeList().get(k).getThisId())) {
								list.add(dragModel.getTreeModel());
								dragModel.setMflag(false);
								continue label1;
							}
						}
						if (dragModel.isMflag()) {
							list.add(dragModel.getTreeList().get(k));
						}else{
							list.add(dragModel.getTreeList().get(k-1));
						}
						
					}
					break label2;
				}
				
			}
			if (list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0) {
				continue;
			}
			dragAppendDocumentBt(dragModel,list.get(i).getChildren());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
