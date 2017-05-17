package com.htsoft.oa.action.communicate;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2009 GuangZhou HongTian Software Limited company.
 */
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.mail.MailUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DataEncryptUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.OutMailFolder;
import com.htsoft.oa.model.communicate.OutMailUserSeting;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.communicate.OutMailFolderService;
import com.htsoft.oa.service.communicate.OutMailUserSetingService;

/**
 * 外部邮箱设置
 * 
 * @author
 * 
 */
public class OutMailUserSetingAction extends BaseAction {
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	@Resource
	private OutMailFolderService outMailFolderService;
	private OutMailUserSeting outMailUserSeting;

	/** 邮箱设置ID */
	private Long setId;

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

	public OutMailUserSeting getOutMailUserSeting() {
		return outMailUserSeting;
	}

	public void setOutMailUserSeting(OutMailUserSeting outMailUserSeting) {
		this.outMailUserSeting = outMailUserSeting;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		AppUser user = ContextUtil.getCurrentUser();
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", user.getId());
		List<OutMailUserSeting> list = outMailUserSetingService.getAll(filter);
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

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				outMailUserSetingService.remove(new Long(id));
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

		OutMailUserSeting outMailUserSeting = outMailUserSetingService
				.get(setId);
		outMailUserSeting.setMailPass(DataEncryptUtil
				.desDecrypt(outMailUserSeting.getMailPass()));
		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toDataJson(outMailUserSeting);
		return SUCCESS;
	}

	/**
	 * 获取当前默认的邮箱设置
	 * 
	 * @return
	 */
	public String getDefault() {
		OutMailUserSeting outMailUserSeting = outMailUserSetingService
				.getDefault(ContextUtil.getCurrentUserId());
		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toDataJson(outMailUserSeting);
		return SUCCESS;
	}

	/**
	 * 设置默认邮箱
	 */
	public String setDefault() {

		outMailUserSetingService.saveDefault(setId,
				ContextUtil.getCurrentUserId());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (outMailUserSeting.getSetId() == null) {
			outMailUserSeting.setUserName(ContextUtil.getCurrentUser()
					.getUsername());
		}
		boolean send = this.send(outMailUserSeting);
		boolean fetch = this.fetch(outMailUserSeting);

		if (!send && !fetch) {
			setJsonString("{failure:true,msg:'连接到smtp,pop服务器失败，请检查书写是否正确!!'}");
		} else if (!send) {
			setJsonString("{failure:true,msg:'连接到smtp服务器失败，请检查书写是否正确!!'}");
		} else if (!fetch) {
			setJsonString("{failure:true,msg:'连接到pop服务器失败，请检查书写是否正确!!'}");
		} else if (send && fetch) {
			outMailUserSeting.setMailPass(DataEncryptUtil
					.desEncrypt(outMailUserSeting.getMailPass()));
			if (outMailUserSeting.getSetId() == null) {
				outMailUserSeting.setUserId(ContextUtil.getCurrentUserId());
				outMailUserSeting.setAppUser(ContextUtil.getCurrentUser());
				outMailUserSeting.setIsDefault(Short.parseShort("0"));
				if (outMailUserSetingService.getDefault(ContextUtil
						.getCurrentUserId()) == null) {
					outMailUserSeting.setIsDefault(Short.parseShort("1"));
				}
				outMailUserSetingService.save(outMailUserSeting);
				for (short i = 1; i < 5; i++) {
					OutMailFolder outMailFolder = new OutMailFolder();
					outMailFolder.setFolderType(i);
					switch (i) {
					case 1:
						outMailFolder.setFolderName("收信箱");
						break;
					case 2:
						outMailFolder.setFolderName("发信箱");
						break;
					case 3:
						outMailFolder.setFolderName("草稿箱");
						break;
					case 4:
						outMailFolder.setFolderName("垃圾箱");
						break;
					}
					outMailFolder.setOutMailUserSeting(outMailUserSeting);
					outMailFolder.setAppUser(ContextUtil.getCurrentUser());
					outMailFolder.setParentId(Long.parseLong("0"));
					outMailFolder.setDepLevel(1);
					outMailFolderService.save(outMailFolder);
					outMailFolder.setPath("0." + outMailFolder.getFolderId()
							+ ".");
					outMailFolderService.save(outMailFolder);
				}
				logger.debug(">>>" + outMailUserSeting);
			} else {
				OutMailUserSeting seting = outMailUserSetingService
						.get(outMailUserSeting.getSetId());
				try {
					BeanUtil.copyNotNullProperties(seting, outMailUserSeting);
					outMailUserSetingService.save(seting);
					logger.debug(">>>" + seting);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			setJsonString(JSON_SUCCESS);
		}

		return SUCCESS;
	}

	/**
	 * 发送邮件
	 * 
	 * @param os
	 * @return
	 */
	protected boolean send(OutMailUserSeting os) {
		return MailUtil.sender("邮件测试连接", "邮件测试连接,请不用回复", os.getMailAddress(),
				os.getMailAddress(), os.getUserName(), os.getMailPass(),
				"smtp", os.getSmtpHost(), os.getSmtpPort());
	}

	/**
	 * 测试接收邮件接口
	 * 
	 * @param os
	 * @return
	 */
	protected boolean fetch(OutMailUserSeting os) {
		return MailUtil.fetch(os.getPopHost(), os.getPopPort(),
				os.getMailAddress(), os.getMailPass());
	}
}
