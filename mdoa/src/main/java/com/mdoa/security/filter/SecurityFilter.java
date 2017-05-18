package com.mdoa.security.filter;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.mdoa.security.annotation.ExistPermissions;
import com.mdoa.security.annotation.HasPermissions;
import com.mdoa.user.model.UserInfo;

public class SecurityFilter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView arg3)
			throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//获取用户权限信息列表
		UserInfo user = (UserInfo) request.getSession().getAttribute("userInfo");
		if(user == null){
			return false ;
		}
		Set<String> permissions = user.getPermissions();
		//获取相应权限注解信息
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		//验证用户是否持有其中一个权限
		HasPermissions hasPermissions = handlerMethod.getMethodAnnotation(HasPermissions.class);
		boolean hasPer = false;
		if(hasPermissions != null){
			for(String permission : hasPermissions.permissions()){
				if(this.hasPermission(permissions, permission)){
					hasPer = true;
					break;
				}
			}
		}else{
			hasPer = true;
		}
		
		//验证用户是否持有其中一个权限，或者其中一个权限的子权限
		ExistPermissions existPermissions = handlerMethod.getMethodAnnotation(ExistPermissions.class);
		boolean existPer = false;
		if(existPermissions != null){
			for(String permission : existPermissions.permissions()){
				if(this.hasPermission(permissions, permission)){
					existPer = true;
					break;
				}
			}
		}else{
			existPer = true;
		}
		if(hasPer && existPer){
			return true;
		}else{
			System.out.println("用户 "+user.getUserName()+" 越权访问："+request.getRequestURL().toString());
			return false;
		}
	}
	
	/**
	 * 验证用户是否持有一个权限，或者一个权限的子权限
	 * @param permissions
	 * @param permission
	 * @return
	 */
	public boolean hasPermission(Set<String> permissions, String permission){
		for(String userPermission : permissions){
			if(permission.substring(0, userPermission.length()).equals(userPermission)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证用户是否持有一个权限
	 * @param permissions
	 * @param permission
	 * @return
	 */
	public boolean existPermission(Set<String> permissions, String permission){
		for(String userPermission : permissions){
			if(userPermission.substring(0, permission.length()).equals(permission)){
				return true;
			}else if(permission.substring(0, userPermission.length()).equals(userPermission)){
				return true;
			}
		}
		return false;
	}
}
