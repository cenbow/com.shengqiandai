package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

import com.alibaba.fastjson.JSON;

/**
 * 用户成功投资后相关业务处理服务类
 * 
 * @author liuyijun
 * 
 */
@Component
public class SinaTakeCashActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");
	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IJmsLogService mapper;

	@Async
	public void doSinaTakeCash(AfterActionMessage msg) {
			try {
				String result = msg.getMethodReturn().toString();
				AccountCash accountCash = JSON.parseObject(
						msg.getMethodArgs()[0].toString(), AccountCash.class);

				this.doSinaTakeCashBiz(accountCash, result);
				JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
				logger.info("*****[system  新浪提现MQ消息处理成功]");
			} catch (Exception e) {
				logger.error(e.getMessage());
				logger.error(e.getStackTrace().toString());
				logger.error(e.getMessage());
				JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
			}
	}

	/**
	 * 用户提现成功后第三方平台数据同步
	 * 
	 * @param cash
	 *            提现记录对象 2014年12月31日 liuyijun
	 */
	private void doSinaTakeCashBiz(AccountCash accountCash, String result) {
		UserWithBLOBs user = this.userMapper.selectByPrimaryKey(accountCash
				.getUserId());
		if (EmptyUtil.isNotEmpty(user) && EmptyUtil.isNotEmpty(user.getPhone())) {
			if (result.equals("success")) {
				DecimalFormat df = new DecimalFormat("#.00");
				/**
				 * 发送提现成功短信
				 */
				this.jmsSenderUtil.sendSms(user.getPhone(),
						ISendConfigUtil.SMS_TAKECASH_SINA_SUCCESS,
						user.getUsername(),
						df.format(accountCash.getCredited()));
			} else {
				/**
				 * 发送提现失败短信
				 */
				this.jmsSenderUtil.sendSms(user.getPhone(),
						ISendConfigUtil.SMS_TAKECASH_FAILD, user.getUsername());
			}
		}
	}

}
