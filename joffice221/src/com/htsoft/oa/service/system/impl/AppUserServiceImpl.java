package com.htsoft.oa.service.system.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.core.dynamicPwd.HttpClient;
import com.htsoft.oa.core.dynamicPwd.YooeResponse;
import com.htsoft.oa.dao.system.AppUserDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.model.system.IndexDisplay;
import com.htsoft.oa.model.system.PanelItem;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.model.system.UserOrg;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.IndexDisplayService;
import com.htsoft.oa.service.system.SysConfigService;

/**
 * @description 用户信息
 * @class AppUserServiceImpl
 * @author 宏天软件
 * @updater YHZ
 * @company www.jee-soft.cn
 * @data 2010-12-27AM
 */
public class AppUserServiceImpl extends BaseServiceImpl<AppUser> implements AppUserService {
	private AppUserDao dao;
	@Resource
	IndexDisplayService indexDisplayService;

	public AppUserServiceImpl(AppUserDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Resource
	private SysConfigService sysConfigService;

	@Override
	public AppUser findByUserName(String username) {
		return dao.findByUserName(username);
	}

	@Override
	public List<AppUser> findByDepartment(String path, PagingBean pb) {
		return dao.findByDepartment(path, pb);
	}

	@Override
	public List<AppUser> findByDepartment(String path, String userIds, PagingBean pb){
		return dao.findByDepartment(path, pb);
	}
	
	@Override
	public List<AppUser> findByRole(Long roleId, PagingBean pb) {
		return dao.findByRole(roleId, pb);
	}

	public List<AppUser> findByRoleId(Long roleId) {
		return dao.findByRole(roleId);
	}

	@Override
	public List<AppUser> findSubAppUser(String path, Set<Long> userIds,
			PagingBean pb) {
		return dao.findSubAppUser(path, userIds, pb);
	}

	@Override
	public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds,
			PagingBean pb) {
		return dao.findSubAppUserByRole(roleId, userIds, pb);
	}

	@Override
	public List<AppUser> findByDepId(Long depId) {
		return dao.findByDepId(depId);
	}

	public String initDynamicPwd(HashMap<String, String> input, String function) {
		SysConfig dynamicPwdConfig = sysConfigService.findByKey("dynamicUri");
		URI base_uri = URI.create(dynamicPwdConfig.getDataValue());
		HttpClient client = new HttpClient(base_uri);
		try {

			YooeResponse response = client.call_api(function, input);

			String ret_cmd = response.getRetCmd();
			logger.debug("=============dynamicPwd status:" + ret_cmd);

			HashMap<String, String> output = response.getVarsDict();
			Iterator<String> i = output.keySet().iterator();
			String result = output.get("ret");

			while (i.hasNext()) {
				String name = i.next();
				String value = output.get(name);
				logger.debug("==============dynamicPwd info:" + name + "="
						+ value);
			}

			return result;

		} catch (IOException e) {
			e.printStackTrace();
			return "\"" + function + "\"失败，异常：" + e.getMessage();
			// TODO Auto-generated catch block
		} catch (Exception e) {
			e.printStackTrace();
			return "\"" + function + "\"失败，异常：" + e.getMessage();
			// TODO Auto-generated catch block
		}
	}

	/**
	 * 按角色ID查找用户
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<AppUser> findUsersByRoleIds(String roleIds) {
		return dao.findUsersByRoleIds(roleIds);
	}

	/**
	 * @description 根据当前用户岗位取得下属岗位的用户
	 * @return List<AppUser>
	 */
	public List<AppUser> findRelativeUsersByUserId() {
		return dao.findRelativeUsersByUserId();
	}
	

	/**
	 * 按角色取得用户列表
	 * @param roleId
	 * @return
	 */
	public List<AppUser> getUsersByRoleId(Long roleId){
		return dao.getUsersByRoleId(roleId);
	}
	
	/**
	 * 返回当前用户的信息，以Json格式返回
	 * @return
	 */
	public String getCurUserInfo(){
	    AppUser currentUser = ContextUtil.getCurrentUser();
		Department curDep = currentUser.getDepartment();
		if (curDep == null) {// 若所属部门为空，则设置一个缺省的部门 TODO
			curDep = new Department();
			curDep.setDepId(0l);
			curDep.setDepName(AppUtil.getCompanyName());
		}
		//去掉公共权限
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
		sb.append("{success:true,user:{userId:'").append(
				currentUser.getUserId()).append("',fullname:'").append(
				currentUser.getFullname()).append("',username:'").append(
				currentUser.getUsername()).append("',depId:'").append(
				curDep.getDepId()).append("',depName:'").append(
				curDep.getDepName()).append("',posName:'").append(currentUser.getPosNames().toString()).
				append("',rights:'");
		sb.append(currentUser.getRights().toString().replace("[", "").replace(
				"]", ""));
		
		Gson gson = new Gson();
		sb.append("',topModules:");
		sb.append(gson.toJson(currentUser.getValidTopModules().values()));
		sb.append(",items:").append(gson.toJson(items).toString());
		sb.append("},sysConfigs:{");
		//系统配置也在此时加载
		List<SysConfig> sysConfigs = sysConfigService.getAll();
		for(SysConfig sysConfig : sysConfigs){
			sb.append("'")
			  .append(sysConfig.getConfigKey())
			  .append("':'")
			  .append(sysConfig.getDataValue())
			  .append("',");
		}
		if(sysConfigs.size()>0){
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("}}");
		
		return sb.toString().replaceAll("\"", "'");
	}
	
	/**
	 * 按部门取得用户列表
	 * @param orgPath
	 * @return
	 */
	@Override
	public List<AppUser> getDepUsers(String orgPath,PagingBean pb,Map map) {
		return dao.getDepUsers(orgPath,pb,map);
	}
	
	/**
	 * 取得想对岗位用户列表
	 * @param reJobId
	 * @return
	 */
	@Override
	public List<AppUser> getReLevelUser(String reJobId,Long startUserId) {
		return dao.getReLevelUser(reJobId,startUserId);
	}
	
	/**
	 * 取得组织主要负责人
	 * @param userOrg
	 * @return
	 */
	@Override
	public List<AppUser> getChargeOrgUsers(Set<UserOrg> userOrgs) {
		return dao.getChargeOrgUsers(userOrgs);
	}


	@Override
	public Boolean isSuperMan(Long userId) {
		return dao.isSuperMan(userId);
	}

	/**
	 * 判断是否有下属用户
	 * 2012-3-15
	 * @param fullname
	 * @param userId
	 * @return
	 */
	@Override
	public List<AppUser> findRelativeUsersByFullname(String fullname,
			Long userId) {
		return dao.findRelativeUsersByFullname(fullname, userId);
	}

}
