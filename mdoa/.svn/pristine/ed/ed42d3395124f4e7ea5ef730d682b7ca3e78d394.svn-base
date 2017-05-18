package com.mdoa.repertory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.repertory.bo.GoodsForm;
import com.mdoa.repertory.dao.GoodsDao;
import com.mdoa.repertory.dao.RepertoryDao;
import com.mdoa.repertory.model.RepertoryGoods;
import com.mdoa.repertory.model.RepertoryGoodsPosition;
import com.mdoa.repertory.model.RepertoryType;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;
/**
 * 仓库的service层
 * @author Administrator
 *
 */
@Service
@Transactional
public class RepertoryService {
	@Autowired
	private RepertoryDao repertoryDao;
	@Autowired
	private GoodsDao goodsDao;
	/**
	 * 根据时间查询仓位仓库和物品的信息以及模糊查询
	 * @param goodsForm
	 * @return
	 */
	public PageModel<GoodsForm> selectRepertoryPosition(GoodsForm goodsForm){
		if(!StringUtil.isEmpty(goodsForm.getGoodsName())){
			goodsForm.setGoodsName("%"+goodsForm.getGoodsName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getRepertoryName())){
			goodsForm.setRepertoryName("%"+goodsForm.getRepertoryName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsPositionName())){
			goodsForm.setGoodsPositionName("%"+goodsForm.getGoodsPositionName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsSize())){
			goodsForm.setGoodsSize("%"+goodsForm.getGoodsSize()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsTypeUrl())){
			goodsForm.setGoodsTypeUrl(goodsForm.getGoodsTypeUrl()+"%");
		}
		PageHelper.startPage(goodsForm.getPage(), goodsForm.getRows());
		List<GoodsForm> list = repertoryDao.selectRepertoryPosition(goodsForm);
		PageModel<GoodsForm> pageModel = new PageModel((Page)list);
		return pageModel;
	}
	/**
	 * 添加仓库
	 * @param repertoryType
	 */
	public void insertRepertoryType(RepertoryType repertoryType){
		if(!repertoryDao.insertRepertoryType(repertoryType)){
			throw new RuntimeException("添加仓库失败");
		}
	}
	/**
	 * 添加仓位
	 * @param goodsPosition
	 */
	public void insertRepertoryPosition(GoodsForm goodsForm){
		if(!repertoryDao.insertRepertoryPosition(goodsForm)){
			throw new RuntimeException("添加仓位失败");
		}
	}
	/**
	 * 删除仓库
	 * @param repertoryId
	 */
	public void deleteRepertory(String repertoryId){
		if(!repertoryDao.deleteRepertory(repertoryId)){
			throw new RuntimeException("删除仓库失败");
		}
	}
	/**
	 * 删除仓位
	 * @param goodsPositionId
	 */
	public void deleteRepertoryPosition(String goodsPositionId){
		//查询该仓位的物品信息
		RepertoryGoodsPosition goodsPosition = repertoryDao.selectGoodsNumberByPosition(goodsPositionId);
		System.out.println(goodsPosition);
		Integer goodsNumber = goodsPosition.getGoodsNumber();
		//判断此仓位是否有物品，如果没有则直接删除仓位
		if(goodsNumber == null || goodsNumber == 0){
			if(!repertoryDao.deleteRepertoryPosition(goodsPositionId)){
				throw new RuntimeException("删除仓位信息失败");
			}
		}else{
			throw new RuntimeException("物品数量不为零，不允许删除仓位");
		}
	}
	/**
	 * 修改仓库信息
	 * @param repertoryType
	 */
	public void updateRepertory(RepertoryType repertoryType){
		if(!repertoryDao.updateRepertory(repertoryType)){
			throw new RuntimeException("修改仓库失败");
		}
	}
	/**
	 * 修改仓位信息
	 * @param goodsPosition
	 */
	public void updateRepertoryPosition(GoodsForm goodsForm){
		
		if(!repertoryDao.updateRepertoryPosition(goodsForm)){
			throw new RuntimeException("修改仓位信息失败");
		}
	}
	/**
	 * 查询仓位信息 用于下拉框
	 * @return
	 */
	public List<RepertoryGoodsPosition> selectGoodsPosition(RepertoryGoodsPosition goodsPosition){
		List<RepertoryGoodsPosition> list = repertoryDao.selectGoodsPosition(goodsPosition);
		return list;
	}
	/**
	 * 查询仓库信息 用于下拉框
	 * @return
	 */
	public List<RepertoryType> selectRepertoryType(){
		List<RepertoryType> list = repertoryDao.selectRepertoryType();
		return list;
	}
}
