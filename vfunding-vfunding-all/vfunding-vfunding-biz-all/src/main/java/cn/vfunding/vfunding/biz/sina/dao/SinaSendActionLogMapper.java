package cn.vfunding.vfunding.biz.sina.dao;

import cn.vfunding.vfunding.biz.sina.model.SinaSendActionLog;

public interface SinaSendActionLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SinaSendActionLog record);

    int insertSelective(SinaSendActionLog record);

    SinaSendActionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SinaSendActionLog record);

    int updateByPrimaryKey(SinaSendActionLog record);
}