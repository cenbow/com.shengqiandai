package cn.vfunding.vfunding.biz.borrow.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;

public interface MortgageCarMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MortgageCar record);

    int insertSelective(MortgageCar record);

    MortgageCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MortgageCar record);

    int updateByPrimaryKey(MortgageCar record);
    //查询抵押物信息列表
    List<MortgageCar> selectMortgageCarListPage(PageSearch search);
    //借款客户信息            
    List<Borrow> selectAddBorrowpageListPage(PageSearch search);
}