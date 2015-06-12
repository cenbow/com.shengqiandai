package cn.vfunding.vfunding.biz.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.beanutil.AccountLogUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountRechargeMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.common.vo.AccountRechargeVO;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;
import cn.vfunding.vfunding.common.module.activemq.message.SendVerifyCodeMessage;

/**
 * 账户充值记录的Service实现类
 * 
 * @author liuyijun
 */
@Service("accountRechargeService")
public class AccountRechargeServiceImpl implements IAccountRechargeService {

	@Autowired
	private AccountRechargeMapper mapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountLogMapper accountLogMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(AccountRecharge record) {
		int result = 0;
		result = this.mapper.insert(record);
		if (record.getStatus() == 1) {
			/**
			 * 1表示充值成功，充值成功后更新账户信息并且保存账户日志信息. 如果充值不成功，只记录充值记录信息，不更新账户信息
			 */
			this.addAccountMoney(record);
		}
		return result;
	}

	/**
	 * 线下充值 方法
	 * 
	 * @author liuhuan
	 */
	@Override
	public int insertSelectiveForOffLine(AccountRecharge record) {
		int result = 0;
		result = this.mapper.insertSelective(record);
		if (record.getStatus() == 1) {
			// Account account = this.accountMapper.selectByUserId(record
			// .getUserId());
			// if (EmptyUtil.isNotEmpty(account)) {
			// account.setUseMoney(account.getUseMoney()
			// .add(record.getMoney()));
			// this.accountMapper.updateByPrimaryKey(account);
			// }
			this.addAccountMoney(record);
		}
		return result;
	}

	@Override
	public int insertSelective(AccountRecharge record) {
		int result = 0;
		result = this.mapper.insertSelective(record);
		if (record.getStatus() == 1) {
			/**
			 * 1表示充值成功，充值成功后更新账户信息并且保存账户日志信息. 如果充值不成功，只记录充值记录信息，不更新账户信息
			 */
			this.addAccountMoney(record);
		}
		return result;
	}

