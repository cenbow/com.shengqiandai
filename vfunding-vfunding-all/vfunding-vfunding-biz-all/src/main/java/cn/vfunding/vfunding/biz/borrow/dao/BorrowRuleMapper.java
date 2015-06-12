package cn.vfunding.vfunding.biz.borrow.dao;

import cn.vfunding.vfunding.biz.borrow.model.BorrowRule;

public interface BorrowRuleMapper {
    int deleteByPrimaryKey(Integer borrowId);

    int insert(BorrowRule record);

    int insertSelective(BorrowRule record);

    BorrowRule selectByPrimaryKey(Integer borrowId);

    int updateByPrimaryKeySelective(BorrowRule record);

    int updateByPrimaryKey(BorrowRule record);
}