package cn.vfunding.common.plat.sender.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.common.constants.SendEmailConstants;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.send.util.SendCommon;
import cn.vfunding.common.framework.utils.beans.BeanUtils;
import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.common.framework.utils.http.RestInvoker;
import cn.vfunding.common.plat.sender.common.ErrorCode;
import cn.vfunding.common.plat.sender.dao.SendEmailMapper;
import cn.vfunding.common.plat.sender.model.Attachment;
import cn.vfunding.common.plat.sender.model.SendEmail;
import cn.vfunding.common.plat.sender.properties.util.EmailTempletPropertiesUtil;
import cn.vfunding.common.plat.sender.service.ISendEmailService;

@Service("sendEmailByThirdSerivce")
public class SendEmailByThirdSerivceImpl implements ISendEmailService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	private static String basuUrl = "http://sendcloud.sohu.com/";
	private static String apiUser = "vfundingsend";
	private static String apiKey = "F1QwfwxYuiJesfXK";

	private static String fromAddress = "service@vfunding.cn";

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
			String toAddress = "";
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
					toAddress += address + ";";
				}
			}
			if (toAddress.endsWith(";")) {
				toAddress = toAddress.substring(0, toAddress.lastIndexOf(";"));
			}
			RestInvoker restInvoker = new RestInvoker();
			restInvoker.setBaseURL(basuUrl);
			MultipartEntityBuilder entity = MultipartEntityBuilder.create();
			entity.addPart(
					"api_user",
					new StringBody(apiUser, ContentType.create("text/plain",
							Consts.UTF_8)));
			entity.addPart(
					"api_key",
					new StringBody(apiKey, ContentType.create("text/plain",
							Consts.UTF_8)));
			entity.addPart(
					"from",
					new StringBody(fromAddress, ContentType.create(
							"text/plain", Consts.UTF_8)));
			entity.addPart(
					"to",
					new StringBody(toAddress, ContentType.create("text/plain",
							Consts.UTF_8)));
			entity.addPart("subject", new StringBody(sendEmail.getTitle(),
					ContentType.create("text/plain", Consts.UTF_8)));
			entity.addPart("html", new StringBody(sendEmail.getContent(),
					ContentType.create("text/plain", Consts.UTF_8)));
			entity.addPart(
					"fromname",
					new StringBody("微积金", ContentType.create("text/plain",
							Consts.UTF_8)));
			if (attachments != null && attachments.size() > 0) {
				for (Attachment attachment : attachments) {
					final byte[] datas = attachment.getAttachmentData();
					entity.addPart(
							"files",
							new ByteArrayBody(datas, attachment
									.getAttachmentName()));
				}
			}

			String result = restInvoker.postFiles("webapi/mail.send.json",
					entity);
			logger.info(result);

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

	@Override
	public void sendByTemplet(SendCommon sendCommon) throws Exception {
		StringBuffer content = new StringBuffer();
		String templetHeadMessage = EmailTempletPropertiesUtil
				.getValue("email.templet.head");
		String templetFooterMessage = EmailTempletPropertiesUtil
				.getValue("email.templet.footer");
		String templetMessage = EmailTempletPropertiesUtil.getValue(sendCommon
				.getSendMessageKey());

		String message = MessageFormat.format(templetMessage,
				sendCommon.getParams());
		content.append(templetHeadMessage);
		content.append(message);
		content.append(templetFooterMessage);
		SendEmail sendEmail = new SendEmail();
		sendEmail.setContent(content.toString());
		sendEmail.setAddress(sendCommon.getEmail());
		sendEmail.setTitle(EmailTempletPropertiesUtil.getValue(sendCommon.getSendMessageKey() + ".title"));
		this.sendMail(sendEmail);
	}
}
