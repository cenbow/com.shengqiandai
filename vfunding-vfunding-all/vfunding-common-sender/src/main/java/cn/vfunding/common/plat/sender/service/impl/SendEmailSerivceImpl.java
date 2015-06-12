package cn.vfunding.common.plat.sender.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import cn.vfunding.common.framework.common.constants.SendEmailConstants;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.send.util.SendCommon;
import cn.vfunding.common.framework.utils.beans.BeanUtils;
import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.common.plat.sender.common.ErrorCode;
import cn.vfunding.common.plat.sender.dao.SendEmailMapper;
import cn.vfunding.common.plat.sender.model.Attachment;
import cn.vfunding.common.plat.sender.model.SendEmail;
import cn.vfunding.common.plat.sender.service.ISendEmailService;

public class SendEmailSerivceImpl implements ISendEmailService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${email.username}")
	private String username;
	@Value("${email.password}")
	private String password;
	@Value("${email.host}")
	private String host;
	@Value("${email.from}")
	private String from;
	@Value("${email.smtp.auth}")
	private String auth;
	@Value("${email.smtp.timeout}")
	private String timeout;

	@Autowired
	private SendEmailMapper sendEmailMapper;

	@Override
	public void sendMailWithHtmlAttachment(List<String> addresses,
			SendEmail sendEmail, List<Attachment> attachments) throws Exception {
		List<SendEmail> emailSends = new ArrayList<SendEmail>();
		try {

			if (addresses == null || addresses.size() <= 0) {
				throw new BusinessException(ErrorCode.ERROR_30000,
						"邮件发送失败,邮箱不能为空");
			}
			if (sendEmail.getContent() == null
					|| sendEmail.getContent().trim().equals("")) {// 校验短信内容不能为空
				throw new BusinessException(ErrorCode.ERROR_30000,
						"邮件发送失败,邮箱内容不能为空");
			}
			Pattern pattern = Pattern
					.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
			for (Iterator<String> iterator = addresses.iterator(); iterator
					.hasNext();) {
				String address = iterator.next().trim();
				if (!pattern.matcher(address).matches()) {
					iterator.remove();
				} else {
					SendEmail sendEmailNew = BeanUtils.copyBean(sendEmail,
							SendEmail.class);
					sendEmailNew.setId(ObjectId.get());
					sendEmailNew.setAddress(address);
					sendEmailNew.setStatus(SendEmailConstants.STATUS_SENDING);
					sendEmailNew.setEmailType(SendEmailConstants.TYPE_SYSTEM);
					sendEmailNew.setSendTime(new Date());
					emailSends.add(sendEmailNew);
					sendEmailMapper.insert(sendEmailNew);
				}
			}
			JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
			senderImpl.setHost(this.host);
			MimeMessage mailMessage = senderImpl.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(
					mailMessage, true, "utf-8");

			messageHelper
					.setTo(addresses.toArray(new String[addresses.size()])); // 收件人
			messageHelper.setFrom(this.from);
			messageHelper.setSubject(sendEmail.getTitle());
			messageHelper.setText(sendEmail.getContent(), true); // true
																	// 表示启动HTML格式的邮件
			if (attachments != null && attachments.size() > 0) {
				for (Attachment attachment : attachments) {
					final byte[] datas = attachment.getAttachmentData();
					InputStreamSource inputStreamSource = new InputStreamSource() {
						@Override
						public InputStream getInputStream() throws IOException {
							return new ByteArrayInputStream(datas);
						}
					};
					messageHelper.addAttachment(attachment.getAttachmentName(),
							inputStreamSource);
				}
			}

			senderImpl.setUsername(this.username);
			senderImpl.setPassword(this.password);
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", auth); // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
			prop.put("mail.smtp.timeout", timeout);
			senderImpl.setJavaMailProperties(prop);
			senderImpl.send(mailMessage); 
			for (SendEmail es : emailSends) {
				sendEmail.setSendTime(new Date());
				es.setStatus(SendEmailConstants.STATUS_SUCC);
				sendEmailMapper.updateByPrimaryKey(es);
			}
		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			for (SendEmail es : emailSends) {
				sendEmail.setSendTime(new Date());
				es.setStatus(SendEmailConstants.STATUS_ERROR);
				sendEmailMapper.updateByPrimaryKey(es);
			}
			e.printStackTrace();
		}

	}

	@Override
	public void sendMail(List<String> addresses, SendEmail sendEmail)
			throws Exception {
		this.sendMailWithHtmlAttachment(addresses, sendEmail, null);
	}

	@Override
	public void sendMail(SendEmail sendEmail) throws Exception {

		List<String> addresses = new ArrayList<String>();
		addresses.add(sendEmail.getAddress());
		sendMail(addresses, sendEmail);
	}

	@Override
	public void sendOrderMail(List<String> addresses, SendEmail sendEmail)
			throws Exception {

		this.sendMailWithHtmlAttachment(addresses, sendEmail, null);
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public void sendByTemplet(SendCommon sendCommon) throws Exception {
	}

}
