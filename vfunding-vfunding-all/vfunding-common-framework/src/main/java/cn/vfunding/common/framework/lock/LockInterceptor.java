package cn.vfunding.common.framework.lock;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.curator.framework.recipes.locks.InterProcessLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class LockInterceptor implements MethodInterceptor {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	LockUtil lockUtil;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object res = null;
		Method method = invocation.getMethod();
		NeedLock need = method.getAnnotation(NeedLock.class);
		if (EmptyUtil.isNotEmpty(need)) {
			InterProcessLock lock = this.lockUtil.getLock(need.value());
			if (lock.acquire(120, TimeUnit.SECONDS)) {
				try {
					res = invocation.proceed();
				} catch (Exception ex) {
					throw ex;
				} finally {
					lock.release();
				}
			} else {
				logger.error("等待锁超时！！！");
				throw new Exception("系统繁忙，请稍后再试");
			}
		} else {
			res = invocation.proceed();
		}
		return res;
	}

}
