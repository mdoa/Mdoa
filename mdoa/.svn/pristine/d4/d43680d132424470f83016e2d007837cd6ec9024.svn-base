package com.mdoa.admin.dao;

import java.util.List;

import com.mdoa.admin.model.AssetsType;
import com.mdoa.admin.model.AssetsModel;

public interface AssetsDao {
	/**
	 * 获取树结构
	 * @param pid
	 * @return
	 */
	List<AssetsModel> list(String pid);
	/**
	 * 获取uuiid
	 * @return
	 */
	String getuuid();
	/**
	 * 添加资产类
	 * @param assetsType
	 * @return
	 */
	boolean addAssetsType(AssetsType assetsType);
	/**
	 * 根据选择的ID查询其url
	 * @param parentTypeId
	 * @return
	 */
	AssetsType selectUrlByparentTypeId(String parentTypeId);
	AssetsType selectUrlByAssetsTypeId(String assetsTypeId);
	/**
	 * 根据当前父ID删除下面的所有资产类
	 * @param assetsType
	 * @return
	 */
	boolean removeAssetsType(AssetsType assetsType);
	/**
	 * 根据ID修改相应的资产类Name
	 * @param assetsType
	 * @return
	 */
	boolean editAssetsType(AssetsType assetsType);
	/**
	 * 拖动中的插入情况：根据Id修改Url
	 * @param assetsType
	 * @return
	 */
	boolean updateDragSourceAssetsUrl(AssetsType dragModel);
	/**
	 * 根据资产id查询资产信息
	 * @param sourceId
	 * @return
	 */
	AssetsType selectAssetsInformationById(String sourceId);
	/**
	 * 拖动插入后修改source的下属资产类的url(append)
	 * @param dragModel
	 * @return
	 */
	boolean updateDragLaterUrl(AssetsType dragModel);
	/**
	 * 拖动插入后修改source的下属资产类的url(bottom,top)
	 * @param dragModel
	 * @return
	 */
	boolean updateDragSourceAssetsUrlBt(AssetsType dragModel);
	/**
	 * 资产类下面是否有资产
	 * @param assetsType
	 * @return
	 */
	List<AssetsType> isAssetsBelowType(AssetsType assetsType);
}
