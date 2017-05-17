package com.htsoft.oa.action.htmobile;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import javax.annotation.Resource;

import org.springframework.security.AuthenticationManager;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

import com.htsoft.core.util.DataEncryptUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.system.AppUserService;

public class MobileLoginAction extends BaseAction {
	private AppUser user;
	private String username;
	private String password;

	// private String rememberMe;//自动登录
	@Resource
	private AppUserService userService;
	@Resource(name = "authenticationManager")
	private AuthenticationManager authenticationManager = null;

	/**
	 * jsp登录
	 * 
	 * @return
	 */
	public String login() {

		AppUser user = userService.findByUserName(username);
		if (user == null) {
			jsonString = "{\"success\":false,\"msg\":\"该用户账号不存在!\"}";
			return SUCCESS;
		}
		if (user.getStatus() != 1) {
			jsonString = "{\"success\":false,\"msg\":\"账号已经被禁用!\"}";
			return SUCCESS;
		}

		// 判断密码及用户名是否一致
		String newPassword = DataEncryptUtil.encryptSha256(password);
		// 密码是否一致
		if (!user.getPassword().equals(newPassword)) {
			jsonString = "{\"success\":false,\"msg\":\"密码不正确\"}";
			return SUCCESS;
		}
		
		try {
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					username, password);
			SecurityContext securityContext = SecurityContextHolder
					.getContext();
			securityContext.setAuthentication(authenticationManager
					.authenticate(authRequest));
			SecurityContextHolder.setContext(securityContext);
			getSession()
					.setAttribute(
							AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY,
							username);

			jsonString = "{\"success\":true,\"username\":\""+user.getFullname()+"\",\"userId\":\""+user.getUserId()+"\"}";
			
		} catch (Exception ex) {
			ex.printStackTrace();
			jsonString = "{\"success\":false,\"msg\":\"" + ex.getCause() + "\"}";
			return SUCCESS;
		}

		return SUCCESS;
	}
	
	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
