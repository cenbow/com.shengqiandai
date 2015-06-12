package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.UserTrack;

public interface IUserTrackService {

	int deleteByPrimaryKey(Integer id);

    int insert(UserTrack record);

    int insertSelective(UserTrack record);

    UserTrack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserTrack record);

    int updateByPrimaryKey(UserTrack record);
    
    /**
     * 查询用户最后一次登录记录信息
     * @param record
     * @return
     * 2014年7月2日
     * liuyijun
     */
    UserTrack selectByLastLogin(UserTrack record);
    
    List<UserTrack> selectBySessionId(String sessionId);
    /**
     * 项目启动时退出所有登录用户
     * 2014年7月10日
     * liuyijun
     */
    void outAllUser();
    /**
     * 更新用户最后一次请求时间
     * @param userId
     * @param sessionId
     * 2014年8月13日
     * liuyijun
     */
    void updateUserLastRequestTime(Integer userId,String sessionId);
    
    /**
     * 检索需要退出的用户
     * @return
     * 2014年8月13日
     * liuyijun
     */
    List<UserTrack> selectNeedOutUser();
    
    /***
     * 用户自动退出 
     * @param second 离最后一次请求多少秒
     * 2014年8月13日
     * liuyijun
     */
    void userAutoOut(int second);
    
}
