package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.JmsLog;

public interface IJmsLogService {

	int insert(JmsLog record);


    JmsLog selectByPrimaryKey(String jmsId);

    int updateByPrimaryKeySelective(JmsLog record);

    List<JmsLog> selectByLose();
    
    boolean canActionByJmsId(String id);
}
