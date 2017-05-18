package com.mdoa.repertory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.base.service.BaseService;
import com.mdoa.repertory.bo.GoodsForm;
import com.mdoa.repertory.dao.GoodsDao;
import com.mdoa.repertory.model.RepertoryGoods;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;
/**
 * 物品的service层
 * @author Administrator
 *
 */
@Service
@Transactional
public class GoodsService extends BaseService{
	@Autowired
	private GoodsDao goodsDao;
	
	/**
	 * 添加物品信息
	 * @param repertoryGoods
	 */
	public void insertGoods(RepertoryGoods repertoryGoods){
		repertoryGoods.setGoodsTypeId(StringUtil.getIdFromUrl(repertoryGoods.getGoodsTypeUrl()));
		if(!goodsDao.insertGoods(repertoryGoods)){
			throw new RuntimeException("添加物品信息失败");
		}
	}
	/**
	 * 修改物品信息
	 * @param repertoryGoods
	 */
	public void updateGoods(GoodsForm goodsForm){
		if(!goodsDao.updateGoods(goodsForm)){
			throw new RuntimeException("修改物品信息失败");
		}
	}
	/**
	 * 删除物品
	 * @param goodsId
	 */
	public void deleteGoods(GoodsForm goodsForm){
		if(!goodsDao.deleteGoods(goodsForm)){
			throw new RuntimeException("删除物品失败");	
		}
	}
	/**
	 *  用户在点击物品的时候，在右侧会显示物品的出入库和仓库的信息
	 * @param goodsId
	 * @return
	 */
	public List<GoodsForm> selectRecordAndRepertoryById(String goodsId){
		List<GoodsForm> list = goodsDao.selectRecordAndRepertoryById(goodsId);
		return list;
	}
	/**
	 * 根据时间去查询物品信息以及查询条件
	 * @param repertoryGoods
	 * @return
	 */
	public PageModel<RepertoryGoods> selectGoodsByTime(GoodsForm goodsForm){
		if(!StringUtil.isEmpty(goodsForm.getGoodsName())){
			goodsForm.setGoodsName("%"+goodsForm.getGoodsName()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsSize())){
			goodsForm.setGoodsSize("%"+goodsForm.getGoodsSize()+"%");
		}
		if(!StringUtil.isEmpty(goodsForm.getGoodsTypeUrl())){
			goodsForm.setGoodsTypeUrl(goodsForm.getGoodsTypeUrl()+"%");
		}
		PageHelper.startPage(goodsForm.getPage(), goodsForm.getRows());
		List<RepertoryGoods> list = goodsDao.selectGoodsByTime(goodsForm);
		PageModel<RepertoryGoods> pageModel = new PageModel((Page)list);
		//汇总数据
		RepertoryGoods repertoryGoods = goodsDao.findSumByTypeUrl(goodsForm.getGoodsTypeUrl());
		pageModel.setSum(repertoryGoods);
		return pageModel;
	}
	
	/**
	 * 查询物品汇总数据
	 * @param goodsForm
	 * @return
	 */
	public RepertoryGoods findSum(GoodsForm goodsForm){
		//汇总数据
		RepertoryGoods repertoryGoods = goodsDao.findSumByTypeUrl(goodsForm.getGoodsTypeUrl());
		return repertoryGoods;
	}
}
