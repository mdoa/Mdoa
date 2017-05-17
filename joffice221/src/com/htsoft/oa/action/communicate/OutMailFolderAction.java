package com.htsoft.oa.action.communicate;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.MailFolder;
import com.htsoft.oa.model.communicate.OutMail;
import com.htsoft.oa.model.communicate.OutMailFolder;
import com.htsoft.oa.model.communicate.OutMailUserSeting;
import com.htsoft.oa.service.communicate.OutMailFolderService;
import com.htsoft.oa.service.communicate.OutMailService;
import com.htsoft.oa.service.communicate.OutMailUserSetingService;

/**
 * 外部邮箱 文件夹
 * 
 * @author
 * 
 */
public class OutMailFolderAction extends BaseAction {

	@Resource
	private OutMailFolderService outMailFolderService;
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	private OutMailFolder outMailFolder;
	@Resource
	private OutMailService outMailService;

	private Long folderId;

	private Long setId;

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public OutMailFolder getOutMailFolder() {
		return outMailFolder;
	}

	public void setOutMailFolder(OutMailFolder outMailFolder) {
		this.outMailFolder = outMailFolder;
	}

	/**
	 * @return the setId
	 */
	public Long getSetId() {
		return setId;
	}

	/**
	 * @param setId
	 *            the setId to set
	 */
	public void setSetId(Long setId) {
		this.setId = setId;
	}

	/**
	 * 显示列表 树
	 */
	public String tree() {

		Long curUserId = ContextUtil.getCurrentUserId();
		String defAccountName = "";
		OutMailUserSeting defaultSet = outMailUserSetingService
				.getDefault(curUserId);
		if (defaultSet != null) {
			defAccountName = defaultSet.getAccountName();
		}

		List<OutMailUserSeting> outMailUserSetingList = outMailUserSetingService
				.getByLoginId(curUserId); // 当前用户邮箱节点
		List<OutMailFolder> outMailFolderList = outMailFolderService
				.findListByUserId(curUserId); // 当前用户文件夹

		String[] otherNode = { "iconCls", "folderType", "accountName" };
		jsonString = JsonTree.generate(
				getResult(outMailUserSetingList, outMailFolderList,
						defAccountName), otherNode, true);
		logger.info("tree json:" + jsonString);

		return SUCCESS;

	}

	/**
	 * 产生树的结果
	 * 
	 * @param lista
	 * @return
	 */
	private List<Object> getResult(List<OutMailUserSeting> userSetingList,
			List<OutMailFolder> list, String defAccountName) {
		List<Object> dataList = new ArrayList<Object>();
		String rootId = "0.0";
		HashMap<String, Object> rootNode = new HashMap<String, Object>();
		rootNode.put("id", rootId);
		rootNode.put("text", "外部邮箱");
		rootNode.put("parentId", null);
		rootNode.put("iconCls", "menu-mail");
		rootNode.put("folderType", -1);
		rootNode.put("accountName", defAccountName);
		rootNode.put("sn", "1");

		dataList.add(rootNode);
		if (userSetingList != null && userSetingList.size() > 0) {
			for (OutMailUserSeting userSeting : userSetingList) {
				HashMap<String, Object> dataRecord = new HashMap<String, Object>();
				dataRecord.put("id", userSeting.getSetId().toString() + ".0");
				dataRecord.put("text", userSeting.getAccountName());
				dataRecord.put("parentId", rootId);
				dataRecord.put("iconCls", "menu-mail_box");
				dataRecord.put("folderType", 0);
				dataRecord.put("accountName", userSeting.getAccountName());
				dataRecord.put("sn", userSeting.getSetId().toString());
				dataList.add(dataRecord);
			}
		}
		if (list != null && list.size() > 0) {
			for (OutMailFolder folder : list) {
				HashMap<String, Object> dataRecord = new HashMap<String, Object>();
				Long setId = folder.getOutMailUserSeting().getSetId();
				dataRecord.put("id", setId + "."
						+ folder.getFolderId().toString());
				dataRecord.put("text", folder.getFolderName());
				String folderParentId = setId + ".0";
				if (folder.getParentId().longValue() != 0) {
					folderParentId = setId + "."
							+ folder.getParentId().toString();
				}
				dataRecord.put("parentId", folderParentId);
				dataRecord.put("iconCls",
						this.getIconCls(folder.getFolderType().shortValue()));

				dataRecord.put("folderType", folder.getFolderType());
				dataRecord.put("accountName", folder.getOutMailUserSeting()
						.getAccountName());
				dataRecord.put("sn", folder.getFolderId().toString());
				dataList.add(dataRecord);
			}
		}

		return dataList;
	}

	/**
	 * 获取图标（IcoCls）
	 * 
	 * @param folderType
	 * @return
	 */
	private Object getIconCls(short folderType) {
		if (folderType == MailFolder.FOLDER_TYPE_RECEIVE.shortValue()) {// 收件箱图标
			return "menu-mail_inbox";
		} else if (folderType == MailFolder.FOLDER_TYPE_SEND.shortValue()) {// 发件箱图标
			return "menu-mail_outbox";
		} else if (folderType == MailFolder.FOLDER_TYPE_DRAFT.shortValue()) {// 草稿箱图标
			return "menu-mail_drafts";
		} else if (folderType == MailFolder.FOLDER_TYPE_DELETE.shortValue()) {// 垃圾箱图标
			return "menu-mail_trash";
		} else {// 其他文件夹图标
			return "menu-mail_folder";
		}
	}

