package cn.vfunding.vfunding.biz.userFrom.dao;

import cn.vfunding.vfunding.biz.userFrom.model.UserFromDic;

public interface UserFromDicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFromDic record);

    int insertSelective(UserFromDic record);

    UserFromDic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFromDic record);

    int updateByPrimaryKey(UserFromDic record);
    
    UserFromDic selectByFromNo(String fromNo);
}