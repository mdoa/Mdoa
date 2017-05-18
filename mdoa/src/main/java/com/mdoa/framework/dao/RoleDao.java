package com.mdoa.framework.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.mdoa.framework.bo.RoleForm;
import com.mdoa.framework.model.Power;
import com.mdoa.framework.model.Role;
import com.mdoa.user.model.UserInfo;

public interface RoleDao {
	
	/**
	 * 获取主键uuid()
	 */
	String getuuid();
	
	/**
	 * 获取角色权限列表
	 * @param form 筛选条件，包含角色名，角色创建时间，所拥有权限名
	 * @return 角色列表
	 */
	List<Role> getRolePowerList(RoleForm form);
	
	/**
	 * 获取角色列表
	 * @param form 筛选条件，包含角色名，角色创建时间，所拥有权限名
	 * @return 角色列表
	 */
	List<Role> getRoleList(RoleForm form);
	
	/**
	 * 添加用户角色
	 * @param role 角色信息
	 * @param powers 权限Id，以_进行分割
	 */
	boolean addRole(Role role);
	
	/**
	 * 添加角色关联权限
	 * @param params 角色Id，权限Id
	 */
	boolean addRolePower(HashMap<String, Object> params);
	
	/**
	 * 清空一个角色下的所有权限
	 * @param form  角色Id
	 */
	boolean cleanRolePower(RoleForm form);
	
	/**
	 * 删除角色
	 * @param form roleId 角色Id
	 * @return 是否成功删除
	 */
	boolean deleteRole(RoleForm form);
	
	/**
	 * 修改角色信息
	 * @param 角色Id， 备注， 角色名
	 * @return 是否删除成功
	 */
	boolean updateRole(Role role);
	
	/**
	 * 添加用户角色信息绑定
	 * @param userInfo
	 * @return
	 */
	boolean addUserRole(HashMap<String, Object> params);
	
	/**
	 * 清空一个用户下的所有角色
	 */
	boolean cleanUserRole(UserInfo userInfo);
	
	/**
	 * 根据用户Id 查询一个用户的所有的持有的角色
	 */
	List<Role> queryUserRoleByUserId(String userId);
	
	/**
	 * 根据角色Id查询角色所持有的权限
	 */
	List<Power> queryRolePower(String roleId);
}
