package cn.vfunding.common.plat.sender.service;

import cn.vfunding.common.framework.send.util.SendCommon;

public interface ISendSmsService {
	/**
	 * 发送短消息
	 * @param phone 手机号码
	 * @param content
	 * @return
	 */
	String sendSms(String phone,String content);
	
	String sendSmsByTemplet(SendCommon common);
	
	/**
	 * 发送短消息
	 * @param phone 手机号码
	 * @param content 内容
	 * @param from 来源
	 * @param type 类型
	 * @return
	 */
	String sendSms(String phone, String content,String from,String type);
}
