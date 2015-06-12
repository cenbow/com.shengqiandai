package cn.vfunding.vfunding.biz.all.test.borrow;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.service.IWeekService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring-context.xml" })
public class WeekConvert {
	@Autowired
	IWeekService weekService;
	
	@Test
	public void getWeekAndBorrow(){
		Week week = weekService.selectWeekAndBorrowById(1003);
		System.out.println(week.getName());
	}
	@Test
	public void subTrialWeek(){
		Week w = new Week();
		w.setId(1003);
		w.setStatus(1);
		w.setSaveStatus(1);
		weekService.weekSubTrial(w);
	}
}
