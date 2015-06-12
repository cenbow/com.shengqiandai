package cn.vfunding.vfunding.biz.user.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.user.model.UserAmount;

public interface UserAmountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAmount record);

    int insertSelective(UserAmount record);

    UserAmount selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAmount record);

    int updateByPrimaryKey(UserAmount record);
    
    /**
     * @Description:多参数查询
     * @param record
     * @return
     * @author liuhuan
     */
    List<UserAmount> selectByParam(UserAmount record);
    
    int deleteByUserId(Integer userId);
}