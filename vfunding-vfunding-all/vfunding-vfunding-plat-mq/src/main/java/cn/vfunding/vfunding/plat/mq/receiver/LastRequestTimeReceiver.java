package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.biz.system.service.IUserTrackService;
import cn.vfunding.vfunding.common.module.activemq.message.LastRequestTimeMessage;

/**
 * 用户最后一次请求时间消息接收者
 * @author liuyijun
 *
 */
@Component
public class LastRequestTimeReceiver extends MessageReceiver{

	@Autowired
	private IUserTrackService  trackService;
	@Override
	public void receive(Object message) {
		LastRequestTimeMessage ms=(LastRequestTimeMessage) message;
		this.trackService.updateUserLastRequestTime(ms.getUserId(), ms.getSessionId());
	}
	
	@PostConstruct
	public void setMessageInfo(){
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.LastRequestTimeMessage");
	}

}
