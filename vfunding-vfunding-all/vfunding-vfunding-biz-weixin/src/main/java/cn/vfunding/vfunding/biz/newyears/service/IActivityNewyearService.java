package cn.vfunding.vfunding.biz.newyears.service;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyear;

public interface IActivityNewyearService {
	int deleteByPrimaryKey(String phone);

	int insertSelective(ActivityNewyear record);

	ActivityNewyear selectByPrimaryKey(String phone);

	int updateByPrimaryKeySelective(ActivityNewyear record);

	Json insertNewyearActivity(String phone, String openId);
	
	ActivityNewyear selectByPhone(String phone);
}