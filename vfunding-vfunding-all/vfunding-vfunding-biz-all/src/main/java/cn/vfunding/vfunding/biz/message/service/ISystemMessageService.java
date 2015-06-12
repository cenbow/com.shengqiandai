package cn.vfunding.vfunding.biz.message.service;

import java.util.List;
import java.util.Map;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;

public interface ISystemMessageService {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemMessage record);

    int insertSelective(SystemMessage record);

    SystemMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemMessage record);

    int updateByPrimaryKey(SystemMessage record);
    
    List<SystemMessage> selectListPage(PageSearch pageSearch);
    
    Integer selectIsLookCount(SystemMessage systemMessage);
    
    boolean deleteSystemMessages(String ids);
    
    boolean deleteSystemMessages(String[] ids);
    
    Map<String, Integer> updateLookAndSelectCount(SystemMessage systemMessage);
}
