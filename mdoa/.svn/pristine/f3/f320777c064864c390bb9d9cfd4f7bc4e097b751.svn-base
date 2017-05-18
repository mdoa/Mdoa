package com.mdoa.personnel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mdoa.base.model.BaseModel;
import com.mdoa.base.service.BaseService;
import com.mdoa.personnel.bo.AwardPunishForm;
import com.mdoa.personnel.dao.PersonAwardPunishTypeDao;
import com.mdoa.personnel.model.PersonAwardPunish;
import com.mdoa.personnel.model.PersonAwardPunishType;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class PersonAwordPunishTypeService extends BaseService{

	@Autowired
	private PersonAwardPunishTypeDao awardPunishTypeDao;
	
	
	/**
	 * 根据创建时间去查询奖惩类别
	 * @param model
	 * @param awardPunishType
	 * @return
	 */
	public PageModel<PersonAwardPunishType> selectAwardPunishTypeByTime(PersonAwardPunishType awardPunishType){
		if(!StringUtil.isEmpty(awardPunishType.getTypeName())){
			awardPunishType.setTypeName("%"+awardPunishType.getTypeName()+"%");
		}
		PageHelper.startPage(awardPunishType.getPage(),awardPunishType.getRows());
		List<PersonAwardPunishType> list = awardPunishTypeDao.selectAwardPunishTypeByTime(awardPunishType);
		PageModel<PersonAwardPunishType> pageModel = new PageModel<>((Page)list);
		return pageModel;
	}
	
	/**
	 * 修改奖惩类别
	 * @param awardPunishType
	 */
	public void updateAwardPunishType(PersonAwardPunishType awardPunishType, HttpServletRequest request){
		UserInfo user = getUser(request);
		awardPunishType.setUpdateUserId(user.getCreateUserId());
		if(!awardPunishTypeDao.updateAwardPunishType(awardPunishType)){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 保存一条奖惩类别
	 * @param awardPunishType
	 */
	public void insertAwardPunishType(PersonAwardPunishType awardPunishType, HttpServletRequest request){
		UserInfo user = getUser(request);
		awardPunishType.setCreateUserId(user.getCreateUserId());
		if(!awardPunishTypeDao.insertAwardPunishType(awardPunishType)){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 删除奖惩类别
	 * @param typeId
	 */
	public void deleteAwardPunishType(String typeId, HttpServletRequest request){
		PersonAwardPunishType awardPunishType = new PersonAwardPunishType();
		UserInfo user = getUser(request);
		awardPunishType.setUpdateUserId(user.getUpdateUserId());
		if(!awardPunishTypeDao.deleteAwardPunishType(typeId)){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 条件查询奖惩信息
	 * @param personAwardPunish
	 * @return
	 */
	public PageModel<AwardPunishForm> selectAwardPunishByTime(AwardPunishForm awardPunishForm){
		if(!StringUtil.isEmpty(awardPunishForm.getUserName())){
			awardPunishForm.setUserName("%"+awardPunishForm.getUserName()+"%");
		}
		if(!StringUtil.isEmpty(awardPunishForm.getPerformUserName())){
			awardPunishForm.setPerformUserName("%"+awardPunishForm.getPerformUserName()+"%");
		}
		PageHelper.startPage(awardPunishForm.getPage(), awardPunishForm.getRows());
		List<AwardPunishForm> list = awardPunishTypeDao.selectAwardPunishByTime(awardPunishForm);
		PageModel<AwardPunishForm> pageModel = new PageModel((Page)list);
		return pageModel;
	}

	/**
	 * 普通修改奖惩信息
	 * @param personAwardPunish
	 */
	public void updateAwardPunish(PersonAwardPunish personAwardPunish, HttpServletRequest request){
		
		UserInfo user = getUser(request);
		personAwardPunish.setUpdateUserId(user.getUpdateUserId());
		if(!awardPunishTypeDao.updateAwardPunish(personAwardPunish)){
			
			throw new RuntimeException();
		}	
	}	
	/**
	 * 更改奖惩信息为已执行
	 * @param personAwardPunish
	 */
	public void updateAwardPunishPerform(PersonAwardPunish personAwardPunish, HttpServletRequest request){
		UserInfo user = getUser(request);
		personAwardPunish.setUpdateUserId(user.getUpdateUserId());
		personAwardPunish.setPerformUserId(user.getUpdateUserId());
		personAwardPunish.setPerformUserName(user.getUpdateUserName());
		if(!awardPunishTypeDao.updateAwardPunishPerform(personAwardPunish)){
			
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * 删除奖惩信息
	 * @param personAwardPunish
	 */
	public void deleteAwardPunish(String awardPunishId, HttpServletRequest request){
		PersonAwardPunish personAwardPunish = new PersonAwardPunish();
		UserInfo user = getUser(request);
		personAwardPunish.setUpdateUserId(user.getUpdateUserId());
		if(!awardPunishTypeDao.deleteAwardPunish(awardPunishId)){
			
			throw new RuntimeException();
		}
		
	}
	
	/**
	 * 插入奖惩信息
	 * @param personAwardPunish
	 */
	public void insertAwardPunish(PersonAwardPunish personAwardPunish, HttpServletRequest request){
		UserInfo user = getUser(request);
		personAwardPunish.setCreateUserId(user.getCreateUserId());
		if(!awardPunishTypeDao.insertAwardPunish(personAwardPunish)){
			
			throw new RuntimeException();
		}
		
	}
	
	
}