	/**
	 * 移动文件夹-树
	 * 
	 * @return
	 */
	public String moveTree() {
		Long curUserId = ContextUtil.getCurrentUserId();
		if (setId == null) {
			jsonString = JSON_FAILURE;
			return SUCCESS;
		}

		List<OutMailFolder> outMailFolderList = outMailFolderService
				.findListByUserIdAndSetId(curUserId, setId); // 当前用户文件夹

		String[] otherNode = { "iconCls", "folderType", "accountName" };
		jsonString = JsonTree.generate(getTreeResult(outMailFolderList),
				otherNode, true);
		logger.info("tree json:" + jsonString);

		return SUCCESS;

	}

	/**
	 * 拼接树结果
	 * 
	 * @param list
	 * @return
	 */
	private List<Object> getTreeResult(List<OutMailFolder> list) {
		List<Object> dataList = new ArrayList<Object>();
		if (list != null && list.size() > 0) {
			for (OutMailFolder folder : list) {
				HashMap<String, Object> dataRecord = new HashMap<String, Object>();
				dataRecord.put("id", folder.getFolderId().toString());
				dataRecord.put("text", folder.getFolderName());
				dataRecord.put("parentId",
						(folder.getParentId().longValue() == 0L ? null : folder
								.getParentId().toString()));
				dataRecord.put("iconCls",
						this.getIconCls(folder.getFolderType().shortValue()));

				dataRecord.put("folderType", folder.getFolderType());
				dataRecord.put("accountName", folder.getOutMailUserSeting()
						.getAccountName());
				dataRecord.put("sn", folder.getFolderId().toString());
				dataList.add(dataRecord);
			}
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
		if (ids != null) {
			for (String id : ids) {
				outMailFolderService.remove(new Long(id));
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
		OutMailFolder outMailFolder = outMailFolderService.get(folderId);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
		jsonString = mapper.toDataJson(outMailFolder);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {

		OutMailFolder parentFolder = null;
		System.out.println("outMailFolder=" + outMailFolder);
		Long parentId = outMailFolder.getParentId();
		System.out.println(parentId);
		if (parentId == null
				|| parentId.longValue() == OutMailFolder.FIRST_PARENTID
						.longValue()) {
			outMailFolder.setParentId(OutMailFolder.FIRST_PARENTID);
			outMailFolder.setDepLevel(OutMailFolder.FIRST_LEVEL);
			System.out.println("11111outMailFolder" + outMailFolder);
		} else {
			parentFolder = outMailFolderService.get(parentId);
			outMailFolder.setDepLevel(parentFolder.getDepLevel() + 1);
			outMailFolder.setOutMailUserSeting(parentFolder
					.getOutMailUserSeting());
			System.out.println("2222outMailFolder" + outMailFolder);
			System.out.println("2222parentFolder" + parentFolder);
		}
		outMailFolder.setFolderType(OutMailFolder.FOLDER_TYPE_OTHER);
		outMailFolder.setUserId(ContextUtil.getCurrentUserId());

		outMailFolderService.save(outMailFolder);

		// 保存后获得folderId，根据folderId设置path
		if (outMailFolder.getParentId().longValue() == OutMailFolder.FIRST_PARENTID
				.longValue()) {
			outMailFolder.setPath("0." + outMailFolder.getFolderId() + ".");
		} else {
			outMailFolder.setPath(parentFolder.getPath()
					+ outMailFolder.getFolderId() + ".");
		}
		outMailFolderService.save(outMailFolder);
		setJsonString("{success:true}");
		return SUCCESS;

	}

	/**
	 * 删除文件
	 * 
	 * @return
	 */
	public String remove() {
		long count = RequestUtil.getLong(getRequest(), "count");
		if (folderId != null) {
			OutMailFolder tmpFolder = outMailFolderService.get(new Long(
					folderId));
			// 取得这个目录下的所有下级目录
			List<OutMailFolder> outMailFolderList = outMailFolderService
					.getFolderLikePath(tmpFolder.getPath());

			// 假如文件夹中的邮件数大于0,则把邮件转到删除箱中
			if (count > 0) {
				short folderId = OutMailFolder.FOLDER_TYPE_DELETE.shortValue();
				OutMailFolder deleteFolder = outMailFolderService.get(new Long(
						folderId));// 获得删除箱
				for (OutMailFolder folder : outMailFolderList) {
					List<OutMail> outMailList = outMailService
							.findByFolderId(folder.getFolderId());
					for (OutMail outMail : outMailList) {
						outMail.setOutMailFolder(deleteFolder);
						outMailService.save(outMail);
					}
				}
			}

			// 批量删除其下的目录
			for (OutMailFolder folder : outMailFolderList) {
				outMailFolderService.remove(folder.getFolderId());
			}
		}

		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 删除文件夹时查询出该文件夹及其子文件夹的邮件数
	 * 
	 * @return
	 */
	public String count() {
		OutMailFolder tmpFolder = outMailFolderService.get(new Long(folderId));
		List<OutMailFolder> outMailFolderList = outMailFolderService
				.getFolderLikePath(tmpFolder.getPath());
		// 查询出该目录及其子目录下的邮件数
		Long total = 0l;
		for (OutMailFolder folder : outMailFolderList) {
			Long count = outMailService.CountByFolderId(folder.getFolderId());
			total += count;
		}

		setJsonString("{success:true,count:" + total + "}");
		return SUCCESS;
	}

}
