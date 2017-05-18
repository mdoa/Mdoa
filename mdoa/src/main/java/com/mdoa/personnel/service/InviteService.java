package com.mdoa.personnel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import com.mdoa.base.service.BaseService;
import com.mdoa.personnel.bo.InviteApplyForm;
import com.mdoa.personnel.bo.InviteEngageUserForm;
import com.mdoa.personnel.dao.InviteDao;
import com.mdoa.personnel.model.InviteRecord;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

@Service
@Transactional
public class InviteService extends BaseService{
	@Autowired
	private InviteDao inviteDao;
	
	//添加计划and流
	public void startInviteApply(InviteApplyForm inviteApplyForm, HttpServletRequest request) {
		inviteApplyForm.setInviteId(inviteDao.getuuid());//手动加inviteId
		UserInfo userInfo=getUser(request);
		inviteApplyForm.setApplyUserName(userInfo.getUserName());
		inviteApplyForm.setApplyUserId(userInfo.getUserId());
		inviteApplyForm.setCreateUserName(userInfo.getUserName()); 
		inviteApplyForm.setCreateUserId(userInfo.getUserId());
		if(!inviteDao.addInvitePlan(inviteApplyForm))
			throw new RuntimeException("招聘计划添加失败！");
		if(!inviteDao.addInvitePlanStream(inviteApplyForm))
			throw new RuntimeException("招聘计划流添加失败！");
	}
	//按照条件进行查询招聘计划:(计划名和申请人)
	public PageModel<InviteApplyForm> queryInviteApply(InviteApplyForm inviteApplyForm) {
		if(!StringUtil.isEmpty(inviteApplyForm.getPlanName())){
			inviteApplyForm.setPlanName(inviteApplyForm.getPlanName()+"%");
		}
		if(!StringUtil.isEmpty(inviteApplyForm.getApplyUserName())){
			inviteApplyForm.setApplyUserName(inviteApplyForm.getApplyUserName()+"%");
		}
		if (!StringUtil.isEmpty(inviteApplyForm.getCreateUserName())) {
			inviteApplyForm.setCreateUserName(inviteApplyForm.getCreateUserName()+"%");
		}
		if (!StringUtil.isEmpty(inviteApplyForm.getUpdateUserName())) {
			inviteApplyForm.setUpdateUserName(inviteApplyForm.getUpdateUserName()+"%");
		}
		PageHelper.startPage(inviteApplyForm.getPage(), inviteApplyForm.getRows());
		List<InviteApplyForm> list =inviteDao.queryInviteApply(inviteApplyForm);
		PageModel<InviteApplyForm> pageInfo=new PageModel<>((Page<InviteApplyForm>)list);
		return pageInfo;
	}
	
	//查询我参与的审批计划
	public PageModel<InviteApplyForm> queryInviteApplyByMy(InviteApplyForm inviteApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		inviteApplyForm.setExamineUserId(userInfo.getUserId());
		inviteApplyForm.setExamineUserName(userInfo.getUserName());
		if (!StringUtil.isEmpty(inviteApplyForm.getPlanName())) {
			inviteApplyForm.setPlanName(inviteApplyForm.getPlanName()+"%");
		}
		if (!StringUtil.isEmpty(inviteApplyForm.getApplyUserName())) {
			inviteApplyForm.setApplyUserName(inviteApplyForm.getApplyUserName()+"%");
		}
		if (!StringUtil.isEmpty(inviteApplyForm.getCreateUserName())) {
			inviteApplyForm.setCreateUserName(inviteApplyForm.getCreateUserName()+"%");
		}
		if (!StringUtil.isEmpty(inviteApplyForm.getUpdateUserName())) {
			inviteApplyForm.setUpdateUserName(inviteApplyForm.getUpdateUserName()+"%");
		}
		PageHelper.startPage(inviteApplyForm.getPage(), inviteApplyForm.getRows());
		List<InviteApplyForm> list =inviteDao.queryInviteApplyByMy(inviteApplyForm);
		PageModel<InviteApplyForm> pageInfo=new PageModel<>((Page<InviteApplyForm>)list);
		return pageInfo;
	}
	
