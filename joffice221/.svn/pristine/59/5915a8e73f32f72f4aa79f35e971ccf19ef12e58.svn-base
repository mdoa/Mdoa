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

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.Constants;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.DateUtil;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.web.action.BaseAction;


import com.htsoft.oa.model.arch.ArchRoll;
import com.htsoft.oa.model.arch.BorrowRecord;
import com.htsoft.oa.model.arch.RollFile;
import com.htsoft.oa.model.arch.RollFileList;
import com.htsoft.oa.model.info.News;
import com.htsoft.oa.model.system.FileAttach;

import com.htsoft.oa.service.arch.ArchRollService;
import com.htsoft.oa.service.arch.BorrowFileListService;
import com.htsoft.oa.service.arch.BorrowRecordService;
import com.htsoft.oa.service.arch.RollFileListService;
import com.htsoft.oa.service.arch.RollFileService;
import com.htsoft.oa.service.system.FileAttachService;

import flexjson.transformer.DateTransformer;
import flexjson.JSONSerializer;

/**
 * 
 * @author
 * 
 */
public class ArchRollAction extends BaseAction {
	@Resource
	private ArchRollService archRollService;//案卷
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
	
	private ArchRoll archRoll;

	private Long rollId;

	public Long getRollId() {
		return rollId;
	}

	public void setRollId(Long rollId) {
		this.rollId = rollId;
	}

	public ArchRoll getArchRoll() {
		return archRoll;
	}

	public void setArchRoll(ArchRoll archRoll) {
		this.archRoll = archRoll;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<ArchRoll> list = archRollService.getAll(filter);
		Date curTime = DateUtil.strToDate();
		for (ArchRoll n : list) {
			if (n.getStatus() == 1 && n.getEndTime() != null
					&& n.getEndTime().getTime() <= curTime.getTime()) {
				n.setStatus(Constants.FLAG_DISABLE);
				archRollService.save(n);
			}
		}
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
				archRoll=archRollService.get(new Long(id));
				java.util.Set borrowFileList_roll = archRoll.getBorrowFileList();
				Iterator borrows_roll =borrowFileList_roll.iterator();//全部借阅
				while (borrows_roll.hasNext()) {
					com.htsoft.oa.model.arch.BorrowFileList borr_roll=(com.htsoft.oa.model.arch.BorrowFileList)borrows_roll.next();
					borrowFileListService.remove(borr_roll);
					borrowFileListService.flush();
					//判断登记表 是否还存在文件，没有就删了它
					BorrowRecord record_roll=borr_roll.getBorrowRecord();
					java.util.Set list_roll =record_roll.getBorrowFileLists();
					if(list_roll==null||list_roll.size()==0){
						borrowRecordService.remove(record_roll);
					}				
				}
				java.util.Set rollFiles = archRoll.getRollFiles();
				Iterator files =rollFiles.iterator();//全部文件
				while (files.hasNext()) {
					RollFile file=(RollFile)files.next();
					
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
				archRollService.remove(archRoll);//删除案卷
				archRollService.flush();
				
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
		ArchRoll archRoll = archRollService.get(rollId);

		// 将数据转成JSON格式
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd");
		jsonString=mapper.toDataJson(archRoll);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (archRoll.getRollId() == null) {
			archRollService.save(archRoll);
		} else {
			ArchRoll orgArchRoll = archRollService.get(archRoll.getRollId());
			try {
				Set rollFileSet=orgArchRoll.getRollFiles();
				Set borrowFileList=orgArchRoll.getBorrowFileList();
				BeanUtil.copyNotNullProperties(orgArchRoll, archRoll);
				orgArchRoll.setRollFiles(rollFileSet);
				orgArchRoll.setBorrowFileList(borrowFileList);
				archRollService.save(orgArchRoll);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;

	}
}
