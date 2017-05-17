package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.UserOrg;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.DemensionService;
import com.htsoft.oa.service.system.OrganizationService;
import com.htsoft.oa.service.system.UserOrgService;

/**
 * 
 * @author
 * 
 */
public class OrganizationAction extends BaseAction {
	@Resource
	private OrganizationService organizationService;
	@Resource
	private UserOrgService userOrgService;
	@Resource
	private AppUserService appUserService;
	@Resource
	private DemensionService demensionService;

	private Organization organization;

	private Long orgId;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());

		String orgSupId = getRequest().getParameter("orgSupId");
		if (StringUtils.isNotEmpty(orgSupId)) {
			Organization supOrg = organizationService.get(new Long(orgSupId));
			filter.addFilter("Q_path_S_LFK",
					supOrg == null ? "0." : supOrg.getPath());
		}
		filter.addSorted("path", "asc");
		List<Organization> list = organizationService.getAll(filter);

		Type type = new TypeToken<List<Organization>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd").create();
		buff.append(gson.toJson(list, type));
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
			//删除某个组织及其下属组织
			for(String id:ids){
				if(!organizationService.delCascade(new Long(id))){
					jsonString="{success:false}";
					return SUCCESS;
				}					
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String get() {
		Organization organization = organizationService.get(orgId);
		List<AppUser> list = appUserService.getChargeOrgUsers(organization
				.getUserOrgs());
		String chargeIds = "";
		String chargeNames = "";
		if (list != null && list.size() > 0) {
			for (int index = 0; index < list.size(); index++) {
				AppUser au = list.get(index);
				if (index == 0) {
					chargeIds += au.getUserId();
					chargeNames += au.getFullname();
				} else {
					chargeIds += "," + au.getUserId();
					chargeNames += "," + au.getFullname();
				}
			}
		}

		Organization supOrg = organizationService.get(organization
				.getOrgSupId());

		// 将数据转成JSON格式
		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer("{success:true,data:{");
		sb.append("\"orgId\":" + organization.getOrgId() + ",");
		sb.append("\"demId\":" + organization.getDemId() + ",");
		sb.append("\"orgName\":" + gson.toJson(organization.getOrgName()) + ",");
		sb.append("\"orgDesc\":" + gson.toJson(organization.getOrgDesc()) + ",");
		sb.append("\"orgSupId\":" + organization.getOrgSupId() + ",");
		sb.append("\"orgSupName\":"
				+ gson.toJson(supOrg == null ? "" : supOrg.getOrgName()) + ",");
		sb.append("\"orgType\":" + organization.getOrgType() + ",");
		sb.append("\"chargeIds\":\"" + chargeIds + "\",");
		sb.append("\"oldChargeIds\":\"" + chargeIds + "\",");
		sb.append("\"chargeNames\":" + gson.toJson(chargeNames));
		sb.append(",sn:"
				+ (organization.getSn() == null ? 0 : organization.getSn()));
		sb.append("}}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String chargeIds = getRequest().getParameter("chargeIds");
		if (organization.getOrgId() == null) {
			organization.setCreatetime(new Date());
			organization.setUpdatetime(new Date());
			organization.setCreatorId(ContextUtil.getCurrentUserId());
			organization.setUpdateId(ContextUtil.getCurrentUserId());

			organizationService.save(organization);
			Long parentId = organization.getOrgSupId();
			if (parentId == 0) {
				organization.setPath("0." + organization.getOrgId() + ".");
				organization.setDepth(1l);
			} else {
				Organization supOrg = organizationService.get(parentId);
				organization.setPath(supOrg.getPath() + organization.getOrgId()
						+ ".");
				organization.setDepth(supOrg.getDepth() == null ? 1 : supOrg
						.getDepth() + 1);
			}
			organizationService.save(organization);

			if (chargeIds != null && chargeIds.length() > 0) {
				String[] pids = chargeIds.split(",");
				for (int index = 0; index < pids.length; index++) {
					UserOrg userOrg = new UserOrg();
					userOrg.setUserid(new Long(pids[index]));
					userOrg.setOrgId(organization.getOrgId());
					userOrg.setIsPrimary(new Short("1"));
					userOrg.setIsCharge(new Short("1"));
					userOrgService.save(userOrg);
				}
			}
		} else {
			Organization orgOrganization = organizationService.get(organization
					.getOrgId());
			try {

				Long parentId = orgOrganization.getOrgSupId();
				if (parentId == 0) {
					orgOrganization.setPath("0." + organization.getOrgId()
							+ ".");
					orgOrganization.setDepth(1l);
				} else {
					Organization supOrg = organizationService.get(parentId);
					orgOrganization.setPath(supOrg.getPath()
							+ orgOrganization.getOrgId() + ".");
					orgOrganization.setDepth(supOrg.getDepth() + 1);
				}
				orgOrganization.setUpdatetime(new Date());
				orgOrganization.setUpdateId(ContextUtil.getCurrentUserId());
				BeanUtil.copyNotNullProperties(orgOrganization, organization);
				organizationService.save(orgOrganization);

				String oldChargeIds = getRequest().getParameter("oldChargeIds");
				String[] pids = chargeIds.split(",");
				userOrgService.updOrgCharge(
						oldChargeIds.length() > 0 ? oldChargeIds : "0",
						orgOrganization.getOrgId());
				if (chargeIds != null && chargeIds.length() > 0) {
					userOrgService.updOrgCharge(
							chargeIds.length() > 0 ? chargeIds : "0",
							orgOrganization.getOrgId());
					for (int index = 0; index < pids.length; index++) {
						List<UserOrg> uolist = userOrgService.getByUserIdOrgId(
								new Long(pids[index]),
								orgOrganization.getOrgId());
						UserOrg userOrg = uolist.size() > 0 ? uolist.get(0)
								: new UserOrg();
						if (userOrg.getUserOrgId() == null) {
							userOrg.setUserid(new Long(pids[index]));
							userOrg.setOrgId(organization.getOrgId());
						}
						userOrg.setIsCharge(new Short("1"));
						if (userOrg.getIsPrimary() == null) {
							userOrg.setIsPrimary(new Short("0"));
						}
						userOrgService.save(userOrg);
					}
				}

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 取得树
	 * 
	 * @return
	 */
	public String tree() {
		String pDemId = getRequest().getParameter("demId");
		Long demId = 0l;
		if (StringUtils.isNotEmpty(pDemId)) {
			demId = new Long(pDemId);
		}
		String[] otherNode = { "orgType", "orgDem" };
		List<Organization> list = organizationService.getByDemId(demId);
		String method = getRequest().getParameter("method");
		jsonString = JsonTree.generate(getResult(list, method), otherNode,
				false);
		logger.info("tree json:" + jsonString);
		return SUCCESS;
	}

	/**
	 * 产生树的结果
	 * 
	 * @param list
	 * @param root
	 * @return
	 */
	private List<Object> getResult(List<Organization> list, String method) {
		List<Object> dataList = new ArrayList<Object>();

		if (!StringUtils.isNotEmpty(method)) {
			HashMap<String, Object> rootNode = new HashMap<String, Object>();
			rootNode.put("id", "0");
			rootNode.put("text", "全部组织");
			rootNode.put("parentId", null);
			rootNode.put("orgDem", 0L);
			dataList.add(rootNode);
		}
		for (Organization organization : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", organization.getOrgId().toString());
			dataRecord.put("text", organization.getOrgName());
			String parentId = organization.getOrgSupId().toString();
			if (StringUtils.isNotEmpty(method)
					&& organization.getOrgSupId() != null
					&& organization.getOrgSupId().longValue() == 0L) {
				parentId = null;
			}
			dataRecord.put("parentId", parentId);
			dataRecord.put("orgType", organization.getOrgType());
			dataRecord.put("orgDem", organization.getDemId());
			dataList.add(dataRecord);
		}

		return dataList;
	}


	/**
	 * 明细
	 * 
	 * @return
	 */
	public String detail() {
		String orgId = getRequest().getParameter("orgId");
		if (StringUtils.isNotEmpty(orgId) && !"0".equals(orgId)) {
			Organization organization = organizationService
					.get(new Long(orgId));
			// 主要负责人
			String chargeUser = "";
			Iterator<UserOrg> auit = organization.getUserOrgs().iterator();
			while (auit.hasNext()) {
				UserOrg uo = auit.next();
				if (uo.getIsCharge() != null && uo.getIsCharge() == 1) {
					chargeUser += uo.getAppUser().getFullname() + ",";
				}
			}
			if (chargeUser.length() > 0) {
				chargeUser = chargeUser.substring(0, chargeUser.length() - 1);
			}
			getRequest().setAttribute("chargeUser", chargeUser);

			// 维度
			getRequest().setAttribute("demName",
					demensionService.get(organization.getDemId()).getDemName());
			if (organization.getOrgSupId() != null
					&& organization.getOrgSupId() != -1) {
				Organization supOrg = organizationService.get(organization
						.getOrgSupId());
				getRequest().setAttribute("supOrg", supOrg);
			}

			// 建立人，修改人
			AppUser user = appUserService.get(organization.getCreatorId());
			getRequest().setAttribute("creatorName", user.getFullname());
			user = appUserService.get(organization.getUpdateId());
			getRequest().setAttribute("updateName", user.getFullname());

			getRequest().setAttribute("org", organization);
		}
		return "detail";

	}

	/**
	 * 取得树
	 * 
	 * @return
	 */
	public String orgStructure() {
		String pDemId = getRequest().getParameter("demId");
		Long demId = 0l;
		if (StringUtils.isNotEmpty(pDemId)) {
			demId = new Long(pDemId);
		}
		StringBuffer buff = new StringBuffer("[{id:'" + 0
				+ "',text:'全部',expanded:true,children:[");
		List<Organization> orgList = organizationService.getByParent(new Long(
				0l), demId);
		for (Organization org : orgList) {
			buff.append("{id:'" + org.getOrgId())
					.append("',text:'" + org.getOrgName()).append("',")
					.append("flag:'").append("org").append("',");
			buff.append(getDeps(org.getOrgId(), demId));
		}
		if (!orgList.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}]");
		setJsonString(buff.toString());
		System.out.println(buff.toString());
		return SUCCESS;
	}

	public String getDeps(Long parentId, Long demId) {
		StringBuffer buff = new StringBuffer();
		List<Organization> orgList = organizationService.getByParent(parentId,
				demId);
		List<AppUser> users = appUserService.getDepUsers(organizationService
				.get(parentId).getPath(), null, null);
		if (orgList.size() == 0 && users.size() == 0) {
			buff.append("leaf:true,expanded:true},");
			return buff.toString();
		} else if (orgList.size() == 0 && users.size() != 0) {
			buff.append("expanded:true,children:[");
			buff.append("{id:'org" + parentId
					+ "',text:'员工',flag:'user',expanded:true,children:[");
			buff.append(getUsers(users, parentId));
			buff.deleteCharAt(buff.length() - 1);
			buff.append("]},");
			return buff.toString();
		} else {
			buff.append("expanded:true,children:[");
			for (Organization org : orgList) {
				buff.append("{id:'org" + parentId
						+ "',text:'员工',flag:'user',expanded:true,");
				buff.append("children:[");
				buff.append(getUsers(users, parentId));
				buff.append("{id:'" + org.getOrgId())
						.append("',text:'" + org.getOrgName()).append("',")
						.append("flag:'").append("org").append("',");
				buff.append(getDeps(org.getOrgId(), demId));
			}
			buff.deleteCharAt(buff.length() - 1);
			buff.append("]},");
			return buff.toString();
		}
	}

	/**
	 * 
	 * 根据部门路径取得部门员工<br>
	 * 
	 * @param 参数
	 *            orgPath 组织路径
	 * 
	 * @return 返回 员工json
	 */
	public String getUsers(List<AppUser> users, Long orgId) {
		StringBuffer buff = new StringBuffer();
		// List<AppUser> users=appUserService.getDepUsers(orgPath, null, null);
		if (users.size() == 0) {
			buff.append("]},");
			return buff.toString();
		} else {
			for (AppUser au : users) {
				buff.append("{id:'" + "org" + orgId + "_" + au.getUserId())
						.append("',text:'" + au.getFullname()).append("',")
						.append("flag:'").append("user").append("',")
						.append("leaf:true,expanded:true").append("},");
			}
			buff.deleteCharAt(buff.length() - 1);
			buff.append("]},");
			return buff.toString();
		}
	}

	/**
	 * 取上级组织名称
	 * 
	 * @return
	 */
	public String getSup() {
		Long supId = new Long(getRequest().getParameter("orgSupId"));
		Organization org = organizationService.get(new Long(supId));
		StringBuffer sb = new StringBuffer("{success:true,data:{");
		Gson gson = new Gson();
		sb.append("\"orgName\":" + gson.toJson(org.getOrgName()));
		sb.append("}}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 取某维度的级数
	 * 
	 * @return
	 */
	public String getLevel() {
		Long demId = new Long(getRequest().getParameter("demId"));
		Long maxDepth = organizationService.getMaxDepth(demId);
		StringBuffer sb = new StringBuffer("[");
		for(Long i=1L;i<=maxDepth;i++){
			sb.append("['"+ i.toString()+"'],");
		}
		if((sb.length()-1)==sb.lastIndexOf(","))
			sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		setJsonString(sb.toString());
		return SUCCESS;
	}
}
