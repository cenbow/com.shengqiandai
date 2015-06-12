package cn.vfunding.vfunding.biz.sina.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.vfunding.biz.sina.service.ISinaBonusService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-context.xml")
public class SyncBonusTest {

	@Autowired
	ISinaBonusService sinaBonusService;
	
	@Test
	public void test(){
		Boolean result = sinaBonusService.loadCreateBaseSinaSettlementData();
		//Boolean result = sinaBonusService.loadSyncSinaSettlementData();
		//Boolean result = sinaBonusService.executeAll();
		System.out.println(result.toString());
	}
}
