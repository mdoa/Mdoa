package com.htsoft.oa.dao.system.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.RegionDao;
import com.htsoft.oa.model.system.Region;

public class RegionDaoImpl extends BaseDaoImpl<Region> implements RegionDao{

	public RegionDaoImpl() {
		super(Region.class);
	}

	/**
	 * 查出所有的省份
	 */
	@Override
	public List<Region> getProvince() {
		Long parentId = 0l;
		String hql = "from Region r where r.parentId = ?";
		return findByHql(hql, new Object[]{parentId});
	}
	
	/**
	 * 根据省份的ID查出该省所有的市
	 */
	@Override
	public List<Region> getCity(Long regionId) {
		String hql = "from Region r where r.parentId = ?";
		return findByHql(hql, new Object[]{regionId});
	}


}