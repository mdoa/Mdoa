package com.mdoa.repertory.service;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.base.service.BaseService;
import com.mdoa.repertory.bo.GoodsForm;
import com.mdoa.repertory.dao.GoodsDao;
import com.mdoa.repertory.dao.RepertoryDao;
import com.mdoa.repertory.dao.RepertoryInOutRecordDao;
import com.mdoa.repertory.model.RepertoryDepartment;
import com.mdoa.repertory.model.RepertoryGoods;
import com.mdoa.repertory.model.RepertoryGoodsPosition;
import com.mdoa.repertory.model.RepertoryInRecord;
import com.mdoa.repertory.model.RepertoryOutRecord;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

/**
 * 商品入库出库service层
 * @author Administrator
 *
 */
@Service
@Transactional
public class RepertoryInOutRecordService extends BaseService{
	
	@Autowired
	private RepertoryInOutRecordDao inOutRecordDao;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private RepertoryDao repertoryDao;
	
	
	/**
	 * 新仓位入库操作
	 * @param goodsForm
	 * @return
	 */
	public Double newGoodsPositionPut(GoodsForm goodsForm, HttpServletRequest request){
		
		UserInfo user = getUser(request);
		goodsForm.setCreateUserId(user.getCreateUserId());
		goodsForm.setCreateUserName(user.getCreateUserName());
		goodsForm.setOperateUserId(user.getCreateUserId());
		goodsForm.setOperateUserName(user.getCreateUserName());
		String uuid = repertoryDao.getuuid();
		goodsForm.setGoodsPositionId(uuid);
		if(!inOutRecordDao.insertInRecord(goodsForm)){
			throw new RuntimeException("插入入库记录失败");
		}
		goodsForm.setGoodsNumber(goodsForm.getInNumber());
		if(!repertoryDao.insertRepertoryPosition(goodsForm)){
			throw new RuntimeException("新建仓位成功");
		}
		//得到前台传来的税后平均价
		Double averPrice = goodsForm.getTaxAverPrice();
		//得到前台传来的入库数量
		Integer inNumber = goodsForm.getInNumber();
		//利用goodsId 去数据库查询 此物品在数据库中 有无 数量
		RepertoryGoods goods = goodsDao.selectGoodsById(goodsForm.getGoodsId());
		if(goods.getTotalNumber() == 0){
			//把最新单价存到数据库中
			goodsForm.setLatestUnitPrice(averPrice);
			//把移动平均价存到数据库
			goodsForm.setMovingAverPrice(averPrice);
			goodsForm.setUpdateUserId(user.getUpdateUserId());
			goodsForm.setUpdateUserName(user.getUpdateUserName());
			if(!goodsDao.updateGoodsByIn(goodsForm)){
				throw new RuntimeException("更新物品数量失败"); 
			}
			return averPrice;		
		}else{
			Integer totalNumber = goods.getTotalNumber();
			//得到物品的最新单价
			Double latestUnitPrice = goods.getLatestUnitPrice();
			Double a = inNumber * averPrice + totalNumber * latestUnitPrice;
			Integer c = inNumber + totalNumber;
			//计算出移动平均价
			Double movingAverPrice = a / c;
			//把最新单价存到数据库中
			goodsForm.setLatestUnitPrice(averPrice);
			//把移动平均价存到数据库
			goodsForm.setMovingAverPrice(movingAverPrice);
			goodsForm.setUpdateUserId(user.getUpdateUserId());
			goodsForm.setUpdateUserName(user.getUpdateUserName());
			if(!goodsDao.updateGoodsByIn(goodsForm)){
				throw new RuntimeException("更新物品数量失败");
			}
			return movingAverPrice;		
		}
	}
	
