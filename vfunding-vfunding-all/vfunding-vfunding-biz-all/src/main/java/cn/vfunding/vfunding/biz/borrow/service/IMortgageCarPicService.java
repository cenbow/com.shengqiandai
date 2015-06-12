package cn.vfunding.vfunding.biz.borrow.service;

import java.util.List;

import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;

public interface IMortgageCarPicService {
	int deleteByPrimaryKey(Integer id);

	int insert(MortgageCarPic record);

	int insertSelective(MortgageCarPic record);

	MortgageCarPic selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MortgageCarPic record);

	int updateByPrimaryKey(MortgageCarPic record);

	List<MortgageCarPic> selectBorrowPic(MortgageCarPic pic);
	
	List<MortgageCarPic> selectBorrowPicList(MortgageCarPic pic);
}
