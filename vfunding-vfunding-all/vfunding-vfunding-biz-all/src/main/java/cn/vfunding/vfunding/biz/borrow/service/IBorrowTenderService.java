package cn.vfunding.vfunding.biz.borrow.service;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.model.UserInvestMoneyVO;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.InvesVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderVO;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;
import cn.vfunding.vfunding.common.module.activemq.message.InvestMessage;
import cn.vfunding.vfunding.common.module.activemq.message.useraction.UserTenderMessage;

public interface IBorrowTenderService {
	int deleteByPrimaryKey(Integer id);

	int insert(BorrowTender record);

	int insertSelective(BorrowTender record);

	BorrowTender selectByPrimaryKey(Integer id);

	/**
	 * 标的所有投资者(用于标的详情页显示)
	 * 
	 * @param borrowId
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTender> selectTenderByBorrowId(Integer borrowId);

	/**
	 * 标的所有投资者
	 * 
	 * @param borrowId
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTender> selectListByBorrowId(Integer borrowId);

	List<BorrowTender> selectListByParam(PageSearch search);

	List<BorrowTenderVO> selectFeelList(PageSearch search);

	/**
	 * 根据用户id，标id查询投资情况
	 * 
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTender> selectListByUserIdBorrowId(Integer userId,
			Integer borrowId);

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
	 * php端在投资成功后调用的接口
	 */
	void afterPhpTender(InvestMessage message);

	/**
	 * 用户投资核心业务
	 * 
	 * @param vo
	 * @return 2014年12月29日 liuyijun
	 */
	@NeedLock
	// @NeedAfterInterceptor("borrowTender")
	@AfterAction(actionName = "borrowTender")
	UserTenderActionResultVO userTenderAction(UserTenderActionVO vo);

	/**
	 * 用户投资辅助业务
	 * 
	 * @param vo
	 * @return 2014年12月29日 liuyijun
	 */
	void userTenderAfter(UserTenderActionResultVO vo);

	/**
	 * @Description:投资列表
	 * @param pageSearch
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTenderVO> selectInvestRecordListPage(PageSearch pageSearch);

	/**
	 * 昨日投资总金额
	 * 
	 * @return author LiLei 2014年5月29日
	 */
	BigDecimal selectYesterdaySumBorrowAccount();

	/**
	 * 我的好友
	 * 
	 * @return
	 * @author liuhuan
	 */
	List<BorrowTender> selectListByMyFriend(PageSearch pageSearch);

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
	 * 新手第一次投资添加系统消息
	 */
	void insertSystemMessage(UserTenderMessage utm);
	
	/**
	 * 查询该用户投资记录
	 * 
	 * wang.zeyan 2015年3月4日
	 * @param userId
	 * @return
	 */
	List<BorrowTender> selectBrrowTenderByUserId(Integer userId);
	
	/**
	 * 查询该用户投资记录 根据 标状态
	 * wang.zeyan 2015年3月5日
	 * @param userId
	 * @return
	 */
	List<BorrowTender> selectTenderStatusByUserId(Integer userId , Integer tenderStatus);
	
	/**
	 * 用户是否有APP投资超过2000元
	 * @param userId
	 * @return
	 */
	Integer hasAppTenderThanTwoThousand(Integer userId);
	
	/**
	 * 2015 四月活动个人投资总额
	 * @param userId
	 * @return
	 * @author louchen 2015-03-30
	 */
	BigDecimal myTotalTenderAccountForApril(Integer userId);
	
}
