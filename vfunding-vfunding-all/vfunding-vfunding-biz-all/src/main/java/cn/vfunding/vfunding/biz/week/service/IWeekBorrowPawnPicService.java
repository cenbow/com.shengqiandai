package cn.vfunding.vfunding.biz.week.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawn;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawnPic;

public interface IWeekBorrowPawnPicService {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(WeekBorrowPawnPic record);

	WeekBorrowPawnPic selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeekBorrowPawnPic record);

	List<WeekBorrowPawnPic> selectPicByPawnId(Integer weekId);
	
	int saveWeekBorrowPawnPic(HttpSession httpSession,WeekBorrowPawn weekBorrowPawn); 
	
	List<WeekBorrowPawnPic> selectPicListByEntity(WeekBorrowPawnPic entity);

}