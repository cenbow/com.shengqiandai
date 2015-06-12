package cn.vfunding.vfunding.biz.utilweixin.dao;

import cn.vfunding.vfunding.biz.utilweixin.model.WeixinToken;

public interface WeixinTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeixinToken record);

    int insertSelective(WeixinToken record);

    WeixinToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeixinToken record);

    int updateByPrimaryKey(WeixinToken record);
}