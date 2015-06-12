package cn.p2p.p2p.biz.borrow.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.borrow.service.IDayBorrowService;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.common.vo.VerifyBorrowVO;
import cn.vfunding.vfunding.biz.system.dao.InvestorsFeeMapper;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
@Service("dayBorrowService")
public class IDayBorrowServiceImpl implements IDayBorrowService {

	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private InvestorsFeeMapper investorsFeeMapper;
	
	@Override
	public List<VerifyBorrowVO> dayBorrowListPage(PageSearch pageSearch) {
		return borrowMapper.dayBorrowListPage(pageSearch);
	}

	@Override
	public Json insertSelective(Borrow borrow, Integer userId, String ip) {
		Json j = new Json();
		borrow.setAddip(ip);
		borrow.setAddtime(Integer.parseInt(DateUtil.getTime()));
		borrow.setUserId(userId);
		borrow.setIsday((byte)1);
		borrow.setStatus((byte)1);
		borrow.setStyle((byte) 2);// 到期一次性还款
		borrow.setVerifyUser(0);
		borrow.setSuccessTime(Integer.parseInt(DateUtil.getTime()));
		borrow.setVerifyTime(Integer.parseInt(DateUtil.getTime()));
		borrow.setVerifyRemark("天标系统自动初审");
		borrowMapper.insertSelective(borrow);
		
		// 插入投资费率
		InvestorsFee investorsFee = new InvestorsFee();
		investorsFee.setBid(borrow.getId());
		investorsFee.setBfee(new BigDecimal("0"));
		investorsFee.setGfee(new BigDecimal("0"));
		investorsFee.setBfeeType(new BigDecimal("0"));
		investorsFee.setAddtime(Integer.parseInt(DateUtil.getTime()));
		investorsFee.setAddip(ip);
		investorsFeeMapper.insert(investorsFee);
		
		j.setSuccess(true);
		return j;
	}

	
	
}
