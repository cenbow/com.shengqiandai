package cn.vfunding.vfunding.biz.cron.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;

public class SqdProductReturnCronServiceImpl {
	/**
	 * 产品自动还款服务
	 * @author huangyuancheng
	 * 
	 */
	@Autowired
	private IBorrowService borrowService;
	
	public void autoRetrunMoney(){
		List<Borrow> blist=borrowService.selectBorrowById(1);
	}
	
}