	/**
	 * 进行入库操作
	 * @param inRecord
	 * @return
	 */
	public Double putInStorageRecord(GoodsForm goodsForm, HttpServletRequest request){
		UserInfo user = getUser(request);
		goodsForm.setCreateUserId(user.getCreateUserId());
		goodsForm.setCreateUserName(user.getCreateUserName());
		goodsForm.setOperateUserId(user.getCreateUserId());
		goodsForm.setOperateUserName(user.getCreateUserName());
		if(!inOutRecordDao.insertInRecord(goodsForm)){
			throw new RuntimeException("插入入库记录失败");
		}
		goodsForm.setUpdateUserId(user.getUpdateUserId());
		goodsForm.setUpdateUserName(user.getUpdateUserName());
		if(!repertoryDao.updatePositionGoodsByIn(goodsForm)){
			throw new RuntimeException("更新仓位物品数量失败");
		}
		//得到前台传来的入库数量
		Integer inNumber = goodsForm.getInNumber();
		//得到前台传来的税后平均价
		Double averPrice = goodsForm.getTaxAverPrice();
		//得到仓库物品
		RepertoryGoods goods = goodsDao.selectGoodsById(goodsForm.getGoodsId());
		//得到仓库物品的库存数量
		Integer totalNumber = goods.getTotalNumber();
		//得到物品的最新单价
		Double latestUnitPrice = goods.getLatestUnitPrice();
		Double a = inNumber * averPrice + totalNumber * latestUnitPrice;
		Integer c = inNumber + totalNumber;
		//计算出移动平均价
		Double movingAverPrice = a / c;
		//把最新单价存到数据库中
		goodsForm.setLatestUnitPrice(averPrice);
		//把移动平均价存到数据库
		goodsForm.setMovingAverPrice(movingAverPrice);
		if(!goodsDao.updateGoodsByIn(goodsForm)){
			throw new RuntimeException("更新物品数量失败");
		}
		return movingAverPrice;	
			
	}	
	/**
	 * 进行出库操作
	 * @param outRecord
	 */
	public void putOutStorageRecord(GoodsForm goodsForm, HttpServletRequest request){
		UserInfo user = getUser(request);
		goodsForm.setUpdateUserId(user.getUpdateUserId());
		goodsForm.setUpdateUserName(user.getUpdateUserName());
		if(!repertoryDao.updatePositionGoodsByOut(goodsForm)){
			throw new RuntimeException("更新仓位物品数量失败");
		}
		if(!goodsDao.updateGoodsByOut(goodsForm)){
			throw new RuntimeException("更新商品数量失败");
		}
		goodsForm.setCreateUserId(user.getCreateUserId());
		goodsForm.setCreateUserName(user.getCreateUserName());
		goodsForm.setOperateUserId(user.getCreateUserId());
		goodsForm.setOperateUserName(user.getCreateUserName());
		if(!inOutRecordDao.insertOutRecord(goodsForm)){
			throw new RuntimeException("插入出库记录失败");
		}
	}
	
	/**
	 * 移库的出库操作
	 * @param goodsForm
	 */
	public void moveGoodsRepertoryByOut(GoodsForm goodsForm, HttpServletRequest request){
		
		UserInfo user = getUser(request);
		goodsForm.setUpdateUserId(user.getUpdateUserId());
		goodsForm.setUpdateUserName(user.getUpdateUserName());
		//得到前台传来的移库数量
		goodsForm.setOutNumber(goodsForm.getMoveNumber());
		if(!repertoryDao.updatePositionGoodsByOut(goodsForm)){
			throw new RuntimeException("更新仓位物品数量失败");
		}
	}
	/**
	 * 移库的入库操作
	 * @param goodsForm
	 */
	public void moveGoodsRepertoryByIn(GoodsForm goodsForm, HttpServletRequest request){
		UserInfo user = getUser(request);
		goodsForm.setCreateUserId(user.getCreateUserId());
		goodsForm.setCreateUserName(user.getCreateUserName());
		//得到前台传来的新仓库id
		goodsForm.setRepertoryId(goodsForm.getNewRepertoryId());
		goodsForm.setGoodsNumber(goodsForm.getMoveNumber());
		String uuid = repertoryDao.getuuid();
		goodsForm.setGoodsPositionId(uuid);
		if(!repertoryDao.insertRepertoryPosition(goodsForm)){
			throw new RuntimeException("新建仓位失败");
		}
	}
	
