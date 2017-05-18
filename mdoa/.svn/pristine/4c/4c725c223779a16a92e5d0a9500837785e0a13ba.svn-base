package com.mdoa.repertory.dao;

import java.util.List;

import com.mdoa.repertory.bo.GoodsForm;
import com.mdoa.repertory.model.RepertoryDepartment;
import com.mdoa.repertory.model.RepertoryInRecord;
import com.mdoa.repertory.model.RepertoryOutRecord;
/**
 * 出库入库记录dao层
 * @author Administrator
 *
 */
public interface RepertoryInOutRecordDao {
	
	String getuuid();
	/**
	 * 插入入库记录
	 * @param inRecord
	 * @return
	 */
	boolean insertInRecord(GoodsForm goodsForm);
	/**
	 * 插入出库记录
	 * @param outRecord
	 * @return
	 */
	boolean insertOutRecord(GoodsForm goodsForm);
	/**
	 * 查询所有物品入库流水列表 以及各种查询
	 * @param goodsForm
	 * @return
	 */
	List<GoodsForm> selectGoodsInRecord(GoodsForm goodsForm);
	/**
	 * 查询所有物品的出库明细列表 以及各种查询
	 * @param goodsForm
	 * @return
	 */
	List<GoodsForm> selectGoodsOutRecord(GoodsForm goodsForm);
	/**
	 * 根据入库记录id查询入库记录
	 * @param inRecordId
	 * @return
	 */
	RepertoryInRecord selectGoodsInRecordById(String inRecordId);
	/**
	 * 根据出库记录id查询出库记录
	 * @param outRecordId
	 * @return
	 */
	RepertoryOutRecord selectGoodsOutRecordById(String outRecordId);
	/**
	 *  查询全部的出库入库记录  以及各种条件查询
	 * @param goodsForm
	 * @return
	 */
	List<GoodsForm> selectAllInOutRecord(GoodsForm goodsForm);
	/**
	 * 删除一条入库记录
	 * @param inRecordId
	 * @return
	 */
	boolean deleteGoodsInRecord(String inRecordId);
	/**
	 * 删除一条出库记录
	 * @param outRecordId
	 * @return
	 */
	boolean deleteGoodsOutRecord(String outRecordId);
	/**
	 * 修改一条入库记录
	 * @param inRecordId
	 * @return
	 */
	boolean updateGoodsInRecord(GoodsForm goodsForm);
	/**
	 * 修改一条出库记录
	 * @param outRecordId
	 * @return
	 */
	boolean updateGoodsOutRecord(GoodsForm goodsForm);
	/**
	 * 插入领用部门的信息
	 * @param goodsForm
	 * @return
	 */
	boolean insertGetDepartment(RepertoryDepartment department);
	/**
	 * 删除一个领用部门
	 * @param departmentId
	 * @return
	 */
	boolean deleteGetDepartment(String departmentId);
	/**
	 * 修改领用部门的信息
	 * @param department
	 * @return
	 */
	boolean updateGetDepartment(RepertoryDepartment department);
	/**
	 * 查询所有部门信息 
	 * @return
	 */
	List<RepertoryDepartment> selectGetDepartment();
	
	/**
	 * 查询出入库汇总
	 * @param goodsForm
	 * @return
	 */
	GoodsForm findSumInOutRecord(GoodsForm goodsForm);
	
	/**
	 * 查询入库汇总
	 * @param goodsForm
	 * @return
	 */
	GoodsForm findSumInRecord(GoodsForm goodsForm);
	
	/**
	 * 查询出库汇总
	 * @param goodsForm
	 * @return
	 */
	GoodsForm findSumOutRecord(GoodsForm goodsForm);
	
}
