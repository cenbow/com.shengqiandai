package cn.vfunding.vfunding.biz.borrow.service;

import cn.vfunding.common.framework.lock.NeedLock;

public interface ITestLockService {
	
	@NeedLock
	void testLock() throws Exception;
}
