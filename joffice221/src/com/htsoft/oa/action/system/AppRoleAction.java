package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.AppFunction;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.service.system.AppFunctionService;
import com.htsoft.oa.service.system.AppRoleService;

/**
 * 角色设置
 * 
 * @author csx
 * 
 */
public class AppRoleAction extends BaseAction {
	@Resource
	private AppFunctionService appFunctionService;

	private static String IS_COPY = "1";

	@Resource
	private AppRoleService appRoleService;

	private AppRole appRole;

	private Long roleId;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public AppRole getAppRole() {
		return appRole;
	}

	public void setAppRole(AppRole appRole) {
		this.appRole = appRole;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<AppRole> list = appRoleService.getAll(filter);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;
	}

	/**
	 * 列出角色树
	 * 
	 */
	public String tree() {
		List<AppRole> list = appRoleService.getAll();
		jsonString = JsonTree.generate(getResult(list), null, false);
		logger.info("tree json:" + jsonString);
		return SUCCESS;
	}

	/**
	 * 产生树的结果
	 * 
	 * @param list
	 * @param method
	 * @return
	 */
	private List<Object> getResult(List<AppRole> list) {
		List<Object> dataList = new ArrayList<Object>();
		for (AppRole role : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", role.getRoleId().toString());
			dataRecord.put("text", role.getRoleName());
			dataRecord.put("parentId", null);
			dataList.add(dataRecord);
		}

		return dataList;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				AppRole appRole = appRoleService.get(new Long(id));
				appRole.getAppUsers().remove(appRole);
				appRole.getFunctions().remove(appRole);
				appRoleService.remove(appRole);
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 角色授权
	 * 
	 * @return
	 */
	public String grant() {
		AppRole appRole = appRoleService.get(roleId);
		String rights = getRequest().getParameter("rights");

		if (rights == null) {
			rights = "";
		}

		if (!rights.equals(appRole.getRights())) {
			appRole.setRights(rights);

			appRole.getFunctions().clear();

			String[] funs = rights.split("[,]");

			for (int i = 0; i < funs.length; i++) {
				if (funs[i].startsWith("_")) {
					AppFunction af = appFunctionService.getByKey(funs[i]);
					if (af != null) {
						appRole.getFunctions().add(af);
					}
				}
			}

			// 检查Right是否发生了变化
			appRoleService.save(appRole);
			// 重新加载权限 优化为只是更新该角色对应的权限
			AppUtil.reloadSecurityDataSource();
		}

		return SUCCESS;
	}

	/**
	 * 加载授权的XML
	 * 
	 * @return
	 */
	public String grantXml() {
		Document grantMenuDoc = AppUtil.getGrantMenuDocument();
		setJsonString(grantMenuDoc.asXML());

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		AppRole appRole = appRoleService.get(roleId);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(appRole);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作 当是isCopy='1'时，则是为角色的复制
	 */
	public String save() {
		String isCopy = getRequest().getParameter("isCopy");
		if (StringUtils.isNotEmpty(isCopy) && IS_COPY.equals(isCopy)) {
			AppRole role = new AppRole();
			role.setIsDefaultIn((short) 0);
			role.setRoleDesc(appRole.getRoleDesc());
			role.setStatus(appRole.getStatus());
			role.setRoleName(appRole.getRoleName());
			appRole = appRoleService.get(appRole.getRoleId());
			Set<AppFunction> set = new HashSet<AppFunction>(
					appRole.getFunctions());
			role.setFunctions(set);
			role.setRights(appRole.getRights());
			appRoleService.save(role);
		} else {
			if (appRole.getRoleId() == null) {
				appRole.setIsDefaultIn((short) 0);
				appRoleService.save(appRole);
			} else {
				AppRole orgRole = appRoleService.get(new Long(appRole
						.getRoleId()));
				try {
					BeanUtil.copyNotNullProperties(orgRole, appRole);
					appRoleService.save(orgRole);
				} catch (Exception ex) {
				}
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 检查用户名是否存在
	 * 
	 * @return
	 */
	public String check() {
		String roleName = getRequest().getParameter("roleName");
		AppRole appRole = appRoleService.getByRoleName(roleName);
		if (appRole == null) {
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return SUCCESS;
	}

}
