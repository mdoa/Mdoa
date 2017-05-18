package com.mdoa.repertory.dao;
import java.util.List;

import com.mdoa.repertory.bo.GoodsForm;
import com.mdoa.repertory.model.RepertoryGoods;
import com.mdoa.repertory.model.RepertoryGoodsType;
/**
 * 物品的dao层
 * @author Administrator
 *
 */
public interface GoodsDao {
	
	String getuuid();
	/**
	 * 插入物品信息
	 * @param repertoryGoods
	 * @return
	 */
	boolean insertGoods(RepertoryGoods repertoryGoods);
	/**
	 * 修改物品信息
	 * @param goodsId
	 * @return
	 */
	boolean updateGoods(GoodsForm goodsForm);
	/**
	 * 删除物品信息
	 * @param goodsId
	 * @return
	 */
	boolean deleteGoods(GoodsForm goodsForm);
	/**
	 * 根据物品的信息查询物品
	 * @param goods
	 * @return
	 */
	List<RepertoryGoods> selectGoodsByTime(GoodsForm goodsForm);
	/**
	 * 插入物品类型信息
	 * @param goodsType
	 * @return
	 */
	boolean insertGoodsType(RepertoryGoods goodsType);
	/**
	 * 删除物品类型信息
	 * @param goodsTypeId
	 * @return
	 */
	boolean deleteGoodsType(String goodsTypeId);
	/**
	 * 修改物品信息类型
	 * @param goodsTypeId
	 * @return
	 */
	boolean updateGoodsType(String goodsTypeId);
	/**
	 * 根据物品类型信息查询物品类型 
	 * @param goodsType
	 * @return
	 */
	List<RepertoryGoodsType> selectGoodsTypeByTime(RepertoryGoodsType goodsType);
	/**
	 * 入库的时候修改物品的数量
	 * @param goods_id
	 * @return
	 */
	boolean updateGoodsByIn(GoodsForm goodsForm);
	/**
	 * 出库的时候修改物品的数量
	 * @param goods_id
	 * @return
	 */
	boolean updateGoodsByOut(GoodsForm goodsForm);
	/**
	 * 修改入库记录的时候修改物品 
	 * @param goodsForm
	 * @return
	 */
	boolean updateGoodsByUpdate(GoodsForm goodsForm);
	/**
	 * 根据物品的id去查询物品的信息 ,用于进行入库操作时查询此物品的信息
	 * @param goodsId
	 * @return
	 */
	RepertoryGoods selectGoodsById(String goodsId);
	/**
	 * 根据物品的id去查询入库出库的记录，以及仓库和仓位的信息
	 * @param goodsId
	 * @return
	 */
	List<GoodsForm> selectRecordAndRepertoryById(String goodsId);
	
	/**
	 * 通过物品Url查询
	 * @param goodsTypeUrl
	 * @return
	 */
	RepertoryGoods findSumByTypeUrl(String goodsTypeUrl);
	
	
}
