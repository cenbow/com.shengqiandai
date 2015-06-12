package cn.vfunding.vfunding.biz.returns.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.vfunding.biz.financialPlanner.vo.FinancialPlannerVO;
import cn.vfunding.vfunding.biz.returns.model.InviteFees;

public interface InviteFeesMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(InviteFees record);

	int insertSelective(InviteFees record);

	InviteFees selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(InviteFees record);

	int updateByPrimaryKey(InviteFees record);

	InviteFees selectByCollectionId(Integer collectionId);

	/**
	 * 总返利
	 * 
	 * @param userId
	 * @return
	 *
	 *         jianwei
	 */
	BigDecimal selectSumFeesByUserId(Integer userId);

	/**
	 * 上月返利
	 * 
	 * @param userId
	 * @return
	 *
	 *         jianwei
	 */
	BigDecimal selectSumFeesLastMonthByUserId(Integer userId);

	/**
	 * 一级返利
	 * 
	 * @param userId
	 * @return
	 *
	 *         jianwei
	 */
	BigDecimal selectSumOneFees(Integer userId);

	/**
	 * 二级返利
	 * 
	 * @param userId
	 * @return
	 *
	 *         jianwei
	 */
	BigDecimal selectSumTwoFees(Integer userId);

	/**
	 * 三级返利
	 * 
	 * @param userId
	 * @return
	 *
	 *         jianwei
	 */
	BigDecimal selectSumThreeFees(Integer userId);

	/**
	 * 根据UserId分组查询 总返利 
	 * @author wang.zeyan 2015年2月28日
	 * @return
	 */
	List<FinancialPlannerVO> selectSumReturnProfitGroupByUserId();

	/**
	 * <p>根据 t_user_id and tenderId 查询</p> 
	 * 
	 * @author wang.zeyan 2015年3月5日
	 * 
	 * @param tuserId
	 * @param tenderId
	 * @return
	 */
	InviteFees selectByTUserIdAndTenderId(@Param("tuserId") Integer tuserId,
			@Param("tenderId")Integer tenderId);
}