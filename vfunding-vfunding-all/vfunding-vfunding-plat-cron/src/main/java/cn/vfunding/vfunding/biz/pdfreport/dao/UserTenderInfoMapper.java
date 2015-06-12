package cn.vfunding.vfunding.biz.pdfreport.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.pdfreport.model.UserTenderInfo;

public interface UserTenderInfoMapper {

	List<UserTenderInfo> selectUserTenderInfo(Integer userId);

	Integer selectUserRanking(Integer userId);
}