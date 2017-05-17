package com.htsoft.oa.core.dao;

import java.io.Serializable;
import java.util.List;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.paging.PagingBean;
/*
 *  杭州梦德软件有限公司 OA办公自动管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
/**
 * 
 * @author csx
 *
 */
public interface GenericDao<T,PK extends Serializable> {
	public T save(T entity);
	public T merge(T entity);
	public T get(PK id);
	public void remove(PK id);
	public void remove(T entity);
	public void evict(T entity);
	
	public List<T> getAll();
	public List<T> getAll(PagingBean pb);
	public List<T> getAll(QueryFilter filter);
	
	public List<T> findByHql( String hql, Object[]objs);
	public List<T> findByHql( String hql, Object[]objs,PagingBean pb );
	public List<T> findByHql( String hql, Object[]objs, int firstResult, int pageSize );
	
	public void flush();
	
	/**
	 * 执行删除或更新语句
	 * @param hql
	 * @param params
	 * @return 返回影响行数
	 */
	public Long update(final String hql,final Object... params);
	
}
