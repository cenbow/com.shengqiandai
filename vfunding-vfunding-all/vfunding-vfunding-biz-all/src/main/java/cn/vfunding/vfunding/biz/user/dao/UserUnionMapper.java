package cn.vfunding.vfunding.biz.user.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.user.model.UserUnion;

public interface UserUnionMapper {
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
     * 查询可复活网站联盟用户
     * @return
     */
    List<UserUnion> selectCanRevive();
    /**
     * 查询不可复活网站联盟用户
     * @return
     */
    List<UserUnion> selectCanNotRevive();
    
    /**
     * 根据微积金用户Id查询
     * @param userId
     * @return
     * 2014年5月21日
     * liuyijun
     */
    UserUnion selectByUserId(Integer userId);
}

