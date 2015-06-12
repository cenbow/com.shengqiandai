package cn.vfunding.vfunding.biz.borrowMobile.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrowMobile.dao.BorrowTenderMobileMapper;
import cn.vfunding.vfunding.biz.borrowMobile.model.BorrowTenderMobile;
import cn.vfunding.vfunding.biz.borrowMobile.service.IBorrowTenderMobileService;

/**
 * @author user
 *
 */
@Service("borrowMobileTenderService")
public class BorrowTenderMobileServiceImpl implements
		IBorrowTenderMobileService {

	@Autowired
	private BorrowTenderMobileMapper btMapper;

	/**
	 * 根据borrowID查询目前投资人数
	 */
	@Override
	public int selectTenderBorrowByIdCount(Integer borrowId) {

		return this.btMapper.selectTenderBorrowByIdCount(borrowId);
		
	}

	/**
	 * 根据borrowId查询投资此标的用户信息
	 */
	@Override
	public List<BorrowTenderMobile> selectTenderBorrowByIdListPage(
			PageSearch pageSearch) {
		// TODO Auto-generated method stub
		return this.btMapper.selectTenderBorrowByIdListPage(pageSearch);
	}
}
