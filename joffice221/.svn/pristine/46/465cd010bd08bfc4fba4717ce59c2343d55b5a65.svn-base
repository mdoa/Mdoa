package com.htsoft.oa.action.info;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.info.News;
import com.htsoft.oa.model.info.NewsComment;
import com.htsoft.oa.service.info.NewsCommentService;
import com.htsoft.oa.service.info.NewsService;
import com.htsoft.oa.service.system.AppUserService;

/**
 * 新闻评论
 * 
 * @author
 * 
 */
public class NewsCommentAction extends BaseAction {
	@Resource
	private NewsCommentService newsCommentService;
	@Resource
	private AppUserService appUserService;
	@Resource
	private NewsService newsService;
	private NewsComment newsComment;

	private Long commentId;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public NewsComment getNewsComment() {
		return newsComment;
	}

	public void setNewsComment(NewsComment newsComment) {
		this.newsComment = newsComment;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		String start = getRequest().getParameter("start");
		List<NewsComment> list = newsCommentService.getAll(filter);
		for (NewsComment newsComment : list) {
			newsComment.setStart(start);
		}

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		System.out.println(jsonString);
		return SUCCESS;

	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				NewsComment delComment = newsCommentService.get(new Long(id));
				News news = delComment.getNews();
				if (news.getReplyCounts() > 1) {
					news.setReplyCounts(news.getReplyCounts() - 1);
				}
				newsService.save(news);
				newsCommentService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		NewsComment newsComment = newsCommentService.get(commentId);
		jsonString = mapper.toDataJson(newsComment);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		// 被回复的新闻回复次数加1
		News news = newsService.get(newsComment.getNewsId());
		news.setReplyCounts(news.getReplyCounts() + 1);
		newsService.save(news);

		newsComment.setAppUser(appUserService.get(newsComment.getUserId()));
		newsComment.setCreatetime(new Date());
		newsComment.setNews(news);
		newsCommentService.save(newsComment);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
