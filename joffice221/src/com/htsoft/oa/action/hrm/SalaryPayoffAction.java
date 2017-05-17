package com.htsoft.oa.action.hrm;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.hrm.SalaryPayoff;
import com.htsoft.oa.model.hrm.StandSalaryItem;
import com.htsoft.oa.service.hrm.SalaryPayoffService;
import com.htsoft.oa.service.hrm.StandSalaryItemService;

/**
 * 薪酬管理
 * 
 * @author
 * 
 */
public class SalaryPayoffAction extends BaseAction {
	@Resource
	private SalaryPayoffService salaryPayoffService;
	@Resource
	private StandSalaryItemService standSalaryItemService;
	private SalaryPayoff salaryPayoff;

	private Long recordId;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public SalaryPayoff getSalaryPayoff() {
		return salaryPayoff;
	}

	public void setSalaryPayoff(SalaryPayoff salaryPayoff) {
		this.salaryPayoff = salaryPayoff;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<SalaryPayoff> list = salaryPayoffService.getAll(filter);
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
				salaryPayoffService.remove(new Long(id));
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
		SalaryPayoff salaryPayoff = salaryPayoffService.get(recordId);
		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toDataJson(salaryPayoff);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (salaryPayoff.getRecordId() == null) {
			salaryPayoff.setCheckStatus(SalaryPayoff.CHECK_FLAG_NONE);
			salaryPayoff.setRegTime(new Date());
			salaryPayoff
					.setRegister(ContextUtil.getCurrentUser().getFullname());
		}
		BigDecimal acutalAmount = salaryPayoff.getStandAmount()
				.add(salaryPayoff.getEncourageAmount())
				.subtract(salaryPayoff.getDeductAmount());
		if (salaryPayoff.getAchieveAmount().compareTo(new BigDecimal(0)) == 1) {
			acutalAmount = acutalAmount.add(salaryPayoff.getAchieveAmount());
		}
		salaryPayoff.setAcutalAmount(acutalAmount);
		salaryPayoffService.save(salaryPayoff);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 审核薪酬发放
	 * 
	 * @return
	 */
	public String check() {
		SalaryPayoff checkSalaryPayoff = salaryPayoffService.get(new Long(
				recordId));
		checkSalaryPayoff.setCheckTime(new Date());
		checkSalaryPayoff.setCheckName(ContextUtil.getCurrentUser()
				.getFullname());
		checkSalaryPayoff.setCheckStatus(salaryPayoff.getCheckStatus());
		checkSalaryPayoff.setCheckOpinion(salaryPayoff.getCheckOpinion());
		salaryPayoffService.save(checkSalaryPayoff);
		return SUCCESS;
	}

	/**
	 * 查询个人薪酬
	 * 
	 * @return
	 */
	public String personal() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<SalaryPayoff> list = salaryPayoffService.getAll(filter);

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:[");
		for (SalaryPayoff salaryDetail : list) {
			buff.append("{recordId:'").append(salaryDetail.getRecordId())
					.append("',fullname:'").append(salaryDetail.getFullname())
					.append("',profileNo:'")
					.append(salaryDetail.getProfileNo()).append("',idNo:'")
					.append(salaryDetail.getIdNo()).append("',standAmount:'")
					.append(salaryDetail.getStandAmount())
					.append("',acutalAmount:'")
					.append(salaryDetail.getAcutalAmount())
					.append("',startTime:'")
					.append(salaryDetail.getStartTime()).append("',endTime:'")
					.append(salaryDetail.getEndTime())
					.append("',checkStatus:'")
					.append(salaryDetail.getCheckStatus());
			List<StandSalaryItem> items = standSalaryItemService
					.getAllByStandardId(salaryDetail.getStandardId());
			StringBuffer content = new StringBuffer(
					"<table class=\"table-info\" cellpadding=\"0\" cellspacing=\"1\" width=\"98%\" align=\"center\"><tr>");

			if (salaryDetail.getEncourageAmount() != new BigDecimal(0)
					&& salaryDetail.getEncourageAmount() != null) {// 奖励金额明细
				content.append("<th>").append("奖励金额</th><td>￥")
						.append(salaryDetail.getEncourageAmount())
						.append("</td>");
			}

			if (salaryDetail.getEncourageAmount() != new BigDecimal(0)
					&& salaryDetail.getEncourageAmount() != null) {// 扣除金额明细
				content.append("<th>").append("扣除金额</th><td>￥")
						.append(salaryDetail.getDeductAmount()).append("</td>");
			}

			if (salaryDetail.getEncourageAmount() != new BigDecimal(0)
					&& salaryDetail.getEncourageAmount() != null) {// 效绩金额明细
				content.append("<th>").append("效绩金额</th><td>￥")
						.append(salaryDetail.getAchieveAmount())
						.append("</td>");
			}
			content.append("</tr></table><table class=\"table-info\" cellpadding=\"0\" cellspacing=\"1\" width=\"98%\" align=\"center\"><tr>");
			// content.append("<th colspan=\"").append(items.size())
			// .append("\">薪酬项目</th></tr><tr>");
			for (StandSalaryItem item : items) {
				content.append("<th>").append(item.getItemName())
						.append("</th>");
			}
			content.append("</tr><tr>");
			for (StandSalaryItem item : items) {
				content.append("<td>￥").append(item.getAmount())
						.append("</td>");
			}
			content.append("</tr></table>");
			buff.append("',content:'").append(content.toString()).append("'},");
		}
		if (list.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]}");

		jsonString = buff.toString();
		return SUCCESS;
	}
}
