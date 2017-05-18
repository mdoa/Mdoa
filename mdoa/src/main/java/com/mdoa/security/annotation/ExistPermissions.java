package com.mdoa.security.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Administrator
 * 该注解用于进行对权限的控制，当用户的权限中存在该权限或改权限的子权限时，则予以放行
 * 相关代码需查看SecurityFilter
 */
@Target(value={ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistPermissions {
	/**
	 * 可以访问所进行注解的权限的数组对象，当具有该数组对象中任意权限的子权限时，均可以进行访问
	 */
	String[] permissions();
}
