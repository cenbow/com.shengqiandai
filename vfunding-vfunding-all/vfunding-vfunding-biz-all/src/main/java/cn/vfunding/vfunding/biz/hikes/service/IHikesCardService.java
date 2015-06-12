package cn.vfunding.vfunding.biz.hikes.service;

import cn.vfunding.vfunding.biz.hikes.model.HikesCard;

public interface IHikesCardService {
	
	int deleteByPrimaryKey(Integer userId);

    int insertSelective(HikesCard record);

    HikesCard selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(HikesCard record);

	int insertHikesCardForActivityTwoMillons(Integer userId);
	
	int selectUnLookCount(Integer userId);
}
