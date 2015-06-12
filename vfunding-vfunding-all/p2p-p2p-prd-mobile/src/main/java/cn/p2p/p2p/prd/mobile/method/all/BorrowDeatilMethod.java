package cn.p2p.p2p.prd.mobile.method.all;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.method.vo.BorrowDetailVO;
import cn.p2p.p2p.prd.mobile.method.vo.BorrowLoanVO;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelLogService;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowCollectionService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.borrow.service.IMortgageCarPicService;
import cn.vfunding.vfunding.biz.borrow.service.IMortgageCarService;
import cn.vfunding.vfunding.biz.borrowMobile.dao.BorrowTenderMobileMapper;
import cn.vfunding.vfunding.biz.borrowMobile.service.IBorrowTenderMobileService;
import cn.vfunding.vfunding.biz.common.vo.RepaymentSituationVO;
import cn.vfunding.vfunding.biz.credit.service.ICreditService;
import cn.vfunding.vfunding.biz.system.service.IAllStatisticsService;
import cn.vfunding.vfunding.biz.system.service.IAreaService;
import cn.vfunding.vfunding.biz.system.service.IInvestorsFeeService;
import cn.vfunding.vfunding.biz.system.service.ILinkageService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserAmountService;
import cn.vfunding.vfunding.biz.user.service.IUserInfoService;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Service
public class BorrowDeatilMethod {
	@Autowired
	private IBorrowService borrowService;
	@Autowired
	private IBorrowTenderService borrowTenderService;
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;
	@Autowired
	private IBorrowCollectionService borrowCollectionService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountFeelService accountFeelService;
	@Autowired
	private IAccountFeelLogService accountFeelLogService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IInvestorsFeeService investorsFeeService;
	@Autowired
	private ICreditService creditService;
	@Autowired
	private IAllStatisticsService allStatisticsService;
	@Autowired
	private BorrowTenderMobileMapper btmMapper;
	@Autowired
	private IBorrowTenderMobileService borrowTenderMobileService;
	@Autowired
	private IAreaService areaService;
	@Autowired
	private IMortgageCarService mortgageCarService;
	@Autowired
	private IMortgageCarPicService mortgageCarPicService;
	@Autowired
	private IUserAmountService userAmountService;

	@Autowired
	private ILinkageService linkageService;
	

