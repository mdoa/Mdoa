package com.htsoft.oa.dao.info.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.info.NewsCommentDao;
import com.htsoft.oa.model.info.NewsComment;

public class NewsCommentDaoImpl extends BaseDaoImpl<NewsComment> implements
		NewsCommentDao {
	protected Class<?> persistType;

	@Override
	public void deleteByNewsId(Long newsId) {
		// TODO Auto-generated method stub
	   final String sql = "delete from NewsComment where NEWSID="+newsId;
	   getHibernateTemplate().bulkUpdate(sql);
	}

	public Class<?> getPersistType() {
		return persistType;
	}

	public void setPersistType(Class<?> persistType) {
		this.persistType = persistType;
	}

	public NewsCommentDaoImpl() {
		super(NewsComment.class);
	}

	public List<NewsComment> getByNewsId(Long NewsId, PagingBean pb) {
		final String hql = "from NewsComment where NEWSID=? order by createtime DESC";
		Object[] params ={NewsId};
		return findByHql(hql, params, pb);
	}

}