package com.mdoa.personnel.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.personnel.bo.LeaveApplyForm;
import com.mdoa.personnel.bo.LeaveExamineForm;
import com.mdoa.personnel.bo.LeaveStreamForm;
import com.mdoa.personnel.dao.LeaveDao;
import com.mdoa.personnel.model.LeaveApply;
import com.mdoa.personnel.model.LeaveStream;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class LeaveService {

	@Autowired
	private LeaveDao leaveDao;
	
	/**
	 * 请假申请
	 * @param leaveApplyForm
	 */
	public void applyForWelfare(LeaveApplyForm leaveApplyForm) {
		leaveApplyForm.setLeaveApplyId(leaveDao.getuuid());
		if(!leaveDao.insertLeaveApply(leaveApplyForm))
			throw new RuntimeException();
		if(!leaveDao.insertLeaveStream(leaveApplyForm))
			throw new RuntimeException();
	}
	
	/**
	 * 查询请假申请信息
	 * @param leaveApplyForm
	 * @return
	 */
	public PageModel<LeaveApply> findApplyByCondition(LeaveApplyForm leaveApplyForm) {
		if(!StringUtil.isEmpty(leaveApplyForm.getUserName())){
			leaveApplyForm.setUserName("'"+leaveApplyForm.getUserName()+"%'");
		}
		//设置分页信息
		PageHelper.startPage(leaveApplyForm.getPage(),leaveApplyForm.getRows());
		List<LeaveApply> list = leaveDao.findApplyByCondition(leaveApplyForm);
		PageModel<LeaveApply> pageInfo =new PageModel<>((Page<LeaveApply>)list);
		return pageInfo;
	}
	
	/**
	 * 通过审核人id查询请假申请信息
	 * @param examineUserId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageModel<LeaveApply> findApplyByExamineUserId(String examineUserId,int pageNum,int pageSize) {
		PageHelper.startPage(pageNum,pageSize);
		List<LeaveApply> list = leaveDao.findApplyByExamineUserId(examineUserId);
		PageModel<LeaveApply> pageInfo =new PageModel<>((Page<LeaveApply>)list);
		return pageInfo;
	}

	/**
	 * 条件查询请假审核流信息
	 * @param leaveApplyForm
	 * @return
	 */
	public PageModel<LeaveStreamForm> findStreamByCondition(LeaveApplyForm leaveApplyForm) {
		if(!StringUtil.isEmpty(leaveApplyForm.getExamineUserName())){
			leaveApplyForm.setExamineUserName("'"+leaveApplyForm.getExamineUserName()+"%'");
		}
		if(!StringUtil.isEmpty(leaveApplyForm.getUserName())){
			leaveApplyForm.setUserName("'"+leaveApplyForm.getUserName()+"%'");
		}
		PageHelper.startPage(leaveApplyForm.getPage(),leaveApplyForm.getRows());
		List<LeaveStreamForm> list = leaveDao.findStreamByCondition(leaveApplyForm);
		PageModel<LeaveStreamForm> pageInfo =new PageModel<>((Page<LeaveStreamForm>)list);
		return pageInfo;
	}
	
	/**
	 * 通过请假申请但仍需审核
	 * @param leaveExamineForm
	 */
	public void passLeaveApply(LeaveExamineForm leaveExamineForm) {
		System.out.println(leaveExamineForm.getNextExamineUserId());
		if(!leaveDao.updateLeaveStream(leaveExamineForm))
			throw new RuntimeException("更新请假申请流程信息失败");		
		if(!leaveDao.insertLeaveStream(leaveExamineForm.toApplyForm()))
			throw new RuntimeException("插入请假申请流程信息失败");
	}
	
	/**
	 * 通过请假申请无需再审核
	 * @param leaveExamineForm
	 */
	public void finallyPassLeaveApply(LeaveExamineForm leaveExamineForm) {
		if(!leaveDao.updateLeaveStream(leaveExamineForm))
			throw new RuntimeException("更新请假申请流程信息失败");
		if(!leaveDao.insertRecordLeaveStream(leaveExamineForm))
			throw new RuntimeException("插入请假申请备案流程信息失败");
		
	}
	
	/**
	 * 驳回请假申请
	 * @param leaveExamineForm
	 */
	public void rejectLeaveApply(LeaveExamineForm leaveExamineForm) {
		if(!leaveDao.updateLeaveStream(leaveExamineForm))
			throw new RuntimeException("更新请假流程信息失败");
		if(!leaveDao.updateLeaveApply(leaveExamineForm))
			throw new RuntimeException("更新请假申请信息失败");
	}
	
	/**
	 * 备案人通过请假申请并备案
	 * @param leaveExamineForm
	 */
	public void recordLeaveApply(LeaveExamineForm leaveExamineForm) {
		if(!leaveDao.recordLeaveStream(leaveExamineForm))
			throw new RuntimeException("备案请假流程信息失败");
		if(!leaveDao.recordLeaveApply(leaveExamineForm))
			throw new RuntimeException("备案请假申请信息失败");
	}
	
	/**
	 * 申请人撤回请假申请
	 * @param leaveExamineForm
	 */
	public void withdrawLeaveApply(LeaveExamineForm leaveExamineForm) {
		if(!leaveDao.withdrawLeaveStream(leaveExamineForm))
			throw new RuntimeException("撤回请假流程失败");
		if(!leaveDao.withdrawLeaveApply(leaveExamineForm))
			throw new RuntimeException("撤回请假申请失败");
	}

}
