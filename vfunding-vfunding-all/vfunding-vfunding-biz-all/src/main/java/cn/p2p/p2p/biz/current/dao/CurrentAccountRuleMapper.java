package cn.p2p.p2p.biz.current.dao;

import cn.p2p.p2p.biz.current.model.CurrentAccountRule;

public interface CurrentAccountRuleMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(CurrentAccountRule record);

    int insertSelective(CurrentAccountRule record);

    CurrentAccountRule selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(CurrentAccountRule record);

    int updateByPrimaryKey(CurrentAccountRule record);
}