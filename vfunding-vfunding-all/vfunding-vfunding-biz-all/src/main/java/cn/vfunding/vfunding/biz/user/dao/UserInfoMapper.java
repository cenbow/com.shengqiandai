package cn.vfunding.vfunding.biz.user.dao;

import cn.vfunding.vfunding.biz.user.model.UserInfo;

public interface UserInfoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKeyWithBLOBs(UserInfo record);

	int updateByPrimaryKey(UserInfo record);
	/**
	 * lx
	 * @param userId
	 * @return
	 */
	UserInfo selectByUser_Id(Integer userId);

	/**
	 * 根据用户Id查询基本信息
	 * 
	 * @param userId
	 * @return 2014年5月8日 liuyijun
	 */
	UserInfo selectByUserId(Integer userId);

}