package cn.vfunding.vfunding.biz.sina.syn.money;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaSendLogWithBLOBs;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISingleHostingPayTradeSinaService;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateSingleHostingPayTradeSendVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

/**
 * 代付推送所有用户可用余额
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class PushMoneyToAll {
	
	private final static String logId = "initProject|Push|";
	
	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	
	@Autowired
	private ISingleHostingPayTradeSinaService borrowVerifySinaService;
	
	/**
	 * 推送数据
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception{
		List<Account> list = accountService.selectAccountUseMoneyThanZero();
		for(Account account:list){
			if(!checkRepeat(account.getUserId())){
				CreateSingleHostingPayTradeSendVO sendVO = new CreateSingleHostingPayTradeSendVO();
				User user = this.userService.selectByPrimaryKey(account.getUserId());
				sendVO.setOut_trade_no(System.currentTimeMillis()+""); //流水号内部唯一
				sendVO.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2001);
				sendVO.setPayee_identity_id(user.getUserId().toString());//收款人
				sendVO.setPayee_identity_type(SinaMemberParmUtil.IdentityType.UID);
				if (!user.getTypeId().equals(40)) {// 收款人账户类型
					sendVO.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
				}else{
					sendVO.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
				}
				sendVO.setAmount(account.getUseMoney().toString());
				sendVO.setSummary("从中间账户代付用户可用余额");
				borrowVerifySinaService.sinaSend(sendVO, logId+account.getUserId(), "从中间账户代付用户可用余额");
			}
		}		
	}
	
	/**
	 * 是否已成功发送
	 * @return
	 * @throws Exception
	 */
	private Boolean checkRepeat(Integer userId) throws Exception {
		SinaSendLogWithBLOBs ssl = this.sinaSendLogService.selectSuccessLogByOrderNo(logId+userId);
		if(EmptyUtil.isEmpty(ssl)){
			return false;
		}else{
			return true;
		}
	}	
}
