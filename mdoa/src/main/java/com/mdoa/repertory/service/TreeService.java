package com.mdoa.repertory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdoa.constant.TreeModelConstant;
import com.mdoa.repertory.dao.TreeDao;
import com.mdoa.repertory.model.RepertoryGoodsType;
import com.mdoa.repertory.model.TreeModel;
import com.mdoa.util.StringUtil;


@Service
@Transactional
public class TreeService {
	@Autowired
	private TreeDao treeDao;
	
	/**
	 * 综合类
	 */
	public void allTreeMethod(RepertoryGoodsType repertoryGoodsType){
		synchronized (this) {
			if (repertoryGoodsType.getAction().equals("添加"))
				addRepertoryType(repertoryGoodsType);
			if (repertoryGoodsType.getAction().equals("删除"))
				removeRepertoryType(repertoryGoodsType);
			if (repertoryGoodsType.getAction().equals("修改"))
				editRepertoryType(repertoryGoodsType);
			if (repertoryGoodsType.getAction().equals("拖动"))
				dragRepertoryType(repertoryGoodsType);
		}
	}
	
	/**
	 * 修改state
	 * @param repertoryGoodsType
	 * @param list
	 */
	public void updateTreeState(RepertoryGoodsType repertoryGoodsType, List<TreeModel> list){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId().equals(repertoryGoodsType.getGoodsTypeUrl()) && repertoryGoodsType.getState().equals("onCollapse"))
				list.get(i).setState("closed");
			if (list.get(i).getId().equals(repertoryGoodsType.getGoodsTypeUrl()) && repertoryGoodsType.getState().equals("onExpand"))
				list.get(i).setState("open");
			if (list.get(i).getChildren() != null) 
				updateTreeState(repertoryGoodsType,list.get(i).getChildren());
		}
	}
	/**
	 * 根据父ID实现树结构
	 * @param pid
	 * @return
	 */
	public List<TreeModel> selectTree(String pid) {
		//根据父ID获得子类集合
		List<TreeModel> treeModels=treeDao.list(pid);
		for (TreeModel treeModel : treeModels) {
			treeModel.setChildren(selectTree(treeModel.getThisId()));//递归实现树结构
		}
		return treeModels;
	}

	/**
	 * 在当前父ID下添加一个物品类
	 * @param id
	 */
	public void addRepertoryType(RepertoryGoodsType repertoryGoodsType) {
		if (repertoryGoodsType.getGoodsTypeName() == null || repertoryGoodsType.getGoodsTypeName() == "" ) 
			throw new RuntimeException("添加的物品类名不能为空");
		repertoryGoodsType.setParentTypeId(StringUtil.getIdFromUrl(repertoryGoodsType.getGoodsTypeUrl()));
		String uuid=treeDao.getuuid();
		//String uuid = "12";
		repertoryGoodsType.setGoodsTypeId(uuid);
		
		//RepertoryGoodsType goodsType=treeDao.selectUrlByparentTypeId(repertoryGoodsType.getParentTypeId());
		repertoryGoodsType.setGoodsTypeUrl(repertoryGoodsType.getGoodsTypeUrl()+"_"+uuid);
		
		if (!treeDao.addRepertoryType(repertoryGoodsType)) {
			throw new RuntimeException("添加物品类失败");
		}else{
			updateDocument(repertoryGoodsType, TreeModelConstant.treeList,uuid);//递归把添加的类设置进去
		}
		
	}
	/**
	 * 添加后递归设置数结构
	 * @param repertoryGoodsType
	 * @param list
	 * @param uuid
	 * @return
	 */
	public List<TreeModel> updateDocument(RepertoryGoodsType repertoryGoodsType,List<TreeModel> list,String uuid){
		if (repertoryGoodsType.getParentTypeId() == null) //判断父ID非空
			throw new RuntimeException("ID不能为空");
		System.out.println("size="+list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println("url="+repertoryGoodsType.getParentTypeId()+"thisid="+list.get(i).getThisId());
			if (list.get(i).getThisId().equals(repertoryGoodsType.getParentTypeId())) {
				TreeModel treeModel=new TreeModel();
				treeModel.setId(repertoryGoodsType.getGoodsTypeUrl());
				treeModel.setThisId(uuid);
				treeModel.setText(repertoryGoodsType.getGoodsTypeName());
				if (list.get(i).getChildren() == null) {
					List<TreeModel> list2 = new ArrayList<>();
					list.get(i).setChildren(list2);
				}
				list.get(i).getChildren().add(treeModel);
				continue;
			}
			if ( list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0 ) {
				continue;
			}
			updateDocument(repertoryGoodsType, list.get(i).getChildren(), uuid);
		}
		return list;
	}
	/**
	 * 根据当前父ID删除下面的所有物品类
	 * @param repertoryGoodsType
	 * @return
	 */
	public void removeRepertoryType(RepertoryGoodsType repertoryGoodsType) {
		if (StringUtil.getIdFromUrl(repertoryGoodsType.getGoodsTypeUrl()) == "0")
			throw new RuntimeException("无法删除根部目录");
		repertoryGoodsType.setGoodsTypeId(StringUtil.getIdFromUrl(repertoryGoodsType.getGoodsTypeUrl()));
		repertoryGoodsType.setGoodsTypeUrl("'"+repertoryGoodsType.getGoodsTypeUrl()+"%"+"'");
		List<RepertoryGoodsType> list = treeDao.isGoodsBelowType(repertoryGoodsType);
		if (list.size() != 0) 
			throw new RuntimeException("物品类下物品为空");
		if (!treeDao.removeRepertoryType(repertoryGoodsType))
			throw new RuntimeException("删除数据库类别失败");
		removeDocument(repertoryGoodsType,TreeModelConstant.treeList.get(0).getChildren());
	}
	/**
	 * 删除后递归树结构
	 * @param repertoryGoodsType
	 * @param list
	 * @return
	 */
	private boolean removeDocument(RepertoryGoodsType repertoryGoodsType, List<TreeModel> list) {
		if (repertoryGoodsType.getGoodsTypeId() == null || repertoryGoodsType.getGoodsTypeId() == "") 
			throw new RuntimeException("删除的物品类ID不能为空");
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(repertoryGoodsType.getGoodsTypeId())) {
				list.remove(i);
				return true;
			}
			if (list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0 ) {
				continue;
			}
			removeDocument(repertoryGoodsType, list.get(i).getChildren());
		}
		return true;
	}
	/**
	 * 根据前台传过来的ID和名字，进行修改
	 * @param repertoryGoodsType
	 */
	public void editRepertoryType(RepertoryGoodsType repertoryGoodsType) {
		if (repertoryGoodsType.getGoodsTypeName() == null || repertoryGoodsType.getGoodsTypeName() == "") 
			throw new RuntimeException("修改的物品类NAME不能为空");
		repertoryGoodsType.setGoodsTypeId(StringUtil.getIdFromUrl(repertoryGoodsType.getGoodsTypeUrl()));
		if (!treeDao.editRepertoryType(repertoryGoodsType))
			throw new RuntimeException();
		editDocument(repertoryGoodsType,TreeModelConstant.treeList);
	}
	/**
	 * 修改数据库后对树节点进行相应的修改
	 * @param repertoryGoodsType
	 * @param list
	 * @return
	 */
	private boolean editDocument(RepertoryGoodsType repertoryGoodsType, List<TreeModel> list) {
		if (repertoryGoodsType.getGoodsTypeId() == null || repertoryGoodsType.getGoodsTypeId() == "") 
			throw new RuntimeException("修改的物品类ID不能空");
		for (int i = 0; i < list.size(); i++) {
			System.out.println("thisIs="+list.get(i).getThisId()+",id"+repertoryGoodsType.getGoodsTypeId());
			if (list.get(i).getThisId().equals(repertoryGoodsType.getGoodsTypeId())) {
				list.get(i).setText(repertoryGoodsType.getGoodsTypeName());
				return true;
			}
			if (list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0) {
				continue;
			}
			editDocument(repertoryGoodsType,list.get(i).getChildren());
		}
	
		return true;
		
	}
    /**
     * (拖动)
     * @param dragModel
     */
	public void dragRepertoryType(RepertoryGoodsType dragModel) {
		
		if (dragModel.getPoint().equals("append")) {//插入的情况需修改Url和souce的parentGoodsId
			dragModel.setTargetId(StringUtil.getIdFromUrl(dragModel.getTargetUrl()));
			dragModel.setSourceId(StringUtil.getIdFromUrl(dragModel.getSourceUrl()));
			dragModel.setSourceUrlo(dragModel.getTargetUrl()+"_"+dragModel.getSourceId());
			if (!treeDao.updateDragSourceGoodsUrl(dragModel)) 
				throw new RuntimeException("数据库移动失败append");
			dragModel.setReplaceUrl("'"+dragModel.getSourceUrlo()+"_"+"'");
			dragModel.setPreviousUrl("'"+dragModel.getSourceUrl()+"_"+"'");
			dragModel.setQueryUrl("'"+dragModel.getSourceUrl()+"%"+"'");//此步操作可以去数据库一次性
			System.out.println("replaceUrl="+dragModel.getReplaceUrl()+",previousUrl="+dragModel.getPreviousUrl()+",queryurl="+dragModel.getQueryUrl());
			List<TreeModel> list=treeDao.list(dragModel.getSourceId());//判断拖动的那个节点是不是叶子节点
			if (list.size()!=0) {
				if (!treeDao.updateDragLaterUrl(dragModel)) 
					throw new RuntimeException("下属物品类Url修改失败append");
			}
			
			if (dragAppendDocumentRemove(dragModel, TreeModelConstant.treeList)) {
				dragAppendDocument(dragModel, TreeModelConstant.treeList);
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
				if (!treeDao.updateDragSourceGoodsUrlBt(dragModel)) 
					throw new RuntimeException("数据库移动失败bottom,top");
				dragModel.setReplaceUrl("'"+dragModel.getSourceUrlo()+"_"+"'");
				dragModel.setPreviousUrl("'"+dragModel.getSourceUrl()+"_"+"'");
				dragModel.setQueryUrl("'"+dragModel.getSourceUrl()+"%"+"'");//此步操作可以去数据库一次性
				List<TreeModel> list=treeDao.list(dragModel.getSourceId());//判断拖动的那个节点是不是叶子节点
				if (list.size()!=0) {
					if (!treeDao.updateDragLaterUrl(dragModel)) 
						throw new RuntimeException("下属物品类Url修改失败botom,top");
				}
				if (dragAppendDocumentRemove(dragModel, TreeModelConstant.treeList.get(0).getChildren())) {
					dragAppendDocumentBt(dragModel, TreeModelConstant.treeList.get(0).getChildren());
				}
				
			}else{
				dragPaiXuSameParent(TreeModelConstant.treeList.get(0).getChildren(),dragModel);
				
			}
		}
		
	}
	

	public void dragPaiXuSameParent(List<TreeModel> list, RepertoryGoodsType dragModel){
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(dragModel.getSourceId())) {
				List<TreeModel> ll=new ArrayList<>();
				ll.addAll(list);
				dragModel.setTreeList(ll);
				list.removeAll(list);
				dragModel.setFlag(true);
				dragModel.setMflag(false);
				dragModel.setSflag(true);
				String sourStr = dragModel.getSourceId();
				String tarStr = dragModel.getTargetId();
				TreeModel lalaTreeModel = null;
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

	private boolean dragAppendDocumentRemove(RepertoryGoodsType dragModel, List<TreeModel> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(dragModel.getSourceId())) {
				TreeModel removeTreeModel=list.remove(i);
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
	 * @return
	 */
	private void dragAppendDocument(RepertoryGoodsType dragModel, List<TreeModel> list) {//append
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(dragModel.getTargetId())) {
				System.out.println("soururlO="+dragModel.getSourceUrlo()+",replace="+dragModel.getReplaceUrl()+",previous="+dragModel.getPreviousUrl());
				dragModel.getTreeModel().setId(dragModel.getSourceUrlo());
				dragModel.getTreeModel().setThisId(dragModel.getSourceId());
				if (dragModel.getTreeModel().getChildren() != null ) {
					lala(dragModel.getTreeModel().getChildren(),dragModel);
				}
				if (list.get(i).getChildren() == null) {
					List<TreeModel> list2 = new ArrayList<>();
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
	

	private void lala(List<TreeModel> list,RepertoryGoodsType dragModel) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("原来的每个Url="+list.get(i).getId()+",previousUrl="+dragModel.getPreviousUrl().replace("'", "")+",replaceUrl="+dragModel.getReplaceUrl().replace("'", "")+",改后的Url="+list.get(i).getId().replace(dragModel.getPreviousUrl(), dragModel.getReplaceUrl()));
			list.get(i).setId(list.get(i).getId().replace(dragModel.getPreviousUrl().replace("'", ""), dragModel.getReplaceUrl().replace("'", "")));
			if (list.get(i).getChildren() == null || list.get(i).getChildren().size() == 0) {
				continue;
			}
			lala(list.get(i).getChildren(),dragModel);
		}
	}


	private void dragAppendDocumentBt(RepertoryGoodsType dragModel, List<TreeModel> list) {//bottom,top
		dragModel.getTreeModel().setId(dragModel.getSourceUrlo());
		dragModel.getTreeModel().setThisId(dragModel.getSourceId());
		if (dragModel.getTreeModel().getChildren() != null) {
			lala(dragModel.getTreeModel().getChildren(),dragModel);
		}
		label2:for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getThisId().equals(dragModel.getTargetId())) {
				List<TreeModel> ll=new ArrayList<>();
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
