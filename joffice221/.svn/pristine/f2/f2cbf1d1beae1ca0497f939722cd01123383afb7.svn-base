package com.htsoft.oa.service.flow;

import javax.servlet.http.HttpServletRequest;

import com.htsoft.oa.model.flow.NodeNodeUserMapping;
import com.htsoft.oa.model.flow.ProcessRun;
import java.util.List;

public interface ProcessService {
	/**
	 * 启动工作流  传入defId
	 * @param request
	 * @return
	 */
	public ProcessRun doStartFlow(HttpServletRequest request) throws Exception;
	/**
	 * 执行流程跳转下一步
	 * @param request
	 * @return
	 */
	public ProcessRun doNextFlow(HttpServletRequest request) throws Exception;
	
	
	/**
	 * 获取流程启动的跳转分支路径及其对应的执行人员
	 * @param defId
	 * @param curUserId
	 * @return
	 */
	public List<NodeNodeUserMapping> getStartDefNodeUserMapping(Long defId,Long curUserId);
	
	
	/**
	 * 获取流程启动的跳转分支路径及其对应的执行人员
	 * @param defId
	 * @param curUserId
	 * @param isNextStart 把开始节点与下一步结节当作一个节点
	 * @return
	 */
	public List<NodeNodeUserMapping> getStartDefNodeUserMapping(Long defId,Long curUserId,boolean isNextStart);
	
	/**
	 * 
	 * @param taskId
	 * @param curUserId
	 * @return
	 */
	public List<NodeNodeUserMapping> getTaskNodeUserMapping(Long taskId,Long curUserId);
	
	/**
	 * 获取某个节点对应的所有跳转分支的人员映射
	 * @param taskId
	 * @param curUserId
	 * @param destName
	 * @return
	 */
	public List<NodeNodeUserMapping> getNodeUserMapping(Long taskId,Long curUserId,String destName);
	
	/**
	 * 移动端执行流程跳转下一步
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public ProcessRun mobileDoNextFlow(HttpServletRequest request) throws Exception;
	
}
