package com.htsoft.oa.action.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ProNodeSet;
import com.htsoft.oa.model.flow.ProUserSet;
import com.htsoft.oa.service.flow.ProNodeSetService;
import com.htsoft.oa.service.flow.ProUserSetService;
/**
 * 
 * @author 
 *
 */
public class ProUserSetAction extends BaseAction{
	@Resource
	private ProUserSetService proUserSetService;
	
	@Resource
	private ProNodeSetService proNodeSetService;
	
	private ProUserSet proUserSet;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProUserSet getProUserSet() {
		return proUserSet;
	}

	public void setProUserSet(ProUserSet proUserSet) {
		this.proUserSet = proUserSet;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProUserSet> list= proUserSetService.getAll(filter);
		
		Type type=new TypeToken<List<ProUserSet>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
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
				proUserSetService.remove(new Long(id));
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
		ProUserSet proUserSet=proUserSetService.get(id);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(proUserSet));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		//set sn
		Integer sn= proUserSetService.findByDefIdDeployIdNodeName(proUserSet.getDefId(),proUserSet.getDeployId(),proUserSet.getNodeName()).size();
		proUserSet.setSn(sn+1);
		if(proUserSet.getDemension().getDemId()==null){
			proUserSet.getDemension().setDemId(1L);
		}			
		if(proUserSet.getId()==null){
			proUserSetService.save(proUserSet);
		}else{
			
			ProUserSet orgProUserSet=proUserSetService.get(proUserSet.getId());
			try{
				BeanUtil.copyNotNullProperties(orgProUserSet, proUserSet);
				proUserSetService.save(orgProUserSet);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	
	/**
	 * 显示用户设置列表
	 */
	public String userSetList(){
		String setId = getRequest().getParameter("setId");
		ProNodeSet proNodeSet = proNodeSetService.get(new Long(setId));
	
		List<ProUserSet> list = proUserSetService.findByDefIdDeployIdNodeName(proNodeSet.getDefId(),proNodeSet.getDeployId(),proNodeSet.getNodeName());
		//输出json
		JacksonMapper mapper = new JacksonMapper(true);
		this.setJsonString(mapper.toResultJson(list));
		return SUCCESS;
		
	}
	
	public String mulSave(){
		String data =getRequest().getParameter("data");
		
		logger.info("data:" + data);
		
		if(StringUtils.isNotEmpty(data)){
			JacksonMapper mapper = new JacksonMapper(true);
			ProUserSet[] proUserSets = mapper.toObject(data, ProUserSet[].class);
			
			for(int i=0;i<proUserSets.length;i++){
				ProUserSet newProUserSet=proUserSetService.get(proUserSets[i].getId());
				try{
					BeanUtil.copyNotNullProperties(newProUserSet, proUserSets[i]);
					newProUserSet.setSn(i+1);
					proUserSetService.save(newProUserSet);
				}catch(Exception ex){
					logger.error(ex.getMessage());
				};
			}
		}
		
		jsonString="{success:true}";
		return SUCCESS;
		
	}
		
}
