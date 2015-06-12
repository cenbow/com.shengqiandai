package cn.vfunding.union.plat.mq.dao;

import cn.vfunding.union.plat.mq.model.InvestInfo;

public interface InvestInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(InvestInfo record);

    int insertSelective(InvestInfo record);

    InvestInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(InvestInfo record);

    int updateByPrimaryKey(InvestInfo record);
}