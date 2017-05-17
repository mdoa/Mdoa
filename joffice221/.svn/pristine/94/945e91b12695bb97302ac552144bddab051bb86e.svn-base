package com.htsoft.oa.action.htmobile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;

import com.htsoft.core.Constants;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.info.News;
import com.htsoft.oa.model.info.NewsComment;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.service.info.NewsCommentService;
import com.htsoft.oa.service.info.NewsService;
import com.htsoft.oa.service.system.OrganizationService;

/*
 * author xianggang
 * 公司  hotent
 */
public class MobileNewsAction extends BaseAction {
	@Resource
	private NewsService newsService;
	@Resource
	private NewsCommentService newsCommentService;
	@Resource
	private OrganizationService organizationService;
	
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
		short isNotice = RequestUtil.getShort(getRequest(), "Q_isNotice_SN_EQ");
		List<News> list = newsService.find(isNotice, filter.getPagingBean());  //获取所有的公告列表 然后再根据当前用户的所属组织来筛选
		if(getRequest().getParameter("Q_isNotice_SN_EQ").equals("1")){  //判断是否是公司公告   
			List<Organization> orgs = organizationService.getByUserId(ContextUtil.getCurrentUserId()); //获取当前用户的组织
			List<Organization> subOrgs=new ArrayList<Organization>();//获取当前用户的组织的所有子组织》》》》》
			for(Organization org:orgs){
				subOrgs.addAll(getSubOrgs(org));
			}
			orgs.addAll(subOrgs);//《《《《获取当前用户的组织的所有子组织
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
		jsonString = mapper.toPageJson(list, filter.getPagingBean().getTotalItems());
		return SUCCESS;

	}

	/**
	 *  根据Id取得公告和评论
	 */
	public String get() {
		QueryFilter filter = new QueryFilter(getRequest());
		Long newsId = RequestUtil.getLong(getRequest(), "newsId");
		News news = newsService.get(newsId);
		//浏览次数加1
		news.setViewCounts(news.getViewCounts() + 1);
		newsService.save(news);
		//获得该新闻或公告的评论并把它拼入json
		List<NewsComment> newsComments = newsCommentService.getByNewsId(newsId, filter.getPagingBean());
		String comment = "";
		if(newsComments.size() > 0){
			comment += "\"comment\":[";
			for(int i = 0; i < newsComments.size(); i++) {
				comment += "{\"newsId\":" + newsComments.get(i).getNewsId() + ",\"fullname\":\"" 
			            + newsComments.get(i).getFullname() + "\",\"createtime\":\"" + newsComments.get(i).getCreatetime()
			            + "\",\"content\":\"" + newsComments.get(i).getContent() + "\"},";
			}
			comment = comment.substring(0, comment.length() - 1);
			comment += "],";
		}
		
		comment += "\"total\":" + newsComments.size() + ",\"currentusername\":\"" + ContextUtil.getCurrentUser().getFullname() + "\"";
		jsonString = mapper.toDataJson(news);
		jsonString = jsonString.substring(0, jsonString.length() - 1);
		jsonString = jsonString + "," + comment + "}";
		return SUCCESS;
	}
	
	public String getUserOrgNames(){
		List<Organization> orgs = organizationService.getByUserId(ContextUtil.getCurrentUserId());
		String [] orgsNames=new String[orgs.size()];
		int i=0;
		for(Organization org:orgs){
			orgsNames[i++]=org.getOrgName();
		} 
		jsonString = mapper.toDataJson(orgsNames);
		return SUCCESS;
	}

}
