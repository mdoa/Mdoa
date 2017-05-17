package com.htsoft.oa.service.system;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.SerialNumber;

public interface SerialNumberService extends BaseService<SerialNumber> {

	/**
	 * 检查别名是否重复
	 * 
	 * @param numberId
	 * @param alias
	 *            别名
	 * @return
	 */
	public boolean checkAlias(Long numberId, String alias);

	/**
	 * 根据别名生成流水号
	 * 
	 * @param alias
	 *            别名
	 * @return
	 */
	public String genNumberByAlias(String alias);

}
