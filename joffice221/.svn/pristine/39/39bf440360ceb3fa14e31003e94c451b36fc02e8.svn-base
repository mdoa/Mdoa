package com.htsoft.oa.dao.communicate;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.communicate.MailFolder;

/**
 * 
 * @author 
 *
 */
public interface MailFolderDao extends BaseDao<MailFolder>{
	/**
	 * 获得用户的文件夹
	 * @param userId 用户ID
	 * @return
	 */
	public List<MailFolder> getUserFolder(Long userId);

	public List<MailFolder> getFolderLikePath(String path);
	/**
	 * 获得默认的文件夹
	 * @param parentId 
	 * @return
	 */
	public List<MailFolder> getMailFolderByParentId(Long parentId);
	
}