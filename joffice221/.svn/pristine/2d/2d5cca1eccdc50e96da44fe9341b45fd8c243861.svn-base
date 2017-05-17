package com.htsoft.oa.action.system;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.Demension;
import com.htsoft.oa.service.system.DemensionService;
/**
 * 人员维度
 * @author 
 *
 */
public class DemensionAction extends BaseAction{
	@Resource
	private DemensionService demensionService;
	private Demension demension;
	
	private Long demId;

	public Long getDemId() {
		return demId;
	}

	public void setDemId(Long demId) {
		this.demId = demId;
	}

	public Demension getDemension() {
		return demension;
	}

	public void setDemension(Demension demension) {
		this.demension = demension;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Demension> list= demensionService.getAll(filter);
		
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
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
				demensionService.remove(new Long(id));
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
		Demension demension=demensionService.get(demId);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(demension);
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(demension.getDemId()==null){
			demensionService.save(demension);
		}else{
			Demension orgDemension=demensionService.get(demension.getDemId());
			try{
				BeanUtil.copyNotNullProperties(orgDemension, demension);
				demensionService.save(orgDemension);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	/**
	 * 下拉维度列表
	 * @return
	 */
	public String combo(){
		String idUpdate = getRequest().getParameter("idUpdate");
		List<Demension> list=demensionService.getAll();
		StringBuffer sb=new StringBuffer();
		if("true".equals(idUpdate)){
			sb=new StringBuffer("[");
		}else{
			sb=new StringBuffer("[['0','全部..']");
		} 
		for(Demension dem:list){
			if(sb.length()>1){
				sb.append(",");
			}
			sb.append("['").append(dem.getDemId()).append("','").append(dem.getDemName()).append("']");
		}
		sb.append("]");
		jsonString=sb.toString();
		return SUCCESS;
	}
}
