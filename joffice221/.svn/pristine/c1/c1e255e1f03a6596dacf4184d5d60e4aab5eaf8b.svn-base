/**
 * 此方法作用为取得当前用户
 */
/*
 *  杭州梦德软件有限公司 OA办公自动管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
package com.htsoft.core.util;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.htsoft.oa.model.system.AppUser;


/**
 * 取得当前上下文帮助类
 * 
 *
 */
public class ContextUtil {
	private static final Log logger=LogFactory.getLog(ContextUtil.class);
	
	/**
	 * 从上下文取得当前用户
	 * @return
	 */
	public static AppUser getCurrentUser(){
		SecurityContext securityContext = SecurityContextHolder.getContext();
        if (securityContext != null) {
            Authentication auth = securityContext.getAuthentication();
            if (auth != null) {
                Object principal = auth.getPrincipal();
                if (principal instanceof AppUser) {
                    return (AppUser) principal;
                }
            } else {
                logger.warn("WARN: securityContext cannot be lookuped using SecurityContextHolder.");
            }
        }
        return null;
	}
	
	public static Long getCurrentUserId(){
		AppUser curUser=getCurrentUser();
		if(curUser!=null) return curUser.getUserId();
		return null;
	}
}
