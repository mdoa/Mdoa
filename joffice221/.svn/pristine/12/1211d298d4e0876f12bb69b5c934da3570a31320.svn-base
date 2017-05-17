package com.htsoft.oa.service.personal.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.personal.DutySystemDao;
import com.htsoft.oa.model.personal.DutySystem;
import com.htsoft.oa.service.personal.DutySystemService;

public class DutySystemServiceImpl extends BaseServiceImpl<DutySystem> implements DutySystemService{
	private DutySystemDao dao;
	
	public DutySystemServiceImpl(DutySystemDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/**
	 * 重写保存方法
	 */
	public DutySystem save(DutySystem duty){
		if(DutySystem.DEFAULT.equals(duty.getIsDefault())){//更新为缺省，则其他缺省的需要更变为非缺省的
			dao.updateForNotDefult();
		}
		dao.save(duty);
		return duty;
	}
	
	/**
	 * 取得缺省的班次
	 * @return
	 */
	public DutySystem getDefaultDutySystem(){
		List<DutySystem> list=dao.getDefaultDutySystem();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

}