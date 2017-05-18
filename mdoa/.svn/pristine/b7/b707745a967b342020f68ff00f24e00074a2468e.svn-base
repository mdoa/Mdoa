package com.mdoa.repertory.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.repertory.bo.AmountQueryForm;
import com.mdoa.repertory.bo.DynamicBalanceForm;
import com.mdoa.repertory.bo.GoodsMonthBalanceForm;
import com.mdoa.repertory.bo.GoodsPriceForm;
import com.mdoa.repertory.bo.GoodsReportFormsForm;
import com.mdoa.repertory.bo.TypeMonthBalanceForm;
import com.mdoa.repertory.dao.ReportFormsDao;
import com.mdoa.repertory.form.DepartmentUseDataForm;
import com.mdoa.repertory.model.GoodsReportForms;
import com.mdoa.repertory.model.RepertoryGoodsMonthBalance;
import com.mdoa.repertory.model.RepertoryGoodsType;
import com.mdoa.repertory.model.crosstab.CrosstabModel;
import com.mdoa.repertory.model.crosstab.DepartmentModel;
import com.mdoa.repertory.model.crosstab.DepartmentUseModel;
import com.mdoa.repertory.model.crosstab.TypeUseDataModel;
import com.mdoa.repertory.model.highcharts.CastDataModel;
import com.mdoa.repertory.model.highcharts.CastDrilldownModel;
import com.mdoa.repertory.model.highcharts.CastGraphicModel;
import com.mdoa.repertory.model.highcharts.FoldLineModel;
import com.mdoa.repertory.model.highcharts.LineDataModel;
import com.mdoa.repertory.model.highcharts.LineGraphicModel;
import com.mdoa.util.DateUtil;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

/**
 * 报表Service层
 * 
 * @author Administrator
 *
 */
@Service
@Transactional
public class ReportFormsService {

	@Autowired
	private ReportFormsDao reportFormsDao;

	/**
	 * 查询物品领用报表数据
	 * 
	 * @param goodsReportFormsForm
	 * @return
	 */
	public CastGraphicModel findGoodsReportByCondition(GoodsReportFormsForm goodsReportFormsForm) {
		// 处理查询参数
		Map<String, String> params = new HashMap<>();
		params.put("departmentIds", goodsReportFormsForm.getDepartmentIds());
		params.put("goodsTypeIds", goodsReportFormsForm.getGoodsTypeIds());
		params.put("goodsTypeUrls", goodsReportFormsForm.getGoodsTypeUrls());
		params.put("startTime", goodsReportFormsForm.getStartTime());
		params.put("endTime", goodsReportFormsForm.getEndTime());
		// 持久层查询报表数据
		List<GoodsReportForms> reportFormses = reportFormsDao.findGoodsReportByCondition(params);	
		// 饼图对象容器
		CastGraphicModel castGraphicModel = new CastGraphicModel();
		castGraphicModel.setCategories(new ArrayList<>());
		castGraphicModel.setDatas(new ArrayList<>());
		//饼图内环名称容器
		List<String> categories = castGraphicModel.getCategories();
		//饼图内环数据容器
		List<CastDataModel> datas = castGraphicModel.getDatas();
		//遍历数据填入对应容器
		for (int i = 0; i < reportFormses.size(); i++) {
			//获取单行数据
			GoodsReportForms reportForms = reportFormses.get(i);
			if(!categories.contains(reportForms.getDepartmentName())){
				//进入则表明还无该条内环名称，填入		
				categories.add(reportForms.getDepartmentName());
				//单条内环数据容器
				CastDataModel castDataModel = new CastDataModel();
				//单个内环数据容器
				CastDrilldownModel castDrilldownModel = new CastDrilldownModel();
				//设置单个内环对应外环的名称
				castDrilldownModel.setName(reportForms.getDepartmentName());
				//设置单个内环对应的外环色值起始值
				//castDrilldownModel.setColor(RepertoryConstant.getColor());
				//单个内环对应的外环名称容器
				List<String> iCategories = new ArrayList<>();
				//单个内环对应的外环数据容器
				List<Integer> iDatas = new ArrayList<>();
				//单个内环具体数值
				int y = 0;
				//准备单个内环以及其对应外环的填充数据
				for (int j = 0; j < reportFormses.size(); j++) {
					GoodsReportForms data = reportFormses.get(j);
					if(data.getDepartmentId().equals(reportForms.getDepartmentId())){
						//填入单个内环对应的单个外环名称
						iCategories.add(data.getGoodsTypeName());
						//填入单个内环对应的单个外环数据
						iDatas.add(data.getTaxAmount());
						//累加外环数值
						y += data.getTaxAmount();
					}
				}
				//填入单个内环对应的所有外环名称
				castDrilldownModel.setCategories(iCategories);
				//填入单个内环对应的所有外环数据
				castDrilldownModel.setDatas(iDatas);
				//填入饼图单个内环数据
				//castDataModel.setColor(castDrilldownModel.getColor());
				castDataModel.setY(y);
				castDataModel.setDrilldown(castDrilldownModel);
				//将单个内环数据放入内环容器
				datas.add(castDataModel);
			}else continue;
		}
		castGraphicModel.setCategories(categories);
		castGraphicModel.setDatas(datas);
		return castGraphicModel;
	}
	
