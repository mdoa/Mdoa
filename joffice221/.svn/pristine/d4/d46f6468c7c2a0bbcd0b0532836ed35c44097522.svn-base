package com.htsoft.oa.action.task;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.jms.MailMessageProducer;
import com.htsoft.core.jms.MobileMessageProducer;
import com.htsoft.core.model.MailModel;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.SmsMobile;
import com.htsoft.oa.model.info.ShortMessage;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.task.Appointment;
import com.htsoft.oa.service.communicate.SmsMobileService;
import com.htsoft.oa.service.info.ShortMessageService;
import com.htsoft.oa.service.task.AppointmentService;

/**
 * 
 * @author csx
 * 
 */
public class AppointmentAction extends BaseAction {
	@Resource
	private AppointmentService appointmentService;

	@Resource
	private ShortMessageService shortMessageService;

	@Resource
	private MailMessageProducer mailMessageProducer;

	@Resource
	private MobileMessageProducer mobileMessageProducer;

	@Resource
	private SmsMobileService smsMobileService;
	private Appointment appointment;

	private String sendMessage;
	private Long appointId;
	private List<Appointment> list;

	public String getSendMessage() {
		return sendMessage;
	}

	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}

	public List<Appointment> getList() {
		return list;
	}

	public void setList(List<Appointment> list) {
		this.list = list;
	}

	public Long getAppointId() {
		return appointId;
	}

	public void setAppointId(Long appointId) {
		this.appointId = appointId;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<Appointment> list = appointmentService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

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
				appointmentService.remove(new Long(id));
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
		Appointment appointment = appointmentService.get(appointId);

		jsonString = mapper.toDataJson(appointment);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		AppUser appUser = ContextUtil.getCurrentUser();
		appointment.setAppUser(appUser);
		String userId = ContextUtil.getCurrentUserId().toString();
		String isMsg = getRequest().getParameter("appointment.sendMessage");
		String isMail = getRequest().getParameter("appointment.sendMail");

		StringBuffer msgContent = new StringBuffer(
				"您的约会主题为:<font color=\"green\">");

		msgContent
				.append(appointment.getSubject())
				.append(",地点：")
				.append(appointment.getLocation())
				.append("</font>")
				.append(",请在<font color=\"red\">")
				.append(DateFormatUtil.formatTimeNoSecond(appointment
						.getStartTime()))
				.append(" 至 ")
				.append(DateFormatUtil.formatTimeNoSecond(appointment
						.getEndTime())).append("</font>")
				.append("这时间段中,准时参加您的约会！");

		if (StringUtils.isNotEmpty(isMsg)) {
			// 发送手机短信
			if (appUser.getMobile() != null) {
				if (logger.isDebugEnabled()) {
					logger.info("Notice " + appUser.getFullname()
							+ " by mobile:" + appUser.getMobile());
				}
				SmsMobile smsMobile = new SmsMobile();
				smsMobile.setPhoneNumber(appUser.getMobile());
				smsMobile.setSmsContent(msgContent.toString());
				smsMobile.setSendTime(new Date());
				smsMobile.setUserId(-1l);
				smsMobile.setUserName("system user");
				smsMobile.setStatus(SmsMobile.STATUS_NOT_SENDED);

				smsMobileService.save(smsMobile);
				// 放置发送队列
				mobileMessageProducer.send(smsMobile);
			}
		}
		Date curDate = new Date();
		String curDateStr = DateFormatUtil.formatTimeNoSecond(curDate);
		if (StringUtils.isNotEmpty(isMail)) {
			// 发送邮件
			if (appUser.getEmail() != null) {
				if (logger.isDebugEnabled()) {
					logger.info("Notice " + appUser.getFullname() + " by mail:"
							+ appUser.getEmail());
				}
				String tempPath = "mail/flowMail.vm";
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("curDateStr", curDateStr);
				model.put("appUser", appUser);
				model.put("appointment", appointment);
				String subject = "来自" + AppUtil.getCompanyName() + "我的约会("
						+ appointment.getSubject() + "--地址:"
						+ appointment.getLocation() + ")提醒";
				MailModel mailModel = new MailModel();
				mailModel.setMailTemplate(tempPath);
				mailModel.setTo(appUser.getEmail());
				mailModel.setSubject(subject);
				mailModel.setMailData(model);
				mailModel.setContent(msgContent.toString());
				// 把邮件加至发送列队中去
				mailMessageProducer.send(mailModel);
			}

		}
		// 发送系统提示信息
		shortMessageService.save(AppUser.SYSTEM_USER, userId,
				msgContent.toString(), ShortMessage.MSG_TYPE_SYS);
		appointmentService.save(appointment);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 首页显示列表
	 */
	public String display() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		filter.addSorted("appointId", "desc");
		List<Appointment> list = appointmentService.getAll(filter);
		getRequest().setAttribute("appointmentList", list);
		return "display";
	}

}
