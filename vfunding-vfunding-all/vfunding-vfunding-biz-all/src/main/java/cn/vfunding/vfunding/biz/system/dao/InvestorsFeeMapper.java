package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.InvestorsFee;

public interface InvestorsFeeMapper {

	int insert(InvestorsFee record);
	
	InvestorsFee selectByBorrowId(Integer borrowId);
	
    int insertSelective(InvestorsFee record);

    InvestorsFee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InvestorsFee record);

    int updateByPrimaryKey(InvestorsFee record);
}
