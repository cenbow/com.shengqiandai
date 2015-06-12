package cn.vfunding.common.framework.utils.beans;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * 任务执行超级接口
 * 
 * @author LiuJun
 * 
 */
public interface ITaskUtil {

	/**
	 * 此方法每次执行时，都是一次新的事务，事务直接互不影响
	 * 
	 * @author LiuJun
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	void runTaskTheTransaction();

	/**
	 * 此方法每次执行时，所有集合执行都是一个事务，一旦出错，所有的东西都会回滚
	 * 
	 * @author LiuJun
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	void runTaskTransaction();
}
