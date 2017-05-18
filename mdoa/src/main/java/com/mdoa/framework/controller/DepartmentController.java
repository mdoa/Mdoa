package com.mdoa.framework.controller;


import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.constant.FrameWorkConstant;
import com.mdoa.framework.bo.DepartmentForm;
import com.mdoa.framework.model.Department;
import com.mdoa.framework.model.FrameworkTree;
import com.mdoa.framework.service.DepartmentService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

@RestController
@RequestMapping("department")
public class DepartmentController extends BaseController{
	
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * 获取公司的部门结构信息，如果单例化的部门结构信息是空的，则调用service层中的方法，为单例化的对象注入结构
	 * @return 部门信息json
	 */
	@RequestMapping("getFramework.do")
	public synchronized String getFramework(){
		try{
			Gson gson = new Gson();
			if(FrameWorkConstant.frameworkDepartments == null){
				FrameworkTree superDepartment = new FrameworkTree();
				superDepartment.setText("杭州航民达美染整有限公司");
				superDepartment.setId("0000");
				superDepartment.setThisId("0000");
				FrameWorkConstant.frameworkDepartments = new LinkedList<FrameworkTree>();
				FrameWorkConstant.frameworkDepartments.add(superDepartment); 
				superDepartment.setChildren(departmentService.injectFrameworkDepartment(superDepartment));
			}
			String json = gson.toJson(FrameWorkConstant.frameworkDepartments);
			return json;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据部门名名称，父部门名称，部门创建时间查询部门,自带分页
	 * @param departmentForm
	 * @return 部门列表
	 */
	@RequestMapping("getList.do")
	public String getDepartmentList(DepartmentForm departmentForm){
		try{
			PageModel<Department> list = departmentService.getDepartmentList(departmentForm);
			Gson gson = new Gson();
			return gson.toJson(list);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据父级URl查询所有该父部门的子部门
	 * @param departmentForm 根据父级URL查询部门结构的各项参数
	 * @return 子部门列表
	 */
	@RequestMapping("getLower.do")
	public String getLower(DepartmentForm departmentForm){
		try{
			PageInfo<Department> departments = departmentService.getLower(departmentForm);
			Gson gson = new Gson();
			return gson.toJson(departments);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 添加部门信息
	 * @param department 创建部门所需要的相关信息
	 * @return 是否创建成功标志位
	 */
	@RequestMapping("insertDepartment.do")
	public String insertDepartment(Department department, HttpServletRequest reuqest){
		try{
			UserInfo useroInfo = getUser(reuqest);
			department.setCreateUserId(useroInfo.getUserId());
			department.setCreateUserName(useroInfo.getUserName());
			departmentService.insertDepartment(department);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据部门和员工的姓名进行查询员工的信息列表
	 * @param departmentForm 
	 * @return 员工的信息列表
	 */
	@RequestMapping("getUserByDept.do")
	public String getUserByDepartment(DepartmentForm departmentForm){
		try{
			PageModel<UserInfo> users = departmentService.getUserByDepartment(departmentForm);
			Gson gson = new Gson();
			return gson.toJson(users);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据部门url来删除部门
	 * @param departmentUrl 部门Url
	 * @return 删除是否成功
	 */
	@RequestMapping("delete.do")
	public String deleteDepartment(String departmentUrl, HttpServletRequest reuqest){
		try{
			UserInfo user = getUser(reuqest);
			departmentService.deleteDepartment(departmentUrl, user.getUserId(), user.getUserName());
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据部门url来修改部门名称
	 * @param departmentUrl 部门url
	 * @return 删除是否成功
	 */
	@RequestMapping("updateName.do")
	public String updateDepartmentName(String departmentUrl, String departmentName, HttpServletRequest reuqest){
		try{
			UserInfo user = getUser(reuqest);
			departmentService.updateDepartmentName(departmentUrl, departmentName, user.getUserId(), user.getUserName());
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 更改部门的父部门，在前端通过拖动的方式修改
	 */
	@RequestMapping("moveDepartment.do")
	public String moveDepartment(@RequestParam String startNodeUrl, @RequestParam String endNodeUrl
			, HttpServletRequest request){
		try {
			departmentService.moveDepartment(startNodeUrl, endNodeUrl,request);
			return Constant.SUCCESS_CODE;
		} catch (RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
}