	public MobileBaseResponse borrowBaseDetail(GeneralRequestVO generalRequest) {
		if (generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("borrowId_fail", "标的编号不可为空或负数");
		// 借款基本详情
		Borrow borrow = this.borrowService.selectBorrowById(generalRequest.getId());
		if (borrow == null)
			return new MobileBaseResponse("borrow_null_fail", "该标的不存在");
		Borrow borrowAll = this.borrowService.selectById(generalRequest.getId());
		// 此标的投资信息
		Integer tenderCount = this.btmMapper.selectTenderBorrowByIdCount(generalRequest.getId());
		// 发标人的个人信息
		Integer borrowUserId = borrowAll.getUserId();// 发标人的userID
		User user = this.userService.selectByPrimaryKey(borrowUserId);
		return new MobileBaseResponse(this.setBdValue(borrow, borrowAll, tenderCount, user));
	}

	
	private BorrowDetailVO setBdValue(Borrow borrow,Borrow borrowAll,Integer tenderCount,User user) {
		BorrowDetailVO bdVo = new BorrowDetailVO();
		DecimalFormat df = new DecimalFormat("0.00");
		bdVo.setAccount(borrow.getAccountStr());
		bdVo.setExpectApr(df.format(borrow.getExpectApr()));
		bdVo.setIsday(String.valueOf(borrow.getIsday()));
		bdVo.setTimeLimit(String.valueOf(borrow.getTimeLimit()));
		bdVo.setTimeLimitDay(String.valueOf(borrow.getTimeLimitDay()));
		bdVo.setAccountYes(df.format(borrow.getAccountYes()));
		bdVo.setStyle(String.valueOf(borrow.getStyle()));
		bdVo.setTenderEndtime(borrow.getTenderEndtime());
		bdVo.setAllBorrowIsRepay(String.valueOf(borrowAll.getBorrowIsRepay()));
		bdVo.setAllStatus(String.valueOf(borrowAll.getStatus()));
		bdVo.setUsername(user.getUsername());
		bdVo.setTenderCount(String.valueOf(tenderCount));
		return bdVo;
	}
	
	
	
	
	public MobileBaseResponse borrowLoanDetail(GeneralRequestVO generalRequest) {
		Borrow borrowAll = this.borrowService.selectById(generalRequest.getId());
		if (borrowAll == null)
			return new MobileBaseResponse("borrow_null_fail", "标的信息不存在");
		// 车主信息
		MortgageCar mortgageCar = mortgageCarService.selectByPrimaryKey(borrowAll.getMortgageId());
		if (mortgageCar == null)
			return new MobileBaseResponse("mortgage_null_fail", "车主信息不存在");
		return new MobileBaseResponse(this.setBlValue(borrowAll, mortgageCar));
	}
	
	private BorrowLoanVO setBlValue(Borrow borrowAll,MortgageCar mortgageCar) {
		BorrowLoanVO responseObject = new BorrowLoanVO();
		DecimalFormat df = new DecimalFormat("0.00");
		responseObject.setBrand(mortgageCar.getBrand());
		responseObject.setBuyMoney(df.format(mortgageCar.getBuyMoney()));
		responseObject.setAssessMoney(df.format(mortgageCar.getAssessMoney()));
		responseObject.setVerifyTimeStr(borrowAll.getVerifyTimeStr());
		return responseObject;
	}



	public MobileBaseResponse borrowCreditDetail(GeneralRequestVO generalRequest) {
		Borrow borrowAll = this.borrowService.selectById(generalRequest.getId());
		if (borrowAll == null)
			return new MobileBaseResponse("borrow_null_fail", "标的信息不存在");
		// 查询用户还款详情
		RepaymentSituationVO repaymentSituation = this.borrowRepaymentService.selectRepaymentSituationVO(borrowAll.getUserId());
		if (repaymentSituation == null)
			return new MobileBaseResponse("credit_null_fail", "借款人信用信息不存在");
		return new MobileBaseResponse(repaymentSituation);
	}

	public MobileBaseResponse borrowInvestDetail(GeneralRequestVO generalRequest) {
		// 根据borrowID查询投资详细
		List<BorrowTender> tenderList = this.borrowTenderService.selectTenderByBorrowId(generalRequest.getId());
		if(tenderList.isEmpty())
			return new MobileBaseResponse("tenderlist_null","暂无投资人");
		return new MobileBaseResponse(tenderList);
		
	}
	
	/**
	 * 抵押汽车图片/借款人身份证图片
	 * @param id  标的ID
	 * @param status 0 身份证图片  2 汽车图片
	 * @return
	 *
	 * 2015年6月5日
	 * lijianwei
	 */
	public MobileBaseResponse borrowCarOrCardPic(GeneralRequestVO generalRequest){
		if(generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("id_fail","标的ID错误");
		if(generalRequest.getStatus() == null)
			return new MobileBaseResponse("type_fail","查询图片类型不可为空");
		Borrow borrow = this.borrowService.selectBorrowById(generalRequest.getId());
		Map<String, Object> map = null;
		if(generalRequest.getStatus() == 0){
			map = new HashMap<String, Object>();
			map.put("borrow", borrow);
		}else if(generalRequest.getStatus() == 2){
			map = new HashMap<String, Object>();
			MortgageCar car = this.mortgageCarService.selectByPrimaryKey(borrow.getMortgageId());
			map.put("car", car);
		}
		MortgageCarPic pic = new MortgageCarPic();
		pic.setCarId(borrow.getMortgageId());
		pic.setType(generalRequest.getStatus()+"");
		List<MortgageCarPic> pics = this.mortgageCarPicService.selectBorrowPicList(pic);
		map.put("pics", pics);
		return new MobileBaseResponse(map);
	}
	
}
