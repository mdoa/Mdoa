package com.htsoft.oa.action.archive;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.archive.ArchivesDep;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.archive.ArchivesDepService;
/**
 * 
 * @author 
 *
 */
public class ArchivesDepAction extends BaseAction{
	@Resource
	private ArchivesDepService archivesDepService;
	private ArchivesDep archivesDep;
	
	private Long archDepId;

	public Long getArchDepId() {
		return archDepId;
	}

	public void setArchDepId(Long archDepId) {
		this.archDepId = archDepId;
	}

	public ArchivesDep getArchivesDep() {
		return archivesDep;
	}

	public void setArchivesDep(ArchivesDep archivesDep) {
		this.archivesDep = archivesDep;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		QueryFilter filter=new QueryFilter(getRequest());
		AppUser curUser = ContextUtil.getCurrentUser();
		filter.addFilter("Q_department.depId_L_EQ", curUser.getDepartment().getDepId().toString());
		filter.addFilter("Q_status_SN_EQ", ArchivesDep.STATUS_UNSIGNED.toString());
		List<ArchivesDep> list= archivesDepService.getAll(filter);
		
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
				archivesDepService.remove(new Long(id));
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
		ArchivesDep archivesDep=archivesDepService.get(archDepId);
		
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm");
		jsonString = mapper.toDataJson(archivesDep);
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		archivesDepService.save(archivesDep);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
