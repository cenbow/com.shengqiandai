package cn.vfunding.vfunding.biz.week.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.week.dao.WeekMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekRateMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekRepaymentMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekTenderMapper;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekRate;
import cn.vfunding.vfunding.biz.week.model.WeekRepayment;
import cn.vfunding.vfunding.biz.week.model.WeekTender;
import cn.vfunding.vfunding.biz.week.service.IWeekCronService;

@Service("weekCronService")
public class WeekCronServiceImpl implements IWeekCronService {

	@Autowired
	private WeekTenderMapper weekTenderMapper;

	@Autowired
	private WeekRateMapper weekRateMapper;

	@Autowired
	private WeekRepaymentMapper weekRepaymentMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private WeekMapper weekMapper;

	@Value("${week.cron.time}")
	private String weekCronTime;

	@Override
	public String updateWeekInfo() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(DateUtil.getNextDayTime(new Date(), -1));
		// 查询用户投资记录
		List<WeekTender> userTenderList = this.weekTenderMapper
				.selectByAddTime(date);
		Account account = new Account();
		for (WeekTender weekTender : userTenderList) {
			account = this.accountMapper.selectByUserId(weekTender.getUserId());
			account.setNoUseMoney(account.getNoUseMoney().subtract(
					weekTender.getMoney()));
			account.setCollection(account.getCollection()
					.add(weekTender.getMoney()).add(weekTender.getInterest()));
			this.accountMapper.updateByPrimaryKey(account);
		}

		// 查询每天每个周盈宝的投资额
		List<WeekTender> list = this.weekTenderMapper
				.selectByDateGroupWeekId(date);
		WeekRepayment wr = null;
		WeekRate weekRate = null;
		BigDecimal sumMoney = new BigDecimal(0);
		for (WeekTender wt : list) {
			// 插入还款记录
			Integer weekId = wt.getWeekId();
			Week week = this.weekMapper.selectByPrimaryKey(weekId);
			weekRate = this.weekRateMapper.selectByPrimaryKey(weekId);
			sumMoney = wt.getMoney();
			// weekRepayment利率
			BigDecimal apr = weekRate.getWeekRate().divide(new BigDecimal(100));
			// 借款人还款列表
			BigDecimal repaymentInterest = sumMoney.multiply(apr)
					.multiply(new BigDecimal(week.getTimeLimit()))
					.divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_DOWN);

			wr = new WeekRepayment();
			wr.setWeekId(weekId);
			wr.setStatus(0);
			wr.setCapital(sumMoney);
			wr.setInterest(repaymentInterest);
			wr.setRepaymentAccount(sumMoney.add(repaymentInterest));
			wr.setRepaymentTime(DateUtil.getNextDayTime(new Date(), 7));
			wr.setAddTime(new Date());
			this.weekRepaymentMapper.insertSelective(wr);
		}
		// 更新认购状态
		this.weekTenderMapper.updateStatusByDate(date);

		return weekCronTime; 
	}
}
