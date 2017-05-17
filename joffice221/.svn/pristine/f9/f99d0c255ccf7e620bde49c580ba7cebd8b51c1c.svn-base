package com.htsoft.oa.dao.flow.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.flow.ProDefinitionDao;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.system.GlobalTypeService;

public class ProDefinitionDaoImpl extends BaseDaoImpl<ProDefinition> implements
		ProDefinitionDao {

	@Resource
	private GlobalTypeService globalTypeService;

	public ProDefinitionDaoImpl() {
		super(ProDefinition.class);
	}

	public ProDefinition getByDeployId(String deployId) {
		String hql = "from ProDefinition pd where pd.deployId=?";
		return (ProDefinition) findUnique(hql, new Object[] { deployId });
	}

	public ProDefinition getByName(String name) {
		String hql = "from ProDefinition pd where pd.name=?";
		return (ProDefinition) findUnique(hql, new Object[] { name });
	}

	@Override
	public List<ProDefinition> getByRights(AppUser curUser,
			ProDefinition proDefinition, QueryFilter filter) {
		String uIds = "%," + curUser.getUserId() + ",%";
		String dIds = "%," + curUser.getDepartment().getDepId() + ",%";

		List params = new ArrayList();
		StringBuffer hql = new StringBuffer(
				"select pd from ProDefRights pr right join pr.proDefinition pd  where 1=1 ");

		if (proDefinition != null && proDefinition.getName() != null) {
			hql.append("and pd.name like = ?");
			params.add("%" + proDefinition.getName() + "%");
		}

		hql.append("and (pr.userIds like ?  or pr.depIds like ? ");
		params.add(uIds);
		params.add(dIds);

		Set<AppRole> roles = curUser.getRoles();
		for (Iterator it = roles.iterator(); it.hasNext();) {
			AppRole role = (AppRole) it.next();
			hql.append("or pr.roleIds like ? ");
			String rIds = "%," + role.getRoleId() + ",%";
			params.add(rIds);
		}

		hql.append(")");

		List<ProDefinition> result = findByHql(hql.toString(), params.toArray());
		filter.getPagingBean().setTotalItems(result.size());
		return result;
	}

	@Override
	public boolean checkNameByVo(ProDefinition proDefinition) {

		StringBuffer hql = new StringBuffer(
				"from ProDefinition pd where pd.name = ? and parentId is null ");

		List params = new ArrayList();
		params.add(proDefinition.getName());
		if (proDefinition.getDefId() != null) {
			hql.append(" and pd.defId != ?");
			params.add(proDefinition.getDefId());
		}
		List result = findByHql(hql.toString(), params.toArray());

		return !(result.size() > 0);

	}
	
	@Override
	public boolean checkDefKeyIfExist(String defKey ) {

		StringBuffer hql = new StringBuffer(
				"from ProDefinition pd where pd.defKey = ? and pd.isMain=1 ");

		List params = new ArrayList();
		params.add(defKey);
		List result = findByHql(hql.toString(), params.toArray());

		return (result.size() > 0);
	}


	@Override
	public boolean checkProcessNameByVo(ProDefinition proDefinition) {

		StringBuffer hql = new StringBuffer(
				"from ProDefinition pd where pd.processName = ? and parentId is null ");
		List params = new ArrayList();
		params.add(proDefinition.getProcessName());
		if (proDefinition.getDefId() != null) {
			hql.append(" and pd.defId != ?");
			params.add(proDefinition.getDefId());
		}
		List result = findByHql(hql.toString(), params.toArray());

		return !(result.size() > 0);
	}

	/**
	 * 按Ids 来取到所有的列表
	 * 
	 * @param docIds
	 * @return
	 */
	private List<ProDefinition> getByIds(List defIds) {

		String docHql = "from ProDefinition pd where pd.defId in (";
		StringBuffer sbIds = new StringBuffer();

		for (int i = 0; i < defIds.size(); i++) {
			sbIds.append(defIds.get(i).toString()).append(",");
		}

		if (defIds.size() > 0) {
			sbIds.deleteCharAt(sbIds.length() - 1);
			docHql += sbIds.toString() + ")";
			return (List<ProDefinition>) findByHql(docHql);
		} else {
			return new ArrayList();
		}
	}

	@Override
	public List<ProDefinition> findRunningPro(ProDefinition proDefinition,
			Short runstate, PagingBean pb) {
		StringBuffer hql = new StringBuffer(
				"select distinct pd.defId from ProcessRun pr join pr.proDefinition pd  where pr.runStatus=? ");
		List params = new ArrayList();
		params.add(runstate);
		if (proDefinition != null
				&& StringUtils.isNotEmpty(proDefinition.getName())) {
			hql.append(" and pd.name like ?");
			params.add("%" + proDefinition.getName() + "%");
		}
		hql.append(" order by pd.defId desc");

		List<Long> defIds = (List<Long>) find(hql.toString(), params.toArray(), pb);

		return getByIds(defIds);
	}

	/**
	 * 更新从版本的parnentId及isMain属性
	 * 
	 * @param parentId
	 *            父ID
	 * @param defKey
	 *            定义Key
	 * @param maxVersionNo
	 *            最大版本号
	 */
	public void updateSubVersion(Long parentId, String defKey,
			Integer maxVersionNo) {
		String sql = "update pro_definition set parentId=? ,isMain=? where defKey=? and newVersion<?";
		this.jdbcTemplate.update(sql, new Object[] { parentId,
				ProDefinition.NOT_MAIN, defKey, maxVersionNo });
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProDefinition> findAllByParentId(Long parentId, PagingBean pb) {
		StringBuffer hql = new StringBuffer(
				" from ProDefinition pd  where pd.parentId= ?");
		List params = new ArrayList();
		params.add(parentId);
		return (List<ProDefinition>) find(hql.toString(), params.toArray(), pb);
	}

	@Override
	public List<ProDefinition> findByRights(AppUser curUser,String processName,String description, PagingBean pb) {
		String uIds = "%,"+curUser.getUserId()+",%";
		String dIds = "%,"+curUser.getDepartment().getDepId()+",%";
		
		List params = new ArrayList();
		StringBuffer hql =new StringBuffer("select pd from ProDefRights pr right join pr.proDefinition pd  where 1=1 ");
		
		if(StringUtils.isNotEmpty(processName)){
			hql.append("and pd.name like ?");
			params.add("%"+processName+"%");
		}
		if(StringUtils.isNotEmpty(description)){
			hql.append("and pd.description like ?");
			params.add("%"+description+"%");
		}
		
		hql.append("and (pr.userIds like ?  or pr.depIds like ? ");
		params.add(uIds);
		params.add(dIds);
		
		Set<AppRole> roles = curUser.getRoles();
		for(Iterator it = roles.iterator();it.hasNext();){
			AppRole role = (AppRole)it.next();
			hql.append("or pr.roleIds like ? ");
			String rIds = "%,"+role.getRoleId()+",%";
			params.add(rIds);
		}
		
		hql.append(")");
		hql.append(" order by pd.defId desc");
				
		return findByHql(hql.toString(),params.toArray(),pb);
	}
}