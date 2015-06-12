package cn.vfunding.vfunding.biz.sina.test;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class QuerySinaTest {

	@Autowired
	private IQuerySinaService querySinaService;
	
	@Test
	public void test() throws Exception{
		Integer userId = 2777;
		Json json = new Json();
		try {
			 Map<String,String> result = this.querySinaService.doQueryBalance(userId);
			 json.setObj(result);
			 if(result.get("success").equals("success")){
				 json.setSuccess(true);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(json.getObj().toString());
	}
}
