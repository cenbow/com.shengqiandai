package cn.vfunding.vfunding.biz.message.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;



public interface SystemMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemMessage record);

    int insertSelective(SystemMessage record);

    SystemMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemMessage record);

    int updateByPrimaryKey(SystemMessage record);
    
    List<SystemMessage> selectListPage(PageSearch pageSearch);
    
    Integer selectIsLookCount(SystemMessage systemMessage);
    
    List<SystemMessage> selectByUserIdAndTitle(@Param("userId") Integer userId,@Param("title") String title);
    
}