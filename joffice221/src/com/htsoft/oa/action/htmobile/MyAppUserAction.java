package com.htsoft.oa.action.htmobile;

import java.util.List;

import javax.annotation.Resource;

import oracle.net.aso.i;

import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.PhoneBook;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.communicate.PhoneBookService;
import com.htsoft.oa.service.system.AppUserService;

public class MyAppUserAction extends BaseAction{

	@Resource
	private AppUserService appUserService;
	@Resource
	private PhoneBookService phoneBookService;
	
	//显示所有用户
	public String list(){
		String isOut=RequestUtil.getString(getRequest(), "isOut");
		if ("0".equals(isOut)) {
			List<AppUser> list = appUserService.getAll();
			for (int i = 0; i < list.size(); i++) {
				if ("-1".equals(list.get(i).getId())) {
					list.remove(i);
				}
			}
			JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toPageJson(list, list.size());
		}else if ("1".equals(isOut)) {
			List<PhoneBook> phoneBooks = phoneBookService.getAll();
			JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toPageJson(phoneBooks, phoneBooks.size());
		}
		
		return SUCCESS;
	}
}
