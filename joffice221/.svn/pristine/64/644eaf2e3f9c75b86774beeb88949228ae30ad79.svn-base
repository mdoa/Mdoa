package com.htsoft.oa.action.htmobile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.util.RequestUtil;
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

public class MyEmailAction extends BaseAction {

	@Resource
	private MailService mailService;
	@Resource
	private MailBoxService mailBoxService;
	@Resource
	private MailFolderService mailFolderService;
	@Resource
	private FileAttachService fileAttachService;
	@Resource
	private ShortMessageService shortMessageService;
	@Resource
	private AppUserService appUserService;

	private Long boxId;

	private Long replyBoxId;

	private Long folderId;

	private Mail mail;

	private AppUser appUser;

	private String sendMessage;

	public Long getBoxId() {
		return boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	public Long getReplyBoxId() {
		return replyBoxId;
	}

	public void setReplyBoxId(Long replyBoxId) {
		this.replyBoxId = replyBoxId;
	}

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Mail getMail() {
		return mail;
	}

	public void setMail(Mail mail) {
		this.mail = mail;
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

	//获取未读邮件条数
	public String getInNum(){
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
		filter.addFilter("Q_mailFolder.folderType_SN_EQ", MailFolder.FOLDER_TYPE_RECEIVE.toString());
		filter.addFilter("Q_readFlag_SN_EQ", Mail.NOT_READ.toString());
		List<MailBox> mailBoxs = mailBoxService.getAll(filter);
		jsonString = "{\"success\":true,\"totalCounts\":"+mailBoxs.size()+"}";
		return SUCCESS;
	}


	// 列出邮件
	public String list() {
		String img = "";
		QueryFilter filter = new QueryFilter(getRequest());
		String mailType = getRequest().getParameter("mailType");
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
		filter.addFilter("Q_mailFolder.folderId_L_EQ", (mailType).toString());
		filter.addSorted("sendTime", "desc");

		List<MailBox> mailBoxs = mailBoxService.getAll(filter);
		// 判断邮件是否已读，未读的在桌面上显示维度图片作为标识
		if ("1".equals(mailType)) {
			for (int i = 0; i < mailBoxs.size(); i++) {
				img = "";
				if (mailBoxs.get(i).getReadFlag() == 0) {
					img = "<input type='image' class='EmailImage' />";
				}
				mailBoxs.get(i).getMail().setImg(img);
			}
		}

		jsonString = mapper.toPageJson(mailBoxs, filter.getPagingBean().getTotalItems());
		return SUCCESS;
	}

	public String get() {
		Long boxId = RequestUtil.getLong(getRequest(), "boxId");
		MailBox mailBox = mailBoxService.get(boxId);
		// 更改邮件状态
		mailBox.setReadFlag((short) 1);
		mailBoxService.save(mailBox);
		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		if (mailBox.getFolderId()!=3) {
			mailBox.getMail().setContent(mailBox.getMail().getContent().replaceAll("\n", "<br\\/>"));
		}
		
		jsonString = mapper.toDataJson(mailBox);
		return SUCCESS;
	}

	// 添加邮件
	public String save() {
		if (mail.getMailStatus().shortValue() == Mail.IS_MAIL.shortValue()) {
			// 假如发送邮件,发件人不能为空
			if (StringUtils.isEmpty(mail.getRecipientIDs())) {
				jsonString = "{\"success\":false,\"msg\":\"收件人不能为空!\"}";
				return SUCCESS;
			}
			// 邮件主题不能为空
			if (StringUtils.isEmpty(mail.getSubject())) {
				jsonString = "{\"success\":false,\"msg\":\"邮件主题不能为空!\"}";
				return SUCCESS;
			}

			// 假如是回复邮件,则原邮件改回复标识
			if (replyBoxId != null && !"".equals(replyBoxId)) {
				MailBox reply = mailBoxService.get(replyBoxId);
				reply.setReplyFlag(Mail.HAVE_READ);
				mailBoxService.save(reply);
			}

			MailFolder receiveFolder = mailFolderService.get(new Long(MailFolder.FOLDER_TYPE_RECEIVE));// 收件箱
			MailFolder sendFolder = mailFolderService.get(new Long(MailFolder.FOLDER_TYPE_SEND));// 发件箱
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
				mailBox.setNote("新邮件已发送");
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
							mailAttachs.add(fileAttachService.get(new Long(id)));
						}
					}
					old.setMailAttachs(mailAttachs);

					setMail(old);
					mailService.save(old);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				MailBox drafted = mailBoxService.get(boxId);
				drafted.setMailFolder(sendFolder);
				drafted.setNote("草稿已发送");
				mailBoxService.save(drafted);
			}

			if (sendMessage != null && sendMessage.equals("1")) {
				StringBuffer msgContent = new StringBuffer("<font color=\"green\">");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				msgContent.append(mail.getSender()).append("</font>").append("在<font color=\"red\">").append(sdf.format(mail.getSendTime())).append("</font>")
						.append("给您发了一封邮件，请注意查收。");
				shortMessageService.save(AppUser.SYSTEM_USER, mail.getRecipientIDs(), msgContent.toString(), ShortMessage.MSG_TYPE_SYS);
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
			 
			jsonString = "{\"success\":true,\"msg\":\"邮件发送成功\"}";
		} else {
			// 草稿时邮件主题不能为空
			if (StringUtils.isEmpty(mail.getSubject())) {
				jsonString = "{\"success\":false,\"msg\":\"邮件主题不能为空!\"}";
				return SUCCESS;
			}

			// 邮件内容不能为空
			// if (StringUtils.isEmpty(mail.getContent())) {
			// setJsonString("{failure:true,msg:'邮件内容不能为空!'}");
			// return SUCCESS;
			// }

			// MailFolder sendFolder = mailFolderService.get(new
			// Long(MailFolder.FOLDER_TYPE_SEND));// 发件箱
			if (mail.getMailId() == null) {// 当该邮件是新建邮件时
				SaveMail();// 先存邮件到mail表,再存到mailBox表

				// 存草稿
				MailFolder draftFolder = mailFolderService.get(new Long(MailFolder.FOLDER_TYPE_DRAFT));// 拿到草稿箱
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
			} else {
				Mail old = mailService.get(mail.getMailId());
				try {
					BeanUtil.copyNotNullProperties(old, mail);
					
					Set<FileAttach> mailAttachs = new HashSet<FileAttach>();
					old.setSendTime(new Date());
					String[] fileIds = mail.getFileIds().split(",");
					for (String id : fileIds) {
						if (!id.equals("")) {
							mailAttachs.add(fileAttachService.get(new Long(id)));
						}
					}
					old.setMailAttachs(mailAttachs);
					 
					setMail(old);
					mailService.save(old);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				MailBox drafted = mailBoxService.get(boxId);
				// drafted.setMailFolder(sendFolder);
				drafted.setNote("草稿修改成功");
				mailBoxService.save(drafted);
			}
			jsonString = "{\"success\":true,\"msg\":\"草稿保存成功\"}";
		}

		return SUCCESS;
	}

	private void SaveMail() {
		// 把邮件保存到mail 表里

		 Set<FileAttach> mailAttachs = new HashSet<FileAttach>();
		setAppUser(ContextUtil.getCurrentUser());// 从上下文取得当前用户
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

	public String del() {
		Long boxId = RequestUtil.getLong(getRequest(), "boxId");
		Long mailType = RequestUtil.getLong(getRequest(), "mailType");
		String allboxIds = RequestUtil.getString(getRequest(), "allboxIds");
		String[] boxIds=allboxIds.split(",");
		if (allboxIds!=null&&allboxIds!="") {
			for (int i = 0; i < boxIds.length; i++) {
				
				// 更改邮件状态
				if (mailType != 4) {
					// 普通删除邮件
					MailBox mailBox = mailBoxService.get(Long.parseLong(boxIds[i]));
					mailBox.setDelFlag((short) 1);
					mailBox.setMailFolder(mailFolderService.get(new Long(MailFolder.FOLDER_TYPE_DELETE)));
					mailBoxService.save(mailBox);
				} else {
					// 彻底删除邮件
					mailBoxService.remove(Long.parseLong(boxIds[i]));
				}
			}
			
		} else {
			
			// 更改邮件状态
			if (mailType != 4) {
				// 普通删除邮件
				MailBox mailBox = mailBoxService.get(boxId);
				mailBox.setDelFlag((short) 1);
				mailBox.setMailFolder(mailFolderService.get(new Long(MailFolder.FOLDER_TYPE_DELETE)));
				mailBoxService.save(mailBox);
			} else {
				// 彻底删除邮件
				mailBoxService.remove(boxId);
			}
		}
		setJsonString("{\"success\":true}");
		return SUCCESS;
	}

	
}