	/**
	 * 查询物品月结报表数据
	 * @param goodsMonthBalanceForm
	 * @return
	 */
	public PageModel<RepertoryGoodsMonthBalance> findGoodsMonthBalanceByCondition(GoodsMonthBalanceForm goodsMonthBalanceForm) {
		if(!StringUtil.isEmpty(goodsMonthBalanceForm.getGoodsName())){
			goodsMonthBalanceForm.setGoodsName("'%"+goodsMonthBalanceForm.getGoodsName()+"%'");
		}
		if(!StringUtil.isEmpty(goodsMonthBalanceForm.getGoodsSize())){
			goodsMonthBalanceForm.setGoodsSize("'%"+goodsMonthBalanceForm.getGoodsSize()+"%'");
		}
		PageHelper.startPage(goodsMonthBalanceForm.getPage(),goodsMonthBalanceForm.getRows());
		List<RepertoryGoodsMonthBalance> list = reportFormsDao.findGoodsMonthBalanceByCondition(goodsMonthBalanceForm);
		PageModel<RepertoryGoodsMonthBalance> pageInfo =new PageModel<>((Page<RepertoryGoodsMonthBalance>)list);
		return pageInfo;
	}
	
	/**
	 * 查询物品月结报表汇总
	 * @param goodsMonthBalanceForm
	 * @return
	 */
	public RepertoryGoodsMonthBalance findSumGoodsMonthBalance(GoodsMonthBalanceForm goodsMonthBalanceForm) {
		if(!StringUtil.isEmpty(goodsMonthBalanceForm.getGoodsName())){
			goodsMonthBalanceForm.setGoodsName("'%"+goodsMonthBalanceForm.getGoodsName()+"%'");
		}
		if(!StringUtil.isEmpty(goodsMonthBalanceForm.getGoodsSize())){
			goodsMonthBalanceForm.setGoodsSize("'%"+goodsMonthBalanceForm.getGoodsSize()+"%'");
		}
		RepertoryGoodsMonthBalance goodsMonthBalance = reportFormsDao.findSumGoodsMonthBalance(goodsMonthBalanceForm);
		return goodsMonthBalance;
	}
	
	/**
	 * 查询分类月结报表数据
	 * @param typeMonthBalanceForm
	 * @return
	 */
	public PageModel<TypeMonthBalanceForm> findTypeMonthBalanceByCondition(TypeMonthBalanceForm typeMonthBalanceForm) {
		if(!StringUtil.isEmpty(typeMonthBalanceForm.getGoodsTypeName())){
			typeMonthBalanceForm.setGoodsTypeName("'%"+typeMonthBalanceForm.getGoodsTypeName()+"%'");
		}
		PageHelper.startPage(typeMonthBalanceForm.getPage(),typeMonthBalanceForm.getRows());
		List<TypeMonthBalanceForm> list = reportFormsDao.findTypeMonthBalanceByCondition(typeMonthBalanceForm);
		PageModel<TypeMonthBalanceForm> pageInfo =new PageModel<>((Page<TypeMonthBalanceForm>)list);
		return pageInfo;
	}
	
