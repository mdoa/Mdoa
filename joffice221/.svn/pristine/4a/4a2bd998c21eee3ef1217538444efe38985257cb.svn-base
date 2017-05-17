package com.htsoft.oa.action.admin;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.OfficeGoods;
import com.htsoft.oa.service.admin.OfficeGoodsService;
/**
 * 办公用品管理
 * @author csx
 *
 */
public class OfficeGoodsAction extends BaseAction{
	@Resource
	private OfficeGoodsService officeGoodsService;
	private OfficeGoods officeGoods;
	
	private Long goodsId;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public OfficeGoods getOfficeGoods() {
		return officeGoods;
	}

	public void setOfficeGoods(OfficeGoods officeGoods) {
		this.officeGoods = officeGoods;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<OfficeGoods> list= officeGoodsService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				officeGoodsService.remove(new Long(id));
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
		OfficeGoods officeGoods=officeGoodsService.get(goodsId);
		jsonString = mapper.toDataJson(officeGoods);
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		officeGoodsService.save(officeGoods);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
