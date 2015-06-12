package cn.vfunding.vfunding.biz.system.service;

import cn.vfunding.vfunding.biz.system.model.Fee;

public interface IFeeService {
    Fee selectByPrimaryKey(Integer id);
    
    Fee selectByTimeLimit(Integer timeLimit);
}
