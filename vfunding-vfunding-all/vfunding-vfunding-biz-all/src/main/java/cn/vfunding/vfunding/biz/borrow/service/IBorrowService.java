package cn.vfunding.vfunding.biz.borrow.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;
import cn.vfunding.vfunding.biz.borrow.model.MortgageType;
import cn.vfunding.vfunding.biz.common.vo.BorrowVO;
import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.common.vo.ReleaseBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.SuccessTenderVO;
import cn.vfunding.vfunding.biz.common.vo.TenderBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.TheTrialBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.VerifyBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.VerifyVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IBorrowService {

	Borrow selectById(Integer id);

	/**
	 * @Description:首页显示6条标
	 * @author liuhuan
	 */
	List<Borrow> selectBorrowForIndex(PageSearch pageSearch, Integer feelRows);

	int insert(Borrow borrow);

	int insertSelective(Borrow record);
	
	/**
	 * 预初审
	 * @author liuhuan
	 */
	int updateSaveBorrowForm(Borrow borrow, MortgageCar car);
	
	/**
	 * @Description:初审 admin 后台审核人员
	 * @author liuhuan
	 */
	//@NeedAfterInterceptor("borrowExamine")
	@AfterAction(actionName="borrowExamine")
	int updateVerifyBorrow(Borrow borrow, Integer userId, InvestorsFee investorsFee,MortgageCar car, String ip);
	/**
	 * 后台借款管理
	 * @author liuhuan
	 */
	List<VerifyBorrowVO> selectBorrowSystemListPage(PageSearch pageSearch);
	/**
	 * @Description:体验标--投标
	 * @param borrow
	 * @param accountFeel
	 * @param account
	 *            有效投标金额
	 * @param ip
	 * @return 投标结果字符串
	 * @author liuhuan
	 */
	@NeedLock
	SuccessTenderVO updateTenderFeelBorrow(TenderBorrowVO tenderBorrowvo,
			String ip);

	/**
	 * @Description: 体验标--复审
	 * @param remark
	 * @param status
	 *            30：满标复审通过,40:复审不通过 ;
	 * @param ip
	 * @return 审核结果字符串
	 * @author liuhuan
	 */
	@NeedLock
	String updateFinalVerifyFeelBorrow(VerifyVO verifyvo);
	/**
	 * 真实标---复审
	 * borrowVerify
	 * @return
	 * @author liuhuan
	 */
	@NeedLock
	@AfterAction(actionName="borrowVerify")
	String updateFinalVerifyBorrow(FinalVerifyVO finalVerify);
	
	/**
	 * 天标----复审
	 * @param finalVerify
	 * @return
	 */
	@NeedLock
	@AfterAction(actionName="borrowVerify")
	String updateFinalVerifyDayBorrow(FinalVerifyVO finalVerify);
	
	void updateFinalVerifyBorrowAfter(FinalVerifyVO finalVerify);
	/**
	 * @Description:体验标--还款
	 * @param borrow
	 * @param ip
	 * @return
	 * @author liuhuan
	 */
	@NeedLock("/locks/pepayFeelBorrow")
	//@NeedAfterInterceptor("pepayFeelBorrow")
	@AfterAction(actionName="pepayFeelBorrow")
	String updateRepayFeelBorrow(String repaymentId, Integer userId, String ip);

	/**
	 * @Description: 还款
	 * @param borrowRepayment
	 *            还款的期数
	 * @param ip
	 * @return
	 * @author liuhuan
	 */
	@NeedLock
	//@NeedAfterInterceptor("pepayBorrow")
	@AfterAction(actionName="pepayBorrow")
	String updateRepayBorrow(String repaymentId, String ip);

	/**
	 * 根据用户ID分页查询
	 * 
	 * @param pageSearch
	 * @return 2014年4月29日 liuyijun
	 */
	List<Borrow> selectByUserIdListPage(PageSearch pageSearch);

	/**
	 * 后台撤标
	 * 
	 * @param borrow
	 * @author liuhuan
	 */
	@NeedLock
	String updateFailBorrow(Borrow borrow, Integer status, String ip);
	/**
	 * 流标
	 * 
	 * @return
	 * @author liuhuan
	 */
	@NeedLock
	void updateFlowBorrow();
	
	/**
	 * 定时 还款短信\邮件提醒
	 * @author liuhuan
	 */
	void updateSendWillRepayBorrow();

	/**
	 * 根据borrowID查询borrow的详情
	 * 
	 * @param borrowId
	 * @return author LiLei 2014年5月12日
	 */
	Borrow selectBorrowById(Integer borrowId);

	/**
	 * @Description:普通标-发标
	 * @param user
	 * @param borrow
	 * @param mortgagetype
	 * @param mortgageCar
	 * @return
	 * @author liuhuan
	 */
	@NeedLock
	MessageVO insertNewBorrow(UserSession user, Borrow borrow, BorrowVO bvo,
			MortgageType mortgagetype, MortgageCar mortgageCar,
			HttpServletRequest request) throws IOException;
	
	@NeedLock
	MessageVO insert8jieBorrow(UserSession user, Borrow borrow, BorrowVO bvo,
			MortgageType mortgagetype, MortgageCar mortgageCar,
			HttpServletRequest request,String card_pic1,String card_pic2,String car_pic1,
			String other3,String other2,String other1, String carcard_pic3,
			String car_pic2,String car_pic3,String car_pic4,String carcard_pic1,String carcard_pic2) throws IOException;

	/**
	 * @Description:发布体验标并初审
	 * @param borrow
	 * @param investorsFee
	 * @return
	 * @author liuhuan
	 */
	String insertFeelBorrow(Borrow borrow, Integer userId, String ip);

	/**
	 * 标的相关图片
	 * 
	 * @param pic
	 * @return author LiLei 2014年5月17日
	 */
	List<MortgageCarPic> selectBorrowPic(MortgageCarPic pic);

	List<Borrow> selectFeelBorrowForIndex();

	BigDecimal selectSumBorrowAccount(Integer userId);

	/**
	 * 根据userID查询借款信息
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年5月19日
	 */

	List<Borrow> selectBorrowByUserIdListPage(PageSearch pageSearch);

	/**
	 * 发标页面，详情查询
	 * 
	 * @author liuhuan
	 */
	ReleaseBorrowVO selectBorrowForRelease(int borrowId);

	/**
	 * borrowList界面 ,查询所有
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年5月26日
	 */
	List<Borrow> selectAllBorrowListPage(PageSearch pageSearch);

	/**
	 * 后台复审
	 * 
	 * @author liuhuan
	 */
	List<VerifyBorrowVO> selectFinalBorrowListPage(PageSearch s);

	/**
	 * 后台初审列表
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年6月5日
	 */
	List<TheTrialBorrowVO> selectTheTrialListPage(PageSearch pageSearch);
	
	/**
	 * 后台撤标
	 * @author liuhuan
	 */
	List<VerifyBorrowVO> failBorrowListPage(PageSearch pageSearch);
	/**
	 * 修改合同时间
	 * @param record
	 * @return
	 * @author liuxuan
	 */
	int updateByPrimaryKeySelective(Borrow record);

	/**
	 * 还款成功 后置发短信
	 * @author lilei
	 * @param repaymentId
	 * 2015年1月23日
	 */
	void updateRepayBorrowAfter(Integer repaymentId);
	void updateRepayDayBorrowAfter(Integer repaymentId);

	/**
	 * 天标还款
	 * @param string
	 * @param remoteAddr
	 * @return
	 */
	@NeedLock
	String updateRepayDayBorrow(String string, String remoteAddr);

	
	/**
	 * sqd添加产品
	 * @author huangyuancheng
	 */
	public int addSqdProduct(Borrow borrow);
}
