package cn.vfunding.common.plat.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.jms.activemq.TestJmsMessage;
import cn.vfunding.common.plat.sender.service.ISendVoiceService;

/**
 * 
 * @author lilei
 * 
 */
@Controller
@RequestMapping("test")
public class TestController {
	@Autowired
	private ISendVoiceService sendVoiceService;

	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public void test(@RequestBody TestJmsMessage message) throws Exception {
		System.out.println(message.getName());
		//Thread.sleep(5000);
	}

}
