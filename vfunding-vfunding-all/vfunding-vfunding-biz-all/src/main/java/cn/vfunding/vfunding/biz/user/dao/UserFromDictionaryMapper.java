package cn.vfunding.vfunding.biz.user.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.user.model.UserFromDictionary;

public interface UserFromDictionaryMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(UserFromDictionary record);

	int insertSelective(UserFromDictionary record);

	UserFromDictionary selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserFromDictionary record);

	int updateByPrimaryKey(UserFromDictionary record);

	List<UserFromDictionary> selectAllFromDictionary();
}