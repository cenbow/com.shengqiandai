package cn.vfunding.vfunding.biz.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.user.dao.UserTypeMapper;
import cn.vfunding.vfunding.biz.user.model.UserType;
import cn.vfunding.vfunding.biz.user.service.IUserTypeService;
@Service("userTypeService")
public class UserTypeServiceImpl implements IUserTypeService {

	@Autowired
	private UserTypeMapper mapper;
	@Override
	public UserType selectByPrimaryKey(Integer typeId) {
		return this.mapper.selectByPrimaryKey(typeId);
	}

}
