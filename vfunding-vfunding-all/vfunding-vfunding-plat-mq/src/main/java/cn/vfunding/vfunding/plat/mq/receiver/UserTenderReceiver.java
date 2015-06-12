package cn.vfunding.vfunding.plat.mq.receiver;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.prize.service.IPrizeService;
import cn.vfunding.vfunding.common.module.activemq.message.useraction.UserTenderMessage;

/**
 * 用户注册消息接收对象
 * 
 * @author liuyijun
 * 
 */
@Component
public class UserTenderReceiver extends MessageReceiver {

	@Autowired
	private IBorrowTenderService tenderService;

	@Autowired
	private IPrizeService prizeService;

	@Value("${AnniversarySeason1TurnplateStart}")
	private String anniversarySeason1TurnplateStart;

	@Value("${AnniversarySeason1TurnplateEnd}")
	private String anniversarySeason1TurnplateEnd;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void receive(Object message) {
		UserTenderMessage ms = (UserTenderMessage) message;
		try {
			Thread.sleep(2000);

			//首投发系统消息
			this.tenderService.insertSystemMessage(ms);
			//投资有效金额大于10000元，一周年庆抽奖次数+1(并且在活动有效期内)
			Date start = DateUtil.getDateToString(anniversarySeason1TurnplateStart, "yyyy-MM-dd HH:mm:ss");
			Date end = DateUtil.getDateToString(anniversarySeason1TurnplateEnd, "yyyy-MM-dd HH:mm:ss");
			Date now = new Date();
			if(now.compareTo(start)>=0 && now.compareTo(end)<=0){
				BorrowTender bt = this.tenderService.selectByPrimaryKey(ms.getTenderId());
				BigDecimal money = new BigDecimal(bt.getAccount());
				if(money.compareTo(new BigDecimal(10000))>=0){
					this.prizeService.addUserPrizeChooseCount(ms.getUserId(),2);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.useraction.UserTenderMessage");
	}

}
