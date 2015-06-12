package cn.vfunding.vfunding.biz.prize.dao;

import cn.vfunding.vfunding.biz.prize.model.PrizeChoose;

public interface PrizeChooseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrizeChoose record);

    int insertSelective(PrizeChoose record);

    PrizeChoose selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrizeChoose record);

    int updateByPrimaryKey(PrizeChoose record);
}