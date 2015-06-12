package cn.vfunding.vfunding.biz.activity.dao;

import cn.vfunding.vfunding.biz.activity.model.WeixinOpenId;

public interface WeixinOpenIdMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(WeixinOpenId record);

	int insertSelective(WeixinOpenId record);

	WeixinOpenId selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeixinOpenId record);

	int updateByPrimaryKey(WeixinOpenId record);

	WeixinOpenId selectByOpenid(String openid);
	
	WeixinOpenId selectByPhone(String phone);
}