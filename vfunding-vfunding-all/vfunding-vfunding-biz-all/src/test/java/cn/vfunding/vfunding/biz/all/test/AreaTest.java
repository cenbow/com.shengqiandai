package cn.vfunding.vfunding.biz.all.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.vfunding.biz.system.dao.AreaMapper;
import cn.vfunding.vfunding.biz.system.model.Area;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring-context.xml" })
public class AreaTest {

	@Autowired
	private AreaMapper areaMapper;
	
	
	@Test
	public void testArea(){
		List<Area>  areas = this.areaMapper.selectAll();
		for (Area area : areas) {
			if(EmptyUtil.isNotEmpty(area.getName())){
				area.setDomain(StringUtil.converterToFirstSpell(area.getName()));
				area.setNid(StringUtil.converterToSpell(area.getName()));
			}
			this.areaMapper.updateByPrimaryKeySelective(area);
		}
	}
	
	@Test
	public void testCity(){
		List<Area>  areas = this.areaMapper.selectAllCity();
		List<Map<String,String>> result=new ArrayList<Map<String,String>>();
		for (Area area : areas) {
			
			Map<String,String> map=new HashMap<String,String>();
			map.put("city", area.getName());
			map.put("label", area.getName());
			map.put("alias", area.getName());
			map.put("value", area.getNid());
		    result.add(map);
		}
		
		System.out.println(JSON.toJSONString(result));
	}
	
}
