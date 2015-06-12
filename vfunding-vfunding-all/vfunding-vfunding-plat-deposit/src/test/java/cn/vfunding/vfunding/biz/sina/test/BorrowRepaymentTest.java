package cn.vfunding.vfunding.biz.sina.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.vfunding.biz.sina.service.IBorrowRepaymentSinaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class BorrowRepaymentTest {

	@Autowired
	private IBorrowRepaymentSinaService borrowRepaymentSinaService;
	
	@Test
	public void test() throws Exception{
		Integer repaymentId = 1521;
		String result = this.borrowRepaymentSinaService.doUserRepayBorrower(repaymentId);
		System.out.println(result);
	}

}
