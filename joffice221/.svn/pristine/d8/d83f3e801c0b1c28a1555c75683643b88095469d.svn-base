package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.Car;
import com.htsoft.oa.model.admin.CarApply;
import com.htsoft.oa.model.info.ShortMessage;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.admin.CarApplyService;
import com.htsoft.oa.service.admin.CarService;
import com.htsoft.oa.service.info.ShortMessageService;

/**
 * 车辆申请管理Action
 * 
 * @description 车辆申请管理
 * @author csx
 * 
 */
public class CarApplyAction extends BaseAction {
	@Resource
	private CarApplyService carApplyService;
	private CarApply carApply;
	@Resource
	private ShortMessageService shortMessageService;
	@Resource
	private CarService carService;

	private Long applyId;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public CarApply getCarApply() {
		return carApply;
	}

	public void setCarApply(CarApply carApply) {
		this.carApply = carApply;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<CarApply> list = carApplyService.getAll(filter);

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
				carApplyService.remove(new Long(id));
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
		CarApply carApply = carApplyService.get(applyId);
		jsonString = mapper.toDataJson(carApply);
		return SUCCESS;
	}

	/**
	 * 判断该车是否在某个时间段在使用
	 */
	private String judgeCarApply(Long carId, Date startTime, Date endTime) {
		if (startTime != null && endTime != null) {
			List<CarApply> list = carApplyService.findByCarIdAndStartEndTime(
					carId, startTime, endTime);
			if (list != null && list.size() > 0) {
				CarApply ca = list.get(0);
				return "车牌号为[" + ca.getCar().getCarNo() + "]" + "在["
						+ DateFormatUtil.formatDateTime(ca.getStartTime())
						+ " - "
						+ DateFormatUtil.formatDateTime(ca.getEndTime())
						+ "]时间段已经申请！";
			} else {
				return "success";
			}
		} else {
			return "success";
		}
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (carApply.getApplyId() != null) {
			CarApply orgCarApply = carApplyService.get(carApply.getApplyId());
			try {
				BeanUtil.copyNotNullProperties(orgCarApply, carApply);
				String msg = judgeCarApply(carApply.getCarId(),
						carApply.getStartTime(), carApply.getEndTime());
				if (msg.equalsIgnoreCase("success")) { // 可以申请
					carApplyService.save(orgCarApply);
					if (orgCarApply.getApprovalStatus() == Car.PASS_APPLY) {
						Long receiveId = orgCarApply.getUserId();
						Car car = carService.get(orgCarApply.getCar()
								.getCarId());
						String content = "你申请的车牌号为" + car.getCarNo()
								+ "已经通过审批，请注意查收";
						shortMessageService.save(AppUser.SYSTEM_USER,
								receiveId.toString(), content,
								ShortMessage.MSG_TYPE_SYS);
					}
				} else {
					setJsonString("{failure:true,msg:'" + msg + "'}");
					return SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String msg = judgeCarApply(carApply.getCarId(),
					carApply.getStartTime(), carApply.getEndTime());
			if (msg.equalsIgnoreCase("success")) { // 可以申请
				carApplyService.save(carApply);
			} else {
				setJsonString("{failure:true,msg:'" + msg + "'}");
				return SUCCESS;
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
