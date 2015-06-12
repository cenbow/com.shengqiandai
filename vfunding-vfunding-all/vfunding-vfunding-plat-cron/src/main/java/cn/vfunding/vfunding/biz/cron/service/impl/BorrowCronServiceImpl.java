package cn.vfunding.vfunding.biz.cron.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.cron.service.IBorrowCronService;

@Service("borrowCronService")
public class BorrowCronServiceImpl implements IBorrowCronService {
	@Value("${flow.borrow.cron.time}")
	private String flowBorrowTime;
	
	@Value("${send.willRepay.borrow.cron.time}")
	private String willRepayBorrowTime;
	@Autowired
	private IBorrowService borrowService;
	
	@Override
	public String flowBorrow() {
		borrowService.updateFlowBorrow();//流标
		return flowBorrowTime;
	}

	/**
	 * 到期还款提醒
	 * @author liuhuan
	 */
	@Override
	public String willRepayBorrow() {
		borrowService.updateSendWillRepayBorrow();
//		borrowService.updateOverdueBorrow();
		return willRepayBorrowTime;
	}

	
}
