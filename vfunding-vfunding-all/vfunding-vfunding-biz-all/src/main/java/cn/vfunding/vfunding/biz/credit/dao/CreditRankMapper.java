package cn.vfunding.vfunding.biz.credit.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.credit.model.CreditRank;

public interface CreditRankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CreditRank record);

    int insertSelective(CreditRank record);

    CreditRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditRank record);

    int updateByPrimaryKey(CreditRank record);
    
    /**
     * 加载所有数据
     * @return
     * 2014年4月24日
     * liuyijun
     */
    List<CreditRank> selectAll();
}