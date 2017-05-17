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
import com.htsoft.oa.model.admin.OfficeGoods;
import com.htsoft.oa.model.admin.OfficeGoodsType;
import com.htsoft.oa.service.admin.OfficeGoodsService;
import com.htsoft.oa.service.admin.OfficeGoodsTypeService;
/**
 * 
 * @author csx
 *
 */
public class OfficeGoodsTypeAction extends BaseAction{
	@Resource
	private OfficeGoodsTypeService officeGoodsTypeService;
	private OfficeGoodsType officeGoodsType;
	@Resource
	private OfficeGoodsService officeGoodsService;
	private Long typeId;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public OfficeGoodsType getOfficeGoodsType() {
		return officeGoodsType;
	}

	public void setOfficeGoodsType(OfficeGoodsType officeGoodsType) {
		this.officeGoodsType = officeGoodsType;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<OfficeGoodsType> list= officeGoodsTypeService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}
	
	/**
	 * 类别树
	 * 
	 */
	
	public String tree(){
		List<OfficeGoodsType> list=officeGoodsTypeService.getAll();
		String method = getRequest().getParameter("method");
		jsonString = JsonTree.generate(getResult(list, method), null, false);
		return SUCCESS;
	}
	
	/**
	 * 产生树的结果
	 * @param list
	 * @param method
	 * @return
	 */
	private List<Object> getResult(List<OfficeGoodsType> list, String method) {
	
		List<Object> dataList = new ArrayList<Object>();
		String parentId = null;
		if (!StringUtils.isNotEmpty(method)) {
			parentId = "0";
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", parentId);
			dataRecord.put("text", "办公用品分类");
			dataRecord.put("parentId", null);
			dataList.add(dataRecord);
	
		}
		for (OfficeGoodsType officeGoodsType : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", officeGoodsType.getTypeId().toString());
			dataRecord.put("text", officeGoodsType.getTypeName());
			dataRecord.put("parentId", parentId);
			dataList.add(dataRecord);
		}
	
		return dataList;
	}

	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				QueryFilter filter=new QueryFilter(getRequest());
				filter.addFilter("Q_officeGoodsType.typeId_L_EQ",id);
				List<OfficeGoods> list=officeGoodsService.getAll(filter);
				if(list.size()>0){
					jsonString="{success:false,message:'该类型下还有用品，请转移后再删除！'}";
					return SUCCESS;
				}
				officeGoodsTypeService.remove(new Long(id));
			}
		}
		jsonString="{success:true}";
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		OfficeGoodsType officeGoodsType=officeGoodsTypeService.get(typeId);
		jsonString = mapper.toDataJson(officeGoodsType);
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		officeGoodsTypeService.save(officeGoodsType);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
