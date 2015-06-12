package cn.vfunding.vfunding.biz.icbcCard.dao;

import cn.vfunding.vfunding.biz.icbcCard.model.IcbcCard;

public interface IcbcCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IcbcCard record);

    int insertSelective(IcbcCard record);

    IcbcCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IcbcCard record);

    int updateByPrimaryKey(IcbcCard record);
    
    IcbcCard selectByUserId(Integer userId);
    
    void addNoFeeCountOne(Integer userId);
}