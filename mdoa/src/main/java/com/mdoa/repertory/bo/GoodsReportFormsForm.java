package com.mdoa.repertory.bo;

/**
 * 物品领用报表表单
 * 
 * @author Administrator
 *
 */
public class GoodsReportFormsForm {

	public String departmentIds;// 部门id以‘，’分隔
	public String goodsTypeUrls;// 查询物品类别url
	public String goodsTypeIds;// 查询物品类别id
	private String startTime;// 查询开始时间
	private String endTime;// 查询结束时间

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		String[] ids = departmentIds.split(",");
		departmentIds = "";
		for (int i = 0; i < ids.length; i++) {
			if (i != ids.length - 1) {
				departmentIds += "'" + ids[i] + "',";
			} else {
				departmentIds += "'" + ids[i] + "'";
			}
		}
		this.departmentIds = departmentIds;
	}

	public String getGoodsTypeUrls() {
		return goodsTypeUrls;
	}

	public void setGoodsTypeUrls(String goodsTypeUrls) {
		String[] urls = goodsTypeUrls.split(",");
		goodsTypeUrls = "";
		for (int i = 0; i < urls.length; i++) {
			if (i != urls.length - 1) {
				goodsTypeUrls += "'" + urls[i] + "',";
			} else {
				goodsTypeUrls += "'" + urls[i] + "'";
			}
		}
		this.goodsTypeUrls = goodsTypeUrls;
	}

	public String getGoodsTypeIds() {
		return goodsTypeIds;
	}

	public void setGoodsTypeIds(String goodsTypeIds) {
		String[] ids = goodsTypeIds.split(",");
		goodsTypeIds = "";
		for (int i = 0; i < ids.length; i++) {
			if (i != ids.length - 1) {
				goodsTypeIds += "'" + ids[i] + "',";
			} else {
				goodsTypeIds += "'" + ids[i] + "'";
			}
		}
		this.goodsTypeIds = goodsTypeIds;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
