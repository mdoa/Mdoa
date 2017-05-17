package com.htsoft.oa.dao.arch;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.arch.BorrowRecord;

/**
 * 
 * @author 
 *
 */
public interface BorrowRecordDao extends BaseDao<BorrowRecord>{
	 Integer OverDue(Long userId) ;
}