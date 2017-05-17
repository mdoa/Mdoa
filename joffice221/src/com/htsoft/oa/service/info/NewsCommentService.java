package com.htsoft.oa.service.info;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.NewsComment;

public interface NewsCommentService extends BaseService<NewsComment>{
	public List<NewsComment> getByNewsId(Long NewsId, PagingBean pb);
}


