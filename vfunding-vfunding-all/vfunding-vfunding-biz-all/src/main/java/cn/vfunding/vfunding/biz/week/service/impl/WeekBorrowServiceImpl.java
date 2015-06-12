package cn.vfunding.vfunding.biz.week.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.week.dao.WeekBorrowMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekMapper;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowService;

@Service("weekBorrowService")
public class WeekBorrowServiceImpl implements IWeekBorrowService {
	@Autowired
	private WeekMapper weekMapper;
	
	@Autowired
	private WeekBorrowMapper weekBorrowMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.weekBorrowMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(WeekBorrow record) {
		return this.weekBorrowMapper.insertSelective(record);
	}

	@Override
	public WeekBorrow selectByPrimaryKey(Integer id) {
		return this.weekBorrowMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeekBorrow record) {
		return weekBorrowMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<WeekBorrow> selectWeekBorrowByWeekId(Integer weekId) {
		return this.weekBorrowMapper.selectWeekBorrowByWeekId(weekId);
	}

	@Override
	public int weekBorrowSubTrial(WeekBorrow record) {
		return this.weekBorrowMapper.updateByWeekIdSelective(record);
	}

	@Override
	public List<WeekBorrow> selectWeekBorrowByWeekIdListPage(
			PageSearch pageSearch) {
		return this.weekBorrowMapper.selectWeekBorrowByWeekIdListPage(pageSearch);
	}

	@Override
	public int saveWeekBorrow(WeekBorrow weekBorrow) {
		int result;
		if (EmptyUtil.isEmpty(weekBorrow.getId())) {
			// 新增weekBorrow
			weekBorrow.setCreateUser(EmployeeSession.getEmpSessionEmpId());
			result = this.insertSelective(weekBorrow);
		} else {
			// 更新weekBorrow
			result = this.updateByPrimaryKeySelective(weekBorrow);
		}
		//更新计划实际发布金额
		Week week = this.weekMapper.selectByPrimaryKey(weekBorrow.getWeekId());
		if(EmptyUtil.isNotEmpty(week) && week.getId()>0){
			this.weekMapper.updateRealityMoneyByWeekId(weekBorrow.getWeekId());
		}
		return result;
	}

	
}