	/**
	 * 查询分类月结报表汇总
	 * @param typeMonthBalanceForm
	 * @return
	 */
	public TypeMonthBalanceForm findSumTypeMonthBalance(TypeMonthBalanceForm typeMonthBalanceForm) {
		if(!StringUtil.isEmpty(typeMonthBalanceForm.getGoodsTypeName())){
			typeMonthBalanceForm.setGoodsTypeName("'%"+typeMonthBalanceForm.getGoodsTypeName()+"%'");
		}
		TypeMonthBalanceForm balanceForm = reportFormsDao.findSumTypeMonthBalance(typeMonthBalanceForm);
		return balanceForm;
	}
	
	/**
	 * 查询分类物品领用入库走势图数据
	 * @param goodsTypeUrl
	 * @return
	 */
	public LineGraphicModel findTypeReportByCondition(String goodsTypeUrl) {

//		//汉字月份数组
//		String[] monthes = {"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
//		//获取当前时间日期类
//		Calendar now = Calendar.getInstance();
//		//获取当前时间的月份数字
//		int month = now.get(Calendar.MONTH);
		
		//多线折线图数据容器
		LineGraphicModel lineGraphicModel = new LineGraphicModel();
		//多线y轴数据容器
		List<LineDataModel> series = new ArrayList<>();
		//x轴数据容器
		List<String> categories = new ArrayList<>();
		//出库数据线数据容器 
		LineDataModel outAmountLine = new LineDataModel();
		//出库数据 出库量容器
		List<Double> outDatas = new ArrayList<>();
		//入库数据线数据容器
		LineDataModel inAmountLine = new LineDataModel();
		//入库数据 入库量容器
		List<Double> inDatas = new ArrayList<>();		
		//设置出库线 名称
		outAmountLine.setName("出库金额");
		//设置入库线名称
		inAmountLine.setName("入库金额");
		//持久层查询出入库数据
		List<TypeMonthBalanceForm> list = reportFormsDao.findTypeYearBalance(goodsTypeUrl);
		if(list != null && list.size() != 0){
			//如果查询结果不为null 则设置物品类型名
			lineGraphicModel.setGoodsTypeName(list.get(0).getGoodsTypeName());
		}
		for(int i = 0;i<list.size();i++){
			//根据查询结果条数 添加汉字月份
			//categories.add(monthes[(month+(12-list.size()))%12]);
			categories.add(list.get(i).getMonthBalanceTimeStr());
			//塞入单个出库数据
			outDatas.add(list.get(i).getCurrentOutAmount());
			//塞入单个入库数据
			inDatas.add(list.get(i).getCurrentInAmount());
			//month++;
		}
		//填入入库数据线数据
		outAmountLine.setData(outDatas);
		//填入出库数据线数据
		inAmountLine.setData(inDatas);
		//将出入库数据线数据设置进y轴数据线容器
		series.add(outAmountLine);
		series.add(inAmountLine);
		//将x轴数据线数据设置进多线折线图数据容器
		lineGraphicModel.setCategories(categories);
		//将y轴数据线数据设置进多线折线图数据容器
		lineGraphicModel.setSeries(series);
		return lineGraphicModel;
	}
	
