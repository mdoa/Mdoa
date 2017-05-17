package com.htsoft.core.json.tree;

/*
 *  杭州梦德软件有限公司 OA办公自动管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 节点类
 * 
 * @author zxh
 * 
 */
public class Node {
	/** * 节点编号 */
	public String id;
	/** * 节点内容 */
	public String text;
	/** * 父节点编号 */
	public String parentId;
	/** 排序 */
	public String sn;
	/** * 其它节点List */
	public List<Map<String, Object>> otherList = new ArrayList<Map<String, Object>>();

	/** * 孩子节点列表 */
	public Children children = new Children();

	/**
	 * 深度遍历，拼接JSON字符串
	 */
	public String toString() {
		StringBuffer result = new StringBuffer("{" + "id : '" + id
				+ "', text : '" + text + "', expanded : true");

		// 拼接其它节点
		if (otherList != null && otherList.size() > 0) {
			for (Map<String, Object> map : otherList) {
				result.append(", ").append(map.get("key")).append(" : '")
						.append(map.get("value")).append("'");
			}
		}
		// 如果有儿子节点 拼接儿子节点
		if (children != null && children.getSize() != 0) {
			result.append(", children : ").append(children.toString());
		} else {
			result.append(", leaf : true");
		}
		result.append("}");
		return result.toString();
	}

	/**
	 * 对子节点ID进行横向排序
	 */
	public void sortChildren() {
		if (children != null && children.getSize() != 0) {
			children.sortChildren();
		}
	}

	/**
	 * 对子节点排序（sn）节点进行横向排序
	 */
	public void sortSnChildren() {
		if (children != null && children.getSize() != 0) {
			children.sortSnChildren();
		}
	}
}
