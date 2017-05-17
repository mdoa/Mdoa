package com.htsoft.oa.dao.task.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.task.AppointmentDao;
import com.htsoft.oa.model.task.Appointment;

@SuppressWarnings("unchecked")
public class AppointmentDaoImpl extends BaseDaoImpl<Appointment> implements
		AppointmentDao {

	public AppointmentDaoImpl() {
		super(Appointment.class);
	}

	/**
	 * 根据当前用户ID读取约会列表在首页显示
	 */
	@Override
	public List<Appointment> showAppointmentByUserId(Long userId, PagingBean pb) {
		ArrayList<Object> paramList = new ArrayList<Object>();
		Calendar cal = Calendar.getInstance();
		StringBuffer hql = new StringBuffer(
				"select vo from Appointment vo where vo.appUser.userId=? and vo.startTime > ? order by vo.startTime ASC");
		paramList.add(userId);
		paramList.add(cal.getTime());
		return findByHql(hql.toString(), paramList.toArray(), pb);
	}

}