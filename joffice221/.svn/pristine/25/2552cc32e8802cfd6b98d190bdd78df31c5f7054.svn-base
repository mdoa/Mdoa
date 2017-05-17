package com.htsoft.oa.action.personal;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.util.DateUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.personal.Duty;
import com.htsoft.oa.model.personal.DutyRegister;
import com.htsoft.oa.model.personal.DutySection;
import com.htsoft.oa.model.personal.DutySystem;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.personal.DutyRegisterService;
import com.htsoft.oa.service.personal.DutySectionService;
import com.htsoft.oa.service.personal.DutyService;
import com.htsoft.oa.service.personal.DutySystemService;
import com.htsoft.oa.service.system.AppUserService;

/**
 * 
 * @author
 * 
 */
public class DutyRegisterAction extends BaseAction {
	@Resource
	private DutyRegisterService dutyRegisterService;
	@Resource
	private DutyService dutyService;
	@Resource
	private DutySystemService dutySystemService;
	@Resource
	private DutySectionService dutySectionService;
	@Resource
	private AppUserService appUserService;

	private DutyRegister dutyRegister;

	private Long registerId;

	public Long getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}

	public DutyRegister getDutyRegister() {
		return dutyRegister;
	}

	public void setDutyRegister(DutyRegister dutyRegister) {
		this.dutyRegister = dutyRegister;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<DutyRegister> list = dutyRegisterService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;

	}

	/**
	 * 显示个人考勤信息列表
	 * 
	 * @return
	 */
	public String person() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.addSorted("registerDate", QueryFilter.ORDER_DESC);
		List<DutyRegister> list = dutyRegisterService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;
	}

	/**
	 * 显示用户的班次，供用户进行签到及签退
	 * 
	 * @return
	 */
	public String today() {
		Long currentUserId = ContextUtil.getCurrentUserId();
		DutySystem dutySystem = null;
		// 取到当前用户的班次
		Duty duty = dutyService.getCurUserDuty(currentUserId);

		if (duty != null) {
			dutySystem = duty.getDutySystem();
		} else {
			dutySystem = dutySystemService.getDefaultDutySystem();
		}

		if (dutySystem == null) {
			setJsonString("{success:true,exception:'尚未为用户设置排班，请联系管理员!'}");
		} else {

			String dutySetting = dutySystem.getSystemSetting();
			// 分割为7天
			String[] day7Sections = dutySetting.split("[|]");

			Calendar curCal = Calendar.getInstance();

			// 取到当前为几天
			int curDay = curCal.get(Calendar.DAY_OF_WEEK);
			// 当天的班制
			String[] curDaySections = day7Sections[curDay - 1].split("[,]");

			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < curDaySections.length; i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				if ("-".equals(curDaySections[i])) {// -代表休息
					map.put("systemName", "用户排班今天休息");
					map.put("allowSignIn", "2");
					map.put("allowSignOff", "2");
					list.add(map);
					continue;
				}
				DutySection dutySection = dutySectionService.get(new Long(
						curDaySections[i]));
				// 取到当前用户签到的记录

				DutyRegister signInReg = dutyRegisterService
						.getTodayUserRegister(currentUserId,
								DutyRegister.SIGN_IN,
								dutySection.getSectionId());
				// 取到当前用户签退的记录
				DutyRegister signOffReg = dutyRegisterService
						.getTodayUserRegister(currentUserId,
								DutyRegister.SIGN_OFF,
								dutySection.getSectionId());

				// 添加是否允许签到及签退
				String signInTime = "";
				String signInFlag = "";
				String allowSignIn = "";
				if (signInReg != null) {// 已经签到
					signInTime = signInReg.getRegisterTime();
					signInFlag = String.valueOf(signInReg.getRegFlag());
				} else {
					// 判断当前时间是否在签到开始和签到结束时间之间，若是，表示当前是可以进行签到
					Calendar startSignInCal = Calendar.getInstance();
					startSignInCal.setTime(dutySection.getStartSignin());
					DateUtil.copyYearMonthDay(startSignInCal, curCal);

					Calendar endSignInCal = Calendar.getInstance();
					endSignInCal.setTime(dutySection.getEndSignin());
					DateUtil.copyYearMonthDay(endSignInCal, curCal);

					int startCmpResult = curCal.compareTo(startSignInCal);
					int endCmpResult = curCal.compareTo(endSignInCal);
					if (startCmpResult >= 0 && endCmpResult <= 0) {
						allowSignIn = "1";
					} else if (startCmpResult < 0) {// 尚未到签到时间
						allowSignIn = "-1";
					} else {// 已过签到时间
						allowSignIn = "0";
					}
				}

				// 添加是否允许签退
				String signOffTime = "";
				String signOffFlag = "";
				String allowSignOff = "";
				if (signOffReg != null) {// 已经签退
					signOffTime = signOffReg.getRegisterTime();
					signOffFlag = String.valueOf(signOffReg.getRegFlag());
					allowSignOff = "0";
				} else {
					// 判断当前时间是否在签退开始和签退结束时间之间，若是，表示当前是可以进行签退
					Calendar startSignOffCal = Calendar.getInstance();
					startSignOffCal.setTime(dutySection.getEarlyOffTime());
					DateUtil.copyYearMonthDay(startSignOffCal, curCal);

					Calendar endSignOffCal = Calendar.getInstance();
					endSignOffCal.setTime(dutySection.getSignOutTime());
					DateUtil.copyYearMonthDay(endSignOffCal, curCal);

					int startCmpResult = curCal.compareTo(startSignOffCal);
					int endCmpResult = curCal.compareTo(endSignOffCal);

					if (startCmpResult >= 0 && endCmpResult <= 0) {
						allowSignOff = "1";
					} else if (startCmpResult < 0) {// 尚未到签退时间
						allowSignOff = "-1";
					} else {// 已过签退时间
						allowSignOff = "0";
					}
				}

				map.put("sectionId", dutySection.getSectionId());
				map.put("systemName", dutySection.getSectionName());
				map.put("startSignin", dutySection.getStartSignin());
				map.put("dutyStartTime", dutySection.getDutyStartTime());
				map.put("endSignin", dutySection.getEndSignin());
				map.put("earlyOffTime", dutySection.getEarlyOffTime());
				map.put("dutyEndTime", dutySection.getDutyEndTime());
				map.put("signOutTime", dutySection.getSignOutTime());

				map.put("signInTime", signInTime);
				map.put("signInFlag", signInFlag);
				map.put("allowSignIn", allowSignIn);

				map.put("signOffTime", signOffTime);
				map.put("signOffFlag", signOffFlag);
				map.put("allowSignOff", allowSignOff);

				list.add(map);
			}
			mapper.setDateFormat(DateFormatUtil.TIME_NOSECOND_FORMAT);
			jsonString = mapper.toResultJson(list);
		}
		dutySystemService.evict(dutySystem);

		return SUCCESS;
	}

	/**
	 * 签到
	 */
	public String signIn() {
		String sectionId = getRequest().getParameter("sectionId");
		dutyRegisterService.signInOff(new Long(sectionId),
				DutyRegister.SIGN_IN, ContextUtil.getCurrentUser(), new Date());
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 签退
	 */
	public String signOff() {
		String sectionId = getRequest().getParameter("sectionId");
		dutyRegisterService
				.signInOff(new Long(sectionId), DutyRegister.SIGN_OFF,
						ContextUtil.getCurrentUser(), new Date());
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 点击图片进行签到签退
	 * 
	 * @return
	 */
	public String signInOff() {
		Long currentUserId = ContextUtil.getCurrentUserId();
		DutySystem dutySystem = null;
		// 取到当前用户的班次
		Duty duty = dutyService.getCurUserDuty(currentUserId);

		if (duty != null) {
			dutySystem = duty.getDutySystem();
		} else {
			dutySystem = dutySystemService.getDefaultDutySystem();
		}

		if (dutySystem == null) {
			jsonString = "{success:false,message:'尚未为用户设置排班，请联系管理员!'}";
		} else {
			String dutySetting = dutySystem.getSystemSetting();
			// 分割为7天
			String[] day7Sections = dutySetting.split("[|]");

			Calendar curCal = Calendar.getInstance();

			// 取到当前为几天
			int curDay = curCal.get(Calendar.DAY_OF_WEEK);
			// 当天的班制
			String[] curDaySections = day7Sections[curDay - 1].split("[,]");
			for (int i = 0; i < curDaySections.length; i++) {
				if ("-".equals(curDaySections[i])) {// -代表休息
					jsonString = "{success:false,message:'用户排班今天休息，不需要签到!'}";
					break;
				}
				DutySection dutySection = dutySectionService.get(new Long(
						curDaySections[i]));
				// 取到当前用户签到的记录
				DutyRegister signInReg = dutyRegisterService
						.getTodayUserRegister(currentUserId,
								DutyRegister.SIGN_IN,
								dutySection.getSectionId());
				// 取到当前用户签退的记录
				DutyRegister signOffReg = dutyRegisterService
						.getTodayUserRegister(currentUserId,
								DutyRegister.SIGN_OFF,
								dutySection.getSectionId());
				// 判断当前时间是否在签到开始和签到结束时间之间，若是，表示当前是可以进行签到
				Calendar startSignInCal = Calendar.getInstance();
				startSignInCal.setTime(dutySection.getStartSignin());
				DateUtil.copyYearMonthDay(startSignInCal, curCal);

				Calendar endSignInCal = Calendar.getInstance();
				endSignInCal.setTime(dutySection.getEndSignin());
				DateUtil.copyYearMonthDay(endSignInCal, curCal);

				// 判断当前时间是否在签退开始和签退结束时间之间，若是，表示当前是可以进行签退
				Calendar startSignOffCal = Calendar.getInstance();
				startSignOffCal.setTime(dutySection.getEarlyOffTime());
				DateUtil.copyYearMonthDay(startSignOffCal, curCal);

				Calendar endSignOffCal = Calendar.getInstance();
				endSignOffCal.setTime(dutySection.getSignOutTime());
				DateUtil.copyYearMonthDay(endSignOffCal, curCal);

				if (signInReg != null && signOffReg != null) {// 已经签到或签退时间
					jsonString = "{success:false,message:'用户已经签到和签退了，不需要签到!'}";
					break;
				} else if (signInReg != null) {// 签到不为空
					// 判断签退时间
					int startCmpResult = curCal.compareTo(startSignOffCal);
					int endCmpResult = curCal.compareTo(endSignOffCal);

					if (startCmpResult >= 0 && endCmpResult <= 0) {
						jsonString = "{success:true,type:'2',sectionId:'"
								+ dutySection.getSectionId() + "'}";
					} else if (startCmpResult < 0) {// 尚未到签退时间
						jsonString = "{success:false,message:'尚未到签退时间!'}";
					} else {// 已过签退时间
						jsonString = "{success:false,message:'已过签退时间!'}";
					}
				} else if (signOffReg != null) {// 签退不为空
					// 判断签到时间
					int startCmpResult = curCal.compareTo(startSignInCal);
					int endCmpResult = curCal.compareTo(endSignInCal);
					if (startCmpResult >= 0 && endCmpResult <= 0) {
						jsonString = "{success:true,type:'1',sectionId:'"
								+ dutySection.getSectionId() + "'}";
					} else if (startCmpResult < 0) {// 尚未到签到时间
						jsonString = "{success:false,message:'尚未到签到时间!'}";
					} else {// 已过签到时间
						jsonString = "{success:false,message:'已过签到时间!'}";
					}
				} else {
					int startSignInResult = curCal.compareTo(startSignInCal);
					int endSignInResult = curCal.compareTo(endSignInCal);

					int startSignOffResult = curCal.compareTo(startSignOffCal);
					int endSignOffResult = curCal.compareTo(endSignOffCal);

					if (startSignInResult >= 0 && endSignOffResult <= 0) {
						if (startSignInResult >= 0 && endSignInResult <= 0) {
							jsonString = "{success:true,type:'1',sectionId:'"
									+ dutySection.getSectionId() + "'}";
						} else if (startSignOffResult >= 0
								&& endSignOffResult <= 0) {
							jsonString = "{success:true,type:'2',sectionId:'"
									+ dutySection.getSectionId() + "'}";
						} else {
							jsonString = "{success:false,message:已过过了签到时间，尚未到签退时间!'}";
						}
					} else {
						jsonString = "{success:false,message:'尚未到签到、签退时间!'}";
					}
				}
			}

		}
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				dutyRegisterService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		DutyRegister dutyRegister = dutyRegisterService.get(registerId);

		jsonString = mapper.toDataJson(dutyRegister);

		return SUCCESS;
	}

	/**
	 * 补签
	 */
	public String save() {
		HttpServletRequest request = getRequest();

		String userIds = request.getParameter("userIds");
		Long sectionId = new Long(request.getParameter("sectionId"));
		Short inOffFlag = new Short(request.getParameter("inOffFlag"));

		Date registerDate = DateUtil.parseDate(request
				.getParameter("registerDate"));

		String[] uIds = userIds.split("[,]");
		for (int i = 0; i < uIds.length; i++) {
			AppUser appUser = appUserService.get(new Long(uIds[i]));
			dutyRegisterService.signInOff(sectionId, inOffFlag, appUser,
					registerDate);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

}
