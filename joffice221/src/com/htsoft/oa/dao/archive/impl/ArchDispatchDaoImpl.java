package com.htsoft.oa.dao.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.Iterator;
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.archive.ArchDispatchDao;
import com.htsoft.oa.model.archive.ArchDispatch;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;

public class ArchDispatchDaoImpl extends BaseDaoImpl<ArchDispatch> implements ArchDispatchDao{

	public ArchDispatchDaoImpl() {
		super(ArchDispatch.class);
	}

	@Override
	public List<ArchDispatch> findByUser(AppUser user,PagingBean pb) {
		Iterator<AppRole> it=user.getRoles().iterator();
		StringBuffer sb=new StringBuffer();
		while(it.hasNext()){
			if(sb.length()>0){
				sb.append(",");
			}
			sb.append(it.next().getRoleId().toString());
		}
		StringBuffer hql=new StringBuffer("from ArchDispatch vo where vo.archUserType=2 and vo.isRead=0 and (vo.userId=?");
		if(sb.length()>0){
			hql.append(" or vo.disRoleId in ("+sb+")");
		}
		hql.append(") order by vo.dispatchId desc");
		Object[] objs={user.getUserId()};
		return findByHql(hql.toString(), objs, pb);
	}

	@Override
	public List<ArchDispatch> findRecordByArc(Long archivesId) {
		String hql="from ArchDispatch vo where (vo.archUserType=0 or vo.archUserType=1) and vo.archives.archivesId=?";
		Object[] objs={archivesId};
		return findByHql(hql,objs);
	}

}