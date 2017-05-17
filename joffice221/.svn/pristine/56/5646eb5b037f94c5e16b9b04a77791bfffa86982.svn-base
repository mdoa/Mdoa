package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.CartRepair;
import com.htsoft.oa.service.admin.CartRepairService;

/**
 * 车辆维修管理Action
 * 
 * @description 车辆维修管理
 * @author csx
 * 
 */
public class CartRepairAction extends BaseAction {
	/** 车辆维修Service */
	@Resource
	private CartRepairService cartRepairService;

	/** 车辆维修 */
	private CartRepair cartRepair;
	/** 车辆维修ID */
	private Long repairId;

	/**
	 * @return the cartRepair
	 */
	public CartRepair getCartRepair() {
		return cartRepair;
	}

	/**
	 * @param cartRepair
	 *            the cartRepair to set
	 */
	public void setCartRepair(CartRepair cartRepair) {
		this.cartRepair = cartRepair;
	}

	/**
	 * @return the repairId
	 */
	public Long getRepairId() {
		return repairId;
	}

	/**
	 * @param repairId
	 *            the repairId to set
	 */
	public void setRepairId(Long repairId) {
		this.repairId = repairId;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<CartRepair> list = cartRepairService.getAll(filter);

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
				cartRepairService.remove(new Long(id));
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
		CartRepair cartRepair = cartRepairService.get(repairId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
		jsonString = mapper.toDataJson(cartRepair);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		cartRepairService.save(cartRepair);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
