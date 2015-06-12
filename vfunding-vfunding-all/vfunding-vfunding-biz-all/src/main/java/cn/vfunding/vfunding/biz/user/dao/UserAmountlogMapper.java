package cn.vfunding.vfunding.biz.user.dao;

import cn.vfunding.vfunding.biz.user.model.UserAmountlog;

public interface UserAmountlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAmountlog record);

    int insertSelective(UserAmountlog record);

    UserAmountlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAmountlog record);

    int updateByPrimaryKeyWithBLOBs(UserAmountlog record);

    int updateByPrimaryKey(UserAmountlog record);
}