package cn.vfunding.union.plat.mq.service;

import cn.vfunding.union.plat.mq.model.RealNameRecord;
import cn.vfunding.vfunding.common.module.activemq.message.UserRealNameMessage;

public interface IRealNameRecordService {

	int insert(RealNameRecord record);

    int insertSelective(RealNameRecord record);

    RealNameRecord selectByPrimaryKey(Integer id);
    
    RealNameRecord selectByUserId(String userId);

    int updateByPrimaryKeySelective(RealNameRecord record);

    int updateByPrimaryKey(RealNameRecord record);
    
    /**
     * 微积金用户实名认证同步网站联盟用户实名认证
     * @param message
     * @return
     * 2014年5月22日
     * liuyijun
     */
    int vfundingUserRealName(UserRealNameMessage message);
}
