package cn.vfunding.common.plat.sender.controller;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.vfunding.common.framework.send.util.SendCommon;
import cn.vfunding.common.framework.utils.beans.BeanUtils;
import cn.vfunding.common.framework.utils.beans.FileUtil;
import cn.vfunding.common.plat.sender.model.Attachment;
import cn.vfunding.common.plat.sender.model.SendEmail;
import cn.vfunding.common.plat.sender.properties.util.EmailTempletPropertiesUtil;
import cn.vfunding.common.plat.sender.service.ISendEmailService;
import cn.vfunding.common.plat.sender.vo.SendEmailVO;

/**
 * 
 * @author liuyijun
 * 
 */
@Controller
@RequestMapping("send/email")
public class SendEmailController {
	
	
	@Autowired
	private ISendEmailService sendEmailService;

	/**
	 * 普通邮件发送接口
	 * 
	 * @param sendEmail
	 *            邮件发送实体(支持群发)
	 */
	@RequestMapping(value = "", method = RequestMethod.POST,consumes={"application/json"})
	@ResponseBody
	public void send(@RequestBody SendEmailVO sendEmailVO) throws Exception {
		sendEmailService.sendMail(sendEmailVO.getAddresses(),
				BeanUtils.copyBean(sendEmailVO, SendEmail.class));
	}

	/**
	 * 带附件普通邮件发送接口
	 * 
	 * @param multipartRequest支持多附件
	 * @param es
	 * @throws Exception
	 */
	@RequestMapping(value = "attachment", method = RequestMethod.POST)
	@ResponseBody
	public void send(MultipartHttpServletRequest multipartRequest,
			  SendEmailVO es) throws Exception {
		List<Attachment> attachments = new ArrayList<Attachment>();
		List<MultipartFile> files = multipartRequest.getFiles("attachments");
		for (MultipartFile multipartFile : files) {
			if(multipartFile.getSize()>0){
				Attachment attachment = new Attachment();
				final InputStream inputStream = multipartFile.getInputStream();				
				byte[] bytes = FileUtil.InputStreamToBytes(inputStream);
				String fileName = multipartFile.getOriginalFilename();
				attachment.setAttachmentData(bytes);
				attachment.setAttachmentName(fileName);
				attachments.add(attachment);
			}			
		}
		sendEmailService.sendMailWithHtmlAttachment(es.getAddresses(),
				BeanUtils.copyBean(es, SendEmail.class), attachments);
	}
	
	
	
	@RequestMapping(value = "/byTemplet", method = RequestMethod.POST,consumes={"application/json"})
	@ResponseBody
	public void send(@RequestBody SendCommon sendCommon) throws Exception {
		sendEmailService.sendByTemplet(sendCommon);
	}
}
