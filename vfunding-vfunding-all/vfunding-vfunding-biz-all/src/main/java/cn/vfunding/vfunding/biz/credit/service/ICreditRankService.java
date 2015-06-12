package cn.vfunding.vfunding.biz.credit.service;

import cn.vfunding.vfunding.biz.credit.model.CreditRank;

public interface ICreditRankService {

	int deleteByPrimaryKey(Integer id);

    int insert(CreditRank record);

    int insertSelective(CreditRank record);

    CreditRank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CreditRank record);

    int updateByPrimaryKey(CreditRank record);
    
    /**
     * 根据积分查询排名信息
     * @param value
     * @return
     * 2014年4月24日
     * liuyijun
     */
    CreditRank selectByCreditValue(Integer value);
}
