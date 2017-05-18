package com.mdoa.personnel.dao;


import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.mdoa.personnel.bo.PackForm;
import com.mdoa.personnel.model.PersonPack;
import com.mdoa.personnel.model.PersonPackPhoto;
import com.mdoa.user.model.UserInfo;

public interface PersonPackDao {
    /**
     * 查询试用期的员工
     * @param packForm
     * @return
     */
	List<PackForm> selectTryUserById(PackForm packForm);
	/**
	 * 把员工转为正式员工
	 * @param userInfo
	 * @return
	 */
	boolean updateTryUser(UserInfo userInfo);
	/**
	 * 根据创建的时间查询到的合同信息
	 * @param personPack
	 * @return
	 */
	List<PersonPack> selectPackByTime(PersonPack personPack);
	/**
	 * 删除合同
	 * @param packId
	 * @return
	 */
	boolean deletePack(String packId);
	/**
	 * 插入一条合同
	 * @param personPack
	 * @return
	 */
	boolean insertPack(PersonPack personPack);
	/**
	 * 更改合同
	 * @param personPack
	 * @return
	 */
	boolean updatePack(PersonPack personPack);
	/**
	 * 根据packid查询合同图片的信息
	 * @param packId
	 * @return
	 */
	List<PersonPackPhoto> selectPhotoByPackId(PersonPackPhoto packPhoto);
	/**
	 * 根据创建的时间查询到的合同照片信息
	 * @return
	 */
	List<PersonPackPhoto> selectPackPhotoByTime();
	/**
	 * 删除合同照片
	 * @param photoId
	 * @return
	 */
	boolean deletePackPhoto(String photoId);
	/**
	 * 插入合同照片
	 * @param packPhoto
	 * @return
	 */
	boolean insertPackPhoto(PersonPackPhoto packPhoto);
	/**
	 * 修改合同照片的信息
	 * @param photoId
	 * @return
	 */
	boolean updatePackPhoto(String photoId);
	/**
	 * 删除合同下的一张照片
	 * @param params
	 * @return
	 */
	boolean deletePackPhotoByPackId(HashMap<String, String> params);
}