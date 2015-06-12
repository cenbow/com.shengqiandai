package cn.vfunding.vfunding.plat.cron;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.cron.service.IBorrowCronService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring.xml" })
public class BorrowTest {

	@Autowired
	private IBorrowCronService borrowCronService;

	@Autowired
	private IBorrowService borrowService;
	
	@Test
	public void test(){
		//borrowCronService.flowBorrow();
		System.out.println("流标结束");
		//borrowService.updateFlowBorrow();//流标
	}

}
