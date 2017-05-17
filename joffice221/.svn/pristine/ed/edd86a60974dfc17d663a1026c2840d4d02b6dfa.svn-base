package com.htsoft.oa.service.flow;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.system.AppUser;

public interface ProDefinitionService extends BaseService<ProDefinition> {
	public ProDefinition getByDeployId(String deployId);

	public ProDefinition getByName(String name);

	public List<ProDefinition> getByRights(AppUser curUser,
			ProDefinition proDefinition, QueryFilter filter);

	/**
	 * 返回true则通过,false则重名
	 * 
	 * @param proDefinition
	 * @return
	 */
	public boolean checkNameByVo(ProDefinition proDefinition);

	/**
	 * 返回true则通过,false则重名
	 * 
	 * @param proDefinition
	 * @return
	 */
	public boolean checkProcessNameByVo(ProDefinition proDefinition);

	/**
	 * 返回是否defkey是否存在
	 * @param defKey
	 * @return
	 */
	public boolean checkDefKeyIfExist(String defKey );
	// 根据流程实现运行状态来查找流程
	public List<ProDefinition> findRunningPro(ProDefinition proDefinition,
			Short runstate, PagingBean pb);
	
	/**
	 * @description 保存流程信息,包含流程发布
	 * @param proDefinition ProDefinition
	 * @param deploy true:保存，并发布;false:只保存
	 * @return 数据操作信息
	 */
	String defSave(ProDefinition proDefinition,Boolean deploy) throws Exception;
	
	/**
	 * 更新从版本的parnentId及isMain属性
	 * @param parentId 父ID
	 * @param defKey 定义Key
	 * @param maxVersionNo 最大版本号
	 */
	public void updateSubVersion(Long parentId,String defKey,Integer maxVersionNo);
	
	/**
	 * 保存及更新流程定义
	 * @param proDefinition
	 * @param isDeploy
	 */
	public ProDefinition saveOrUpdate(ProDefinition proDefinition,boolean isDeploy) throws Exception;

	/**
	 * 编辑当前版本
	 * @param proDefinition
	 * @param isEdit
	 * @return
	 * @throws Exception
	 */
	public ProDefinition update(ProDefinition proDefinition,boolean isEdit)throws Exception;
	
	/**
	 * 历史版本分页
	 * @param parentId
	 * @param pb
	 * @return
	 */
	public List<ProDefinition> findAllByParentId(Long parentId, PagingBean pb);
	
	public List<ProDefinition> findByRights(AppUser curUser,String processName,String description, PagingBean pb);
	
}
