package cn.vfunding.vfunding.biz.thirdplat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.thirdplat.dao.ThirdRelationshipMapper;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;
import cn.vfunding.vfunding.biz.thirdplat.service.IThirdRelationshipService;

@Service
public class ThirdRelationshipServiceImpl implements IThirdRelationshipService {

	@Autowired
	private ThirdRelationshipMapper thirdRelationshipMapper;

	@Override
	public int insert(ThirdRelationship record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ThirdRelationship record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ThirdRelationship selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ThirdRelationship selectByUserIdType(Integer userId, Integer type) {
		// TODO Auto-generated method stub
		return this.thirdRelationshipMapper.selectByUserIdType(userId, type);
	}

	@Override
	public int updateByPrimaryKeySelective(ThirdRelationship record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ThirdRelationship record) {
		// TODO Auto-generated method stub
		return 0;
	}

}
