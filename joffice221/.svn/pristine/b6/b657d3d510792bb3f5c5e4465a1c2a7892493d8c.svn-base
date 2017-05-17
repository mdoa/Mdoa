package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司企业管理平台   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-20010 GuangZhou HongTian Software Limited company.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.PinyinUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.GlobalType;
import com.htsoft.oa.service.system.GlobalTypeService;

/**
 * 
 * @author
 * 
 */
public class GlobalTypeAction extends BaseAction {
	@Resource
	private GlobalTypeService globalTypeService;
	private GlobalType globalType;

	private Long proTypeId;

	private String catKey = GlobalType.CAT_PRODUCT_TYPE;

	public String getCatKey() {
		return catKey;
	}

	public void setCatKey(String catKey) {
		this.catKey = catKey;
	}

	public Long getProTypeId() {
		return proTypeId;
	}

	public void setProTypeId(Long proTypeId) {
		this.proTypeId = proTypeId;
	}

	public GlobalType getGlobalType() {
		return globalType;
	}

	public void setGlobalType(GlobalType globalType) {
		this.globalType = globalType;
	}

	/**
	 * 取得其子类节点列表
	 * 
	 * @return
	 */
	public String sub() {
		Long parentId = null;
		String sParentId = getRequest().getParameter("parentId");
		if (StringUtils.isNotEmpty(sParentId)) {
			parentId = new Long(sParentId);
		}
		List<GlobalType> list = globalTypeService.getByParentIdCatKey(parentId,
				catKey);
		jsonString = mapper.toResultJson(list);
		return SUCCESS;
	}

