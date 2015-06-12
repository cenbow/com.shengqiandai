package cn.vfunding.vfunding.biz.aop.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.vfunding.biz.aop.IBorrowTenderAop;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;

@Component("borrowTenderAop")
public class BorrowTenderAopImpl implements IBorrowTenderAop {

	@Autowired
	private IBorrowTenderService BorrowTenderService;

	@Override
	public UserTenderActionResultVO insertUserTenderAction(UserTenderActionVO vo) {
		//return this.BorrowTenderService.insertUserTenderAction(vo);
		return this.BorrowTenderService.userTenderAction(vo);
	}

}
