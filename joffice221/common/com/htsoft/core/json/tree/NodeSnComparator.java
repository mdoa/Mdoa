package com.htsoft.core.json.tree;

/*
 *  杭州梦德软件有限公司 OA办公自动管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.Comparator;

/**
 * 按照节点SN排序
 * 
 * @author zxh
 * 
 */
public class NodeSnComparator implements Comparator<Node> {
	/**
	 * 按照节点SN排序
	 */
	@Override
	public int compare(Node o1, Node o2) {
		int j1 = Integer.parseInt(((Node) o1).sn);
		int j2 = Integer.parseInt(((Node) o2).sn);
		return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
	}
}
