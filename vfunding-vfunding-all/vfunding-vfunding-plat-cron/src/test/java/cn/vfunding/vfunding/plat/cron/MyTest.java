package cn.vfunding.vfunding.plat.cron;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.vfunding.biz.pdfreport.dao.UserUsefundsPieMapper;
import cn.vfunding.vfunding.biz.pdfreport.model.UserUsefundsPie;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring.xml" })
public class MyTest {

	@Autowired
	private UserMapper userMapper;

	UserUsefundsPieMapper pieMapper;

	@Test
	public void test1() {
		try {
			UserWithBLOBs u = this.userMapper.selectByPrimaryKey(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test2() {
		try {
			List<UserUsefundsPie> pie = this.pieMapper.selectUserUsefundsPie();
			System.out.println(pie.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
