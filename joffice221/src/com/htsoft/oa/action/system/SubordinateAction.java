package com.htsoft.oa.action.system;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.RelativeUser;
import com.htsoft.oa.model.system.Subordinate;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.SubordinateService;
import com.htsoft.oa.service.system.UserOrgService;
/**
 * 
 * @author 
 *
 */
public class SubordinateAction extends BaseAction{
	@Resource
	private SubordinateService subordinateService;
	@Resource
	private AppUserService appUserService;
	@Resource
	private UserOrgService userOrgService;
	private Subordinate subordinate;
	
	private Long SubordinateId;

	public Long getSubordinateId() {
		return SubordinateId;
	}

	public void setSubordinateId(Long SubordinateId) {
		this.SubordinateId = SubordinateId;
	}

	public Subordinate getSUBORDINATE() {
		return subordinate;
	}

	public void setSUBORDINATE(Subordinate subordinate) {
		this.subordinate = subordinate;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<Subordinate> list= subordinateService.getAll(filter);
		
		Type type=new TypeToken<List<Subordinate>>(){}.getType();
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
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				Subordinate delSub=subordinateService.get(new Long(id));
				if(delSub==null) break;
					delByUid(delSub.getJobUserId().toString(),delSub.getUserId().toString());
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
		Subordinate sUBORDINATE=subordinateService.get(SubordinateId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(sUBORDINATE));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(subordinate.getSubordinateId()==null){
			subordinateService.save(subordinate);
		}else{
			Subordinate orgSUBORDINATE=subordinateService.get(subordinate.getSubordinateId());
			try{
				BeanUtil.copyNotNullProperties(orgSUBORDINATE, subordinate);
				subordinateService.save(orgSUBORDINATE);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	/**
	 * 查询某用户的上下级记录
	 * @return
	 */
	public String findDinate(){
		String uid=getRequest().getParameter("userId");
		String demId=getRequest().getParameter("demId");
		String relative=getRequest().getParameter("relative");
		List<Subordinate> list=subordinateService.findByCondition(new Long(uid), new Long(demId), new Short(relative));
		if(list.size()<1){
			JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
			jsonString=mapper.toPageJson(list, list.size());
			return SUCCESS;
		}
		Organization uidOrg= subordinateService.getUserOrg(demId,uid);
		Short relativeFlag=new Short(relative);
		for(Subordinate sde:list){
			Organization jobOrg= subordinateService.getUserOrg(demId,sde.getJobUserId().toString());
			Long dep=0L;
			switch(relativeFlag){
					case 0:
						dep= jobOrg.getDepth()-uidOrg.getDepth()+1;
						break;
					case 1:
						dep=uidOrg.getDepth()- jobOrg.getDepth()+1;
						break;
					case 2:
						dep=0L;
						break;
			}
			sde.setRelative(new Short(dep.toString()));
		}
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
		jsonString=mapper.toPageJson(list, list.size());
		//jsonString="{success:true}";
		return SUCCESS;
	}
	
	
	/**
	 * 添加多条记录
	 */
	public String mutliAdd() {
		boolean yesOrNoCurrentUser = false;
		ArrayList<Long> ex = new ArrayList<Long>(); // 存在用户,不需要添加
		String noEx = ""; // 不存在的用户
		String jobUserIds = getRequest().getParameter("jobUserIds");// 岗位员工列表
		String userId = getRequest().getParameter("userId"); // 所属员工
		String demId=getRequest().getParameter("demId");//所属维度
		String relative=getRequest().getParameter("relative");
		String delIds=getRequest().getParameter("delIds");
		delByUid(delIds,userId);
		
		if(jobUserIds.equals(""))
			return SUCCESS;
		AppUser appUser = appUserService.get(new Long(userId)); // 所属员工的详细信息
		Organization	 userOrg=subordinateService.getUserOrg(demId,userId); 
		if(userOrg==null){
			setJsonString("{success:false,msg:'"+appUser.getFullname()+"在本维度下没有加入任何组织，暂时不能添加上下级关系'}");
			return SUCCESS;
		}
		// 筛选可以添加的用户信息
		for (String uid : jobUserIds.split(",")) {
			if (uid.equals(userId)) { // 判断是否为自己
				yesOrNoCurrentUser = true;
			} else {
				AppUser apu = subordinateService.judge(new Long(userId), new Long(uid),new Long(demId));
				if (apu == null) {// 该用户不存在,需要添加的用户
					ex.add(new Long(uid));
				} else {
					noEx += apu.getFullname() + ","; // 不需要添加的,保存fullname
				}
			}
		}
		if (!noEx.equals("")){
			noEx = noEx.substring(0, noEx.length() - 1);
		}
		// 添加操作
		String msg = "";
		String errorMsg="";
		String notInMsg="";
		if (ex != null && ex.size() > 0) {
			String exMsg = "";
			for (Long uid : ex) {
				AppUser jobUser = appUserService.get(uid);
				Organization	 jobUserOrg=subordinateService.getUserOrg(demId,uid.toString()); 
				if(jobUserOrg==null){
					notInMsg+=jobUser.getFullname()+",";
					break;
				}
			
				if( relative.equals(Subordinate.SUPER_FLAG_TRUE.toString()) && userOrg.getDepth()<jobUserOrg.getDepth()){//上级
					errorMsg+=jobUser.getFullname()+"所属组织是下级组织,无法成为"+appUser.getFullname()+"的上级,";
					continue;
				}
				else if( relative.equals(Subordinate.SUPER_FLAG_FALSE.toString()) &&   userOrg.getDepth()>jobUserOrg.getDepth()){//下级
					errorMsg+=jobUser.getFullname()+"所属组织是上级组织,无法成为"+appUser.getFullname()+"的下级,";
					continue;
				}
				// 设置Subordinate对象属性
				Subordinate subSave=new Subordinate();
				subSave.setAppUser(appUser);
				subSave.setJobUser(jobUser);
				subSave.setDemID(new Long(demId));
				subSave.setRelative(new Short(relative));
				
				subordinateService.save(subSave);
								
				// 添加相对级别的用户信息
				Short level = RelativeUser.SUPER_FLAG_FALSE; // 下级
				if( relative.equals(RelativeUser.SUPER_FLAG_FALSE.toString())){
					level = RelativeUser.SUPER_FLAG_TRUE; // 上级
				}
				else if( relative.equals(RelativeUser.SUPER_FLAG_VIS.toString())){
					level = RelativeUser.SUPER_FLAG_VIS; // 上级
				}
				// 判断对应级别信息是否存在
				
				Subordinate opposite=new Subordinate();
				opposite.setAppUser(jobUser);
				opposite.setJobUser(appUser);
				opposite.setDemID(new Long(demId));
				opposite.setRelative(level);			
				subordinateService.save(opposite);
				// end
				
				exMsg += jobUser.getFullname() + ",";
			}
			msg = "{success:true,msg:'";			
			if(exMsg.length()>1)
				msg+="成功添加[" + exMsg.substring(0, exMsg.length() - 1) + "]用户,";
			if (errorMsg != null && !errorMsg.equals("")) {
				msg += "[" + errorMsg.substring(0,errorMsg.length()-1) + "]添加失败,";
			}
			if(!notInMsg.equals("")){
				msg += "[" + notInMsg.substring(0, notInMsg.length() - 1) + "]不在本维度下不能添加,";
			}
			if (yesOrNoCurrentUser) {
				msg += "用户本身不能添加,";
			}
			if(msg.endsWith(","))
				msg=msg.substring(0,msg.length()-1);
			msg = msg + "！'}";
		} else {
			msg = "{success:true,msg:'对不起，没有适合添加的用户，请重新选择！'}"; // 没有可以适合添加的用户
		}		
		setJsonString(msg);
		return SUCCESS;
	}

	
	/**
	 * 批量删除
	 * @return
	 */
	public void delByUid(String delIds,String uid){
		
		if(!delIds.equals("")){		
			for(String id:delIds.split(",")){
				if(id.equals("")) break;
				Subordinate delSub=subordinateService.getByBothUserId(new Long(uid), new Long(id));
				if(delSub==null) break;
					subordinateService.remove(delSub.getSubordinateId());
				delSub=subordinateService.getByBothUserId(new Long(id),new Long(uid));//删除对应的另一条
				if(delSub==null) break;
					subordinateService.remove(delSub.getSubordinateId());
			}
		}		
	}
}
