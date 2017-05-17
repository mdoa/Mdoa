package com.htsoft.core.json.tree;

/*
 *  杭州梦德软件有限公司 OA办公自动管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * JSON树
 * 
 * <pre>
 * 使用说明：请参考 <code> GlobalTypeAction.java</code>的tree
 * </pre>
 * 
 * @author zxh
 * 
 */
public class JsonTree {

	/**
	 * 生成JSON树
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @param otherNode
	 *            其它节点key数组
	 * @param sort
	 *            是否进行排序(不排序是按id排序,否则按sn排序)
	 * @return json树的数据
	 */
	public static String generate(List<?> dataList, String[] otherNode,
			Boolean sort) {
		StringBuffer result = new StringBuffer("[");
		// 节点哈希表<节点id，用于临时存储节点对象>
		HashMap<String, Node> nodeList = construction(dataList, otherNode);
		// 根节点
		List<Node> rootNodeList = new ArrayList<Node>();
		// 构造无序的内存多叉树
		Set<Entry<String, Node>> set = nodeList.entrySet();
		for (Iterator<Entry<String, Node>> it = set.iterator(); it.hasNext();) {
			Node node = (Node) it.next().getValue();
			if (node.parentId == null || node.parentId.equals("")) {
				// 处理有多个父节点节点的
				rootNodeList.add(node);
			} else {
				if (nodeList.get(node.parentId) != null) {
					nodeList.get(node.parentId).children.addChild(node);
				}
			}
		}
		
		if (sort) {// 也对父类进行排序
			Collections.sort(rootNodeList, new NodeSnComparator());
		}
		
		for (Node rootNode : rootNodeList) {
			if (sort) {// 对内存多叉树进行横向排序 sn 排序
				rootNode.sortSnChildren();
			} else {
				rootNode.sortChildren();
			}
			// 输出树形菜单的JSON字符串
			result.append(rootNode.toString()).append(",");
		}
		if (!rootNodeList.isEmpty()) {
			result.deleteCharAt(result.length() - 1);
		}

		result.append("]");
		return result.toString();

	}

	/**
	 * 节点哈希表<节点id，用于临时存储节点对象>
	 * 
	 * @param dataList
	 *            读取层次数据结果集列表
	 * @param otherNode
	 *            其它节点key
	 * @return 节点哈希表
	 */
	@SuppressWarnings("unchecked")
	private static HashMap<String, Node> construction(List<?> dataList,
			String[] otherNode) {
		// 节点哈希表<节点id，用于临时存储节点对象>
		HashMap<String, Node> nodeMap = new HashMap<String, Node>();
		// 根据结果集构造节点列表（存入哈希表）
		for (Iterator<?> it = dataList.iterator(); it.hasNext();) {
			Map<String, Object> dataRecord = ((Map<String, Object>) it.next());
			Node node = new Node();
			node.id = (String) dataRecord.get("id");
			node.text = (String) dataRecord.get("text");
			node.parentId = (String) dataRecord.get("parentId");
			node.sn = (String) dataRecord.get("sn");
			// 其它节点
			if (otherNode != null && otherNode.length > 0) {
				for (String key : otherNode) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("key", key);
					map.put("value", dataRecord.get(key) == null ? ""
							: dataRecord.get(key));
					node.otherList.add(map);
				}
			}
			nodeMap.put(node.id, node);
		}
		return nodeMap;
	}
}
