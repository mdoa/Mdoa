package com.htsoft.oa.action.arch;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.Date;
import java.util.Iterator;
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
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.arch.BorrowRecord;
import com.htsoft.oa.model.arch.RollFile;
import com.htsoft.oa.model.arch.RollFileList;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.arch.BorrowFileListService;
import com.htsoft.oa.service.arch.BorrowRecordService;
import com.htsoft.oa.service.arch.RollFileListService;
import com.htsoft.oa.service.arch.RollFileService;
import com.htsoft.oa.service.system.FileAttachService;

/**
 * 
 * @author
 * 
 */
public class RollFileAction extends BaseAction {
	@Resource
	private RollFileService rollFileService;//文件
	@Resource
	private RollFileListService rollFileListService;//附件
	@Resource
	private FileAttachService fileAttachService;//硬盘件
	@Resource
	private BorrowRecordService borrowRecordService;//借阅
	@Resource
	private BorrowFileListService borrowFileListService;//借阅

	private RollFile rollFile;

	private Long rollFileId;

	public Long getRollFileId() {
		return rollFileId;
	}

	public void setRollFileId(Long rollFileId) {
		this.rollFileId = rollFileId;
	}

	public RollFile getRollFile() {
		return rollFile;
	}

	public void setRollFile(RollFile rollFile) {
		this.rollFile = rollFile;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<RollFile> list = rollFileService.getAll(filter);
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
		jsonString=mapper.toPageJson(list, filter.getPagingBean().getTotalItems());

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

				RollFile file=(RollFile)rollFileService.get(new Long(id));
				
				java.util.Set rollFileLists = file.getRollFileLists(); //全部附件
				Iterator lists = rollFileLists.iterator();
				while (lists.hasNext()) {
					RollFileList list=(RollFileList)lists.next();
					FileAttach fileAttach=list.getFileAttach();  //全部硬件
					rollFileListService.remove(list);
					rollFileListService.flush();
					fileAttachService.removeByPath(fileAttach.getFilePath());
					
				}

				//全部借阅
				java.util.Set borrowFileList_file = file.getBorrowFileList();
				Iterator borrows_file =borrowFileList_file.iterator();//全部借阅
				while (borrows_file.hasNext()) {
					com.htsoft.oa.model.arch.BorrowFileList borr_file=(com.htsoft.oa.model.arch.BorrowFileList)borrows_file.next();
					borrowFileListService.remove(borr_file);
					borrowFileListService.flush();
					//判断登记表 是否还存在文件，没有就删了它
					BorrowRecord record_file=borr_file.getBorrowRecord();
					java.util.Set list_file =record_file.getBorrowFileLists();
					if(list_file==null||list_file.size()==0){
						borrowRecordService.remove(record_file);
					}
					
					
				}
				rollFileService.remove(file);
				rollFileService.flush();
			
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
		RollFile rollFile = rollFileService.get(rollFileId);
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd");
		jsonString=mapper.toDataJson(rollFile);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		Long rid=null;
		if (rollFile.getRollFileId() == null) {
			rollFile.setRollNo(rollFile.getArchRoll().getRollNo());
			rollFile.setAfNo(rollFile.getArchRoll().getArchFond().getAfNo());
			rollFileService.save(rollFile);
			rid=rollFile.getRollFileId();
		} else {
			RollFile orgRollFile = rollFileService.get(rollFile.getRollFileId());
			try {
				Set rollFileList=orgRollFile.getRollFileLists();
				Set borrowFileList=orgRollFile.getBorrowFileList();
				BeanUtil.copyNotNullProperties(orgRollFile, rollFile);
				orgRollFile.setRollFileLists(rollFileList);
				orgRollFile.setBorrowFileList(borrowFileList);
				rollFileService.save(orgRollFile);
				rid=orgRollFile.getRollFileId();
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}

	
		String params = getRequest().getParameter("params");
		if (StringUtils.isNotEmpty(params)) {
			Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			RollFileList[] rls = gson.fromJson(params, RollFileList[].class);
			if (rls != null && rls.length > 0) {
				for (RollFileList rl : rls) {
					rl.setRollFileId(rid);
					rollFileListService.save(rl);

				}
			}

		}

		setJsonString("{success:true,rollFileId:" + rollFile.getRollFileId()
				+ "}");
		return SUCCESS;

	}
	
	
	public String updateDownLoad() {



	
		String params = getRequest().getParameter("params");
		logger.debug("params="+params);
		if (StringUtils.isNotEmpty(params)) {
			Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			RollFileList[] rls = gson.fromJson(params, RollFileList[].class);
			if (rls != null && rls.length > 0) {
				for (RollFileList rl : rls) {
					
					rollFileListService.save(rl);

				}
			}

		}

		setJsonString("{success:true}");
		return SUCCESS;

	}
	
	
	public String tidy(){
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
		.create();
		String params = getRequest().getParameter("params");
		
		if (StringUtils.isNotEmpty(params)) {
			RollFile[] rfs = gson.fromJson(params, RollFile[].class);
			
			if (rfs != null && rfs.length > 0) {
				for (RollFile rollFile : rfs) {
					RollFile orgRollFile = rollFileService.get(rollFile.getRollFileId());
					try {
						Set rollFileList=orgRollFile.getRollFileLists();
						Set borrowFileList=orgRollFile.getBorrowFileList();
						BeanUtil.copyNotNullProperties(orgRollFile, rollFile);
						orgRollFile.setRollFileLists(rollFileList);
						orgRollFile.setBorrowFileList(borrowFileList);
						orgRollFile.setTidyName(ContextUtil.getCurrentUser().getFullname());
						orgRollFile.setTidyTime(new Date());
						rollFileService.save(orgRollFile);
						
					} catch (Exception ex) {
						logger.error(ex.getMessage());
					}
				}
			}

		}

		setJsonString("{success:true}");
		return SUCCESS;
	}

}
