package com.htsoft.oa.action.arch;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.arch.BorrowFileList;
import com.htsoft.oa.model.arch.BorrowRecord;
import com.htsoft.oa.service.arch.BorrowFileListService;
import com.htsoft.oa.service.arch.BorrowRecordService;
/**
 * 
 * @author 
 *
 */
public class BorrowRecordAction extends BaseAction{
	@Resource
	private BorrowRecordService borrowRecordService;
	private BorrowRecord borrowRecord;
	@Resource
	private BorrowFileListService borrowFileListService;
	
	private Long recordId;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public BorrowRecord getBorrowRecord() {
		return borrowRecord;
	}

	public void setBorrowRecord(BorrowRecord borrowRecord) {
		this.borrowRecord = borrowRecord;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<BorrowRecord> list= borrowRecordService.getAll(filter);
		
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd");
		jsonString=mapper.toPageJson(list, filter.getPagingBean().getTotalItems());
		return SUCCESS;
	}
	/**
	 * 是否 有超期的借阅记录
	 */
	public String isExist(){	
		Integer OverDueNum=borrowRecordService.OverDue(ContextUtil.getCurrentUserId());
		jsonString="{success:false}";
		if(OverDueNum>0){
			jsonString="{success:true}";
		}
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
				borrowRecordService.remove(new Long(id));
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
		BorrowRecord borrowRecord=borrowRecordService.get(recordId);
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd");
		jsonString = mapper.toDataJson(borrowRecord);
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		
		if (borrowRecord.getRecordId()== null) {
			borrowRecordService.save(borrowRecord);
		} else {
			BorrowRecord orgBorrowRecord = borrowRecordService.get(borrowRecord.getRecordId());
			try {
				Set borrowFileLists = orgBorrowRecord.getBorrowFileLists();
				BeanUtil.copyNotNullProperties(orgBorrowRecord, borrowRecord);
				orgBorrowRecord.setBorrowFileLists(borrowFileLists);
				borrowRecordService.save(orgBorrowRecord);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		
		
		String params = getRequest().getParameter("params");
		if (StringUtils.isNotEmpty(params)) {
			Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			BorrowFileList[] bfl = gson.fromJson(params, BorrowFileList[].class);
			if (bfl != null && bfl.length > 0) {
				for (BorrowFileList l : bfl) {
					
					l.setRecordId(borrowRecord.getRecordId());
					
					borrowFileListService.save(l);

				}
			}

		}

		setJsonString("{success:true,recordId:" + borrowRecord.getRecordId()
				+ "}");
		
		
		
		return SUCCESS;
	}
	public String check(){
		
		BorrowRecord r=borrowRecordService.get(borrowRecord.getRecordId());
		r.setReturnStatus(borrowRecord.getReturnStatus());
		r.setCheckId(ContextUtil.getCurrentUserId());
		r.setCheckName(ContextUtil.getCurrentUser().getUsername());
		borrowRecordService.save(r);
		
		setJsonString("{success:true,recordId:" + r.getRecordId()
				+ "}");
		
		
		
		return SUCCESS;
	}
}
