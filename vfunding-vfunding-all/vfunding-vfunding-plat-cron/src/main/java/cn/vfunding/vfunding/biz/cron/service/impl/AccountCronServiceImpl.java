package cn.vfunding.vfunding.biz.cron.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.cron.service.IAccountCronService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

@Service("accountCronService")
public class AccountCronServiceImpl implements IAccountCronService {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Value("${leaveUnusedMoney.cron.time}")
	private String leaveUnusedMoneyTime;


	@Override
	public String leaveUnusedMoney() {
		List<Account> list = this.accountService.selectByLeaveUnused();
		for (Account account : list) {
			UserWithBLOBs cuser = this.userMapper.selectByPrimaryKey(account
					.getUserId());
			if (EmptyUtil.isNotEmpty(cuser)
					&& EmptyUtil.isNotEmpty(cuser.getPhone())) {
				/**
				 * 发送闲置资金提醒短信
				 */
				this.jmsSenderUtil.sendSms(cuser.getPhone(),
						ISendConfigUtil.SMS_LEAVEUNUSEDMONEY,cuser.getUsername());
			}
		}
		return leaveUnusedMoneyTime;
	}

}
