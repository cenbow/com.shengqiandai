package cn.vfunding.vfunding.biz.all.dic.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.vfunding.vfunding.biz.sina.model.SinaDic;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring-context.xml" })
public class DicTest {
	@Autowired
	private ISinaDicService sinaDicService;
	
	@Test
	public void loadDic(){
		List<SinaDic> sds = sinaDicService.dicLoad("PayStatus");
		SinaDic sd = sinaDicService.dicLoad("PayStatus", "SUCCESS");
		System.out.println(JSON.toJSONString(sds));
		System.out.println(JSON.toJSONString(sd));
	}
	
}
