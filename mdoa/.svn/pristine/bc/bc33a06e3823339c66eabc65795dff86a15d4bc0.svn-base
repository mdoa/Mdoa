package com.mdoa.user.dao;

import java.util.HashMap;

import com.mdoa.user.model.UserInfo;

public interface UserDao {
	
	/**
	 * 根据用户的用户名查询用户信息
	 * @return 用户信息
	 */
	UserInfo selectUserInfoByAccount(String account);
	/**
	 * 修改密码
	 * @param newPassword
	 */
	boolean updatePassword(HashMap<String, String> hashMap);
}
