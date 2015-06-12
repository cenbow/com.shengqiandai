package cn.p2p.p2p.biz.account.service;

import java.math.BigDecimal;
import java.util.List;

import cn.p2p.p2p.biz.account.model.InterestDays;
import cn.vfunding.common.framework.utils.page.PageSearch;

public interface IInterestDaysService {

	List<InterestDays> selectByUserIdListPage(PageSearch pageSearch);

	BigDecimal selectByUserIdSum(Integer userId);

	BigDecimal selectByUserIdYesterday(Integer userId);

	int insertInterestDays(String dateStr);

	Integer selectByDateStr(String dateStr);

	List<InterestDays> selectByUserDateStr(InterestDays record);

}