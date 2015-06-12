package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.AllStatistics;

public interface AllStatisticsMapper {

	AllStatistics selectAllStatistics();
	
	/**
	 * @Description:统计用户发标的相关数据 
	 * @return
	 * @author liuhuan
	 */
	AllStatistics selectBorrow(Integer userId);
	/**
	 * @Description:统计用户还款的相关数据
	 * @return
	 * @author liuhuan
	 */
	AllStatistics selectBorrowRepayment(Integer userId);
	
	/**
	 * @Description:查询用户相关数据
	 * @param userId
	 * @return
	 * @author liuhuan
	 */
	AllStatistics selectStatisticsByUserId(Integer userId);
	
	/**
	 * 查询用户待还数据
	 * @param userId
	 * @return
	 * @author liuhuan
	 */
	AllStatistics selectRepayForUser(Integer userId);
	/**
	 * 查询用户待收数据
	 * @param userId
	 * @return
	 * @author liuhuan
	 */
	AllStatistics selectColletionForUser(Integer userId);
	
	/**
	 * 用户历史待收
	 * @author liuhuan
	 */
	AllStatistics selectSumColletionUser(Integer userId);
}
