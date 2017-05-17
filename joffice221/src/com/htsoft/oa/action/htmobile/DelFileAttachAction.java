package com.htsoft.oa.action.htmobile;

import javax.annotation.Resource;

import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.Mail;
import com.htsoft.oa.model.communicate.OutMail;
import com.htsoft.oa.service.communicate.MailService;
import com.htsoft.oa.service.communicate.OutMailService;
import com.htsoft.oa.service.system.FileAttachService;
import com.informix.util.stringUtil;

public class DelFileAttachAction extends BaseAction {

	@Resource
	private FileAttachService fileAttachService;
	// 删除附件
	public String del() {
		String fileId = RequestUtil.getString(getRequest(), "fileId");// 要删除的附件类型id
		fileAttachService.removeByPath(fileAttachService.get(Long.parseLong(fileId)).getFilePath());
		fileAttachService.remove(Long.parseLong(fileId));
		return SUCCESS;
	}

}
