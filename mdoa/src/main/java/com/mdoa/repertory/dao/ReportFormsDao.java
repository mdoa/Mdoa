package com.mdoa.repertory.dao;

import java.util.List;
import java.util.Map;

import com.mdoa.repertory.bo.AmountQueryForm;
import com.mdoa.repertory.bo.DynamicBalanceForm;
import com.mdoa.repertory.bo.GoodsMonthBalanceForm;
import com.mdoa.repertory.bo.GoodsPriceForm;
import com.mdoa.repertory.bo.TypeMonthBalanceForm;
import com.mdoa.repertory.form.DepartmentUseDataForm;
import com.mdoa.repertory.model.GoodsReportForms;
import com.mdoa.repertory.model.RepertoryGoodsMonthBalance;
import com.mdoa.repertory.model.RepertoryGoodsType;
import com.mdoa.repertory.model.crosstab.DepartmentModel;
import com.mdoa.repertory.model.crosstab.DepartmentUseModel;
import com.mdoa.repertory.model.crosstab.TypeUseDataModel;

/**
 * 报表相关Dao
 * 
 * @author Administrator
 *
 */
public interface ReportFormsDao {

	/**
	 * 获取价格走势数据
	 * 
	 * @param dateList
	 */
	List<GoodsPriceForm> getPriceTrendData(GoodsPriceForm goodsPriceForm);

	/**
	 * 查询物品领用报表数据
	 * 
	 * @param params
	 * @return
	 */
	List<GoodsReportForms> findGoodsReportByCondition(Map<String, String> params);

	/**
	 * 查询物品月结报表数据
	 * 
	 * @param goodsMonthBalanceForm
	 * @return
	 */
	List<RepertoryGoodsMonthBalance> findGoodsMonthBalanceByCondition(GoodsMonthBalanceForm goodsMonthBalanceForm);

	/**
	 * 查询分类月结报表数据
	 * 
	 * @param typeMonthBalanceForm
	 * @return
	 */
	List<TypeMonthBalanceForm> findTypeMonthBalanceByCondition(TypeMonthBalanceForm typeMonthBalanceForm);

	/**
	 * 查询分类物品领用入库走势图数据
	 * 
	 * @param goodsTypeUrl
	 * @return
	 */
	List<TypeMonthBalanceForm> findTypeYearBalance(String goodsTypeUrl);
	
	/**
	 * 查询月结日期
	 * @return
	 */
	List<String> findMonthBalanceTime();

	/**
	 * 查询部门领用各物品类的总金额
	 * @param amountQueryForm 
	 * @return 
	 */
	List<AmountQueryForm> selectAmountByDepartmentId(String departmentId);
	
	/**
	 * 查询所有部门的部门ID
	 * @return
	 */
	List<AmountQueryForm> queryAllReDepartment();
	
	/**
	 * 结算月结数据
	 * @param goodsMonthBalanceForm
	 * @return
	 */
	List<RepertoryGoodsMonthBalance> countGoodsMonthBalance(GoodsMonthBalanceForm goodsMonthBalanceForm);
	
	/**
	 * 批量插入月结数据
	 * @param list
	 * @return
	 */
	int insertMonthBalanceByBatch(List<RepertoryGoodsMonthBalance> monthBalances);
	
	/**
	 * 查询动态报表数据
	 * @param dynamicBalanceForm
	 * @return
	 */
	List<RepertoryGoodsMonthBalance> countDynamicBalance(DynamicBalanceForm dynamicBalanceForm);

	/**
	 * 查询根据部门 和用途 分组的 领用金额信息
	 * @param departments
	 * @return
	 */
	List<TypeUseDataModel> goodsDeptGetUseForm(DepartmentUseDataForm departmentUseDataForm);
	
	/**
	 * 查询根据部门分组的 领用金额信息
	 * @param departments
	 * @return
	 */
	List<TypeUseDataModel> goodsDeptGetForm(DepartmentUseDataForm departmentUseDataForm);
	
	/**
	 * 根据部门ids获取部门信息列表
	 * @param departments
	 * @return
	 */
	List<DepartmentModel> getDataDept(DepartmentUseDataForm departmentUseDataForm);
	
	/**
	 * 获取所有的部门的汇总量
	 */
	List<TypeUseDataModel> goodsGetForm(DepartmentUseDataForm departmentUseDataForm);
	
	/**
	 * 根据部门ids获取部门信息列表
	 * @param departments
	 * @return
	 */
	List<DepartmentUseModel> getDeptUse(DepartmentUseDataForm departmentUseDataForm);
	
	/**
	 * 获取物品的第一层级大类别
	 */
	List<RepertoryGoodsType> getGoodsFirstType();
	
	/**
	 * 获取最后一次月结日期
	 */
	String findLastMonthBalanceTime();
	
	/**
	 * 插入月结日期
	 * @param endTime
	 * @return
	 */
	boolean insertMonthBalanceTime(String monthBalanceTime);
	
	/**
	 * 查询物品月结报表汇总
	 * @param goodsMonthBalanceForm
	 * @return
	 */
	RepertoryGoodsMonthBalance findSumGoodsMonthBalance(GoodsMonthBalanceForm goodsMonthBalanceForm);
	
	/**
	 * 查询物品月结报表汇总
	 * @param typeMonthBalanceForm
	 * @return
	 */
	TypeMonthBalanceForm findSumTypeMonthBalance(TypeMonthBalanceForm typeMonthBalanceForm);
	
	/**
	 * 查询动态报表汇总
	 * @param dynamicBalanceForm
	 * @return
	 */
	RepertoryGoodsMonthBalance findSumDynamicBalance(DynamicBalanceForm dynamicBalanceForm);
}
