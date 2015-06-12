package cn.vfunding.vfunding.biz.user.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.model.UserAmountapply;
import cn.vfunding.vfunding.biz.user.model.UserAmountapplyWithBLOBs;

public interface IUserAmountapplyService {

	int deleteByPrimaryKey(Integer id);
	
	int insert(UserAmountapplyWithBLOBs record);
	
	int insertSelective(UserAmountapplyWithBLOBs record);
	
	UserAmountapplyWithBLOBs selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(UserAmountapplyWithBLOBs record);
	
	int updateByPrimaryKeyWithBLOBs(UserAmountapplyWithBLOBs record);
	
	int updateByPrimaryKey(UserAmountapply record);
	
	List<UserAmountapplyWithBLOBs> selectByParam(UserAmountapply record);

	List<UserAmountapply> selectAmountApplyListPage(PageSearch pageSearch);
}
