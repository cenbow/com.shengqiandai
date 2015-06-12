package cn.vfunding.vfunding.biz.activity.dao;

import java.util.ArrayList;

import cn.vfunding.vfunding.biz.activity.model.ActivityHongbao;

public interface ActivityHongbaoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ActivityHongbao record);

	int insertSelective(ActivityHongbao record);

	ActivityHongbao selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ActivityHongbao record);

	int updateByPrimaryKey(ActivityHongbao record);

	ActivityHongbao selectByPhone(String phone);

	ArrayList<ActivityHongbao> selectTopFive();
}