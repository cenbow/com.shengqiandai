package cn.p2p.p2p.biz.current.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.current.dao.CurrentAccountRuleMapper;
import cn.p2p.p2p.biz.current.dao.CurrentMapper;
import cn.p2p.p2p.biz.current.dao.CurrentRuleMapper;
import cn.p2p.p2p.biz.current.dao.CurrentTenderMapper;
import cn.p2p.p2p.biz.current.dao.CurrentUserAccountMapper;
import cn.p2p.p2p.biz.current.model.Current;
import cn.p2p.p2p.biz.current.model.CurrentAccountRule;
import cn.p2p.p2p.biz.current.model.CurrentRule;
import cn.p2p.p2p.biz.current.model.CurrentTender;
import cn.p2p.p2p.biz.current.model.CurrentUserAccount;
import cn.p2p.p2p.biz.current.service.ICurrentTenderService;
import cn.p2p.p2p.biz.current.vo.UserAccountActionResultVO;
import cn.p2p.p2p.biz.current.vo.UserCurrentAccountVO;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;

@Service
public class CurrentTenderServiceImpl implements ICurrentTenderService {

	@Autowired
	private CurrentMapper currentMapper;

	@Autowired
	private CurrentRuleMapper currentRuleMapper;

	@Autowired
	private CurrentUserAccountMapper currentUserAccountMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private CurrentAccountRuleMapper currentAccountRuleMapper;

	@Autowired
	private CurrentTenderMapper currentTenderMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(CurrentTender record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CurrentTender selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(CurrentTender record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public synchronized MobileBaseResponse UserCurrentTender(UserCurrentAccountVO vo) {
		// TODO Auto-generated method stub
		MobileBaseResponse response = new MobileBaseResponse();
		UserAccountActionResultVO result = new UserAccountActionResultVO();
		result.setStatus(1);
		Integer userId = vo.getUserId();
		Integer currentId = vo.getCurrentId();
		BigDecimal money = vo.getMoney();
		if (money.doubleValue() <= 0) {
			return new MobileBaseResponse("current_fail", "金额有误");
		}
		// 查询活期宝的信息
		Current current = this.currentMapper.selectByPrimaryKey(currentId);
		CurrentRule currentRule = this.currentRuleMapper.selectByPrimaryKey(currentId);
		BigDecimal currentMoney = current.getSumMoney().subtract(current.getBuyMoney());
		if (current.getStatus() != 1) {
			return new MobileBaseResponse("current_fail", "此标不可投");
		}
		if (currentMoney.doubleValue() <= 0) {
			return new MobileBaseResponse("current_fail", "活期宝已满");
		}
		if (money.doubleValue() < currentRule.getLowestAccount().doubleValue()) {
			return new MobileBaseResponse("current_fail", "投资金额少于单标最低投资额");
		}
		if (money.doubleValue() > currentRule.getMostAccount().doubleValue()) {
			return new MobileBaseResponse("current_fail", "投资金额大于单标最大投资额");
		}
		// 查询用户账户情况
		Account account = this.accountMapper.selectByUserId(userId);
		if (account.getUseMoney().doubleValue() < money.doubleValue()) {
			return new MobileBaseResponse("current_fail", "可用余额不足");
		}
		BigDecimal tenderMoney = money;
		result.setPayMoney(money);
		if (currentMoney.doubleValue() < tenderMoney.doubleValue()) {
			// 部分投资
			tenderMoney = currentMoney;
			result.setStatus(2);
		}
		result.setRealPayMoney(tenderMoney);
		// 查询活期宝的用户账户
		CurrentUserAccount cua = this.currentUserAccountMapper.selectByPrimaryKey(userId);
		// 查询活期账户规则
		CurrentAccountRule accountRule = this.currentAccountRuleMapper.selectByPrimaryKey(userId);

		// 超过活期宝的单人限额
		if (accountRule.getMostHoldMoney().doubleValue() > 0 && cua.getTotal().subtract(cua.getUseInterest()).add(tenderMoney).doubleValue() > accountRule.getMostHoldMoney().doubleValue()) {
			return new MobileBaseResponse("current_fail", "超过活期宝的单人最大限额");
		}

		// 扣除用户账户的余额
		account.setTotal(account.getTotal().subtract(tenderMoney));
		account.setUseMoney(account.getUseMoney().subtract(tenderMoney));
		accountMapper.updateByPrimaryKey(account);

		// 为活期宝账户添加等待金额
		cua.setTotal(cua.getTotal().add(tenderMoney));
		cua.setNoUseMoney(cua.getNoUseMoney().add(tenderMoney));
		this.currentUserAccountMapper.updateByPrimaryKeySelective(cua);

		// 更新活期宝信息
		current.setBuyMoney(current.getBuyMoney().add(tenderMoney));
		current.setTenderCount(current.getTenderCount() + 1);
		if (current.getBuyMoney().doubleValue() == current.getSumMoney().doubleValue()) {
			current.setSoldoutTime(new Date());
		}
		this.currentMapper.updateByPrimaryKeySelective(current);
		// 添加投资记录
		CurrentTender ct = new CurrentTender();
		ct.setUserId(userId);
		ct.setCurrentId(currentId);
		ct.setMoney(tenderMoney);
		ct.setAddip(vo.getUserip());
		ct.setAddTime(new Date());
		this.currentTenderMapper.insertSelective(ct);
		result.setTenderId(ct.getId());
		response.setResponseObject(result);
		return response;
	}

}