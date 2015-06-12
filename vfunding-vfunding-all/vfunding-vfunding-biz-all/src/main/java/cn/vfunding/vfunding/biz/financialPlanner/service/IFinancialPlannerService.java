package cn.vfunding.vfunding.biz.financialPlanner.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.financialPlanner.vo.FinancialPlannerVO;
import cn.vfunding.vfunding.biz.returns.model.InviteFees;
import cn.vfunding.vfunding.biz.returns.model.ReturnFeeData;
import cn.vfunding.vfunding.biz.user.model.NumberInvitationVO;

/**
 * 
 * @author wang.zeyan
 * @date 2015年2月28日
 * @version 1.0
 */
public interface IFinancialPlannerService {

	/**
	 * 返利排行榜 金额倒序 wang.zeyan 2015年2月28日
	 * 
	 * @return
	 */
	List<FinancialPlannerVO> returnProfitLeaderboard();
	
	/**
	 * 返利排行榜 邀请人数倒序 wang.zeyan 2015年3月2日
	 * 
	 * @return
	 */
	List<NumberInvitationVO> NumberInvitationLeaderboard();

	/**
	 * 根据id查询返利总额
	 * @param userId
	 * @return
	 * @author jianwei
	 */
	BigDecimal selectSumFeesByUserId(Integer userId);
	/**
	 * 根据id查询上月返利总额
	 * @param userId
	 * @return
	 * @author jianwei
	 */
	BigDecimal selectSumFeesLastMonthByUserId(Integer userId);
	
	 /**
     * 一级返利
     * @param userId
     * @return
     *
     * jianwei
     */
    BigDecimal selectSumOneFees(Integer userId);
    /**
     * 二级返利
     * @param userId
     * @return
     *
     * jianwei
     */
    BigDecimal selectSumTwoFees(Integer userId);
    /**
     * 三级返利
     * @param userId
     * @return
     *
     * jianwei
     */
    BigDecimal selectSumThreeFees(Integer userId);
    
    /**
     * 返利明细列表
     * @param pageSearch
     * @return
     *
     * jianwei
     */
    List<ReturnFeeData> selectRebateDatailListPage(PageSearch pageSearch);

    
    /**
     * 查询未使用返利红包总金额
     * @param userId
     * @return
     *
     * jianwei
     */
    BigDecimal selectInviteFeesByGiftboxSumMoney(Integer userId);

    
    /**
     * <p>根据 t_user_id and tenderId 查询</p> 
     * 
     * wang.zeyan 2015年3月5日
     * @param tuserId
     * @param tenderId
     * @return
     */
    InviteFees selectByTUserIdAndTenderId(Integer tuserId,Integer tenderId);

}
