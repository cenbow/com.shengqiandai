package cn.p2p.p2p.biz.account.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.account.dao.InterestDaysMapper;
import cn.p2p.p2p.biz.account.model.InterestDays;
import cn.p2p.p2p.biz.account.service.IInterestDaysService;
import cn.vfunding.common.framework.utils.page.PageSearch;

@Service
public class InterestDaysServiceImpl implements IInterestDaysService {

	@Autowired
	private InterestDaysMapper interestDaysMapper;
	@Override
	public List<InterestDays> selectByUserIdListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.interestDaysMapper.selectByUserIdListPage(pageSearch);
	}

	@Override
	public BigDecimal selectByUserIdSum(Integer userId) {
		// TODO Auto-generated method stub
		return this.interestDaysMapper.selectByUserIdSum(userId);
	}

	@Override
	public BigDecimal selectByUserIdYesterday(Integer userId) {
		// TODO Auto-generated method stub
		return this.interestDaysMapper.selectByUserIdYesterday(userId);
	}

	@Override
	public List<InterestDays> selectByUserDateStr(InterestDays record) {
		// TODO Auto-generated method stub
		return this.interestDaysMapper.selectByUserDateStr(record);
	}

	@Override
	public int insertInterestDays(String dateStr) {
		// TODO Auto-generated method stub
		return this.interestDaysMapper.insertInterestDays(dateStr);
	}

	@Override
	public Integer selectByDateStr(String dateStr) {
		// TODO Auto-generated method stub
		return this.interestDaysMapper.selectByDateStr(dateStr);
	}


}
