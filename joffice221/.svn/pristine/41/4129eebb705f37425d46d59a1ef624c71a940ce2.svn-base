package com.htsoft.test.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.oa.dao.system.GlobalTypeDao;
import com.htsoft.oa.model.system.GlobalType;
import com.htsoft.test.BaseTestCase;

public class GlobalTypeDaoTestCase extends BaseTestCase {

	@Resource
	private GlobalTypeDao globalTypeDao;

	// @Test
	// @Rollback(false)
	public void get() {
		String catKey = "PWP";
		List<GlobalType> list = globalTypeDao.getByParentIdCatKey(0L, catKey);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> children = new ArrayList<Object>();
		if (list != null && list.size() > 0) {
			for (GlobalType globalType : list) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("id", globalType.getProTypeId());
				map1.put("text", globalType.getTypeName());
				map1.put("expanded", true);
				List<Object> children1 = new ArrayList<Object>();
				children1 = getChildType(globalType.getProTypeId(), catKey);
				if (children1 != null && children1.size() > 0) {
					map1.put("children", children1);
				} else {
					map1.put("leaf", true);
				}
				children.add(map1);
			}
		}

		map.put("id", 0);
		map.put("text", "总分类");
		if (children != null && children.size() > 0) {
			map.put("expanded", true);
			map.put("children", children);
		}
		System.out.println(map);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		String jsonString = mapper.toJson(map);
		System.err.println(jsonString);
	}

	public List<Object> getChildType(Long parentId, String catKey) {
		List<GlobalType> list = globalTypeDao.getByParentIdCatKey(parentId,
				catKey);
		List<Object> children = new ArrayList<Object>();
		if (list != null && list.size() > 0) {
			for (GlobalType globalType : list) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", globalType.getProTypeId());
				map.put("text", globalType.getTypeName());
				map.put("expanded", true);
				List<Object> children1 = new ArrayList<Object>();
				children1 = getChildType(globalType.getProTypeId(), catKey);
				if (children1 != null && children1.size() > 0) {
					map.put("children", children1);
				} else {
					map.put("leaf", true);
				}
				children.add(map);
			}
		}
		return children;
	}

	public List<Map<String, Object>> buildTree() {
		List<Map<String, Object>> reslut = new ArrayList<Map<String, Object>>();

		// String catKey = "REGULATION";
		String catKey = "AR_FD";
		List<GlobalType> list = globalTypeDao.getByCatKey(catKey);
		for (GlobalType globalType : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", globalType.getProTypeId());
			map.put("text", globalType.getTypeName());
			// map.put("nodeKey", globalType.getNodeKey());

			map.put("parentId", globalType.getParentId());

			map.put("expanded", true);

			if (globalType.getParentId().longValue() != 0L) {
				map.put("leaf", true);
			}
			reslut.add(map);

		}

		return reslut;

	}

	/**
	 * 
	 * @param srcArr
	 * @param childFlag
	 *            子节点对应父节点属性
	 * @param parentFlag
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> generate(List<Map<String, Object>> srcArr,
			String childFlag, String fatherFlag) {
		List<Map<String, Object>> reslut = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < srcArr.size(); i++) {
			Map<String, Object> fatherMap = srcArr.get(i);
			String father = fatherMap.get(fatherFlag).toString();
			for (int j = 0; j < srcArr.size(); j++) {
				Map<String, Object> childMap = srcArr.get(j);
				String child = childMap.get(childFlag).toString();
				if (father.equals(child)) {
					List<Map<String, Object>> children = (List<Map<String, Object>>) fatherMap
							.get("children");
					if (children == null) {
						children = new ArrayList<Map<String, Object>>();
					}
					children.add(childMap);
					// 注意这里，需要重新put一下
					fatherMap.put("children", children);
				}
				System.err.println("测试：" + fatherMap.get(childFlag));
				if (fatherMap.get(childFlag).toString().equals("0")) {
					reslut.add(fatherMap);
				}
			}
		}

		return reslut;

	};

	public List<Object> getVirtualResult() {
		List<Object> dataList = new ArrayList<Object>();
		// 读取层次数据结果集列表
		String catKey = "AR_FD";
		List<GlobalType> list = globalTypeDao.getByCatKey(catKey);
		for (GlobalType globalType : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", globalType.getProTypeId());
			dataRecord.put("text", globalType.getTypeName());
			dataRecord.put("parentId", globalType.getParentId());
			dataRecord.put("nodeKey", globalType.getNodeKey());
			dataRecord.put("catKey", globalType.getCatKey());
			dataRecord.put("version", globalType.getVersion());
			dataList.add(dataRecord);
		}

		HashMap<String, Object> dataRecord = new HashMap<String, Object>();
		dataRecord.put("id", 0);
		dataRecord.put("text", "总分类");
		dataRecord.put("parentId", null);

		dataList.add(dataRecord);
		return dataList;
	}

	@Test
	public void test() {
		String[] otherNode = { "nodeKey", "catKey", "version" };
		String s = JsonTree.generate(getVirtualResult(), otherNode, false);
		System.err.println(s);
	}
}
