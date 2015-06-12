package cn.vfunding.common.framework.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;

public class LockUtil implements InitializingBean {

	@Value("${zookeeper.server}")
	private String zookeeperConnectionString;

	private RetryPolicy retryPolicy = null;
	private CuratorFramework client = null;

	public InterProcessMutex getLock(String path) {
		if (EmptyUtil.isEmpty(path)) {
			path = "/locks/borrow";
		} else if (!path.startsWith("/")) {
			path += "/";
		}
		InterProcessMutex lock = new InterProcessMutex(client, path);
		return lock;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		retryPolicy = new ExponentialBackoffRetry(1000, 3);
		client = CuratorFrameworkFactory.newClient(zookeeperConnectionString,
				retryPolicy);
		client.start();
	}

}
