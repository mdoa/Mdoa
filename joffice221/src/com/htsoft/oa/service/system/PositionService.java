package com.htsoft.oa.service.system;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Position;

public interface PositionService extends BaseService<Position>{
	/**
	 * 取得某个节点下的所有子节点
	 * @param parentId
	 * @return
	 */
	public List<Position> getByParent(Long parentId);
	
	/**
	 * 按路径查找所有节点
	 * @param path
	 * @return
	 */
	public List<Position> getByPath(String path);
	/**
	 * 
	 * @param posId
	 */
	public Boolean delPositionCascade(Long posId);
	
	/**
	 * 根据某岗位取得相应岗位
	 * @param 
	 * posId 当前岗位
	 * posName 岗位名称
	 * @return
	 */
	public List<Position> getRelativeUnderling(Long posId, PagingBean pb);
	
	/**
	 * 父节点的下一级子节点
	 * @param posId
	 * @param pb
	 * @param posName
	 * @return
	 */
	public List<Position> getUnderLing(Long posId, PagingBean pb, String posName);
	
	/**
	 * 取得同级岗位的主岗位
	 * @param posId 岗位id
	 * @return
	 */
	public List<Position> getUnderByParent(Long posId);
	
	/**
	 * 取得同级岗位
	 * @param 
	 * posId 岗位id
	 * posName 岗位名称
	 * @return
	 */
	public List<Position> getSameLevel(Long posId, PagingBean pb, String posName);
	
	/**
	 * 保存岗位树序列
	 * @param curNode
	 * @param oldParent
	 * @param newParent
	 * @param setIdx
	 */
	public void saveTreeSeq(Long curNode, Long oldParent, Long newParent, int setIdx);
	
}


