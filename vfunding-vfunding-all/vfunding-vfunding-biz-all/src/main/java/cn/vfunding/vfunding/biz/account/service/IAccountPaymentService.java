package cn.vfunding.vfunding.biz.account.service;

import cn.vfunding.vfunding.biz.account.model.AccountPayment;
import cn.vfunding.vfunding.biz.account.model.AccountPaymentWithBLOBs;

public interface IAccountPaymentService {

	int deleteByPrimaryKey(Integer id);

    int insert(AccountPaymentWithBLOBs record);

    int insertSelective(AccountPaymentWithBLOBs record);

    AccountPaymentWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountPaymentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AccountPaymentWithBLOBs record);

    int updateByPrimaryKey(AccountPayment record);
}
