package com.htsoft.oa.service.document.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.document.DocFolderDao;
import com.htsoft.oa.model.document.DocFolder;
import com.htsoft.oa.service.document.DocFolderService;

public class DocFolderServiceImpl extends BaseServiceImpl<DocFolder> implements DocFolderService{
	private DocFolderDao dao;
	
	public DocFolderServiceImpl(DocFolderDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	public List<DocFolder> getUserFolderByParentId(Long userId,Long parentId){
		return dao.getUserFolderByParentId(userId, parentId);
	}
	
	/**
	 * 取得某path下的所有Folder
	 * @param path
	 * @return
	 */
	public List<DocFolder> getFolderLikePath(String path){
		return dao.getFolderLikePath(path);
	}

	@Override
	public List<DocFolder> getPublicFolderByParentId(Long parentId) {
		return dao.getPublicFolderByParentId( parentId);
	}
	
	@Override
	public List<DocFolder> getPublicFolder() {
		return dao.getPublicFolder();
	}
	
	@Override
	public List<DocFolder> findByParentId(Long parentId) {
		
		return dao.findByParentId(parentId);
	}

	@Override
	public List<DocFolder> findByUserAndName(Long userId, String foleName) {
		return dao.findByUserAndName(userId, foleName);
	}

	@Override
	public List<DocFolder> getOnlineFolderByParentId(Long parentId) {
		return dao.getOnlineFolderByParentId(parentId);
	}

	@Override
	public List<DocFolder> getByUserId(Long userId) {
		return dao.getByUserId(userId);
	}
	
}