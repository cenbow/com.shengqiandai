package cn.vfunding.vfunding.biz.mq.service;

import cn.vfunding.vfunding.biz.borrow.model.BorrowAuto;


public interface IBorrowAutoService {
	
	BorrowAuto selectByPrimaryKey(Integer id);

	int updateBorrowAuto(BorrowAuto auto);

	BorrowAuto selectBorrowAutoByUserId(Integer userId);
}
