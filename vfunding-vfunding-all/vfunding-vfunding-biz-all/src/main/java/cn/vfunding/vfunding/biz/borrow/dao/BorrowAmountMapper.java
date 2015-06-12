package cn.vfunding.vfunding.biz.borrow.dao;

import cn.vfunding.vfunding.biz.borrow.model.BorrowAmount;
import cn.vfunding.vfunding.biz.borrow.model.BorrowAmountWithBLOBs;

public interface BorrowAmountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowAmountWithBLOBs record);

    int insertSelective(BorrowAmountWithBLOBs record);

    BorrowAmountWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BorrowAmountWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BorrowAmountWithBLOBs record);

    int updateByPrimaryKey(BorrowAmount record);
}