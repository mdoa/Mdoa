package com.htsoft.oa.service.system;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Diary;

/**
 * @description 日志管理
 * @class DiaryService
 * @updater YHZ
 * @company www.jee-soft.cn
 * @data 2010-12-27AM
 * 
 */
public interface DiaryService extends BaseService<Diary> {

	public List<Diary> getAllBySn(PagingBean pb);

	/**
	 * 查找下属的所有工作日志
	 * 
	 * @param userIds
	 * @param pb
	 * @return
	 */
	public List<Diary> getSubDiary(String userIds, PagingBean pb, String sort);

}
