package cn.vfunding.vfunding.biz.system.dao;

import cn.vfunding.vfunding.biz.system.model.SuggestMessage;

public interface SuggestMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SuggestMessage record);

    int insertSelective(SuggestMessage record);

    SuggestMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SuggestMessage record);

    int updateByPrimaryKey(SuggestMessage record);
}