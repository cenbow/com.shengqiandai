package cn.vfunding.vfunding.biz.user.service;

import java.util.List;

import cn.vfunding.vfunding.biz.user.model.UserUnion;

public interface IUserUnionService {

	int deleteByPrimaryKey(String unionUserId);

    int insert(UserUnion record);

    int insertSelective(UserUnion record);

    UserUnion selectByPrimaryKey(String unionUserId);

    int updateByPrimaryKeySelective(UserUnion record);

    int updateByPrimaryKey(UserUnion record);
    /**
     * 查询出所有可获利的网站联盟用户
     * @return
     */
    List<UserUnion> selectCanProfitUser();
    
    /**
     * 查询可复活的用户信息
     * @return
     */
    List<UserUnion> selectCanRevive();
    
    /**
     * 查询不可复活的用户信息
     * @return
     */
    List<UserUnion> selectNotRevive();
    
    /**
     * 根据微积金用户Id查询
     * @param userId
     * @return
     * 2014年5月21日
     * liuyijun
     */
    UserUnion selectByUserId(Integer userId);
}
