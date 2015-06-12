package cn.vfunding.vfunding.biz.user.service;

import cn.vfunding.vfunding.biz.user.model.UserType;

public interface IUserTypeService {

	UserType selectByPrimaryKey(Integer typeId);
}
