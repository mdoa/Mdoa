package com.htsoft.oa.service.arch.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.arch.BorrowRecordDao;
import com.htsoft.oa.model.arch.BorrowRecord;
import com.htsoft.oa.service.arch.BorrowRecordService;

public class BorrowRecordServiceImpl extends BaseServiceImpl<BorrowRecord> implements BorrowRecordService{
	private BorrowRecordDao dao;
	
	public BorrowRecordServiceImpl(BorrowRecordDao dao) {
		super(dao);
		this.dao=dao;
	}
	public Integer OverDue(Long userId) {
		return	dao.OverDue(userId);
	}
}