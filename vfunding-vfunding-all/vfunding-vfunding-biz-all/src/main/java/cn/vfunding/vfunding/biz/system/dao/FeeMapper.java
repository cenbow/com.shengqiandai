package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.Fee;

public interface FeeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Fee record);

    int insertSelective(Fee record);

    Fee selectByPrimaryKey(Integer id);
    
    Fee selectByTimeLimit(Integer timeLimit);

    int updateByPrimaryKeySelective(Fee record);

    int updateByPrimaryKey(Fee record);
}