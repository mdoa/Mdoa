package com.htsoft.oa.dao.arch.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.util.DateUtil;
import com.htsoft.oa.dao.arch.BorrowRecordDao;
import com.htsoft.oa.model.arch.BorrowRecord;
import com.htsoft.oa.model.system.AppRole;

public class BorrowRecordDaoImpl extends BaseDaoImpl<BorrowRecord> implements BorrowRecordDao{

	public BorrowRecordDaoImpl() {
		super(BorrowRecord.class);
	}
	/**
	 * 图书超期
	 */
	public Integer OverDue(Long userId) {
		Date curTime = DateUtil.strToDate();
		StringBuffer sb = new StringBuffer(
				"from BorrowRecord br where br.returnDate <= ? and appUser.userId= ?  and br.returnStatus=1");
		ArrayList<Object> paramList = new ArrayList<Object>();
		paramList.add(curTime);
		paramList.add(userId);	
		return   findByHql(sb.toString(), paramList.toArray()).size();
	}
}