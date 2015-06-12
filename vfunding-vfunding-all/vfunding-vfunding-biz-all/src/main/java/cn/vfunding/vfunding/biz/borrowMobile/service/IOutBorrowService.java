package cn.vfunding.vfunding.biz.borrowMobile.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrowMobile.model.OutBorrowVO;

public interface IOutBorrowService {
	List<OutBorrowVO> selectOutBorrowListPage(PageSearch pageSearch, Integer feelRows);

	OutBorrowVO selectOutBorrowById(Integer id);

	List<OutBorrowVO> selectMidaiNewListPage(PageSearch pageSearch);

	List<OutBorrowVO> selectMidaiSuccessListPage(PageSearch pageSearch);
}
