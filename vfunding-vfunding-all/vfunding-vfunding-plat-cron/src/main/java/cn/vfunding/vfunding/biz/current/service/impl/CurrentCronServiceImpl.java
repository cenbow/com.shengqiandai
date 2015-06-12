package cn.vfunding.vfunding.biz.current.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.current.dao.CurrentInterestLogMapper;
import cn.p2p.p2p.biz.current.dao.CurrentMapper;
import cn.p2p.p2p.biz.current.dao.CurrentUserAccountMapper;
import cn.p2p.p2p.biz.current.model.Current;
import cn.p2p.p2p.biz.current.model.CurrentInterestLog;
import cn.p2p.p2p.biz.current.model.CurrentUserAccount;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.current.service.ICurrentCronService;

@Service("currentCronService")
public class CurrentCronServiceImpl implements ICurrentCronService {

	@Autowired
	private CurrentMapper currentMapper;

	@Autowired
	private CurrentUserAccountMapper currentUserAccountMapper;
	
	@Autowired
	private CurrentInterestLogMapper currentInterestLogMapper;

	/**
	 * 更新活期宝用户账户信息
	 */
	public void updateUserAccount() {
		// TODO Auto-generated method stub
		List<CurrentUserAccount> listAcount = this.currentUserAccountMapper.selectAllCurentAccount();
		for (CurrentUserAccount userAccount : listAcount) {
			if(userAccount.getNoUseMoney().doubleValue()>0){
				CurrentUserAccount ua = new CurrentUserAccount();
				ua.setUserId(userAccount.getUserId());
				ua.setUseMoney(ua.getUseMoney().add(ua.getNoUseMoney()));
				ua.setNoUseMoney(new BigDecimal(0));
				this.currentUserAccountMapper.updateByPrimaryKeySelective(ua);
			}
		}

	}

	/**
	 * 生息
	 */
	public void insertUserProfit() {
		// TODO Auto-generated method stub
		PageSearch pageSearch = new PageSearch();
		List<Current> list = this.currentMapper.selectCurrentListPage(pageSearch);
		BigDecimal apr = new BigDecimal(0.075);
		if (list != null && list.size() > 0) {
			Current current = list.get(0);
			apr = current.getApr().divide(new BigDecimal(100));
		}

		List<CurrentUserAccount> listAcount = this.currentUserAccountMapper.selectAllCurentAccount();
		BigDecimal interest = new BigDecimal(0);
		for (CurrentUserAccount userAccount : listAcount) {
			if(userAccount.getUseMoney().doubleValue()>0 ){
				interest = apr.multiply(userAccount.getUseMoney()).divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
				CurrentUserAccount ua = new CurrentUserAccount();
				ua.setUserId(userAccount.getUserId());
				ua.setUseInterest(userAccount.getUseInterest().add(interest));
				ua.setTotal(userAccount.getTotal().add(interest));
				this.currentUserAccountMapper.updateByPrimaryKeySelective(ua);
				
				CurrentInterestLog interestLog = new CurrentInterestLog();
				interestLog.setUserId(userAccount.getUserId());
				interestLog.setInterest(interest);
				interestLog.setMoney(ua.getUseMoney());
				interestLog.setAddtime(new Date());
				this.currentInterestLogMapper.insertSelective(interestLog);
			}else{
				CurrentInterestLog interestLog = new CurrentInterestLog();
				interestLog.setUserId(userAccount.getUserId());
				interestLog.setInterest(new BigDecimal(0));
				interestLog.setMoney(new BigDecimal(0));
				interestLog.setAddtime(new Date());
				this.currentInterestLogMapper.insertSelective(interestLog);
			}
		}

	}

	@Override
	public String updateAllCurrentInfo() {
		this.insertUserProfit();
		this.updateUserAccount();
		return null;
	}

}
