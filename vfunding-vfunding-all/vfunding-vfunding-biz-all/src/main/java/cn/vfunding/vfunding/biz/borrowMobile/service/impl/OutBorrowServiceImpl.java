package cn.vfunding.vfunding.biz.borrowMobile.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrowMobile.dao.BorrowTenderMobileMapper;
import cn.vfunding.vfunding.biz.borrowMobile.dao.OutBorrowMapper;
import cn.vfunding.vfunding.biz.borrowMobile.model.OutBorrowVO;
import cn.vfunding.vfunding.biz.borrowMobile.service.IOutBorrowService;

@Service("outBorrowService")
public class OutBorrowServiceImpl implements IOutBorrowService {

	@Autowired
	private OutBorrowMapper borrowMapper;

	@Autowired
	private BorrowTenderMobileMapper btmMapper;

	/**
	 * borrow列表
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年5月8日
	 */
	@Override
	public List<OutBorrowVO> selectOutBorrowListPage(PageSearch pageSearch, Integer feelRows) {
		List<OutBorrowVO> list = this.borrowMapper.selectOutBorrowListPage(pageSearch);
		for (OutBorrowVO b : list) {
			Integer status = b.getStatus();
			BigDecimal aprAccount = b.getAccountYes().divide(b.getAccount(), 2);
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
		return list;
	}

	@Override
	public OutBorrowVO selectOutBorrowById(Integer id) {
		// TODO Auto-generated method stub
		OutBorrowVO bdm = this.borrowMapper.selectOutBorrowById(id);
		Integer tenderCount = this.btmMapper.selectTenderBorrowByIdCount(id);
		if (tenderCount != null) {
			bdm.setTenderborrowCount(tenderCount);
		} else {
			bdm.setTenderborrowCount(0);
		}
		return bdm;
	}

	@Override
	public List<OutBorrowVO> selectMidaiNewListPage(PageSearch pageSearch) {
		List<OutBorrowVO> list = this.borrowMapper.selectMidaiNewListPage(pageSearch);
		Integer tenderCount = 0;
		for (OutBorrowVO outBorrowVO : list) {
			tenderCount = this.btmMapper.selectTenderBorrowByIdCount(outBorrowVO.getId());
			outBorrowVO.setTenderborrowCount(tenderCount);
		}
		return list;
	}

	@Override
	public List<OutBorrowVO> selectMidaiSuccessListPage(PageSearch pageSearch) {
		// TODO Auto-generated method stub
		List<OutBorrowVO> list = this.borrowMapper.selectMidaiSuccessListPage(pageSearch);
		Integer tenderCount = 0;
		for (OutBorrowVO outBorrowVO : list) {
			tenderCount = this.btmMapper.selectTenderBorrowByIdCount(outBorrowVO.getId());
			outBorrowVO.setTenderborrowCount(tenderCount);
		}
		return list;
	}
}
