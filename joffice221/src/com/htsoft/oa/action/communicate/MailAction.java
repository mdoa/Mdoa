package com.htsoft.oa.action.communicate;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.Mail;
import com.htsoft.oa.model.communicate.MailBox;
import com.htsoft.oa.model.communicate.MailFolder;
import com.htsoft.oa.model.info.ShortMessage;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.communicate.MailBoxService;
import com.htsoft.oa.service.communicate.MailFolderService;
import com.htsoft.oa.service.communicate.MailService;
import com.htsoft.oa.service.info.ShortMessageService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.FileAttachService;

import flexjson.JSONSerializer;

/**
 * 邮件
 * 
 * @author csx
 * 
 */
public class MailAction extends BaseAction {

	@Resource
	private MailService mailService;
	@Resource
	private FileAttachService fileAttachService;
	@Resource
	private AppUserService appUserService;
	@Resource
	private MailFolderService mailFolderService;
	@Resource
	private MailBoxService mailBoxService;
	@Resource
	private ShortMessageService shortMessageService;

	private Mail mail;

	private Long mailId;

	private AppUser appUser;

	private Long folderId;

	private Long boxId;

	private String sendMessage;

	private Long replyBoxId;

	private String boxIds;// 移动邮件的集合

	private Long fileId;// 附件Id

	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Long getFolderId() {
		if (folderId == null) {
			return 1l;
		} else {
			return folderId;
		}
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getBoxId() {
		return boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	public String getBoxIds() {
		return boxIds;
	}

	public void setBoxIds(String boxIds) {
		this.boxIds = boxIds;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public Long getReplyBoxId() {
		return replyBoxId;
	}

	public void setReplyBoxId(Long replyBoxId) {
		this.replyBoxId = replyBoxId;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		if (folderId == null
				|| folderId.longValue() == MailFolder.FOLDER_TYPE_RECEIVE
						.shortValue()) {// folderId = 1 表示收件箱
			setFolderId(new Long(MailFolder.FOLDER_TYPE_RECEIVE));
		}
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.addFilter("Q_mailFolder.folderId_L_EQ", folderId.toString());
		if (folderId.longValue() != MailFolder.FOLDER_TYPE_DELETE.shortValue()) {
			filter.addFilter("Q_delFlag_SN_EQ", "0");// 假如不是删除箱的数据则只取出未删除的数据
		}
		filter.addSorted("sendTime", "desc");
		List<MailBox> list = mailBoxService.getAll(filter);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		MailFolder deleteFolder = mailFolderService.get(new Long(
				MailFolder.FOLDER_TYPE_DELETE));
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {

			System.out.println(getFolderId());
			if (getFolderId().longValue() == MailFolder.FOLDER_TYPE_DELETE
					.shortValue()) {
				for (String id : ids) {
					mailBoxService.remove(new Long(id));
				}
			} else {
				for (String id : ids) {
					MailBox mailBoxDelete = mailBoxService.get(new Long(id));
					mailBoxDelete.setDelFlag(Mail.HAVE_DELETE);
					mailBoxDelete.setMailFolder(deleteFolder);
					mailBoxService.save(mailBoxDelete);
				}
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
		MailBox mailBox = mailBoxService.get(boxId);
		setMail(mailBox.getMail());
		mailBox.setReadFlag(Mail.HAVE_READ);
		mailBoxService.save(mailBox);

		if (mail.getMailStatus() != 1) {// 假如为草稿
			JSONSerializer serializer = new JSONSerializer();
			// 将数据转成JSON格式
			StringBuffer sb = new StringBuffer(
					"{success:true,totalCounts:1,data:");
			sb.append(serializer.exclude(
					new String[] { "class", "mail.appUser",
							"appUser.department", "mailFolder.appUser" })
					.serialize(mail));
			sb.append("}");
			setJsonString(sb.toString());
			return SUCCESS;
		} else {
			getRequest().setAttribute("mail", mail);
			getRequest().setAttribute("boxId", mailBox.getBoxId());

			getRequest().setAttribute("mailAttachs", mail.getMailAttachs());
			return "detail";
		}

	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		
		if (mail.getMailStatus().shortValue() == Mail.IS_MAIL.shortValue()) {
			// 假如发送邮件,发件人不能为空
			if (StringUtils.isEmpty(mail.getRecipientIDs())) {
				setJsonString("{failure:true,msg:'收件人不能为空!'}");
				return SUCCESS;
			}
			// 邮件主题不能为空
			if (StringUtils.isEmpty(mail.getSubject())) {
				setJsonString("{failure:true,msg:'邮件主题不能为空!'}");
				return SUCCESS;
			}
			// 邮件内容不能为空
			// if (StringUtils.isEmpty(mail.getContent())) {
			// setJsonString("{failure:true,msg:'邮件内容不能为空!'}");
			// return SUCCESS;
			// }
			// 假如是回复邮件,则原邮件改回复标识
			if (replyBoxId != null && !"".equals(replyBoxId)) {
				MailBox reply = mailBoxService.get(replyBoxId);
				reply.setReplyFlag(Mail.HAVE_READ);
				mailBoxService.save(reply);
			}

			MailFolder receiveFolder = mailFolderService.get(new Long(
					MailFolder.FOLDER_TYPE_RECEIVE));// 收件箱
			MailFolder sendFolder = mailFolderService.get(new Long(
					MailFolder.FOLDER_TYPE_SEND));// 发件箱
			String[] recipientIDs = mail.getRecipientIDs().split(",");// 收件人IDs
			String[] copyToIDs = mail.getCopyToIDs().split(",");// 抄送人IDs

			if (mail.getMailId() == null) {// 当该邮件是新建邮件时
				SaveMail();

				// 邮件保存到已发送

				MailBox mailBox = new MailBox();
				mailBox.setMail(mail);
				mailBox.setMailFolder(sendFolder);
				mailBox.setAppUser(ContextUtil.getCurrentUser());
				mailBox.setSendTime(mail.getSendTime());
				mailBox.setDelFlag(Mail.NOT_DELETE);
				mailBox.setReadFlag(Mail.NOT_READ);
				mailBox.setNote("已发送的邮件");
				mailBox.setReplyFlag(Mail.NOT_REPLY);
				mailBoxService.save(mailBox);
			} else {
				Mail old = mailService.get(mail.getMailId());
				try {
					BeanUtil.copyNotNullProperties(old, mail);
					Set<FileAttach> mailAttachs = new HashSet<FileAttach>();
					old.setSendTime(new Date());
					String[] fileIds = mail.getFileIds().split(",");
					for (String id : fileIds) {
						if (!id.equals("")) {
							mailAttachs
									.add(fileAttachService.get(new Long(id)));
						}
					}
					old.setMailAttachs(mailAttachs);
					setMail(old);
					mailService.save(old);
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
				MailBox drafted = mailBoxService.get(boxId);
				drafted.setMailFolder(sendFolder);
				drafted.setNote("已发送的邮件");
				mailBoxService.save(drafted);
			}
			// 发送系统提示信息
			if (sendMessage != null && sendMessage.equals("on")) {
				StringBuffer msgContent = new StringBuffer(
						"<font color=\"green\">");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				msgContent.append(mail.getSender()).append("</font>")
						.append("在<font color=\"red\">")
						.append(sdf.format(mail.getSendTime()))
						.append("</font>").append("给您发了一封邮件，请注意查收。");
				shortMessageService.save(AppUser.SYSTEM_USER,
						mail.getRecipientIDs(), msgContent.toString(),
						ShortMessage.MSG_TYPE_SYS);
			}
			// 发送邮件
			for (String id : recipientIDs) {
				if (!"".equals(id)) {
					MailBox mailBoxSend = new MailBox();
					mailBoxSend.setMail(mail);
					mailBoxSend.setMailFolder(receiveFolder);
					mailBoxSend.setAppUser(appUserService.get(new Long(id)));
					mailBoxSend.setSendTime(mail.getSendTime());
					mailBoxSend.setDelFlag(Mail.NOT_DELETE);
					mailBoxSend.setReadFlag(Mail.NOT_READ);
					mailBoxSend.setNote("发送出去的邮件");
					mailBoxSend.setReplyFlag(Mail.NOT_REPLY);
					mailBoxService.save(mailBoxSend);
				}

			}
			// 发送抄送邮件
			for (String id : copyToIDs) {
				if (!"".equals(id)) {
					MailBox mailBoxCopy = new MailBox();
					mailBoxCopy.setMail(mail);
					mailBoxCopy.setMailFolder(receiveFolder);
					mailBoxCopy.setAppUser(appUserService.get(new Long(id)));
					mailBoxCopy.setSendTime(mail.getSendTime());
					mailBoxCopy.setDelFlag(Mail.NOT_DELETE);
					mailBoxCopy.setReadFlag(Mail.NOT_READ);
					mailBoxCopy.setNote("抄送出去的邮件");
					mailBoxCopy.setReplyFlag(Mail.NOT_REPLY);
					mailBoxService.save(mailBoxCopy);
				}
			}

		} else {
			// 草稿时邮件主题不能为空
			if (StringUtils.isEmpty(mail.getSubject())) {
				setJsonString("{failure:true,msg:'邮件主题不能为空!'}");
				return SUCCESS;
			}

			// 邮件内容不能为空
			// if (StringUtils.isEmpty(mail.getContent())) {
			// setJsonString("{failure:true,msg:'邮件内容不能为空!'}");
			// return SUCCESS;
			// }

			SaveMail();// 先存邮件到mail表,再存到mailBox表

			// 存草稿
			MailFolder draftFolder = mailFolderService.get(new Long(
					MailFolder.FOLDER_TYPE_DRAFT));// 拿到草稿箱
			MailBox mailBox = new MailBox();
			mailBox.setMail(mail);
			mailBox.setMailFolder(draftFolder);
			mailBox.setAppUser(ContextUtil.getCurrentUser());
			mailBox.setSendTime(mail.getSendTime());
			mailBox.setDelFlag(Mail.NOT_DELETE);
			mailBox.setReadFlag(Mail.NOT_READ);
			mailBox.setNote("存草稿");
			mailBox.setReplyFlag(Mail.NOT_REPLY);
			mailBoxService.save(mailBox);
		}

		setJsonString("{success:true}");
		return SUCCESS;
	}

	public void SaveMail() {
		// 把邮件保存到mail 表里
		Set<FileAttach> mailAttachs = new HashSet<FileAttach>();
		setAppUser(ContextUtil.getCurrentUser());
		mail.setAppSender(appUser);
		mail.setSendTime(new Date());
		mail.setSender(appUser.getFullname());
		String[] fileIds = mail.getFileIds().split(",");
		for (String id : fileIds) {
			if (!id.equals("")) {
				mailAttachs.add(fileAttachService.get(new Long(id)));
			}
		}
		mail.setMailAttachs(mailAttachs);
		mailService.save(mail);
	}

	/**
	 * 邮件回复和转发
	 */
	public String opt() {
		setMail(mailService.get(mailId));
		String opt = getRequest().getParameter("opt");
		Mail reply = new Mail();
		// 邮件转发时把附件也转发
		reply.setFileIds(mail.getFileIds());
		reply.setFilenames(mail.getFilenames());
		StringBuffer newSubject = new StringBuffer(
				"<br><br><br><br><br><br><br><hr>");
		newSubject.append("<br>----<strong>" + opt + "邮件</strong>----");
		newSubject.append("<br><strong>发件人</strong>:" + mail.getSender());
		newSubject.append("<br><strong>发送时间</strong>:" + mail.getSendTime());
		newSubject.append("<br><strong>收件人</strong>:"
				+ mail.getRecipientNames());
		String copyToNames = mail.getCopyToNames();
		if (!"".equals(copyToNames) && copyToNames != null) {
			newSubject.append("<br><strong>抄送人</strong>:" + copyToNames);
		}
		newSubject.append("<br><strong>主题</strong>:" + mail.getSubject());
		newSubject.append("<br><strong>内容</strong>:<br><br>"
				+ mail.getContent());
		reply.setContent(newSubject.toString());
		reply.setSubject(opt + ":" + mail.getSubject());
		reply.setImportantFlag(Mail.COMMON);
		reply.setFileIds(mail.getFileIds());
		if (opt.equals("回复")) {
			MailBox replyBox = mailBoxService.get(boxId);
			replyBox.setReplyFlag(Mail.HAVE_REPLY);
			mailBoxService.save(replyBox);
			reply.setRecipientIDs("" + mail.getAppSender().getUserId());
			reply.setRecipientNames(mail.getSender());
		}
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
		jsonString = mapper.toDataJson(reply);
		return SUCCESS;
	}

	/**
	 * 移动邮件至指定文件夹
	 */
	public String move() {
		MailFolder moveToFolder = mailFolderService.get(new Long(folderId));
		String[] ids = boxIds.split(",");
		StringBuffer msg = new StringBuffer("{");
		boolean moveSuccess = false;
		if (ids[0] != null && !"".equals(ids[0])) {// 判断草稿不能移到其他,除了删除箱,判断正式邮件不能移至草稿箱
			Mail moveTest = mailBoxService.get(new Long(ids[0])).getMail();
			if (moveTest.getMailStatus().shortValue() == Mail.IS_DRAFT.shortValue()) {
				// 假如是草稿
				if (folderId.longValue() == MailFolder.FOLDER_TYPE_DRAFT
						.shortValue()
						|| folderId.longValue() == MailFolder.FOLDER_TYPE_DELETE
								.longValue()) {
					// 假如是草稿箱或者是删除箱,则允许移动
					moveSuccess = true;
				} else
					msg.append("msg:'草稿只能移至草稿箱或是垃圾箱(移至垃圾箱相当于删除,请注意!)'");
			} else if (moveTest.getMailStatus().shortValue() == Mail.IS_MAIL.shortValue()) {
				// 假如是正式邮件
				if (folderId.longValue() != MailFolder.FOLDER_TYPE_DRAFT
						.shortValue()) {
					// 假如不是草稿箱,允许移动
					moveSuccess = true;
				} else
					msg.append("msg:'正式邮件不能移至草稿箱'");
			}
		}
		if (moveSuccess) {
			// 把id为包含在ids数组里的邮件移动至所选文件夹
			for (String id : ids) {
				if (!"".equals(id)) {
					MailBox mailBoxMove = mailBoxService.get(new Long(id));
					mailBoxMove.setMailFolder(moveToFolder);
					if (folderId.longValue() != MailFolder.FOLDER_TYPE_DELETE
							.shortValue()) {
						mailBoxMove.setDelFlag(Mail.NOT_DELETE);
					} else {
						mailBoxMove.setDelFlag(Mail.HAVE_DELETE);
					}
					mailBoxService.save(mailBoxMove);
				}
			}
			msg.append("success:true}");
			setJsonString(msg.toString());
		} else {
			// 不合规则,不允许移动
			msg.append(",failure:true}");
			setJsonString(msg.toString());
		}
		return SUCCESS;
	}

	/**
	 * 草稿邮件删除附件
	 * 
	 * @return
	 */
	public String attach() {
		String fileIds = getRequest().getParameter("fileIds");
		String filenames = getRequest().getParameter("filenames");
		setMail(mailService.get(mailId));
		Set<FileAttach> mailAttachs = mail.getMailAttachs();
		FileAttach remove = fileAttachService.get(fileId);
		mailAttachs.remove(remove);
		mail.setMailAttachs(mailAttachs);
		mail.setFileIds(fileIds);
		mail.setFilenames(filenames);
		mailService.save(mail);
		fileAttachService.remove(fileId);
		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String search() {
		QueryFilter filter = new QueryFilter(getRequest());
		String searchContent = getRequest().getParameter("searchContent");
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.addFilter("Q_delFlag_SN_EQ", "0");
		QueryFilter filter1 = filter;
		QueryFilter filter2 = new QueryFilter(getRequest());
		filter2.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter2.addFilter("Q_delFlag_SN_EQ", "0");
		filter1.addFilter("Q_mail.subject_S_LK", searchContent);
		filter2.addFilter("Q_mail.content_S_LK", searchContent);
		List<MailBox> list1 = mailBoxService.getAll(filter1);
		List<MailBox> list2 = mailBoxService.getAll(filter2);
		if (list2.size() > 0 && list1.size() > 0) {
			for (int i = 0; i < list2.size(); i++) {
				boolean flag = true;
				for (int j = 0; j < list1.size(); j++) {
					if (list1.get(j) == list2.get(i)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					list1.add(list2.get(i));
				}
			}
		}
		if (list1.size() == 0) {
			list1 = list2;
		}
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list1, filter1.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String display() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.addFilter("Q_mailFolder.folderId_L_EQ",
				Short.toString(MailFolder.FOLDER_TYPE_RECEIVE));
		filter.addFilter("Q_delFlag_SN_EQ",
				Short.toString(Mail.NOT_DELETE));
		filter.addSorted("sendTime", "desc");
		filter.addSorted("readFlag", "desc");
		List<MailBox> list = mailBoxService.getAll(filter);
		getRequest().setAttribute("mailBoxList", list);
		return "display";
	}

	/**
	 * 返回较旧的一封邮件
	 * 
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public String prev() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.getPagingBean().setPageSize(1);// 只取一条记录
		filter.addSorted("sendTime", "desc");
		List<MailBox> list = null;
		list = mailBoxService.getAll(filter);
		if (list.size() != 0) {
			MailBox mailBox = list.get(0);
			setMail(mailBox.getMail());
			mailBox.setReadFlag(Mail.HAVE_READ);
			mailBoxService.save(mailBox);
			JacksonMapper mapper = new JacksonMapper(true,
					"yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toDataJson(mailBox);
		}
		return SUCCESS;
	}

	/**
	 * 返回较新的一封邮件
	 * 
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public String next() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.getPagingBean().setPageSize(1);// 只取一条记录
		filter.addSorted("sendTime", "Asc");
		List<MailBox> list = null;
		list = mailBoxService.getAll(filter);
		if (list.size() != 0) {
			MailBox mailBox = list.get(0);
			setMail(mailBox.getMail());
			mailBox.setReadFlag(Mail.HAVE_READ);
			mailBoxService.save(mailBox);
			JacksonMapper mapper = new JacksonMapper(true,
					"yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toDataJson(mailBox);
		}
		return SUCCESS;
	}
}
