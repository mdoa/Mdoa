package com.htsoft.oa.service.communicate;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.communicate.SmsMobile;

public interface SmsMobileService extends BaseService<SmsMobile>{
	public List<SmsMobile> getNeedToSend();
	public void saveSms(String userIds,String content);
	public void sendSms();
	public void sendOneSms(SmsMobile smsMobile);
}


