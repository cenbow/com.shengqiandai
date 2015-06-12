package cn.vfunding.vfunding.biz.borrowMobile.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrowMobile.dao.BorrowMobileMapper;
import cn.vfunding.vfunding.biz.borrowMobile.dao.BorrowTenderMobileMapper;
import cn.vfunding.vfunding.biz.borrowMobile.model.BorrowDetailsMobile;
import cn.vfunding.vfunding.biz.borrowMobile.model.BorrowMobile;
import cn.vfunding.vfunding.biz.borrowMobile.service.IBorrowMobileService;

@Service("borrowMobileService")
public class BorrowMobileServiceImpl implements IBorrowMobileService {

	@Autowired
	private BorrowMobileMapper borrowMapper;

	@Autowired
	private BorrowTenderMobileMapper btmMapper;

	/**
	 * borrow列表
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年5月8日
	 */
	@Override
	public List<BorrowMobile> selectBorrowListPage(PageSearch pageSearch, Integer feelRows) {
//		List<BorrowMobile> listAll = new ArrayList<BorrowMobile>();
//		if (pageSearch.getPage() == 1) {
//			listAll = this.borrowMapper.selectFeelBorrow(feelRows);
//			for (BorrowMobile b : listAll) {
//				Integer status = b.getStatus();
//				BigDecimal aprAccount = b.getAccountYes().divide(b.getAccount(), 2, BigDecimal.ROUND_DOWN);
//				BigDecimal rc = b.getRepaymentAccount();
//				BigDecimal rys = b.getRepaymentYesAccount();
//				if (status == 10 && aprAccount.doubleValue() < 1) {
//					b.setStatus(910);
//				} else if (status == 10 && aprAccount.doubleValue() == 1d) {
//					b.setStatus(920);
//				} else if (status == 30 && rc.doubleValue() > rys.doubleValue() + 1d) {
//					b.setStatus(930);
//				} else if (status == 30 && rc.doubleValue() <= rys.doubleValue() + 1d) {
//					b.setStatus(940);
//				}
//			}
//		}
		List<BorrowMobile> list = this.borrowMapper.selectBorrowListPage(pageSearch);
		for (BorrowMobile b : list) {
			Integer status = b.getStatus();
			BigDecimal aprAccount = b.getAccountYes().divide(b.getAccount(), 2, BigDecimal.ROUND_DOWN);
			BigDecimal rc = b.getRepaymentAccount();
			BigDecimal rys = b.getRepaymentYesAccount();
			if (status == 1 && aprAccount.doubleValue() < 1) {
				b.setStatus(91);
			} else if (status == 1 && aprAccount.doubleValue() == 1d) {
				b.setStatus(92);
			} else if (status == 3 && rc.doubleValue() > rys.doubleValue() + 1d) {
				b.setStatus(93);
			} else if (status == 3 && rc.doubleValue() <= rys.doubleValue() + 1d) {
				b.setStatus(94);
			}
		}
//		listAll.addAll(list);
		return list;
	}

	@Override
	public BorrowDetailsMobile selectBorrowById(Integer id) {
		// TODO Auto-generated method stub
		BorrowDetailsMobile bdm = this.borrowMapper.selectBorrowById(id);
		Integer tenderCount = this.btmMapper.selectTenderBorrowByIdCount(id);
		if (tenderCount != null) {
			bdm.setTenderborrowCount(tenderCount);
		} else {
			bdm.setTenderborrowCount(0);
		}
		return bdm;
	}
}
