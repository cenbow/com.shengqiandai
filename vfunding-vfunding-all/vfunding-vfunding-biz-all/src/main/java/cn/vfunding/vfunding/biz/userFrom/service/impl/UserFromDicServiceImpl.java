package cn.vfunding.vfunding.biz.userFrom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.userFrom.dao.UserFromDicMapper;
import cn.vfunding.vfunding.biz.userFrom.model.UserFromDic;
import cn.vfunding.vfunding.biz.userFrom.service.IUserFromDicService;
@Service
public class UserFromDicServiceImpl implements IUserFromDicService {
	@Autowired
	private UserFromDicMapper userFromDicMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userFromDicMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserFromDic record) {
		// TODO Auto-generated method stub
		return userFromDicMapper.insertSelective(record);
	}

	@Override
	public UserFromDic selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userFromDicMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserFromDic record) {
		// TODO Auto-generated method stub
		return updateByPrimaryKeySelective(record);
	}

	@Override
	public UserFromDic selectByFromNo(String fromNo) {
		// TODO Auto-generated method stub
		return userFromDicMapper.selectByFromNo(fromNo);
	}

}