	//查询待备案流程
	public PageModel<InviteApplyForm> queryInviteRecordApply(InviteApplyForm inviteApplyForm) {
		if (!StringUtil.isEmpty(inviteApplyForm.getPlanName())) {
			inviteApplyForm.setPlanName(inviteApplyForm.getPlanName()+"%");
		}
		if (!StringUtil.isEmpty(inviteApplyForm.getApplyUserName())) {
			inviteApplyForm.setApplyUserName(inviteApplyForm.getApplyUserName()+"%");
		}
		if (!StringUtil.isEmpty(inviteApplyForm.getCreateUserName())) {
			inviteApplyForm.setCreateUserName(inviteApplyForm.getCreateUserName()+"%");
		}
		if (!StringUtil.isEmpty(inviteApplyForm.getUpdateUserName())) {
			inviteApplyForm.setUpdateUserName(inviteApplyForm.getUpdateUserName()+"%");
		}
		PageHelper.startPage(inviteApplyForm.getPage(), inviteApplyForm.getRows());
		List<InviteApplyForm> list =inviteDao.queryInviteRecordApply(inviteApplyForm);
		//System.out.println("nasme="+list.get(0).getPlanName());
		PageModel<InviteApplyForm> pageInfo=new PageModel<>((Page<InviteApplyForm>)list);
		return pageInfo;
	}
	//按照条件进行查询招聘计划流:(审批人和流类型)
	public PageModel<InviteApplyForm> queryStreamInviteApply(InviteApplyForm inviteApplyForm) {
		if(!StringUtil.isEmpty(inviteApplyForm.getExamineUserName())){
			inviteApplyForm.setExamineUserName(inviteApplyForm.getExamineUserName()+"%");
		}
		if(!StringUtil.isEmpty(inviteApplyForm.getStreamType())){
			inviteApplyForm.setStreamType(inviteApplyForm.getStreamType().trim());
		}
		PageHelper.startPage(inviteApplyForm.getPage(), inviteApplyForm.getRows());
		List<InviteApplyForm> list=inviteDao.queryStreamInviteApply(inviteApplyForm);
		PageModel<InviteApplyForm> pageInfo=new PageModel<InviteApplyForm>((Page<InviteApplyForm>)list);
		return pageInfo;
	}
	//撤回计划
	public void backInviteApply(InviteApplyForm inviteApplyForm, HttpServletRequest request) {
		UserInfo userInfo = getUser(request);
		inviteApplyForm.setUpdateUserId(userInfo.getUserId());
		inviteApplyForm.setUpdateUserName(userInfo.getUserName());
		if(!inviteDao.backInviteApply(inviteApplyForm))
			throw new RuntimeException("撤回招聘计划失败！");
		if(!inviteDao.backInviteStreamApply(inviteApplyForm))
			throw new RuntimeException("撤回招聘计划流失败！");
	}
	//删除计划
	public void deleteInviteApply(InviteApplyForm inviteApplyForm) {
		if(!inviteDao.deleteInviteApply(inviteApplyForm))
			throw new RuntimeException("删除招聘计划失败");
		if(!inviteDao.deleteInviteStreamApply(inviteApplyForm))	
			throw new RuntimeException("删除计划流失败");
	}
	
