
package cn.vfunding.vfunding.biz.borrow.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.common.vo.RepaymentSituationVO;

public interface IBorrowRepaymentService {

	BorrowRepayment selectByPrimaryKey(Integer id);

	List<BorrowRepayment> selectByBorrowIdStatus(
			@Param("borrowId") Integer borrowId, @Param("status") Integer status);

	// 体验标还款列表
	List<BorrowRepayment> selectFeelByUserIdStatusListPage(PageSearch pageSearch);
	/**
	 *  招标中的体验标
	 * @return
	 * @author liuhuan
	 */
	List<BorrowRepayment> selectFeelBorrowingListPage(PageSearch pageSearch);
	/**
	 * @Description:借款人待还记录
	 * @param userId
	 * @param status
	 * @return
	 * @author liuhuan
	 */
	List<BorrowRepayment> selectByUserIdStatusListPage(PageSearch pageSearch);
	/**
	 * 总计数量
	 * 
	 * @param pageSearch
	 * @return
	 * @author liuhuan
	 */
	int countByUserIdStatus(PageSearch pageSearch);

	/**
	 * 根据userID查询历史借款的情况
	 * 
	 * @param userId
	 * @return author LiLei 2014年5月16日
	 */
	RepaymentSituationVO selectRepaymentSituationVO(Integer userId);

	/**
	 * 根据userID查询待还款信息
	 * 
	 * @param pagesearch
	 * @return author LiLei 2014年5月16日
	 */
	List<BorrowRepayment> selectWaitRepaymentListPage(PageSearch pagesearch);

	/**
	 * @Description:用户体验金待收本息
	 * @param parseInt
	 * @return
	 * @author liuhuan
	 */
	BigDecimal selectRepayAccountByUserId(int parseInt);
	
	/**
	 * 后台还款列表
	 * @author liuhuan
	 */
	List<BorrowRepayment> selectByRepaymentSystemListPage(PageSearch pageSearch);
	/**
	 * 根据借款人用户ID查询当天带还款标的
	 * @param userId
	 * @return
	 *
	 * jianwei
	 */
	List<BorrowRepayment> selectTodayRepayByUserId(Map<String, String> map);
}
