package cn.vfunding.common.plat.sender.dao;

import cn.vfunding.common.plat.sender.model.SendSms;

public interface SendSmsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SendSms record);

    int insertSelective(SendSms record);

    SendSms selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SendSms record);

    int updateByPrimaryKey(SendSms record);
}