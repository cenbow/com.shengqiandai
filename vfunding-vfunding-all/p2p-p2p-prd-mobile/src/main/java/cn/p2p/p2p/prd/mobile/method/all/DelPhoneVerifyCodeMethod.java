package cn.p2p.p2p.prd.mobile.method.all;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.ContBefore;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Component
public class DelPhoneVerifyCodeMethod {
	@Resource(name = "shardedJedisPool")
	private ShardedJedisPool shardedJedisPool;

	/**
	 * 发送验证码
	 * 
	 * @param generalRequest
	 * @return
	 *
	 *         2015年5月25日 lijianwei
	 * @throws InterruptedException
	 */
	@ContBefore
	@Async
	public MobileBaseResponse removePhoneVerifyCode(String phone) {
		ShardedJedis jedis = null;
		System.out.println("async start");
		try {
			Thread.sleep(300000);
		} catch (InterruptedException ex) {
			Thread.interrupted();
		}
		System.out.println("async end");
		jedis = shardedJedisPool.getResource();
		jedis.del(DigestUtils.md5Hex(phone + "1QAZ2wsx"));
		return new MobileBaseResponse();
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.3.11");
		String output;
		jedis.set("hello", "world");
		output = jedis.get("hello");
		System.out.println(output);
		jedis.del("hello");
	}

}
