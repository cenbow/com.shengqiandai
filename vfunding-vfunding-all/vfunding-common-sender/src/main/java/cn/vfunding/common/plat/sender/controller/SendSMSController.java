package cn.vfunding.common.plat.sender.controller;

import org.eclipse.jetty.io.NetworkTrafficListener.Empty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.send.util.SendCommon;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.plat.sender.service.ISendSmsService;
import cn.vfunding.common.plat.sender.vo.SendSmsCommonVO;

/**
 * 
 * @author liuyijun
 * 
 */
@Controller
@RequestMapping("send/sms")
public class SendSMSController {
	@Autowired
	private ISendSmsService sendSmsService;

	/**
	 * 验证码发送接口
	 * 
	 * @param sendPhone
	 *            验证码发送接口(不支持群发)
	 */
	@RequestMapping(value = "")
	@ResponseBody
	public Integer send(@RequestParam("phone") String phone,
			@RequestParam("content") String content,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "from", required = false) String from)
			throws Exception {
		String result;
		if (EmptyUtil.isNotEmpty(type) && EmptyUtil.isNotEmpty(from)) {
			result = sendSmsService.sendSms(phone, content, from, type);
		} else {
			result = sendSmsService.sendSms(phone, content);
		}
		Integer i = Integer.parseInt(result);
		return i;
	}

	@RequestMapping(value = "/byJson", method = RequestMethod.POST)
	@ResponseBody
	public Integer sendByJson(@RequestBody SendSmsCommonVO vo) throws Exception {
		String result;
		if (EmptyUtil.isNotEmpty(vo.getType())
				&& EmptyUtil.isNotEmpty(vo.getFrom())) {
			result = sendSmsService.sendSms(vo.getPhone(), vo.getContent(),
					vo.getFrom(), vo.getType());
		} else {
			result = sendSmsService.sendSms(vo.getPhone(), vo.getContent());
		}
		Integer i = Integer.parseInt(result);
		return i;
	}

	@RequestMapping(value = "/byTemplet", method = RequestMethod.POST)
	@ResponseBody
	public Integer sendByTemplet(@RequestBody SendCommon sendCommon)
			throws Exception {
		String result = "-1";
		if (EmptyUtil.isNotEmpty(sendCommon)) {
			result = this.sendSmsService.sendSmsByTemplet(sendCommon);
		}
		Integer i = Integer.parseInt(result);
		return i;
	}

}
