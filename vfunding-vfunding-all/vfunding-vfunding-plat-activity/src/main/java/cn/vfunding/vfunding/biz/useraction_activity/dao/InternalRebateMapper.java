package cn.vfunding.vfunding.biz.useraction_activity.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.useraction_activity.model.InternalRebate;

public interface InternalRebateMapper {

	List<InternalRebate> selectInternalInterest(Integer borrowId);
}
