package cn.vfunding.vfunding.biz.newyears.dao;

import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyear;

public interface ActivityNewyearMapper {
    int deleteByPrimaryKey(String phone);

    int insert(ActivityNewyear record);

    int insertSelective(ActivityNewyear record);

    ActivityNewyear selectByPrimaryKey(String phone);

    int updateByPrimaryKeySelective(ActivityNewyear record);

    int updateByPrimaryKey(ActivityNewyear record);
    
    ActivityNewyear selectByPhone(String phone);
}