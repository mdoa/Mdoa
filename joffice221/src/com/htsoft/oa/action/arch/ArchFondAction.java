package com.htsoft.oa.action.arch;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.arch.ArchFond;
import com.htsoft.oa.model.arch.ArchRoll;
import com.htsoft.oa.model.arch.BorrowFileList;
import com.htsoft.oa.model.arch.BorrowRecord;
import com.htsoft.oa.model.arch.RollFile;
import com.htsoft.oa.model.arch.RollFileList;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.arch.ArchFondService;
import com.htsoft.oa.service.arch.ArchRollService;
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
public class ArchFondAction extends BaseAction {
	@Resource
	private ArchFondService archFondService;// 全宗
	@Resource
	private ArchRollService archRollService;// 案卷
	@Resource
	private RollFileService rollFileService;// 文件
	@Resource
	private RollFileListService rollFileListService;// 附件
	@Resource
	private FileAttachService fileAttachService;// 硬盘件
	@Resource
	private BorrowRecordService borrowRecordService;// 借阅
	@Resource
	private BorrowFileListService borrowFileListService;// 借阅

	private ArchFond archFond;

	private Long archFondId;

	public Long getArchFondId() {
		return archFondId;
	}

	public void setArchFondId(Long archFondId) {
		this.archFondId = archFondId;
	}

	public ArchFond getArchFond() {
		return archFond;
	}

	public void setArchFond(ArchFond archFond) {
		this.archFond = archFond;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<ArchFond> list = archFondService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String listRollTree() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ArchRoll> list = archRollService.getAll(filter);
		jsonString = JsonTree.generate(getResult(list), null, false);

		// 以下是原来版本
		// if (list != null && list.size() > 0) {
		// ArchRoll archRoll = list.get(0);
		// StringBuffer buff = new StringBuffer("[{id:'0',text:'"
		// + archRoll.getAfNo() + "',expanded:true,children:[");
		// if (list.size() > 0) {
		// for (ArchRoll roll : list) {
		// buff.append("{id:'" + roll.getRollNo())
		// .append("',text:'" + roll.getRollNo())
		// .append("',allowChildren:false,leaf :true},");
		// }
		// buff.deleteCharAt(buff.length() - 1);
		// }
		// buff.append("]}]");
		// jsonString = buff.toString();
		// }
		return SUCCESS;
	}

