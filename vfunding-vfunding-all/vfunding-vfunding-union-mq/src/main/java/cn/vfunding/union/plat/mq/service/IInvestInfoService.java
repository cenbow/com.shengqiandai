package cn.vfunding.union.plat.mq.service;

import cn.vfunding.union.plat.mq.model.InvestInfo;
import cn.vfunding.vfunding.common.module.activemq.message.InvestMessage;

public interface IInvestInfoService {

	int deleteByPrimaryKey(String id);

    int insert(InvestInfo record);

    int insertSelective(InvestInfo record);
    /**
     * 根据mq消息对象创建并保存投资信息对象
     * @param message
     * @return
     */
    int insertByInvestMessage(InvestMessage message);

    InvestInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InvestInfo record);

    int updateByPrimaryKey(InvestInfo record);
}
