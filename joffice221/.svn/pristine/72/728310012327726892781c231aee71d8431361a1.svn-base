package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.JsonUtil;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.DepUsers;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.service.system.DepUsersService;
import com.htsoft.oa.service.system.DepartmentService;

import flexjson.transformer.DateTransformer;
import flexjson.JSONSerializer;

/**
 * @description 部门人员管理
 * @class DepUsersAction
 * @author 宏天软件
 * @updater YHZ
 * @company www.jee-soft.cn
 * @createtime 2011-1-14AM
 * 
 */
public class DepUsersAction extends BaseAction {
	@Resource
	private DepUsersService depUsersService;
	@Resource
	private DepartmentService departmentService;

	private DepUsers depUsers;

	private Long depUserId;

	public Long getDepUserId() {
		return depUserId;
	}

	public void setDepUserId(Long depUserId) {
		this.depUserId = depUserId;
	}

	public DepUsers getDepUsers() {
		return depUsers;
	}

	public void setDepUsers(DepUsers depUsers) {
		this.depUsers = depUsers;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<DepUsers> list = depUsersService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "appUser.accessionTime" });
		buff.append(serializer.serialize(list));

		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids)
				depUsersService.remove(new Long(id));
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 */
	public String get() {
		DepUsers depUsers = depUsersService.get(depUserId);
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(JsonUtil.getJSONSerializer(new String[] { "accessionTime" })
				.serialize(depUsers));
		sb.append("}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String msg = "{success:true,msg:'数据操作成功！'}"; // 默认成功
		// 1.判断是否已经添加该部门
		boolean isAdd = depUsers != null && depUsers.getDepUserId() == null;
		// 2.判断是否添加主部门
		Long userId = depUsers.getAppUser().getUserId();
		if (depUsers.getIsMain().equals(DepUsers.ISMAIN)
				&& depUsersService.existsDep(depUsers.getDepUserId(), userId)) { // 判断是否为主部门
			msg = "{failure:true,msg:'对不起，该用户已经添加了主部门，请选择添加副部门！'}";
			setJsonString(msg);
			return SUCCESS;
		}
		// 获取sn
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addSorted("sn", "DESC");
		filter.getPagingBean().setPageSize(1);
		filter.getPagingBean().setStart(0);
		List<DepUsers> list = depUsersService.getAll(filter);
		Integer sn = 0; // sn
		if (list != null && list.size() > 0)
			sn = list.get(0).getSn() + 1;
		depUsers.setSn(sn);
		// end 获取sn
		if (isAdd) // 添加之前，判断是否已经添加
			msg = depUsersService.add(depUsers);
		else
			depUsersService.save(depUsers);
		setJsonString(msg);
		return SUCCESS;
	}

	/**
	 * 根据部门查找列表
	 */
	public String select() {
		QueryFilter filter = new QueryFilter(getRequest());
		//PagingBean pb = getInitPagingBean();
		String strDepId = getRequest().getParameter("depId");
		// 表示从上级目录开始进行查找
		String path = "0.";
		if (StringUtils.isNotEmpty(strDepId)) {
			Long depId = Long.parseLong(strDepId);
			Department dep = departmentService.get(depId);
			if (dep != null) {
				path = dep.getPath();
				filter.addFilter("Q_department.path_S_LK", path);
			}
		}
		// List<DepUsers> list = depUsersService.search(path, depUsers, pb);
		// StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		// .append(pb.getTotalItems()).append(",result:");
		// JSONSerializer serializer = new JSONSerializer();
		// serializer.transform(new DateTransformer("yyyy-MM-dd"),
		// new String[] { "appUser.accessionTime" });
		// buff.append(serializer.exclude(new String[] {
		// "appUser.password","department.class","class" })
		// .serialize(list));
		// buff.append("}");

		// jsonString = buff.toString();
		// return SUCCESS;

		List<DepUsers> list = depUsersService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd"),
				new String[] { "appUser.accessionTime" });
		buff.append(serializer.serialize(list));

		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	public String sn() {
		String depParams = getRequest().getParameter("depParams");
		if (StringUtils.isNotEmpty(depParams)) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			DepUsers[] dus = gson.fromJson(depParams, DepUsers[].class);
			if (dus != null && dus.length > 0) {
				for (DepUsers du : dus) {

					if (du.getDepUserId() != null) {
						DepUsers orgDepUsers = depUsersService.get(du
								.getDepUserId());
						try {

							BeanUtil.copyNotNullProperties(orgDepUsers, du);
							depUsersService.save(orgDepUsers);
						} catch (Exception ex) {
							logger.error(ex.getMessage());
						}
					} else {
						depUsersService.save(du);
					}
				}
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

}
