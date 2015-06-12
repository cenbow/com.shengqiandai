package cn.vfunding.vfunding.biz.user.service;

import cn.vfunding.vfunding.biz.user.model.UserIntroduce;

public interface IUserIntroduceService {
    int deleteByPrimaryKey(Integer id);

    int insert(UserIntroduce record);

    int insertSelective(UserIntroduce record);

    UserIntroduce selectByPrimaryKey(Integer id);
    
    UserIntroduce selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(UserIntroduce record);

    int updateByPrimaryKey(UserIntroduce record);
    
    /**
     * 是否符合推送介绍页条件
     * @param userId
     * @return
     */
    Boolean isUserIntroduce(Integer userId);
    
    /**
     * 更新推送时间
     * @param userId
     * @return
     */
    int updateIntroduceTime(Integer userId);
}