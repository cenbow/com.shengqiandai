package cn.vfunding.vfunding.biz.borrow.dao;

import cn.vfunding.vfunding.biz.borrow.model.BorrowVouchCollection;

public interface BorrowVouchCollectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowVouchCollection record);

    int insertSelective(BorrowVouchCollection record);

    BorrowVouchCollection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BorrowVouchCollection record);

    int updateByPrimaryKey(BorrowVouchCollection record);
}