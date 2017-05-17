package com.htsoft.oa.service.system.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.DiaryDao;
import com.htsoft.oa.model.system.Diary;
import com.htsoft.oa.service.system.DiaryService;

public class DiaryServiceImpl extends BaseServiceImpl<Diary> implements DiaryService{
	private DiaryDao dao;
	
	public DiaryServiceImpl(DiaryDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<Diary> getAllBySn(PagingBean pb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Diary> getSubDiary(String userIds, PagingBean pb, String sort) {
		return dao.getSubDiary(userIds, pb, sort);
	}

}