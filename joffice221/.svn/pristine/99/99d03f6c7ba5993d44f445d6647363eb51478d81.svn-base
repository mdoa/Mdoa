package com.htsoft.oa.dao.info.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.Constants;
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.util.DateUtil;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.info.NewsDao;
import com.htsoft.oa.model.info.News;

@SuppressWarnings("unchecked")
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao{
	
	public NewsDaoImpl() {
		super(News.class);
	}

	@Override
	public List<News> findByTypeId(final Long typeId,final PagingBean pb) {
		final String hql = "from News n where n.newsType.typeId=?";
		Object[] params ={typeId};
		return findByHql(hql, params, pb);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<News> findBySearch(Short isNotice, String searchContent,PagingBean pb) {
		ArrayList<Comparable> params=new ArrayList<Comparable>();
		StringBuffer hql = new StringBuffer("from News n where n.isNotice= ? and n.status = ?");
		params.add(isNotice);
		params.add(Constants.FLAG_ACTIVATION);
		if(StringUtils.isNotEmpty(searchContent)){
			hql.append(" and (n.subject like ? or n.content like ?)");
			params.add("%"+searchContent+"%");
			params.add("%"+searchContent+"%");
		}
		hql.append(" and (n.expTime>? or n.expTime = null)");
		params.add(DateUtil.strToDate());
		hql.append(" order by n.updateTime desc");
		return findByHql(hql.toString(),params.toArray(), pb);
	}

	@Override
	public List<News> findImageNews(Long sectionId,PagingBean pb) {
		String hql="from News vo where vo.section.sectionId = ? " +
				"and (vo.expTime>? or vo.expTime = null) and status = 1 order by vo.updateTime desc";
		return findByHql(hql,new Object[]{sectionId,DateUtil.strToDate()},pb);
	}

	@Override
	public List<News> findImageCommonNews(Long sectionId,PagingBean pb) {
		String hql="from News vo where vo.section.sectionId = ? " +
				"and (vo.expTime>? or vo.expTime = null) " +
				"and vo.isNotice = 0 and status = 1 order by vo.updateTime desc";
		return findByHql(hql,new Object[]{sectionId,DateUtil.strToDate()},pb);
	}
	
	@Override
	public List<News> findNotice(Long sectionId,Long orgId,PagingBean pb){
		String hql = "from News vo where vo.section.sectionId = ? " +"and where vo.orgIds=?"+
				"and (vo.expTime>? or vo.expTime = null) and status = 1 order by vo.updateTime desc";
		return findByHql(hql,new Object[]{sectionId,orgId,DateUtil.strToDate()},pb);
	} 
	
	@Override
	public List<News> find(Short isNotice, PagingBean pb) {
		String hql="from News vo where vo.isNotice=? order by vo.createtime desc";
		Object[] params ={isNotice};
		return findByHql(hql, params, pb);
	}
	
}
