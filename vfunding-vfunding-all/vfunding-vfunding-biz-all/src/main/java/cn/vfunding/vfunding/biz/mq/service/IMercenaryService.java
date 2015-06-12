package cn.vfunding.vfunding.biz.mq.service;

import cn.vfunding.vfunding.common.module.activemq.message.MercenaryMessage;

public interface IMercenaryService {
	void insertMercenaryInterest(MercenaryMessage mercenaryMessage);
}
