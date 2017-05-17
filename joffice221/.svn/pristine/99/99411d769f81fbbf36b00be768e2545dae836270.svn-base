package com.htsoft.oa.dao.info;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.ShortMessage;

public interface ShortMessageDao extends BaseDao<ShortMessage> {

//	public ShortMessage findByRead(AppUser user);
	public List<ShortMessage> findAll(Long userId,PagingBean pb);
	public List<ShortMessage> findByUser(Long userId);
	public List searchShortMessage(Long userId,ShortMessage shortMessage, Date from, Date to, PagingBean pb,Short readFlag,String sort,String dir);
}
