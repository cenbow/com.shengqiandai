package cn.p2p.p2p.biz.borrow.service;

import java.util.List;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.common.vo.VerifyBorrowVO;

/**
 * 天标管理service
 * @author liuhuan
 */
public interface IDayBorrowService {

	
	List<VerifyBorrowVO> dayBorrowListPage(PageSearch pageSearch);

	
	Json insertSelective(Borrow borrow, Integer userId, String ip);
	
}
