package com.htsoft.core.sms;

import org.springframework.beans.factory.FactoryBean;

import com.htsoft.core.sms.impl.ModemMessage;

/**
 * 短信FactoryBean。
 * <br>
 * <pre>
 * 根据短信类型返回不同发送短信方式。
 * 1.type modem 使用Modem发送短信。
 * 
 * 配置在app-beans.xml
 * 
 * &lt;bean id="sms" class="com.hotent.core.sms.SmsFactoryBean">
 *			&lt;property name="type" value="${smsType}"/>
 * &lt;/bean>
 * 
 * smsType:配置在 jdbc.properties
 * </pre>
 * @author ray
 *
 */
public class SmsFactoryBean implements FactoryBean {
	
	private String type="modem";
	
	
	/**
	 * 短信类型。
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	@Override
	public IShortMessage getObject() throws Exception {
		IShortMessage sms=null;
		if("modem".equals(type)){
			sms=ModemMessage.getInstance();
		}
		return sms;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return IShortMessage.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
