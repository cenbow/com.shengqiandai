package cn.vfunding.vfunding.biz.pdfreport.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.pdfreport.model.UserTenderLine;

public interface UserTenderLineMapper {
	List<UserTenderLine> selectUserTenderLine(Integer userId);
	
	List<UserTenderLine> selectAllTenderLine();
}