package com.htsoft.oa.dao.flow;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.ProcessRun;

/**
 * @description 运行中的流程管理
 * @class ProcessRunDao
 * @author 宏天软件
 * @updater YHZ
 * @company www.jee-soft.cn
 * @data 2010-12-28PM
 * 
 */
public interface ProcessRunDao extends BaseDao<ProcessRun> {

	/**
	 * 
	 * @param piId
	 * @return
	 */
	public ProcessRun getByPiId(String piId);

	/**
	 * 
	 * @param defId
	 * @param pb
	 * @return
	 */
	public List<ProcessRun> getByDefId(Long defId, PagingBean pb);

	/**
	 * 按标题模糊查询某个用户所参与的流程列表
	 * 
	 * @param userId
	 * @param subject
	 * @param pb
	 * @return
	 */
	public List<ProcessRun> getByUserIdSubject(Long userId, String subject,
			PagingBean pb);

	/**
	 * @description 根据流程定义Id,查询对应的数据，如果存在:true,否则:false
	 * @param defId
	 *            流程定义Id
	 * @return 存在:true
	 */
	boolean checkRun(Long defId);
	/**
	 * 查找某个流程正在进行的实例
	 * @param defId
	 * @return
	 */
	public List<ProcessRun> getProcessRunning(Long defId);
}