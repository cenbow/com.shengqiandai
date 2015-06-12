package cn.vfunding.vfunding.biz.borrow.dao;

import cn.vfunding.vfunding.biz.borrow.model.BorrowAmountlog;

public interface BorrowAmountlogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowAmountlog record);

    int insertSelective(BorrowAmountlog record);

    BorrowAmountlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BorrowAmountlog record);

    int updateByPrimaryKey(BorrowAmountlog record);
}