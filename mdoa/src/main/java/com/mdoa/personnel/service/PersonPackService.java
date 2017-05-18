package com.mdoa.personnel.service;

import java.util.HashMap;
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
import com.mdoa.personnel.bo.PackForm;
import com.mdoa.personnel.dao.PersonPackDao;
import com.mdoa.personnel.model.PersonPack;
import com.mdoa.personnel.model.PersonPackPhoto;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class PersonPackService extends BaseService{
	
	@Autowired
	private PersonPackDao personPackDao;
	
	/**
	 * 查询试用期的员工
	 * @param packForm
	 * @return
	 */
	public PageModel<PackForm> selectTryUserById(PackForm packForm){
		packForm.setDepartmentName(StringUtil.toLikeRight(packForm.getDepartmentName()));
		packForm.setUserName(StringUtil.toLikeRight(packForm.getUserName()));
		packForm.setPostName(StringUtil.toLikeRight(packForm.getPostName()));
		packForm.setIdCard(StringUtil.toLikeRight(packForm.getIdCard()));
		PageHelper.startPage(packForm.getPage(), packForm.getRows());
		List<PackForm> list = personPackDao.selectTryUserById(packForm);
		PageModel<PackForm> pageModel = new PageModel((Page)list);
		return pageModel;
	}
	
	/**
	 * 转正员工
	 * @param userInfo
	 */
	public void updateTryUser(UserInfo userInfo){
		if(!personPackDao.updateTryUser(userInfo)){
			throw new RuntimeException("转正失败");
		}
	}
	
	/**
	 * 查询合同的信息
	 * @param model
	 * @param pack
	 * @return
	 */
	public PageModel<PersonPack> selectPackByTime(PersonPack personPack){
		if(!StringUtil.isEmpty(personPack.getUserName())){
			personPack.setUserName("%"+personPack.getUserName()+"%");
		}
		PageHelper.startPage(personPack.getPage(), personPack.getRows());
		List<PersonPack> list = personPackDao.selectPackByTime(personPack);
		PageModel<PersonPack> pageModel = new PageModel((Page)list);
		return pageModel;
	}
	
	/**
	 * 根据packid查询合同图片的信息
	 * @param packId
	 * @return
	 */
	public List<PersonPackPhoto> selectPhotoByPackId(PersonPackPhoto packPhoto){

		List<PersonPackPhoto> list = personPackDao.selectPhotoByPackId(packPhoto);
		return list;
	}

	/**
	 * 修改合同信息
	 * @param personPack
	 */
	public void updatePack(PersonPack personPack, HttpServletRequest request){
		
		UserInfo user = getUser(request);
		personPack.setUpdateUserId(user.getUpdateUserId());
		if(!personPackDao.updatePack(personPack)){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 保存个人合同信息
	 * @param personPack
	 */
	public void insertPack(PersonPack personPack, HttpServletRequest request){
		UserInfo user = getUser(request);
		personPack.setCreateUserId(user.getCreateUserId());
		if(!personPackDao.insertPack(personPack)){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 根据合同id去删除合同信息
	 * @param packId
	 */
	public void deletePack(String packId, HttpServletRequest request){
		PersonPack personPack = new PersonPack();
		UserInfo user = getUser(request);
		personPack.setUpdateUserId(user.getUpdateUserId());
		if(!personPackDao.deletePack(packId)){
			throw new RuntimeException();
		}
	}
	
// =====================================================================
	/**
	 * 通过创建时间的先后 查询合同照片
	 * @param model
	 * @return
	 */
	public PageModel<PersonPackPhoto> selectPackPhotoByTime(BaseModel model){
		
		PageHelper.startPage(model.getPage(), model.getRows());
		List<PersonPackPhoto> list = personPackDao.selectPackPhotoByTime();
		PageModel<PersonPackPhoto> pageModel =new PageModel((Page)list);
		return pageModel;
		
	}
	
	/**
	 * 删除合同照片信息
	 * @param photoId
	 */
	public void deletePackPhoto(String photoId, HttpServletRequest request){
		PersonPackPhoto personPackPhoto = new PersonPackPhoto();
		UserInfo user = getUser(request);
		personPackPhoto.setUpdateUserId(user.getUpdateUserId());
		if(!personPackDao.deletePackPhoto(photoId)){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 插入合同照片
	 * @param packPhoto
	 */
	public void insertPackPhoto(PersonPackPhoto packPhoto, HttpServletRequest request){
		UserInfo user = getUser(request);
		packPhoto.setCreateUserId(user.getCreateUserId());
		if(!personPackDao.insertPackPhoto(packPhoto)){	
			throw new RuntimeException();
		}
	}
	
	/**
	 * 修改合同照片的信息
	 * @param photoId
	 */
	public void updatePackPhoto(String photoId, HttpServletRequest request){
		PersonPackPhoto personPackPhoto = new PersonPackPhoto();
		UserInfo user = getUser(request);
		personPackPhoto.setUpdateUserId(user.getUpdateUserId());
		if(!personPackDao.updatePackPhoto(photoId)){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 删除合同下的一张照片
	 * @param packId
	 * @param photoId
	 */
	public void deletePackPhotoByPackId(String packId,String photoId){
		HashMap<String, String> params  = new HashMap<String, String>();
		params.put("packId", packId);
		params.put("photoId", photoId);
		if(!personPackDao.deletePackPhotoByPackId(params)){
			throw new RuntimeException("删除照片失败");
		}
		
	}
	
}
