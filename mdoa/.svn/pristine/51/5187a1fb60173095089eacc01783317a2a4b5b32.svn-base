package com.mdoa.user.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.base.service.BaseService;
import com.mdoa.constant.Constant;
import com.mdoa.constant.FileConstant;
import com.mdoa.framework.bo.LeadForm;
import com.mdoa.framework.service.LeadService;
import com.mdoa.framework.service.RoleService;
import com.mdoa.personnel.model.PersonEducation;
import com.mdoa.personnel.model.PersonTrain;
import com.mdoa.personnel.model.PersonWork;
import com.mdoa.user.dao.UserInfoDao;
import com.mdoa.user.model.UserInfo;
import com.mdoa.user.model.UserInfoOther;
import com.mdoa.user.model.UserTransfer;
import com.mdoa.util.ExcelModel;
import com.mdoa.util.ExcelUtil;
import com.mdoa.util.FileUtil;
import com.mdoa.util.JSONUtil;
import com.mdoa.util.Md5Util;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class UserInfoService extends BaseService{
	
	@Autowired
	private UserInfoDao userInfoDao;
	
	@Autowired
	private LeadService leadService;
	
	@Autowired
	private RoleService roleService;
	/**
	 * 添加用户基本信息
	 * @param userInfo
	 * @return
	 * @throws Exception 
	 */
	public String saveUserInfo(UserInfo userInfo) throws Exception{
		if (StringUtil.isEmpty(userInfo.getBirthdayStr()))
			throw new RuntimeException("添加员工信息失败");
		if (StringUtil.isEmpty(userInfo.getWorkTimeStr()))
			throw new RuntimeException("添加员工信息失败");
		if(StringUtil.isEmpty(userInfo.getUserId()))
			userInfo.setUserId(getuuid());
		userInfo.setPassword(Md5Util.getMd5Str("123456"));
		this.saveIdCardImg(userInfo);
		if(!userInfoDao.saveUserInfo(userInfo)){
			throw new RuntimeException("添加员工基本信息失败");
		}
		//将userInfo中的信息填装到leadForm中
		if(!StringUtil.isEmpty(userInfo.getLeadId())){
			LeadForm leadForm = new LeadForm();
			leadForm.setCreateUserId(userInfo.getCreateUserId());
			leadForm.setCreateUserName(userInfo.getCreateUserName());
			leadForm.setUserId(userInfo.getUserId());
			leadForm.setUserName(userInfo.getUserName());
			leadForm.setLeadId(userInfo.getLeadId());
			leadForm.setLeadName(userInfo.getLeadName());
			//为员工添加上级领导
			leadService.addLeader(leadForm);
		}
		return userInfo.getUserId();
	}
	
	/**
	 * 添加用户工作信息
	 */
	public String saveWork(PersonWork personWork){
		personWork.setWorkId(getuuid());
		if(!userInfoDao.saveWork(personWork)){
			throw new RuntimeException("添加员工基本信息失败");
		}
		return personWork.getWorkId();
	}
	
	/**
	 * 添加培训信息
	 */
	public String saveTrain(PersonTrain personTrain){
		personTrain.setTrainDocId(getuuid());
		if(!userInfoDao.saveTrain(personTrain)){
			throw new RuntimeException();
		}
		return personTrain.getTrainDocId();
	}
	
	/**
	 * 添加教育信息
	 */
	public String saveEdu(PersonEducation personEducation){
		personEducation.setEducationId(getuuid());
		if(!userInfoDao.saveEdu(personEducation)){
			throw new RuntimeException();
		}
		return personEducation.getEducationId();
	}
	
	/**
	 * 修改用户基本信息
	 * @param userInfo
	 * @return
	 * @throws Exception 
	 */
	public String updateUserInfo(UserInfo userInfo) throws Exception{
//		if(this.selectUserInfoById(userInfo).getRows().size() > 0){
//			throw new RuntimeException("将员工修改为错误的员工信息");
//		}
		
		if(!StringUtil.isEmpty(userInfo.getPassword())){
			userInfo.setPassword(Md5Util.getMd5Str(userInfo.getPassword()));
		}
		//保存所上传的图片base编码到服务器
		saveIdCardImg(userInfo);
		if(!userInfoDao.updateUserInfo(userInfo)){
			throw new RuntimeException("修改员工基本信息失败");
		}
		//如果不存在上级Id或者上级名称，则不予修改
		if(!StringUtil.isEmpty(userInfo.getLeadId()) && !StringUtil.isEmpty(userInfo.getLeadName())){
			//将userInfo中的信息填装到leadForm中
			LeadForm leadForm = new LeadForm();
			leadForm.setUpdateUserId(userInfo.getCreateUserId());
			leadForm.setUpdateUserName(userInfo.getCreateUserName());
			leadForm.setCreateUserId(userInfo.getCreateUserId());
			leadForm.setCreateUserName(userInfo.getCreateUserName());
			leadForm.setUserId(userInfo.getUserId());
			leadForm.setUserName(userInfo.getUserName());
			leadForm.setLeadId(userInfo.getLeadId());
			leadForm.setLeadName(userInfo.getLeadName());
			//修改员工上级信息
			leadService.updateUserLead(leadForm);
		}
		return userInfo.getUserId();
	}
	
	/**
	 * 修改工作信息
	 */
	public String updateWork(PersonWork personWork){
		if(!userInfoDao.updateWork(personWork)){
			throw new RuntimeException("添加员工基本信息失败");
		}
		return personWork.getWorkId();
	}
	
	/**
	 * 修改教育信息
	 */
	public String updateEdu(PersonEducation personEducation){
		if(!userInfoDao.updateEdu(personEducation)){
			throw new RuntimeException();
		}
		return personEducation.getEducationId();
	}
	/**
	 * 查询用户基本信息
	 * @param userInfo
	 * @return
	 */
	public PageModel<UserInfo> selectUserInfoById(UserInfo userInfo){
		PageHelper.startPage(userInfo.getPage(), userInfo.getRows());
		List<UserInfo> list = userInfoDao.selectUserInfoById(userInfo);
		PageModel<UserInfo> pageModel = new PageModel<UserInfo>((Page<UserInfo>)list);
		return pageModel;
	}
	/**
	 * 查询用户基本信息
	 * @param userInfo
	 * @return
	 */
	public PageModel<UserInfo> selectUserInfo(UserInfo userInfo){
		userInfo.setDepartmentName(StringUtil.toLikeRight(userInfo.getDepartmentName()));
		userInfo.setUserName(StringUtil.toLikeRight(userInfo.getUserName()));
		userInfo.setPostName(StringUtil.toLikeRight(userInfo.getPostName()));
		userInfo.setDepartmentUrl(StringUtil.toLikeRight(userInfo.getDepartmentUrl()));
		userInfo.setAddress(StringUtil.toLikeAll(userInfo.getAddress()));
		userInfo.setNativePlace(StringUtil.toLikeAll(userInfo.getNativePlace()));
		PageHelper.startPage(userInfo.getPage(), userInfo.getRows());
		List<UserInfo> list = userInfoDao.selectUserInfo(userInfo);
		PageModel<UserInfo> pageModel = new PageModel<UserInfo>((Page<UserInfo>)list);
		return pageModel;
	}
	
	/**
	 * 查询工作信息
	 */
	public PageModel<PersonWork> selectWork(PersonWork personWork){
		PageHelper.startPage(personWork.getPage(), personWork.getRows());
		List<PersonWork> list = userInfoDao.selectWork(personWork);
		PageModel<PersonWork> pageModel = new PageModel<PersonWork>((Page<PersonWork>)list);
		return pageModel;
	}
	
	/**
	 * 查询教育信息
	 */
	public PageModel<PersonEducation> selectEdu(PersonEducation personEducation){
		PageHelper.startPage(personEducation.getPage(), personEducation.getRows());
		List<PersonEducation> list = userInfoDao.selectEdu(personEducation);
		PageModel<PersonEducation> pageModel = new PageModel<PersonEducation>((Page<PersonEducation>)list);
		return pageModel;
	}
	
	/**
	 * 重置密码
	 * @param userInfo
	 */
	public void resetPassword(UserInfo userInfo) {
		userInfo.setPassword(Md5Util.getMd5Str("123456"));
		if (!userInfoDao.resetPassword(userInfo)) 
			throw new RuntimeException("重置密码失败");
	}

	/**
	 * 角色赋予
	 * @param userInfo
	 */
	public void roleGive(UserInfo userInfo) {
		if(!StringUtil.isEmpty(userInfo.getRoleIds())) {
			//清空用户角色
			roleService.cleanUserRole(userInfo);
			//重新添加用户角色
			roleService.addUserRole(userInfo);
		}
	}
	
	/**
	 * 员工调动
	 * @throws Exception 
	 */
	public void userTransfer(UserTransfer userTransfer, HttpServletRequest request) throws Exception{
		if(StringUtil.isEmpty(userTransfer.getTransferTimeStr())){
			throw new RuntimeException("调动时间出现空") ;
		}
		UserInfo user = getUser(request);
		//调用service 层方法对来修改用户的部门及岗位
		UserInfo userInfo = new UserInfo();
		userInfo.setUpdateUserId(user.getUserId());
		userInfo.setUpdateUserName(user.getUserName());
		userInfo.setUserId(userTransfer.getUserId());
		userInfo.setDepartmentUrl(userTransfer.getNewDepartmentUrl());
		userInfo.setDepartmentName(userTransfer.getNewDepartmentName());
		userInfo.setPostId(userTransfer.getNewPostId());
		userInfo.setPostName(userTransfer.getNewPostName());
		userInfoDao.updateUserInfo(userInfo);//修改用户基本信息
		//调用dao层方法添加员工调动记录
		userTransfer.setCreateUserId(user.getUserId());
		userTransfer.setCreateUserName(user.getUserName());
		if(!userInfoDao.addUserTransferInfo(userTransfer))
			throw new RuntimeException("添加员工调动信息失败");
	}
	/**
	 * 员工批量调动
	 * @param userTransfer
	 */
	public void userTransfers(UserTransfer userTransfer, HttpServletRequest request) {
		UserInfo user = getUser(request);
		userTransfer.setUserIds(userTransfer.getUserIds());
		userTransfer.setUpdateUserId(user.getUserId());
		userTransfer.setUpdateUserName(user.getUserName());
		this.updateUserInfos(userTransfer);
		userTransfer.setCreateUserId(user.getUserId());
		userTransfer.setCreateUserName(user.getUserName());
		List<UserTransfer> list = new ArrayList<>();
		for (int i = 0; i < userTransfer.getOldDepartmentUrls().length; i++) {
			UserTransfer e = new UserTransfer();
			e.setCreateUserId(user.getUserId());
			e.setCreateUserName(user.getUserName());
			e.setOldDepartmentUrl(userTransfer.getOldDepartmentUrls()[i]);
			e.setOldPostId(userTransfer.getOldPostIds()[i]);
			e.setUserId(userTransfer.getIdCards()[i]);
			e.setNewDepartmentUrl(userTransfer.getNewDepartmentUrl());
			e.setNewDepartmentName(userTransfer.getNewDepartmentName());
			e.setNewPostId(userTransfer.getNewPostId());
			e.setNewPostName(userTransfer.getNewPostName());
			e.setTransferTimeStr(userTransfer.getTransferTimeStr());
			e.setRemark(userTransfer.getRemark());
			list.add(e);
		}
		if (!userInfoDao.addUserTransferInsfo(list)) 
			throw new RuntimeException("批量添加员工调动信息失败");
		
	}
	/**
	 * 批量修改用户基本信息
	 */
	public void updateUserInfos(UserTransfer userTransfer){
		if (!userInfoDao.updateUserInfos(userTransfer))
			throw new RuntimeException("批量修改用户失败");
			
	}
	/**
	 * 查询员工调动记录
	 */
	public PageModel<UserTransfer> selectUserTransfer(UserTransfer userTransfer, HttpServletRequest request){
		PageHelper.startPage(userTransfer.getPage(), userTransfer.getRows());
		//进行右模糊转换
		userTransfer.setUserName(StringUtil.toLikeRight(userTransfer.getUserName()));
		List<UserTransfer> list = userInfoDao.selectUserTransfer(userTransfer);
		PageModel<UserTransfer> pageModel = new PageModel<UserTransfer>((Page<UserTransfer>)list);
		return pageModel;
	}
	
	/**
	 * 修改调动记录
	 * @param userTransfer
	 */
	public void updateUserTransfer(UserTransfer userTransfer, HttpServletRequest request) {
		UserInfo userInfo = getUser(request);
		userTransfer.setUpdateUserId(userInfo.getUserId());
		userTransfer.setUpdateUserName(userInfo.getUserName());
		if (!userInfoDao.updateUserTransfer(userTransfer)) 
			throw new RuntimeException("修改调动记录表失败");
		if (!userInfoDao.updateUserTransferInfo(userTransfer)) 
			throw new RuntimeException("修改人员信息表失败");
	}
	
	/**
	 * 删除人员调动记录
	 * @param userTransfer
	 * @throws Exception 
	 */
	public void deleteUserTransfer(UserTransfer userTransfer, HttpServletRequest request) throws Exception {
		UserInfo user = getUser(request);
		userTransfer.setUpdateUserId(user.getUserId());
		userTransfer.setUpdateUserName(user.getUserName());
		if(!userInfoDao.deleteUserTransfer(userTransfer))
			throw new RuntimeException("删除人员调动记录失败");
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(userTransfer.getUserId());
		userInfo.setDepartmentUrl(userTransfer.getOldDepartmentUrl());
		userInfo.setDepartmentName(userTransfer.getOldDepartmentName());
		userInfo.setPostId(userTransfer.getOldPostId());
		userInfo.setPostName(userTransfer.getOldPostName());
		userInfo.setUpdateUserId(user.getUserId());
		userInfo.setUpdateUserName(user.getUserName());
		this.updateUserInfo(userInfo);
	}
	
	/**
	 * 导出人员数据到Excel的方法
	 * @param jsonString 
	 * @param userInfo 
	 */
	public void writeToExcel(UserInfo userInfo, String jsonString, String filePath) throws Exception{
		List<ExcelModel> modelDetails = JSONUtil.jsonToExcelModelList(jsonString);
		PageModel<UserInfo> page = this.selectUserInfo(userInfo);
		List<UserInfo> userList = page.getRows();
		for(int i = 0 ; i<userList.size();i++){
			userList.get(i).parseData();
		}
		ExcelUtil.writeListToExcel(filePath,userList, modelDetails);
	}
	
	/**
	 * 添加人员的其他信息
	 */
	public String addOtherInfo(UserInfoOther userInfoOther){
		String uuid = userInfoDao.getuuid();
		userInfoOther.setUserInfoOtherId(uuid);
		if(!userInfoDao.addOtherInfo(userInfoOther))
			throw new RuntimeException("添加员工其他信息失败");
		return uuid;
	}
	
	/**
	 * 修改人员的其他信息
	 */
	public void updateOtherInfo(UserInfoOther userInfoOther){
		if(!userInfoDao.updateOtherInfo(userInfoOther))
			throw new RuntimeException("修改员工其他信息失败");
	}
	
	/**
	 * 查询人员的其他信息
	 */
	public List<UserInfoOther> selectOtherInfo(UserInfoOther userInfoOther){
		List<UserInfoOther> list = userInfoDao.selectOtherInfo(userInfoOther);
		return list;
	}
	
	/**
	 * 设定员工的身份证招聘信息
	 * 并将照片保存至本地
	 * @throws Exception 
	 */
	public void saveIdCardImg(UserInfo userInfo) throws Exception{
		String idCard = userInfo.getIdCard();
		String upImgBase = userInfo.getIdCardUpImgBase();
		if(!StringUtil.isEmpty(upImgBase)){
			String idCardUpImg = FileConstant.FILE_PATH + FileConstant.USER_IDCARD + "/" + idCard + "upImg.jpg";
			FileUtil.saveBase64(upImgBase, idCardUpImg);
			userInfo.setIdCardUpImg(idCardUpImg);
		}
		String downImgBase = userInfo.getIdCardDownImgBase();
		if(!StringUtil.isEmpty(downImgBase)){
			String idCardDownImg = FileConstant.FILE_PATH + FileConstant.USER_IDCARD + "/" + idCard + "downImg.jpg";
			FileUtil.saveBase64(downImgBase, idCardDownImg);
			userInfo.setIdCardDownImg(idCardDownImg);
		}
		String photoBase = userInfo.getPhotoBase();
		if(!StringUtil.isEmpty(photoBase)){
			String photoImg = FileConstant.FILE_PATH + FileConstant.USER_IDCARD + "/" + idCard + "photo.jpg";
			FileUtil.saveBase64(photoBase, photoImg);
			userInfo.setPhoto(photoImg);
		}
	}
}











