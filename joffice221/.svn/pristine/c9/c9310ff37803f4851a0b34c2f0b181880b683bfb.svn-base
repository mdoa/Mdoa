package com.htsoft.oa.service.system.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.PositionDao;
import com.htsoft.oa.dao.system.UserPositionDao;
import com.htsoft.oa.model.system.Position;
import com.htsoft.oa.model.system.UserPosition;
import com.htsoft.oa.service.system.PositionService;

public class PositionServiceImpl extends BaseServiceImpl<Position> implements PositionService{

	private PositionDao dao;
	@Resource
	private UserPositionDao userPositionDao;	
	public PositionServiceImpl(PositionDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/**
	 * 取得某个节点下的所有子节点
	 * @param parentId
	 * @return
	 */
	public List<Position> getByParent(Long parentId){
		return dao.getByParent(parentId);
	}
	
	/**
	 * 按路径查找所有节点
	 * @param path
	 * @return
	 */
	public List<Position> getByPath(String path){
		return dao.getByPath(path);
	}
	
	/**
	 * 删除某个岗位及其下属岗位
	 * @param posId
	 */
	public Boolean delPositionCascade(Long posId){
		Position position=get(posId);
		evict(position);
		List<Position> listPos=getByPath(position.getPath());
		for(Position pos:listPos){
			List<UserPosition> list=userPositionDao.getByPosId(pos.getPosId());
			if(list.size()>0)
				return false;
		}
		for(Position pos:listPos){
			remove(pos);
		}
		return true;
	}
	
	/**
	 * 根据某岗位取得相应岗位
	 * @param 
	 * posId 当前岗位
	 * @return
	 */
	public List<Position> getRelativeUnderling(Long posId, PagingBean pb) {
		return dao.getRelativeUnderling(posId, pb);
	}

	@Override
	public List<Position> getUnderLing(Long posId, PagingBean pb, String posName) {
		return dao.getUnderLing(posId, pb, posName);
	}

	/**
	 * 取得同级岗位的主岗位
	 * @param posId 岗位id
	 * @return
	 */
	public List<Position> getUnderByParent(Long posId) {
		return dao.getUnderByParent(posId);
	}

	/**
	 * 取得同级岗位
	 * @param 
	 * posId 岗位id
	 * posName 岗位名称
	 * @return
	 */
	public List<Position> getSameLevel(Long posId, PagingBean pb, String posName) {
		return dao.getSameLevel(posId, pb, posName);
	}
	
	/**
	 * 保存岗位树序列
	 * @param curNode
	 * @param oldParent
	 * @param newParent
	 * @param setIdx
	 */
	public void saveTreeSeq(Long curNode, Long oldParent, Long newParent, int setIdx)
	{
		List<Position> newPosList = dao.getByParent(newParent);
		Position curPos = dao.get(curNode);
		
		// 新旧父节点相同时,先删除旧节点
		if(oldParent.longValue()==newParent.longValue())
		{
			newPosList.remove(curPos);
		}
		
		newPosList.add(setIdx, curPos);
		Position newParentNode = dao.get(newParent);
		
		// 保存新节点
		saveSonNode(newPosList, newParentNode);
	}
	
	/**
	 * 保存新的节点移位序列
	 * @param list
	 * @param parentNode
	 */
	public void saveSonNode(List<Position> list, Position parentNode)
	{
		for(int sn=0;sn<list.size();sn++){
			Position pos = list.get(sn);
			pos.setPosSupId(parentNode==null?0:parentNode.getPosId());
			pos.setPath(parentNode==null?"0."+pos.getPosId()+"."
					:parentNode.getPath()+pos.getPosId()+".");
			pos.setDepth(parentNode==null?1:parentNode.getDepth()+1);
			pos.setSn(sn);
			dao.save(pos);
			List<Position> sonList = dao.getByParent(pos.getPosId());
			if(sonList!=null&&sonList.size()>0){
				saveSonNode(sonList, pos);
			}
		}
	}
	
}