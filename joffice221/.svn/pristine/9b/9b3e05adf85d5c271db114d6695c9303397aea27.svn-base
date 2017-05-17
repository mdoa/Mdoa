package com.htsoft.oa.core.jms;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.htsoft.core.sms.IShortMessage;
import com.htsoft.core.sms.SmsFactoryBean;
import com.htsoft.oa.model.communicate.SmsMobile;
import com.htsoft.oa.service.communicate.SmsMobileService;

public class MobileMessageConsumer {
//	private static final Log logger=LogFactory.getLog(MobileMessageConsumer.class);
	
	private IShortMessage smsMessage;
	
	public void setSmsMessage(IShortMessage smsMessage) {
		this.smsMessage = smsMessage;
	}

	/**
	 * 发送一条手机短信
	 * @param smsMobile
	 * @throws Exception 
	 */
	public void sendMobileMsg(SmsMobile smsMobile) throws Exception{
//		logger.debug("send the sms mobile message now for :" + smsMobile.getPhoneNumber());
		//TODO 通过短信猫或端口发送
		//smsMessage.sendOneSms(smsMobile);
		String[] phones=smsMobile.getPhoneNumber().split(",");
		String message=smsMobile.getSmsContent();
		List<String> list=new ArrayList<String>();
		for(String str:phones){
			list.add(str);
		}
		smsMessage.sendSms(list, message);	
	}
	
}
