package com.htsoft.oa.action.flow;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.FormRule;
import com.htsoft.oa.service.flow.FormRuleService;

/**
 * 表单验证规则
 * 
 * @author
 * 
 */
public class FormRuleAction extends BaseAction {
	@Resource
	private FormRuleService formRuleService;
	private FormRule formRule;

	private Long ruleId;

	public Long getRuleId() {
		return ruleId;
	}

	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}

	public FormRule getFormRule() {
		return formRule;
	}

	public void setFormRule(FormRule formRule) {
		this.formRule = formRule;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<FormRule> list = formRuleService.getAll(filter);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
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
				formRuleService.remove(new Long(id));
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
		FormRule formRle = formRuleService.get(ruleId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(formRle);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (formRule.getRuleId() == null) {
			formRuleService.save(formRule);
		} else {
			FormRule orgFormRule = formRuleService.get(formRule.getRuleId());
			try {
				BeanUtil.copyNotNullProperties(orgFormRule, formRule);
				formRuleService.save(orgFormRule);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;

	}

	/**
	 * 获得所有的验证规则
	 * 
	 * @return
	 */
	public String getAll() {
		List<FormRule> list = formRuleService.getAll();
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toJson(list);
		return SUCCESS;
	}

}
