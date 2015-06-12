package cn.vfunding.vfunding.biz.redis.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.common.framework.database.redis.RedisManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring-context.xml" })
public class RedisTest {

	@Autowired
	private RedisManager redisManager;

	@Test
	public void testRedis() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "liuyijun");
		map.put("pwd", "123");

		this.redisManager.saveMap("testmap", map);
	}

	@Test
	public void testRedisHase() throws Exception {
//
//		Long l = this.redisManager.saveHash("testhash", "field1", "value22");
//		System.out.println(l);
	}

	@Test
	public void testRedisMap() throws Exception {
//		List<String> list = this.redisManager
//				.getValuesFromMapByStoreKeyAndMapKey(
//						"jetty-session-18g04n1d7hz9pjiqa1f4tu34w",
//						"attributes");
//		for (String string : list) {
//			System.out.println(string);
//		}
	}
}
