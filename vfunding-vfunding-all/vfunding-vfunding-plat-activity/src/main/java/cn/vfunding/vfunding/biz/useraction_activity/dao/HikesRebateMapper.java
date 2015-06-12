package cn.vfunding.vfunding.biz.useraction_activity.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.useraction_activity.model.HikesRebate;

public interface HikesRebateMapper {

	List<HikesRebate> selectHikesInterest(Integer borrowId);
}
