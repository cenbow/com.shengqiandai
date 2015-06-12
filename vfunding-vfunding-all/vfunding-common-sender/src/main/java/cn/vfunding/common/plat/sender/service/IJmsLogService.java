package cn.vfunding.common.plat.sender.service;

import java.util.List;

import cn.vfunding.common.plat.sender.model.JmsLog;

public interface IJmsLogService {

	int insert(JmsLog record);


    JmsLog selectByPrimaryKey(String jmsId);

    int updateByPrimaryKeySelective(JmsLog record);

    
   
    List<JmsLog> selectByLose();
}
