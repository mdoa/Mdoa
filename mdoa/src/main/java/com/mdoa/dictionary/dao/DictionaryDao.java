package com.mdoa.dictionary.dao;

import java.util.HashMap;
import java.util.List;

import com.mdoa.dictionary.model.Dictionary;

public interface DictionaryDao {

	
	/**
	 * 查询信息(数据类型)
	 * @param dictionary
	 * @return
	 */
	List<Dictionary> queryDictionaryType(Dictionary dictionary);
	/**
	 * 查询信息(option)
	 * @param dictionary
	 * @return
	 */
	List<Dictionary> queryDictionary(Dictionary dictionary);
	/**
	 * 删除信息
	 * @param dictionary
	 * @return
	 */
	boolean deleteDictionary(Dictionary dictionary);
	/**
	 * 修改信息
	 * @param dictionary
	 * @return
	 */
	boolean updateDictionary(Dictionary dictionary);
	/**
	 * 添加信息
	 * @param dictionary
	 * @return
	 */
	boolean addDictionary(Dictionary dictionary);
	
	/**
	 * 查询数据字典某项的当前最大order_code
	 * @param selectKey
	 * @return
	 */
	Integer findLastOrderCodeBySelectKey(String selectKey);
}
