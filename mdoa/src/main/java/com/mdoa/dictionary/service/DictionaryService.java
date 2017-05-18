package com.mdoa.dictionary.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.mdoa.base.service.BaseService;
import com.mdoa.dictionary.dao.DictionaryDao;
import com.mdoa.dictionary.model.Dictionary;
import com.mdoa.dictionary.model.DictionaryModelConstant;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.JSONUtil;
import com.mdoa.util.PageModel;

@Service
@Transactional
public class DictionaryService extends BaseService{
	
	@Autowired
	private DictionaryDao dictionaryDao;
	
	/**
	 * 查询信息(数据类型)
	 * @param dictionary
	 * @return
	 */
	public PageModel<Dictionary> queryDictionaryType(Dictionary dictionary) {
		
		PageModel<Dictionary> pageModel = new PageModel<>();
		pageModel.setRows(dictionaryDao.queryDictionaryType(dictionary));
		return pageModel;
	}
	/**
	 * 查询信息(option)
	 * @param dictionary
	 * @return
	 */
	public PageModel<Dictionary> queryDictionary(Dictionary dictionary) {
		PageModel<Dictionary> pageModel = new PageModel<>();
		List<Dictionary> list = dictionaryDao.queryDictionary(dictionary);
		pageModel.setRows(list);
		if (DictionaryModelConstant.dictionaryMap == null) {
			DictionaryModelConstant.dictionaryMap = new HashMap<>();
			achieveSingleMap(DictionaryModelConstant.dictionaryMap,list);
		}
		return pageModel;
	}
	
	private void achieveSingleMap(HashMap<String, Map<String, String>> dictionaryMap, List<Dictionary> list) {
		
		for (int i = 0; i < list.size(); i++) {
			Map<String, String> map = new HashMap<>();
			map.put("g", "g");
			dictionaryMap.put(list.get(i).getSelectKey(), map);
		}
		for (int i = 0; i < list.size(); i++) {
			Iterator<Entry<String, Map<String, String>>> it = dictionaryMap.entrySet().iterator();
			while(it.hasNext()){
				  Entry<String, Map<String, String>> entry = it.next();
				  if (list.get(i).getSelectKey().equals(entry.getKey())) {
						dictionaryMap.get(list.get(i).getSelectKey()).put(list.get(i).getOptionKey(), list.get(i).getOptionValue());
					}	
				}
			
			}
			
		//DictionaryModelConstant.dictionaryMap = dictionaryMap;
	}
	/**
	 * 删除信息
	 * @param dictionary
	 */
	public void deleteDictionary(Dictionary dictionary) {
		DictionaryModelConstant.dictionaryMap.get(dictionary.getSelectKey()).remove(dictionary.getOptionKey());
		if (!dictionaryDao.deleteDictionary(dictionary)) 
			throw new RuntimeException("删除数据字典记录失败!");
	}

	/**
	 * 修改信息
	 * @param dictionary
	 */
	public void updateDictionary(Dictionary dictionary, HttpServletRequest request) {
		DictionaryModelConstant.dictionaryMap.get(dictionary.getSelectKey()).put(dictionary.getOptionKey(), dictionary.getOptionValue());
		
		UserInfo userInfo = getUser(request);
		if (!dictionaryDao.updateDictionary(dictionary)) 
			throw new RuntimeException("修改数据字典记录失败");
		if (dictionary.getIsDefault() != null && dictionary.getIsDefault() != "") {
			Dictionary dictionary2 = new Dictionary();
			dictionary2.setSelectKey(dictionary.getSelectKey());
			List<Dictionary> list = dictionaryDao.queryDictionary(dictionary2);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getIsDefault().equals("1") && !list.get(i).getDictionaryId().equals(dictionary.getDictionaryId())) {
					dictionary2.setUpdateUserId(userInfo.getUserId());
					dictionary2.setUpdateUserName(userInfo.getUserName());
					dictionary2.setIsDefault("0");
					dictionary2.setDictionaryId(list.get(i).getDictionaryId());
					dictionaryDao.updateDictionary(dictionary2);
				}
			}
		}
	}
	/**
	 * 添加信息
	 * @param dictionary
	 */
	public void addDictionary(Dictionary dictionary) {
		DictionaryModelConstant.dictionaryMap.get(dictionary.getSelectKey()).put(dictionary.getOptionKey(), dictionary.getOptionValue());
		
		String selectKey = dictionary.getSelectKey();
		Integer lastOrderCode = dictionaryDao.findLastOrderCodeBySelectKey(selectKey);
		if(lastOrderCode == null)
			throw new RuntimeException("SelectKey异常");
		dictionary.setOrderCode(lastOrderCode + 1);
		if (!dictionaryDao.addDictionary(dictionary))
			throw new RuntimeException("添加信息失败");
	}
	
	/**
	 * 修改数据项顺序
	 * @param jsonString
	 */
	public void updateDictionaryOrder(String jsonString, HttpServletRequest request) {
		List<Dictionary> dictionaries = JSONUtil.<Dictionary>jsonToList(jsonString,Dictionary[].class);
		UserInfo userInfo = getUser(request);
		for(int i = 0;i<dictionaries.size();i++){
			Dictionary dictionary = dictionaries.get(i);
			dictionary.setUpdateUserId(userInfo.getUserId());
			dictionary.setUpdateUserName(userInfo.getUserName());
			System.out.println(dictionary.getUpdateUserId());
			System.out.println(dictionary.getUpdateUserName());
			if(!dictionaryDao.updateDictionary(dictionary))
				throw new RuntimeException("修改数据项顺序失败！");
		}
	}
	
	
}
