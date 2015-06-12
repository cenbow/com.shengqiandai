package cn.vfunding.common.plat.sender.dao;

import cn.vfunding.common.plat.sender.model.RealnameRecord;

public interface RealnameRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RealnameRecord record);

    int insertSelective(RealnameRecord record);

    RealnameRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RealnameRecord record);

    int updateByPrimaryKey(RealnameRecord record);
}