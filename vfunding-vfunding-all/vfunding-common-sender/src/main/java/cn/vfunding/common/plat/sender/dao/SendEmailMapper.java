package cn.vfunding.common.plat.sender.dao;

import cn.vfunding.common.plat.sender.model.SendEmail;

public interface SendEmailMapper {
    int deleteByPrimaryKey(String id);

    int insert(SendEmail record);

    int insertSelective(SendEmail record);

    SendEmail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SendEmail record);

    int updateByPrimaryKey(SendEmail record);
}