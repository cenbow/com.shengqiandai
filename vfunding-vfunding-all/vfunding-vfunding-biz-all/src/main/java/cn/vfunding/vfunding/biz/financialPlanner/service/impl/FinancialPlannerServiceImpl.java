package cn.vfunding.vfunding.biz.financialPlanner.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.financialPlanner.service.IFinancialPlannerService;
import cn.vfunding.vfunding.biz.financialPlanner.vo.FinancialPlannerVO;
import cn.vfunding.vfunding.biz.message.dao.GiftboxMessageMapper;
import cn.vfunding.vfunding.biz.returns.dao.InviteFeesMapper;
import cn.vfunding.vfunding.biz.returns.dao.ReturnfeeDataMapper;
import cn.vfunding.vfunding.biz.returns.model.InviteFees;
import cn.vfunding.vfunding.biz.returns.model.ReturnFeeData;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.NumberInvitationVO;

/**
 * 
 * @author wang.zeyan
 * @date 2015年2月28日
 * @version 1.0
 */
@Service("financialPlannerService")
public class FinancialPlannerServiceImpl implements IFinancialPlannerService {
	
	@Autowired
	private InviteFeesMapper inviteFeesMapper;
	@Autowired
	private ReturnfeeDataMapper returnfeeDataMapper;
	@Autowired
	private GiftboxMessageMapper giftboxMessageMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<FinancialPlannerVO> returnProfitLeaderboard() {
		return inviteFeesMapper.selectSumReturnProfitGroupByUserId();
	}
	
	@Override
	public List<NumberInvitationVO> NumberInvitationLeaderboard() {
		return userMapper.selectNumberInvitationLeaderboard();
	}

	@Override
	public BigDecimal selectSumFeesByUserId(Integer userId) {
		return inviteFeesMapper.selectSumFeesByUserId(userId);
	}

	@Override
	public BigDecimal selectSumFeesLastMonthByUserId(Integer userId) {
		return inviteFeesMapper.selectSumFeesLastMonthByUserId(userId);
	}

	@Override
	public BigDecimal selectSumOneFees(Integer userId) {
		return inviteFeesMapper.selectSumOneFees(userId);
	}

	@Override
	public BigDecimal selectSumTwoFees(Integer userId) {
		return inviteFeesMapper.selectSumTwoFees(userId);
	}

	@Override
	public BigDecimal selectSumThreeFees(Integer userId) {
		return inviteFeesMapper.selectSumThreeFees(userId);
	}

	@Override
	public List<ReturnFeeData> selectRebateDatailListPage(PageSearch pageSearch) {

		return returnfeeDataMapper.selectRebateDatailListPage(pageSearch);
	}


	@Override
	public BigDecimal selectInviteFeesByGiftboxSumMoney(Integer userId) {
		// TODO Auto-generated method stub
		return giftboxMessageMapper.selectInviteFeesByGiftboxSumMoney(userId);
	}


	@Override
	public InviteFees selectByTUserIdAndTenderId(Integer tuserId,
			Integer tenderId) {

		return inviteFeesMapper.selectByTUserIdAndTenderId(tuserId, tenderId);
	}


	
	
	
}
