package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.Payment;
import cn.vfunding.vfunding.biz.system.model.PaymentWithBLOBs;

public interface PaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentWithBLOBs record);

    int insertSelective(PaymentWithBLOBs record);

    PaymentWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaymentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(PaymentWithBLOBs record);

    int updateByPrimaryKey(Payment record);
}