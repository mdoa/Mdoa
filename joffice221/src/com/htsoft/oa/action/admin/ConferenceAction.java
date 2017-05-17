package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.admin.BoardRoo;
import com.htsoft.oa.model.admin.BoardType;
import com.htsoft.oa.model.admin.Conference;
import com.htsoft.oa.service.admin.BoardRooService;
import com.htsoft.oa.service.admin.BoardTypeService;
import com.htsoft.oa.service.admin.ConfPrivilegeService;
import com.htsoft.oa.service.admin.ConferenceService;


/**
 * @description ConferenceAction
 * @class ConferenceAction
 * @author YHZ
 * @company www.jee-soft.cn
 * @date 2010-10-8 PM
 * 
 */
public class ConferenceAction extends BaseAction {


	@Resource
	private ConferenceService conferenceService;
	@Resource
	private BoardRooService boardRooService;
	@Resource
	private BoardTypeService boardTypeService;
	@Resource
	private ConfPrivilegeService confPrivilegeService;

	private Long confId;
	private String filePath;
	private String checkReason;
	// ###权限Id####//
	private String updater; // 修改人

	private Conference conference;

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Long getConfId() {
		return confId;
	}

	public void setConfId(Long confId) {
		this.confId = confId;
	}

	public Conference getConference() {
		return conference;
	}

	public void setConference(Conference conference) {
		this.conference = conference;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}
	
	// 我的将要参加的会议
	public String displayMyconf() {
	   if(conference == null)
	    	conference = new Conference();
	    conference.setStatus((short)1);
	    PagingBean pb = getInitPagingBean();
	  //获取排序列表信息
	    String sort = getRequest().getParameter("sort");
	    String dir = getRequest().getParameter("dir");
	    List<Conference> list = conferenceService.myJoin(conference, false, pb,sort,dir);
		for (int i = 0; i < list.size(); i++) {
			if (i > 7) { // 只显示8条数据
				for (int j = 7; j < list.size(); j++)
					list.remove(j);
			}
		}
		getRequest().setAttribute("myConferenceList", list);
		return "display";
	}

	/**
	 * @description 暂存会议信息
	 */
	public String temporary() {
		QueryFilter filter = new QueryFilter(getRequest());
		// 暂存会议status=0
		filter.addFilter("Q_status_SN_EQ", "0");
		return filter(filter);
	}

	/**
	 * @description 待我参加
	 */
	public String myJoin() {
	   if(conference == null)
	    	conference = new Conference();
	    	conference.setStatus((short)1);
	    PagingBean pb = getInitPagingBean();
	    //获取排序列表信息
	    String sort = getRequest().getParameter("sort");
	    String dir = getRequest().getParameter("dir");
	    List<Conference> list = conferenceService.myJoin(conference, false, pb,sort,dir);
	    return toJson(pb, list);
	}

	/**
	 * @description 我已参加
	 */
	public String myJoined() {
	    if(conference == null)
	    	conference = new Conference();
	    conference.setStatus((short)1);
	    PagingBean pb = getInitPagingBean();
	  //获取排序列表信息
	    String sort = getRequest().getParameter("sort");
	    String dir = getRequest().getParameter("dir");
	    List<Conference> list = conferenceService.myJoin(conference, true, pb,sort,dir);
	    return toJson(pb, list);
	}

