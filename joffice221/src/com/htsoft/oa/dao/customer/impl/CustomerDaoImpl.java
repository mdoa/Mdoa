package com.htsoft.oa.dao.customer.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.customer.CustomerDao;
import com.htsoft.oa.model.customer.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{

	public CustomerDaoImpl() {
		super(Customer.class);
	}

	@Override
	public boolean checkCustomerNo(final String customerNo) {
		final String hql = "select count(*) from Customer c where c.customerNo = ?";
		Long count = (Long)getHibernateTemplate().execute(new HibernateCallback(){

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setString(0, customerNo);
				return query.uniqueResult();
			}});
		if(count!=0){
			return false;
		}else{
			return true;
		}
	}

}