package com.mdoa.personnel.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.base.model.BaseModel;
import com.mdoa.constant.Constant;
import com.mdoa.constant.FileConstant;
import com.mdoa.personnel.bo.PackForm;
import com.mdoa.personnel.model.PersonPack;
import com.mdoa.personnel.model.PersonPackPhoto;
import com.mdoa.personnel.service.PersonPackService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.FileUtil;
import com.mdoa.util.PageModel;

@RestController
@RequestMapping("personnel")
public class PersonPackController extends BaseController{
	
	@Autowired
	private PersonPackService personPackService;
	
	/**
	 * 查询试用期的员工
	 * @param packForm
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectTryUserById.do")
	public String selectTryUserById(PackForm packForm){
		try{
			PageModel<PackForm> pageModel = personPackService.selectTryUserById(packForm);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 转正员工
	 * @param userInfo
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updateTryUser.do")
	public String updateTryUser(UserInfo userInfo){
		try{
			personPackService.updateTryUser(userInfo);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据时间查询个人合同信息
	 * @param model
	 * @param pack
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectPackByTime.do")
	public String selectPackByTime(PersonPack personPack){	
		try{
			PageModel<PersonPack> pageModel = personPackService.selectPackByTime(personPack);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据packid查询合同图片的信息
	 * @param packId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectPhotoByPackId.do")
	public String selectPhotoByPackId(PersonPackPhoto packPhoto){
		try{
			List<PersonPackPhoto> list = personPackService.selectPhotoByPackId(packPhoto);
			Gson gson = new Gson();
			return gson.toJson(list);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 *保存个人合同信息 
	 * @param personPack
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("insertPack.do")
	public String insertPack(PersonPack personPack, HttpServletRequest request){
		
		try{
			personPackService.insertPack(personPack, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 修改个人合同信息
	 * @param personPack
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updatePack.do")
	public String updatePack(PersonPack personPack,HttpServletRequest request,Model model){
		
		try{
			personPackService.updatePack(personPack, request);
			request.setAttribute("personPack", personPack);
			model.addAttribute("personPack", personPack);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
		
	}
	/**
	 * 删除个人合同信息
	 * @param userId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("deletePack.do")
	public String deletePack(String packId, HttpServletRequest request){
		
		try{
			personPackService.deletePack(packId, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
//==================================================================================
	
	/**
	 * 上传图片
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("uploadImage.do")
	public String uploadImage(MultipartFile file,HttpServletRequest request,PersonPackPhoto packPhoto){
		try {
			//判断文件是否为空
			if(!file.isEmpty()){
				UserInfo user = getUser(request);
				List<PersonPackPhoto> list = personPackService.selectPhotoByPackId(packPhoto);
				int i = list.size();
				int page = i + 1;
				String bigImageUrl = FileConstant.FILE_PATH + FileConstant.PERSONNEL_PACK +"/"+ file.getOriginalFilename();
				String smallImageUrl = FileUtil.uploadImage(file, bigImageUrl, 500);
				packPhoto.setPhotoName(file.getOriginalFilename());
				packPhoto.setUrl(bigImageUrl);
				packPhoto.setUrlSmall(smallImageUrl);
				packPhoto.setCurrentPage(page);
				packPhoto.setCreateUserId(user.getCreateUserId());
				personPackService.insertPackPhoto(packPhoto, request);
			}
			return Constant.SUCCESS_CODE;
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
		
	}
	
	/**
	 * 图片下载
	 * @param file
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("downLoadImage.do")
	public String  downLoadImage(HttpServletRequest request, HttpServletResponse response,PersonPackPhoto packPhoto){
		try{
			FileUtil.download(request, response, packPhoto.getUrl(),  packPhoto.getPhotoName());
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 通过创建时间的先后 查询合同照片
	 * @param model
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectPackPhotoByTime.do")
	public String selectPackPhotoByTime(BaseModel model){
		try{
			PageModel<PersonPackPhoto> pageModel = personPackService.selectPackPhotoByTime(model);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
			
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}	
	}
	
	/**
	 * 删除合同照片信息
	 * @param photoId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("deletePackPhoto.do")
	public String  deletePackPhoto(String photoId, HttpServletRequest request){
		try{
			personPackService.deletePackPhoto(photoId, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
		
	}
	
	/**
	 * 修改合同照片的信息
	 * @param photoId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updatePackPhoto.do")
	public String updatePackPhoto(String photoId, HttpServletRequest request){
		try{
			personPackService.updatePackPhoto(photoId, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 删除合同下的一张照片
	 * @param packId
	 * @param photoId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("deletePackPhotoByPackId.do")
	public String deletePackPhotoByPackId(String packId,String photoId){
		try{
			personPackService.deletePackPhotoByPackId(packId, photoId);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
}
