package org.vfunding.vfunding.biz.sina.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class BorrowTenderTest {
	@Autowired
	private IHostingCollectTradeSinaService borrowTenderSinaService;
	/**
	 * 
	 */
//	@Test
//	public void bowTen(){
//		UserTenderActionResultVO vo = new UserTenderActionResultVO();
//		vo.setRealPayMoney(new BigDecimal(500));
//		vo.setTenderId(105);
//		vo.setUserId(904);
//		vo.setUserip("192.168.3.2");
//		try {
//			String aaa = borrowTenderSinaService.borrowTenderSinaSend(vo,"123");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
