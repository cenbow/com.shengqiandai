package cn.vfunding.vfunding.biz.test.dao;

import cn.vfunding.vfunding.biz.test.model.VfTest;

public interface VfTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VfTest record);

    int insertSelective(VfTest record);

    VfTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VfTest record);

    int updateByPrimaryKey(VfTest record);
}