	/**
	 * 获取价格走势数据
	 * @param repertoryWeightedAverPrice
	 */
	public FoldLineModel getPriceTrendData(GoodsPriceForm goodsPriceForm) {
		
		FoldLineModel foldLineModel = new FoldLineModel();
	
		/*SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		int nowYear = Integer.parseInt(yearFormat.format(date));
		int nowMonth = Integer.parseInt(monthFormat.format(date))-1;
		int otherYear;
		int otherMonth;
		if (nowMonth<12) {
			nowMonth++;
			otherYear = nowYear-1;
			otherMonth = nowMonth-1;
			goodsPriceForm.setNowTimeStr(nowYear+"-"+nowMonth+"-"+"01");
			goodsPriceForm.setOtherTimeStr(otherYear+"-"+otherMonth+"-"+"01");
		}else{
			nowYear++;
			otherYear = nowYear-2;
			goodsPriceForm.setNowTimeStr(nowYear+"-"+01+"-01");
			goodsPriceForm.setOtherTimeStr(otherYear+"-"+"12"+"01");
		}*/
		List<GoodsPriceForm> list=reportFormsDao.getPriceTrendData(goodsPriceForm);
		List<String> cList = new ArrayList<>();
		List<Double> dList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			cList.add(list.get(i).getMonthBalanceTimeStr());
			dList.add(list.get(i).getWeightedAverPrice());
		}
		foldLineModel.setCategories(cList);
		foldLineModel.setData(dList);
		foldLineModel.setGoodsId(goodsPriceForm.getGoodsId());
		foldLineModel.setName(goodsPriceForm.getGoodsName());
		return foldLineModel;
	}
	/**
	 * 查询月结日期
	 * @return
	 */
	public List<String> findMonthBalanceTime() {
		List<String> dates = reportFormsDao.findMonthBalanceTime();
		return dates;
	}
	
	/**
	 * 查询月结日期
	 * @return
	 */
	public String findLastMonthBalanceTime() {
		String date = reportFormsDao.findLastMonthBalanceTime();
		return date;
	}
	
	/**
	 * 开始月结的方法
	 * @param goodsMonthBalanceForm
	 */
	public void startMonthBalance(GoodsMonthBalanceForm goodsMonthBalanceForm) {
		String startTime = goodsMonthBalanceForm.getStartTime();
		String endTime = goodsMonthBalanceForm.getEndTime();
		if(DateUtil.strToDate(startTime,"yyyy-MM-dd").getTime() >= DateUtil.strToDate(endTime,"yyyy-MM-dd").getTime())
			throw new RuntimeException("月结结束日期小于开始日期！！");
		List<RepertoryGoodsMonthBalance> list = reportFormsDao.countGoodsMonthBalance(goodsMonthBalanceForm);
		if(list == null || list.size() == 0)
			throw new RuntimeException("生成月结报表数据失败");
		if(list.size() != reportFormsDao.insertMonthBalanceByBatch(list))
			throw new RuntimeException("插入月结报表异常");
		if(!reportFormsDao.insertMonthBalanceTime(endTime))
			throw new RuntimeException("插入月结日期异常");
	}
	
	/**
	 * 查询动态报表
	 * @param dynamicBalanceForm
	 */
	public PageModel<RepertoryGoodsMonthBalance> findDynamicBalance(DynamicBalanceForm dynamicBalanceForm) {
		String startTime = dynamicBalanceForm.getStartTime();
		String endTime = dynamicBalanceForm.getEndTime();
		if(!StringUtil.isEmpty(startTime) && !StringUtil.isEmpty(endTime)){
			if(DateUtil.strToDate(startTime,"yyyy-MM-dd").getTime() > DateUtil.strToDate(endTime,"yyyy-MM-dd").getTime())
				throw new RuntimeException("月结结束日期小于开始日期！！");
		}
		//物品名全模糊
		if(!StringUtil.isEmpty(dynamicBalanceForm.getGoodsName())){
			dynamicBalanceForm.setGoodsName(StringUtil.toLikeAll(dynamicBalanceForm.getGoodsName()));
		}
		//物品规格右模糊
		if(!StringUtil.isEmpty(dynamicBalanceForm.getGoodsSize())){
			dynamicBalanceForm.setGoodsSize(StringUtil.toLikeRight(dynamicBalanceForm.getGoodsSize()));
		}
		PageHelper.startPage(dynamicBalanceForm.getPage(),dynamicBalanceForm.getRows());
		List<RepertoryGoodsMonthBalance> list = reportFormsDao.countDynamicBalance(dynamicBalanceForm);
		System.out.println(list.get(0));
		PageModel<RepertoryGoodsMonthBalance> pageInfo =new PageModel<>((Page<RepertoryGoodsMonthBalance>)list);
		if(list == null || list.size() == 0)
			throw new RuntimeException("生成月结报表数据失败");
		return pageInfo;
	}
	
	/**
	 * 查询动态报表汇总
	 * @param dynamicBalanceForm
	 */
	public RepertoryGoodsMonthBalance findSumDynamicBalance(DynamicBalanceForm dynamicBalanceForm) {
		String startTime = dynamicBalanceForm.getStartTime();
		String endTime = dynamicBalanceForm.getEndTime();
		if(!StringUtil.isEmpty(startTime) && !StringUtil.isEmpty(endTime)){
			if(DateUtil.strToDate(startTime,"yyyy-MM-dd").getTime() > DateUtil.strToDate(endTime,"yyyy-MM-dd").getTime())
				throw new RuntimeException("月结结束日期小于开始日期！！");
		}
		//物品名全模糊
		if(!StringUtil.isEmpty(dynamicBalanceForm.getGoodsName())){
			dynamicBalanceForm.setGoodsName(StringUtil.toLikeAll(dynamicBalanceForm.getGoodsName()));
		}
		//物品规格右模糊
		if(!StringUtil.isEmpty(dynamicBalanceForm.getGoodsSize())){
			dynamicBalanceForm.setGoodsSize(StringUtil.toLikeRight(dynamicBalanceForm.getGoodsSize()));
		}
		RepertoryGoodsMonthBalance monthBalance = reportFormsDao.findSumDynamicBalance(dynamicBalanceForm);
		return monthBalance;
	}
	
	/**
	 * 获取部门物品领用报表
	 * goodsDeptGetForm
	 */
	public CrosstabModel goodsDeptGetForm(DepartmentUseDataForm departmentUseDataForm){
		//获取第一层级的物品类别
		List<RepertoryGoodsType> goodsTypes = reportFormsDao.getGoodsFirstType();
		//查询获取部门列表
		List<DepartmentModel> deptDatas = reportFormsDao.getDataDept(departmentUseDataForm);
		//根据出库列表查 询 用途 和 部门 分组
		List<DepartmentUseModel> deptUseModels = reportFormsDao.getDeptUse(departmentUseDataForm);
		
		//获取根据 部门分组 的物品使用量   （部门类型  数据）
		List<TypeUseDataModel> typeDataModels = reportFormsDao.goodsDeptGetForm(departmentUseDataForm);
		//获取根据  部门及用途 分组的 物品使用量  （部门用途类型   数据）
		List<TypeUseDataModel> typeGetUseDatas = reportFormsDao.goodsDeptGetUseForm(departmentUseDataForm);
		
		//遍历部门模型
		for(DepartmentModel deptData : deptDatas){
			//在部门模型中添加一个 空的  部门用途 模型
			deptData.setDeptUseModels(new LinkedList<DepartmentUseModel>());
			//遍历部门用途模型，将适当的模型添加到该部门模型中
			for(DepartmentUseModel deptUseModel : deptUseModels){
				if(deptUseModel.getDepartmentId().equals(deptData.getDepartmentId())){
					//添加模型
					deptData.getDeptUseModels().add(deptUseModel);
					//在 部门用途模型  中添加   数据模型
					deptUseModel.setTypeUseDataModels(new LinkedList<TypeUseDataModel>());
					//遍历所有的数据模型，如果发现符合的模型则放入部门用途模型中
					for(RepertoryGoodsType goodsType:goodsTypes){
						boolean flag = true;
						for(TypeUseDataModel typeGetUseData : typeGetUseDatas){
							//查看是否该数据模型符合要求
							if(typeGetUseData.getUseTypeKey().equals(deptUseModel.getUseTypeKey()) &&
									typeGetUseData.getDepartmentId().equals(deptUseModel.getDepartmentId()) &&
									typeGetUseData.getGoodsTypeId().equals(goodsType.getGoodsTypeId())){
								//添加数据模型
								deptUseModel.getTypeUseDataModels().add(typeGetUseData);
								flag = false;
								break ;
							}
						}
						if(flag)
							deptUseModel.getTypeUseDataModels().add(new TypeUseDataModel());
					}
				}
			}
		}		
		//将物品的部门使用量， 分别装载进部门中
		for(DepartmentModel deptData : deptDatas){
			//填充进一个空的数量 List 容器
			deptData.setTypeUseDatas(new LinkedList<TypeUseDataModel>());
			//遍历所需的   部门类型   数据
			for(RepertoryGoodsType goodsType : goodsTypes){
				boolean flag1 = true;
				for(TypeUseDataModel typeDataModel:typeDataModels){
					if(typeDataModel.getDepartmentId().equals(deptData.getDepartmentId()) &&
							typeDataModel.getGoodsTypeId().equals(goodsType.getGoodsTypeId())){
						deptData.getTypeUseDatas().add(typeDataModel);
						flag1 = false;
						break ;
					}
				}
				if(flag1)
					deptData.getTypeUseDatas().add(new TypeUseDataModel());
			}
		}
		
		List<TypeUseDataModel> typeAmounts = reportFormsDao.goodsGetForm(departmentUseDataForm);
		
		List<TypeUseDataModel> typeAmounts2 = new LinkedList<TypeUseDataModel>();
		
		for(RepertoryGoodsType goodsType : goodsTypes){
			boolean flag = true; 
			for(TypeUseDataModel typeAmount : typeAmounts){
				if(goodsType.getGoodsTypeId().equals(typeAmount.getGoodsTypeId())){
					typeAmounts2.add(typeAmount);
					flag = false;
					break ;
				}
			}
			if(flag)
				typeAmounts2.add(new TypeUseDataModel());
		}
		
		CrosstabModel model = new CrosstabModel();
		model.setDeptDatas(deptDatas);
		model.setGoodsTypes(goodsTypes);
		model.setTypeAmounts(typeAmounts2);
		return model;
	}
	
	/**
	 * 查询部门领用各物品类的总金额
	 */
	public CrosstabModel selectAmountByDepartmentId(DepartmentUseDataForm departmentUseDataForm) {
		//获取第一层级的物品类别
		List<RepertoryGoodsType> goodsTypes = reportFormsDao.getGoodsFirstType();
		//查询获取部门列表
		List<DepartmentModel> deptDatas = reportFormsDao.getDataDept(departmentUseDataForm);
		//根据出库列表查 询 用途 和 部门 分组
		List<DepartmentUseModel> deptUseModels = reportFormsDao.getDeptUse(departmentUseDataForm);
		
		//获取根据 部门分组 的物品使用量   （部门类型  数据）
		List<TypeUseDataModel> typeDataModels = reportFormsDao.goodsDeptGetForm(departmentUseDataForm);
		
		//将物品的部门使用量， 分别装载进部门中
		for(DepartmentModel deptData : deptDatas){
			//填充进一个空的数量 List 容器
			deptData.setTypeUseDatas(new LinkedList<TypeUseDataModel>());
			//遍历所需的   部门类型   数据
			for(RepertoryGoodsType goodsType : goodsTypes){
				boolean flag1 = true;
				for(TypeUseDataModel typeDataModel:typeDataModels){
					if(typeDataModel.getDepartmentId().equals(deptData.getDepartmentId()) &&
							typeDataModel.getGoodsTypeId().equals(goodsType.getGoodsTypeId())){
						deptData.getTypeUseDatas().add(typeDataModel);
						flag1 = false;
						break ;
					}
				}
				if(flag1)
					deptData.getTypeUseDatas().add(new TypeUseDataModel());
			}
		}
		List<TypeUseDataModel> typeAmounts = reportFormsDao.goodsGetForm(departmentUseDataForm);
		
		List<TypeUseDataModel> typeAmounts2 = new LinkedList<TypeUseDataModel>();
		for(RepertoryGoodsType goodsType : goodsTypes){
			boolean flag = true; 
			for(TypeUseDataModel typeAmount : typeAmounts){
				if(goodsType.getGoodsTypeId().equals(typeAmount.getGoodsTypeId())){
					typeAmounts2.add(typeAmount);
					flag = false;
					break ;
				}
			}
			if(flag)
				typeAmounts2.add(new TypeUseDataModel());
		}
		
		CrosstabModel model = new CrosstabModel();
		model.setDeptDatas(deptDatas);
		model.setGoodsTypes(goodsTypes);
		model.setTypeAmounts(typeAmounts2);
		return model;
	}
}
