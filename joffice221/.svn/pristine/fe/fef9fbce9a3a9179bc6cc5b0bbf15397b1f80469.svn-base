package com.htsoft.oa.action.communicate;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2009 GuangZhou HongTian Software Limited company.
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.mail.EmailAddress;
import com.htsoft.core.mail.MailUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DataEncryptUtil;
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

/**
 * 
 * @author
 * 
 */
public class OutMailAction extends BaseAction {

	@Resource
	private OutMailService outMailService;
	private OutMail outMail;
	@Resource
	private FileAttachService fileAttachService;
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	private OutMailUserSeting outMailUserSeting;
	@Resource
	private AppUserService appUserService;
	private AppUser appUser;
	@Resource
	private OutMailFolderService outMailFolderService;
	private OutMailFolder outMailFolder;

	private Long mailId;
	private String outMailIds;// 移动邮件的集合
	private Long fileId;// 附件Id
	private Long folderId;// 文件夹
	private Long setId;// 邮箱设置

	public OutMailUserSeting getOutMailUserSeting() {
		return outMailUserSeting;
	}

	public void setOutMailUserSeting(OutMailUserSeting outMailUserSeting) {
		this.outMailUserSeting = outMailUserSeting;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public String getOutMailIds() {
		return outMailIds;
	}

	public void setOutMailIds(String outMailIds) {
		this.outMailIds = outMailIds;
	}

	public Long getFolderId() {
		if (folderId == null) {
			return 1L;
		} else {
			return folderId;
		}
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public Long getMailId() {
		return mailId;
	}

	public void setMailId(Long mailId) {
		this.mailId = mailId;
	}

	public OutMail getOutMail() {
		return outMail;
	}

	public void setOutMail(OutMail outMail) {
		this.outMail = outMail;
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
	 * 显示列表
	 */

	public String list() {

		// String isFetch=getRequest().getParameter("isFetch");

		// 如果选择收件箱，就到邮件服务器上，收了新邮件
		// if(isFetch!=null&&isFetch.equals("Y")){
		// isFetch=null;
		// fetch();
		// }
		OutMailUserSeting defaultSet = outMailUserSetingService
				.getDefault(ContextUtil.getCurrentUserId());
		Long setId = 0L;
		setFolderId(0L);
		if (defaultSet != null) {
			setId = defaultSet.getSetId();
		}
		String reSetId = getRequest().getParameter("setId");
		if (reSetId != null && Long.parseLong(reSetId) > 0L) {
			setId = Long.parseLong(reSetId);
		}
		if (setId != 0) {
			setFolderId(outMailFolderService.getByFolderTypeAndSetId(
					OutMailFolder.FOLDER_TYPE_RECEIVE, setId).getFolderId());
		}
		String refolderId = getRequest().getParameter("folderId");
		if (refolderId != null && Long.parseLong(refolderId) > 0L) {
			setFolderId(Long.parseLong(refolderId));
		}
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addFilter("Q_outMailFolder.folderId_L_EQ", folderId.toString());
		filter.addFilter("Q_outMailUserSeting.setId_L_EQ", setId.toString());
		List<OutMail> list = outMailService.getAll(filter);
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
		OutMailFolder deleteFolder = outMailFolderService
				.getByFolderTypeAndSetId(OutMailFolder.FOLDER_TYPE_DELETE,
						setId);
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			long refolderId = outMailService.get(Long.parseLong(ids[0]))
					.getFolderId();
			if (OutMailFolder.FOLDER_TYPE_DELETE.shortValue() == outMailFolderService
					.get(refolderId).getFolderType().shortValue()) {
				for (String id : ids) {
					outMail = outMailService.get(new Long(id));
					if (outMail != null) {
						Set<FileAttach> outMailFiles = outMail
								.getOutMailFiles();
						outMailService.remove(outMail);

						if (outMailFiles != null && outMailFiles.size() > 0) {
							for (FileAttach f : outMailFiles) {
								delPhysicalFile(f);
								fileAttachService.remove(f);
							}
						}
					}

				}
			} else {
				for (String id : ids) {
					outMail = outMailService.get(new Long(id));

					outMail.setOutMailFolder(deleteFolder);
					outMailService.save(outMail);
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
		outMail = outMailService.get(mailId);
		setOutMail(outMail);
		outMail.setReadFlag(OutMail.HAVE_READ);// 设为已读
		outMailService.save(outMail);
		short folderType = outMailFolderService.get(outMail.getFolderId())
				.getFolderType();
		if (OutMailFolder.FOLDER_TYPE_DRAFT == folderType) {// 假如为草稿
			JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
			jsonString = mapper.toDataJson(outMail);
			return SUCCESS;
		} else {// 假如为查看
			if (StringUtils.isEmpty(outMail.getReceiverNames())) {
				outMail.setReceiverNames("(收信人未写)");
			}
			getRequest().setAttribute("outMail_view", outMail);
			getRequest()
					.setAttribute("outMailFiles", outMail.getOutMailFiles());
			return "detail";
		}
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		logger.debug("save start...");

		setJsonString("{success:true}");

		String opt = getRequest().getParameter("opt");
		appUser = appUserService.get(new Long(ContextUtil.getCurrentUserId()));// 员工
		outMailUserSeting = outMailUserSetingService.get(outMail
				.getOutMailUserSeting().getSetId());// 邮箱设置
		outMailUserSeting.setAppUser(appUser);
		outMail.setUserId(outMailUserSeting.getAppUser().getUserId());
		outMail.setSenderAddresses(outMailUserSeting.getMailAddress());
		outMail.setSenderName(outMailUserSeting.getAppUser().getUsername());
		outMail.setMailDate(new Date());
		outMail.setReceiverAddresses( MailUtil.getAddressesToStr(outMail
				.getReceiverNames()));
		outMail.setReceiverNames(MailUtil.getNamesToStr(outMail
				.getReceiverNames()));
		outMail.setcCAddresses(MailUtil.getAddressesToStr(outMail
				.getcCNames()));
		outMail.setcCNames(MailUtil.getNamesToStr(outMail.getcCNames()));
		outMail.setReadFlag(new Short("0"));
		outMail.setReplyFlag(new Short("0"));

		// 附件
		Set<FileAttach> outMailFiles = new HashSet<FileAttach>();
		String[] fileIds = outMail.getFileIds().split(",");
		for (String id : fileIds) {
			if (!id.equals("") && !id.equals("null")) {
				outMailFiles.add(fileAttachService.get(new Long(id)));
			}
		}
		outMail.setOutMailFiles(outMailFiles);
		outMail.setOutMailUserSeting(outMailUserSeting);

		try {

			if (opt != null && opt.trim().equals("attach")) {// 存草稿
				outMailFolder = outMailFolderService.getByFolderTypeAndSetId(
						OutMailFolder.FOLDER_TYPE_DRAFT,
						outMailUserSeting.getSetId());// 草稿箱
				outMail.setOutMailFolder(outMailFolder);// 将邮件保存到草稿箱
				outMailService.save(outMail);
			} else {
				OutMail newOutMail = new OutMail();
				BeanUtil.copyNotNullProperties(newOutMail, outMail);
				if (newOutMail.getContent() == null
						|| newOutMail.getContent().equals("")) {
					newOutMail.setContent("	");
				}

				newOutMail.setMailId(null);
				outMailFolder = outMailFolderService.getByFolderTypeAndSetId(
						OutMailFolder.FOLDER_TYPE_SEND,
						outMailUserSeting.getSetId());// 发件箱
				newOutMail.setOutMailFolder(outMailFolder);// 将邮件保存到发件箱

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

			}

		} catch (Exception e) {
			e.printStackTrace();
			setJsonString("{failure:true,msg:'发送邮件失败!请检查书写的邮件格式是否正确!!'}");
			return SUCCESS;
		}
		logger.debug("save end...");
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
		setOutMail(outMailService.get(mailId));
		Set<FileAttach> mailAttachs = outMail.getOutMailFiles();
		FileAttach remove = fileAttachService.get(fileId);
		delPhysicalFile(remove);// 删除物理文件
		mailAttachs.remove(remove);// 删除表
		outMail.setOutMailFiles(mailAttachs);
		outMail.setFileIds(fileIds);
		outMail.setFileNames(filenames);
		outMailService.save(outMail);
		fileAttachService.remove(fileId);
		return SUCCESS;
	}

	/**
	 * 移动邮件至指定文件夹
	 */
	public String move() {

		StringBuffer msg = new StringBuffer("{");
		if (outMailIds != null && outMailIds.length() > 0 && folderId != null) {// 断判要移到的文件,与文件不为空!
			OutMailFolder moveToFolder = outMailFolderService.get(new Long(
					folderId));// 要移到的文件夹
			String[] ids;// 要移动的文件

			ids = outMailIds.split(",");
			boolean moveSuccess = true;
			if (ids != null && ids.length > 0) {

				if (moveSuccess) {
					// 把id为包含在ids数组里的邮件移动至所选文件夹
					for (String id : ids) {
						if (!"".equals(id)) {
							outMail = outMailService.get(new Long(id));
							outMail.setOutMailFolder(moveToFolder);
							outMailService.save(outMail);

						}
					}
					msg.append("success:true}");
					setJsonString(msg.toString());
				} else {
					// 不合规则,不允许移动
					msg.append("failure:true}");
					setJsonString(msg.toString());
				}
			}
		} else {
			msg.append("msg:'请选择要移动的邮件及文件夹!'");
			msg.append(",failure:true}");
			setJsonString(msg.toString());
		}

		return SUCCESS;
	}

	/**
	 * 邮件回复和转发
	 */
	public String opt() {
		setOutMail(outMailService.get(mailId));
		String opt = getRequest().getParameter("opt");
		// 设为已读
		outMail.setReadFlag(OutMail.HAVE_READ);
		if (opt != null && opt.trim().equals("回复")) {// 如果为回复
			// 设为回复
			outMail.setReplyFlag(OutMail.HAVE_REPLY);

		}

		outMailService.save(outMail);
		if (opt != null && opt.trim().equals("回复")) {// 如果为回复
			//
			outMail.setReceiverAddresses(outMail.getSenderAddresses());
			outMail.setReceiverNames(outMail.getSenderName());
		}
		if (opt != null && opt.trim().equals("转发")) {// 如果为回复
			//
			outMail.setReceiverAddresses(null);
			outMail.setReceiverNames(null);
		}
		StringBuffer newSubject = new StringBuffer(
				"<br><br><br><br><br><br><br><hr>");
		newSubject.append("<br>----<strong>" + opt + "邮件</strong>----");
		newSubject
				.append("<br><strong>发件人</strong>:" + outMail.getSenderName());
		newSubject.append("<br><strong>发送时间</strong>:" + outMail.getMailDate());
		newSubject.append("<br><strong>收件人</strong>:"
				+ outMail.getReceiverNames());
		String copyToNames = outMail.getcCNames();
		if (!"".equals(copyToNames) && copyToNames != null) {
			newSubject.append("<br><strong>抄送人</strong>:" + copyToNames);
		}
		newSubject.append("<br><strong>主题</strong>:" + outMail.getTitle());
		newSubject.append("<br><strong>内容</strong>:<br><br>"
				+ outMail.getContent());
		outMail.setContent(newSubject.toString());
		outMail.setTitle(opt + ":" + outMail.getTitle());
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
		jsonString = mapper.toDataJson(outMail);
		return SUCCESS;
	}

	/**
	 * 
	 * @param list
	 * @param totalCounts
	 * @return 将List<OutMail> 转化为jsonstring
	 * 
	 */
	protected String getOutMailJsonStr(List<OutMail> list, int totalCounts) {
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, totalCounts);
		return jsonString;
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



	/**
	 * 
	 * 到邮件服务器收邮件
	 * 
	 * @param username
	 * @param password
	 * @throws Exception
	 */
	public String fetch() {
		List<OutMailUserSeting> userSetingList = new ArrayList<OutMailUserSeting>();
		AppUser appUser =  ContextUtil.getCurrentUser();
		if (setId != null && setId.longValue() > 0L) {
			userSetingList.add(outMailUserSetingService.get(setId));
		} else {
			userSetingList = outMailUserSetingService.getByLoginId(appUser.getUserId());// 邮箱设置
		}
		if(userSetingList == null || userSetingList.size() == 0){
			setJsonString("{success:false,message:'请先在邮箱设置菜单中设置邮箱配置！'}");
			return SUCCESS;
		}
		//收邮件并进行保存
		outMailService.fetch(userSetingList,appUser);
		setJsonString("{success:true,msg:'收取邮件完成！'}");
		return SUCCESS;
	}

	/***
	 * 删除物理文件
	 */

	protected boolean delPhysicalFile(FileAttach fileAttach) {
		String fp_p = FilePathUtil.FILE_PATH_ROOT;// 父目录
		String fp_full = fp_p + fileAttach.getFilePath();// 全目录
		File del_f = new File(fp_full);
		if (del_f.delete()) {
			logger.info("删除文件：" + fp_full);
		} else {
			logger.info("文件不存在：" + fp_full);
		}
		return true;
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
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.getPagingBean().setPageSize(1);// 只取一条记录
		filter.addSorted("mailDate", "desc");
		List<OutMail> list = null;
		list = outMailService.getAll(filter);
		if (list.size() != 0) {
			JacksonMapper mapper = new JacksonMapper(true,
					"yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toDataJson(list.get(0));
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
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.getPagingBean().setPageSize(1);// 只取一条记录
		filter.addSorted("mailDate", "Asc");
		List<OutMail> list = null;
		list = outMailService.getAll(filter);
		if (list.size() != 0) {
			outMail = list.get(0);
			setOutMail(outMail);
			outMail.setReadFlag(OutMail.HAVE_READ);// 设为已读
			outMailService.save(outMail);
			JacksonMapper mapper = new JacksonMapper(true,
					"yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toDataJson(outMail);
		}
		return SUCCESS;
	}
}
