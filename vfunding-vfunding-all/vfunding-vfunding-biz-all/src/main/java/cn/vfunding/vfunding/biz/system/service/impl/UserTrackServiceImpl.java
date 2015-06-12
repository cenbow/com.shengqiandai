package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.system.dao.UserTrackMapper;
import cn.vfunding.vfunding.biz.system.model.UserTrack;
import cn.vfunding.vfunding.biz.system.service.IUserTrackService;
@Service("userTrackService")
public class UserTrackServiceImpl implements IUserTrackService{

	@Autowired
	private UserTrackMapper mapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserTrack record) {
	return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(UserTrack record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public UserTrack selectByPrimaryKey(Integer id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserTrack record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserTrack record) {
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public UserTrack selectByLastLogin(UserTrack record) {
		return this.mapper.selectByLastLogin(record);
	}

	@Override
	public List<UserTrack> selectBySessionId(String sessionId) {
		return this.mapper.selectBySessionId(sessionId);
	}

	
	

	@Override
	public void outAllUser() {
		this.mapper.outAllUser();
	}

	@Override
	public void updateUserLastRequestTime(Integer userId, String sessionId) {
		UserTrack track=new UserTrack();
		track.setUserId(userId.toString());
		track.setSessionId(sessionId);
		this.mapper.updateLastReqTime(track);
	}

	@Override
	public List<UserTrack> selectNeedOutUser() {
		return this.mapper.selectNeedOut();
	}

	@Override
	public void userAutoOut(int second) {
		 this.mapper.userAutoOut(second);
	}

}