	/**
	 * 查询所有物品的入库流水列表 以及各种查询
	 * @param goodsForm
	 * @return
	 */
	public PageModel<GoodsForm> selectGoodsInRecord(GoodsForm goodsForm){
		if(!StringUtil.isEmpty(goodsForm.getGoodsName())){
			goodsForm.setGoodsName("%"+goodsForm.getGoodsName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsSize())){
			goodsForm.setGoodsSize("%"+goodsForm.getGoodsSize()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getOperateUserName())){
			goodsForm.setOperateUserName("%"+goodsForm.getOperateUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getPutUserName())){
			goodsForm.setPutUserName("%"+goodsForm.getPutUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsTypeUrl())){
			goodsForm.setGoodsTypeUrl(goodsForm.getGoodsTypeUrl()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsPositionName())){
			goodsForm.setGoodsPositionName("%"+goodsForm.getGoodsPositionName()+"%");
		}
		PageHelper.startPage(goodsForm.getPage(), goodsForm.getRows());
		List<GoodsForm> list = inOutRecordDao.selectGoodsInRecord(goodsForm);
		PageModel<GoodsForm> pageModel = new PageModel((Page)list);
		GoodsForm form = inOutRecordDao.findSumInRecord(goodsForm);
		pageModel.setSum(form);
		return pageModel;
	}
	/**
	 * 查询所有物品的出库明细列表 以及各种查询
	 * @param goodsForm
	 * @return
	 */
	public PageModel<GoodsForm> selectGoodsOutRecord(GoodsForm goodsForm){
		if(!StringUtil.isEmpty(goodsForm.getUpdateUserName())){
			goodsForm.setUpdateUserName("%"+goodsForm.getUpdateUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsName())){
			goodsForm.setGoodsName("%"+goodsForm.getGoodsName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsSize())){
			goodsForm.setGoodsSize("%"+goodsForm.getGoodsSize()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getOperateUserName())){
			goodsForm.setOperateUserName("%"+goodsForm.getOperateUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsTypeUrl())){
			goodsForm.setGoodsTypeUrl(goodsForm.getGoodsTypeUrl()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsPositionName())){
			goodsForm.setGoodsPositionName("%"+goodsForm.getGoodsPositionName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGetUserName())){
			goodsForm.setGetUserName("%"+goodsForm.getGetUserName()+"%");
		}
		PageHelper.startPage(goodsForm.getPage(), goodsForm.getRows());
		List<GoodsForm> list = inOutRecordDao.selectGoodsOutRecord(goodsForm);
		PageModel<GoodsForm> pageModel = new PageModel((Page)list);
		GoodsForm form = inOutRecordDao.findSumOutRecord(goodsForm);
		pageModel.setSum(form);
		return pageModel;
	}
	
	/**
	 * 查询出入库汇总
	 * @param goodsForm
	 * @return
	 */
	public GoodsForm findSumOutRecord(GoodsForm goodsForm) {
		if(!StringUtil.isEmpty(goodsForm.getUpdateUserName())){
			goodsForm.setUpdateUserName("%"+goodsForm.getUpdateUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsName())){
			goodsForm.setGoodsName("%"+goodsForm.getGoodsName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsSize())){
			goodsForm.setGoodsSize("%"+goodsForm.getGoodsSize()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getOperateUserName())){
			goodsForm.setOperateUserName("%"+goodsForm.getOperateUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsTypeUrl())){
			goodsForm.setGoodsTypeUrl(goodsForm.getGoodsTypeUrl()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsPositionName())){
			goodsForm.setGoodsPositionName("%"+goodsForm.getGoodsPositionName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGetUserName())){
			goodsForm.setGetUserName("%"+goodsForm.getGetUserName()+"%");
		}
		GoodsForm form = inOutRecordDao.findSumOutRecord(goodsForm);
		return form;
	}
	
	/**
	 * 查询入库汇总
	 * @param goodsForm
	 * @return
	 */
	public GoodsForm findSumInRecord(GoodsForm goodsForm) {
		if(!StringUtil.isEmpty(goodsForm.getGoodsName())){
			goodsForm.setGoodsName("%"+goodsForm.getGoodsName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsSize())){
			goodsForm.setGoodsSize("%"+goodsForm.getGoodsSize()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getOperateUserName())){
			goodsForm.setOperateUserName("%"+goodsForm.getOperateUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getPutUserName())){
			goodsForm.setPutUserName("%"+goodsForm.getPutUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsTypeUrl())){
			goodsForm.setGoodsTypeUrl(goodsForm.getGoodsTypeUrl()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsPositionName())){
			goodsForm.setGoodsPositionName("%"+goodsForm.getGoodsPositionName()+"%");
		}
		GoodsForm form = inOutRecordDao.findSumInRecord(goodsForm);
		return form;
	}
	
