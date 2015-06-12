package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.JmsLog;

public interface JmsLogMapper {
    int deleteByPrimaryKey(String jmsId);

    int insert(JmsLog record);

    int insertSelective(JmsLog record);

    JmsLog selectByPrimaryKey(String jmsId);

    int updateByPrimaryKeySelective(JmsLog record);

    int updateByPrimaryKey(JmsLog record);
    
    
    List<JmsLog> selectByLose();
    
    Integer selectStatusByJmsId(String id);
}