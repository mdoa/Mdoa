package com.htsoft.oa.action.htmobile;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;


import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.mail.EmailAddress;
import com.htsoft.core.mail.MailUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DataEncryptUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.OutMail;
import com.htsoft.oa.model.communicate.OutMailFolder;
import com.htsoft.oa.model.communicate.OutMailUserSeting;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.communicate.OutMailFolderService;
import com.htsoft.oa.service.communicate.OutMailService;
import com.htsoft.oa.service.communicate.OutMailUserSetingService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.FileAttachService;
import com.htsoft.oa.util.FilePathUtil;

public class MyOutMailAction extends BaseAction {

	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	private OutMailUserSeting outMailUserSeting;
	@Resource
	private OutMailFolderService outMailFolderService;
	private OutMailFolder outMailFolder;
	
	@Resource
	private FileAttachService fileAttachService;
	@Resource
	private OutMailService outMailService;
	
	private OutMail outMail;
	@Resource
	private AppUserService appUserService;
	private AppUser appUser;
	
	
	public OutMailUserSeting getOutMailUserSeting() {
		return outMailUserSeting;
	}
	public void setOutMailUserSeting(OutMailUserSeting outMailUserSeting) {
		this.outMailUserSeting = outMailUserSeting;
	}
	public OutMail getOutMail() {
		return outMail;
	}
	public void setOutMail(OutMail outMail) {
		this.outMail = outMail;
	}
	public AppUser getAppUser() {
		return appUser;
	}
	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	public OutMailFolder getOutMailFolder() {
		return outMailFolder;
	}
	public void setOutMailFolder(OutMailFolder outMailFolder) {
		this.outMailFolder = outMailFolder;
	}

