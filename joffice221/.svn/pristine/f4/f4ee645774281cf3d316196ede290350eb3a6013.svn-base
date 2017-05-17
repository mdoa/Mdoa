package com.htsoft.oa.service.system;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Dictionary;

public interface DictionaryService extends BaseService<Dictionary> {

	public List<String> getAllItems();

	public List<String> getAllByItemName(String itemName);

	public List<Dictionary> getByItemName(final String itemName);

	/**
	 * 根据nodeKey获取字典。
	 * 
	 * @param nodeKey
	 * @return
	 */
	public List<Dictionary> getByNodeKey(String nodeKey);

	/**
	 * 根据分类ID获取字典。
	 * 
	 * @param nodeKey
	 * @return
	 */
	public List<Dictionary> getByProTypeId(Long proTypeId);

}
