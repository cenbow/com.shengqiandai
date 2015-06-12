package cn.vfunding.vfunding.biz.credit.dao;

import cn.vfunding.vfunding.biz.credit.model.Credit;

public interface CreditMapper {
    int insert(Credit record);

    int insertSelective(Credit record);
    
    /**
     * 根据用户ID查询用户积分
     * @param userId
     * @return
     * 2014年4月24日
     * liuyijun
     */
    Integer selectValueByUserId(Integer userId);
    
    int updataByUserId(Credit record);
    
    int deleteByPrimaryKey(Integer userId);
    
}