package com.htsoft.oa.core;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */

/**
 * 全应用程序的常量
 * 
 * @author csx
 * 
 */
public class Constants {
	/**
	 * 代表禁用
	 */
	public static final Short FLAG_DISABLE = 0;
	/**
	 * 代表激活
	 */
	public static final Short FLAG_ACTIVATION = 1;
	/**
	 * 代表记录删除
	 */
	public static final Short FLAG_DELETED = 1;
	/**
	 * 代表未删除记录
	 */
	public static final Short FLAG_UNDELETED = 0;

	/**
	 * 应用程序的完整日期格式化符
	 */
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 短日期格式
	 */
	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	/***
	 * HH:mm 时间格式
	 */
	public static final String DATE_FORMAT_HM = "HH:mm";
	/**
	 * 日期格式数组</br> 暂时只考虑这几种日期格式yyyy-MM-dd HH:mm:ss、yyyy-MM-dd
	 */
	public static final String[] ACCEPT_DATE_FORMATS = { DATE_FORMAT_FULL,
			DATE_FORMAT_YMD, DATE_FORMAT_HM };

	/**
	 * 流程启动者，可用于在流程定义使用
	 */
	// public static final String FLOW_START_USER="flowStartUser";
	// /**
	// * 流程启动ID
	// */
	// public static final String FLOW_START_USERID="flowStartUserId";

	/**
	 * 流程任务执行过程中，指定了某人执行该任务，该标识会存储于Variable变量的Map中，随流程进入下一步
	 */
	public static final String FLOW_ASSIGN_ID = "flowAssignId";

	/**
	 * 为会签任务指定多个用户
	 */
	public static final String FLOW_SIGN_USERIDS = "signUserIds";

	/**
	 * 若当前流程节点分配的节点为流程启动者，其userId则为以下值
	 */
	public static final String FLOW_START_ID = "__start";
	/**
	 * 若当前流程分配置为当前启动者的上司，则其对应的ID为以下值
	 */
	public static final String FLOW_SUPER_ID = "__super";

	/**
	 * 请假流程的key
	 */
	public static final String FLOW_LEAVE_KEY = "ReqHolidayOut";

	/**
	 * 流程下一步跳转列表常量
	 */
	public static final String FLOW_NEXT_TRANS = "nextTrans";
	/**
	 * 公文ID
	 */
	public static final String ARCHIES_ARCHIVESID = "archives.archivesId";
	/**
	 * 公司LOGO路径
	 */
	public static final String COMPANY_LOGO = "app.logoPath";
	/**
	 * 默认的LOGO
	 */
	public static final String DEFAULT_LOGO = "/images/ht-logo.png";
	/**
	 * 公司名称
	 */
	public static final String COMPANY_NAME = "app.companyName";
	/**
	 * 默认公司的名称
	 */
	public static final String DEFAULT_COMPANYNAME = "广州市宏天软件有限公司";
	/**
	 * 通过审核
	 */
	public static final Short FLAG_PASS = 1;
	/**
	 * 不通过审核
	 */
	public static final Short FLAG_NOTPASS = 2;

	/**
	 * 启用
	 */
	public static final Short ENABLED = 1;
	/**
	 * 未启用
	 */
	public static final Short UNENABLED = 0;

}
