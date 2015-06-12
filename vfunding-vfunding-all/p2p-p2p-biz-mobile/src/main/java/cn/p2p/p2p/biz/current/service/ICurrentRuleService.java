package cn.p2p.p2p.biz.current.service;

import cn.p2p.p2p.biz.current.model.CurrentRule;

public interface ICurrentRuleService {
    int deleteByPrimaryKey(Integer currentId);

    int insertSelective(CurrentRule record);

    CurrentRule selectByPrimaryKey(Integer currentId);

    int updateByPrimaryKeySelective(CurrentRule record);

}