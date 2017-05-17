package com.htsoft.oa.action.htmobile;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.info.News;
import com.htsoft.oa.model.info.NewsComment;
import com.htsoft.oa.service.info.NewsCommentService;
import com.htsoft.oa.service.info.NewsService;
import com.htsoft.oa.service.system.AppUserService;

public class MobileNewsCommentAction extends BaseAction {
	@Resource
	private AppUserService appUserService;
	private NewsComment newsComment;
	@Resource
	private NewsService newsService;
	@Resource
	private NewsCommentService newsCommentService;
	
	public NewsComment getNewsComment() {
		return newsComment;
	}
	public void setNewsComment(NewsComment newsComment) {
		this.newsComment = newsComment;
	}
	/**
	 * 加载更多公告或新闻评论
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		Long newsId = RequestUtil.getLong(getRequest(), "newsId");
		List<NewsComment> newsComments = newsCommentService.getByNewsId(newsId, filter.getPagingBean());
		jsonString = mapper.toPageJson(newsComments, filter.getPagingBean().getTotalItems());
		return SUCCESS;
	}
	
	/**
	 * 保存公司公告评论
	 */
	public String save() {
		// 被回复的新闻回复次数加1
		News news = newsService.get(newsComment.getNewsId());
		news.setReplyCounts(news.getReplyCounts() + 1);
		newsService.save(news);
        newsComment.setUserId(ContextUtil.getCurrentUserId());
		newsComment.setAppUser(appUserService.get(newsComment.getUserId()));
		newsComment.setCreatetime(new Date());
		newsComment.setNews(news);
		String content=newsComment.getContent().replace("\n", "B_L_A_N_K");
		newsComment.setContent(content);
		newsCommentService.save(newsComment); 
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
