package com.htsoft.core.dao.impl;
/*
 *  杭州梦德软件有限公司 OA办公自动管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.dao.BaseDao;

/**
 * 
 * @author csx
 *
 * @param <T> 基础表类，对于主键为long类型　，则直接继承该类，若主键为其他类型，
 * 需要直接继承GenericDaoImpl
 */

@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> extends GenericDaoImpl<T, Long> implements BaseDao<T>{

	public BaseDaoImpl(Class<?> persistType) {
		super(persistType);
	}

}
