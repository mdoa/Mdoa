package com.mdoa.personnel.dao;

import java.util.HashMap;
import java.util.List;

import com.mdoa.personnel.bo.TrainApplyForm;
import com.mdoa.personnel.model.Train;
import com.mdoa.personnel.model.TrainIdAndName;
import com.mdoa.personnel.model.TrainUserIdAndNameByPosition;

public interface TrainDao {

	/**
	 * 获取uuid
	 */
	String getuuid();
	
	//发起培训申请，添加一张培训计划表和一张培训流程表以及一张培训对象表
	boolean startTrainApply(TrainApplyForm trainApplyForm);
	boolean startTrainApplyStream(TrainApplyForm trainApplyForm);
	
	/**
	 * 查询培训的详细信息
	 * @param trainApplyForm
	 * @return
	 */
	TrainApplyForm queryTrainApply(TrainApplyForm trainApplyForm);
	
	
	//查询培训流程表
	List<TrainApplyForm> queryTrainApplyStream(TrainApplyForm trainApplyForm);
	//驳回培训申请，修成中状态改为驳回，申请表中也改为驳回
	boolean rejectTrainApplyStream(TrainApplyForm trainApplyForm);

	boolean rejectTrainApply(TrainApplyForm trainApplyForm);
	//通过审批并给下一级，流程表修改为通过，新建一个新的流程（审批人为next...）
	boolean passAndNextUpdateStream(TrainApplyForm trainApplyForm);
	boolean passAndNextAddStream(TrainApplyForm trainApplyForm);
	//进入备案流程
	boolean joinTrainRecordStream(TrainApplyForm trainApplyForm);
	//人事进行备案:修改培训申请表和修改流程表
	boolean hrRecordUpdateTrainApply(TrainApplyForm trainApplyForm);
	boolean hrRecordUpdateTrainApplyStream(TrainApplyForm trainApplyForm);
	//人事驳回并让下一级进行审批
	boolean rejectAndNextUpdateTrainStream(TrainApplyForm trainApplyForm);
	boolean rejectAndNextAddTrainStream(TrainApplyForm trainApplyForm);
	//撤回培训申请,要撤回培训流程和撤回培训申请
	boolean backTrainApply(TrainApplyForm trainApplyForm);
	boolean backTrainApplyStream(TrainApplyForm trainApplyForm);
	//写培训记录，修改培训申请表中的培训记录
	boolean wirteTrainRecord(TrainApplyForm trainApplyForm);
	//查询培训人员信息
	List<TrainApplyForm>  queryTrainPersonMessage(TrainApplyForm trainApplyForm);
	//修改培训人员信息
	boolean updateTrainRecord(TrainApplyForm trainApplyForm);
	//删除培训人员信息
	boolean deleteTrainRecord(TrainApplyForm trainApplyForm);
   
	/**
	 * 查询我参与的审核流程
	 */
	List<TrainApplyForm> queryMyTrainStream(TrainApplyForm trainApplyForm);
	
	/**
	 * 查询所有的等待进行备案的流程
	 * @param trainApplyForm
	 */
	List<TrainApplyForm> queryTrainRecordStream(TrainApplyForm trainApplyForm);
	
	/**
	 * 根据记录Id来修改员工的培训记录
	 */
	boolean updateTrainJoinFlag(HashMap<String, String> params);

	List<TrainApplyForm> queryTrainApplyO(TrainApplyForm trainApplyForm);
}
