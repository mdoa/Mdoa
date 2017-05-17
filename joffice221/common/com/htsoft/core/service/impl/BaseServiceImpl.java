package com.htsoft.core.service.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.dao.GenericDao;
import com.htsoft.core.service.BaseService;

public class BaseServiceImpl<T> extends GenericServiceImpl<T, Long> implements BaseService<T>{

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public BaseServiceImpl(GenericDao dao) {
		super(dao);
	}
	
}
