package com.htsoft.oa.dao.system;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Diary;

/**
 * 
 * @author 
 *
 */
public interface DiaryDao extends BaseDao<Diary>{
	//public List<Diary> getAllBySn(PagingBean pb);
	/**
	 * 查找所有下属的工作日志
	 */
	public List<Diary> getSubDiary(String userIds,PagingBean pb, String sort);
}