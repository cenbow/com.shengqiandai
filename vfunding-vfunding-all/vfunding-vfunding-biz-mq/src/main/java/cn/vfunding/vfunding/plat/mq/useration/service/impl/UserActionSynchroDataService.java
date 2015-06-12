package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import java.net.ConnectException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.apache.http.conn.HttpHostConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.redislog.model.AgainLog;
import cn.vfunding.vfunding.biz.redislog.service.IAgainLogService;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;
import cn.vfunding.vfunding.biz.thirdplat.service.IThirdRelationshipService;

import com.alibaba.fastjson.JSON;

/**
 * 用户动作后同步数据处理服务类
 * 
 * @author liuyijun
 *
 */
@Component
public class UserActionSynchroDataService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	@Qualifier("cjdRestInvokerFactory")
	private RestInvokerFactory cjdRestInvokerFactory;
	@Autowired
	@Qualifier("activityRestInvokerFactory")
	private RestInvokerFactory activityRestInvokerFactory;

	@Autowired
	@Qualifier("sinaRestInvokerFactory")
	private RestInvokerFactory sinaRestInvokerFactory;

	@Autowired
	private IAgainLogService logService;

	@Autowired
	private IThirdRelationshipService thirdRelationshipService;

	/**
	 * 用户动作后相关数据同步及活动处理
	 * 
	 * @param obj
	 * @param url
	 * @param actionName
	 *            2015年1月15日 liuyijun
	 */
	public void doSynchroData(Object obj, String url, String actionName, String category, Integer userId) {
		String serviceUrl = "";
		logger.info("*****[" + category + " " + userId + " " + actionName + " 请求开始]");
		try {

			if (category.equals("sina")) {
				serviceUrl = this.sinaRestInvokerFactory.getBaseURL() + url;
				logger.info("*****[" + category + " " + userId + " " + actionName + " 发送请求]");
				this.sinaRestInvokerFactory.getRestInvoker().post(url, obj);
				logger.info("*****[" + category + " " + userId + " " + actionName + " 发送请求完成]");
			} else if (category.equals("cjd")) {
				serviceUrl = this.cjdRestInvokerFactory.getBaseURL() + url;
				if (actionName.equals("borrowExamine") || actionName.equals("borrowVerify") || actionName.equals("weekExamine") || actionName.equals("pepayBorrow")) {
					this.cjdRestInvokerFactory.getRestInvoker().post(url, obj);
				} else {
					ThirdRelationship ship = this.thirdRelationshipService.selectByUserIdType(userId, 1);
					if (this.isCjdUserCheck(ship)) {
						this.cjdRestInvokerFactory.getRestInvoker().post(url, obj);
					} else {
						// 不是财经道用户，但如果是【投资】动作也更新财经道标的信息，剩余可投金额等
						if (actionName.equals("borrowTender")) {
							url = "/cjdUserAction/borrowSync";
							this.cjdRestInvokerFactory.getRestInvoker().post(url, obj);
						}
					}
				}
			} else if (category.equals("activity")) {
				serviceUrl = this.activityRestInvokerFactory.getBaseURL() + url;
				this.activityRestInvokerFactory.getRestInvoker().post(url, obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			logger.error("#####[" + category + " " + userId + " " + actionName + " 错误返回信息]:[" + e.getMessage() + "]");
			logger.error("#####[" + category + " " + userId + " " + actionName + " 请求参数]:[" + url + "][" + JSON.toJSONString(obj) + "]");
			if (e instanceof TimeoutException || e instanceof ConnectException || e instanceof HttpHostConnectException) {
				this.doErrorLog(serviceUrl, actionName, JSON.toJSONString(obj), category);
			}
		}
		logger.info("*****[" + category + " " + userId + " " + actionName + " 请求结束]");
	}

	/**
	 * 用户动作后同步第三方数据出错是记录补发信息
	 * 
	 * @param url
	 *            调用的服务的完整URL信息
	 * @param actionName动作名称
	 * @param arg
	 *            字符串式参数信息 2015年1月15日 liuyijun
	 */
	private void doErrorLog(String url, String actionName, String arg, String category) {
		AgainLog log = new AgainLog(actionName, category, arg, url);
		try {
			this.logService.insertNewLog(log);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.info("[system 添加补单记录]");
	}

	/**
	 * 检验是否是财经道用户
	 * 
	 * @param ship
	 * @return 2014年12月31日 liuyijun
	 */
	private boolean isCjdUserCheck(ThirdRelationship ship) {
		if (EmptyUtil.isNotEmpty(ship)) {
			Calendar cal = Calendar.getInstance();
			if (EmptyUtil.isEmpty(ship.getUserTracktime())) {
				cal.setTime(new Date());
			} else {
				cal.setTime(ship.getUserTracktime());
			}
			cal.add(Calendar.HOUR, 24);
			if (ship.getUserType() == 0 || (ship.getUserType() == 1 && cal.getTime().after(new Date()))) {
				return true;
			}
		}
		return false;
	}

}
