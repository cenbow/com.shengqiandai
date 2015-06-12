package cn.vfunding.vfunding.biz.report.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.UserActivityZan;

public interface UserActivityZanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserActivityZan record);

    int insertSelective(UserActivityZan record);

    UserActivityZan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserActivityZan record);

    int updateByPrimaryKey(UserActivityZan record);
    
    List<UserActivityZan> selectByUserNameListPage(PageSearch pageSearch);
    
    UserActivityZan selectByUserNameAndLast(String userName);
}