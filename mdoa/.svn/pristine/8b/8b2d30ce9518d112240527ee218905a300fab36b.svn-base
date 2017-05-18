package com.mdoa.framework.dao;

import java.util.HashMap;
import java.util.List;

import com.mdoa.framework.bo.DepartmentForm;
import com.mdoa.framework.model.Department;
import com.mdoa.framework.model.FrameworkTree;
import com.mdoa.user.model.UserInfo;

public interface DepartmentDao {
	
	/**
	 * 获取uuid
	 */
	String getuuid();
	
	/**
	 * 根据父部门ID获取部门列表
	 * @return 部门列表List
	 */
	List<FrameworkTree> getLowerBySuperId(FrameworkTree frameworkTree);
	
	/**
	 * 根据父部门的URl查询所有的子门
	 * @param url
	 * @return 所有的子部门List
	 */
	List<Department> getLowerBySuperUrl(HashMap<String, Object> paramsMap);
	
	/**
	 * 添加部门信息
	 * @return 返回是否添加成功
	 */
	boolean insertDepartment(Department department);
	
	/**
	 * 根据筛选条件部门名称，创建时间，修改时间查询
	 * @param departmentForm
	 * @return 部门信息列表
	 */
	List<Department> getDepartmentList(DepartmentForm departmentForm);
	
	/**
	 * 根据部门名称或者员工姓名来查询员工
	 * @param departmentForm 可以根据部门查询，也可以根据员工姓名查询员工
	 * @return 员工信息列表
	 */
	List<UserInfo> getUserByDepartment(DepartmentForm departmentForm);
	
	/**
	 * 根据部门的Id来删除部门
	 * @param params 部门的Id
	 * @return 是否删除成功
	 */
	boolean deleteDepartment(HashMap<String, String> params);
	
	
	/**
	 * 修改部门名称
	 * @param params 必备 用户Id， 用户名， 部门Id ， 部门名
	 * @return 是否修改成功
	 */
	boolean updateDepartmentName(HashMap<String, String> params);
	/**
	 * 修改部门名称信息的时候，userInfo表里面关联的部门名称也要修改
	 * @param params 
	 * @return
	 */
	boolean updateUserDepartmentName(HashMap<String, String> params);
	/**
	 * 修改部门的url为新的url
	 */
	boolean updateDepartmentUrl(HashMap<String, String> params);
	
	/**
	 * 修改子部门Url为新的Url
	 */
	boolean updateChildDepartmentUrl(HashMap<String, String> params);
	
	
}
