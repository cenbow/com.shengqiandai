package cn.vfunding.vfunding.biz.borrowMobile.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrowMobile.model.BorrowDetailsMobile;
import cn.vfunding.vfunding.biz.borrowMobile.model.BorrowMobile;

public interface BorrowMobileMapper {

	/**
	 * APP端-主页显示borrowList
	 * 
	 * @param pageSearch
	 * @return
	 */
	List<BorrowMobile> selectBorrowListPage(PageSearch pageSearch);

	/**
	 * 体验标查询
	 * 
	 * @param rows
	 * @return author LiLei 2014年5月8日
	 */
	List<BorrowMobile> selectFeelBorrow(Integer rows);

	/**
	 * APP端-显示borrow详细类容
	 * 
	 * @param pageSearch
	 * @return
	 */
	BorrowDetailsMobile selectBorrowById(Integer id);
}