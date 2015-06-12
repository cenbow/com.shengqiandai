package cn.vfunding.common.plat.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.plat.sender.service.ISendVoiceService;

/**
 * 
 * @author lilei
 * 
 */
@Controller
@RequestMapping("send/voice")
public class SendVoiceController {
	@Autowired
	private ISendVoiceService sendVoiceService;

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
			result = sendVoiceService.sendVoice(phone, content, from, type);
		} else {
			result = sendVoiceService.sendVoice(phone, content, "", "");
		}
		Integer i = Integer.parseInt(result);
		return i;
	}

}
