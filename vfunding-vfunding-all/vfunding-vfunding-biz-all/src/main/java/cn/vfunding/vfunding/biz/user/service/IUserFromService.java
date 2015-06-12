package cn.vfunding.vfunding.biz.user.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.model.UserFrom;

public interface IUserFromService {
	int deleteByPrimaryKey(Integer id);

	int insert(UserFrom record);

	int insertSelective(UserFrom record);

	UserFrom selectByPrimaryKey(Integer id);
	
	UserFrom selectByUserId(Integer userId);

	int updateByPrimaryKeySelective(UserFrom record);

	int updateByPrimaryKey(UserFrom record);

	List<UserFrom> selectFromUserInfoListPage(PageSearch pageSearch);
}