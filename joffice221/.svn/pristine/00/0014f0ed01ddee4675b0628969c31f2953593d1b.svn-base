package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.AssetsType;
import com.htsoft.oa.model.admin.FixedAssets;
import com.htsoft.oa.service.admin.AssetsTypeService;
import com.htsoft.oa.service.admin.FixedAssetsService;

/**
 * 资产类型Action
 * 
 * @company <a href="http://www.Mendersoft.com">杭州梦德软件有限公司</a>
 * @description 资产类型
 * @author csx
 */
public class AssetsTypeAction extends BaseAction {
	@Resource
	private AssetsTypeService assetsTypeService;
	@Resource
	private FixedAssetsService fixedAssetsService;

	private AssetsType assetsType;

	private Long assetsTypeId;

	/**
	 * @return the assetsType
	 */
	public AssetsType getAssetsType() {
		return assetsType;
	}

	/**
	 * @param assetsType
	 *            the assetsType to set
	 */
	public void setAssetsType(AssetsType assetsType) {
		this.assetsType = assetsType;
	}

	/**
	 * @return the assetsTypeId
	 */
	public Long getAssetsTypeId() {
		return assetsTypeId;
	}

	/**
	 * @param assetsTypeId
	 *            the assetsTypeId to set
	 */
	public void setAssetsTypeId(Long assetsTypeId) {
		this.assetsTypeId = assetsTypeId;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<AssetsType> list = assetsTypeService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 资产类型树
	 * 
	 */
	public String tree() {
		List<AssetsType> list = assetsTypeService.getAll();
		String method = getRequest().getParameter("method");
		jsonString = JsonTree.generate(getResult(list, method), null, false);
		return SUCCESS;
	}

	/**
	 * 产生树的结果
	 * 
	 * @param list
	 *            类型树列表
	 * @param method
	 *            是否有父节点
	 * @return
	 */
	private List<Object> getResult(List<AssetsType> list, String method) {

		List<Object> dataList = new ArrayList<Object>();
		String parentId = null;
		if (!StringUtils.isNotEmpty(method)) {
			parentId = "0";
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", parentId);
			dataRecord.put("text", "资产类型");
			dataRecord.put("parentId", null);
			dataList.add(dataRecord);

		}
		for (AssetsType type : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", type.getAssetsTypeId().toString());
			dataRecord.put("text", type.getTypeName());
			dataRecord.put("parentId", parentId);
			dataList.add(dataRecord);
		}

		return dataList;
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
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_assetsType.assetsTypeId_L_EQ", id);
				List<FixedAssets> list = fixedAssetsService.getAll(filter);
				if (list.size() > 0) {
					jsonString = "{success:false,message:'该类型下还有资产，请将资产移走后再进行删除！'}";
					return SUCCESS;
				}
				assetsTypeService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		AssetsType assetsType = assetsTypeService.get(assetsTypeId);
		jsonString = mapper.toDataJson(assetsType);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		assetsTypeService.save(assetsType);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 获取类型下拉框
	 */
	public String combox() {
		List<AssetsType> list = assetsTypeService.getAll();
		StringBuffer buff = new StringBuffer("[");
		for (AssetsType assetsType : list) {
			buff.append("['" + assetsType.getAssetsTypeId() + "','"
					+ assetsType.getTypeName() + "'],");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return SUCCESS;
	}
}