	@Override
	public AccountRecharge selectByPrimaryKey(Integer id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(AccountRecharge record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(AccountRecharge record) {
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public int selectUserIsRecharge(Integer userId) {
		return this.mapper.selectUserIsRecharge(userId);
	}

	/**
	 * type 0:充值,1:提现
	 * 
	 * @param search
	 * @param type
	 * @return author LiLei 2014年5月5日
	 */
	@Override
	public List<UserRechargeCashMobile> selectByUserIdListPage(
			PageSearch search, Integer type) {
		if (type == 0) {
			return this.mapper.selectByUserIdRechargeListPage(search);
		} else if (type == 1) {
			return this.mapper.selectByUserIdCashListPage(search);
		} else {
			return null;
		}

	}

	@Override
	public int updateStatus(AccountRecharge record) {
		int result = 0;
		result = this.mapper.updateByPrimaryKeySelective(record);
		if (record.getStatus() == 1) {
			this.addAccountMoney(record);
		}
		return result;
	}

	@Override
	public AccountRecharge selectByTradeNo(String tradeNo) {
		return this.mapper.selectByTradeNo(tradeNo);
	}

	@Override
	public List<AccountRechargeVO> selectWaitApplyListPage(PageSearch search) {
		return mapper.selectWaitApplyListPage(search);
	}

	/**
	 * 审核线下充值
	 * 
	 * @author liuhuan
	 */
	@Override
	public int updateCheckRechargeOffline(AccountRecharge recharge, String ip) {
		int i = 0;
		if (recharge.getStatus() == 0) {// 审核失败
			recharge.setStatus((byte) 3);// 审核失败状态为3
			i = mapper.updateByPrimaryKeySelective(recharge);
		} else if (recharge.getStatus() == 1) {
			mapper.updateByPrimaryKeySelective(recharge);
			BigDecimal money = null;
			if (EmptyUtil.isNotEmpty(recharge.getFee())) {
				money = recharge.getMoney().subtract(recharge.getFee());// 到账金额
			} else {
				money = recharge.getMoney();
			}
			Account account = accountMapper
					.selectByUserId(recharge.getUserId());
			account.setUseMoney(account.getUseMoney().add(money));
			account.setTotal(account.getTotal().add(money));
			recharge.setRemark("线下充值，订单号:" + recharge.getTradeNo());
			recharge.setAddip(ip);
			AccountLog accountLog = AccountLogUtil
					.createLogByAccountRechargeAndAccount(recharge, account);
			accountMapper.updateByPrimaryKeySelective(account);
			accountLogMapper.insertSelective(accountLog);
			// 支付宝充值成功发短信
			if (recharge.getPayment() == 1) {
				String rechargeContent = "亲爱的微积金用户：您支付宝充值资金" + money
						+ "元已到账，请您登陆网站或APP投资，感谢您对微积金的支持，祝您生活愉快！";
				User user = userMapper.selectByPrimaryKey(recharge.getUserId());
				SendVerifyCodeMessage sendCode = new SendVerifyCodeMessage();
				sendCode.setMobile(user.getPhone());
				sendCode.setContent(rechargeContent);
				//this.jmsSender.sendAsynchronousMessage(sendCode);
				this.jmsSenderUtil.asynSendSystemJms(sendCode);
			}
			i = 1;
		} else {
			i = -1;// 重复审核
		}
		return i;
	}

	/**
	 * 根据用户id查询充值记录
	 */
	@Override
	public List<AccountRecharge> selectByIdListPage(PageSearch search) {
		return mapper.selectByIdListPage(search);
	}

	/**
	 * 根据充值记录更新用户账户信息并且创建保存账户日志信息
	 * 
	 * @param record
	 *            2014年12月26日 liuyijun
	 * @throws Exception 
	 */
	private void addAccountMoney(AccountRecharge record){
		BigDecimal money = null;
		if (EmptyUtil.isNotEmpty(record.getFee())) {
			money = record.getMoney().subtract(record.getFee());// 到账金额
		} else {
			money = record.getMoney();
		}
		Account account = this.accountMapper.selectByUserId(record.getUserId());
		if (EmptyUtil.isNotEmpty(account)) {
			account.setUseMoney(EmptyUtil.isNotEmpty(account.getUseMoney()) ? account
					.getUseMoney().add(money) : money);
			account.setTotal(EmptyUtil.isNotEmpty(account.getTotal()) ? account
					.getTotal().add(money) : money);
			this.accountMapper.updateByPrimaryKey(account);
			AccountLog accountLog = AccountLogUtil
					.createLogByAccountRechargeAndAccount(record, account);
			accountLogMapper.insertSelective(accountLog);
		}else{
			try {
				throw new RuntimeException("account表用户["+record.getUserId().toString()+"]资金数据无法搜索到,充值实际没有到账,充值流水号["+record.getTradeNo()+"]");
			} catch (RuntimeException e) {
				e.getMessage();
				e.printStackTrace();
			}finally{
				throw new RuntimeException("account表用户["+record.getUserId().toString()+"]资金数据无法搜索到,充值实际没有到账,充值流水号["+record.getTradeNo()+"]");
			}	
		}
	}

	/**
	 * 新浪回调更新状态
	 * @param tradeNo 流水号
	 * @param depositAmount 通知充值金额
	 * @param success 新浪消息标志位
	 * @return
	 * @author louchen 2015-01-21
	 * @throws Exception 
	 */
	@Override
	public synchronized int sinaReturnUpdateStatus(AccountRecharge rec,String depositAmount, Boolean success)
			throws Exception {
		int result=0;
		if(EmptyUtil.isNotEmpty(rec)){
			//新浪网银是否充值成功
			if(success){
				//等待充值状态
				if(rec.getStatus() == 4){
					BigDecimal money = new BigDecimal(depositAmount);
					//充值金额和新浪回调通知充值金额是否一致
					if(rec.getMoney().equals(money)){
						rec.setStatus((byte) 1); 
						result =  this.updateStatus(rec);
					}else{
						throw new Exception("流水号【"+rec.getTradeNo()+"】充值金额和新浪通知充值金额不一致!充值金额="+rec.getMoney()+",新浪通知充值金额="+depositAmount);
					}				
				}else{
					throw new Exception("流水号【"+rec.getTradeNo()+"】充值状态异常!充值状态="+rec.getStatus());
				}
			}else{
				//更新充值失败状态,仅在等待状态
				if(rec.getStatus() == 4){
					rec.setStatus((byte) 3); 
					result =  this.updateStatus(rec);
				}
			}
		}else{
			throw new RuntimeException("充值流水号异常找不到充值记录");
		}
		return result;
	}
	
	@Override
	public int sinaReturnUpdateStatus(String tradeNo,String depositAmount,Boolean success) throws Exception {
		AccountRecharge rec = this.selectByTradeNo(tradeNo);
		return this.sinaReturnUpdateStatus(rec, depositAmount, success);
	}
	
	/**
	 * 获取充值状态中文提示
	 * @param status
	 * @return
	 * @author louchen 2015-01-22
	 */
	public String getRechargeStatusMsg(byte status){ 
		String msg = "";
		if(status==0){
			msg = "充值待审";
		}else if(status==1){
			msg = "充值成功";
		}else if(status==4){
			msg = "充值等待";
		}else{
			msg = "充值失败";
		}
		return msg;
	}
}
