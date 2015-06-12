package cn.vfunding.vfunding.biz.borrow.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowRepaymentMapper;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.common.vo.RepaymentSituationVO;
@Service("borrowRepaymentService")
public class BorrowRepaymentServiceImpl implements IBorrowRepaymentService {

	@Autowired
	private BorrowRepaymentMapper borrowRepaymentMapper;

	@Override
	public BorrowRepayment selectByPrimaryKey(Integer id) {
		return borrowRepaymentMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<BorrowRepayment> selectFeelByUserIdStatusListPage(
			PageSearch pageSearch) {
		return borrowRepaymentMapper
				.selectFeelByUserIdStatusListPage(pageSearch);
	}
	@Override
	public List<BorrowRepayment> selectFeelBorrowingListPage(
			PageSearch pageSearch) {
		return borrowRepaymentMapper
				.selectFeelBorrowingListPage(pageSearch);
	}

	/**
	 * 根据order升序排列
	 */
	@Override
	public List<BorrowRepayment> selectByBorrowIdStatus(Integer borrowId,
			Integer status) {
		return borrowRepaymentMapper.selectByBorrowIdStatus(borrowId, status);
	}

	@Override
	public List<BorrowRepayment> selectByUserIdStatusListPage(
			PageSearch pageSearch) {
		return borrowRepaymentMapper.selectByUserIdStatusListPage(pageSearch);
	}

	@Override
	public int countByUserIdStatus(PageSearch pageSearch) {
		return borrowRepaymentMapper.countByUserIdStatus(pageSearch);
	}

	/**
	 * 查询用户还款详情
	 * 
	 * @param userId
	 * @return author LiLei 2014年5月16日
	 */
	@Override
	public RepaymentSituationVO selectRepaymentSituationVO(Integer userId) {
		RepaymentSituationVO repayStituation = new RepaymentSituationVO();

		List<RepaymentSituationVO> list = this.borrowRepaymentMapper
				.selectSumRepayment(userId);
		for (RepaymentSituationVO vo : list) {
			if (vo.getStatus() == 0) {
				repayStituation.setWaitRayment(vo.getSuccessBorrow());
			} else if (vo.getStatus() == 1) {
				repayStituation.setSuccessRayment(vo.getSuccessBorrow());
			} else if (vo.getStatus() == 2) {
				repayStituation.setLoseRayment(vo.getSuccessBorrow());
			}
		}
		repayStituation.setSuccessBorrow(repayStituation.getSuccessRayment()
				+ repayStituation.getWaitRayment());
		Integer advanceRayment = this.borrowRepaymentMapper
				.selectaDvanceRayment(userId);
		Integer overdueRaymentLittle = this.borrowRepaymentMapper
				.selectaOverdueRaymentLittle(userId);
		Integer overdueRaymentmore = this.borrowRepaymentMapper
				.selectaOverdueRaymentmore(userId);
		Integer overdueRaymentNo = this.borrowRepaymentMapper
				.selectOverdueRaymentNo(userId);
		repayStituation.setAdvanceRayment(advanceRayment);
		repayStituation.setOverdueRaymentLittle(overdueRaymentLittle);
		repayStituation.setOverdueRaymentmore(overdueRaymentmore);
		repayStituation.setOverdueRaymentNo(overdueRaymentNo);
		return repayStituation;
	}
	/**
	 * 根据userID查询待还款信息
	 * 
	 * @param pagesearch
	 * @return author LiLei 2014年5月16日
	 */
	@Override
	public List<BorrowRepayment> selectWaitRepaymentListPage(
			PageSearch pagesearch) {
		return this.borrowRepaymentMapper
				.selectWaitRepaymentListPage(pagesearch);
	}
	/**
	 * @Description:用户体验金待收本息
	 * @param parseInt
	 * @return
	 * @author liuhuan
	 */
	@Override
	public BigDecimal selectRepayAccountByUserId(int userId) {
		return this.borrowRepaymentMapper.selectRepayAccountByUserId(userId);
	}

	/**
	 * 后台还款列表
	 * @author liuhuan
	 */
	@Override
	public List<BorrowRepayment> selectByRepaymentSystemListPage(PageSearch pageSearch) {
		return this.borrowRepaymentMapper.selectByRepaymentSystemListPage(pageSearch);
	}

	@Override
	public List<BorrowRepayment> selectTodayRepayByUserId(Map<String, String> map) {
		return this.borrowRepaymentMapper.selectTodayRepayByUserId(map);
	}
	
}
