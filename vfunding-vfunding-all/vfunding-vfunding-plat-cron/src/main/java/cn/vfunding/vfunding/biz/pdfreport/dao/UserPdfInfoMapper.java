package cn.vfunding.vfunding.biz.pdfreport.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.pdfreport.model.UserPdfInfo;

public interface UserPdfInfoMapper {

	List<UserPdfInfo> selectUserInfo();

	List<UserPdfInfo> selectSendEmailUser();

	UserPdfInfo selectAllTenderInfo();
	
}