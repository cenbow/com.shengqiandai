package cn.vfunding.vfunding.plat.cron;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.common.framework.database.redis.RedisManager;
import cn.vfunding.vfunding.biz.cron.service.IAgainLogCronService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-context.xml" })
public class AgainLogTest {

	@Autowired
	@Qualifier("againLogsCronService")
	private IAgainLogCronService againLogCronService;

	@Autowired
	private RedisManager redisManager;

	@Test
	public void test() {
		this.againLogCronService.retryRequest();
	}

	@Test
	public void testList() throws Exception {
		// this.againLogCronService.retryRequest();

		List<String> fa = new ArrayList<String>();
		fa.add("test1");
		fa.add("test2");
		fa.add("test3");
		fa.add("test4");
		fa.add("test5");
		redisManager.saveList("againLogsByList", fa);
	}

	@Test
	public void testMap() throws Exception {
		Map<String, String> user = new HashMap<String, String>();
		user.put("name", "minxr");
		user.put("pwd", "password");
		redisManager.saveMap("testMap", user);

	}
	
	@Test
	public void testListMap() throws Exception {
		Map<String, String> user = new HashMap<String, String>();
		user.put("name", "minxr");
		user.put("pwd", "password");
		
		List<Map<String, String>> fa = new ArrayList<Map<String, String>>();
		//redisManager.saveList("againLogsByList", fa);

	}

}
