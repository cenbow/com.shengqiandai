package cn.vfunding.vfunding.biz.usertoken.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.usertoken.dao.UserTokenMapper;
import cn.vfunding.vfunding.biz.usertoken.model.UserToken;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

@Service
public class UserTokenServiceImpl implements IUserTokenService {

	@Autowired
	private UserTokenMapper userTokenMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.userTokenMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(UserToken record) {
		return this.userTokenMapper.insert(record);
	}

	@Override
	public UserToken selectByPrimaryKey(Integer id) {
		return this.userTokenMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserToken record) {
		return this.userTokenMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public UserToken selectBySelective(UserToken record) {
		return this.userTokenMapper.selectBySelective(record);
	}
	@Override
	public UserToken selectByToken(String token){
		UserToken ut = new UserToken();
		ut.setToken(token);
		return this.selectBySelective(ut);
	}

	@Override
	public String selectTokenByUserId(Integer userId) {
		UserToken ut = new UserToken();
		ut.setUserId(userId);
		ut = this.selectBySelective(ut);
		return ut.getToken();
	}

	@Override
	public Integer selectUserIdByToken(String token) {
		UserToken ut = new UserToken();
		ut.setToken(token);
		ut = this.selectBySelective(ut);
		return ut.getUserId();
	}

}
