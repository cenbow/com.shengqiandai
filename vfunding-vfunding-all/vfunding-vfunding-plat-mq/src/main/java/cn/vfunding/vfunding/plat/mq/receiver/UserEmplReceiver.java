package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmployeeService;
import cn.vfunding.vfunding.common.module.activemq.message.UserEmpMessage;

@Component
public class UserEmplReceiver extends MessageReceiver {

	/**
	 * 邮件服务器
	 */
	@Resource(name = "senderEmailRestInvokerFactory")
	private RestInvokerFactory senderEmailRestInvokerFactory;
	@Autowired
	private IEmployeeService employeeService;

	@Override
	public void receive(Object message) {
		UserEmpMessage msg = (UserEmpMessage) message;
		this.employeeService.insertUserEmpByReg(msg);
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.UserEmpMessage");
	}


}