	/**
	 * 修改入库记录
	 * @param goodsForm
	 * @return
	 */
	public Double editGoodsInRecord(GoodsForm goodsForm, HttpServletRequest request){
		UserInfo user = getUser(request);
		goodsForm.setUpdateUserId(user.getUpdateUserId());
		goodsForm.setUpdateUserName(user.getUpdateUserName());
		goodsForm.setOperateUserId(user.getCreateUserId());
		goodsForm.setOperateUserName(user.getCreateUserName());
		//得到前台传来修改后的入库数量
		Integer inNumber = goodsForm.getInNumber();
		//得到前台传来修改后物品的税后金额
		Double taxAmount = goodsForm.getTaxAmount();
		//根据前台传来的入库记录的id去数据库查询到入库记录的信息
		RepertoryInRecord inRecord = inOutRecordDao.selectGoodsInRecordById(goodsForm.getInRecordId());
		//得到此物品修改前数据库的税后金额
		Double taxAmount2 = inRecord.getTaxAmount();
		//得到物品入库记录的入库数量
		Integer inNumber2 = inRecord.getInNumber();
		//根据物品id得到此物品的信息
		RepertoryGoods goods = goodsDao.selectGoodsById(goodsForm.getGoodsId());
		//得到物品的最新库存
		Integer totalNumber = goods.getTotalNumber();
		//得到物品的移动平均价
		Double movingAverPrice = goods.getMovingAverPrice();
		//得到修改后的最新库存
		Integer newNumber = totalNumber - inNumber2 + inNumber;
		//得到修改后的库存金额
		Double newPrice = totalNumber * movingAverPrice - taxAmount2 + taxAmount;
		Double newMovingAverPrice = newPrice / newNumber;
		System.out.println(newMovingAverPrice);
		//根据前台传来的仓位id获取到仓位的物品数量
		RepertoryGoodsPosition goodsPosition = repertoryDao.selectGoodsNumberByPosition(goodsForm.getGoodsPositionId());
		Integer goodsNumber = goodsPosition.getGoodsNumber();
		Integer newGoodsNumber = goodsNumber - inNumber2 + inNumber;
		goodsForm.setGoodsNumber(newGoodsNumber);
		if(!repertoryDao.updateRepertoryPosition(goodsForm)){
			throw new RuntimeException("更新仓位物品数量失败");
		}
		goodsForm.setMovingAverPrice(newMovingAverPrice);
		goodsForm.setTotalNumber(newNumber);
		if(!goodsDao.updateGoodsByUpdate(goodsForm)){
			throw new RuntimeException("更新物品数量失败");
		}
		if(!inOutRecordDao.updateGoodsInRecord(goodsForm)){
			throw new RuntimeException("更新入库记录失败");
		}
		return newMovingAverPrice;
	}
	/**
	 * 修改出库记录
	 * @param goodsForm
	 */
	public void editGoodsOutRecord(GoodsForm goodsForm, HttpServletRequest request){
		UserInfo user = getUser(request);
		goodsForm.setUpdateUserId(user.getUpdateUserId());
		goodsForm.setUpdateUserName(user.getUpdateUserName());
		goodsForm.setOperateUserId(user.getCreateUserId());
		goodsForm.setOperateUserName(user.getCreateUserName());
		//得到前台传来的修改后的出库数量
		Integer outNumber = goodsForm.getOutNumber();
		//根据前台传来的出库仓位id查询出该物品在仓位的数量
		RepertoryGoodsPosition position = repertoryDao.selectGoodsNumberByPosition(goodsForm.getGoodsPositionId());
		Integer goodsNumber = position.getGoodsNumber();
		//根据前台传来的出库记录id查询到此出库记录的出库数量
		RepertoryOutRecord outRecord = inOutRecordDao.selectGoodsOutRecordById(goodsForm.getOutRecordId());
		Integer outNumber2 = outRecord.getOutNumber();
		//根据前台传来的物品id得到物品的总数量
		RepertoryGoods goods = goodsDao.selectGoodsById(goodsForm.getGoodsId());
		Integer totalNumber = goods.getTotalNumber();
		//计算出此物品在仓位中的新的数量
		Integer newtotalNumber = totalNumber + outNumber2 - outNumber;
		//计算出此物品的新的数量
		Integer newGoodsNumber = goodsNumber + outNumber2 - outNumber;
		goodsForm.setTotalNumber(newtotalNumber);
		goodsForm.setGoodsPositionId(goodsForm.getGoodsPositionId());
		goodsForm.setGoodsNumber(newGoodsNumber);
		if(!repertoryDao.updateRepertoryPosition(goodsForm)){
			throw new RuntimeException("更新仓位物品数量失败");
		}
		if(!goodsDao.updateGoodsByUpdate(goodsForm)){
			throw new RuntimeException("更新物品数量失败");
		}
		
		if(!inOutRecordDao.updateGoodsOutRecord(goodsForm)){
			throw new RuntimeException("更新入库记录失败");
		}
		
	}
	/**
	 * 查询全部的出库入库记录  以及各种条件查询
	 * @param goodsForm
	 * @return
	 */
	public PageModel<GoodsForm> selectAllInOutRecord(GoodsForm goodsForm){
		if(!StringUtil.isEmpty(goodsForm.getGoodsName())){
			goodsForm.setGoodsName("%"+goodsForm.getGoodsName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsSize())){
			goodsForm.setGoodsSize("%"+goodsForm.getGoodsSize()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsTypeUrl())){
			goodsForm.setGoodsTypeUrl(goodsForm.getGoodsTypeUrl()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getPutUserName())){
			goodsForm.setPutUserName("%"+goodsForm.getPutUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getOperateUserName())){
			goodsForm.setOperateUserName("%"+goodsForm.getOperateUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsPositionName())){
			goodsForm.setGoodsPositionName("%"+goodsForm.getGoodsPositionName()+"%");
		}
		PageHelper.startPage(goodsForm.getPage(), goodsForm.getRows());
		List<GoodsForm> list = inOutRecordDao.selectAllInOutRecord(goodsForm);
		PageModel<GoodsForm> pageModel = new PageModel((Page)list);
		return pageModel;
	}
	
