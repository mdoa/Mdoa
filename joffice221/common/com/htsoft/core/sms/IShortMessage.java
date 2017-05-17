package com.htsoft.core.sms;

import java.util.List;

/**
 * 发送短消息接口。
 * @author ray
 *
 */
public interface IShortMessage {
	public boolean sendSms(List<String> mobiles, String message);
}
