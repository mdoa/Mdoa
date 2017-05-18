package com.mdoa.personnel.dao;

import java.util.List;

import com.mdoa.personnel.bo.InviteApplyForm;
import com.mdoa.personnel.model.InviteRecord;

public interface InviteDao {
	/**
	 * 获取uuid
	 */
	String getuuid();
	//添加计划and流
	boolean addInvitePlan(InviteApplyForm inviteApplyForm);
	boolean addInvitePlanStream(InviteApplyForm inviteApplyForm);//发起的时候加的审批流
	//查询计划
	List<InviteApplyForm> queryInviteApply(InviteApplyForm inviteApplyForm);
	//撤回计划and流
	boolean backInviteApply(InviteApplyForm inviteApplyForm);
	boolean backInviteStreamApply(InviteApplyForm inviteApplyForm);
	//删除计划and流
	boolean deleteInviteApply(InviteApplyForm inviteApplyForm);
	boolean deleteInviteStreamApply(InviteApplyForm inviteApplyForm);
	//查询计划流
	List<InviteApplyForm> queryStreamInviteApply(InviteApplyForm inviteApplyForm);
	//收到审批信息后去填写审批意见==然后给下一个人,根据下一个人的id添加一个审批流
	boolean examineUpdateStream(InviteApplyForm inviteApplyForm);
	boolean examineAddStream(InviteApplyForm inviteApplyForm);//后面根据下一个审批人加的审批流
	//审批流程驳回,同时修改计划表和流程表中的状态
	boolean rejectInviteStream(InviteApplyForm inviteApplyForm);
	boolean rejectInviteApply(InviteApplyForm inviteApplyForm);
	//进入备案状态，添加备案计划流
	boolean recordAddInviteStream(InviteApplyForm inviteApplyForm);
	//人事进行备案审批，需要同时修改2张表,①修改流②修改计划表
	boolean recordUpdateInviteApplyStream(InviteApplyForm inviteApplyForm);
	boolean recordUpdateInviteApply(InviteApplyForm inviteApplyForm);
	//记录表的增删改查
	boolean addInviteRecord(InviteRecord inviteRecord);
	boolean deleteInviteRecord(InviteRecord inviteRecord);
	boolean updateInviteRecord(InviteRecord inviteRecord);
	List<InviteRecord> queryAllInviteRecord(InviteRecord inviteRecord);
	//驳回并指定下一个人,修改和添加流程表
	boolean rejectUpdateNextInviteStream(InviteApplyForm inviteApplyForm);
	boolean rejectAddNextInviteStream(InviteApplyForm inviteApplyForm);
	//查询我参与的审批流程
	List<InviteApplyForm> queryInviteApplyByMy(InviteApplyForm inviteApplyForm);
	//查询待备案流程
	List<InviteApplyForm> queryInviteRecordApply(InviteApplyForm inviteApplyForm);
	//批量删除招聘记录
	boolean deleteInviteRecordAll(InviteRecord inviteRecord);
	
	
}
