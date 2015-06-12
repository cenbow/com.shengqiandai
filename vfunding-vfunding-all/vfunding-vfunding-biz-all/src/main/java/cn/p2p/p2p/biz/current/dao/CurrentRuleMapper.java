package cn.p2p.p2p.biz.current.dao;

import cn.p2p.p2p.biz.current.model.CurrentRule;

public interface CurrentRuleMapper {
    int deleteByPrimaryKey(Integer currentId);

    int insert(CurrentRule record);

    int insertSelective(CurrentRule record);

    CurrentRule selectByPrimaryKey(Integer currentId);

    int updateByPrimaryKeySelective(CurrentRule record);

    int updateByPrimaryKey(CurrentRule record);
}