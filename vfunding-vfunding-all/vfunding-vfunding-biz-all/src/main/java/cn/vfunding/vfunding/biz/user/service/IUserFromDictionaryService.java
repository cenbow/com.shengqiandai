package cn.vfunding.vfunding.biz.user.service;

import java.util.List;

import cn.vfunding.vfunding.biz.user.model.UserFromDictionary;

public interface IUserFromDictionaryService {

	int deleteByPrimaryKey(Integer id);

	int insert(UserFromDictionary record);

	int insertSelective(UserFromDictionary record);

	UserFromDictionary selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserFromDictionary record);

	int updateByPrimaryKey(UserFromDictionary record);

	List<UserFromDictionary> selectAllFromDictionary();
}
