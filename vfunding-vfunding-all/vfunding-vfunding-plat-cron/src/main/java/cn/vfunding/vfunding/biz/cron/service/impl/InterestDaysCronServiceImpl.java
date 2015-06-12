package cn.vfunding.vfunding.biz.cron.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.account.dao.InterestDaysMapper;
import cn.vfunding.vfunding.biz.cron.service.IInterestDaysCronService;

@Service
public class InterestDaysCronServiceImpl implements IInterestDaysCronService {

	@Autowired
	private InterestDaysMapper interestDaysMapper;
	
	@Override
	public int insertInterestDays(String dateStr) {
		// TODO Auto-generated method stub
		return this.interestDaysMapper.insertInterestDays(dateStr);
	}


}
