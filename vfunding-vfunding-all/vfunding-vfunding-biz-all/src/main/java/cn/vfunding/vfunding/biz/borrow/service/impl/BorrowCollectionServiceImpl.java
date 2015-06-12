package cn.vfunding.vfunding.biz.borrow.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowCollectionMapper;
import cn.vfunding.vfunding.biz.borrow.model.BorrowCollection;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowCollectionService;
import cn.vfunding.vfunding.biz.common.vo.CollectionTenderUserVO;
@Service("borrowCollectionService")
public class BorrowCollectionServiceImpl implements IBorrowCollectionService {

	@Autowired
	private BorrowCollectionMapper borrowCollectionMapper;
	

	@Override
	public List<BorrowCollection> selectCollectionTimeByUserId(Integer userId,Integer status) {
		return borrowCollectionMapper.selectCollectionTimeByUserId(userId,status);
	}


	@Override
	public BigDecimal selectWaitFeelInterest(int userId) {
		return borrowCollectionMapper.selectWaitFeelInterest(userId);
	}


	@Override
	public String selectFeelRepayTime(int userId) {
		return borrowCollectionMapper.selectFeelRepayTime(userId);
	}


	@Override
	public List<CollectionTenderUserVO> selectCollectionRecordListPage(PageSearch pageSearch) {
		return borrowCollectionMapper.selectCollectionRecordListPage(pageSearch);
	}


	@Override
	public List<CollectionTenderUserVO> selectFeelList(PageSearch pageSearch) {
		return borrowCollectionMapper.selectFeelList(pageSearch);
	}

	
}
