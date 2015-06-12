package cn.vfunding.vfunding.biz.borrow.service;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.BorrowCollection;
import cn.vfunding.vfunding.biz.common.vo.CollectionTenderUserVO;

public interface IBorrowCollectionService {

	
	List<BorrowCollection> selectCollectionTimeByUserId(Integer userId,Integer status);

	BigDecimal selectWaitFeelInterest(int parseInt);

	String selectFeelRepayTime(int parseInt);

	/**
	 * 待收列表
	 * @author liuhuan
	 */
	List<CollectionTenderUserVO> selectCollectionRecordListPage(PageSearch pageSearch);
	
	List<CollectionTenderUserVO> selectFeelList(PageSearch pageSearch);
	
}
