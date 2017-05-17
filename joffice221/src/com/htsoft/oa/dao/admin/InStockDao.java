package com.htsoft.oa.dao.admin;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.math.BigDecimal;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.InStock;

/**
 * 
 * @author 
 *
 */
public interface InStockDao extends BaseDao<InStock>{
	public Integer findInCountByBuyId(Long buyId);
}