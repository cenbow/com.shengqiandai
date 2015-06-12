package cn.vfunding.vfunding.biz.user.dao;

import cn.vfunding.vfunding.biz.user.model.UserType;

public interface UserTypeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(UserType record);

    int insertSelective(UserType record);

    UserType selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(UserType record);

    int updateByPrimaryKeyWithBLOBs(UserType record);

    int updateByPrimaryKey(UserType record);
    
    
}