package cn.vfunding.vfunding.biz.week.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawnPic;

public interface WeekBorrowPawnPicMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(WeekBorrowPawnPic record);

	int insertSelective(WeekBorrowPawnPic record);

	WeekBorrowPawnPic selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeekBorrowPawnPic record);

	int updateByPrimaryKey(WeekBorrowPawnPic record);

	List<WeekBorrowPawnPic> selectByPawnId(Integer id);

	List<WeekBorrowPawnPic> selectPicByPawnId(Integer pawnId);
	
	List<WeekBorrowPawnPic> selectPicListByEntity(WeekBorrowPawnPic entity);
}