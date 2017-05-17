package com.htsoft.oa.action.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.Dictionary;
import com.htsoft.oa.model.system.GlobalType;
import com.htsoft.oa.service.system.DictionaryService;
import com.htsoft.oa.service.system.GlobalTypeService;

/**
 * 
 * @author
 * 
 */
public class DictionaryAction extends BaseAction {
	@Resource
	private DictionaryService dictionaryService;
	@Resource
	private GlobalTypeService globalTypeService;

	private Dictionary dictionary;

	private Long dicId;

	private String itemName;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getDicId() {
		return dicId;
	}

	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public String mulSave() {
		String data = getRequest().getParameter("data");

		if (StringUtils.isNotEmpty(data)) {

			JacksonMapper mapper = new JacksonMapper(true);
			Dictionary[] dics = mapper.toObject(data, Dictionary[].class);

			for (int i = 0; i < dics.length; i++) {
				Dictionary dic = dictionaryService.get(dics[i].getDicId());
				try {
					BeanUtil.copyNotNullProperties(dic, dics[i]);

					dictionaryService.save(dic);
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
				;
			}
		}

		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		String sParentId = getRequest().getParameter("parentId");
		if (StringUtils.isNotEmpty(sParentId) && !"0".equals(sParentId)) {
			GlobalType globalType = globalTypeService.get(new Long(sParentId));
			filter.addFilter("Q_globalType.path_S_LFK", globalType.getPath());
		}
		List<Dictionary> list = dictionaryService.getAll(filter);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;
	}

	/**
	 * 根据条目查询出字典的value并返回数组
	 * 
	 * @return
	 */
	public String load() {
		List<String> list = dictionaryService.getAllByItemName(itemName);
		StringBuffer buff = new StringBuffer("[");
		for (String itemName : list) {
			buff.append("'").append(itemName).append("',");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return SUCCESS;
	}

	/**
	 * 通过nodeKey 获得数据字典
	 * 
	 * @return
	 */
	public String loadItem() {
		
		String nodeKey = getRequest().getParameter("nodeKey");
		String proTypeId = getRequest().getParameter("proTypeId");
		List<Dictionary> list = new ArrayList<Dictionary>();
		if(StringUtil.isNotEmpty(nodeKey)){
			list = dictionaryService.getByNodeKey(nodeKey);
		}else if(StringUtil.isNotEmpty(proTypeId)){
			list = dictionaryService.getByProTypeId(new Long(proTypeId));
		}


		StringBuffer buff = new StringBuffer("[");
		for (Dictionary dic : list) {
			buff.append("[").append(dic.getDicId()).append(",'")
					.append(dic.getItemValue()).append("'],");

		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return SUCCESS;
	}

	public String loadItemRecord() {
		List<Dictionary> list = dictionaryService.getByItemName(itemName);
		JacksonMapper mapper = new JacksonMapper(true);
		jsonString = mapper.toDataJson(list);
		return SUCCESS;
	}

	public String typeChange() {
		String dicIds = getRequest().getParameter("dicIds");
		String dicTypeId = getRequest().getParameter("dicTypeId");

		if (StringUtils.isNotEmpty(dicIds) && StringUtils.isNotEmpty(dicTypeId)) {
			GlobalType globalType = globalTypeService.get(new Long(dicTypeId));

			String[] ids = dicIds.split("[,]");
			if (ids != null) {
				for (String id : ids) {
					Dictionary dic = dictionaryService.get(new Long(id));
					dic.setGlobalType(globalType);
					dic.setItemName(globalType.getTypeName());

					dictionaryService.save(dic);
				}
			}
		}
		setJsonString("{success:true}");
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
			for (String id : ids) {
				dictionaryService.remove(new Long(id));
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
	public String get() {
		Dictionary dictionary = dictionaryService.get(dicId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(dictionary);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {

		if (dictionary.getDicId() != null) {
			Dictionary orgDic = dictionaryService.get(dictionary.getDicId());
			try {
				BeanUtil.copyNotNullProperties(orgDic, dictionary);
				dictionaryService.save(orgDic);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		} else {
			String parentId = getRequest().getParameter("parentId");
			if (StringUtils.isNotEmpty(parentId)) {
				GlobalType globalType = globalTypeService
						.get(new Long(parentId));
				dictionary.setGlobalType(globalType);
				List<Dictionary> list = dictionaryService.getByProTypeId(new Long(parentId));
				dictionary.setSn((short) (list.size()+1));
			}
			
			dictionaryService.save(dictionary);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 获得条目
	 * 
	 * @return
	 */
	public String items() {
		List<String> list = dictionaryService.getAllItems();
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, list.size());
		return SUCCESS;
	}
}