	public GoodsForm findSumInOutRecord(GoodsForm goodsForm) {
		if(!StringUtil.isEmpty(goodsForm.getGoodsName())){
			goodsForm.setGoodsName("%"+goodsForm.getGoodsName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsSize())){
			goodsForm.setGoodsSize("%"+goodsForm.getGoodsSize()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsTypeUrl())){
			goodsForm.setGoodsTypeUrl(goodsForm.getGoodsTypeUrl()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getPutUserName())){
			goodsForm.setPutUserName("%"+goodsForm.getPutUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getOperateUserName())){
			goodsForm.setOperateUserName("%"+goodsForm.getOperateUserName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsPositionName())){
			goodsForm.setGoodsPositionName("%"+goodsForm.getGoodsPositionName()+"%");
		}
		GoodsForm form = inOutRecordDao.findSumInOutRecord(goodsForm);
		return form;
	}
	
	/**
	 * 删除一条入库记录
	 * @param inRecordId
	 */
	public Double deleteGoodsInRecord(String inRecordId){
		RepertoryInRecord inRecord = inOutRecordDao.selectGoodsInRecordById(inRecordId);	
		Integer inNumber = inRecord.getInNumber();
		//得到此物品修改前数据库的税后金额
		Double taxAmount = inRecord.getTaxAmount();
		RepertoryGoodsPosition goodsPosition = repertoryDao.selectGoodsNumberByPosition(inRecord.getGoodsPositionId());
		Integer goodsNumber = goodsPosition.getGoodsNumber();
		RepertoryGoods goods = goodsDao.selectGoodsById(inRecord.getGoodsId());
		Integer totalNumber = goods.getTotalNumber();
		//得到物品的移动平均价
		Double movingAverPrice = goods.getMovingAverPrice();
		//计算出删除入库记录后 仓位物品的数量
		Integer newGoodsNumber = goodsNumber - inNumber;	
		//计算出删除入库记录后 物品总量的数量
		Integer newTotalNumber = totalNumber - inNumber;
		Double newPrice = totalNumber * movingAverPrice - taxAmount;
		Double newMovingAverPrice = newPrice / newTotalNumber;
		GoodsForm goodsForm = new GoodsForm();
		goodsForm.setGoodsPositionId(inRecord.getGoodsPositionId());
		goodsForm.setGoodsNumber(newGoodsNumber);
		if(!repertoryDao.updateRepertoryPosition(goodsForm)){
			throw new RuntimeException("更新仓位物品数量失败");
		}
		goodsForm.setMovingAverPrice(newMovingAverPrice);
		goodsForm.setTotalNumber(newTotalNumber);
		goodsForm.setGoodsId(inRecord.getGoodsId());
		goodsForm.setLatestUnitPrice(newMovingAverPrice);
		if(!goodsDao.updateGoods(goodsForm)){
			throw new RuntimeException("更新物品数量失败");
		}
		
		if(!inOutRecordDao.deleteGoodsInRecord(inRecordId)){
			throw new RuntimeException("删除入库记录失败");
		}
		return newMovingAverPrice;
		
	}
	/**
	 * 删除一条出库记录
	 * @param outRecordId
	 */
	public void deleteGoodsOutRecord(String outRecordId){
		RepertoryOutRecord outRecord = inOutRecordDao.selectGoodsOutRecordById(outRecordId);
		Integer outNumber = outRecord.getOutNumber();
		RepertoryGoodsPosition goodsPosition = repertoryDao.selectGoodsNumberByPosition(outRecord.getGoodsPositionId());
		//得到此物品在仓位中的数量
		Integer goodsNumber = goodsPosition.getGoodsNumber();
		RepertoryGoods goods = goodsDao.selectGoodsById(outRecord.getGoodsId());
		//得到此物品的总数量
		Integer totalNumber = goods.getTotalNumber();
		//计算出删除出库记录后 仓位物品的数量
		Integer newGoodsNumber = goodsNumber + outNumber;
		//计算出删除出库记录后 物品总量的数量
		Integer newTotalNumber = totalNumber + outNumber;
		GoodsForm goodsForm = new GoodsForm();
		goodsForm.setGoodsPositionId(outRecord.getGoodsPositionId());
		goodsForm.setGoodsNumber(newGoodsNumber);
		if(!repertoryDao.updateRepertoryPosition(goodsForm)){
			throw new RuntimeException("更新仓位物品数量失败");
		}
		goodsForm.setTotalNumber(newTotalNumber);
		goodsForm.setGoodsId(outRecord.getGoodsId());
		if(!goodsDao.updateGoods(goodsForm)){
			throw new RuntimeException("更新物品数量失败");
		}
		if(!inOutRecordDao.deleteGoodsOutRecord(outRecordId)){
			throw new RuntimeException("删除出库记录失败");
		}
	}
	
