package cn.vfunding.vfunding.biz.sina.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.sina.service.IAccountRechargeSinaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class AccountRechargeTest {

	@Autowired
	private IAccountRechargeSinaService accountRechargeSinaService;
		
	@Test
	public void test() throws Exception{
		AccountRecharge rec = new AccountRecharge();
		rec.setId(1002);
		rec.setUserId(20);
		rec.setMoney(new BigDecimal("100000"));
		rec.setAddip("127.0.0.1");
		//String result = this.accountRechargeSinaService.doUserRechargeByOnlineBank(rec, "TESTBANK","http://www.163.com","http://www.baidu.com");
		//System.out.println(result);
	}

}
