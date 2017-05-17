package com.htsoft.oa.service.system.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.io.File;
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.FileAttachDao;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.system.FileAttachService;

public class FileAttachServiceImpl extends BaseServiceImpl<FileAttach>
		implements FileAttachService {
	private FileAttachDao dao;

	public FileAttachServiceImpl(FileAttachDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public void removeByPath(String filePath) {
		// 删除数据库的文件的同时，也删除对应的文件
		FileAttach fileAttach = dao.getByPath(filePath);
		String fullFilePath = AppUtil.getAppAbsolutePath() + "/attachFiles/"
				+ filePath;
		logger.info("file:" + fullFilePath);
		File file = new File(fullFilePath);
		if (file.exists()) {
			file.delete();
		}
		if (fileAttach != null) {
			dao.remove(new Long(fileAttach.getFileId()));
		}
	}

	@Override
	public FileAttach getByPath(String filePath) {
		return dao.getByPath(filePath);
	}

	/**
	 * @description 删除多条数据
	 * @param fileIds
	 *            文件ids
	 */
	@Override
	public void mutilDel(String fileIds) {
		for (String str : fileIds.split(",")) {
			dao.remove(dao.get(new Long(str)));
		}
	}

	/**
	 * @description 分页查询附件信息,备注：图片格式[bmp,png,jpg,gif,tiff]
	 * @param pb
	 *            PagingBean
	 * @param filePath
	 *            filePath搜索条件
	 * @param bo
	 *            boolean,true:file文件,false:image图片文件
	 * @return List <FileAttach>
	 */
	@Override
	public List<FileAttach> fileList(PagingBean pb, String filePath, boolean bo) {
		return dao.fileList(pb, filePath, bo);
	}

	@Override
	public List<FileAttach> fileList(String fileType) {
		return dao.fileList(fileType);
	}
}