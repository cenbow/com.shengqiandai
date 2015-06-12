package cn.p2p.p2p.biz.current.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.current.dao.CurrentRedeemMapper;
import cn.p2p.p2p.biz.current.dao.CurrentRedeemRuleMapper;
import cn.p2p.p2p.biz.current.dao.CurrentUserAccountMapper;
import cn.p2p.p2p.biz.current.model.CurrentRedeem;
import cn.p2p.p2p.biz.current.model.CurrentRedeemRule;
import cn.p2p.p2p.biz.current.model.CurrentUserAccount;
import cn.p2p.p2p.biz.current.service.ICurrentRedeemService;
import cn.p2p.p2p.biz.current.vo.UserAccountActionResultVO;
import cn.p2p.p2p.biz.current.vo.UserCurrentAccountVO;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;

@Service
public class CurrentRedeemServiceImpl implements ICurrentRedeemService {

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(CurrentRedeem record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CurrentRedeem selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(CurrentRedeem record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Autowired
	private CurrentUserAccountMapper currentUserAccountMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private CurrentRedeemMapper currentRedeemMapper;

	@Autowired
	private CurrentRedeemRuleMapper currentRedeemRuleMapper;

	@Override
	public MobileBaseResponse userRedeem(UserCurrentAccountVO vo) {
		// TODO Auto-generated method stub
		UserAccountActionResultVO result = new UserAccountActionResultVO();
		BigDecimal zero = new BigDecimal(0);
		Integer userId = vo.getUserId();
		BigDecimal money = vo.getMoney();
		// 查询用户账户情况
		CurrentUserAccount cua = this.currentUserAccountMapper.selectByPrimaryKey(userId);
		if (EmptyUtil.isEmpty(cua)) {
			return new MobileBaseResponse("Redeem_fail","您还未开通活期宝账户");
		}
		if (cua.getUseMoney().add(cua.getNoUseMoney()).doubleValue() < money.doubleValue()) {
			return new MobileBaseResponse("Redeem_fail","活期宝账户本金不足");
		}
		CurrentRedeemRule redeemRule = this.currentRedeemRuleMapper.selectByPrimaryKey(userId);
		BigDecimal daySumMoney = this.currentRedeemMapper.selectByUserIdAndDate(userId);
		if (daySumMoney.add(money).doubleValue() > redeemRule.getDayMostAccount().doubleValue()) {
			return new MobileBaseResponse("Redeem_fail","超过单天最大提现金额");
		}
		BigDecimal noUseMoney = cua.getNoUseMoney();
		BigDecimal redeemInterest = zero;
		// 更新活期账户
		if (money.doubleValue() > noUseMoney.doubleValue()) {
			// 得到剩余本金
			BigDecimal surplusMoney = cua.getUseMoney().add(cua.getNoUseMoney()).subtract(money);
			cua.setUseMoney(surplusMoney);
			// 得到所取金额与剩余本金的比例
			BigDecimal scale = (cua.getUseMoney().subtract(surplusMoney)).divide(cua.getUseMoney(), 20, BigDecimal.ROUND_HALF_UP);
			// 得到对应的所取利息
			redeemInterest = cua.getUseInterest().multiply(scale).setScale(2, BigDecimal.ROUND_HALF_UP);
			cua.setUseInterest(cua.getUseInterest().subtract(redeemInterest));
			cua.setTotal(surplusMoney.add(cua.getUseInterest()));
			cua.setNoUseMoney(zero);
		} else {
			cua.setNoUseMoney(cua.getNoUseMoney().subtract(money));
			cua.setTotal(cua.getTotal().subtract(money));
		}
		this.currentUserAccountMapper.updateByPrimaryKeySelective(cua);

		// 得到一共赎回的本息
		BigDecimal redeemMoney = money.add(redeemInterest);
		// 更新用户账户
		Account account = this.accountMapper.selectByUserId(vo.getUserId());
		account.setTotal(account.getTotal().add(redeemMoney));
		account.setUseMoney(account.getUseMoney().add(redeemMoney));
		this.accountMapper.updateByPrimaryKeySelective(account);

		// 添加赎回记录
		CurrentRedeem cr = new CurrentRedeem();
		cr.setUserId(userId);
		cr.setMoney(money);
		cr.setInterest(redeemInterest);
		cr.setAddTime(new Date());
		cr.setAddip(vo.getUserip());
		this.currentRedeemMapper.insertSelective(cr);
		// 返回结果集
		result.setStatus(1);
		result.setPayMoney(redeemMoney);
		return new MobileBaseResponse(result);
	}

}