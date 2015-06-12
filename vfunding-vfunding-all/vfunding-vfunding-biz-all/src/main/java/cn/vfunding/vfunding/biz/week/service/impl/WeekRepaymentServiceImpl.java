package cn.vfunding.vfunding.biz.week.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.week.dao.WeekMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekRepaymentMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekTenderMapper;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekRepayment;
import cn.vfunding.vfunding.biz.week.model.WeekTender;
import cn.vfunding.vfunding.biz.week.service.IWeekRepaymentService;
import cn.vfunding.vfunding.biz.week.vo.SearchWeekRepaymentVO;
import cn.vfunding.vfunding.biz.week.vo.WeekRepaymentVO;
import cn.vfunding.vfunding.common.module.activemq.message.SendVerifyCodeMessage;

@Service("weekRepaymentService")
public class WeekRepaymentServiceImpl implements IWeekRepaymentService {

	@Autowired
	private WeekMapper weekMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private WeekRepaymentMapper weekRepaymentMapper;

	@Autowired
	private WeekTenderMapper weekTenderMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private AccountLogMapper accountLogMapper;
	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WeekRepayment record) {
		return this.weekRepaymentMapper.insert(record);
	}

	@Override
	public int insertSelective(WeekRepayment record) {
		return this.weekRepaymentMapper.insertSelective(record);
	}

	@Override
	public WeekRepayment selectByPrimaryKey(Integer id) {
		return this.weekRepaymentMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeekRepayment record) {
		return this.weekRepaymentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WeekRepayment record) {
		return this.weekRepaymentMapper.updateByPrimaryKey(record);
	}

	public List<WeekRepayment> selectWeekRepaymentListPage(PageSearch pageSearch) {
		List<WeekRepayment> weekRepayments = this.weekRepaymentMapper.selectWeekRepaymentListPage(pageSearch);
		for(WeekRepayment wr : weekRepayments){
			Week w = weekMapper.selectByPrimaryKey(wr.getWeekId());
			wr.setWeekName(w.getName());
			BigDecimal amountMoney = new BigDecimal(0);
			if(w.getShareYescount() != null && w.getSharePrice() != null && w.getShareYescount() >= 0){
				amountMoney = w.getSharePrice().multiply(new BigDecimal(w.getShareYescount()));
			}
			wr.setAmountMoney(amountMoney);
			if(wr.getRepaymentUser() != null){
				wr.setRepaymentUserName(userMapper.selectByPrimaryKey(wr.getRepaymentUser()).getUsername());
			}
			if(w.getHolderUser() != null){
				wr.setHolderUserName(userMapper.selectByPrimaryKey(w.getHolderUser()).getUsername());
			}
		}
		return weekRepayments;
	}

	@Override
	public List<WeekRepaymentVO> selectWeekRepaymentGroupByWeekIdListPage(
			PageSearch pageSearch) {
		return this.weekRepaymentMapper
				.selectWeekRepaymentGroupByWeekIdListPage(pageSearch);
	}

	@Override
	public WeekRepaymentVO selectSumWeekRepayment(
			SearchWeekRepaymentVO searchWeekRepaymentVO) {
		return this.weekRepaymentMapper
				.selectSumWeekRepayment(searchWeekRepaymentVO);
	}

	@Override
	public Json updateRepayWeek(Integer weekRepaymentId, UserSession user,String ip) {//request.getRemoteAddr()
		Json j = new Json();
		WeekRepayment wr = this.weekRepaymentMapper
				.selectByPrimaryKey(weekRepaymentId);
		// 还款金额
		BigDecimal repaymentAccount = wr.getRepaymentAccount();
		Integer weekId = wr.getWeekId();
		Integer userId = user.getUserId();
		if (wr.getStatus() == 1) {
			//throw new BusinessException("8005012", "此标已还款,不需再次还款");
			j.setMsg("此标已还款,不需再次还款");
			return j;
		}
		Date repaymentTime = wr.getRepaymentTime();
		if (new Date().before(repaymentTime)) {
			//throw new BusinessException("8005012", "未到还款日期");
			j.setMsg("未到还款日期");
			return j;
		}
		// 查询还款人账户
		Account account = accountMapper.selectByUserId(userId);
		BigDecimal repaymentMoney = wr.getRepaymentAccount();
		if (account.getUseMoney().doubleValue() < repaymentMoney.doubleValue()) {
			//throw new BusinessException("8005012", "余额不足，请充值后还款");
			j.setMsg("余额不足，请充值后还款");
			return j;
		}
		// 计算weekTender所需还款的总金额
		WeekTender wt = new WeekTender();
		wt.setWeekId(weekId);
		wt.setStatus(2);
		wt.setRepaymentTime(repaymentTime);
		List<WeekTender> listTender = this.weekTenderMapper
				.selectByParameters(wt);
		BigDecimal repaySum = new BigDecimal(0);
		for (WeekTender weekTender : listTender) {
			repaySum = weekTender.getInterest()
					.add(weekTender.getPlatformFee())
					.add(weekTender.getGuaranteeFee());
		}
		if (repaySum.subtract(repaymentAccount).doubleValue() > 0.1) {
			//throw new BusinessException("8005012", "还款失败，还款金额异常");
			j.setMsg("还款失败，还款金额异常");
			return j;
		}

		Week week = this.weekMapper.selectByPrimaryKey(weekId);
		// 借款人扣款
		account.setUseMoney(account.getUseMoney().subtract(repaymentAccount));
		account.setTotal(account.getTotal().subtract(repaymentAccount));
		AccountLog accountlog = new AccountLog();
		accountlog.setUserId(userId);
		accountlog.setType("repayment");
		accountlog.setMoney(repaymentAccount);
		accountlog.setUseMoney(account.getUseMoney());
		accountlog.setNoUseMoney(account.getNoUseMoney());
		accountlog.setCollection(account.getCollection());
		accountlog.setTotal(account.getTotal());
		accountlog.setToUser(0);
		accountlog.setRemark("对[<a href='/week/weekDetail/" + weekId
				+ "' target=_blank><font color=red>" + week.getName()
				+ "</font></a>]借款标的还款");
		accountlog.setBorrowId(week.getId());
		accountlog.setAddtime(Integer.parseInt(DateUtil.getTime()));
		accountlog.setAddip(ip);
		accountMapper.updateByPrimaryKey(account);
		accountLogMapper.insertSelective(accountlog);

		// 跟新weekRepament
		wr.setStatus(1);
		wr.setRepaymentYestime(new Date());
		wr.setRepaymentUser(userId);
		this.weekRepaymentMapper.updateByPrimaryKeySelective(wr);
		// 存放tenderUserID 用于后续的发送短信
		Set<Integer> userSet = new HashSet<Integer>();
		Account tenderAccount = new Account();
		// 跟新tender
		for (WeekTender weekTender : listTender) {
			userSet.add(weekTender.getUserId());
			// 还款金额
			BigDecimal repayAccount = weekTender.getRepaymentAccount();
			// 更新用户投资记录
			weekTender.setRepaymentYesaccount(repayAccount);
			weekTender.setStatus(3);
			this.weekTenderMapper.updateByPrimaryKey(weekTender);
			tenderAccount = accountMapper
					.selectByUserId(weekTender.getUserId()); // 真实资金账户
			// 投资人回款到账
			tenderAccount.setUseMoney(tenderAccount.getUseMoney().add(
					repayAccount));
			tenderAccount.setCollection(tenderAccount.getCollection().subtract(
					repayAccount));
			// 利息再次增加???
			// tenderAccount.setTotal(tenderAccount.getTotal().add(collectionInterest));
			AccountLog accountlog1 = new AccountLog();
			accountlog1.setUserId(weekTender.getUserId());
			accountlog1.setType("collection");
			accountlog1.setMoney(repayAccount);
			accountlog1.setUseMoney(tenderAccount.getUseMoney());
			accountlog1.setNoUseMoney(tenderAccount.getNoUseMoney());
			accountlog1.setCollection(tenderAccount.getCollection());
			accountlog1.setTotal(tenderAccount.getTotal());
			accountlog1.setToUser(0);
			accountlog1.setRemark("[<a href='/week/weekDetail/" + weekId
					+ "' target=_blank><font color=red>" + week.getName()
					+ "</font></a>]入账");
			accountlog1.setBorrowId(week.getId());
			accountlog1.setAddtime(Integer.parseInt(DateUtil.getTime()));
			accountlog1.setAddip(ip);
			accountMapper.updateByPrimaryKey(tenderAccount);
			accountLogMapper.insert(accountlog1);
		}

		// 投资人发短信
		String content = "你的：" + week.getName() + "标，已如期还款，请及时查收。";
		User userBean = new User();
		for (Integer tenderUserId : userSet) {
			userBean = userMapper.selectByPrimaryKey(tenderUserId);
			if (EmptyUtil.isNotEmpty(userBean.getPhone())) {
				SendVerifyCodeMessage sendCode = new SendVerifyCodeMessage();
				sendCode.setMobile(userBean.getPhone());
				// sendCode.setMobile("13774326783");
				sendCode.setContent(content);
				//this.jmsSender.sendAsynchronousMessage(sendCode);
				this.jmsSenderUtil.asynSendSystemJms(sendCode);
			}
		}

		// 借款人发短信
		String toBorrower = "您已经成功对：" + week.getName() + "标的还款"
				+ repaymentAccount + "元。";
		userBean = userMapper.selectByPrimaryKey(userId);
		SendVerifyCodeMessage sendCode = new SendVerifyCodeMessage();
		sendCode.setMobile(userBean.getPhone());
		// sendCode.setMobile("13774326783");
		sendCode.setContent(toBorrower);
		//this.jmsSender.sendAsynchronousMessage(sendCode);
		this.jmsSenderUtil.asynSendSystemJms(sendCode);
		j.setMsg("还款成功");
		j.setSuccess(true);
		return j;
	}
}