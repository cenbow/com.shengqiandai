package cn.vfunding.vfunding.biz.borrow.dao;

import cn.vfunding.vfunding.biz.borrow.model.BorrowLine;

public interface BorrowLineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowLine record);

    int insertSelective(BorrowLine record);

    BorrowLine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BorrowLine record);

    int updateByPrimaryKeyWithBLOBs(BorrowLine record);

    int updateByPrimaryKey(BorrowLine record);
}