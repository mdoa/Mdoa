package com.htsoft.oa.action.personal;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.personal.Duty;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.personal.DutyService;
import com.htsoft.oa.service.system.AppUserService;

/**
 * 排班管理
 * 
 * @author csx
 * 
 */
public class DutyAction extends BaseAction {
	@Resource
	private DutyService dutyService;
	@Resource
	private AppUserService appUserService;

	private Duty duty;

	private Long dutyId;

	public Long getDutyId() {
		return dutyId;
	}

	public void setDutyId(Long dutyId) {
		this.dutyId = dutyId;
	}

	public Duty getDuty() {
		return duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<Duty> list = dutyService.getAll(filter);

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
				dutyService.remove(new Long(id));
			}
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		Duty duty = dutyService.get(dutyId);
		jsonString = mapper.toDataJson(duty);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {

		// 可能存在多个用户的选择
		String userId = getRequest().getParameter("duty.userId");

		String[] uIds = userId.split("[,]");

		// 用于存储已经存在用户班制的用户姓名
		StringBuffer sb = new StringBuffer("");
		boolean isExcept = false;
		if (uIds != null) {
			for (int i = 0; i < uIds.length; i++) {
				AppUser user = appUserService.get(new Long(uIds[i]));
				Duty uDuty = new Duty();
				try {
					// 检查该用户目前这段时间内是否已经添加了班制,若添加了则需要提示
					BeanUtil.copyNotNullProperties(uDuty, duty);

					boolean isExist = false;
					if (uDuty.getDutyId() == null) {
						isExist = dutyService.isExistDutyForUser(
								user.getUserId(), uDuty.getStartTime(),
								uDuty.getEndTime());
					}
					if (isExist) {
						isExcept = true;
						sb.append(user.getFullname()).append(",");
					} else {
						uDuty.setAppUser(user);
						uDuty.setFullname(user.getFullname());
						dutyService.save(uDuty);
					}

				} catch (Exception ex) {
					logger.error("error:" + ex.getMessage());
				}
			}

		}

		if (isExcept) {//
			sb.append("在该时间(")
					.append(DateFormatUtil.formatDate(duty.getStartTime()))
					.append("至")
					.append(DateFormatUtil.formatDate(duty.getEndTime()))
					.append(")内已经存在班制!");
		}

		setJsonString("{success:true,exception:'" + sb.toString() + "'}");
		return SUCCESS;
	}
}
