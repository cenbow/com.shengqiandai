package cn.vfunding.vfunding.biz.borrow.dao;

import cn.vfunding.vfunding.biz.borrow.model.BorrowVouchRepayment;

public interface BorrowVouchRepaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowVouchRepayment record);

    int insertSelective(BorrowVouchRepayment record);

    BorrowVouchRepayment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BorrowVouchRepayment record);

    int updateByPrimaryKey(BorrowVouchRepayment record);
}