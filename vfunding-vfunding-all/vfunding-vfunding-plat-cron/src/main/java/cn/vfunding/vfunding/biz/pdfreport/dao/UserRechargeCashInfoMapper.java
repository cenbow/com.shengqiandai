package cn.vfunding.vfunding.biz.pdfreport.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.pdfreport.model.UserRechargeCashInfo;

public interface UserRechargeCashInfoMapper {

	List<UserRechargeCashInfo> selectUserRechargeCash(Integer userId);

}