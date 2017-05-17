package com.htsoft.oa.dao.system.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

import com.htsoft.core.Constants;
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.AppUserDao;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.Position;
import com.htsoft.oa.model.system.UserOrg;
import com.htsoft.oa.model.system.UserPosition;

/**
 * @description 用户信息管理
 * @class AppUserDaoImpl
 * @author 宏天软件
 * @updater YHZ
 * @company www.jee-soft.cn
 * @data 2010-12-27AM
 */
@SuppressWarnings("unchecked")
public class AppUserDaoImpl extends BaseDaoImpl<AppUser> implements AppUserDao,
		UserDetailsService {

	public AppUserDaoImpl() {
		super(AppUser.class);
	}

	@Override
	public AppUser findByUserName(String username) {
		String hql = "from AppUser au where au.username=?";
		Object[] params = { username };
		List<AppUser> list = findByHql(hql, params);
		AppUser user = null;
		if (list.size() != 0) {
			user = list.get(0);

		}
//		 String countHql="select count(vo.userId) from AppUser vo where vo.delFlag=0";
//		 Object countVal=findUnique(countHql,null);
//		 if(new Long(countVal.toString())>=1002){
//		 user.setStatus((short)0);
//		 }
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException, DataAccessException {
		AppUser user = null;
		try {
			user = (AppUser) getHibernateTemplate().execute(
					new HibernateCallback() {
						public Object doInHibernate(Session session)
								throws HibernateException, SQLException {
							String hql = "from AppUser ap where ap.username=? and ap.delFlag = ?";
							Query query = session.createQuery(hql);
							query.setString(0, username);
							query.setShort(1, Constants.FLAG_UNDELETED);
							AppUser user = null;
							user = (AppUser) query.uniqueResult();

							if (user != null) {
								Hibernate.initialize(user.getRoles());
								Hibernate.initialize(user.getDepartment());
								user.init();
							}
							return user;
						}
					});
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// 初始化其对应的公司
		if (user != null && user.getDepartment() != null) {
			String path = user.getDepartment().getPath();
			if (path != null) {
				String[] ids = path.split("[.]");
				for (String id : ids) {
					if (!"0".equals(id) && StringUtils.isNotEmpty(id)) {
						Organization org = (Organization) findUnique(
								"from Organization org where org.orgId=?",
								new Object[] { new Long(id) });
						if (Organization.ORG_TYPE_COMPANY.equals(org
								.getOrgType())) {
							user.setCompany(org);
							break;
						}
					}
				}
			}
		}

		// 若用户当前不属于任何公司，则其为公共部门下的。
		if (user != null && user.getCompany() == null) {
			Organization org = (Organization) findUnique(
					"from Organization org where org.orgId=?",
					new Object[] { new Long(Organization.PUBLIC_COMPANY_ID) });
			user.setCompany(org);
		}

//		 String countHql="select count(vo.userId) from AppUser vo where vo.delFlag=0";
//		 Object countVal=findUnique(countHql,null);
//		 if(new Long(countVal.toString())>=1002){
//		 user.setStatus((short)0);
//		 }else if(user.getUserId().longValue() ==
//		 AppUser.SUPER_USER.longValue()){
//		 user.setStatus((short)1);
//		 }

		return user;
	}

	/**
	 * 根据部门PATH属性查找
	 */
	@Override
	public List findByDepartment(String path, PagingBean pb) {
		List list = new ArrayList();
		String hql = new String();
		if ("0.".equals(path)) {
			hql = "from AppUser vo2 where vo2.delFlag = ?";
			list.add(Constants.FLAG_UNDELETED);
		} else {
			// TODO
			hql = "select distinct au from AppUser au where au.department.path like ? and au.delFlag=? ";
			// hql =
			// "select DISTINCT vo2 from Department vo1,AppUser vo2,DepUsers vo3 where 1=1"
			// + " and vo3.appUser=vo2"
			// + " and vo3.department=vo1"
			// + " and vo1.path like ? and vo2.delFlag = ? order by vo3.sn";
			list.add(path + "%");
			list.add(Constants.FLAG_UNDELETED);
		}
		return findByHql(hql, list.toArray(), pb);
	}

	@Override
	public List findByDepartment(Department department) {
		String hql = "select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department and vo1.path like ? and vo2.delFlag = ?";
		Object[] params = { department.getPath() + "%",
				Constants.FLAG_UNDELETED };
		return findByHql(hql, params);
	}

	@Override
	public List findByRole(Long roleId) {
		String hql = "select vo from AppUser vo join vo.roles roles where roles.roleId=? and vo.delFlag = ?";
		Object[] objs = { roleId, Constants.FLAG_UNDELETED };
		return findByHql(hql, objs);
	}

	@Override
	public List findByRole(Long roleId, PagingBean pb) {
		String hql = "select vo from AppUser vo join vo.roles roles where roles.roleId=? and vo.delFlag = ?";
		Object[] objs = { roleId, Constants.FLAG_UNDELETED };
		return findByHql(hql, objs, pb);
	}

	@Override
	public List<AppUser> findByDepartment(String path) {
		String hql = "select vo2 from Department vo1,AppUser vo2 where vo1.depId=vo2.depId and vo1.path like ? and vo2.delFlag =?";
		Object[] params = { path + "%", Constants.FLAG_UNDELETED };
		return findByHql(hql, params);
	}

	public List findByRoleId(Long roleId) {
		String hql = "select vo from AppUser vo join vo.roles as roles where roles.roleId=? and vo.delFlag =?";
		return findByHql(hql, new Object[] { roleId, Constants.FLAG_UNDELETED });
	}

	public List findByUserIds(Long... userIds) {
		String hql = "select vo from AppUser vo where vo.delFlag=? ";

		if (userIds == null || userIds.length == 0)
			return null;
		hql += " where vo.userId in (";
		int i = 0;
		for (@SuppressWarnings("unused")
		Long userId : userIds) {
			if (i++ > 0) {
				hql += ",";
			}
			hql += "?";
		}
		hql += " )";

		return findByHql(hql,
				new Object[] { Constants.FLAG_UNDELETED, userIds });
	}

	@Override
	public List<AppUser> findSubAppUser(String path, Set<Long> userIds,
			PagingBean pb) {
		String st = "";
		if (userIds.size() > 0) {
			Iterator<Long> it = userIds.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				sb.append(it.next().toString() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			st = sb.toString();
		}

		List list = new ArrayList();
		StringBuffer hql = new StringBuffer();
		if (path != null) {
			hql.append("select vo2 from Department vo1,AppUser vo2 where vo1=vo2.department ");
			hql.append(" and vo1.path like ?");
			list.add(path + "%");
		} else {
			hql.append("from AppUser vo2 where 1=1 ");
		}
		if (st != "") {
			hql.append(" and vo2.userId not in (" + st + ")");
		}
		hql.append(" and vo2.delFlag = ?");
		list.add(Constants.FLAG_UNDELETED);
		return findByHql(hql.toString(), list.toArray(), pb);
	}

	@Override
	public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds,
			PagingBean pb) {
		String st = "";
		if (userIds.size() > 0) {
			Iterator<Long> it = userIds.iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext()) {
				sb.append(it.next().toString() + ",");
			}
			sb.deleteCharAt(sb.length() - 1);
			st = sb.toString();
		}
		StringBuffer hql = new StringBuffer(
				"select vo from AppUser vo join vo.roles roles where roles.roleId=?");
		List list = new ArrayList();
		list.add(roleId);
		if (st != "") {
			hql.append(" and vo.userId not in (" + st + ")");
		}
		hql.append(" and vo.delFlag =?");
		list.add(Constants.FLAG_UNDELETED);
		return findByHql(hql.toString(), list.toArray(), pb);
	}

	@Override
	public List<AppUser> findByDepId(Long depId) {
		String hql = "from AppUser vo where vo.delFlag=0 and vo.department.depId=?";
		Object[] objs = { depId };
		return findByHql(hql, objs);
	}

	/**
	 * 查找某组角色列表下所有的用户
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<AppUser> findUsersByRoleIds(String roleIds) {
		if (StringUtils.isEmpty(roleIds)) {
			return new ArrayList();
		}
		String hql = "select distinct au from AppUser as au join au.roles as roles where roles.roleId in ("
				+ roleIds + ") and au.delFlag =?";

		return findByHql(hql, new Object[] { Constants.FLAG_UNDELETED });
	}

	/**
	 * @description 根据当前用户岗位取得下属岗位的用户
	 * @return List<AppUser>
	 */
	public List<AppUser> findRelativeUsersByUserId() {

		// 按Position取下属
		StringBuffer sb = new StringBuffer(
				"select up from UserPosition up where up.appUser.userId = ? ");

		Query query = getSession().createQuery(sb.toString());
		query.setLong(0, ContextUtil.getCurrentUserId());
		List<UserPosition> uplist = query.list();
		List<Position> plist = new ArrayList();
		for (UserPosition up : uplist) {

			List<Position> tlist = (List) findByHql("select p from Position p where p.posId = "
					+ up.getPosition().getPosId());

			Set<Position> mainPosSub = tlist.get(0) == null ? null : tlist.get(
					0).getMainPositionSubs();
			Set<Position> subPosSub = tlist.get(0) == null ? null : tlist
					.get(0).getSubPositionSubs();
			if (mainPosSub != null && subPosSub != null) {
				tlist.addAll(mainPosSub);
				tlist.addAll(subPosSub);
			}

			for (Position pos : tlist) {
				if (!plist.contains(pos)) {
					plist.add(pos);
				}
			}
		}

		String paths = "";
		if (plist.size() > 0) {
			for (Position pos : plist) {
				if (paths.length() > 0)
					paths += ",";
				paths += pos.getPath();
			}
		}

		// 取得路径对应posId
		sb = new StringBuffer("select p from Position p ");
		String[] pths = paths.split(",");
		for (int index = 0; index < pths.length; index++) {
			if (index == 0) {
				sb.append("where (p.path like '" + pths[0]
						+ "%' and p.path <> '" + pths[0] + "') ");
			} else {
				sb.append("or (p.path like '" + pths[index]
						+ "%' and p.path <> '" + pths[index] + "') ");
			}
		}

		// 得到对应用户
		String pathHql = sb.toString();
		sb = new StringBuffer(
				"select distinct au from AppUser au,UserPosition up "
						+ "where au.userId = up.appUser.userId "
						+ "and up.position.posId in (" + pathHql + ") "
						+ "and au.delFlag = 0");

		List<AppUser> users = findByHql(sb.toString());

		// 按Relative_job取下属
		sb = new StringBuffer(
				"select distinct au0 from AppUser au0 where au0.userId "
						+ "in (select ru.jobUser.userId from AppUser au, RelativeUser ru "
						+ "where au.userId = ? and au.delFlag = 0 "
						+ "and au.userId = ru.appUser.userId) ");

		users.addAll(findByHql(sb.toString(),
				new Object[] { ContextUtil.getCurrentUserId() }));

		return users;
	}

	@Override
	public List<AppUser> findByDepartment(String path, String userIds,
			PagingBean pb) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("");
		if ("0.".equals(path)) {
			hql.append("from AppUser vo2 where vo2.delFlag = ? ");
			list.add(Constants.FLAG_UNDELETED);
		} else {
			hql.append("select DISTINCT vo2 from Department vo1,AppUser vo2,DepUsers vo3 where 1=1"
					+ " and vo3.appUser=vo2"
					+ " and vo3.department=vo1"
					+ " and vo1.path like ? and vo2.delFlag = ? ");
			list.add(path + "%");
			list.add(Constants.FLAG_UNDELETED);
		}
		// 删除userIds中的数据
		if (userIds != null && !userIds.equals("")) {
			hql.append("and vo2.userId in (?) ");
			list.add(userIds);
		}
		hql.append("order by vo3.sn "); // 排序
		logger.debug("自定义AppUserDaoImpl : " + hql.toString());
		return findByHql(hql.toString(), list.toArray(), pb);
	}

	/**
	 * 按角色取得用户列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<AppUser> getUsersByRoleId(Long roleId) {
		String hql = "from AppUser au join au.roles as role where role.roleId=?";
		return (List<AppUser>) findByHql(hql, new Object[] { roleId });
	}

	/**
	 * 按部门取得用户列表
	 * 
	 * @param orgPath
	 * @return
	 */
	@Override
	public List<AppUser> getDepUsers(String orgPath, PagingBean pb, Map map) {

		String hql = "from AppUser au where au.delFlag=0 ";
		if (!"0.".equals(orgPath)) {
			hql = "select distinct uo.appUser from UserOrg uo "
					+ "where uo.organization.path like ? and uo.appUser.delFlag = 0 ";

			if (map != null) {
				if (StringUtils.isNotEmpty(map.get("username").toString())) {
					hql += "and uo.appUser.username like '%"
							+ map.get("username").toString().trim() + "%' ";
				}
				if (StringUtils.isNotEmpty(map.get("fullname").toString())) {
					hql += "and uo.appUser.fullname like '%"
							+ map.get("fullname").toString().trim() + "%' ";
				}
			}

			if (pb != null) {
				return findByHql(hql, new Object[] { orgPath + "%" }, pb);
			} else {
				return findByHql(hql, new Object[] {orgPath});
			}
		}

		if (map != null) {
			if (StringUtils.isNotEmpty(map.get("username").toString())) {
				hql += "and au.username like '%"
						+ map.get("username").toString().trim() + "%' ";
			}
			if (StringUtils.isNotEmpty(map.get("fullname").toString())) {
				hql += "and au.fullname like '%"
						+ map.get("fullname").toString().trim() + "%' ";
			}
		}
		if (pb != null) {
			return findByHql(hql, new Object[] {}, pb);
		} else {
			return findByHql(hql, new Object[] {});
		}
	}

	/**
	 * 取得相对岗位用户列表
	 * 
	 * @param reJobId
	 * @param startUserId
	 *            流程发起人
	 * @return
	 */
	public List<AppUser> getReLevelUser(String reJobId, Long startUserId) {

		// 取得当前用户所有岗位
		// Long userId = ContextUtil.getCurrentUserId();

		List<Position> plist = new ArrayList<Position>();

		String hql = "select distinct p from UserPosition up, Position p "
				+ "where up.position.posId = p.posId "
				+ "and up.appUser.userId = ? ";

		Query query = getSession().createQuery(hql);
		query.setLong(0, startUserId);
		List<Position> plist3 = query.list();

		if (plist3 != null && plist3.size() > 0) {
			plist.addAll(plist3);
		}

		// 如果取下x级岗位，则把已关联的同级岗位也计算
		if (Integer.parseInt(reJobId) < 0) {
			if (plist != null && plist.size() > 0) {
				for (Position p : plist3) {

					List<Position> tlist = (List) findByHql("select p from Position p where p.posId = "
							+ p.getPosId());

					Set<Position> mainPosSub = tlist.get(0) == null ? null
							: tlist.get(0).getMainPositionSubs();
					Set<Position> subPosSub = tlist.get(0) == null ? null
							: tlist.get(0).getSubPositionSubs();
					if (mainPosSub != null && subPosSub != null) {
						mainPosSub.addAll(subPosSub);
					}

					if (mainPosSub != null && mainPosSub.size() > 0) {
						for (Position pos : mainPosSub) {
							if (!plist.contains(pos)) {
								plist.add(pos);
							}
						}
					}

				}
			}
		}

		// 拼接路径
		int tmpReJobId = Integer.parseInt(reJobId) * (-1);
		String rePath = "";
		int ltFlag = 0;
		if (plist != null && plist.size() > 0) {
			for (int index = 0; index < plist.size(); index++) {
				String path = ((Position) plist.get(index)).getPath();

				String[] curLvArr = path.substring(0, path.length() - 1).split(
						"\\.");
				int curLevel = curLvArr.length;
				int aftLevel = curLevel + tmpReJobId;
				if (aftLevel < curLevel) {
					for (int index0 = 0; index0 < aftLevel; index0++) {
						rePath += curLvArr[index0] + ".";
					}
				} else if (aftLevel > curLevel) {
					rePath += path;
					for (int index0 = 0; index0 < aftLevel - curLevel; index0++) {
						rePath += "%.";
					}
				} else {
					rePath += path;
				}
				if (index == 0) {
					ltFlag = aftLevel - curLevel;
				}
				rePath += ",";
			}
		}

		// 取得路径对应posId
		StringBuffer sb = null;
		String[] pths = rePath.length() == 0 ? new String[0] : rePath
				.substring(0, rePath.length() - 1).split(",");
		if (ltFlag > 0) {

			sb = new StringBuffer("select p from Position p ");
			for (int index = 0; index < pths.length; index++) {
				query = getSession().createQuery(
						"select p from Position p where p.path like '"
								+ pths[index] + "' ");
				plist = query.list();
				int matchFlag = pths[index].split("\\.").length;
				if (plist != null && plist.size() > 0) {
					for (int index1 = 0; index1 < plist.size(); index1++) {
						Position p = plist.get(index1);
						if (matchFlag == p.getPath().split("\\.").length) {
							if (sb.toString().indexOf("where") == -1) {
								sb.append("where p.path = '" + p.getPath()
										+ "' ");
							} else {
								sb.append("or p.path = '" + p.getPath() + "' ");
							}
						}
					}
				}
			}
		} else {
			sb = new StringBuffer(
					"select p from Position p where p.posSupId in "
							+ "(select p0.posSupId from Position p0 ");
			for (int index = 0; index < pths.length; index++) {
				if (index == 0) {
					sb.append("where p0.path = '" + pths[0] + "' ");
				} else {
					sb.append("or p0.path = '" + pths[index] + "' ");
				}
			}
			sb.append(")");
		}

		List<Position> tplist = (List) findByHql(sb.toString());
		if (!"0".equals(reJobId)) {
			plist.clear();
			for (Position pos : tplist) {
				plist.add(pos);
			}
			for (Position pos : plist) {
				tplist.addAll(pos.getMainPositionSubs());
				tplist.addAll(pos.getSubPositionSubs());
				boolean flag = false;
				if (pos.getMainPositionSubs().size() == 0
						&& pos.getSubPositionSubs().size() == 0) {
					for (int index = 0; index < pths.length; index++) {
						if (pos.getPath().indexOf(pths[index].split("%")[0]) != -1) {
							flag = true;
							break;
						}
					}
					if (!flag) {
						tplist.remove(pos);
					}
				}
			}
		}

		List<AppUser> list = new ArrayList<AppUser>();
		for (Position pos : tplist) {
			Set<UserPosition> upSet = pos.getUserPositions();
			Iterator<UserPosition> upIt = upSet.iterator();
			while (upIt.hasNext()) {
				UserPosition up = upIt.next();
				AppUser au = up.getAppUser();
				if (!list.contains(au))
					list.add(au);
			}
		}

		if ("0".equals(reJobId) && list.size() == 0) {
			list.add(ContextUtil.getCurrentUser());
		}

		return list;
	}

	/**
	 * 取得组织主要负责人
	 * 
	 * @param userOrg
	 * @return
	 */
	@Override
	public List<AppUser> getChargeOrgUsers(Set userOrgs) {
		String isChargeUser = "";
		Iterator<UserOrg> it = userOrgs.iterator();
		while (it.hasNext()) {
			UserOrg userOrg = it.next();
			if (userOrg.getIsCharge() != null && userOrg.getIsCharge() == 1) {
				isChargeUser += userOrg.getUserid() + ",";
			}
		}
		isChargeUser = isChargeUser.length() == 0 ? "0" : isChargeUser
				.substring(0, isChargeUser.length() - 1);
		String hql = "from AppUser au where au.userId in (" + isChargeUser
				+ ") and au.delFlag=0";
		return findByHql(hql);
	}

	/**
	 * 判断是否为超级管理员
	 * 
	 * @param userId
	 *            用户id
	 * @return 超级管理员true
	 */
	@Override
	public Boolean isSuperMan(Long userId) {
		AppUser appUser = get(userId);
		if (appUser != null && appUser.getRoles() != null
				&& appUser.getRoles().size() > 0) {
			for (AppRole role : appUser.getRoles()) {
				if (role != null
						&& (role.getRoleName().equals("超级管理员")
								|| role.getRoleName().equals("管理员") || role
								.getRights().equals("__ALL"))) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断是否有下属用户
	 * 
	 * @param userId
	 *            用户id
	 * @return true
	 */
	@Override
	public List<AppUser> findRelativeUsersByFullname(String fullname,
			Long userId) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer sb = new StringBuffer(
				"select  u.jobUser from Subordinate u where u.relative = 0 ");
		if (userId != 0) {
			sb.append("and u.appUser.userId = ? ");
			paramList.add(userId);
		}
		if (StringUtils.isNotEmpty(fullname)) {
			sb.append("and u.jobUser.fullname like ?");
			paramList.add("%" + fullname + "%");
		}
		return findByHql(sb.toString(), paramList.toArray());
	}
}
