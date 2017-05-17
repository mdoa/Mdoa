package com.htsoft.oa.action.document;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.document.Seal;
import com.htsoft.oa.service.document.SealService;

/**
 * 印章管理Action
 * 
 * @author
 * 
 */
public class SealAction extends BaseAction {
	@Resource
	private SealService sealService;
	private Seal seal;

	private Long sealId;

	public Long getSealId() {
		return sealId;
	}

	public void setSealId(Long sealId) {
		this.sealId = sealId;
	}

	public Seal getSeal() {
		return seal;
	}

	public void setSeal(Seal seal) {
		this.seal = seal;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<Seal> list = sealService.getAll(filter);

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
				sealService.remove(new Long(id));
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
		Seal seal = sealService.get(sealId);
		jsonString = mapper.toDataJson(seal);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		seal.setBelongId(ContextUtil.getCurrentUserId());
		if (seal.getSealId() == null) {
			sealService.save(seal);
		} else {
			Seal orgSeal = sealService.get(seal.getSealId());
			try {
				BeanUtil.copyNotNullProperties(orgSeal, seal);
				sealService.save(orgSeal);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;

	}
}
