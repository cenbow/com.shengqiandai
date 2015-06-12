package cn.vfunding.vfunding.biz.mq.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.mq.model.Mercenary;

public interface MercenaryMapper {

	List<Mercenary> selectMercenaryInterest(Integer borrowId);
	
	void updateTenderInviteFees(Integer id);
}
