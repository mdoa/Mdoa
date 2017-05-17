package com.htsoft.oa.action.system;
/*
 *  杭州梦德软件有限公司
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.util.BeanUtil;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;


import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.UserOrg;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.OrganizationService;
import com.htsoft.oa.service.system.UserOrgService;
/**
 * 
 * @author 
 *
 */
public class UserOrgAction extends BaseAction{
	@Resource
	private AppUserService appUserService;
	@Resource
	private OrganizationService organizationService;
	
	@Resource
	private UserOrgService userOrgService;
	private UserOrg userOrg;
	
	private Long userOrgId;

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public UserOrg getUserOrg() {
		return userOrg;
	}

	public void setUserOrg(UserOrg userOrg) {
		this.userOrg = userOrg;
	}
	/**
	 * 查找某一用户所有的部门列表
	 * @return
	 */
	public String find(){
		
		String userId=getRequest().getParameter("userId");
		if(StringUtils.isNotEmpty(userId)){
			List<UserOrg> userOrgs=userOrgService.getDepOrgsByUserId(new Long(userId));
			StringBuffer sb=new StringBuffer("{success:true,result:[");
			for(UserOrg userOrg:userOrgs){
				sb.append("{userOrgId:'").append(userOrg.getUserOrgId())
				.append("',orgId:'").append(userOrg.getOrganization().getOrgId())
				.append("',orgName:'").append(userOrg.getOrganization().getOrgName())
				.append("',isPrimary:'").append(userOrg.getIsPrimary())
				.append("',isCharge:'").append(userOrg.getIsCharge()).append("'},");
			}
			if(userOrgs.size()>0){
				sb.deleteCharAt(sb.length()-1);
			}
			sb.append("]}");
			jsonString=sb.toString();
		}else{
			jsonString="{success:true,result[]}";
		}
		return SUCCESS;
	}
	/**
	 * 为某个部分添加所属组织或部门
	 * @return
	 */
	public String addOrgs(){
		String userIds=getRequest().getParameter("userIds");
		String orgId=getRequest().getParameter("orgId");
		if(StringUtils.isNotEmpty(userIds)){
			String[]uIds=userIds.split("[,]");
			Organization org=organizationService.get(new Long(orgId));
			for(String id:uIds){
				List userOrgs=userOrgService.getByUserIdOrgId(new Long(id), new Long(orgId));
				if(userOrgs.size()==0){
					UserOrg userOrg=new UserOrg();
					AppUser user=appUserService.get(new Long(id));
					userOrg.setAppUser(user);
					userOrg.setOrganization(org);
					userOrg.setIsPrimary(UserOrg.NOT_PRIMARY);
					userOrg.setIsCharge(UserOrg.NOT_CHARGE);
					userOrgService.save(userOrg);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<UserOrg> list= userOrgService.getAll(filter);
		
		Type type=new TypeToken<List<UserOrg>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String userIds = getRequest().getParameter("ids");
		String orgId = getRequest().getParameter("orgId");
		Organization org = organizationService.get(new Long(orgId));
		List<Organization> orgList= organizationService.getByPath(org.getPath());
		String returnMeg="成功删除该记录！";
		for(String uid:userIds.split(",")){
			if(uid.length()>0){
				for(Organization og:orgList){
					if(	orgId.equals(og.getOrgId().toString())){
						if(userOrgService.getUsersByOrgIdandIsPrimary(og.getOrgId(), UserOrg.PRIMARY).size()>0){
							returnMeg="注意：该部门是用户的主部门不能成功删除，请先修改用户的主部门信息再进行删除！";
						}else{
							userOrgService.delCascade(new Long(uid), og.getOrgId());
						}
					}else{
						userOrgService.delCascade(new Long(uid), og.getOrgId(), UserOrg.NOT_PRIMARY);
						//判断该部门是否所属于该用户
						if(userOrgService.getByUserIdOrgId(new Long(uid), og.getOrgId()).size()>0){
							returnMeg="注意：该用户不属于该部门不能成功删除，请选择对应用户所属的部门信息进行删除！";
						}
					}				
				}
			}
		}
		jsonString="{success:true,msg:\""+returnMeg+"\"}";
		
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		UserOrg userOrg=userOrgService.get(userOrgId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(userOrg));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(userOrg.getUserOrgId()==null){
			userOrgService.save(userOrg);
		}else{
			UserOrg orgUserOrg=userOrgService.get(userOrg.getUserOrgId());
			try{
				BeanUtil.copyNotNullProperties(orgUserOrg, userOrg);
				userOrgService.save(orgUserOrg);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	/**
	 * 删除对应的用户组织
	 * @return
	 */
	public String del(){
		if(userOrgId!=null){
			userOrgService.remove(userOrgId);
		}
		return SUCCESS;
	}
}
