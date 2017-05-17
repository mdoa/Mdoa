package com.htsoft.oa.service.communicate.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.htsoft.core.mail.MailUtil;
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.DataEncryptUtil;
import com.htsoft.oa.dao.communicate.OutMailDao;
import com.htsoft.oa.model.communicate.OutMail;
import com.htsoft.oa.model.communicate.OutMailFolder;
import com.htsoft.oa.model.communicate.OutMailUserSeting;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.communicate.OutMailFolderService;
import com.htsoft.oa.service.communicate.OutMailService;

public class OutMailServiceImpl extends BaseServiceImpl<OutMail> implements
		OutMailService {
	private OutMailDao dao;
	@Resource
	private OutMailFolderService outMailFolderService;

	public OutMailServiceImpl(OutMailDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<OutMail> findByFolderId(Long folderId) {
		return dao.findByFolderId(folderId);
	}

	public Long CountByFolderId(Long folderId) {
		return dao.CountByFolderId(folderId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.htsoft.oa.service.communicate.OutMailService#fetch(java.util.List,
	 * com.htsoft.oa.model.system.AppUser)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void fetch(List<OutMailUserSeting> userSetingList, AppUser appUser) {
		// TODO Auto-generated method stub
		// 邮箱UID的Map
		Map<String, String> uidMap = dao.getUidByUserId(appUser.getUserId());
		for (OutMailUserSeting outMailUserSeting : userSetingList) {
			OutMailFolder fectchFolder = outMailFolderService
					.getByFolderTypeAndSetId(OutMailFolder.FOLDER_TYPE_RECEIVE,
							outMailUserSeting.getSetId());// 要移到收件箱

			List<Map<String, Object>> list = MailUtil
					.fetch(outMailUserSeting.getPopHost(), outMailUserSeting
							.getPopPort(), outMailUserSeting.getMailAddress(),
							DataEncryptUtil.desDecrypt(outMailUserSeting
									.getMailPass()), uidMap, appUser
									.getUsername());
			for (Map<String, Object> map : list) {
				OutMail outMail = new OutMail();
				outMail.setUid(map.get("uid").toString());
				outMail.setSenderAddresses(map.get("senerAddress").toString());
				outMail.setSenderName(map.get("senderName").toString());
				outMail.setReceiverAddresses(map.get("receiverAddresses")
						.toString());
				outMail.setReceiverNames(map.get("receiverNames").toString());
				outMail.setcCAddresses(map.get("ccAddresses").toString());
				outMail.setcCNames(map.get("ccNames").toString());

				outMail.setbCCAddresses(map.get("bccAddresses").toString());
				outMail.setbCCAnames(map.get("bccNames").toString());

				outMail.setTitle(map.get("subject").toString());
				outMail.setContent(map.get("content").toString());
				outMail.setMailDate((Date) map.get("sentDate"));
				Set<FileAttach> mailFiles= (Set<FileAttach>) map.get("mailFiles");
				outMail.setOutMailFiles(mailFiles);

				// 其它
				outMail.setReadFlag(OutMail.NOT_READ);
				outMail.setReplyFlag(OutMail.NOT_REPLY);

				// 收件箱
				outMail.setOutMailFolder(fectchFolder);
				outMail.setOutMailUserSeting(outMailUserSeting);
				outMail.setUserId(appUser.getUserId());
				
				
				outMail = dao.save(outMail);
				
				
				// 补回 fileid ,filename,字段,用豆号隔开
				String f_id = "";
				String f_name = "";
				if (outMail.getOutMailFiles() != null && outMail.getOutMailFiles().size() > 0) {
					for (FileAttach f : outMail.getOutMailFiles()) {
						f_id += f.getFileId().toString() + ",";
						f_name += f.getFileName().toString() + ",";
					}
					if (f_id.length() > 1) {
						f_id = f_id.substring(0, f_id.length() - 1);

					}
					if (f_name.length() > 1) {
						f_name = f_name.substring(0,
								f_name.length() - 1);

					}
				}
				outMail.setFileIds(f_id);
				outMail.setFileNames(f_name);
				dao.save(outMail);
			}

		}

	}
}