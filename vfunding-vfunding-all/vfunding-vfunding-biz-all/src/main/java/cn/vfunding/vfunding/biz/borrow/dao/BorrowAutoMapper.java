package cn.vfunding.vfunding.biz.borrow.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowAuto;

public interface BorrowAutoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BorrowAuto record);

    int insertSelective(BorrowAuto record);

    BorrowAuto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BorrowAuto record);

    int updateByPrimaryKey(BorrowAuto record);
    
    List<BorrowAuto> selectBorrowAutoUserId(Borrow borrow);

	BorrowAuto selectBorrowAutoByUserId(Integer userId);
}