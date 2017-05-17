package com.htsoft.oa.service.communicate.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.communicate.PhoneGroupDao;
import com.htsoft.oa.model.communicate.PhoneGroup;
import com.htsoft.oa.service.communicate.PhoneGroupService;

public class PhoneGroupServiceImpl extends BaseServiceImpl<PhoneGroup> implements PhoneGroupService{
	private PhoneGroupDao dao;
	
	public PhoneGroupServiceImpl(PhoneGroupDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Integer findLastSn(Long userId) {
		return dao.findLastSn(userId);
	}

	@Override
	public PhoneGroup findBySn(Integer sn, Long userId,Short isPublic) {
		return dao.findBySn(sn, userId,isPublic);
	}

	@Override
	public List<PhoneGroup> findBySnUp(Integer sn, Long userId,short isPublic) {
		return dao.findBySnUp(sn, userId,isPublic);
	}

	@Override
	public List<PhoneGroup> findBySnDown(Integer sn, Long userId,short isPublic) {
		return dao.findBySnDown(sn, userId,isPublic);
	}

	@Override
	public List<PhoneGroup> getAll(Long userId) {
		return dao.getAll(userId);
	}

	@Override
	public PhoneGroup findPublicBySn(Integer sn) {
		return dao.findPublicBySn(sn);
	}

	@Override
	public List<PhoneGroup> findPublicBySnDown(Integer sn) {
		return dao.findPublicBySnDown(sn);
	}

	@Override
	public List<PhoneGroup> findPublicBySnUp(Integer sn) {
		return dao.findPublicBySnUp(sn);
	}

	@Override
	public Integer findPublicLastSn() {
		return dao.findPublicLastSn();
	}

	@Override
	public List<PhoneGroup> getPublicAll() {
		return dao.getPublicAll();
	}

}