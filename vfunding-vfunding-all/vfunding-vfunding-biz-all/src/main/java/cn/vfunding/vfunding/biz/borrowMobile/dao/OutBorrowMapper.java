package cn.vfunding.vfunding.biz.borrowMobile.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrowMobile.model.OutBorrowVO;

public interface OutBorrowMapper {

	/**
	 * 金融1号点
	 * 
	 * @param pageSearch
	 * @return
	 */
	List<OutBorrowVO> selectOutBorrowListPage(PageSearch pageSearch);

	/**
	 * 金融1号点
	 * 
	 * @param pageSearch
	 * @return
	 */
	OutBorrowVO selectOutBorrowById(Integer id);

	List<OutBorrowVO> selectMidaiNewListPage(PageSearch pageSearch);

	List<OutBorrowVO> selectMidaiSuccessListPage(PageSearch pageSearch);
}