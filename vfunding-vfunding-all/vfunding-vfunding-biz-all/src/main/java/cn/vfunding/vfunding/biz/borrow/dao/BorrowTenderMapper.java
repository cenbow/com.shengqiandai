package cn.vfunding.vfunding.biz.borrow.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.model.UserInvestMoneyVO;
import cn.vfunding.vfunding.biz.cjdao.vo.CjdaoUserTenderVO;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.InvesVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderVO;

public interface BorrowTenderMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(BorrowTender record);

	int insertSelective(BorrowTender record);

	BorrowTender selectByPrimaryKey(Integer id);

	List<BorrowTender> selectTenderByBorrowId(Integer borrowId);

	/**
	 * 标的所有投资者
	 * 
	 * @param borrowId
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTender> selectListByBorrowId(Integer borrowId);
	
	/**
	 * 标的所有app投资者
	 * 
	 * @param borrowId
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTender> selectListByBorrowIdAndApp(Integer borrowId);

	/**
	 * @Description:根据相关参数查询集合
	 * @param tender
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTender> selectListByParam(PageSearch search);

	/**
	 * 体验了列表
	 * 
	 * @author liuhuan
	 */
	List<BorrowTenderVO> selectFeelList(PageSearch search);

	int updateByPrimaryKeySelective(BorrowTender record);

	int updateByPrimaryKey(BorrowTender record);

	/**
	 * 根据用户ID查询出该用户前指定天数内投资总额
	 * 
	 * @param userId
	 * @return
	 */
	Integer selectInvestMoneyByUserIdAndDays(UserInvestMoneyVO vo);

	/**
	 * 根据指定时间查询当天所有投资信息
	 * 
	 * @param date
	 *            yyyy-MM-dd格式字符串日期
	 * @return
	 */
	List<BorrowTender> selectByDate(String date);

	/**
	 * 根据用户id，标id查询投资情况
	 * 
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTender> selectListByUserIdBorrowId(
			@Param("userId") Integer userId, @Param("borrowId") Integer borrowId);

	/**
	 * 投资列表
	 * 
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTenderVO> selectInvestRecordListPage(PageSearch pageSearch);

	/**
	 * 我的好友投资记录
	 * 
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTender> selectListByMyFriend(PageSearch pageSearch);

	/**
	 * 昨日投资总金额
	 * 
	 * @return author LiLei 2014年5月29日
	 */
	BigDecimal selectYesterdaySumBorrowAccount();

	/**
	 * 根据userId查询投资次数
	 * 
	 * @param userId
	 * @return author LiLei 2014年6月9日
	 */
	Integer selectByUserId(Integer userId);

	/**
	 * 用户有效投标总额
	 * 
	 * @author liuhuan
	 */
	BigDecimal sumTenderAccountByUserId(Integer userId);

	/**
	 * 协议集合
	 * 
	 * @author liuhuan
	 */
	List<BorrowTenderVO> selectAgreementPage(Integer id);

	/**
	 * 查询用户投资记录
	 * 
	 * @param pageSearch
	 * @return lx
	 */
	List<InvesVO> selectByIdListPage(PageSearch pageSearch);

	/**
	 * @author liuhuan
	 */
	List<UserTenderVO> selectTendersGroupByUserId(Integer borrowId);

	/**
	 * 根据borrowID查询财经道的用户投资信息
	 */
	List<CjdaoUserTenderVO> selectUserTenderByBorrowId(Integer borrowId);

	/**
	 * 根据BorrowTender Parameters查询BorrowTender
	 */
	List<BorrowTender> selectByParameters(BorrowTender borrowTender);

	/**
	 * 查询该用户投资记录
	 * 
	 * wang.zeyan 2015年3月4日
	 * 
	 * @param userId
	 * @return
	 */
	List<BorrowTender> selectBrrowTenderByUserId(Integer userId);

	/**
	 * 查询该用户投资记录
	 * 
	 * louchen 2015年4月9日
	 * 
	 * @param userId
	 * @return
	 */
	List<BorrowTender> selectBrrowTenderAccountThanOneThousandByUserId(
			Integer userId);

	/**
	 * 查询该用户投资记录 根据 标状态
	 * 
	 * wang.zeyan 2015年3月5日
	 * 
	 * @param userId
	 * @return
	 */
	List<BorrowTender> selectByUserIdandTenderStatus(
			@Param("userId") Integer userId,
			@Param("tenderStatus") Integer tenderStatus);

	/**
	 * 用户是否有APP投资超过2000元
	 * 
	 * @param userId
	 * @return
	 * @author louchen 2015-03-24
	 */
	Integer hasAppTenderThanTwoThousand(@Param("userId") Integer userId);

	/**
	 * 2015 四月活动个人投资总额
	 * 
	 * @param userId
	 * @return
	 * @author louchen 2015-03-30
	 */
	BigDecimal myTotalTenderAccountForApril(@Param("userId") Integer userId);

	/**
	 * 活动期间个人总投资额
	 *
	 * wang.zeyan 2015年4月24日
	 * 
	 * @return
	 */
	BigDecimal activityTimeTotalInvest(@Param("userId") Integer userId,
			@Param("startDate") String startDate,
			@Param("endDate") String endDate);

}