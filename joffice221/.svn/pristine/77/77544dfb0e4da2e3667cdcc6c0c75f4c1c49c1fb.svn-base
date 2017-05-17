package com.htsoft.oa.dao.info;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.News;

public interface NewsDao extends BaseDao<News>{
	public List<News> findByTypeId(Long typeId,PagingBean pb);

	public List<News> findBySearch(Short isNotice,String searchContent,PagingBean pb);
	/**
	 * 查找图片新闻
	 * @param pb
	 * @return
	 */
	public List<News> findImageNews(Long sectionId,PagingBean pb);
	
	/**
	 * 查找一般栏目图片新闻
	 * @param pb
	 * @return
	 */
	public List<News> findImageCommonNews(Long sectionId,PagingBean pb);
	/**
	 * 查找新闻或公告
	 */
	public List<News> find(Short isNotice, PagingBean pb);
	
	/**
	 * 根据部门ID查找公告
	 * @param sectionId
	 * @param pb
	 * @return
	 */
	public List<News> findNotice(Long sectionId,Long orgId,PagingBean pb);
}
