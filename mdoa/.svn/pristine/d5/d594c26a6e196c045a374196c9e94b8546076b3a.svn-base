package com.mdoa.user.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdoa.base.service.BaseService;
import com.mdoa.constant.Constant;
import com.mdoa.framework.model.Power;
import com.mdoa.framework.service.PowerService;
import com.mdoa.user.dao.UserDao;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.Md5Util;

@Service
@Transactional
public class UserService extends BaseService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PowerService powerService;
	
	public void login(UserInfo userInfo, HttpServletRequest request){
		if (userInfo.getUserAccount().equals(Constant.ADMIN_ACCOUNT) 
				&& userInfo.getPassword().equals(Constant.ADMIN_PASSWORD)) {
			Set<String> powers = new HashSet<String>();
			powers.add("admin");
			userInfo.setUserId("admin");
			userInfo.setUserName("超级管理员");
			userInfo.setPermissions(powers);
			request.getSession().setAttribute("userInfo", userInfo);
			return ;
		}
		UserInfo user = userDao.selectUserInfoByAccount(userInfo.getUserAccount());
		if(!Md5Util.getMd5Str(userInfo.getPassword()).equals(user.getPassword()))
			throw new RuntimeException("用户登录失败");
		
		Set<String> powers = new HashSet<String>();
		List<Power> powerList = powerService.getPowerByUser(user.getUserId());
		for(Power power:powerList){
			powers.add(power.getPower());
		}
		user.setPermissions(powers);
		request.getSession().setAttribute("userInfo", user);
	}

	/**
	 * 修改密码
	 * @param newPassword
	 */
	public void updatePassword(String oldPassword, String newPassword, String nowPassword ,String userId,
			HttpServletRequest request) {
		UserInfo userInfo = getUser(request);
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("newPassword", Md5Util.getMd5Str(newPassword));
		hashMap.put("userId", userId);
		System.out.println("nowPassword="+nowPassword+",oldPasswqord="+Md5Util.getMd5Str(oldPassword));
		if (!nowPassword.equals(Md5Util.getMd5Str(oldPassword))){
			throw new RuntimeException("密码不正确！");
		}else{
			userInfo.setPassword( Md5Util.getMd5Str(newPassword));
		}
		if (!userDao.updatePassword(hashMap)) {
			throw new RuntimeException("新密码修改失败");
		}
		
		
	}
	
	
}
