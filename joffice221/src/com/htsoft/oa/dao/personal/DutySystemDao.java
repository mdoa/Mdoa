package com.htsoft.oa.dao.personal;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.DutySystem;

/**
 * 
 * @author 
 *
 */
public interface DutySystemDao extends BaseDao<DutySystem>{
	/**
	 *  更新为非缺省
	 */
	public void updateForNotDefult();
	/**
	 * 取得缺省的班制
	 * @return
	 */
	public List<DutySystem> getDefaultDutySystem();
}