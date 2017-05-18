package com.mdoa.repertory.dao;

import java.util.List;

import com.mdoa.repertory.bo.GoodsForm;
import com.mdoa.repertory.model.RepertoryGoodsPosition;
import com.mdoa.repertory.model.RepertoryType;
/**
 * 仓库dao层
 * @author Administrator
 *
 */
public interface RepertoryDao {
	
	
	/**
	 * 获取uuid
	 */
	String getuuid();
	/**
	 * 添加仓库
	 * @param repertoryType
	 * @return
	 */
	boolean insertRepertoryType(RepertoryType repertoryType);
	/**
	 * 添加仓位
	 * @param goodsPosition
	 * @return
	 */
	boolean insertRepertoryPosition(GoodsForm goodsForm);
	/**
	 * 删除仓位信息
	 * @param goodsPositionId
	 * @return
	 */
	boolean deleteRepertoryPosition(String goodsPositionId);
	/**
	 * 删除仓库信息
	 * @param repertoryId
	 * @return
	 */
	boolean deleteRepertory(String repertoryId);
	/**
	 * 修改仓库信息
	 * @param repertoryId
	 * @return
	 */
	boolean updateRepertory(RepertoryType repertoryType);
	/**
	 * 修改仓位信息
	 * @param goodsPositionId
	 * @return
	 */
	boolean updateRepertoryPosition(GoodsForm goodsForm);
	/**
	 * 查询仓位仓库和物品的信息
	 * @param goodsForm
	 * @return
	 */
	List<GoodsForm> selectRepertoryPosition(GoodsForm goodsForm);
	/**
	 * 根据仓位id去查询物品的信息,用于删除仓位
	 * @param goodsPositionId
	 * @return
	 */
	RepertoryGoodsPosition selectGoodsNumberByPosition(String goodsPositionId);
	/**
	 * 入库的时候修改仓位的物品数量
	 * @param goodsForm
	 * @return
	 */
	boolean updatePositionGoodsByIn(GoodsForm goodsForm);
	/**
	 * 出库的时候修改仓位的物品数量
	 * @param goodsForm
	 * @return
	 */
	boolean updatePositionGoodsByOut(GoodsForm goodsForm);
	/**
	 * 查询仓位信息 用于下拉框
	 * @return
	 */
	List<RepertoryGoodsPosition> selectGoodsPosition(RepertoryGoodsPosition goodsPosition);
	/**
	 * 查询仓库信息 用于下拉框
	 * @return
	 */
	List<RepertoryType> selectRepertoryType();
}
