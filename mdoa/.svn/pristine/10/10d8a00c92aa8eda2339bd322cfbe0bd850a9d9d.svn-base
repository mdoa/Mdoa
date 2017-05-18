package com.mdoa.personnel.dao;

import java.util.List;

import com.mdoa.personnel.bo.InsuranceForm;
import com.mdoa.personnel.bo.InsuranceTypeForm;
import com.mdoa.personnel.model.InsuranceType;


public interface InsuranceDao {
	
	/**
	 * 通过社保类型id查询社保信息
	 * @param typeId
	 * @return
	 */
	List<InsuranceForm> findInsuranceByTypeId(String typeId);

	/**
	 * 通过用户id查询社保信息
	 * @param userId
	 * @return
	 */
	List<InsuranceForm> findInsuranceByUserId(String userId);
	
	/**
	 * 插入社保信息
	 * @param insuranceForm
	 * @return
	 */
	boolean insertInsurance(InsuranceForm insuranceForm);
	
	/**
	 * 通过社保大类型查询社保类型信息
	 * @param superType
	 * @return
	 */
	List<InsuranceTypeForm> findTypeBySuperType(String superType);
	
	/**
	 * 插入社保类型信息
	 * @param insuranceTypeForm
	 * @return
	 */
	boolean insertInsuranceType(InsuranceTypeForm insuranceTypeForm);
	
	/**
	 * 条件查询社保信息
	 * @param insuranceForm
	 * @return
	 */
	List<InsuranceForm> findInsuranceByCondition(InsuranceForm insuranceForm);
	
	/**
	 * 条件查询社保类型
	 * @param insuranceTypeForm
	 * @return
	 */
	List<InsuranceType> findTypeByCondition(InsuranceTypeForm insuranceTypeForm);

}
