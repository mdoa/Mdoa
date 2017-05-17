package com.htsoft.oa.service.system.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.Calendar;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.oa.dao.system.SerialNumberDao;
import com.htsoft.oa.model.system.SerialNumber;
import com.htsoft.oa.service.system.SerialNumberService;
import com.htsoft.oa.util.SerialNumberUtil;

public class SerialNumberServiceImpl extends BaseServiceImpl<SerialNumber>
		implements SerialNumberService {
	private SerialNumberDao dao;

	public SerialNumberServiceImpl(SerialNumberDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public boolean checkAlias(Long id, String alias) {
		return dao.checkAlias(id, alias);
	}

	@Override
	public String genNumberByAlias(String alias) {
		String number = "";
		if (StringUtil.isNotEmpty(alias)) {
			SerialNumber serialNumber = dao.getByAlias(alias);
			if (BeanUtil.isNotEmpty(serialNumber)) {
				Calendar calendar = Calendar.getInstance();
				SerialNumberUtil.setCalendar(calendar);
				if (serialNumber.getRegulation().contains(
						SerialNumberUtil.idNumber)) {// 流水号生成方式
					number = this.genSerialNumberByNo(serialNumber, calendar);
				} else {
					number = SerialNumberUtil.genNumberByRandom(
							serialNumber.getRegulation(),
							serialNumber.getNoLength());
				}
			}
		}
		return number;
	}

	/**
	 * 流水号方式生成
	 * 
	 * @param serialNumber
	 * @param calendar
	 * @return
	 */
	private String genSerialNumberByNo(SerialNumber serialNumber,
			Calendar calendar) {
		String nowDate = DateFormatUtil.format(calendar.getTime(), "yyyyMMdd");
		int curValue = serialNumber.getCurValue();
		int initValue = serialNumber.getInitValue();
		String curDate = serialNumber.getCurDate();
		if (StringUtil.isEmpty(curDate)) {
			curDate = nowDate;
		}
		// 根据当前生成规则生成流水号
		short genType = serialNumber.getGenType();
		if (genType == SerialNumber.genEveryDay.shortValue()) {
			if (!nowDate.substring(6, 8).equals(curDate.substring(6, 8))) {
				curValue = initValue;
			} else {
				curValue++;
			}
		} else if (genType == SerialNumber.genEveryMonth.shortValue()) {
			if (!nowDate.substring(4, 6).equals(curDate.substring(4, 6))) {
				curValue = initValue;
			} else {
				curValue++;
			}
		} else if (genType == SerialNumber.genEveryYear.shortValue()) {
			if (!nowDate.substring(0, 4).equals(curDate.substring(0, 4))) {
				curValue = initValue;
			} else {
				curValue++;
			}
		} else {
			curValue++;
		}

		serialNumber.setCurDate(nowDate);
		// 当前值
		serialNumber.setCurValue(curValue);

		dao.save(serialNumber);

		return SerialNumberUtil.genNumber(serialNumber.getRegulation(),
				curValue, serialNumber.getNoLength());

	}

}