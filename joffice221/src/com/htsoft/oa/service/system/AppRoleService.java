package com.htsoft.oa.service.system;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/

import java.util.HashMap;
import java.util.Set;

import javax.jws.WebService;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppRole;
@WebService
public interface AppRoleService extends BaseService<AppRole>{
	public AppRole getByRoleName(String roleName);
	
	/**
	 * 获取安全认证的数据源
	 * @return
	 */
	public HashMap<String,Set<String>>getSecurityDataSource();
	
	public AppRole getById(Long roleId);
}
