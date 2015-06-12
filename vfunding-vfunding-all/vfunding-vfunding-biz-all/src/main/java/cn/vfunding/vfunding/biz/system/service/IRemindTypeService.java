package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.RemindType;

public interface IRemindTypeService {
    int deleteByPrimaryKey(Integer id);

    int insert(RemindType record);

    int insertSelective(RemindType record);

    RemindType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RemindType record);

    int updateByPrimaryKey(RemindType record);
    /**
     * 获取所有数据(缓存)
     * @return
     * @author liuyijun
     */
    List<RemindType> selectAll();
}