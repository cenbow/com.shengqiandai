package cn.vfunding.vfunding.biz.borrow.service.impl;

import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.borrow.service.ITestLockService;
@Service("testLockService")
public class TestLockServiceImpl implements ITestLockService {

	@Override
	public void testLock() throws Exception {
		System.out.println("testLockService");
		Thread.sleep(30000);
	}

}
