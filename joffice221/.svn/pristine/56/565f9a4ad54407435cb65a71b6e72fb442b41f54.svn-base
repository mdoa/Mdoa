package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.GoodsApply;
import com.htsoft.oa.model.admin.OfficeGoods;
import com.htsoft.oa.model.info.ShortMessage;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.admin.GoodsApplyService;
import com.htsoft.oa.service.admin.OfficeGoodsService;
import com.htsoft.oa.service.info.ShortMessageService;

/**
 * 商品申请
 * 
 * @author csx
 * 
 */
public class GoodsApplyAction extends BaseAction {

	@Resource
	private GoodsApplyService goodsApplyService;
	private GoodsApply goodsApply;
	@Resource
	private ShortMessageService shortMessageService;
	@Resource
	private OfficeGoodsService officeGoodsService;

	private Long applyId;

	public Long getApplyId() {
		return applyId;
	}

	public void setApplyId(Long applyId) {
		this.applyId = applyId;
	}

	public GoodsApply getGoodsApply() {
		return goodsApply;
	}

	public void setGoodsApply(GoodsApply goodsApply) {
		this.goodsApply = goodsApply;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<GoodsApply> list = goodsApplyService.getAll(filter);
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
				goodsApplyService.remove(new Long(id));
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
		GoodsApply goodsApply = goodsApplyService.get(applyId);
		jsonString = mapper.toDataJson(goodsApply);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (goodsApply.getApplyId() != null) {
			GoodsApply orgGoodsApply = goodsApplyService.get(goodsApply
					.getApplyId());
			try {
				BeanUtil.copyNotNullProperties(orgGoodsApply, goodsApply);

				if (orgGoodsApply.getApprovalStatus() == GoodsApply.PASS_APPLY) {
					OfficeGoods officeGoods = officeGoodsService
							.get(orgGoodsApply.getGoodsId());
					Integer con = orgGoodsApply.getUseCounts();
					Integer least = officeGoods.getStockCounts() - con;
					if (least < 0) {
						setJsonString("{success:false,message:'库存不足!'}");
						return SUCCESS;
					}
					Long receiveId = orgGoodsApply.getUserId();
					String content = "你申请的办公用品为" + officeGoods.getGoodsName()
							+ "已经通过审批，请查收";
					shortMessageService.save(AppUser.SYSTEM_USER,
							receiveId.toString(), content,
							ShortMessage.MSG_TYPE_SYS);
					officeGoods.setStockCounts(least);
					officeGoodsService.save(officeGoods);
				}
				goodsApplyService.save(orgGoodsApply);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			goodsApplyService.save(goodsApply);
		}

		setJsonString("{success:true}");
		return SUCCESS;
	}

}
