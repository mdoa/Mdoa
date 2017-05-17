package com.htsoft.oa.service.document;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.document.DocPrivilege;
import com.htsoft.oa.model.system.AppUser;

public interface DocPrivilegeService extends BaseService<DocPrivilege>{
	public List<DocPrivilege> getAll(DocPrivilege docPrivilege,Long folderId,PagingBean pb);
	public List<Integer> getRightsByFolder(AppUser user, Long folderId);
	public Integer getRightsByDocument(AppUser user, Long docId);
	/**
	 * 获取文件夹权限的信息
	 * 2011-9-14
	 * @param folderId
	 * @return
	 */
	public DocPrivilege findByFolderId(Long folderId);
	
	/**
	 * 获取文档权限信息
	 */
	public DocPrivilege findByDocId(Long docId);
	
}


