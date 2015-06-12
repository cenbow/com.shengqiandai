package cn.vfunding.common.plat.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.jms.activemq.JmsSender;
import cn.vfunding.common.framework.jms.activemq.JmsSenderModel;
import cn.vfunding.common.framework.jms.activemq.JmsSenderModelBuilder;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;

/**
 * 
 * @author lilei
 * 
 */
@Controller
@RequestMapping("send/jms")
public class SendJmsController {
	// @Autowired
	// @Qualifier("jms.sender.useraction")
	// private JmsSender jmsUserActionSender;
	//
	// @Autowired
	// @Qualifier("jms.sender.system")
	// private JmsSender jmsSystemSender;

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public void sendJms(@RequestBody JmsSenderModel model) throws Exception {
		// if (EmptyUtil.isNotEmpty(model)) {
		// if (EmptyUtil.isNotEmpty(model.getJmsDestinationName())) {
		// if
		// (model.getJmsDestinationName().equals(JmsSenderModelBuilder.JMS_SYSTEM))
		// {
		// if (EmptyUtil.isNotEmpty(model.getMessage())) {
		// this.jmsSystemSender.sendAsynchronousMessage(model
		// .getMessage());
		// }
		// } else if
		// (model.getJmsDestinationName().equals(JmsSenderModelBuilder.JMS_USERACTION))
		// {
		// if (EmptyUtil.isNotEmpty(model.getMessage())) {
		//
		// this.jmsUserActionSender.sendAsynchronousMessage(model
		// .getMessage());
		// }
		// }
		// }
		// }

		System.out.println(model.getMessage().getClass());
	}
}
