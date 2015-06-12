package cn.vfunding.vfunding.biz.activity.service;

import java.util.ArrayList;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.activity.model.ActivityHongbao;

public interface IActivityHongbaoService {

	Json insertActivityHongbao(String phone, Integer share_phone, String openId);

	boolean selectActivityHongbao(String phone);

	int deleteByPrimaryKey(Integer id);

	int insert(ActivityHongbao record);

	int insertSelective(ActivityHongbao record);

	ActivityHongbao selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ActivityHongbao record);

	int updateByPrimaryKey(ActivityHongbao record);

	void updateUserAccount(Integer userId, String phone);

	ArrayList<ActivityHongbao> selectTopFive();
	
	ActivityHongbao selectByPhone(String phone);
}