package cn.vfunding.vfunding.biz.week.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.week.dao.WeekBorrowPawnMapper;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawn;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowPawnService;

@Service("weekBorrowPawnService")
public class WeekBorrowPawnServiceImpl implements IWeekBorrowPawnService {

	@Autowired
	private WeekBorrowPawnMapper weekBorrowPawnMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.weekBorrowPawnMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(WeekBorrowPawn record) {
		return this.weekBorrowPawnMapper.insertSelective(record);
	}

	@Override
	public WeekBorrowPawn selectByPrimaryKey(Integer id) {
		return this.weekBorrowPawnMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeekBorrowPawn record) {
		return this.weekBorrowPawnMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public WeekBorrowPawn selectByBorrowId(Integer borrowId) {
		return this.weekBorrowPawnMapper.selectByBorrowId(borrowId);
	}

	@Override
	public int saveWeekBorrowPawn(WeekBorrow weekBorrow,
			WeekBorrowPawn weekBorrowPawn) {
		if(EmptyUtil.isEmpty(weekBorrowPawn.getId())){
			//新增weekBorrowPawn
			weekBorrowPawn.setAddTime(new Date());
			weekBorrowPawn.setBorrowId(weekBorrow.getId());
			return this.insertSelective(weekBorrowPawn);
		}else{
			//更新weekBorrowPawn
			return this.updateByPrimaryKeySelective(weekBorrowPawn);
		}
	}

}