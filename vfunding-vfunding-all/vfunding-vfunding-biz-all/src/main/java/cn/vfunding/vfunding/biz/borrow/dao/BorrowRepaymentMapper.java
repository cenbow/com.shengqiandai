package cn.vfunding.vfunding.biz.borrow.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.cjdao.vo.CjdaoBorrowRepaymentVO;
import cn.vfunding.vfunding.biz.common.vo.RepaymentSituationVO;

public interface BorrowRepaymentMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(BorrowRepayment record);

	int insertSelective(BorrowRepayment record);

	BorrowRepayment selectByPrimaryKey(Integer id);

	/**
	 * 根据order升序排列
	 */
	List<BorrowRepayment> selectByBorrowIdStatus(@Param("borrowId") Integer borrowId, @Param("status") Integer status);

	/**
	 * @Description:借款人待还记录
	 * @param userId
	 * @param status
	 * @return
	 * @author liuhuan
	 */
	List<BorrowRepayment> selectByUserIdStatusListPage(PageSearch pageSearch);

	int countByUserIdStatus(PageSearch pageSearch);

	int updateByPrimaryKeySelective(BorrowRepayment record);

	int updateByPrimaryKey(BorrowRepayment record);

	List<BorrowRepayment> selectFeelByUserIdStatusListPage(PageSearch pageSearch);

	/**
	 * 招标中的体验标
	 * 
	 * @return
	 * @author liuhuan
	 */
	List<BorrowRepayment> selectFeelBorrowingListPage(PageSearch pageSearch);

	/**
	 * 后台还款列表
	 * 
	 * @author liuhuan
	 */
	List<BorrowRepayment> selectByRepaymentSystemListPage(PageSearch pageSearch);

	/**
	 * 逾期未还款
	 * 
	 * @param userId
	 * @return author LiLei 2014年5月16日
	 */
	Integer selectOverdueRaymentNo(Integer userId);

	/**
	 * 逾期大于30天
	 * 
	 * @param userId
	 * @return author LiLei 2014年5月16日
	 */
	Integer selectaOverdueRaymentmore(Integer userId);

	/**
	 * 逾期小于30天
	 * 
	 * @param userId
	 * @return author LiLei 2014年5月16日
	 */
	Integer selectaOverdueRaymentLittle(Integer userId);

	/**
	 * 提前还款
	 * 
	 * @param userId
	 * @return author LiLei 2014年5月16日
	 */
	Integer selectaDvanceRayment(Integer userId);

	/**
	 * 成功 流标 待还
	 * 
	 * @param userId
	 * @return author LiLei 2014年5月16日
	 */
	List<RepaymentSituationVO> selectSumRepayment(Integer userId);

	/**
	 * 借款人待还信息
	 * 
	 * @param userId
	 * @return author LiLei 2014年5月16日
	 */
	List<BorrowRepayment> selectWaitRepaymentListPage(PageSearch pageSearch);

	/**
	 * @Description:用户体验金待收本息
	 * @param parseInt
	 * @return
	 * @author liuhuan
	 */
	BigDecimal selectRepayAccountByUserId(int userId);

	/**
	 * n 天后需要还款的标
	 * 
	 * @author liuhuan
	 */
	List<BorrowRepayment> selectWillRepayBorrow(int days);
	
	/**
	 * 查询逾期标的
	 * @return
	 */
	List<BorrowRepayment> selectOverdueBorrow();

	/**
	 * 查询财经道用户的还款信息
	 * @param repaymentId
	 * @return
	 */
	List<CjdaoBorrowRepaymentVO> selectCjdaoUserRepayment(Integer repaymentId);
	
	/**
	 * 根据借款人用户ID查询当天带还款标的
	 * @param userId
	 * @return
	 *
	 * jianwei
	 */
	List<BorrowRepayment> selectTodayRepayByUserId(Map<String, String> map);
	
	/**
	 * 所有逾期的还款
	 * @return
	 * @author louchen
	 * @date 2015-05-11
	 */
	List<BorrowRepayment> selectLateBorrowRepayment();
	
	/**
	 * 和当前时间比较相差多少天
	 * @return
	 */
	Integer selectCurrentDateDiff(@Param("time") Integer time); 
}