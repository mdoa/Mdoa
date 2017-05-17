package com.htsoft.oa.action.htmobile;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.PhoneBook;
import com.htsoft.oa.model.communicate.PhoneGroup;
import com.htsoft.oa.service.communicate.PhoneBookService;

import freemarker.template.utility.StringUtil;

/*
 * author xianggang
 * 公司  hotent
 */
public class AddressBookAction extends BaseAction {

	@Resource
	private PhoneBookService phoneBookService;
	private PhoneBook phoneBook;
	private PhoneGroup phoneGroup;
	private String phoneId;

	/**
	 * 取得通讯录列表,isPublic=1时表示为公共通讯簿，isPublic=0时为个人通讯录
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.setSearchAll(true);
		List<PhoneBook> list = phoneBookService.getAll(filter);
		for (int i = 0; i < list.size(); i++) {
			PhoneBook pb = list.get(i);
			if (StringUtils.isEmpty(pb.getQqNumber()))
				pb.setQqNumber("");
			if (StringUtils.isEmpty(pb.getPhone()))
				pb.setPhone("");
			if (StringUtils.isEmpty(pb.getMsn()))
				pb.setMsn("");
		}
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 取得共享通讯录列表
	 */
	public String shared() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_isShared_SN_EQ", "1");
		filter.setSearchAll(true);
		List<PhoneBook> list = phoneBookService.getAll(filter);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	public String get() {
		PhoneBook phoneBook = phoneBookService.get(Long.parseLong(phoneId));
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
		jsonString = mapper.toDataJson(phoneBook);
		return SUCCESS;
	}

	public String save() {
		phoneBook.setAppUser(ContextUtil.getCurrentUser());
		phoneBookService.save(phoneBook);
		setJsonString("{\"success\":true}");
		return SUCCESS;
	}

	public String del() {
			String[] phones = StringUtil.split(phoneId, ',');
			for (String phone : phones) {
//				phoneBookService.remove(Long.parseLong(phone));
			}
		setJsonString("{\"success\":true}");
		return SUCCESS;
	}

	public PhoneGroup getPhoneGroup() {
		return phoneGroup;
	}

	public void setPhoneGroup(PhoneGroup phoneGroup) {
		this.phoneGroup = phoneGroup;
	}


	public String getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}

	public PhoneBook getPhoneBook() {
		return phoneBook;
	}

	public void setPhoneBook(PhoneBook phoneBook) {
		this.phoneBook = phoneBook;
	}

}
