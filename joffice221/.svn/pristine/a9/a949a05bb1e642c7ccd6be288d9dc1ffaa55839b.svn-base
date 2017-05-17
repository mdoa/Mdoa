package com.htsoft.oa.service.flow.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.oa.dao.flow.ProUserAssignDao;
import com.htsoft.oa.model.flow.ProUserAssign;
import com.htsoft.oa.service.flow.ProUserAssignService;

public class ProUserAssignServiceImpl extends BaseServiceImpl<ProUserAssign> implements ProUserAssignService{
	private ProUserAssignDao dao;
	
	public ProUserAssignServiceImpl(ProUserAssignDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	public List<ProUserAssign> getByDeployId(String deployId){
		return dao.getByDeployId(deployId);
	}
	/*
	 * (non-Javadoc)
	 * @see com.htsoft.oa.service.flow.ProUserAssignService#getByDeployIdActivityName(java.lang.String, java.lang.String)
	 */
	public ProUserAssign getByDeployIdActivityName(String deployId,String activityName){
		return dao.getByDeployIdActivityName(deployId, activityName);
	}
	/**
	 * 把旧版本的流程的人员配置复制至新版本上去
	 * @param oldDeplyId
	 * @param newDeployId
	 */
	public void copyNewConfig(String oldDeployId,String newDeployId){
		List<ProUserAssign> list=getByDeployId(oldDeployId);
		for(ProUserAssign assign:list){
			try{
				ProUserAssign temp=new ProUserAssign();
				BeanUtil.copyNotNullProperties(temp, assign);
				temp.setAssignId(null);
				temp.setDeployId(newDeployId);
				dao.save(temp);
			}catch(Exception ex){
				
			}
		}
	}

}