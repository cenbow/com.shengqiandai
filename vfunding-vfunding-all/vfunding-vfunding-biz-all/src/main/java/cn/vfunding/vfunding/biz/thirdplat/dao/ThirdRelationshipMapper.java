package cn.vfunding.vfunding.biz.thirdplat.dao;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;

public interface ThirdRelationshipMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ThirdRelationship record);

    int insertSelective(ThirdRelationship record);

    ThirdRelationship selectByPrimaryKey(Integer id);
    
    ThirdRelationship selectByUserIdType(@Param("userId")Integer userId,@Param("type")Integer type);

    int updateByPrimaryKeySelective(ThirdRelationship record);

    int updateByPrimaryKey(ThirdRelationship record);
}