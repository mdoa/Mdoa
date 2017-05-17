package com.htsoft.oa.core.json.tree;

/*
 *  杭州梦德软件有限公司 OA办公自动管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 孩子列表类
 * 
 * @author zxh
 * 
 */
public class Children {
	/** 孩子节点List */
	public List<Node> list = new ArrayList<Node>();

	/**
	 * 孩子节点size
	 * 
	 * @return
	 */
	public int getSize() {
		return list.size();
	}

	/**
	 * 增加孩子节点
	 * 
	 * @param node
	 */
	public void addChild(Node node) {
		list.add(node);
	}

	/**
	 * 拼接孩子节点的JSON字符串
	 */
	public String toString() {
		StringBuffer result = new StringBuffer("[");
		for (Iterator<Node> it = list.iterator(); it.hasNext();) {
			result.append(((Node) it.next()).toString()).append(",");
		}
		if (!list.isEmpty()) {
			result.deleteCharAt(result.length() - 1);
		}
		result.append("]");
		return result.toString();
	}

	/**
	 * 孩子节点排序
	 */
	public void sortChildren() {
		// 对本层节点进行排序
		// 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器
		Collections.sort(list, new NodeIDComparator());
		// 对每个节点的下一层节点进行排序
		for (Iterator<Node> it = list.iterator(); it.hasNext();) {
			((Node) it.next()).sortChildren();
		}
	}

	/**
	 * 孩子节点其它节点排序
	 */
	public void sortSnChildren() {
		Collections.sort(list, new NodeSnComparator());
		// 对每个节点的下一层节点进行排序
		for (Iterator<Node> it = list.iterator(); it.hasNext();) {
			((Node) it.next()).sortSnChildren();
		}
	}
}