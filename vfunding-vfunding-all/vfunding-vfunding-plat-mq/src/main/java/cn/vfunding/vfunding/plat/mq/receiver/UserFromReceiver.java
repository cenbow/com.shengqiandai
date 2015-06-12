package cn.vfunding.vfunding.plat.mq.receiver;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.http.HttpRequester;
import cn.vfunding.vfunding.biz.message.dao.GiftboxMessageMapper;
import cn.vfunding.vfunding.biz.message.dao.SystemMessageMapper;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;
import cn.vfunding.vfunding.biz.thirdplat.model.JjCard;
import cn.vfunding.vfunding.biz.thirdplat.service.IJjCardService;
import cn.vfunding.vfunding.biz.user.dao.UserFromDictionaryMapper;
import cn.vfunding.vfunding.biz.user.dao.UserFromMapper;
import cn.vfunding.vfunding.biz.user.model.UserFrom;
import cn.vfunding.vfunding.biz.user.model.UserFromDictionary;
import cn.vfunding.vfunding.common.module.activemq.message.UserFromMessage;

/**
 * 用户来源
 * 
 * @author lilei
 * 
 */
@Component
public class UserFromReceiver extends MessageReceiver {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserFromMapper userFromMapper;

	@Autowired
	private UserFromDictionaryMapper userFromDictionaryMapper;

	@Autowired
	private SystemMessageMapper systemMessageMapper;
	@Autowired
	private GiftboxMessageMapper giftboxMessageMapper;
	@Autowired
	private IJjCardService jjCardService;

	@Override
	public void receive(Object message) {
		UserFromMessage ms = (UserFromMessage) message;
		logger.info("执行用户来源MQ");
		this.insertUserFrom(ms);
	}

	public void insertUserFrom(UserFromMessage userFromMs) {
		// 用户来源
		String referUrl = userFromMs.getReferUrl();
		UserFrom userFrom = new UserFrom();
		userFrom.setUserId(userFromMs.getUserId());
		userFrom.setAddtime(new Date());
		userFrom.setFromUrl(referUrl);
		if (userFromMs.getThirdSource() != null
				&& userFromMs.getThirdSource().equals("1")) {// 来帮我
			String url = "http://www.bangwoya.com/callback/callback.php?oid=30000031&sn="
					+ userFromMs.getThirdSn()
					+ "&uid="
					+ userFromMs.getUserId();
			HttpRequester httpRequest = new HttpRequester();
			try {
				httpRequest.sendPost(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userFrom.setFromUrl(userFromMs.getThirdSn());
			userFrom.setFromName("来帮我");
		} else if (userFromMs.getThirdSource() != null
				&& userFromMs.getThirdSource().equals("2")) {// JJ斗地主
			SystemMessage systemMessage = new SystemMessage();
			systemMessage.setIsLook(0);
			systemMessage.setType(0);
			systemMessage.setReceiveUser(userFromMs.getUserId());
			systemMessage.setAddtime(new Date());
			JjCard jc = jjCardService.selectByNoSend(JjCard.DAY_RACE_UNSEND);
			if (jc == null) {
				systemMessage.setTitle("微积金&amp;JJ斗地主大赛参赛码");
				systemMessage.setContent("恭喜您，注册成功!斗地主大赛参赛吗已发放完毕.");
			} else {
				systemMessage.setTitle("微积金&amp;JJ斗地主大赛参赛码");
				systemMessage
						.setContent("恭喜您，注册成功!您的微积金斗地主大赛参赛码：[<font color='red'>"
								+ jc.getCardNo()
								+ "</font>]。您的5元现金红包已发放至您的微积金账户，请登录微积金“我的账户”－“礼品盒”中查收。");
			}
			int i = this.systemMessageMapper.insertSelective(systemMessage);
			GiftboxMessage gm = new GiftboxMessage();
			gm.setSendUser(1);
			gm.setAddtime(new Date());
			gm.setTitle("恭喜您获得微积金&JJ斗地主大赛5元现金红包");
			gm.setContent("感谢您参加微积金斗地主比赛，恭喜您获得由微积金提供的5元现金红包,您可在微积金平台投资或者提现使用.生效后点击“使用”即可充值到您的现金账户。微积金理财年化10-20%收益,本息保障,详询4009919999.关注官方微信：vfunding获取最新活动信息。");
			gm.setIsLook(0);
			gm.setTakeTime(new Date());
			gm.setLoseTime(DateUtil.getNextDayTime(new Date(), 30));
			gm.setMoney(new BigDecimal(5));
			gm.setReceiveUser(userFromMs.getUserId());
			gm.setStatus(0);
			gm.setType(0);
			giftboxMessageMapper.insertSelective(gm);
			if (i > 0) {
				if (jc != null) {
					jc.setSendTime(new Date());
					jc.setStatus(1);
					jjCardService.updateByPrimaryKeySelective(jc);
				}
			}
			userFrom.setFromName("JJ斗地主");
		} else {
			List<UserFromDictionary> listUserFrom = this.userFromDictionaryMapper
					.selectAllFromDictionary();
			userFrom.setFromName("自然");
			for (UserFromDictionary ufd : listUserFrom) {
				if (referUrl.contains(ufd.getFromKey())) {
					userFrom.setFromName(ufd.getFromName());
					break;
				}
			}
		}
		this.userFromMapper.insertSelective(userFrom);
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.UserFromMessage");
	}

}
