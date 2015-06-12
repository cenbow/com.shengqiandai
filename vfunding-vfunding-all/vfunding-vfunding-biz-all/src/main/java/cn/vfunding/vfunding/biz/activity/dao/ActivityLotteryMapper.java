package cn.vfunding.vfunding.biz.activity.dao;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.vfunding.biz.activity.model.ActivityLottery;

public interface ActivityLotteryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityLottery record);

    int insertSelective(ActivityLottery record);

    ActivityLottery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityLottery record);

    int updateByPrimaryKey(ActivityLottery record);
    
    ActivityLottery selectByActivityAndUserId(@Param("activity") String activity,@Param("userId") Integer userId);
    
    ActivityLottery selectEmpty();
}