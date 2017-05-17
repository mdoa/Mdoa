package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.SerialNumber;
import com.htsoft.oa.service.system.SerialNumberService;
import com.htsoft.oa.util.SerialNumberUtil;

/**
 * 
 * @author
 * 
 */
public class SerialNumberAction extends BaseAction {
	@Resource
	private SerialNumberService serialNumberService;
	private SerialNumber serialNumber;

	private Long numberId;

	public Long getNumberId() {
		return numberId;
	}

	public void setNumberId(Long numberId) {
		this.numberId = numberId;
	}

	public SerialNumber getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(SerialNumber serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<SerialNumber> list = serialNumberService.getAll(filter);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				serialNumberService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		SerialNumber serialNumber = serialNumberService.get(numberId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(serialNumber);

		return SUCCESS;
	}

	/**
	 * 检查别名是否存在
	 * 
	 * @return
	 */
	public String checkAlias() {
		Long numberId = RequestUtil.getLong(getRequest(), "numberId");
		String alias = RequestUtil.getString(getRequest(), "alias");
		if (serialNumberService.checkAlias(numberId, alias)) {
			setJsonString("{success:false,'message':'别名已经存在请更换其他别名！'}");
		} else {
			setJsonString("{success:true}");
		}
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {

		if (serialNumber.getNumberId() == null) {
			serialNumber.setCurValue(serialNumber.getInitValue());
			serialNumberService.save(serialNumber);
		} else {
			SerialNumber orgSerialNumber = serialNumberService.get(serialNumber
					.getNumberId());
			try {
				BeanUtil.copyNotNullProperties(orgSerialNumber, serialNumber);
				serialNumberService.save(orgSerialNumber);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;

	}

	/**
	 * 根据别名生成流水号
	 * 
	 * @return
	 */
	public String genNumber() {
		String alias = RequestUtil.getString(getRequest(), "alias");
		String number = serialNumberService.genNumberByAlias(alias);
		setJsonString("{success:true,number:'" + number + "'}");
		return SUCCESS;
	}

	/**
	 * 预览生成流水号
	 * 
	 * @return
	 * @throws InterruptedException
	 */
	public String preview() throws InterruptedException {
		String value = "生成的10个流水号:";

		int initValue = serialNumber.getInitValue();
		int length = serialNumber.getNoLength();
		int step = serialNumber.getStep();
		String rule = serialNumber.getRegulation();
		for (int i = 0; i < 10; i++) {
			Thread.sleep(SerialNumberUtil.randomNumber(3));//程序太快的随机等待个时间让生成的预览好看些
			SerialNumberUtil.setCalendar(Calendar.getInstance());
			String v = SerialNumberUtil.genNumber(rule, initValue, length);
			initValue = initValue + step;
			value = value + "</br>" + v;
		}
		setJsonString("{success:true,'msg':'" + value + "'}");
		return SUCCESS;

	}

}