	//外部邮箱菜单
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
		/*for (int i = 0; i < outMailUserSetingList.size(); i++) {
			System.out.println(outMailUserSetingList.get(i).getSetId()+"   "+outMailUserSetingList.get(i).getAccountName());
		}*/
		jsonString = mapper.toDataJson(outMailUserSetingList);
		return SUCCESS;
	}
	
	//获取未读邮件条数
	public String getOutNum(){
		String setId = getRequest().getParameter("setId");
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_outMailUserSeting.setId_L_EQ", setId);
		filter.addFilter("Q_outMailFolder.folderType_SN_EQ", OutMailFolder.FOLDER_TYPE_RECEIVE.toString());
		filter.addFilter("Q_readFlag_SN_EQ", OutMail.NOT_READ.toString());
		List<OutMail>outMails=outMailService.getAll(filter);
		jsonString = "{\"success\":true,\"totalCounts\":"+outMails.size()+"}";
		return SUCCESS;
	}
	
	public String list() {
		String img = "";
		QueryFilter filter = new QueryFilter(getRequest());
		String setId = getRequest().getParameter("setId");
		String mailType = getRequest().getParameter("mailType");
		filter.addFilter("Q_outMailFolder.outMailUserSeting.setId_L_EQ", setId);
		filter.addFilter("Q_outMailFolder.folderType_SN_EQ", mailType);
		List<OutMail> outMails=outMailService.getAll(filter);
		// 判断邮件是否已读，未读的在桌面上显示维度图片作为标识
		if ("1".equals(mailType)) {
			img = "<input type='image' class='EmailImage' />";
			for (int i = 0; i < outMails.size(); i++) {
				if (outMails.get(i).getReadFlag() == 0) {
					outMails.get(i).setImg(img);
				}
			}
 			img="";
		}

		jsonString = mapper.toPageJson(outMails, filter.getPagingBean().getTotalItems());
		return SUCCESS;
	}
	
	public String get(){
		Long mailId = RequestUtil.getLong(getRequest(), "mailId");
		OutMail outMail=outMailService.get(mailId);
		outMail.setReadFlag(OutMail.HAVE_READ);
		if(outMailFolderService.get(outMail.getFolderId()).getFolderType()!=3){
			outMail.setContent(outMail.getContent().replaceAll("\n", "<br\\/>"));
		}
		
		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toDataJson(outMail);
		return SUCCESS;
	}
	
	//获取发件人人信息
		public String getRec(){
			Long userId = RequestUtil.getLong(getRequest(), "userId");
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
			List<OutMailUserSeting> outMailUserSetings = outMailUserSetingService.getAll(filter);
			jsonString = mapper.toDataJson(outMailUserSetings);
			return SUCCESS;
		}
		
		public String save(){
			String setId = getRequest().getParameter("setId");
			String camopt = getRequest().getParameter("camopt");
			System.out.println("fasdfasdfasdf         "+outMail.getMailId());
			appUser = appUserService.get(new Long(ContextUtil.getCurrentUserId()));// 员工
			outMailUserSeting = outMailUserSetingService.get(Long.parseLong(setId));// 邮箱设置
			outMailUserSeting.setAppUser(appUser);
			outMail.setUserId(outMailUserSeting.getAppUser().getUserId());
			outMail.setSenderAddresses(outMailUserSeting.getMailAddress());
			outMail.setSenderName(outMailUserSeting.getAppUser().getUsername());
			outMail.setMailDate(new Date());
			outMail.setReceiverAddresses( MailUtil.getAddressesToStr(outMail
					.getReceiverAddresses()));
			outMail.setReceiverNames(MailUtil.getNamesToStr(outMail
					.getReceiverNames()));
			outMail.setcCAddresses(MailUtil.getAddressesToStr(outMail.getcCAddresses()));
			outMail.setcCNames(MailUtil.getNamesToStr(outMail.getcCNames()));
			outMail.setReadFlag(new Short("0"));
			outMail.setReplyFlag(new Short("0"));
			// 附件
			if (outMail.getFileIds()!=null) {
				Set<FileAttach> outMailFiles = new HashSet<FileAttach>();
				
				String[] fileIds = outMail.getFileIds().split(",");
				for (String id : fileIds) {
					if (!id.equals("") && !id.equals("null")) {
						outMailFiles.add(fileAttachService.get(new Long(id)));
						
					}
				}
				outMail.setOutMailFiles(outMailFiles);
			}
			
			outMail.setOutMailUserSeting(outMailUserSeting);

			try {
				if (camopt!=null && "1".equals(camopt)) {
					OutMail newOutMail = new OutMail();
					BeanUtil.copyNotNullProperties(newOutMail, outMail);
					if (newOutMail.getContent() == null
							|| newOutMail.getContent().equals("")) {
						newOutMail.setContent("	");
					}
					//newOutMail.setMailId(null);
					//if(StringUtils.isEmpty(newOutMail.getMailId().toString())){
					outMailFolder = outMailFolderService.getByFolderTypeAndSetId(OutMailFolder.FOLDER_TYPE_SEND, outMailUserSeting.getSetId());// 发件箱
					newOutMail.setOutMailFolder(outMailFolder);// 将邮件保存到发件箱
					//}
					// 解析地址
					List<EmailAddress> reviceList = MailUtil.getEMailStrToList(
							newOutMail.getReceiverAddresses(),
							newOutMail.getReceiverNames());// 收件人
					List<EmailAddress> ccList = null;
					
					if (newOutMail.getcCAddresses() != null
							&& !newOutMail.getcCAddresses().trim().equals("")
							&& !newOutMail.getcCAddresses().trim().equals("null")
							&& newOutMail.getcCAddresses().length() > 2) {
						// 是否有抄送人
						ccList = MailUtil.getEMailStrToList(
								newOutMail.getcCAddresses(),
								newOutMail.getcCNames());// 抄送人
					}
					// 发送邮件
					this.send(newOutMail, reviceList, ccList, outMailUserSeting);
					outMailService.save(newOutMail);
				}else {
					outMailFolder = outMailFolderService.getByFolderTypeAndSetId(
							OutMailFolder.FOLDER_TYPE_DRAFT,
							outMailUserSeting.getSetId());// 草稿箱
					outMail.setOutMailFolder(outMailFolder);// 将邮件保存到草稿箱
					outMailService.save(outMail);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				setJsonString("{failure:true,msg:'发送邮件失败!请检查书写的邮件格式是否正确!!'}");
				return SUCCESS;
			}
			jsonString = "{\"success\":true,\"msg\":\"邮件发送成功\"}";
			return SUCCESS;
		}
		/**
		 * 发送邮件
		 * 
		 * @param outMail
		 * @param toList
		 * @param ccList
		 * @param userSeting
		 * @throws Exception
		 */
		protected void send(OutMail outMail, List<EmailAddress> toList,
				List<EmailAddress> ccList, OutMailUserSeting userSeting)
				throws Exception {

			// 添加收件人
			InternetAddress[] to = MailUtil.getAddressByType(toList);

			InternetAddress[] cc = null;

			// 添加抄送人
			if (ccList != null && ccList.size() > 0) {
				cc =  MailUtil.getAddressByType(ccList);
			}
			Map<String, String> attachments = new HashMap<String, String>();
			// 添加附件
			if (outMail.getFileIds() != null && outMail.getFileIds().length() > 0) {// 判断字附串不为空
				String fileids = outMail.getFileIds();
				String[] fileid_s = fileids.split(",");
				for (int i = 0; i < fileid_s.length; i++) {
					FileAttach fileAttach = fileAttachService.get(new Long(
							fileid_s[i]));
					attachments.put(
							FilePathUtil.FILE_PATH_ROOT + fileAttach.getFilePath(),
							fileAttach.getFileName());
				}
			}

			// 发送邮件
			MailUtil.sender(outMail.getTitle(), outMail.getContent(), to, cc, null,
					attachments, userSeting.getMailAddress(),
					userSeting.getUserName(),
					DataEncryptUtil.desDecrypt(userSeting.getMailPass()),
					outMail.getMailDate(), OutMail.SEND_TYPE,
					userSeting.getSmtpHost(), userSeting.getSmtpPort());

		}
		
		//删除邮件
		public String del(){
			Long emailId = RequestUtil.getLong(getRequest(), "mailId");
			Long mailType = RequestUtil.getLong(getRequest(), "mailType");
			String allmailIds = RequestUtil.getString(getRequest(), "allmailIds");
			String setId = RequestUtil.getString(getRequest(), "setId");
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_outMailUserSeting.setId_L_EQ", setId.toString());
			filter.addFilter("Q_folderType_SN_EQ", OutMailFolder.FOLDER_TYPE_DELETE.toString());
			List<OutMailFolder> outMailFolders=outMailFolderService.getAll(filter);
			String[] mailIds=allmailIds.split(",");
			if (allmailIds!=null&&allmailIds!="") {
				for (int i = 0; i < mailIds.length; i++) {
					if (mailType != 4) {
						outMail=outMailService.get(Long.parseLong(mailIds[i]));
						outMail.setOutMailFolder(outMailFolders.get(0));
						outMailService.save(outMail);
					}else {
						outMailService.remove(Long.parseLong(mailIds[i]));
					}
				}
			}else {
				if (mailType != 4) {
					outMail=outMailService.get(emailId);
					outMail.setOutMailFolder(outMailFolders.get(0));
					outMailService.save(outMail);
				}else {
					outMailService.remove(emailId);
				}
			}
			
			return SUCCESS;
		}
	
//	// 邮件总集合，包括（收件箱，发件箱，草稿箱，垃圾箱），跳转类型，统计数量
//	class emailmessage {
//		private String title;
//		private String mailType;
//		private int count;
//		private String newmail;
//
//		public String getNewmail() {
//			return newmail;
//		}
//
//		public void setNewmail(String newmail) {
//			this.newmail = newmail;
//		}
//
//		public String getTitle() {
//			return title;
//		}
//
//		public void setTitle(String title) {
//			this.title = title;
//		}
//
//		public String getMailType() {
//			return mailType;
//		}
//
//		public void setMailType(String mailType) {
//			this.mailType = mailType;
//		}
//
//		public int getCount() {
//			return count;
//		}
//
//		public void setCount(int count) {
//			this.count = count;
//		}
//
//	}
}