	/**
	 * 产生树的结果
	 * 
	 * @param list
	 *            树的list
	 * @return
	 */
	private List<Object> getResult(List<ArchRoll> list) {
		List<Object> dataList = new ArrayList<Object>();
		if (list != null && list.size() > 0) {
			HashMap<String, Object> rootNode = new HashMap<String, Object>();
			rootNode.put("id", "0");
			rootNode.put("text", list.get(0).getAfNo());
			rootNode.put("parentId", null);
			dataList.add(rootNode);
		}

		for (ArchRoll archRoll : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", archRoll.getRollNo().toString());
			dataRecord.put("text", archRoll.getRollNo().toString());
			dataRecord.put("parentId", "0");
			dataList.add(dataRecord);
		}

		return dataList;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids == null)
			return SUCCESS;
		for (String id : ids) {
			archFond = archFondService.get(new Long(id));// 全宗
			// 全部借阅
			java.util.Set<BorrowFileList> borrowFileList_fond = archFond
					.getBorrowFileList();
			Iterator<?> borrows_fond = borrowFileList_fond.iterator();// 全部借阅
			while (borrows_fond.hasNext()) {
				com.htsoft.oa.model.arch.BorrowFileList borr_fond = (com.htsoft.oa.model.arch.BorrowFileList) borrows_fond
						.next();
				borrowFileListService.remove(borr_fond);
				borrowFileListService.flush();
				// 判断登记表 是否还存在文件，没有就删了它
				BorrowRecord record_fond = borr_fond.getBorrowRecord();
				java.util.Set<BorrowFileList> list_fond = record_fond
						.getBorrowFileLists();
				if (list_fond == null || list_fond.size() == 0) {
					borrowRecordService.remove(record_fond);
				}

			}

			java.util.Set<ArchRoll> archRolls = archFond.getArchRolls();
			Iterator<?> rolls = archRolls.iterator();// 全部案卷
			while (rolls.hasNext()) {
				com.htsoft.oa.model.arch.ArchRoll archRoll = (com.htsoft.oa.model.arch.ArchRoll) rolls
						.next();
				java.util.Set<BorrowFileList> borrowFileList_roll = archRoll
						.getBorrowFileList();
				Iterator<?> borrows_roll = borrowFileList_roll.iterator();// 全部借阅
				while (borrows_roll.hasNext()) {
					com.htsoft.oa.model.arch.BorrowFileList borr_roll = (com.htsoft.oa.model.arch.BorrowFileList) borrows_roll
							.next();
					borrowFileListService.remove(borr_roll);
					borrowFileListService.flush();
					// 判断登记表 是否还存在文件，没有就删了它
					BorrowRecord record_roll = borr_roll.getBorrowRecord();
					java.util.Set<BorrowFileList> list_roll = record_roll
							.getBorrowFileLists();
					if (list_roll == null || list_roll.size() == 0) {
						borrowRecordService.remove(record_roll);
					}

				}

				java.util.Set<RollFile> rollFiles = archRoll.getRollFiles();
				Iterator<?> files = rollFiles.iterator();// 全部文件
				while (files.hasNext()) {
					RollFile file = (RollFile) files.next();

					java.util.Set<RollFile> rollFileLists = file
							.getRollFileLists(); // 全部附件
					Iterator<?> lists = rollFileLists.iterator();
					while (lists.hasNext()) {
						RollFileList list = (RollFileList) lists.next();
						FileAttach fileAttach = list.getFileAttach(); // 全部硬件
						rollFileListService.remove(list);
						rollFileListService.flush();
						fileAttachService
								.removeByPath(fileAttach.getFilePath());

					}

					// 全部借阅
					java.util.Set<BorrowFileList> borrowFileList_file = file
							.getBorrowFileList();
					Iterator<?> borrows_file = borrowFileList_file.iterator();// 全部借阅
					while (borrows_file.hasNext()) {
						com.htsoft.oa.model.arch.BorrowFileList borr_file = (com.htsoft.oa.model.arch.BorrowFileList) borrows_file
								.next();
						borrowFileListService.remove(borr_file);
						borrowFileListService.flush();
						// 判断登记表 是否还存在文件，没有就删了它
						BorrowRecord record_file = borr_file.getBorrowRecord();
						java.util.Set<BorrowFileList> list_file = record_file
								.getBorrowFileLists();
						if (list_file == null || list_file.size() == 0) {
							borrowRecordService.remove(record_file);
						}

					}
					rollFileService.remove(file);
					rollFileService.flush();
				}
				archRollService.remove(archRoll);// 删除案卷
				archRollService.flush();
			}
			archFondService.remove(archFond);// 删除全宗
			archFondService.flush();
		}

		jsonString = JSON_SUCCESS;

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		ArchFond archFond = archFondService.get(archFondId);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");

		jsonString = mapper.toDataJson(archFond);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (archFond.getGlobalType() != null
				&& archFond.getGlobalType().getProTypeId() != null) {
		} else {
			archFond.setGlobalType(null);
		}
		if (archFond.getArchFondId() == null) {
			archFondService.save(archFond);
		} else {
			ArchFond orgArchFond = archFondService
					.get(archFond.getArchFondId());
			try {
				Set<ArchRoll> archRollSet = orgArchFond.getArchRolls();
				Set<BorrowFileList> borrowFileList = orgArchFond
						.getBorrowFileList();
				BeanUtil.copyNotNullProperties(orgArchFond, archFond);
				orgArchFond.setArchRolls(archRollSet);
				orgArchFond.setBorrowFileList(borrowFileList);
				archFondService.save(orgArchFond);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;

	}
}
