package com.htsoft.oa.service.info.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.info.NewsCommentDao;
import com.htsoft.oa.dao.info.NewsDao;
import com.htsoft.oa.model.info.News;
import com.htsoft.oa.service.info.NewsService;

public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService{
	private NewsDao newsDao;
	private NewsCommentDao newsCommentDao;
	public NewsServiceImpl(NewsDao dao,NewsCommentDao newsCommentDao) {
		super(dao);
		this.newsDao=dao;
		this.newsCommentDao=newsCommentDao;
	}

	@Override
	public List<News> findByTypeId(Long typeId,PagingBean pb) {
		return newsDao.findByTypeId(typeId,pb);
	}

	@Override
	public List<News> findBySearch(Short isNotice,String searchContent,PagingBean pb) {
		return newsDao.findBySearch(isNotice,searchContent,pb);
	}

	@Override
	public List<News> findImageNews(Long sectionId,PagingBean pb) {
		return newsDao.findImageNews(sectionId,pb);
	}

	@Override
	public List<News> findImageCommonNews(Long sectionId,PagingBean pb) {
		return newsDao.findImageCommonNews(sectionId,pb);
	}

	@Override
	public void delete(Long newsId) {
		// TODO Auto-generated method stub
		newsCommentDao.deleteByNewsId(newsId);
		newsDao.remove(newsId);
	}
	
	@Override
	/**
	 * 查找新闻或公告
	 */
	public List<News> find(Short isNotice, PagingBean pb) {
		return newsDao.find(isNotice, pb);
	}

	@Override
	/**
	 * 查找公告
	 */
	public List<News> findNotice(Long sectionId,Long orgId,PagingBean pb){
		return newsDao.findNotice(sectionId, orgId, pb);
	}
}
