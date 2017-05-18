package com.mdoa.personnel.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.base.service.BaseService;
import com.mdoa.framework.model.Department;
import com.mdoa.framework.model.Post;
import com.mdoa.personnel.bo.TrainApplyForm;
import com.mdoa.personnel.dao.TrainDao;
import com.mdoa.personnel.model.Train;
import com.mdoa.personnel.model.TrainIdAndName;
import com.mdoa.personnel.model.TrainUserIdAndNameByPosition;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class TrainService extends BaseService{

	@Autowired
	private TrainDao trainDao;

	/**
	 * 把前台的部门还有数据都TrainIdAndName中，以list集合的方法返回
	 * @param trainApplyForm
	 * @return
	 */
	public List<TrainIdAndName> getDepartmentList(TrainApplyForm trainApplyForm){
		List<TrainIdAndName> list = new ArrayList<TrainIdAndName>();
		String[] departmentIds=trainApplyForm.getDepartmentIds();
		String[] departmentNames=trainApplyForm.getDepartmentNames();
		if(departmentIds.length == departmentNames.length){
			for (int i = 0; i < departmentIds.length; i++) {
				TrainIdAndName trainIdAndName = new TrainIdAndName();
				trainIdAndName.setDepartmentId(departmentIds[i]);
				trainIdAndName.setDepartmentName(departmentNames[i]);
				trainIdAndName.setTrainApplyForm(trainApplyForm);
				list.add(trainIdAndName);
			}
			return list;
		}
		throw new RuntimeException("获取部门集合失败");
	}
	/**
	 * 把前台的岗位还有数据都TrainIdAndName中，以list集合的方法返回
	 * @param trainApplyForm
	 * @return
	 */
	public List<TrainIdAndName> getPostList(TrainApplyForm trainApplyForm){
		List<TrainIdAndName> list = new ArrayList<TrainIdAndName>();
		String[] postIds=trainApplyForm.getPostIds();
		String[] postNames=trainApplyForm.getPostNames();
		if(postIds.length == postNames.length){            
			for (int i = 0; i < postNames.length; i++) {
				TrainIdAndName trainIdAndName = new TrainIdAndName();
				trainIdAndName.setPostId(postIds[i]);
				trainIdAndName.setPostName(postNames[i]);
				trainIdAndName.setTrainApplyForm(trainApplyForm);
				list.add(trainIdAndName);
			}
			return list;
		}
		throw new RuntimeException("获取岗位集合失败");
	}
	/**
	 * 把前台的用户还有数据都TrainIdAndName中，以list集合的方法返回
	 * @param trainApplyForm
	 * @return
	 */
	public List<TrainIdAndName> getUserList(TrainApplyForm trainApplyForm){
		List<TrainIdAndName> list = new ArrayList<TrainIdAndName>();
		
		String[] userIds = StringUtil.splitString(trainApplyForm.getUserId());
		String[] userNames = StringUtil.splitString(trainApplyForm.getUserName());
		if(userIds.length == userNames.length){
			for (int i = 0; i < userNames.length; i++) {
				TrainIdAndName trainIdAndName = new TrainIdAndName();
				trainIdAndName.setUserId(userIds[i]);
				trainIdAndName.setUserName(userNames[i]);
				trainIdAndName.setTrainApplyForm(trainApplyForm);
				list.add(trainIdAndName);
			}
			return list;
		}
		throw new RuntimeException("获取用户集合失败");
	}
	
	/**
	 * 发起培训申请，添加一张培训计划表和一张培训流程表以及一张培训对象表
	 * @param trainApplyForm
	 */
	public void startTrainApply(TrainApplyForm trainApplyForm, HttpServletRequest request) {
		String uuid = getuuid();
		trainApplyForm.setTrainId(uuid);
		UserInfo userInfo=getUser(request);
		trainApplyForm.setCreateUserId(userInfo.getUserId());
		trainApplyForm.setCreateUserName(userInfo.getUserName());
		if(!trainDao.startTrainApply(trainApplyForm))
			throw new RuntimeException("培训计划表添加失败");
		if(!trainDao.startTrainApplyStream(trainApplyForm))
			throw new RuntimeException("培训流程表添加失败");
		
	}
	/**
	 * 查询培训信息o
	 * @param trainApplyForm
	 * @return
	 */
	public PageModel<TrainApplyForm> queryTrainApplyO(TrainApplyForm trainApplyForm) {
		if(!StringUtil.isEmpty(trainApplyForm.getTrainName()))
			trainApplyForm.setTrainName(trainApplyForm.getTrainName()+"%");
		PageHelper.startPage(trainApplyForm.getPage(), trainApplyForm.getRows());
		List<TrainApplyForm> list=trainDao.queryTrainApplyO(trainApplyForm);
		PageModel<TrainApplyForm> pageInfo=new PageModel<>((Page<TrainApplyForm>)list);
		return pageInfo;
	}
	/**
	 * 查询培训流程表
	 * @param trainApplyForm
	 * @return
	 */
	public List<TrainApplyForm> queryTrainApplyStream(TrainApplyForm trainApplyForm) {
		List<TrainApplyForm> list=trainDao.queryTrainApplyStream(trainApplyForm);
		return list;
	}
	/**
	 * 驳回培训申请的情况
	 * @param trainApplyForm
	 */
	public void rejectTrainApply(TrainApplyForm trainApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		trainApplyForm.setExamineUserId(userInfo.getUserId());
		trainApplyForm.setExamineUserName(userInfo.getUserName());
		trainApplyForm.setUpdateUserId(userInfo.getUserId());
		trainApplyForm.setUpdateUserName(userInfo.getUserName());
		if(!trainDao.rejectTrainApplyStream(trainApplyForm))
			throw new RuntimeException("驳回培训流程失败");
		if(!trainDao.rejectTrainApply(trainApplyForm))
			throw new RuntimeException("驳回培训申请失败");
	}
	/**
	 * 通过审批并给下一级
	 * @param trainApplyForm
	 */
	public void passAndNextTrainApply(TrainApplyForm trainApplyForm) {
		if(!trainDao.passAndNextUpdateStream(trainApplyForm))
			throw new RuntimeException("通过培训流程失败");
		trainApplyForm.setExamineUserId(trainApplyForm.getNextExamineUser());
		trainApplyForm.setExamineUserName(trainApplyForm.getNextExamineUserName());
		if(!trainDao.passAndNextAddStream(trainApplyForm))
			throw new RuntimeException("新流程添加失败");
	}
	/**
	 * 进入备案流程
	 * @param trainApplyForm
	 */
	public void joinTrainRecordStream(TrainApplyForm trainApplyForm) {
		if(!trainDao.joinTrainRecordStream(trainApplyForm))
			throw new RuntimeException("进入备案流程失败");
		if(!trainDao.passAndNextUpdateStream(trainApplyForm))
			throw new RuntimeException("通过培训流程失败");
	}
	/**
	 * 人事进行备案
	 * @param trainApplyForm
	 */
	public void hrRecordTrain(TrainApplyForm trainApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		trainApplyForm.setExamineUserId(userInfo.getUserId());
		trainApplyForm.setExamineUserName(userInfo.getUserName());
		trainApplyForm.setRecordUserId(userInfo.getUserId());
		trainApplyForm.setUpdateUserId(userInfo.getUserId());
		trainApplyForm.setUpdateUserName(userInfo.getUserName());
		if(!trainDao.hrRecordUpdateTrainApply(trainApplyForm))
			throw new RuntimeException("备案培训申请失败");
		if(!trainDao.hrRecordUpdateTrainApplyStream(trainApplyForm))
			throw new RuntimeException("备案培训流失败");
	}
	/**
	 * 人事驳回并让下一级进行审批
	 * @param trainApplyForm
	 */
	public void rejectAndNextTrainApply(TrainApplyForm trainApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		trainApplyForm.setExamineUserId(userInfo.getUserId());
		trainApplyForm.setExamineUserName(userInfo.getUserName());
		if(!trainDao.rejectAndNextUpdateTrainStream(trainApplyForm))
			throw new RuntimeException("人事驳回流程失败");
		if(!trainDao.rejectAndNextAddTrainStream(trainApplyForm))
			throw new RuntimeException("人事添加下一级流程失败");
	}

	/**
	 * 撤回培训申请
	 * @param trainApplyForm
	 */
	public void backTrainApply(TrainApplyForm trainApplyForm) {
		if(!trainDao.backTrainApply(trainApplyForm))
			throw new RuntimeException("撤回培训申请失败");
		if(!trainDao.backTrainApplyStream(trainApplyForm))
			throw new RuntimeException("撤回培训流程失败");
	}

	/**
	 * 写培训记录
	 * @param trainApplyForm
	 */
	public void wirteTrainRecord(TrainApplyForm trainApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		trainApplyForm.setUpdateUserId(userInfo.getUserId());
		trainApplyForm.setUpdateUserName(userInfo.getUserName());
		if(!trainDao.wirteTrainRecord(trainApplyForm))
			throw new RuntimeException("培训记录修改失败");
	}
	
	
	/**
	 * 根据培训Id查询培训的详细信息
	 * 待优化
	 */
	public TrainApplyForm queryTrainMessage(TrainApplyForm trainApplyForm){
		TrainApplyForm train = trainDao.queryTrainApply(trainApplyForm);
		return train;
	}
	
	/**
	 * 查询培训人员信息
	 * @param trainApplyForm
	 * @return
	 */
	public PageModel<TrainApplyForm> queryTrainPersonMessage(TrainApplyForm trainApplyForm, HttpServletRequest request) {
		PageHelper.startPage(trainApplyForm.getPage(),trainApplyForm.getRows());
		List<TrainApplyForm> list =  trainDao.queryTrainPersonMessage(trainApplyForm);
		PageModel<TrainApplyForm> pageModel=new PageModel<>((Page<TrainApplyForm>)list);
		return pageModel;
	}
	/**
	 * 修改培训人员信息
	 * @param trainApplyForm
	 */
	public void updateTrainRecord(TrainApplyForm trainApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		trainApplyForm.setUpdateUserId(userInfo.getUserId());
		if (!trainDao.updateTrainRecord(trainApplyForm))
			throw new RuntimeException("修改培训人员信息失败");
			
	}
	/**
	 * 删除培训人员信息
	 * @param trainApplyForm
	 */
	public void deleteTrainRecord(TrainApplyForm trainApplyForm, HttpServletRequest request) {
		UserInfo userInfo=getUser(request);
		trainApplyForm.setUpdateUserId(userInfo.getUserId());
		if(!trainDao.deleteTrainRecord(trainApplyForm))
			throw new RuntimeException("删除人员信息失败");
	}
	
	/**
	 * 查询我参与的培训审核流程信息
	 */
	public PageModel<TrainApplyForm> queryMyTrainStream(TrainApplyForm trainApplyForm){
		if(!StringUtil.isEmpty(trainApplyForm.getCreateUserName()))
			trainApplyForm.setCreateUserName("'"+trainApplyForm.getCreateUserName()+"%'");
		if(!StringUtil.isEmpty(trainApplyForm.getTrainName()))
			trainApplyForm.setTrainName("'"+trainApplyForm.getTrainName()+"%'");
		PageHelper.startPage(trainApplyForm.getPage(), trainApplyForm.getRows());
		List<TrainApplyForm> list = trainDao.queryMyTrainStream(trainApplyForm);
		PageModel<TrainApplyForm> page = new PageModel<TrainApplyForm>((Page<TrainApplyForm>)list);
		return page;
	}
	
	/**
	 * 查询需要进行备案的培训流程
	 * @param trainApplyForm 申请人姓名，培训名称
	 * @return 
	 */
	public PageModel<TrainApplyForm> queryTrainRecordStream(TrainApplyForm trainApplyForm, HttpServletRequest request){
		PageHelper.startPage(trainApplyForm.getPage(), trainApplyForm.getRows());
		List<TrainApplyForm> list = trainDao.queryTrainRecordStream(trainApplyForm);
		PageModel<TrainApplyForm> page = new PageModel<TrainApplyForm>((Page<TrainApplyForm>)list);
		return page;
	}
	
	/**
	 * 根据记录Id来修改员工的培训记录
	 */
	public void updateTrainJoinFlag(String joinFlag, String trainDocId, HttpServletRequest request){
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("joinFlag", joinFlag);
		params.put("trainDocId", trainDocId);
		UserInfo user = getUser(request);
		params.put("userName", user.getUserName());
		params.put("userId", user.getUserId());
		if(!trainDao.updateTrainJoinFlag(params)){
			throw new RuntimeException("根据员工Id来修改员工的培训记录");
		}
	}
	
}
