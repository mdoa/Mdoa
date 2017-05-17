package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.Constants;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.log.Action;
import com.htsoft.core.model.OnlineUser;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DataEncryptUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.model.system.IndexDisplay;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.PanelItem;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.model.system.UserOrg;
import com.htsoft.oa.model.system.UserPosition;
import com.htsoft.oa.service.system.AppRoleService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.DepartmentService;
import com.htsoft.oa.service.system.IndexDisplayService;
import com.htsoft.oa.service.system.OrganizationService;
import com.htsoft.oa.service.system.PositionService;
import com.htsoft.oa.service.system.SubordinateService;
import com.htsoft.oa.service.system.SysConfigService;
import com.htsoft.oa.service.system.UserOrgService;
import com.htsoft.oa.service.system.UserPositionService;

/**
 * 
 * @author csx
 * 
 */
public class AppUserAction extends BaseAction {
	@Resource
	private UserPositionService userPositionService;
	@Resource
	private PositionService positionService;
	@Resource
	private OrganizationService organizationService;
	@Resource
	UserOrgService userOrgService;

	private static Long SUPPER_MANAGER_ID = -1l;// 超级管理员角色ID
	@Resource
	private AppUserService appUserService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private AppRoleService appRoleService;

	@Resource
	private IndexDisplayService indexDisplayService;

	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private SubordinateService subordinateService;
	
	private AppUser appUser;

	private Long userId;

	private Long depId;

	private Long roleId;

	public Long getDepId() {
		return depId;
	}

