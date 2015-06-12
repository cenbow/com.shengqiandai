package cn.vfunding.vfunding.biz.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.user.dao.UserFromDictionaryMapper;
import cn.vfunding.vfunding.biz.user.model.UserFromDictionary;
import cn.vfunding.vfunding.biz.user.service.IUserFromDictionaryService;

@Service("userFromDictionaryService")
public class UserFromDictionaryServiceImpl implements
		IUserFromDictionaryService {

	@Autowired
	private UserFromDictionaryMapper userFromDictionaryMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.userFromDictionaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserFromDictionary record) {
		return  this.userFromDictionaryMapper.insert(record);
	}

	@Override
	public int insertSelective(UserFromDictionary record) {
		return this.userFromDictionaryMapper.insertSelective(record);
	}

	@Override
	public UserFromDictionary selectByPrimaryKey(Integer id) {
		return this.userFromDictionaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserFromDictionary record) {
		return this.userFromDictionaryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserFromDictionary record) {
		return this.userFromDictionaryMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UserFromDictionary> selectAllFromDictionary() {
		return this.userFromDictionaryMapper.selectAllFromDictionary();
	}

}
