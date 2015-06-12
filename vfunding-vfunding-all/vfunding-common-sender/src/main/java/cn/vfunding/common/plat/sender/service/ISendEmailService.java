package cn.vfunding.common.plat.sender.service;

import java.util.List;

import cn.vfunding.common.framework.send.util.SendCommon;
import cn.vfunding.common.plat.sender.model.Attachment;
import cn.vfunding.common.plat.sender.model.SendEmail;

public interface ISendEmailService {
	/**
	 * 发送带附件的邮件
	 * @param sendEmail  邮件实体
	 * @param attachments  附件
	 * @throws Exception
	 */
	void sendMailWithHtmlAttachment(List<String> addresss,SendEmail sendEmail,List<Attachment> attachments) throws Exception;
	/**
	 * 发送普通邮件
	 * @param sendEmail  邮件实体
	 * @throws Exception
	 */
	void sendMail(List<String> addresss,SendEmail sendEmail) throws Exception;
	/**
	 * 发送订单邮件
	 * @param sendEmail  邮件实体
	 * @throws Exception
	 */
	void sendOrderMail(List<String> addresss,SendEmail sendEmail) throws Exception;
	
	/**
	 * 发送普通邮件
	 * @param sendEmail  邮件实体
	 * @throws Exception
	 */
	void sendMail(SendEmail sendEmail) throws Exception;
	
	/**
	 * 使用模板发送邮件
	 * @param sendCommon
	 * @throws Exception
	 */
	void sendByTemplet(SendCommon sendCommon) throws Exception;
	
	
}