	public void setDepId(Long depId) {
		this.depId = depId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	/**
	 * 显示当前用户,并且加载该用户的所有权限
	 * 
	 * @return
	 */
	public String getCurrent() {
		AppUser currentUser = ContextUtil.getCurrentUser();
		Department curDep = currentUser.getDepartment();
		if (curDep == null) {// 若所属部门为空，则设置一个缺省的部门 TODO
			curDep = new Department();
			curDep.setDepId(0l);
			curDep.setDepName(AppUtil.getCompanyName());
		}
		// 去掉公共权限
		// Iterator<String> publicIds = AppUtil.getPublicMenuIds().iterator();
		// StringBuffer publicIdSb = new StringBuffer();
		//
		// while (publicIds.hasNext()) {
		// publicIdSb.append(",").append(publicIds.next());
		// }
		List<IndexDisplay> list = indexDisplayService.findByUser(currentUser
				.getUserId());
		List<PanelItem> items = new ArrayList<PanelItem>();
		for (IndexDisplay id : list) {
			PanelItem pi = new PanelItem();
			pi.setPanelId(id.getPortalId());
			pi.setColumn(id.getColNum());
			pi.setRow(id.getRowNum());
			items.add(pi);
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{success:true,user:{userId:'")
				.append(currentUser.getUserId()).append("',fullname:'")
				.append(currentUser.getFullname()).append("',username:'")
				.append(currentUser.getUsername()).append("',depId:'")
				.append(curDep.getDepId()).append("',depName:'")
				.append(curDep.getDepName()).append("',rights:'");
		sb.append(currentUser.getRights().toString().replace("[", "")
				.replace("]", ""));
		// 去掉公共权限
		// if (!"".equals(currentUser.getRights()) && publicIdSb.length() > 0) {
		// sb.append(publicIdSb.toString());
		// }

		Gson gson = new Gson();
		sb.append("',topModules:");
		sb.append(gson.toJson(currentUser.getTopModules().values()));
		sb.append(",items:").append(gson.toJson(items).toString());
		sb.append("},sysConfigs:{");
		// 系统配置也在此时加载
		List<SysConfig> sysConfigs = sysConfigService.getAll();
		for (SysConfig sysConfig : sysConfigs) {
			sb.append("'").append(sysConfig.getConfigKey()).append("':'")
					.append(sysConfig.getDataValue()).append("',");
		}
		if (sysConfigs.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("}}");

		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 显示用户列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_delFlag_SN_EQ", Constants.FLAG_UNDELETED.toString());
		List<AppUser> list = appUserService.getAll(filter);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 根据部门查找列表
	 */
	public String select() {
		// 是否根据角色查询翻页
		String roleId = getRequest().getParameter("roleId");
		if (StringUtils.isNotEmpty(roleId)) {
			return find();
		}
		// 是否根据查询条件再翻页查询
		String fullname = getRequest().getParameter("Q_fullname_S_LK");
		if (StringUtils.isNotEmpty(fullname)) {
			return list();
		}
		PagingBean pb = getInitPagingBean();
		String strDepId = getRequest().getParameter("depId");
		// 表示从上级目录开始进行查找
		String path = "0.";
		appUser = ContextUtil.getCurrentUser();
		if (StringUtils.isNotEmpty(strDepId)) {
			Long depId = Long.parseLong(strDepId);
			Department dep = departmentService.get(depId);
			if (dep != null) {
				path = dep.getPath();
			}
		} else {
			Department dep = appUser.getDepartment();
			if (dep != null) {
				path = dep.getPath();
			}
		}

		List<AppUser> list = appUserService.findByDepartment(path, pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");

		Type type = new TypeToken<List<AppUser>>() {
		}.getType();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();
		return SUCCESS;
	}

	/**
	 * 在线用户
	 * 
	 * @return
	 */
	public String online() {
		Map<String, OnlineUser> onlineUsers = new HashMap<String, OnlineUser>();
		Map<String, OnlineUser> onlineUsersByDep = new HashMap<String, OnlineUser>();
		Map<String, OnlineUser> onlineUsersByRole = new HashMap<String, OnlineUser>();

		onlineUsers = AppUtil.getOnlineUsers();// 获得所有在线用户

		// 按部门选择在线用户
		if (depId != null) {
			for (String sessionId : onlineUsers.keySet()) {
				OnlineUser onlineUser = new OnlineUser();
				onlineUser = onlineUsers.get(sessionId);
				// if(onlineUser.getDepId().equals(depId)){
				String path = "";
				if (!onlineUser.getUserId().equals(AppUser.SUPER_USER)) {
					path = onlineUser.getDepPath();
				}
				if (!depId.equals(new Long(0))) {
					if (java.util.regex.Pattern.compile("." + depId + ".")
							.matcher(path).find()) {
						onlineUsersByDep.put(sessionId, onlineUser);
					}
				} else {
					onlineUsersByDep.put(sessionId, onlineUser);
				}
			}
		}

		// 按角色选择在线用户
		if (roleId != null) {
			for (String sessionId : onlineUsers.keySet()) {
				OnlineUser onlineUser = new OnlineUser();
				onlineUser = onlineUsers.get(sessionId);
				if (java.util.regex.Pattern.compile("," + roleId + ",")
						.matcher(onlineUser.getRoleIds()).find()) {
					onlineUsersByRole.put(sessionId, onlineUser);
				}
			}
		}

		Type type = new TypeToken<java.util.Collection<OnlineUser>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(onlineUsers.size()).append(",result:");

		Gson gson = new Gson();
		if (depId != null) {
			buff.append(gson.toJson(onlineUsersByDep.values(), type));
		} else if (roleId != null) {
			buff.append(gson.toJson(onlineUsersByRole.values(), type));
		} else {
			buff.append(gson.toJson(onlineUsers.values(), type));
		}
		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	/**
	 * 
	 * 根据角色查询
	 */
	public String find() {
		String strRoleId = getRequest().getParameter("roleId");
		PagingBean pb = getInitPagingBean();
		if (StringUtils.isNotEmpty(strRoleId)) {
			List<AppUser> userList = appUserService.findByRole(
					Long.parseLong(strRoleId), pb);
			Type type = new TypeToken<List<AppUser>>() {
			}.getType();
			StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
					.append(pb.getTotalItems()).append(",result:");
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create();
			buff.append(gson.toJson(userList, type));
			buff.append("}");

			jsonString = buff.toString();
		} else {
			jsonString = "{success:false}";
		}
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		StringBuffer buff = new StringBuffer("{success:true");
		if (ids != null) {
			buff.append(",msg:'");
			for (String id : ids) {
				AppUser delUser = appUserService.get(new Long(id));
				AppRole superManager = appRoleService.get(SUPPER_MANAGER_ID);
				if (delUser.getRoles().contains(superManager)) {
					buff.append("员工:").append(delUser.getUsername())
							.append("是超级管理员,不能删除!<br><br/>");
				} else if (delUser.getUserId().equals(
						ContextUtil.getCurrentUserId())) {
					buff.append("不能删除自己!<br></br>");
				} else {
					userOrgService.delCascade(new Long(id), new Long(0));
					delUser.setStatus(Constants.FLAG_DISABLE);
					delUser.setDelFlag(Constants.FLAG_DELETED);
					delUser.setUsername("__" + delUser.getUsername());
					appUserService.save(delUser);
				}
			}
			buff.append("'");
		}
		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		if (userId == null) {
			userId = ContextUtil.getCurrentUserId();
		}
		appUser = appUserService.get(userId);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(appUser);

		return SUCCESS;
	}

	/**
	 * 查询已有角色
	 */
	public String selectedRoles() {
		if (userId != null) {
			setAppUser(appUserService.get(userId));
			Set<AppRole> roles = appUser.getRoles();
			StringBuffer sb = new StringBuffer("[");
			for (AppRole role : roles) {
				sb.append("['" + role.getRoleId() + "','" + role.getRoleName()
						+ "'],");
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			setJsonString(sb.toString());
		}
		return SUCCESS;
	}

	/**
	 * 为某一用户提供可选的角色
	 * 
	 * @return
	 */
	public String getRoles() {

		List<AppRole> list = appRoleService.getAll();
		String userId = getRequest().getParameter("userId");

		List<AppRole> allRole = new ArrayList<AppRole>();

		Set curRoles = new HashSet();
		if (StringUtils.isNotEmpty(userId)) {
			AppUser user = appUserService.get(new Long(userId));
			curRoles = user.getRoles();
		}
		for (AppRole role : list) {
			if (!curRoles.contains(role)
					&& role.getStatus() != Constants.FLAG_DISABLE) {
				allRole.add(role);
			}
		}

		Type type = new TypeToken<List<AppRole>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		buff.append(gson.toJson(allRole, type));
		buff.append("}");
		jsonString = buff.toString();

		return SUCCESS;
	}

	/**
	 * 返回某一用户已有的角色
	 * 
	 * @return
	 */
	public String getSelRoles() {
		String userId = getRequest().getParameter("userId");

		if (StringUtils.isNotEmpty(userId)) {
			AppUser user = appUserService.get(new Long(userId));
			Set<AppRole> curRoles = user.getRoles();

			Type type = new TypeToken<Set<AppRole>>() {
			}.getType();
			StringBuffer buff = new StringBuffer("{success:true,result:");
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create();
			buff.append(gson.toJson(curRoles, type));
			buff.append("}");
			jsonString = buff.toString();

		}
		return SUCCESS;
	}

	/**
	 * 按职位查找所有用户 信息
	 * 
	 * @return
	 */
	public String posUsers() {
		String posId = getRequest().getParameter("posId");
		QueryFilter filter = new QueryFilter(getRequest());
		if (StringUtils.isNotEmpty(posId) && !"0".equals(posId)) {
			filter.addFilter("Q_positions.posId_L_EQ", posId.trim());
		}

		filter.addFilter("Q_delFlag_SN_EQ", Constants.FLAG_UNDELETED.toString());

		List<AppUser> list = appUserService.getAll(filter);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		;
		return SUCCESS;
	}

	/**
	 * 查询可选角色
	 * 
	 * @return
	 */
	public String chooseRoles() {
		List<AppRole> chooseRoles = appRoleService.getAll();
		if (userId != null) {
			setAppUser(appUserService.get(userId));
			Set<AppRole> selectedRoles = appUser.getRoles();
			for (AppRole role : selectedRoles) {
				chooseRoles.remove(role);
			}
		}
		StringBuffer sb = new StringBuffer("[");
		for (AppRole role : chooseRoles) {
			if (role.getStatus() != 0) {
				sb.append("['" + role.getRoleId() + "','" + role.getRoleName()
						+ "'],");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@Action(description = "修改密码")
	public String resetPassword() {
		String userId = getRequest().getParameter("appUserUserId");
		String oldPassword = DataEncryptUtil.encryptSha256(getRequest()
				.getParameter("oldPassword"));
		String newPassword = getRequest().getParameter("newPassword");
		String againPassword = getRequest().getParameter("againPassword");
		if (StringUtils.isNotEmpty(userId)) {
			setAppUser(appUserService.get(new Long(userId)));
		} else {
			setAppUser(ContextUtil.getCurrentUser());
		}
		StringBuffer msg = new StringBuffer("{msg:'");
		boolean pass = false;
		if (oldPassword.equals(appUser.getPassword())) {
			if (newPassword.equals(againPassword)) {
				pass = true;
			} else
				msg.append("两次输入不一致.'");
		} else
			msg.append("旧密码输入不正确.'");
		if (pass) {
			appUser.setPassword(DataEncryptUtil.encryptSha256(newPassword));
			appUserService.save(appUser);
			setJsonString("{success:true}");
		} else {
			msg.append(",success:false}");
			setJsonString(msg.toString());
		}
		return SUCCESS;
	}

	/**
	 * 重置密码
	 * 
	 * @return
	 */
	@Action(description = "重置密码")
	public String createPassword() {
		String userId = getRequest().getParameter("appUserUserId");
		String newPassword = getRequest().getParameter("newPassword");
		String password = getRequest().getParameter("password");
		StringBuffer msg = new StringBuffer("{msg:'");
		if (StringUtils.isNotEmpty(userId)) {
			setAppUser(appUserService.get(new Long(userId)));
		} else {
			setAppUser(ContextUtil.getCurrentUser());
		}

		if (newPassword.equals(password)) {
			appUser.setPassword(DataEncryptUtil.encryptSha256(newPassword));
			appUserService.save(appUser);
			setJsonString("{success:true}");
		} else {
			msg.append("重置失败!,两次输入的密码不一致,请重新输入!.'");
			msg.append(",failure:true}");
			setJsonString(msg.toString());
		}

		return SUCCESS;
	}

	/**
	 * 删除用户照片
	 * 
	 * @return
	 */
	public String photo() {
		setAppUser(appUserService.get(getUserId()));
		appUser.setPhoto("");
		appUserService.save(appUser);
		return SUCCESS;
	}

	/**
	 * 按部门查找合适做下属的用户
	 * 
	 * @return
	 */
	public String subAdepartment() {
		PagingBean pb = getInitPagingBean();
		String strDepId = getRequest().getParameter("depId");
		String path = "0.";
		AppUser user = ContextUtil.getCurrentUser();
		if (StringUtils.isNotEmpty(strDepId)) {
			Long depId = Long.parseLong(strDepId);
			Department dep = departmentService.get(depId);
			if (dep != null) {
				path = dep.getPath();
			}
		} else {
			Department dep = user.getDepartment();
			if (dep != null) {
				path = dep.getPath();
			}
		}
		if ("0.".equals(path)) {
			path = null;
		}
		Long uId = user.getUserId();

		// Set<Long> userIds = userSubService.findAllUpUser(uId);
		// List<Long> userIdsL = userSubService.subUsers(uId);

		// userIds.add(uId);
		// for (Long l : userIdsL) {
		// userIds.add(l);
		// }

		List<AppUser> list = appUserService.findSubAppUser(path, new HashSet(),
				pb);
		Type type = new TypeToken<List<AppUser>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	/**
	 * 根据角色来选择合适做下属的用户
	 * 
	 * @return
	 */
	public String subArole() {
		String strRoleId = getRequest().getParameter("roleId");
		PagingBean pb = getInitPagingBean();
		AppUser user = ContextUtil.getCurrentUser();
		if (StringUtils.isNotEmpty(strRoleId)) {
			Long uId = user.getUserId();
			// Set<Long> userIds = userSubService.findAllUpUser(uId);
			// List<Long> userIdsL = userSubService.subUsers(uId);
			// userIds.add(uId);
			// for (Long l : userIdsL) {
			// userIds.add(l);
			// }
			// List<AppUser> userList = appUserService.findSubAppUserByRole(
			// new Long(strRoleId), userIds, pb);
			// List<AppUser>
			// userList=appUserService.findByRole(Long.parseLong(strRoleId),
			// pb);
			Type type = new TypeToken<List<AppUser>>() {
			}.getType();
			StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
					.append(pb.getTotalItems()).append(",result:");
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation().create();
			buff.append(gson.toJson(new ArrayList(), type));
			buff.append("}");
			jsonString = buff.toString();
		} else {
			jsonString = "{success:false}";
		}
		return SUCCESS;
	}

	/**
	 * 按在线查找合适当下属的用户
	 */

	public String onlineAsub() {
		Map<String, OnlineUser> onlineUsers = new HashMap<String, OnlineUser>();
		Map<String, OnlineUser> onlineUsersBySub = new HashMap<String, OnlineUser>();
		onlineUsers = AppUtil.getOnlineUsers();// 获得所有在线用户
		// 按在线用户
		AppUser user = ContextUtil.getCurrentUser();
		Long uId = user.getUserId();
		Set<Long> userIds = new HashSet();// userSubService.findAllUpUser(uId);
		userIds.add(uId);
		List<Long> userIdsL = new ArrayList();// userSubService.subUsers(uId);
		for (Long l : userIdsL) {
			userIds.add(l);
		}
		for (String sessionId : onlineUsers.keySet()) {
			OnlineUser onlineUser = new OnlineUser();
			onlineUser = onlineUsers.get(sessionId);
			if (!userIds.contains(onlineUser.getUserId())) {
				onlineUsersBySub.put(sessionId, onlineUser);
			}
		}
		Type type = new TypeToken<java.util.Collection<OnlineUser>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(onlineUsers.size()).append(",result:");
		Gson gson = new Gson();
		buff.append(gson.toJson(onlineUsersBySub.values(), type));
		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	/**
	 * 上属
	 * 
	 * @return
	 */
	public String upUser() {
		Set<AppUser> set = subordinateService.getUpUser(ContextUtil
				.getCurrentUserId());

		StringBuffer buff = new StringBuffer("[");
		for (Iterator it = set.iterator(); it.hasNext();) {
			AppUser user = (AppUser) it.next();
			buff.append("['" + user.getUserId().toString() + "','"
					+ user.getFullname() + "'],");
		}
		if (set.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return SUCCESS;
	}

	/**
	 * 当前用户修改个人资料
	 * 
	 * @return
	 */
	@Action(description = "修改个人资料")
	public String profile() {

		AppUser curAppUser = ContextUtil.getCurrentUser();

		AppUser orgAppUser = appUserService.get(curAppUser.getUserId());

		orgAppUser.setFullname(appUser.getFullname());
		orgAppUser.setEmail(appUser.getEmail());
		orgAppUser.setTitle(appUser.getTitle());
		orgAppUser.setPhone(appUser.getPhone());
		orgAppUser.setMobile(appUser.getMobile());
		orgAppUser.setFax(appUser.getFax());
		orgAppUser.setAddress(appUser.getAddress());
		orgAppUser.setZip(appUser.getZip());
		orgAppUser.setPhoto(appUser.getPhoto());

		appUserService.save(orgAppUser);
		// 使其不需要重新登录都能正常显示已经修改过的用户名
		curAppUser.setFullname(orgAppUser.getFullname());

		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String bindDyPwd() {
		StringBuffer msg = new StringBuffer("{success:true,msg:'");
		String curDynamicPwd = getRequest().getParameter("curDynamicPwd");
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("app", "demoauthapp");
		input.put("user", appUser.getDynamicPwd());
		input.put("pw", curDynamicPwd);

		String result = appUserService.initDynamicPwd(input, "bind");

		if (result.equals("ok")) {
			AppUser orgUser = appUserService.get(appUser.getUserId());
			orgUser.setDynamicPwd(appUser.getDynamicPwd());
			orgUser.setDyPwdStatus(AppUser.DYNPWD_STATUS_BIND);
			appUserService.save(orgUser);
			msg.append("成功绑定!");
		} else {
			msg.append(result);
		}
		msg.append("'}");
		setJsonString(msg.toString());
		return SUCCESS;
	}

	/**
	 * 
	 */
	public String unbindDyPwd() {
		StringBuffer msg = new StringBuffer("{success:true,msg:'");
		String curDynamicPwd = getRequest().getParameter("curDynamicPwd");
		HashMap<String, String> input = new HashMap<String, String>();
		input.put("app", "demoauthapp");
		input.put("user", appUser.getDynamicPwd());
		input.put("pw", curDynamicPwd);

		String result = appUserService.initDynamicPwd(input, "unbind");

		if (result.equals("ok")) {
			AppUser orgUser = appUserService.get(appUser.getUserId());
			orgUser.setDyPwdStatus(AppUser.DYNPWD_STATUS_UNBIND);
			appUserService.save(orgUser);
			msg.append("解绑成功!");
		} else {
			msg.append(result);
		}
		msg.append("'}");
		setJsonString(msg.toString());
		return SUCCESS;
	}

	/**
	 * 针对对话框的用户选择及条件过滤
	 * 
	 * @return
	 */
	public String dialog() {
		QueryFilter filter = new QueryFilter(getRequest());
		// 是否为显示当前部门的员工
		String curDep = getRequest().getParameter("cuDep");
		if (StringUtils.isNotEmpty(curDep)) {
			AppUser curUser = ContextUtil.getCurrentUser();
			filter.addFilter("Q_orgId_L_EQ", curUser.getOrgId().toString());
		}
		// 显示非删除的用户
		filter.addFilter("Q_delFlag_SN_EQ", "0");
		List<AppUser> userList = appUserService.getAll(filter);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		Type type = new TypeToken<List<AppUser>>() {
		}.getType();
		buff.append(gson.toJson(userList, type));
		buff.append("}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	/**
	 * 保存并更新用户信息
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		if (appUser.getUserId() != null) {
			appUser = appUserService.get(new Long(appUser.getUserId()));
			try {
				BeanUtil.populateEntity(getRequest(), appUser, "appUser");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			AppUser au = appUserService.findByUserName(appUser.getUsername());
			if (au != null) {
				jsonString = "{success:false,msg:'该用户已存在'}";
				return SUCCESS;
			}

			appUser.setPassword(DataEncryptUtil.encryptSha256(appUser.getPassword()));
			appUser.setDelFlag(Constants.FLAG_UNDELETED);
		}

		appUserService.save(appUser);

		// 处理其对应的部门，用户等
		String roleIds = getRequest().getParameter("roleIds");
		String posIds = getRequest().getParameter("posIds");
		String orgIds = getRequest().getParameter("orgIds");

		logger.debug("roleIds:" + roleIds + " posIds:" + posIds + " orgIds:"
				+ orgIds);
		if (StringUtils.isNotEmpty(roleIds)) {
			String[] roleIdArr = roleIds.split("[,]");
			// 处理该用户对应的角色
			if (roleIdArr != null) {
				appUser.setRoles(new HashSet<AppRole>());
				for (String roleId : roleIdArr) {
					AppRole role = appRoleService.get(new Long(roleId));
					appUser.getRoles().add(role);
				}
				appUserService.save(appUser);

			}
		}

		Gson gson = new Gson();
		if (StringUtils.isNotEmpty(posIds)) {
			UserPosition[] ups = (UserPosition[]) gson.fromJson(posIds,
					UserPosition[].class);

			if (ups != null) {
				// 检查该用户的Position是否存在，不存在，则添加
				for (UserPosition up : ups) {
					if (up.getUserPosId() == null) {// 若为新增加的
						up.setAppUser(appUser);
						up.setPosition(positionService.get(up.getPosId()));
						userPositionService.save(up);
					} else {// 若为更新的
						UserPosition pos = userPositionService.get(up
								.getUserPosId());
						pos.setIsPrimary(up.getIsPrimary());
						userPositionService.save(pos);
					}
				}
			}
		}

		if (StringUtils.isNotEmpty(orgIds)) {

			// 处理orgIds
			UserOrg[] uos = (UserOrg[]) gson.fromJson(orgIds, UserOrg[].class);

			if (orgIds != null && orgIds.length() > 0) {

				userOrgService.delCascade(appUser.getUserId(), new Long("0"));
				for (UserOrg uo : uos) {

					UserOrg tuo = new UserOrg();
					tuo.setIsPrimary(uo.getIsPrimary());
					tuo.setIsCharge(uo.getIsCharge());
					tuo.setAppUser(appUser);

					Organization organization = organizationService.get(uo
							.getOrgId());
					if (uo.getIsPrimary() == UserOrg.PRIMARY) {
						// 设置主部门
						Department dep = departmentService.get(uo.getOrgId());
						appUser.setDepartment(dep);
						// 更新其对应的所属公司ID
						String path = dep.getPath();
						if (path != null) {
							String[] ids = path.split("[.]");
							for (String id : ids) {
								if (!"0".equals(id)
										&& StringUtils.isNotEmpty(id)) {// 不为0
									Organization org = (Organization) organizationService
											.get(new Long(id));
									if (Organization.ORG_TYPE_COMPANY
											.equals(org.getOrgType())) {
										appUser.setOrgId(new Long(id));
										break;
									}
								}
							}
						}
						appUserService.save(appUser);
					}
					tuo.setOrganization(organization);
					userOrgService.save(tuo);
				}
			}
		}
		return SUCCESS;
	}

	/**
	 * 加载用户实体
	 * 
	 * @return
	 */
	public String load() {
		AppUser appUser = appUserService.get(userId);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(appUser);
		return SUCCESS;
	}

	/**
	 * 删除选择的角色
	 * 
	 * @return
	 */
	public String delRole() {
		String roleId = getRequest().getParameter("roleId");

		if (StringUtils.isNotEmpty(roleId)) {
			AppUser appUser = appUserService.get(userId);
			AppRole appRole = appRoleService.get(new Long(roleId));
			appUser.getRoles().remove(appRole);
			appUserService.save(appUser);
		}

		return SUCCESS;
	}

	// 取得某个部门下的所有用户
	public String depUsers() {
		String depId = getRequest().getParameter("depId");
		String username = getRequest().getParameter("Q_username_S_LK");
		String fullname = getRequest().getParameter("Q_fullname_S_LK");
		String dir = getRequest().getParameter("dir");
		String sort = getRequest().getParameter("sort");
		PagingBean pb = getInitPagingBean();
		Organization org = null;
		if (StringUtils.isNotEmpty(depId) && !"0".equals(depId)) {
			org = organizationService.get(new Long(depId));
		}
		String orgPath = org == null ? "0." : org.getPath();
		Map map = new HashMap();
		map.put("username", username == null ? "" : username);
		map.put("fullname", fullname == null ? "" : fullname);
		map.put("dir", dir == null ? "" : dir);
		map.put("sort", dir == null ? "" : sort);
		List<AppUser> users = appUserService.getDepUsers(orgPath, pb, map);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(pb.getTotalItems()).append(",result:[ ");
		Gson gson = new Gson();
		if (users.size() > 0) {
			for (AppUser au : users) {
				buff.append("{");
				buff.append("\"userId\":")
						.append("\"" + au.getUserId() + "\",");
				buff.append("\"username\":").append(
						gson.toJson(au.getUsername()) + ",");
				buff.append("\"fullname\":").append(
						gson.toJson(au.getFullname()) + ",");
				buff.append("\"primaryDep\":").append(
						gson.toJson(au.getPrimaryDep()) + ",");
				buff.append("\"email\":").append(
						gson.toJson(au.getEmail()) + ",");
				buff.append("\"depNames\":").append(
						gson.toJson(au.getDepNames()) + ",");
				buff.append("\"posNames\":").append(
						gson.toJson(au.getPosNames()) + ",");
				buff.append("\"roleNames\":").append(
						gson.toJson(au.getRoleNames()) + ",");
				buff.append("\"accessionTime\":").append(
						gson.toJson(au.getAccessionTime().toString()
								.substring(0, 10)));
				buff.append("},");
			}
		}
		jsonString = buff.toString().substring(0, buff.length() - 1) + "]}";

		return SUCCESS;
	}
}
