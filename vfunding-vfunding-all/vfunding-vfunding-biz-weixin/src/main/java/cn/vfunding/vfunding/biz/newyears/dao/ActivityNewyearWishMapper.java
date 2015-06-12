package cn.vfunding.vfunding.biz.newyears.dao;

import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyearWish;

public interface ActivityNewyearWishMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityNewyearWish record);

    int insertSelective(ActivityNewyearWish record);

    ActivityNewyearWish selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityNewyearWish record);

    int updateByPrimaryKey(ActivityNewyearWish record);
    
    ActivityNewyearWish selectByPhone(String phone);
}