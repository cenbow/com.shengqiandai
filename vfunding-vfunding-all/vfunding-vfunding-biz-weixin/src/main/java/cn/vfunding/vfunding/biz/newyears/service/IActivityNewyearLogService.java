package cn.vfunding.vfunding.biz.newyears.service;

import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyearLog;

public interface IActivityNewyearLogService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(ActivityNewyearLog record);

    ActivityNewyearLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityNewyearLog record);

}