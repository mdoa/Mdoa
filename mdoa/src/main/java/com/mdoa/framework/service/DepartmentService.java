package com.mdoa.framework.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mdoa.base.service.BaseService;
import com.mdoa.constant.FrameWorkConstant;
import com.mdoa.framework.bo.DepartmentForm;
import com.mdoa.framework.dao.DepartmentDao;
import com.mdoa.framework.model.Department;
import com.mdoa.framework.model.FrameworkTree;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class DepartmentService extends BaseService{
	
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 为单例化的部门结构注入结构
	 * 部门结构从数据库中进行获取
	 */
	public List<FrameworkTree> injectFrameworkDepartment(FrameworkTree superDepartment){
		//根据父级部门Id查询该部门下的下一级子部门
		List<FrameworkTree> departments = departmentDao.getLowerBySuperId(superDepartment);
		//递归调用，查询每一层的子部门的下一层级子部门，并设置进部门中
		for(FrameworkTree department : departments){
			department.setChildren(injectFrameworkDepartment(department));
		}
		//返回所有的部门信息
		return departments;
	}
	
	
	/**
	 * 根据父部门Url进行查询，所有的子部门
	 * @param departmentForm 包含了一系列的参数信息
	 * @return 子部门的集合，其中包含了分页信息
	 */
	public PageInfo<Department> getLower(DepartmentForm departmentForm){
		//设置分页所需的当前页和总页数
		PageHelper.startPage(departmentForm.getPage(), departmentForm.getRows());
		//创建url并放入HashMap
		String url = "'"+departmentForm.getSuperDepartmentUrl()+"%'";
		HashMap<String, Object> paramsMap = new HashMap();
		paramsMap.put("url", url);
		//调用持久层查询
		List<Department> list = departmentDao.getLowerBySuperUrl(paramsMap);
		//设置分页详细信息
		PageInfo<Department> pageInfo=new PageInfo<>(list);
		return pageInfo;
	}
	
	/**
	 * 添加部门信息到数据库中
	 * 由于需要根据Id设定url所以需要先获取uuid处理后再存入
	 * @param department 部门信息，包含父路径，部门名称等
	 */
	public void insertDepartment(Department department){
		String uuid = departmentDao.getuuid();
		//设置部门的唯一主键以及部门路径
		department.setDepartmentId(uuid);
		department.setSuperDepartmentId(StringUtil.getIdFromUrl(department.getSuperDepartmentUrl()));
		department.setUrl(department.getSuperDepartmentUrl() + "_" +uuid);
		if(!departmentDao.insertDepartment(department)){
			throw new RuntimeException("添加部门信息失败");
		}
		//获取需要添加到的父节点
		FrameworkTree parent = this.getTargetByUrl(department.getSuperDepartmentUrl(), FrameWorkConstant.frameworkDepartments);
		//创建新的部门对象
		FrameworkTree newDept = new FrameworkTree();
		newDept.setText(department.getDepartmentName());
		newDept.setId(department.getUrl());
		newDept.setChildren(new LinkedList<FrameworkTree>());
		//添加子部门到父部门中
		parent.getChildren().add(newDept);
	}
	
	
	/**
	 * 根据部门的 创建时间， 部门名称， 父级部门名称进行查询的方法
	 * @param departmentForm 包含有用户进行筛选的信息
	 * @return 部门列表以及分页信息
	 */
	public PageModel<Department> getDepartmentList(DepartmentForm departmentForm){
		//对部门名称和父部门名称进行处理
		if(!StringUtil.isEmpty(departmentForm.getDepartmentName())){
			String departmentName = "'"+departmentForm.getDepartmentName()+"%'";
			departmentForm.setDepartmentName(departmentName);
		}
		//对父部门名称进行处理
		if(!StringUtil.isEmpty(departmentForm.getSuperDepartmentName())){
			String superDepartmentName = "'"+departmentForm.getSuperDepartmentName()+"%'";
			departmentForm.setSuperDepartmentName(superDepartmentName);
		}
		//设置分页所需的当前页和总页数
		PageHelper.startPage(departmentForm.getPage(), departmentForm.getRows());
		//调用持久层查询
		List<Department> list = departmentDao.getDepartmentList(departmentForm);
		//设置分页详细信息
		PageModel<Department> pageInfo=new PageModel<Department>((Page<Department>)list);
		return pageInfo;
	}
	
	/**
	 * 根据部门和人员姓名查询员工，具有分页
	 * @param departmentForm 部门，员工姓名，分页信息
	 * @return 员工列表
	 */
	public PageModel<UserInfo> getUserByDepartment(DepartmentForm departmentForm){
		if(!StringUtil.isEmpty(departmentForm.getUserName())){
			departmentForm.setUserName("'"+departmentForm.getUserName()+"%'");
		}
		PageHelper.startPage(departmentForm.getPage(), departmentForm.getRows());
		//调用持久层查询
		List<UserInfo> list = departmentDao.getUserByDepartment(departmentForm);
		//设置分页详细信息
		PageModel<UserInfo> pageInfo=new PageModel<UserInfo>((Page<UserInfo>)list);
		return pageInfo;
	}
	
	/**
	 * 根据部门的Url来对部门进行删除
	 * @param departmentId 部门Id
	 * @param userId 修改人Id
	 * @param userName 修改人名称
	 */
	public void deleteDepartment(String departmentUrl, String userId, String userName){
		HashMap<String, String> params  = new HashMap<String, String>();
		params.put("departmentUrl", StringUtil.toLikeRight(departmentUrl));
		params.put("userId", userId);
		params.put("userName", userName);
		if(!departmentDao.deleteDepartment(params)){
			throw new RuntimeException("删除部门失败");
		}
		//获取父级节点Url
		String parentUrl = departmentUrl.substring(0, departmentUrl.lastIndexOf("_"));
		//获取父级节点
		FrameworkTree parent = this.getTargetByUrl(parentUrl, FrameWorkConstant.frameworkDepartments);
		//移除父节点中的子节点
		List<FrameworkTree> childs = parent.getChildren();
		for(int i = 0 ; i < childs.size() ; i++){
			if(childs.get(i).getId().equals(departmentUrl)){
				childs.remove(i);
				break ;
			}
		}
	}
	
	/**
	 * 根据部门ID修改部门的名称
	 * @param departmentUrl 部门Id
	 * @param departmentName 新的部门名称
	 * @param userId 用户Id
	 * @param userName 用户名称
	 */
	public void updateDepartmentName(String departmentUrl, String departmentName, String userId, String userName){
		HashMap<String, String> params  = new HashMap<String, String>();
		params.put("departmentId", StringUtil.getIdFromUrl(departmentUrl));
		params.put("departmentUrl", departmentUrl);
		params.put("userId", userId);
		params.put("userName", userName);
		params.put("departmentName", departmentName);
		if(!departmentDao.updateDepartmentName(params)){
			throw new RuntimeException("修改部门名称失败");
		}
		if(!departmentDao.updateUserDepartmentName(params)){
			throw new RuntimeException("修改员工部门名称失败");
		}
		FrameworkTree target = this.getTargetByUrl(departmentUrl, FrameWorkConstant.frameworkDepartments);
		target.setText(departmentName);
	}
	
	/**
	 * 更改部门的父部门，在前端通过拖动的方式修改
	 */
	public void moveDepartment(String startNodeUrl, String endNodeUrl,HttpServletRequest reuqest){
		String newUrl = endNodeUrl + "_" +StringUtil.getIdFromUrl(startNodeUrl);
		String superDeptId = StringUtil.getIdFromUrl(endNodeUrl);
		HashMap<String ,String> params = new HashMap<String, String>();
		params.put("newUrl",newUrl);
		params.put("superDeptId",superDeptId);
		params.put("startNodeUrl",startNodeUrl);
		
		UserInfo userInfo = getUser(reuqest);
		params.put("updateUserId", userInfo.getUserId());
		params.put("updateUserName", userInfo.getUserName());
		if(!departmentDao.updateDepartmentUrl(params)){
			throw new RuntimeException("更改部门Url失败");
		}
		params.put("startNodeUrl", "'" + startNodeUrl + "%'");
		departmentDao.updateChildDepartmentUrl(params);
		
		//获取所移动的节点的原始父节点
		FrameworkTree oldParent = this.getTargetByUrl(
				startNodeUrl.substring(0, startNodeUrl.lastIndexOf("_")), FrameWorkConstant.frameworkDepartments);
		//寻找原始父节点下的所移动的节点
		for(int i = 0; i < oldParent.getChildren().size() ; i++){
			FrameworkTree target = oldParent.getChildren().get(i);
			if(target.getId().equals(startNodeUrl)){
				//寻找新的位置的父节点
				FrameworkTree newParent = this.getTargetByUrl(endNodeUrl, FrameWorkConstant.frameworkDepartments);
				//添加到新的父节点下
				newParent.getChildren().add(target);
				//替换Url为新的Url
				target.setId(newUrl);
				//获取所有移动的节点的子节点
				List<FrameworkTree> childs = this.getAllChildren(target);
				//获取新的Url
				for(FrameworkTree child : childs){
					//替换所有的旧父节点Url为新的父节点url
					child.getId().replace(startNodeUrl, newUrl);
				}
				//从旧的父节点下移除目标子节点
				oldParent.getChildren().remove(target);
				break ;
			}
		}
	}

	/**
	 * 通过Url获取树节点
	 * @param url
	 * @return
	 */
	public FrameworkTree getTargetByUrl(String url, List<FrameworkTree> targets){
		FrameworkTree child = null;
		for(FrameworkTree target:targets){
			if (StringUtil.isInclude(url, target.getId())) {
				
				if(url.equals(target.getId())){
					return target;
				}else{
					child =  getTargetByUrl(url, target.getChildren());
				}
				break ;
			}
		}
		return child;
	}
	
	/**
	 * 通过节点的目标，获取该节点的所有子节点
	 */
	public List<FrameworkTree> getAllChildren(FrameworkTree target){
		List<FrameworkTree> childs = target.getChildren();
		for(FrameworkTree child: childs){
			childs.addAll(getAllChildren(child));
		}
		return childs;
	}
	
}
