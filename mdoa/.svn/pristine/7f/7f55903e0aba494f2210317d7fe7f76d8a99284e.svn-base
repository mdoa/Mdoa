package com.mdoa.personnel.dao;

import java.util.List;
import com.mdoa.personnel.bo.AwardPunishForm;
import com.mdoa.personnel.model.PersonAwardPunish;
import com.mdoa.personnel.model.PersonAwardPunishType;

public interface PersonAwardPunishTypeDao{
  
	
	/**
	 * 根据时间去查询奖惩类别
	 * @param awardPunishType
	 * @return
	 */
	List<PersonAwardPunishType> selectAwardPunishTypeByTime(PersonAwardPunishType awardPunishType);
	/**
	 * 删除奖惩类别
	 * @param punishmentTypeId
	 * @return
	 */
	boolean deleteAwardPunishType(String punishmentTypeId);
	/**
	 * 插入一条奖惩类别
	 * @param awardPunishType
	 * @return
	 */
	boolean insertAwardPunishType(PersonAwardPunishType awardPunishType);
	/**
	 * 修改奖惩类别
	 * @param awardPunishType
	 * @return
	 */
	boolean updateAwardPunishType(PersonAwardPunishType awardPunishType);
	/**
	 * 查询用户的奖惩信息
	 * @param personAwardPunish
	 * @return
	 */
	List<AwardPunishForm> selectAwardPunishByTime(AwardPunishForm awardPunishForm);
	/**
	 * 普通修改奖惩信息
	 * @param personAwardPunish
	 * @return
	 */
	boolean updateAwardPunish(PersonAwardPunish personAwardPunish);
	/**
	 * 更改奖惩信息为已执行
	 * @param personAwardPunish
	 * @return
	 */
	boolean updateAwardPunishPerform(PersonAwardPunish personAwardPunish);
	/**
	 * 删除奖惩信息
	 * @param awardPunishId
	 * @return
	 */
	boolean deleteAwardPunish(String awardPunishId);
	/**
	 * 插入奖惩信息
	 * @param personAwardPunish
	 * @return
	 */
	boolean insertAwardPunish(PersonAwardPunish personAwardPunish);
}