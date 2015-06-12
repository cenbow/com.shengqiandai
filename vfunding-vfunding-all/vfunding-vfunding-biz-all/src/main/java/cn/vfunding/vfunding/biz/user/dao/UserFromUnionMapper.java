package cn.vfunding.vfunding.biz.user.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.user.model.CleanOldUserVO;
import cn.vfunding.vfunding.biz.user.model.UserFromUnion;

public interface UserFromUnionMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserFromUnion record);

    int insertSelective(UserFromUnion record);

    UserFromUnion selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserFromUnion record);

    int updateByPrimaryKey(UserFromUnion record);
    /**
     * 根据网站联盟用户ID查询他所带来的离当前时间最后一个注册的用户信息
     * @param unionUserId
     * @return
     */
    UserFromUnion selectByUnionUserIdAndLastRegist(String unionUserId);
    /**
     * 网站联盟用户自最后一次复活前所有的用户被清零
     * @param vo
     * @return
     */
    int cleanOldUserByUnionUser(CleanOldUserVO vo);
    
    /**
     * 查询所有需要更新期数的用户信息
     * @return
     */
    List<UserFromUnion> selectByNeedChangePeriods();
}