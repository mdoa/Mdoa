package com.htsoft.oa.action.admin;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.InStock;
import com.htsoft.oa.model.admin.OfficeGoods;
import com.htsoft.oa.service.admin.InStockService;
import com.htsoft.oa.service.admin.OfficeGoodsService;
/**
 * 商品入库管理
 * @author csx
 *
 */
public class InStockAction extends BaseAction{
	@Resource
	private InStockService inStockService;
	private InStock inStock;
	@Resource
	private OfficeGoodsService officeGoodsService;
	
	private Long buyId;

	public Long getBuyId() {
		return buyId;
	}

	public void setBuyId(Long buyId) {
		this.buyId = buyId;
	}

	public InStock getInStock() {
		return inStock;
	}

	public void setInStock(InStock inStock) {
		this.inStock = inStock;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<InStock> list= inStockService.getAll(filter);

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
				inStockService.remove(new Long(id));
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
		InStock inStock=inStockService.get(buyId);
		jsonString = mapper.toDataJson(inStock);
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		Integer inCount= inStock.getInCounts();
		BigDecimal price=inStock.getPrice();
		BigDecimal amount=null;
		if(inCount!=null&&price!=null){
			amount=price.multiply(BigDecimal.valueOf(inCount));//总额的计算
		}
		inStock.setAmount(amount);
		Long goodsId=inStock.getGoodsId();
		OfficeGoods goods=officeGoodsService.get(goodsId);
		if(inStock.getBuyId()==null){
		    goods.setStockCounts(goods.getStockCounts()+inStock.getInCounts());
		}else{
			Integer newInCount=inStock.getInCounts(); //修改之后的入库数量
			Integer oldInCount=inStockService.findInCountByBuyId(inStock.getBuyId());//修改前的入库数量
			if(!oldInCount.equals(newInCount)){
				goods.setStockCounts(goods.getStockCounts()-oldInCount+newInCount);
			}
		}
		inStockService.save(inStock);
		officeGoodsService.save(goods);
		setJsonString("{success:true}");
		return SUCCESS;
	}

}
