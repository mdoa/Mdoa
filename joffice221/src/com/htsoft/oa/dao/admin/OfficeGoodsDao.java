package com.htsoft.oa.dao.admin;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.OfficeGoods;

/**
 * 
 * @author 
 *
 */
public interface OfficeGoodsDao extends BaseDao<OfficeGoods>{
	public List<OfficeGoods> findByWarm();
}