	public String mulSave() {
		String data = getRequest().getParameter("data");

		logger.info("data:" + data);

		if (StringUtils.isNotEmpty(data)) {

			JacksonMapper mapper = new JacksonMapper(true);
			GlobalType[] types = mapper.toObject(data, GlobalType[].class);

			for (int i = 0; i < types.length; i++) {
				GlobalType newType = globalTypeService.get(types[i]
						.getProTypeId());
				try {
					BeanUtil.copyNotNullProperties(newType, types[i]);
					newType.setSn(i + 1);
					globalTypeService.save(newType);
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}

		jsonString = "{success:true}";
		return SUCCESS;
	}

	public String getTree() {
		List<GlobalType> list = globalTypeService.getByCatKey(catKey);
		jsonString = mapper.toJson(list);
		return SUCCESS;

	}

	/**
	 * 产生树
	 * 
	 * @return
	 */
	public String tree() {
		String[] otherNode = { "nodeKey", "isPublic" };
		List<GlobalType> list = globalTypeService.getByCatKey(catKey);
		String method = getRequest().getParameter("method");
		jsonString = JsonTree
				.generate(getResult(list, method), otherNode, true);
		logger.info("tree json:" + jsonString);
		return SUCCESS;
	}

	/**
	 * 产生树的结果
	 * 
	 * @param list
	 * @param method
	 * @return
	 */
	private List<Object> getResult(List<GlobalType> list, String method) {
		List<Object> dataList = new ArrayList<Object>();

		if (!StringUtils.isNotEmpty(method)) {
			HashMap<String, Object> rootNode = new HashMap<String, Object>();
			rootNode.put("id", "0");
			rootNode.put("text", "总分类");
			rootNode.put("parentId", null);
			dataList.add(rootNode);
		}
		for (GlobalType globalType : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", globalType.getProTypeId().toString());
			dataRecord.put("text", globalType.getTypeName().toString());
			String parentId = globalType.getParentId() == null ? "0"
					: globalType.getParentId().toString();
			if (StringUtils.isNotEmpty(method)
					&& globalType.getParentId() != null
					&& globalType.getParentId().longValue() == 0L) {
				parentId = null;
			}
			dataRecord.put("parentId", parentId);
			dataRecord.put("sn", globalType.getSn() == null ? Integer.toString(list.size()) : globalType
					.getSn().toString());
			dataRecord.put("nodeKey", globalType.getNodeKey());
			if (globalType.getUserId() != null && globalType.getUserId() > 0) {
				dataRecord.put("isPublic", false); // 私有
			} else {
				dataRecord.put("isPublic", true);// 公共
			}
			dataList.add(dataRecord);
		}

		return dataList;
	}

	/**
	 * 数字字典类型下拉
	 * 
	 * @param catKey
	 * @return
	 */
	public String dicCombo() {

		List<GlobalType> typeList = globalTypeService.getByCatKey(catKey);
		StringBuffer sb = new StringBuffer("[");
		for (GlobalType globalType : typeList) {
			sb.append("['");
			if (globalType.getDepth() > 1) {
				String str = "";
				for (int i = 1; i < globalType.getDepth(); i++) {
					str += "-";
				}
				sb.append(str);
			}
			sb.append(globalType.getTypeName()).append("','")
					.append(globalType.getProTypeId()).append("'],");
		}
		if (typeList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 个人计划类型下拉
	 * 
	 * @param catKey
	 * @return
	 */
	public String combo() {

		List<GlobalType> typeList = globalTypeService
				.getByParentIdCatKeyUserId(new Long(0l), catKey,
						ContextUtil.getCurrentUserId());
		StringBuffer sb = new StringBuffer("[");
		for (GlobalType globalType : typeList) {
			sb.append("['").append(globalType.getProTypeId()).append("','")
					.append(globalType.getTypeName()).append("'],");
		}
		if (typeList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<GlobalType> list = globalTypeService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				// 删除该分类时，需要删除该分类下的所有子分类，包括其子子分类
				globalTypeService.mulDel(new Long(id));
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 删除数字字典类型
	 * 
	 * @return
	 */
	public String DelTypeDIC() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				int count = globalTypeService.getByParentIdCatKey(new Long(id),
						"DIC").size();
				globalType = globalTypeService.get(new Long(id));
				Integer sn = globalType.getSn();
				List<GlobalType> gb = globalTypeService.getByCatKeyAndGTsn(
						globalType.getCatKey(), sn);
				// 删除该分类时，需要删除该分类下的所有子分类，包括其子子分类
				globalTypeService.mulDel(new Long(id));
				// 设置sn
				if (gb != null && gb.size() > 0) {
					for (GlobalType global : gb) {
						global.setSn(global.getSn() - count - 1);
						globalTypeService.save(global);
					}
				}
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 根据proTypeId删除下面对应的所有节点信息，包含本身
	 * 
	 * @return
	 */
	public String delChildrens() {
		if (proTypeId != null && proTypeId > 0) {
			globalTypeService.delChildrens(proTypeId);
			jsonString = "{success:true}";
		}
		jsonString = "{failure:true}";
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		GlobalType globalType = globalTypeService.get(proTypeId);

		jsonString = mapper.toDataJson(globalType);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {

		String isPublic = getRequest().getParameter("isPublic"); // 是否公共
		if (globalType != null && globalType.getProTypeId() != null) {
			GlobalType orgGlobalType = globalTypeService.get(globalType
					.getProTypeId());
			if (globalType.getCatKey().equals("DIC")
					&& !globalType.getNodeKey().equals(
							orgGlobalType.getNodeKey())) {
				List<GlobalType> list = globalTypeService
						.getByCatKey(globalType.getCatKey());
				for (int i = 0; i < list.size(); i++) {
					if (globalType.getNodeKey()
							.equals(list.get(i).getNodeKey())) {
						setJsonString("{success:false,message:'节点Key已存在，请输入唯一的节点key再保存！'}");
						return SUCCESS;
					}
				}
			}
			try {
				BeanUtil.copyNotNullProperties(orgGlobalType, globalType);
				if (StringUtils.isEmpty(isPublic)
						|| isPublic.equalsIgnoreCase("true")) { // 公共
					orgGlobalType.setUserId(null);
				}
				globalTypeService.save(orgGlobalType);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		} else {
			if (globalType.getCatKey().equals("DIC")) {
				List<GlobalType> list = globalTypeService
						.getByCatKey(globalType.getCatKey());
				for (int i = 0; i < list.size(); i++) {
					if (globalType.getNodeKey()
							.equals(list.get(i).getNodeKey())) {
						setJsonString("{success:false,message:'节点Key已存在，请输入唯一的节点key再保存！'}");
						return SUCCESS;
					}
				}
			}
			// 缺省0代表父节点
			String parentPath = "0.";
			int level = 1;
			if (globalType != null && globalType.getParentId() != null
					&& globalType.getParentId() != 0) {
				GlobalType parentType = globalTypeService.get(globalType
						.getParentId());
				if (parentType != null) {
					parentPath = parentType.getPath();
					level = parentType.getDepth() + 1;
				}
			}
			globalType.setDepth(level);

			// set sn
			if (globalType.getCatKey().equals("DIC")) {
				Integer sn = 0;
				GlobalType parentType = globalTypeService.get(globalType
						.getParentId());
				if (globalTypeService.get(globalType.getParentId()) != null) {
					sn = parentType.getSn();
				}

				List<GlobalType> gb = globalTypeService.getByCatKeyAndGTsn(
						globalType.getCatKey(), sn);
				if (gb != null && gb.size() > 0) {
					for (GlobalType global : gb) {
						global.setSn(global.getSn() + 1);
						globalTypeService.save(global);
					}
				}
				globalType.setSn(sn + 1);
			} else {
				Integer sn = globalTypeService.getCountsByParentId(globalType
						.getParentId());
				globalType.setSn(sn + 1);
			}
			if (StringUtils.isNotEmpty(isPublic)
					&& isPublic.equalsIgnoreCase("false")) { // 似有
				AppUser user = ContextUtil.getCurrentUser();
				if (user != null) {
					globalType.setUserId(user.getUserId());
				}
			} else { // 公共
				globalType.setUserId(null);
			}
			globalTypeService.save(globalType);

			globalType.setPath(parentPath + globalType.getProTypeId() + ".");
			if (StringUtils.isNotEmpty(isPublic)
					&& isPublic.equalsIgnoreCase("false")) { // 似有
				AppUser user = ContextUtil.getCurrentUser();
				if (user != null) {
					globalType.setUserId(user.getUserId());
				}
			} else { // 公共
				globalType.setUserId(null);
			}
			globalTypeService.save(globalType);
		}

		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 根据当前用户权限产生流程分类树
	 * 
	 * @return
	 */
	public String flowTree() {
		AppUser curUser = ContextUtil.getCurrentUser();
		List<GlobalType> list = null;
		if (curUser.isSupperManage()) {// 假如是超级管理员,则有全部权限
			list = globalTypeService.getByCatKey(catKey);
		} else {
			List<GlobalType> allList = globalTypeService.getByCatKey(catKey);
			list = globalTypeService.getByRightsCatKey(curUser, catKey);
			list = this.getGlobalTypeRights(list, allList);
		}

		String[] otherNode = { "nodeKey" };
		String method = getRequest().getParameter("method");
		jsonString = JsonTree.generate(getResult(list, method), otherNode,
				false);
		logger.info("tree json:" + jsonString);
		return SUCCESS;
	}

	/**
	 * 解决有子类没父类的bug
	 * 
	 * @param list
	 * @param allList
	 *            所有分类
	 * @return
	 */
	private List<GlobalType> getGlobalTypeRights(List<GlobalType> list,
			List<GlobalType> allList) {
		if (list != null && list.size() > 0) {
			Set<Long> addId = new HashSet<Long>();
			for (GlobalType globalType : list) {// 子类有权限父类就有权限
				if (globalType.getParentId() > 0) {// 判断有没有子类
					Boolean flag = true;
					for (GlobalType type : list) {
						if (type.getProTypeId().longValue() == globalType
								.getParentId()) {
							flag = false;
							break;
						}
					}
					if (flag) {
						addId.add(globalType.getParentId());
					}
				}
			}
			// 增加的ID
			if (addId != null && addId.size() > 0) {
				for (Long id : addId) {
					for (GlobalType globalType : allList) {
						if (globalType.getProTypeId().longValue() == id
								.longValue()) {
							list.add(globalType);
							break;
						}
					}
				}
			}
		}
		return list;
	}

	/**
	 * 获得文件分类
	 * 
	 * @return
	 */
	public String getTypeName() {

		String nodeKey = getRequest().getParameter("nodeKey");
		String typeName = "总分类";
		if (StringUtil.isNotEmpty(nodeKey)) {
			globalType = globalTypeService.findByFileType(nodeKey);
			if (!BeanUtil.isEmpty(globalType)) {
				String path = globalType.getPath();
				String[] pathArray = path.split("\\.");
				typeName = "";
				for (String typeId : pathArray) {
					typeName = typeName + "/" + this.getName(new Long(typeId));
				}

			}
		}
		StringBuffer buff = new StringBuffer("{success:true");
		buff.append(",typeName: '").append(typeName).append("'");
		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	public String getName(Long typeId) {
		String typeName = "总分类";
		if (typeId != 0) {
			typeName = globalTypeService.get(typeId).getTypeName();
		}
		return typeName;

	}

	/**
	 * 获取拼音
	 * 
	 * @return
	 */
	public String getPinyin() {
		String typeName = getRequest().getParameter("typeName");
		String nodeKey = PinyinUtil.getPinyinToLowerCase(typeName);
		jsonString = "{'nodeKey':" + mapper.toJson(nodeKey) + "}";
		return SUCCESS;

	}
}
