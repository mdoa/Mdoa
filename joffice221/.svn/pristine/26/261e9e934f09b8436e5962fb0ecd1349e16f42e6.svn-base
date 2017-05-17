package com.htsoft.oa.dao.system.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.UserPositionDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.UserPosition;

@SuppressWarnings("unchecked")
public class UserPositionDaoImpl extends BaseDaoImpl<UserPosition> implements UserPositionDao{

	public UserPositionDaoImpl() {
		super(UserPosition.class);
	}
	/**
	 * 查找某一用户的所有职位
	 * @param userId
	 * @return
	 */
	public List<UserPosition> getByUserPos(Long userId){
		String hql="from UserPosition up where up.appUser.userId=?";
		return (List<UserPosition>)findByHql(hql,new Object[]{userId});
	}
	
	/**
	 * 取得某个岗位的所有的人员
	 * 
	 * @param posId
	 * @return
	 */
//	public List<Long> getUserIdsByPosId(Long posId) {
//		String hql = "select u.appUser.userId from UserPosition u where u.position.posId=?";
//		List userIds = findByHql(hql, new Object[] { posId });
//		return userIds;
//	}
	
	/**
	 * 删除UserPosition对象
	 * @param userId
	 * @param posId
	 * @return
	 */
	public void delCascade(Long userId, Long posId){
		String hql = "delete UserPosition up where up.appUser.userId = "+userId;
		if(posId!=0){
			hql+=" and up.position.posId="+ posId;
		}
		getSession().createQuery(hql).executeUpdate();
	}
	
	public List getByPosId(Long posId){
		String hql="from UserPosition up where up.position.posId=?";
		return findByHql(hql,new Object[]{posId});
	}
	
	public List<AppUser> getUsersByPosId(Long posId){
		String hql="select distinct up.appUser from UserPosition up where up.position.posId=?";
		return (List)findByHql(hql,new Object[]{posId});
	}
	
}