	/**
	 *  插入领用部门的信息
	 * @param departmentId
	 */
	public void insertGetDepartment(RepertoryDepartment department){
		if(!inOutRecordDao.insertGetDepartment(department)){
			throw new RuntimeException("新建领用部门");
		}
	}
	/**
	 * 删除一个领用部门
	 * @param department
	 */
	public void deleteGetDepartment(String departmentId){
		if(!inOutRecordDao.deleteGetDepartment(departmentId)){
			throw new RuntimeException("删除领用部门失败");
		}
	}
	/**
	 * 修改领用部门的信息
	 * @param department
	 */
	public void updateGetDepartment(RepertoryDepartment department){
		if(!inOutRecordDao.updateGetDepartment(department)){
			throw new RuntimeException("修改领用部门的信息失败");
		}
	}
	/**
	 * 查询所有部门信息 
	 * @return
	 */
	public List<RepertoryDepartment> selectGetDepartment(){
		List<RepertoryDepartment> list = inOutRecordDao.selectGetDepartment();
		return list;
	}
	
	/**
	 * 进行移库移位操作
	 */
	public void moveGoodsRepertoryByOutIn(GoodsForm goodsForm, HttpServletRequest request){
		this.moveGoodsRepertoryByOut(goodsForm, request);
		this.moveGoodsRepertoryByIn(goodsForm, request);
	}

}
