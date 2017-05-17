package com.htsoft.core.web.action;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.mail.MailSender;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htsoft.core.Constants;
import com.htsoft.core.engine.MailEngine;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.DateUtil;
import com.htsoft.core.web.paging.PagingBean;

/**
 * Ext Base Action for all the request.
 * 
 * @author csx
 * 
 */
public class BaseAction {
	/**
	 * The action execution was successful. (动作执行是成功的。)
	 */
	public static final String SUCCESS = "success";
	/**
	 * The action execution require more input in order to succeed.
	 * (动作执行需要更多的输入为了成功。)
	 */
	public static final String INPUT = "input";
	/**
	 * The action execution was a failure. (动作执行是失败的。)
	 */
	public static final String ERROR = "error";
	/**
	 * The action execution was successful but do not show a view.
	 * (动作执行很成功,但是不显示一个视图。)
	 */
	public static final String NONE = "none";
	/**
	 * The action execution was cancel
	 */
	public static final String CANCEL = "cancel";
	/**
	 * The action execution was view
	 */
	public static final String VIEW = "view";

	/**
	 * 成功跳转的页面(jsp)
	 */
	private String successResultValue = "/jsonString.jsp";

	/**
	 * 默认返回json字符串
	 */
	public static final String JSON_SUCCESS = "{\"success\":\"true\"}";

	/**
	 * 返回错误
	 */
	public static final String JSON_ERROR = "{success:false}";

	/**
	 * 返回失败
	 */
	public static final String JSON_FAILURE = "{failure:true}";

	/**
	 * 结合Ext的分页功能： dir DESC limit 25 sort id start 50
	 */
	/**
	 * 当前是升序还是降序排数据
	 */
	protected String dir;
	/**
	 * 排序的字段
	 */
	protected String sort;
	/**
	 * 每页的大小 默认25
	 */
	protected Integer limit = 25;
	/**
	 * 开始取数据的索引号
	 */
	protected Integer start = 0;

	/**
	 * json字符串
	 */
	protected String jsonString = JSON_SUCCESS;

	/**
	 * 日志
	 */
	protected transient final Log logger = LogFactory.getLog(getClass());

	/**
	 * 邮件发送
	 */
	protected MailEngine mailEngine;

	/**
	 * 邮件发送
	 */
	protected MailSender mailSender;

	/**
	 * 创建一个Jackson对象
	 */
	protected JacksonMapper mapper = new JacksonMapper(true,
			Constants.DATE_FORMAT_FULL);

	/**
	 * 构造方法
	 */
	public BaseAction() {
	}

	/**
	 * Convenience method to get the request
	 * 
	 * @return current request
	 */
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * Convenience method to get the response
	 * 
	 * @return current response
	 */
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * Convenience method to get the session. This will create a session if one
	 * doesn't exist.
	 * 
	 * @return the session from the request (request.getSession()).
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	// ---------------------------Methods------------------------------

	protected PagingBean getInitPagingBean() {
		PagingBean pb = new PagingBean(start, limit);
		return pb;
	}

	/**
	 * 列表
	 * 
	 * @return
	 */
	public String list() {
		return SUCCESS;
	}

	/**
	 * 编辑
	 * 
	 * @return
	 */
	public String edit() {
		return INPUT;
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	public String save() {
		return INPUT;
	}

	/**
	 * 全部保存
	 * 
	 * @return
	 */
	public String multiSave() {
		return SUCCESS;
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	public String delete() {
		return SUCCESS;
	}

	/**
	 * 多选删除
	 * 
	 * @return
	 */
	public String multiDelete() {
		return SUCCESS;
	}

	/**
	 * 按url返回其默认对应的jsp
	 * 
	 * @return
	 * @throws Exception
	 */
	public String execute() throws Exception {
		HttpServletRequest request = getRequest();
		String uri = request.getRequestURI();
		String url = uri.substring(request.getContextPath().length());
		url = url.replace(".do", ".jsp");
		url = "/pages" + url;

		if (logger.isInfoEnabled()) {
			logger.info("forward url:" + url);
		}
		setSuccessResultValue(url);
		return SUCCESS;

	}

	/**
	 * gson list的列表
	 * 
	 * @param listData
	 * @param totalItems
	 * @param onlyIncludeExpose
	 *            仅是格式化包括@Expose标签的字段
	 * @return
	 */
	public String gsonFormat(List<?> listData, int totalItems,
			boolean onlyIncludeExpose) {
		StringBuffer buff = new StringBuffer("{\"success\":true,\"totalCounts\":")
				.append(totalItems).append(",\"result\":");

		Gson gson = null;
		if (onlyIncludeExpose) {
			gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
					.setDateFormat(Constants.DATE_FORMAT_FULL).create();
		} else {
			gson = new GsonBuilder().setDateFormat(Constants.DATE_FORMAT_FULL)
					.create();
		}
		buff.append(gson.toJson(listData));

		buff.append("}");

		return buff.toString();
	}

	public String gsonFormat(List<?> listData, int totalItems) {
		return gsonFormat(listData, totalItems, false);
	}

	/**
	 * 传递导出所需参数
	 */
	public void setExportParameter(List<?> list) {
		getRequest().setAttribute("colId", getRequest().getParameter("colId"));
		getRequest().setAttribute("colName",
				getRequest().getParameter("colName"));
		getRequest().setAttribute("exportType",
				getRequest().getParameter("exportType"));
		getRequest().setAttribute("isExport",
				getRequest().getParameter("isExport"));
		String datetime = DateUtil.getTimeFormat("yyyyMMddhhmmssSSS");
		getRequest().setAttribute("fileName", datetime);
		getRequest().setAttribute("__exportList", list);
	}

	// ---------------------------getting and
	// setting------------------------------
	/**
	 * @return the successResultValue
	 */
	public String getSuccessResultValue() {
		return successResultValue;
	}

	/**
	 * @param successResultValue
	 *            the successResultValue to set
	 */
	public void setSuccessResultValue(String successResultValue) {
		this.successResultValue = successResultValue;
	}

	/**
	 * @return the jsonString
	 */
	public String getJsonString() {
		return jsonString;
	}

	/**
	 * @param jsonString
	 *            the jsonString to set
	 */
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	/**
	 * @return the mailEngine
	 */
	public MailEngine getMailEngine() {
		return mailEngine;
	}

	/**
	 * @param mailEngine
	 *            the mailEngine to set
	 */
	public void setMailEngine(MailEngine mailEngine) {
		this.mailEngine = mailEngine;
	}

	/**
	 * @return the mailSender
	 */
	public MailSender getMailSender() {
		return mailSender;
	}

	/**
	 * @param mailSender
	 *            the mailSender to set
	 */
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * @return the mapper
	 */
	public JacksonMapper getMapper() {
		return mapper;
	}

	/**
	 * @param mapper
	 *            the mapper to set
	 */
	public void setMapper(JacksonMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @param dir
	 *            the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort
	 *            the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit
	 *            the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}

	/**
	 * @param start
	 *            the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}

}
