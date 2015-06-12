package cn.vfunding.vfunding.biz.borrowMobile.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrowMobile.model.BorrowDetailsMobile;
import cn.vfunding.vfunding.biz.borrowMobile.model.BorrowMobile;

public interface IBorrowMobileService {
	List<BorrowMobile> selectBorrowListPage(PageSearch pageSearch,
			Integer feelRows);

	BorrowDetailsMobile selectBorrowById(Integer id);
}
