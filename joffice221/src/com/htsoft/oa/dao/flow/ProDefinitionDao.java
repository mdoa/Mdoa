package com.htsoft.oa.dao.flow;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.system.AppUser;
/**
 * @author
 */
public interface ProDefinitionDao extends BaseDao<ProDefinition> {
	/**
	 * 按发布ID取得XML
	 * 
	 * @param deployId
	 * @return
	 */
	public ProDefinition getByDeployId(String deployId);
	/**
	 * get by name
	 * @param name
	 * @return
	 */
	public ProDefinition getByName(String name);

	public List<ProDefinition> getByRights(AppUser curUser,ProDefinition proDefinition, QueryFilter filter);

	public boolean checkNameByVo(ProDefinition proDefinition);
	
	/**
	 * 返回是否defkey是否存在
	 * @param defKey
	 * @return
	 */
	public boolean checkDefKeyIfExist(String defKey );

	public boolean checkProcessNameByVo(ProDefinition proDefinition);
	//根据流程实现运行状态来查找流程
	public List<ProDefinition> findRunningPro(ProDefinition proDefinition,Short runstate,PagingBean pb);
	/**
	 * 更新从版本的parnentId及isMain属性
	 * @param parentId 父ID
	 * @param defKey 定义Key
	 * @param maxVersionNo 最大版本号
	 */
	public void updateSubVersion(Long parentId,String defKey,Integer maxVersionNo);
	
	/**
	 * 历史版本分页
	 * @param parentId
	 * @param pb
	 * @return
	 */
	public List<ProDefinition> findAllByParentId(Long parentId, PagingBean pb);
	
	public List<ProDefinition> findByRights(AppUser curUser,String processName,String description, PagingBean pb);
}