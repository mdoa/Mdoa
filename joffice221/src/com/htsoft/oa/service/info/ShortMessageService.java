package com.htsoft.oa.service.info;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.info.ShortMessage;

public interface ShortMessageService extends BaseService<ShortMessage> {
	/**
	 * 查找某个用户的所有内部消息
	 */
	public List<ShortMessage> findAll(Long userId,PagingBean pb);
	
	/**
	 * 根据传入用户ID查询内部消息
	 * @param userId
	 * @return
	 */
	public List<ShortMessage> findByUser(Long userId);
	
	/**
	 * 根据是已读的状态查找
	 * @param userId用户Id
	 * @param shortMessage
	 * @param from
	 * @param to
	 * @param pb
	 * @param readFlag是否已读
	 * @param sort
	 * @param dir
	 * @return
	 */
	public List searchShortMessage(Long userId,ShortMessage shortMessage, Date from, Date to, PagingBean pb,Short readFlag,String sort,String dir);
	
	/**
	 * 保存发送消息
	 * @param senderId发送人
	 * @param receiveIds接收人
	 * @param content消息内容
	 * @param msgType消息类型
	 * 1=个人信息
	   2=日程安排
	   3=计划任务
	   4=代办任务提醒
	   5=系统提醒
	 * @return
	 */
	public ShortMessage save (Long senderId,String receiveIds,String content,Short msgType);
}
