package com.htsoft.oa.action.communicate;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.SmsMobile;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.communicate.SmsMobileService;
import com.htsoft.oa.service.system.AppUserService;
/**
 * 
 * @author 
 *
 */
public class SmsMobileAction extends BaseAction{
	@Resource
	private SmsMobileService smsMobileService;
	@Resource
	private AppUserService appUserService;
	private SmsMobile smsMobile;
	
	private Long smsId;

	public Long getSmsId() {
		return smsId;
	}

	public void setSmsId(Long smsId) {
		this.smsId = smsId;
	}

	public SmsMobile getSmsMobile() {
		return smsMobile;
	}

	public void setSmsMobile(SmsMobile smsMobile) {
		this.smsMobile = smsMobile;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<SmsMobile> list= smsMobileService.getAll(filter);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list,filter.getPagingBean().getTotalItems());
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
				smsMobileService.remove(new Long(id));
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
		SmsMobile smsMobile=smsMobileService.get(smsId);
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd");
		jsonString = mapper.toDataJson(smsMobile);
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		StringBuffer msg = new StringBuffer("");
		String recipientIds = getRequest().getParameter("recipientIds");
		if(StringUtils.isNotEmpty(recipientIds)){
			//发送站内手机短信
			String[] ids = recipientIds.split(",");
			for(String userId : ids){
				AppUser appUser = appUserService.get(new Long(userId));
				if(appUser.getMobile() == null){
					msg.append("用户<font color=\"red\">").append(appUser.getUsername()).append("</font>");
				}else{
					SmsMobile smsInner = new SmsMobile();
					smsInner.setPhoneNumber(appUser.getMobile());
					smsInner.setRecipients(appUser.getFullname());
					smsInner.setSendTime(new Date());
					smsInner.setSmsContent(smsMobile.getSmsContent());
					smsInner.setUserId(ContextUtil.getCurrentUserId());
					smsInner.setUserName(ContextUtil.getCurrentUser().getFullname());
					smsInner.setStatus(SmsMobile.STATUS_NOT_SENDED);
					smsMobileService.save(smsInner);
				}
			}
			if(msg.length()>0){
				msg.append("未设置手机号码.");
			}
		}else{
			//发送站外手机短信
			String mobileNums = smsMobile.getPhoneNumber();
			if(StringUtils.isNotEmpty(mobileNums)){
				String[] numbers = mobileNums.split(",");
				for(String num : numbers){
					SmsMobile smsOutside = new SmsMobile();
					smsOutside.setPhoneNumber(num);
					smsOutside.setRecipients(num);
					smsOutside.setSendTime(new Date());
					smsOutside.setSmsContent(smsMobile.getSmsContent());
					smsOutside.setUserId(ContextUtil.getCurrentUserId());
					smsOutside.setUserName(ContextUtil.getCurrentUser().getFullname());
					smsOutside.setStatus(SmsMobile.STATUS_NOT_SENDED);
					smsMobileService.save(smsOutside);
				}
			}
			
		}
		setJsonString("{success:true,msg:'"+msg.toString()+"'}");
		return SUCCESS;
	}
	/**
	 * 测试设备连接
	 * @return
	 */
	public String send(){
		smsMobile.setSendTime(new Date());
		smsMobileService.save(smsMobile);
		smsMobileService.sendOneSms(smsMobile);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
