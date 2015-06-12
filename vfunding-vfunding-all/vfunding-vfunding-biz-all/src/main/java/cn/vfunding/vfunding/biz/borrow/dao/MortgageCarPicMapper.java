package cn.vfunding.vfunding.biz.borrow.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;

public interface MortgageCarPicMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(MortgageCarPic record);

	int insertSelective(MortgageCarPic record);

	MortgageCarPic selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(MortgageCarPic record);

	int updateByPrimaryKey(MortgageCarPic record);

	List<MortgageCarPic> selectBorrowPic(MortgageCarPic pic);
	
	/**
	 * @Description:多参数查询
	 * @author liuhuan
	 */
	List<MortgageCarPic> selectBorrowPicList(MortgageCarPic pic);
}