package cn.vfunding.vfunding.plat.mq.useraction.service;

import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

public interface IUserActionService {

	/**
	 * 用户动作后通知处理接口
	 * 
	 * 2015年1月5日
	 * liuyijun
	 */
	void doUserAction(AfterActionMessage msg);
}
