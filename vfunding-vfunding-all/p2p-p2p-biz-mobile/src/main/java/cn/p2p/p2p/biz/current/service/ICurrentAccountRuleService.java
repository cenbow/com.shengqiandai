package cn.p2p.p2p.biz.current.service;

import cn.p2p.p2p.biz.current.model.CurrentAccountRule;

public interface ICurrentAccountRuleService {
    int deleteByPrimaryKey(Integer userId);

    int insertSelective(CurrentAccountRule record);

    CurrentAccountRule selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(CurrentAccountRule record);

}