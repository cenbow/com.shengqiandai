package cn.p2p.p2p.biz.current.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.current.dao.CurrentUserAccountMapper;
import cn.p2p.p2p.biz.current.model.CurrentUserAccount;
import cn.p2p.p2p.biz.current.service.ICurrentUserAccountService;

@Service
public class CurrentUserAccountServiceImpl implements ICurrentUserAccountService{

	
	@Autowired
	private CurrentUserAccountMapper currentUserAccountMapper;
	@Override
	public int deleteByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(CurrentUserAccount record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CurrentUserAccount selectByPrimaryKey(Integer userId) {
		// TODO Auto-generated method stub
		return this.currentUserAccountMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(CurrentUserAccount record) {
		// TODO Auto-generated method stub
		return 0;
	}
   

}