	/**
	 * @description 待开会议{status=1发送,startTime未到}
	 */
	public String waitOpen() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_startTime_D_GE", DateUtil.getDateTime());
		filter.addFilter("Q_status_SN_EQ", "1");
		return filter(filter);
	}

	/**
	 * @description 已开会议{status=1发送,endTime已过}
	 */
	public String haveOpen() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_endTime_D_LE", DateUtil.getDateTime());
		filter.addFilter("Q_status_SN_EQ", "1");
		return filter(filter);
	}

	/**
	 * @description 获取创建会议纪要标题信息
	 */
	public String getConfTopic() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_status_SN_EQ", "1");
		filter.addFilter("Q_endTime_D_LE", new Date().getTime() + "");
		List<Conference> list = conferenceService.getAll(filter);
		for(int i = 0; i<list.size(); i++){
			Conference conf = list.get(i);
			short st = confPrivilegeService.getPrivilege(conf.getConfId(), Conference.EDIT);
			if(st != Conference.EDIT){
				list.remove(i);
			}
		}
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
		jsonString=mapper.toPageJson(list, filter.getPagingBean().getTotalItems());
		return SUCCESS;
	}

	/**
	 * @description 发送会议通知审批
	 */
	public String send() {
		String rs = conferenceService.judgeBoardRoomNotUse(conference.getStartTime(),
				conference.getEndTime(), conference.getRoomId());
		if(rs.equalsIgnoreCase("success"))
			return customSave(Conference.Apply);
		else{
			 setJsonString("{failure:true,msg:'" + rs + "'}");
			 return SUCCESS;
		}
	}

	/**
	 * @description 暂存会议申请
	 */
	public String temp() {
		return customSave(Conference.TEMP);
	}

	/**
	 * 显示列表,并筛选没有该用户权限的数据
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		return filter(filter);
	}

	/**
	 * 批量删除,删除之前
	 */
	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids)
				conferenceService.remove(new Long(id));
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * @description 显示详细信息
	 */
	public String get() {
		Conference conference = conferenceService.get(confId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(conference);
		return SUCCESS;
	}

	/**
	 * 编辑
	 */
	public String save() {
		conferenceService.save(conference);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * @description 加载会议室信息[编号Id,标题title]
	 */
	public String getBoardroo() {
		List<BoardRoo> list = boardRooService.getAll();
		StringBuffer bf = new StringBuffer("[");
		for (BoardRoo br : list) {
			bf.append("['").append(br.getRoomId()).append("','").append(
					br.getRoomName()).append("'],");
		}
		bf.deleteCharAt(bf.length() - 1).append("]");
		setJsonString(bf.toString());
		return SUCCESS;
	}

	/**
	 * @description 获取所有的会议类型
	 */
	public String getTypeAll() {
		List<BoardType> list = boardTypeService.getAll();
		StringBuffer bf = new StringBuffer("[");
		for (BoardType bt : list) {
			bf.append("['").append(bt.getTypeId()).append("','").append(
					bt.getTypeName()).append("'],");
		}
		bf.deleteCharAt(bf.length() - 1).append("]");
		setJsonString(bf.toString());
		return SUCCESS;
	}

	/**
	 * @description 会议审批: 首先判断会议室是否可用，然后修改会议状态[审批]
	 */
	public String apply() {
		String msg = "{success:true}";
		String status = getRequest().getParameter("status");
		boolean bo = status != null && status.equals("1") ? true : false;
		if (bo) {// 通过审核,首先判断会议室是否可用
			String rs = judgeBoardRoomUse();
			if (rs.equalsIgnoreCase("success")) // 会议室可用
				conferenceService.apply(confId, checkReason, bo);
			else { // 会议室不可用
				Conference cf = conferenceService.get(confId);
				cf.setStatus(Conference.UNAPPLY);
				cf.setCheckReason("审核未通过," + rs);
				conferenceService.save(cf); // 修改
				msg = "{failure:true,msg:'" + rs + "'}";
			}
		} else
			conferenceService.apply(confId, checkReason, bo);
		setJsonString(msg);
		return SUCCESS;
	}

	/**
	 * @description 查询待我审核的会议信息
	 */
	public String waitCheckConf() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_checkUserId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addFilter("Q_status_SN_EQ", "2");
		filter.addSorted("createtime", "DESC");
		return filter(filter);
	}

	/**
	 * 查询没有通过的会议审核记录信息
	 */
	public String unThrought() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_status_SN_EQ", "3");
		filter.addSorted("createtime", "DESC");
		return filter(filter);
	}

	/**
	 * 待我审核的会议提示信息
	 */
	public String displyApply() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_checkUserId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addFilter("Q_status_SN_EQ", "2");
		filter.addFilter("Q_startTime_D_GE", DateUtil.getDateTime());
		filter.addSorted("createtime", "DESC");
		List<Conference> list = conferenceService.getAll(filter);
		if (list.size() > 8) {
			for (int i = 7; i < list.size(); i++) {
				list.remove(i);
			}
		}
		getRequest().setAttribute("applyConferenceList", list);
		return "displayApply";
	}

	// ###############################私有方法##################################//
	/**
	 * @description 将List转化为Json格式的数据
	 */
	private String toJson(PagingBean pb, List<Conference> list) {
		int total = list.size();
		if(pb != null){
			total = pb.getTotalItems();
		}
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, total);
		return SUCCESS;

	}
	

	/**
	 * @description 根据QueryFilter筛选查询对应的数据，返回Json格式的数据
	 */
	private String filter(QueryFilter filter) {
		filter.addSorted("startTime", "DESC");// 开始时间倒序
		List<Conference> list = conferenceService.getAll(filter);
		// 筛选可以查看的数据显示
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}


	/**
	 * 暂存和发送操作
	 */
	private String customSave(Short st) {
		if (conference.getIsEmail() == null)
			conference.setIsEmail(Conference.ISNOEMAIL);
		if (conference.getIsMobile() == null)
			conference.setIsMobile(Conference.ISNOMOBILE);
		conference.setStatus(st);
		// 查看人：主持人，记录人,参加人,当前创建会议用户,审核人
		String viewer = conference.getCompere() + ","
				+ conference.getRecorder() + "," + conference.getAttendUsers()
				+ "," + ContextUtil.getCurrentUserId() + ","
				+ conference.getCheckUserId();
		viewer = removeRepeatUserId(viewer);
		// 修改人：选择的用户,当前申请会议的用户
		updater = updater + "," + ContextUtil.getCurrentUserId();
		updater = removeRepeatUserId(updater);
		// 纪要人：会议记录人
		if (st == Conference.Apply) {
			conferenceService.send(conference, viewer, updater, conference
					.getRecorder(), filePath);
		} else {
			conferenceService.temp(conference, viewer, updater, conference
					.getRecorder(), filePath);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 判断会议室是否可用
	 */
	private String judgeBoardRoomUse() {
		Conference cf = conferenceService.get(confId);
		String msg = conferenceService.judgeBoardRoomNotUse(cf.getStartTime(),
				cf.getEndTime(), cf.getRoomId());
		return msg;
	}

	/**
	 * 去掉重复的用户id
	 */
	private String removeRepeatUserId(String uIds) {
		String msg = "";
		Map<String, String> map = new HashMap<String, String>();
		for (String uId : uIds.split(",")) {
			if (!map.containsKey(uId)) {
				map.put(uId, uId);
				msg += uId + ",";
			}
		}
		msg = msg.substring(0, msg.length() - 1);
		return msg;
	}
}
