package cn.vfunding.vfunding.biz.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.system.dao.AllStatisticsMapper;
import cn.vfunding.vfunding.biz.system.model.AllStatistics;
import cn.vfunding.vfunding.biz.system.service.IAllStatisticsService;
@Service("allStatisticsService")
public class AllStatisticServicesImpl implements IAllStatisticsService {

	@Autowired
	private AllStatisticsMapper allStatisticsMapper;
	@Override
	public AllStatistics selectAllStatistics() {
		return allStatisticsMapper.selectAllStatistics();
	}
	/**
	 * @Description:统计用户发标的相关数据 
	 * @return
	 * @author liuhuan
	 */

	@Override
	public AllStatistics selectBorrow(Integer userId) {
		return allStatisticsMapper.selectBorrow(userId);
	}
	/**
	 * @Description:统计用户还款的相关数据
	 * @return
	 * @author liuhuan
	 */
	@Override
	public AllStatistics selectBorrowRepayment(Integer userId) {
		return allStatisticsMapper.selectBorrowRepayment(userId);
	}
	
	@Override
	public AllStatistics selectRepayForUser(Integer userId) {
		return allStatisticsMapper.selectRepayForUser(userId);
	}
	@Override
	public AllStatistics selectColletionForUser(Integer userId) {
		return allStatisticsMapper.selectColletionForUser(userId);
	}
	@Override
	public AllStatistics selectSumColletionUser(Integer userId) {
		return allStatisticsMapper.selectSumColletionUser(userId);
	}
}
