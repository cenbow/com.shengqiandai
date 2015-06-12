package cn.vfunding.vfunding.biz.newyears.service;

import cn.vfunding.vfunding.biz.newyears.model.ActivityNewyearWish;

public interface IActivityNewyearWishService {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityNewyearWish record);

    int insertSelective(ActivityNewyearWish record);

    ActivityNewyearWish selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityNewyearWish record);

    int updateByPrimaryKey(ActivityNewyearWish record);
    
    ActivityNewyearWish selectByPhone(String phone);
}