package cn.vfunding.vfunding.biz.newyears.dao;

import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyearLog;

public interface ActivityNewyearLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityNewyearLog record);

    int insertSelective(ActivityNewyearLog record);

    ActivityNewyearLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityNewyearLog record);

    int updateByPrimaryKey(ActivityNewyearLog record);
}