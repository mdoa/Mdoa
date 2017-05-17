package com.htsoft.oa.action.info;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;

import com.htsoft.core.Constants;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.util.DateUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.info.News;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.service.info.NewsService;
import com.htsoft.oa.service.system.OrganizationService;

/**
 * 
 * @author
 * 
 */
public class NewsAction extends BaseAction {
	@Resource
	private NewsService newsService;
	@Resource
	private OrganizationService organizationService;
	private News news;

	private Long newsId;

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
	/**
	 * 获取某组织的所有子组织 包括子组织的子组织
	 */
	public List<Organization> getSubOrgs(Organization org){
		List<Organization> subOrgs=organizationService.getByParent(org.getOrgId(), org.getDemId());
		List<Organization> childOrgs=new ArrayList<Organization>();
		for(Organization subOrg:subOrgs){
			childOrgs.addAll(getSubOrgs(subOrg));
		}
		subOrgs.addAll(childOrgs);
		return subOrgs;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<News> list = newsService.getAll(filter);  //获取所有的公告列表 然后再根据当前用户的所属组织来筛选
		if(getRequest().getParameter("Q_isNotice_SN_EQ").equals("1")){  //判断是否是公司公告   
			List<Organization> orgs = organizationService.getByUserId(ContextUtil.getCurrentUserId()); //获取当前用户的组织
			List<Organization> subOrgs=new ArrayList<Organization>();
			for(Organization org:orgs){
				subOrgs.addAll(getSubOrgs(org));
			}
			orgs.addAll(subOrgs);
			int i=0;
			String [] orgsIds=new String[orgs.size()];  // 设置为当前用户的组织权限的Id 包括组织下的子组织》》》》》
			for(Organization org:orgs){
				orgsIds[i++]=org.getOrgId()+"";
			} // 《《《《设置为当前用户的组织权限的Id 包括组织下的子组织
			List<News> tempList=new ArrayList<News>();//设置该用户权限之内的公告集合》》》》
			for (News news : list) {
				if(StringUtil.isEmpty(news.getOrgIds())){
					tempList.add(news);
				} else {
					String[] newsOrgIds = news.getOrgIds().split(",");
					for (int k = 0; k < newsOrgIds.length; k++) {
						if (ArrayUtils.contains(orgsIds, newsOrgIds[k])) {
							if (!tempList.contains(news)) {
								tempList.add(news);
							}
						}
					}
				}
			}//《《《《设置该用户权限之内的公告集合
			list=tempList;
		}
		Date curTime = DateUtil.strToDate();
		for (News n : list) {
			if (n.getStatus() == 1 && n.getExpTime() != null
					&& n.getExpTime().getTime() <= curTime.getTime()) {
				n.setStatus(Constants.FLAG_DISABLE);
				newsService.save(n);
			}
		}
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
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
			for (String newsId : ids) {
				newsService.delete(new Long(newsId));
//				newsService.remove(new Long(id));
			}
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		News news = newsService.get(newsId);

		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toDataJson(news);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		news.setUpdateTime(new Date());
		if (news.getNewsId() == null) {
			news.setViewCounts(0);
			news.setReplyCounts(0);
			newsService.save(news);
		} else {
			News orgNews = newsService.get(news.getNewsId());
			try {
				BeanUtil.copyNotNullProperties(orgNews, news);
				newsService.save(orgNews);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;

	}

	/**
	 * 删除新闻图标在新闻表中的记录
	 */
	public String icon() {
		setNews(newsService.get(getNewsId()));
		news.setSubjectIcon("");
		newsService.save(news);
		return SUCCESS;
	}

	/**
	 * 收索栏中查询新闻的记录
	 */
	public String search() {
		QueryFilter filter = new QueryFilter(getRequest());
		String searchContent = getRequest().getParameter("searchContent");
		filter.addFilter("Q_isNotice_SN_EQ", "0");
		filter.addFilter("Q_status_SN_EQ", "1");
		filter.addFilter("Q_expTime_D_GT", DateUtil.getDate());
		QueryFilter filter1 = filter;
		filter1.addFilter("Q_content_S_LK", "%" + searchContent + "%");
		List<News> list1 = newsService.getAll(filter1);
		QueryFilter filter2 = new QueryFilter(getRequest());
		filter.addFilter("Q_isNotice_SN_EQ", "0");
		filter.addFilter("Q_status_SN_EQ", "1");
		filter.addFilter("Q_expTime_D_GT", DateUtil.getDate());
		filter2.addFilter("Q_subject_S_LK", "%" + searchContent + "%");
		List<News> list2 = newsService.getAll(filter2);
		if (list2.size() > 0 && list1.size() > 0) {
			for (int i = 0; i < list2.size(); i++) {
				boolean flag = true;
				for (int j = 0; j < list1.size(); j++) {
					if (list1.get(j) == list2.get(i)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					list1.add(list2.get(i));
				}
			}
		}
		if (list1.size() == 0) {
			list1 = list2;
		}
		jsonString = mapper.toPageJson(list1, filter1.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 显示列表下一条记录
	 */
	public String next() {
		jsonString = this.getNewInfo(false);
		return SUCCESS;

	}

	/**
	 * 显示列表下一条记录
	 * 
	 * @return
	 */
	public String prev() {
		jsonString = this.getNewInfo(true);
		return SUCCESS;
	}

	/**
	 * 获取新闻记录
	 * 
	 * @param dir
	 * @return
	 */
	private String getNewInfo(Boolean dir) {
		StringBuffer buff = new StringBuffer("{success:true");

		QueryFilter filter = new QueryFilter(getRequest());
		filter.getPagingBean().setPageSize(1);// 只取一条记录
		if (dir) {
			filter.addSorted("newsId", "desc");
		}
		List<News> list = newsService.getAll(filter);
		if (list != null && list.size() > 0) {
			newsId = list.get(0).getNewsId();
		}
		buff.append(",newsId: ").append(newsId);
		buff.append("}");
		return buff.toString();
	}
	
	/**
	 * 查看全部新闻
	 * @return
	 */
	public String display(){
		QueryFilter filter = new QueryFilter(getRequest()); 
		List<News> list = newsService.getAll(filter);
		getRequest().setAttribute("newsList", list);
		return "display";
	}
}
