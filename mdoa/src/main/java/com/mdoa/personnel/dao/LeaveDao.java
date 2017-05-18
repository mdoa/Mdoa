package com.mdoa.personnel.dao;

import java.util.List;

import com.mdoa.personnel.bo.LeaveApplyForm;
import com.mdoa.personnel.bo.LeaveExamineForm;
import com.mdoa.personnel.bo.LeaveStreamForm;
import com.mdoa.personnel.model.LeaveApply;
import com.mdoa.personnel.model.LeaveStream;

public interface LeaveDao {
	/**
	 * 得到uuid
	 * @return
	 */
	String getuuid();

	/**
	 * 插入请假申请信息
	 * @param leaveApplyForm
	 * @return
	 */
	boolean insertLeaveApply(LeaveApplyForm leaveApplyForm);
	
	/**
	 * 插入请假流程信息
	 * @param leaveApplyForm
	 * @return
	 */
	boolean insertLeaveStream(LeaveApplyForm leaveApplyForm);
	
	/**
	 * 条件查询请假申请信息
	 * @param leaveApplyForm
	 * @return
	 */
	List<LeaveApply> findApplyByCondition(LeaveApplyForm leaveApplyForm);
	
	/**
	 * 审核人id查询请假申请信息
	 * @param examineUserId
	 * @return
	 */
	List<LeaveApply> findApplyByExamineUserId(String examineUserId);
	
	/**
	 * 条件查询请假审核流程信息
	 * @param leaveApplyForm
	 * @return
	 */
	List<LeaveStreamForm> findStreamByCondition(LeaveApplyForm leaveApplyForm);
	
	/**
	 * 更新请假流程信息
	 * @param leaveExamineForm
	 * @return
	 */
	boolean updateLeaveStream(LeaveExamineForm leaveExamineForm);
	
	/**
	 * 插入请假流程备案信息
	 * @param leaveExamineForm
	 * @return
	 */
	boolean insertRecordLeaveStream(LeaveExamineForm leaveExamineForm);
	
	/**
	 * 更新请假申请信息
	 * @param leaveExamineForm
	 * @return
	 */
	boolean updateLeaveApply(LeaveExamineForm leaveExamineForm);
	
	/**
	 * 备案请假流程信息
	 * @param leaveExamineForm
	 * @return
	 */
	boolean recordLeaveStream(LeaveExamineForm leaveExamineForm);
	
	/**
	 * 备案请假申请信息
	 * @param leaveExamineForm
	 * @return
	 */
	boolean recordLeaveApply(LeaveExamineForm leaveExamineForm);
	
	/**
	 * 撤回请假流程
	 * @param leaveExamineForm
	 * @return
	 */
	boolean withdrawLeaveStream(LeaveExamineForm leaveExamineForm);
	
	/**
	 * 撤回请假申请
	 * @param leaveExamineForm
	 * @return
	 */
	boolean withdrawLeaveApply(LeaveExamineForm leaveExamineForm);
	

}
