package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.RemindType;

public interface RemindTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RemindType record);

    int insertSelective(RemindType record);

    RemindType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RemindType record);

    int updateByPrimaryKey(RemindType record);
    /**
     * 加载所有数据
     * @return
     * @author liuyijun
     */
    List<RemindType> selectAll();
}