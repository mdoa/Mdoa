package com.htsoft.oa.dao.system.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.DiaryDao;
import com.htsoft.oa.model.system.Diary;

/**
 * @description 日志管理
 * @class DiaryDaoImpl
 * @author YHZ
 * @company www.jee-soft.cn
 * @data 2010-12-27AM
 * 
 */
@SuppressWarnings("unchecked")
public class DiaryDaoImpl extends BaseDaoImpl<Diary> implements DiaryDao {

	public DiaryDaoImpl() {
		super(Diary.class);
	}

	@Override
	public List<Diary> getSubDiary(String userIds, PagingBean pb,String sort) {
		String hql = "from Diary vo where vo.appUser.userId in (" + userIds
				+ ") and vo.diaryType=1 order by "+sort;
		return findByHql(hql, null, pb);
	}

}