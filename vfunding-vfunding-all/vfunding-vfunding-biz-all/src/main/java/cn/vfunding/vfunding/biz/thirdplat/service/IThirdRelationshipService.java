package cn.vfunding.vfunding.biz.thirdplat.service;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;

public interface IThirdRelationshipService {

	int insert(ThirdRelationship record);

	int insertSelective(ThirdRelationship record);

	ThirdRelationship selectByPrimaryKey(Integer id);

	ThirdRelationship selectByUserIdType(@Param("userId") Integer userId, @Param("type") Integer type);

	int updateByPrimaryKeySelective(ThirdRelationship record);

	int updateByPrimaryKey(ThirdRelationship record);
}