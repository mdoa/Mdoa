package com.mdoa.repertory.dao;

import java.util.List;

import com.mdoa.repertory.model.DragModel;
import com.mdoa.repertory.model.RepertoryGoodsType;
import com.mdoa.repertory.model.TreeModel;

public interface TreeDao {
	/**
	 * 获取树结构
	 * @param pid
	 * @return
	 */
	List<TreeModel> list(String pid);
	/**
	 * 获取一个uuid
	 * @return
	 */
	String getuuid();
	/**
	 * 添加物品类
	 * @param repertoryGoodsType
	 * @return
	 */
	boolean addRepertoryType(RepertoryGoodsType repertoryGoodsType);
	/**
	 * 根据选择的ID查询其url
	 * @param parentTypeId
	 * @return
	 */
	RepertoryGoodsType selectUrlByparentTypeId(String parentTypeId);
	RepertoryGoodsType selectUrlByGoodsTypeId(String goodsTypeId);
	/**
	 * 根据当前父ID删除下面的所有物品类
	 * @param repertoryGoodsType
	 * @return
	 */
	boolean removeRepertoryType(RepertoryGoodsType repertoryGoodsType);
	/**
	 * 根据ID修改相应的物品类Name
	 * @param repertoryGoodsType
	 * @return
	 */
	boolean editRepertoryType(RepertoryGoodsType repertoryGoodsType);
	/**
	 * 拖动中的插入情况：根据Id修改Url
	 * @param dragModel
	 */
	boolean updateDragSourceGoodsUrl(RepertoryGoodsType dragModel);
	/**
	 * 根据货物ID查询货物信息
	 * @param sourceId
	 * @return
	 */
	RepertoryGoodsType selectGoodsInformationById(String sourceId);
	/**
	 * 拖动插入后修改source的下属物品类的url(append)
	 * @param dragModel
	 * @return
	 */
	boolean updateDragLaterUrl(RepertoryGoodsType dragModel);
	/**
	 * 拖动插入后修改source的下属物品类的url(bottom,top)
	 * @param dragModel
	 * @return
	 */
	boolean updateDragSourceGoodsUrlBt(RepertoryGoodsType dragModel);
	/**
	 * 物品类下面是否有物品
	 * @param repertoryGoodsType
	 * @return
	 */
	List<RepertoryGoodsType> isGoodsBelowType(RepertoryGoodsType repertoryGoodsType);
	
	
}
