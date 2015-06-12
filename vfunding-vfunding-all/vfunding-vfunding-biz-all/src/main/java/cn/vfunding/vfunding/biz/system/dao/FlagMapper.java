package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.Flag;

public interface FlagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Flag record);

    int insertSelective(Flag record);

    Flag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Flag record);

    int updateByPrimaryKey(Flag record);
    /**
     * 加载所有数据
     * @return
     * @author liuyijun
     */
    List<Flag> selectAll();
}