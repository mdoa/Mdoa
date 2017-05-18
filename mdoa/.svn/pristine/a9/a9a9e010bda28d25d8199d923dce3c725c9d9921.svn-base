package com.mdoa.framework.service;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.base.service.BaseService;
import com.mdoa.framework.bo.LeadForm;
import com.mdoa.framework.dao.LeadDao;
import com.mdoa.framework.model.Lead;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class LeadService extends BaseService{
	
	@Autowired
	private LeadDao leadDao;
	/**
	 * 获取员工上级
	 * @param userId 用户的Id
	 * @return 上级信息
	 */
	public UserInfo getLeader(LeadForm form){
		return leadDao.getLeader(form);
	}
	/**
	 * 获取员工的下级 
	 * @param userId 用户的Id
	 * @return 下级列表
	 */
	public PageModel<UserInfo> getLower(LeadForm form){
		PageHelper.startPage(form.getPage(),form.getRows());
		Page<UserInfo> list = (Page<UserInfo>)leadDao.getLower(form);
		return new PageModel<UserInfo>(list);
	}
	
	/**
	 * 为员工添加上级
	 * @param form userId，leaderId
	 */
	public void addLeader(LeadForm form){
		if(!leadDao.addLeader(form)){
			throw new RuntimeException("添加员工上级失败");
		}
	}
	
	/**
	 * 为员工添加下级
	 * @param form userId lowerIds
	 */
	public void addLower(LeadForm form, HttpServletRequest request){
		String[] lowerIds = StringUtil.splitString(form.getLowerIds());
		String[] lowerNames = StringUtil.splitString(form.getLowerNames());
		List<UserInfo> lowers = new LinkedList<UserInfo>();
		for(int i = 0 ; i < lowerIds.length ; i++){
			UserInfo user = new UserInfo();
			user.setUserId(lowerIds[i]);
			user.setUserName(lowerNames[i]);
			lowers.add(user);
		}
		form.setLowers(lowers);
		UserInfo userInfo = getUser(request);
		form.setCreateUserId(userInfo.getUserId());
		form.setCreateUserName(userInfo.getUserName());
		if(!leadDao.addLower(form)){
			throw new RuntimeException("添加员工下级失败");
		}
	}
	
	/**
	 * 删除员工上级
	 * @param form userId leaderId
	 */
	public void deleteLeader(LeadForm form){
		if(!leadDao.deleteLeader(form)){
			throw new RuntimeException("删除员工下级失败");
		}
	}
	
	/**
	 * 删除员工下级
	 * @param form userId lowers
	 */
	public void deleteLower(LeadForm form){
		if(!leadDao.deleteLower(form)){
			throw new RuntimeException("删除员工下级失败");
		}
	}
	
	/**
	 * 根据上下级员工名查询员工上下级关系
	 * @param form userName leadName 
	 */
	public PageModel<Lead> getLeadLowerList(LeadForm form){
		if(!StringUtil.isEmpty(form.getUserName())){
			form.setUserName("'" + form.getUserName() + "%'");
		}
		if(!StringUtil.isEmpty(form.getLeadName())){
			form.setLeadName("'" + form.getLeadName() + "%'");
		}
		PageHelper.startPage(form.getPage(),form.getRows());
		Page<Lead> leads = (Page<Lead>)leadDao.getLeadLowerList(form);
		PageModel<Lead> page = new PageModel<Lead>(leads);
		return page;
	}
	
	/**
	 * 修改员工的上级人员
	 */
	public void updateUserLead(LeadForm form){
		if(!leadDao.updateUserLead(form)){
			if(!leadDao.addLeader(form)){
				throw new RuntimeException("修改员工上级失败");
			}
		}
	}
}
