package cn.vfunding.vfunding.biz.useraction_activity.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.useraction_activity.model.FriendRebate;

public interface FriendRebateMapper {

	List<FriendRebate> selectFriendInterest(Integer borrowId);
	
	void updateTenderInviteFees(Integer id);
	
	
}
