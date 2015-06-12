package cn.vfunding.vfunding.biz.activity.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.activity.model.ActivityHongbaoLog;

public interface ActivityHongbaoLogMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ActivityHongbaoLog record);

	int insertSelective(ActivityHongbaoLog record);

	ActivityHongbaoLog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ActivityHongbaoLog record);

	int updateByPrimaryKey(ActivityHongbaoLog record);

	List<ActivityHongbaoLog> selectByParameters(ActivityHongbaoLog ahbl);

}