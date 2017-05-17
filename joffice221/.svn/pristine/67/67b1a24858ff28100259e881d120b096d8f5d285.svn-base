package com.htsoft.oa.action.system;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Position;
import com.htsoft.oa.model.system.UserOrg;
import com.htsoft.oa.model.system.UserPosition;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.PositionService;
import com.htsoft.oa.service.system.UserPositionService;
/**
 * 
 * @author 
 *
 */
public class UserPositionAction extends BaseAction{
	@Resource
	private UserPositionService userPositionService;	
	@Resource
	private PositionService positionService;
	@Resource
	private AppUserService appUserService;
	private UserPosition userPosition;
	
	private Long userPosId;

	public Long getUserPosId() {
		return userPosId;
	}

	public void setUserPosId(Long userPosId) {
		this.userPosId = userPosId;
	}

	public UserPosition getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(UserPosition userPosition) {
		this.userPosition = userPosition;
	}
	
	/**
	 * 查找某一用户的职位
	 * @return
	 */
	public String find(){
		String userId=getRequest().getParameter("userId");
		if(StringUtils.isNotEmpty(userId)){
			StringBuffer sb=new StringBuffer("{success:true,result:[");
			List<UserPosition> list=userPositionService.getByUserPos(new Long(userId));
			for(UserPosition up:list){
				sb.append("{userPosId:'").append(up.getUserPosId()).append("',posId:'").append(up.getPosition().getPosId()).append("',posName:'").append(up.getPosition().getPosName()).append("',isPrimary:'").append(up.getIsPrimary()).append("'},");
			}
			if(list.size()>0){
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
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<UserPosition> list= userPositionService.getAll(filter);
		
		Type type=new TypeToken<List<UserPosition>>(){}.getType();
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
		String posId = getRequest().getParameter("posId");
		for(String uid:userIds.split(",")){
			if(uid.length()>0){
				userPositionService.delCascade(new Long(uid), new Long(posId));
			}
		}
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		UserPosition userPosition=userPositionService.get(userPosId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(userPosition));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		String userIds=getRequest().getParameter("userIds");
		String posId=getRequest().getParameter("posId");
		if(StringUtils.isNotEmpty(userIds)){
			String[]uIds=userIds.split("[,]");
			Position pos=positionService.get(new Long(posId));
			for(String id:uIds){
				List userPos=userPositionService.getUserIdsByPosId(new Long(posId));
				if(!userPos.contains(new Long(id))){
					UserPosition up=new UserPosition();
					AppUser user=appUserService.get(new Long(id));
					up.setAppUser(user);
					up.setPosition(pos);
					up.setIsPrimary(UserPosition.NOT_PRIMARY);
					userPositionService.save(up);
				}
			}
		}
		return SUCCESS;
		
	}
	/**
	 * 删除用户部门
	 * @return
	 */
	public String del(){
		if(userPosId!=null){
			userPositionService.remove(userPosId);
		}
		return SUCCESS;
	}
}