	//审批人进行审批(通过)，把审批内容进行update，并指定下一个审批人Id，把此人作为审批人添加审批流
	public void examineUpdateAddStream(InviteApplyForm inviteApplyForm) {
		if(!inviteDao.examineUpdateStream(inviteApplyForm))
			throw new RuntimeException();
		if(!inviteDao.examineAddStream(inviteApplyForm))
			throw new RuntimeException();
	}
	//审批被驳回的情况
	public void rejectInviteStream(InviteApplyForm inviteApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		inviteApplyForm.setUpdateUserId(userInfo.getUserId());
		inviteApplyForm.setUpdateUserName(userInfo.getUserName());
		inviteApplyForm.setExamineUserId(userInfo.getUserId());
		inviteApplyForm.setExamineUserName(userInfo.getUserName());
		if(!inviteDao.rejectInviteStream(inviteApplyForm))
			throw new RuntimeException();
		if(!inviteDao.rejectInviteApply(inviteApplyForm))
			throw new RuntimeException();
	}
	// 进入备案流程
	public void recordAddInviteStream(InviteApplyForm inviteApplyForm) {
		if(!inviteDao.recordAddInviteStream(inviteApplyForm))
			throw new RuntimeException();
		if(!inviteDao.examineUpdateStream(inviteApplyForm))
			throw new RuntimeException();
	}
	//人事进行备案审批
	public void recordUpdateInviteApply(InviteApplyForm inviteApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		inviteApplyForm.setExamineUserId(userInfo.getUserId());
		inviteApplyForm.setExamineUserName(userInfo.getUserName());
		if(!inviteDao.recordUpdateInviteApplyStream(inviteApplyForm))
			throw new RuntimeException("备案流修改失败");
		if(!inviteDao.recordUpdateInviteApply(inviteApplyForm))
			throw new RuntimeException("备案计划修改失败");
	}
	//人事驳回并提交给下一个人
	public void rejectUpdateAddInviteApply(InviteApplyForm inviteApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		inviteApplyForm.setExamineUserId(userInfo.getUserId());
		inviteApplyForm.setExamineUserName(userInfo.getUserName());
		if(!inviteDao.rejectUpdateNextInviteStream(inviteApplyForm))
			throw new RuntimeException();
		if(!inviteDao.rejectAddNextInviteStream(inviteApplyForm))
			throw new RuntimeException();
	}
	//添加招聘记录
	public void addInviteRecord(InviteRecord inviteRecord) {
		if(!inviteDao.addInviteRecord(inviteRecord))
			throw new RuntimeException("添加招聘记录失败");
	}
	//删除招聘记录
	public void deleteInviteRecord(InviteRecord inviteRecord) {
		if(!inviteDao.deleteInviteRecord(inviteRecord))
			throw new RuntimeException("删除招聘记录失败");
	}
	//批量删除招聘记录
	public void deleteInviteRecordAll(InviteRecord inviteRecord) {
		if(!inviteDao.deleteInviteRecordAll(inviteRecord))
			throw new RuntimeException("批量删除招聘记录失败");
	}
	//修改招聘记录
	public void updateInviteRecord(InviteRecord inviteRecord) {
		if(!inviteDao.updateInviteRecord(inviteRecord))
			throw new RuntimeException("修改招聘记录失败");
	}
	//查看招聘记录
	public PageModel<InviteRecord> queryAllInviteRecord(InviteRecord inviteRecord) {
		if(!StringUtil.isEmpty(inviteRecord.getName())){
			inviteRecord.setName(inviteRecord.getName()+"%");
		}
		PageHelper.startPage(inviteRecord.getPage(), inviteRecord.getRows());
		List<InviteRecord> list =inviteDao.queryAllInviteRecord(inviteRecord);
		PageModel<InviteRecord> pageInfo=new PageModel<>((Page<InviteRecord>)list);
		
		return pageInfo;
	}
	//判断是否招聘完成
	public void judgeInviteFinish(InviteApplyForm inviteApplyForm) {
		//Integer planCount=pageInfo.getList().get(0).getPlanCount();
		//Integer reallyCount=pageInfo.getList().get(0).getReallyCount();
		if(inviteApplyForm.getPlanCount()!=inviteApplyForm.getReallyCount())
			throw new RuntimeException("招聘未完成");
	}
	//判断是否有招聘权限（停止招聘与招聘中，招聘完成：3,4,5）
	public void chageInviteApply(InviteApplyForm inviteApplyForm) {
		String inviteStatus=inviteApplyForm.getInviteStatus();
		if(!inviteStatus.equals("3") && !inviteStatus.equals("4") && !inviteStatus.equals("5"))
			throw new RuntimeException("没有招聘权限");
	}
	//聘用人员
	public void engageUser(InviteEngageUserForm inviteEngageUserForm) {
		/*if (!inviteDao.engageUser(inviteEngageUserForm)) {
			throw new RuntimeException("人员表天加失败");
		}*/
	}
	
	
	
	
	
	
	
}
