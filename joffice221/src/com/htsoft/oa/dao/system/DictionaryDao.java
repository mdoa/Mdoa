package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.Dictionary;

/**
 * 
 * @author
 * 
 */
public interface DictionaryDao extends BaseDao<Dictionary> {

	public List<String> getAllItems();

	public List<String> getAllByItemName(String itemName);

	public List<Dictionary> getByItemName(final String itemName);

	/**
	 * 根据nodeKey获取字典。
	 * 
	 * @param nodeKey
	 * @return
	 */
	public List<Dictionary>  getByNodeKey(String nodeKey);
	/**
	 * 根据分类ID获取字典。
	 * 
	 * @param proTypeId
	 * @return
	 */
	public List<Dictionary> getByProTypeId(Long proTypeId);

}