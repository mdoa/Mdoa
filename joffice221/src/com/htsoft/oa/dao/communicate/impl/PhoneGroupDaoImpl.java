package com.htsoft.oa.dao.communicate.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.ArrayList;
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.communicate.PhoneGroupDao;
import com.htsoft.oa.model.communicate.PhoneGroup;

public class PhoneGroupDaoImpl extends BaseDaoImpl<PhoneGroup> implements PhoneGroupDao{

	public PhoneGroupDaoImpl() {
		super(PhoneGroup.class);
	}

	@Override
	public Integer findLastSn(Long userId) {
		String hql = "select max(sn) from PhoneGroup vo where vo.isPublic=0 and vo.appUser.userId=?";
		Object[] object={userId};
		List list =findByHql(hql,object);
		return (Integer)list.get(0);
	}

	@Override
	public PhoneGroup findBySn(Integer sn, Long userId,Short isPublic) {
		String hql="select vo from PhoneGroup vo where vo.isPublic=?";
		List<PhoneGroup> list = new ArrayList<PhoneGroup>();
		if(isPublic==0){
			hql+="and vo.appUser.userId=? and vo.sn=?";
			Object[] object ={isPublic,userId,sn};
			list=findByHql(hql,object);
		}else{
			hql+="and vo.sn=?";
			Object[] object ={isPublic,sn};
			list=findByHql(hql,object);
		}
		return (PhoneGroup)list.get(0);
	}

	@Override
	public List<PhoneGroup> findBySnUp(Integer sn, Long userId,short isPublic) {
		String hql="from PhoneGroup vo where vo.isPublic=?";
		List<PhoneGroup> list = new ArrayList<PhoneGroup>();
		if(isPublic==0){
			hql+="and vo.appUser.userId=? and vo.sn<?";
			Object[] object ={isPublic,userId,sn};
			list=findByHql(hql,object);
		}else{
			hql+="and vo.sn<?";
			Object[] object ={isPublic,sn};
			list=findByHql(hql,object);
		}
		return list;
	}

	@Override
	public List<PhoneGroup> findBySnDown(Integer sn, Long userId,short isPublic) {
		String hql="from PhoneGroup vo where vo.isPublic=?";
		List<PhoneGroup> list = new ArrayList<PhoneGroup>();
		if(isPublic==0){
			hql+="and vo.appUser.userId=? and vo.sn>?";
			Object[] object ={isPublic,userId,sn};
			list=findByHql(hql,object);
		}else{
			hql+="and vo.sn>?";
			Object[] object ={isPublic,sn};
			list=findByHql(hql,object);
		}
		return list;
	}

	@Override
	public List<PhoneGroup> getAll(Long userId) {
		String hql="from PhoneGroup vo where vo.isPublic=0 and vo.appUser.userId=? order by vo.sn asc";
		Object[] object={userId};
		return findByHql(hql,object);
	}

	@Override
	public PhoneGroup findPublicBySn(Integer sn) {
		String hql="select vo from PhoneGroup vo where vo.isPublic=1 and vo.sn=?";
		Object[] object ={sn};
		List<PhoneGroup> list=findByHql(hql,object);
		return (PhoneGroup)list.get(0);
	}

	@Override
	public List<PhoneGroup> findPublicBySnDown(Integer sn) {
		String hql="from PhoneGroup vo where vo.isPublic=1 and vo.sn>?";
		Object[] object ={sn};
		return findByHql(hql,object);
	}

	@Override
	public List<PhoneGroup> findPublicBySnUp(Integer sn) {
		String hql="from PhoneGroup vo where vo.isPublic=1 and vo.sn<?";
		Object[] object ={sn};
		return findByHql(hql,object);
	}

	@Override
	public Integer findPublicLastSn() {
		String hql = "select max(sn) from PhoneGroup vo where vo.isPublic=1";
		List list =findByHql(hql);
		return (Integer)list.get(0);
	}

	@Override
	public List<PhoneGroup> getPublicAll() {
		String hql="from PhoneGroup vo where vo.isPublic=1 order by vo.sn asc";
		return findByHql(hql);
	}
}