package com.mdoa.repertory.model.highcharts;

import java.util.List;

public class CastGraphicModel {
	
	private List<String> categories;
	private List<CastDataModel> datas;
	
	public CastGraphicModel(){}
	
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<CastDataModel> getDatas() {
		return datas;
	}
	public void setDatas(List<CastDataModel> datas) {
		this.datas = datas;
	}
	
}
