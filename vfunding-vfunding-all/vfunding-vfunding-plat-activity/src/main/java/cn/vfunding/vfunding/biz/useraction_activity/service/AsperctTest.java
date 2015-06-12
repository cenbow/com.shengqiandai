package cn.vfunding.vfunding.biz.useraction_activity.service;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import cn.vfunding.vfunding.biz.user.model.User;

@Aspect
public class AsperctTest {
	

	@Before("execution(* cn.vfunding.vfunding.biz.user.service.impl.UserServiceImpl.updateRealName(..))")
	public void test() {
		System.out.println("66");
	}
	
	@After("execution(* cn.vfunding.vfunding.biz.user.service.impl.UserServiceImpl.updateRealName(..))")
	public void test1(JoinPoint jp) {
		Object [] args = jp.getArgs();
		if(args == null || args.length == 0 || !(args[0] instanceof User)){
			return ;
		}
		System.out.println(args[0].getClass());
		User user = (User) args[0]; 
		String status = user.getRealStatus();
		System.out.println("status:"+status);
			
	}
	
	/**
	 * 
	 * <p>设置实名信息成功后动作</p>
	 *
	 * wang.zeyan 2015年4月23日
	 */
	@AfterReturning("execution(* cn.vfunding.vfunding.biz.user.service.impl.UserServiceImpl.updateRealName(..))")
	public void test2(JoinPoint jp) {
		System.out.println("33");
	}
}
