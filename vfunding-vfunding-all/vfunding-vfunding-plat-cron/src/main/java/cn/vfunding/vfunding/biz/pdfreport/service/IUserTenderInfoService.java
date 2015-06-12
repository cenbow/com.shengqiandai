package cn.vfunding.vfunding.biz.pdfreport.service;

import java.util.List;

import cn.vfunding.vfunding.biz.pdfreport.model.UserTenderLine;

public interface IUserTenderInfoService {

	List<UserTenderLine> selectUserTenderInfo();

}
