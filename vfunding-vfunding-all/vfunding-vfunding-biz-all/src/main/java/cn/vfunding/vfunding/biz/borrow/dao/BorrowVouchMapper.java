package cn.vfunding.vfunding.biz.borrow.dao;

import cn.vfunding.vfunding.biz.borrow.model.BorrowVouch;

public interface BorrowVouchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowVouch record);

    int insertSelective(BorrowVouch record);

    BorrowVouch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BorrowVouch record);

    int updateByPrimaryKeyWithBLOBs(BorrowVouch record);

    int updateByPrimaryKey(BorrowVouch record);
}