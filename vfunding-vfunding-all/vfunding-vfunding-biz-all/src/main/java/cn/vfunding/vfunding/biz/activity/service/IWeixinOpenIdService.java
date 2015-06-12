package cn.vfunding.vfunding.biz.activity.service;

import cn.vfunding.vfunding.biz.activity.model.WeixinOpenId;

public interface IWeixinOpenIdService {
    int deleteByPrimaryKey(Integer id);

    int insert(WeixinOpenId record);

    int insertSelective(WeixinOpenId record);

    WeixinOpenId selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeixinOpenId record);

    int updateByPrimaryKey(WeixinOpenId record);
    
    WeixinOpenId selectByOpenid(String openid);
    
    WeixinOpenId selectByPhone(String phone);
}