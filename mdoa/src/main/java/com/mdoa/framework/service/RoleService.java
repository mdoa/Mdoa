package com.mdoa.framework.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mdoa.framework.bo.RoleForm;
import com.mdoa.framework.dao.RoleDao;
import com.mdoa.framework.model.Power;
import com.mdoa.framework.model.Role;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class RoleService {
	
	@Autowired
	RoleDao roleDao;
	
	/**
	 * 获取角色列表
	 * @param form 筛选条件，包含角色名，角色创建时间，所拥有权限名
	 * @return 角色列表
	 */
	public PageModel<Role> getRolePowerList(RoleForm form){
		PageHelper.startPage(form.getPage(), form.getRows());
		List<Role> roles = roleDao.getRoleList(form);
		PageModel<Role> page = new PageModel<Role>((Page<Role>)roles);
		return page;
	}
	
	/**
	 * 获取角色列表
	 * @param form 筛选条件，包含角色名，角色创建时间，所拥有权限名
	 * @return 角色列表
	 */
	public PageModel<Role> getRoleList(RoleForm form){
		if(!StringUtil.isEmpty(form.getRoleName())){
			form.setRoleName("'" + form.getRoleName() + "%'");
		}
		PageHelper.startPage(form.getPage(), form.getRows());
		List<Role> roles = roleDao.getRoleList(form);
		PageModel<Role> page = new PageModel<Role>((Page<Role>)roles);
		return page;
	}
	
	/**
	 * 添加用户角色
	 * @param role 角色信息
	 * @param powers 权限Id，以_进行分割
	 */
	public void addRole(Role role){
		//获取所需要的ID
		role.setRoleId(roleDao.getuuid());
		if(!roleDao.addRole(role))
			throw new RuntimeException("添加角色失败");
	}
	
	/**
	 * 对用户角色的权限进行更新，这种更新实质上为删除原本的所有权限，之后再添加新的权限
	 * @param form 权限Id列表， 角色Id
	 */
	public void updatePower(RoleForm form){
		//清空用户的权限
		this.cleanRolePower(form);
		//设置参数集合
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", form.getRoleId());
		params.put("createUserId", form.getUserId());
		params.put("createUserName", form.getUserName());
		//重新添加用户的权限
		if(StringUtil.isEmpty(form.getPowers())){
			return ;
		}
		this.addRolePower(params, StringUtil.splitString(form.getPowers()));
	}
	
	/**
	 * 清空一个角色下的所有权限
	 * @param form  角色Id 或
	 */
	public void cleanRolePower(RoleForm form){
		roleDao.cleanRolePower(form);
	}
	
	/**
	 * 添加权限到角色下
	 * @param form 权限Id列表， 角色Id
	 */
	public void addRolePower(HashMap<String, Object> params, String[] powers){
		//遍历 进行角色授权
		params.put("powers", powers);
		if(!roleDao.addRolePower(params)){
			throw new RuntimeException("角色添加权限失败");
		}
	}
	
	/**
	 * 删除角色信息
	 * @param form 包含 RoleIds，以逗号分隔的角色列表
	 */
	public void deleteRole(RoleForm form){
		if(StringUtil.isEmpty(form.getRoleIds())){
			throw new RuntimeException("删除角色信息失败");
		}
		if(!roleDao.deleteRole(form)){
			throw new RuntimeException("删除角色信息失败");
		}
		this.cleanRolePower(form);
	}
	
	/**
	 * 修改角色的角色名，和备注信息
	 * @param role roleName，roleId，remark 
	 */
	public void updateRole(Role role){
		if(!roleDao.updateRole(role)){
			throw new RuntimeException("修改角色信息失败");
		}
	}
	
	/**
	 * 为用户添加角色绑定信息
	 */
	public void addUserRole(UserInfo userInfo){
		String[] roleIds = StringUtil.splitString(userInfo.getRoleIds());
		if(roleIds == null || roleIds.length == 0){
			return ;
		}
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("createUserId", userInfo.getCreateUserId());
		params.put("createUserName", userInfo.getCreateUserName());
		params.put("userId", userInfo.getUserId());
		params.put("userName", userInfo.getUserName());
		params.put("roleIds", roleIds);
		if(!roleDao.addUserRole(params)){
			throw new RuntimeException("添加用户角色权限失败");
		}
	}
	
	/**
	 * 删除所有的用户持有的角色
	 */
	public void cleanUserRole(UserInfo userInfo){
		roleDao.cleanUserRole(userInfo);
	}
	
	/**
	 * 根据用户Id来查询用户的权限信息
	 */
	public List<Role> queryUserRoleByUserId(String userId){
		return roleDao.queryUserRoleByUserId(userId);
	}
	
	/**
	 * 根据角色Id查询角色所持有的权限
	 */
	public List<Power> queryRolePower(String roleId){
		return roleDao.queryRolePower(roleId);
	}
}
