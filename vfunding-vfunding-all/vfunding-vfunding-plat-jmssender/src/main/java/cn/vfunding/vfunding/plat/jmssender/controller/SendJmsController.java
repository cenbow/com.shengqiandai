package cn.vfunding.vfunding.plat.jmssender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.jms.activemq.JmsSender;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.common.jmssender.JmsSenderObj;
import cn.vfunding.vfunding.common.jmssender.SenderObjBuilder;

import com.alibaba.fastjson.JSON;

/**
 * jms消息发送中心
 * @author liuyijun
 * 
 */
@Controller
@RequestMapping("send/jms")
public class SendJmsController {
	@Autowired
	@Qualifier("jms.sender.useraction")
	private JmsSender jmsUserActionSender;

	@Autowired
	@Qualifier("jms.sender.system")
	private JmsSender jmsSystemSender;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public void sendJms(@RequestBody JmsSenderObj obj) throws Exception {
		if (EmptyUtil.isNotEmpty(obj)) {
			if (EmptyUtil.isNotEmpty(obj.getJmsDestinationName())) {
				if (obj.getJmsDestinationName().equals(
						SenderObjBuilder.JMS_SYSTEM)) {
					if (EmptyUtil.isNotEmpty(obj.getInstanceJSON())) {
						this.jmsSystemSender.sendAsynchronousMessage(JSON
								.parseObject(obj.getInstanceJSON(),
										obj.getMessageCls()));
					}
				} else if (obj.getJmsDestinationName().equals(
						SenderObjBuilder.JMS_USERACTION)) {
					if (EmptyUtil.isNotEmpty(obj.getInstanceJSON())) {
						this.jmsUserActionSender.sendAsynchronousMessage(JSON
								.parseObject(obj.getInstanceJSON(),
										obj.getMessageCls()));
					}
				}
			}
		}
	}
}
