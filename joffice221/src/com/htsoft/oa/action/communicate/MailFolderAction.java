package com.htsoft.oa.action.communicate;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.MailBox;
import com.htsoft.oa.model.communicate.MailFolder;
import com.htsoft.oa.service.communicate.MailBoxService;
import com.htsoft.oa.service.communicate.MailFolderService;

/**
 * 
 * @author csx
 * 
 */
public class MailFolderAction extends BaseAction {
	@Resource
	private MailFolderService mailFolderService;
	@Resource
	private MailBoxService mailBoxService;

	private MailFolder mailFolder;

	private Long folderId;

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public MailFolder getMailFolder() {
		return mailFolder;
	}

	public void setMailFolder(MailFolder mailFolder) {
		this.mailFolder = mailFolder;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		Long curUserId = ContextUtil.getCurrentUserId();
		String[] otherNode = { "iconCls" };
		List<MailFolder> list = mailFolderService.getMailFolderByParentId(0L);// 默认的节点
		List<MailFolder> userList = mailFolderService.getUserFolder(curUserId);// 当前用户的文件夹
		if (userList != null) {
			for (MailFolder mailFolder : userList) {
				list.add(mailFolder);
			}
		}
		jsonString = JsonTree.generate(getResult(list), otherNode, false);
		logger.info("tree json:" + jsonString);
		return SUCCESS;

	}

	/**
	 * 产生树的结果
	 * 
	 * @param lista
	 * @return
	 */
	private List<Object> getResult(List<MailFolder> list) {
		List<Object> dataList = new ArrayList<Object>();

		HashMap<String, Object> rootNode = new HashMap<String, Object>();
		rootNode.put("id", "0");
		rootNode.put("text", "我的邮箱");
		rootNode.put("parentId", null);
		rootNode.put("iconCls", "menu-mail_box");

		dataList.add(rootNode);
		for (MailFolder folder : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", folder.getFolderId().toString());
			dataRecord.put("text", folder.getFolderName());
			String parentId = folder.getParentId() == null ? "0" : folder
					.getParentId().toString();
			dataRecord.put("parentId", parentId);
			dataRecord.put("iconCls",
					this.getIconCls(folder.getFolderType().shortValue()));
			dataList.add(dataRecord);
		}

		return dataList;
	}

	/**
	 * 获取图标（IcoCls）
	 * 
	 * @param folderType
	 * @return
	 */
	private String getIconCls(short folderType) {
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
	 * 批量删除--未用到
	 * 
	 * @return
	 */
	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				mailFolderService.remove(new Long(id));
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
		MailFolder tmpFolder = mailFolderService.get(new Long(folderId));
		List<MailFolder> mailFolderList = mailFolderService
				.getFolderLikePath(tmpFolder.getPath());
		// 查询出该目录及其子目录下的邮件数
		Long total = 0l;
		for (MailFolder folder : mailFolderList) {
			Long count = mailBoxService.CountByFolderId(folder.getFolderId());
			total += count;
		}

		setJsonString("{success:true,count:" + total + "}");
		return SUCCESS;
	}

	/**
	 * 删除文件
	 * 
	 * @return
	 */
	public String remove() {
		String count = getRequest().getParameter("count");
		if (folderId != null) {
			MailFolder tmpFolder = mailFolderService.get(new Long(folderId));
			List<MailFolder> mailFolderList = mailFolderService
					.getFolderLikePath(tmpFolder.getPath());

			// 假如文件夹中的邮件数大于0,则把邮件转到删除箱中
			if (count != null && new Long(count) > 0) {
				MailFolder deleteFolder = mailFolderService.get(4l);// 获得删除箱
				for (MailFolder folder : mailFolderList) {
					List<MailBox> mailBoxList = mailBoxService
							.findByFolderId(folder.getFolderId());
					for (MailBox mailBox : mailBoxList) {
						mailBox.setMailFolder(deleteFolder);
						mailBoxService.save(mailBox);
					}
				}
			}

			// 批量删除其下的目录
			for (MailFolder folder : mailFolderList) {
				mailFolderService.remove(folder.getFolderId());
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
		MailFolder mailFolder = mailFolderService.get(folderId);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
		jsonString = mapper.toDataJson(mailFolder);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		MailFolder parentFolder = null;
		Long parentId = mailFolder.getParentId();
		if (parentId == null
				|| parentId.longValue() == MailFolder.FIRST_PARENTID
						.longValue()) {
			mailFolder.setParentId(MailFolder.FIRST_PARENTID);
			mailFolder.setDepLevel(MailFolder.FIRST_LEVEL);
		} else {
			parentFolder = mailFolderService.get(parentId);
			mailFolder.setDepLevel(parentFolder.getDepLevel() + 1);
		}
		mailFolder.setFolderType(MailFolder.FOLDER_TYPE_OTHER);
		mailFolder.setUserId(ContextUtil.getCurrentUserId());
		mailFolderService.save(mailFolder);

		// 保存后获得folderId，根据folderId设置path
		if (mailFolder.getParentId().longValue() == MailFolder.FIRST_PARENTID
				.longValue()) {
			mailFolder.setPath("0." + mailFolder.getFolderId() + ".");
		} else {
			mailFolder.setPath(parentFolder.getPath()
					+ mailFolder.getFolderId() + ".");
		}
		mailFolderService.save(mailFolder);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
