package cn.vfunding.vfunding.biz.borrow.service;

import cn.vfunding.vfunding.biz.borrow.model.BorrowRule;

public interface IBorrowRuleService {
	
	int deleteByPrimaryKey(Integer borrowId);


    int insertSelective(BorrowRule record);

    BorrowRule selectByPrimaryKey(Integer borrowId);

    int updateByPrimaryKeySelective(BorrowRule record);

}
