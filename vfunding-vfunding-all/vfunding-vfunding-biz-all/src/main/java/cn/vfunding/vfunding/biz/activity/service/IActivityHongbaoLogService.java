package cn.vfunding.vfunding.biz.activity.service;

import cn.vfunding.vfunding.biz.activity.model.ActivityHongbaoLog;

public interface IActivityHongbaoLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityHongbaoLog record);

    int insertSelective(ActivityHongbaoLog record);

    ActivityHongbaoLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityHongbaoLog record);

    int updateByPrimaryKey(ActivityHongbaoLog record);
}