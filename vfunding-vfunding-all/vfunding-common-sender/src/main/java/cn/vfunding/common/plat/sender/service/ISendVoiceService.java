package cn.vfunding.common.plat.sender.service;

public interface ISendVoiceService {
	/**
	 *  发送语音验证码
	 * @param phone 手机号码
	 * @param content 内容
	 * @return
	 */
	String sendVoice(String phone,String content);
	/**
	 * 发送语音验证码
	 * @param phone 手机号码
	 * @param content 内容
	 * @param from 来源
	 * @param type 类型
	 * @return
	 */
	String sendVoice(String phone, String content,String from,String type);

}
