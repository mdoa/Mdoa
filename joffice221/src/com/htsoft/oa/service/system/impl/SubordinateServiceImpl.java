package com.htsoft.oa.service.system.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.OrganizationDao;
import com.htsoft.oa.dao.system.SubordinateDao;
import com.htsoft.oa.dao.system.UserOrgDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.Subordinate;
import com.htsoft.oa.model.system.UserOrg;
import com.htsoft.oa.service.system.SubordinateService;
import com.htsoft.oa.service.system.UserOrgService;

public class SubordinateServiceImpl extends BaseServiceImpl<Subordinate> implements SubordinateService{
	@SuppressWarnings("unused")
	private SubordinateDao dao;
	@Resource
	private UserOrgDao userOrgDao;
	@Resource
	private OrganizationDao organizationDao;
	public SubordinateServiceImpl(SubordinateDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/**
	 * 获取上下级记录
	 * @param userId
	 * @param demId
	 * @param isSuper
	 * @return
	 */
	public List<Subordinate> findByCondition(Long userId, Long demId, Short relative){
		return dao.findByCondition(userId, demId, relative);
	}
	
	/**
	 * 根据userId和jobUserId及demId查询对应数据的总条数，返回:对应的总数据条数
	 */	
	public AppUser judge(Long userId, Long jobUserId,Long demId){
		return dao.judge(userId, jobUserId, demId);
	}
	/**
	 * 获取上下级记录
	 * @param userId
	 * @param jobUserId
	 * @return
	 */
	public Subordinate getByBothUserId(Long userId,  Long jobUserId){
		return dao.getByBothUserId(userId, jobUserId);
	}
	
	/**
	 * 获取某维度下的某用户某级用户
	 * @param userId
	 * @param jobUserId
	 * @return
	 */
	public List<AppUser> getByLevel(Long userId,Long demId,Integer level){
		Short relative;
		 List<AppUser> userList=new ArrayList<AppUser>();  //最终返回数据
		 Organization userOrg=getUserOrg(demId.toString(),userId.toString());  //取主用户组织
		 if(userOrg==null) return null;
		 String path=userOrg.getPath();             //主用户组织路径
		 String[] temp= path.split("[.]");     
		 StringBuffer targetPath=new StringBuffer("");
		Integer targerDepth=new Integer(userOrg.getDepth().toString());		
		if(level>0){
			relative=new Short("1");
			targerDepth=targerDepth-level+1;//加1 是因为第1级为同部门
		}
	    else if(level==0){
			relative=new Short("2");
	    }
		else{
			relative=new Short("0");
			targerDepth=targerDepth-level-1;
		}	
		 List<Subordinate>list=dao.findByCondition(userId, demId, relative);//取主用户的上下级关系 
		 if(level==0){		  //若为同级，直接返回查询结果
			 for(Subordinate sub:list)
				 userList.add(sub.getJobUser());
			 return userList;
		 }
		 for(int i=0;i<targerDepth;i++){
			 targetPath.append(temp[i]+".");
		 }
		 List<Organization> targetlist=organizationDao.getDirectByPath(targetPath.toString(),new Long( targerDepth.toString())); //取得对应级别上的组织  这是数组
	 
		
		 for(Subordinate sub:list){
			Long jobUDId= sub.getJobUser().getDepartment().getDepId();
			for(Organization org:targetlist){
				if(org.getOrgId().equals(jobUDId)){
					userList.add(sub.getJobUser());
					break;
				}				
			}
		 }
		return userList;
	}
	
	//查询某维度下某用户所属组织
	public Organization getUserOrg(String demId,	String userId ) {
		List<UserOrg> userOrglists=userOrgDao.getDepOrgsByUserId(new Long(userId));
		
		Organization userOrg=null; 
		for(UserOrg item:userOrglists){
			Organization orgza=item.getOrganization();
				if(orgza.getDemId().toString().equals(demId)){
					if(userOrg==null || item.getIsPrimary()==1)
							userOrg=orgza;		
			}
		}		
		return userOrg;
	}
	
	/**
	 * 取得某个用户的上级
	 */
	public Set<AppUser> getUpUser(Long userId){
		return dao.getUpUser(userId);
	}
}