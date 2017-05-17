package com.htsoft.oa.action.admin;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.DepreType;
import com.htsoft.oa.model.admin.FixedAssets;
import com.htsoft.oa.service.admin.DepreTypeService;
import com.htsoft.oa.service.admin.FixedAssetsService;
/**
 * 
 * @author csx
 *
 */
public class FixedAssetsAction extends BaseAction{
	@Resource
	private FixedAssetsService fixedAssetsService;
	private FixedAssets fixedAssets;
	
	@Resource
	private DepreTypeService depreTypeService;
	
	private Long assetsId;

	public Long getAssetsId() {
		return assetsId;
	}

	public void setAssetsId(Long assetsId) {
		this.assetsId = assetsId;
	}

	public FixedAssets getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(FixedAssets fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<FixedAssets> list= fixedAssetsService.getAll(filter);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
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
				fixedAssetsService.remove(new Long(id));
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
		FixedAssets fixedAssets=fixedAssetsService.get(assetsId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
		jsonString = mapper.toDataJson(fixedAssets);
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
	    Long typeId=fixedAssets.getDepreType().getDepreTypeId();
	    if(typeId!=null){
	    	DepreType depreType =depreTypeService.get(typeId);
		    if(depreType.getCalMethod()!=2){//不为工作量折算时
			    BigDecimal remainRate=fixedAssets.getRemainValRate();
			    BigDecimal depreRate=new BigDecimal("1").subtract(remainRate.divide(new BigDecimal("100"))).divide(fixedAssets.getIntendTerm(),2,2);  //折旧率
			    fixedAssets.setDepreRate(depreRate);
		    }
	    }
	    fixedAssetsService.save(fixedAssets);
		setJsonString("{success:true}");
		return SUCCESS;
	}


}
