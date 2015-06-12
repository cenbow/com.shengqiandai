package cn.vfunding.vfunding.prd.www.borrow.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.ConverterUtil;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelLogService;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowCollection;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRule;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;
import cn.vfunding.vfunding.biz.borrow.model.MortgageType;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowCollectionService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRuleService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.borrow.service.IMortgageCarPicService;
import cn.vfunding.vfunding.biz.borrow.service.IMortgageCarService;
import cn.vfunding.vfunding.biz.borrowMobile.dao.BorrowTenderMobileMapper;
import cn.vfunding.vfunding.biz.borrowMobile.service.IBorrowTenderMobileService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.BorrowVO;
import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.common.vo.ProfitCalJsonVO;
import cn.vfunding.vfunding.biz.common.vo.ProfitCalVO;
import cn.vfunding.vfunding.biz.common.vo.QueryParameterVO;
import cn.vfunding.vfunding.biz.common.vo.ReleaseBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.RepaymentSituationVO;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.biz.common.vo.SuccessTenderVO;
import cn.vfunding.vfunding.biz.common.vo.TenderBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.VerifyVO;
import cn.vfunding.vfunding.biz.credit.service.ICreditService;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.system.model.Area;
import cn.vfunding.vfunding.biz.system.model.Fee;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
import cn.vfunding.vfunding.biz.system.service.IAllStatisticsService;
import cn.vfunding.vfunding.biz.system.service.IAreaService;
import cn.vfunding.vfunding.biz.system.service.IFeeService;
import cn.vfunding.vfunding.biz.system.service.IInvestorsFeeService;
import cn.vfunding.vfunding.biz.system.service.ILinkageService;
import cn.vfunding.vfunding.biz.system.service.IProfitCalService;
import cn.vfunding.vfunding.biz.thirdplat.service.ICashVolumeService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserAmount;
import cn.vfunding.vfunding.biz.user.model.UserInfo;
import cn.vfunding.vfunding.biz.user.service.IUserAmountService;
import cn.vfunding.vfunding.biz.user.service.IUserInfoService;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Controller
@RequestMapping("borrow")
public class BorrowController extends BaseController {

	@Autowired
	private IBorrowRuleService borrowRuleService;

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
	private ICashVolumeService cashVolumeService;

	@Autowired
	private HikesCardMapper hikesCardMapper;

	@Autowired
	private ILinkageService linkageService;
	@Autowired
	private IQuerySinaService querySinaService;
	
	@Autowired
	private IFeeService feeService;
	
	@Autowired
	private IProfitCalService profitCalService;

	/**
	 * @Description:金额大小写转换
	 * @param account
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping(value = "getMoneyUppercase")
	@ResponseBody
	public Json getMoneyUppercase(@RequestParam("account") String account) {
		Json j = new Json();
		if (!EmptyUtil.isEmpty(account)) {
			j.setMsg(ConverterUtil.digitUppercase(Double.parseDouble(account)));
		}
		return j;
	}

	/**
	 * @Description:二次发标 status : 2-更新；4-重发
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "")
	@RequestMapping(value = "/handleBorrow/{borrowId}")
	public ModelAndView handleBorrow(@PathVariable("borrowId") int borrowId,
			@RequestParam("status") int status) {
		ModelAndView mv = new ModelAndView("borrow/releaseBorrow");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);// 账户信息
		ReleaseBorrowVO borrowvo = borrowService
				.selectBorrowForRelease(borrowId);
		borrowvo.setBigAccount(ConverterUtil.digitUppercase(borrowvo
				.getAccount().doubleValue()));
		MortgageCar mortgageCar = mortgageCarService
				.selectByPrimaryKey(borrowvo.getMortgageId());

		if (status == 2) { // 更新标才显示图片
			UserAmount amount = new UserAmount();
			amount.setUserId(UserSession.getLoginUserId());
			amount = userAmountService.selectByParam(amount).get(0);
			MortgageCarPic p = new MortgageCarPic();
			p.setCarId(mortgageCar.getId());
			p.setType("2");
			List<MortgageCarPic> carpic = mortgageCarPicService
					.selectBorrowPicList(p);// 车的详细图片4张
			p.setType("3");
			List<MortgageCarPic> carcardpic = mortgageCarPicService
					.selectBorrowPicList(p);// 行驶证的详细图片2张
			p.setType("4");
			List<MortgageCarPic> other = mortgageCarPicService
					.selectBorrowPicList(p);// 其他3张
			p.setType("0");
			List<MortgageCarPic> cardpic1 = mortgageCarPicService
					.selectBorrowPicList(p);// 身份证正面
			p.setType("1");
			List<MortgageCarPic> cardpic2 = mortgageCarPicService
					.selectBorrowPicList(p);// 身份证反面
			List<Area> areas = areaService.selectByParentId(0); // 获取籍贯
			mv.addObject("areas", areas);
			mv.addObject("carpic", carpic);
			mv.addObject("carcardpic", carcardpic);
			mv.addObject("other", other);
			mv.addObject("cardpic1", cardpic1);
			mv.addObject("cardpic2", cardpic2);
			mv.addObject("amount", amount);
		}

		borrowvo.setHandleType(status);// 2-更新；4-重发
		mv.addObject("mortgageCar", mortgageCar);
		mv.addObject("borrowvo", borrowvo);
		return mv;
	}

	/**
	 * @Description:转向发标页面
	 * @param request
	 * @return
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "/borrow/releaseBorrow")
	@RequestMapping(value = "releaseBorrow")
	public ModelAndView releaseBorrow(HttpServletRequest request) {
		// 借款额度
		UserAmount amount = new UserAmount();
		amount.setUserId(UserSession.getLoginUserId());
		amount = userAmountService.selectByParam(amount).get(0);
		if (amount.getCredit().intValue() == 0
				&& amount.getTenderVouch().intValue() == 0
				&& amount.getBorrowVouch().intValue() == 0) {
			return new ModelAndView("redirect:/userAmount/toApplyOnlinePage");
		}
		ModelAndView mv = new ModelAndView("borrow/releaseBorrow");
		List<Area> areas = areaService.selectByParentId(0); // 获取籍贯
		ModelAndViewUtil.addAccountToView(mv, this.accountService);// 账户信息
		mv.addObject("areas", areas);
		mv.addObject("amount", amount);
		return mv;
	}

	@NeedSession(returnUrl = "/borrow/releaseBorrow")
	@RequestMapping(value = "/finishBorrow")
	public ModelAndView finishBorrow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("borrow/releaseBorrow");
		String borrowId = request.getParameter("id");
		String msg = request.getParameter("msg");
		BorrowVO bvo = (BorrowVO) request.getAttribute("bvo");
		try {
			if (!EmptyUtil.isEmpty(borrowId)) {
				UserAmount record = new UserAmount();
				record.setUserId(UserSession.getLoginUserId());
				UserAmount amount = userAmountService.selectByParam(record)
						.get(0);
				ReleaseBorrowVO borrowvo = borrowService
						.selectBorrowForRelease(Integer.parseInt(URLDecoder
								.decode(borrowId, "utf-8")));
				MortgageCar mortgageCar = mortgageCarService
						.selectByPrimaryKey(borrowvo.getMortgageId());
				mv.addObject("borrowvo", borrowvo);
				mv.addObject("mortgageCar", mortgageCar);
				mv.addObject("bvo", bvo);
				mv.addObject("amount", amount);
			}
			mv.addObject("msg", URLDecoder.decode(msg, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * @Description:发标普通标
	 * @param request
	 * @param borrow
	 * @return
	 * @author liuhuan
	 * @throws IOException
	 */
	@NeedSession(returnUrl = "borrow/releaseBorrow")
	@RequestMapping(value = "/toReleaseBorrow", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView toReleaseBorrow(HttpServletRequest request,
			Borrow borrow, MultipartHttpServletRequest multipartRequest,
			BorrowVO bvo, MortgageCar mortgageCar) throws IOException {
		ModelAndView mv = new ModelAndView("redirect:/borrow/finishBorrow");
		UserSession user = UserSession.getUserSession();
		String msg = "";
		mv.addObject("borrow", borrow);
		if (EmptyUtil.isEmpty(borrow.getAccount())) {
			msg = "借款金额不能为空";
		} else if (EmptyUtil.isEmpty(borrow.getApr())) {
			msg = "借款年利率不能为空";
		} else if (bvo.getTypeName() == null) {
			msg = "抵押品种不能为空";
		} else if (bvo.getTypeName().equals("债权抵押")
				&& EmptyUtil.isEmpty(bvo.getContract_End())
				&& EmptyUtil.isEmpty(bvo.getContract_Start())) {
			msg = "合同时间不能为空";
		} else if (borrow.getTimeLimit() == -1) {
			msg = "借款期限不能为空";
		} else if (borrow.getValidTime() == -1) {
			msg = "有效时间不能为空";
		} else if (borrow.getStyle() == -1) {
			msg = "还款方式不能为空";
		} else if (borrow.getDanbao() == -1) {
			msg = "是否担保不能为空";
		} else if (borrow.getLowestAccount().intValue() == -1) {
			msg = "最低投标金额不能为空";
		} else if (EmptyUtil.isEmpty(borrow.getName())) {
			msg = "借款标题不能为空";
		} else if (EmptyUtil.isEmpty(borrow.getContent())) {
			msg = "借款描述不能为空";
		} else if (EmptyUtil.isEmpty(mortgageCar.getOwnerName())) {
			msg = "车主姓名不能为空";
		} else if (EmptyUtil.isEmpty(mortgageCar.getOwnerCardid())) {
			msg = "车主身份证不能为空";
		} else if (EmptyUtil.isEmpty(mortgageCar.getOwnerMobile())) {
			msg = "车主手机号不能为空";
		} else if (EmptyUtil.isEmpty(mortgageCar.getOwnerAddress())) {
			msg = "车主籍贯不能为空";
		} else if (EmptyUtil.isEmpty(mortgageCar.getCarNumber())) {
			msg = "车辆识别代号不能为空";
		} else if (EmptyUtil.isEmpty(mortgageCar.getCarLicense())) {
			msg = "车牌号不能为空";
		} else if (EmptyUtil.isEmpty(mortgageCar.getCarNumber())) {
			msg = "车发动机号不能为空";
		} else if (EmptyUtil.isEmpty(bvo.getRegister_Date())) {
			msg = "注册日期不能为空";
		} else if (EmptyUtil.isEmpty(bvo.getCertification_Date())) {
			msg = "发证日期不能为空";
		} else if (EmptyUtil.isEmpty(bvo.getCheckValid_Date())) {
			msg = "检验有效日期不能为空";

			/*
			 * 上传图片暂时不做限制 } else if
			 * (request.getSession().getAttribute("card_pic1") == null &&
			 * bvo.getHandleType() != 2) {// 正面 msg = "请上传身份证图片"; } else if
			 * (request.getSession().getAttribute("card_pic2") == null &&
			 * bvo.getHandleType() != 2) {// 反面 msg = "请上传身份证图片"; } else if
			 * (request.getSession().getAttribute("car_pic1") == null &&
			 * bvo.getHandleType() != 2) { // 汽车1 msg = "请上传汽车图片"; } else if
			 * (request.getSession().getAttribute("car_pic2") == null &&
			 * bvo.getHandleType() != 2) { // 汽车2 msg = "请上传汽车图片"; } else if
			 * (request.getSession().getAttribute("car_pic3") == null &&
			 * bvo.getHandleType() != 2) { // 汽车3 msg = "请上传汽车图片"; } else if
			 * (request.getSession().getAttribute("car_pic4") == null &&
			 * bvo.getHandleType() != 2) { // 汽车4 msg = "请上传汽车图片"; } else if
			 * (request.getSession().getAttribute("carcard_pic1") == null &&
			 * bvo.getHandleType() != 2) { // 行驶证1 msg = "请上传行驶证图片";
			 */
		} else {
			UserAmount record = new UserAmount();
			record.setUserId(user.getUserId());
			UserAmount amount = userAmountService.selectByParam(record).get(0);
			if (amount.getBorrowVouch().doubleValue() < borrow.getAccount()
					.doubleValue()) {
				msg = "借款金额不能超过可借总额度";
			} else {
				MortgageType mortgagetype = new MortgageType();
				mortgagetype.setTypeName(bvo.getTypeName());
				mortgageCar.setRegisterDate(DateUtil.getDateToString(
						bvo.getRegister_Date(), "yyyy-MM-dd"));
				mortgageCar.setCertificationDate(DateUtil.getDateToString(
						bvo.getCertification_Date(), "yyyy-MM-dd"));
				mortgageCar.setCheckValidDate(DateUtil.getDateToString(
						bvo.getCheckValid_Date(), "yyyy-MM"));
				borrow.setAddip(request.getRemoteAddr());

				MessageVO mvo = borrowService.insertNewBorrow(user, borrow,
						bvo, mortgagetype, mortgageCar, request);
				msg = mvo.getMsg();
				mv.addObject("id",
						URLEncoder.encode(mvo.getId().toString(), "utf-8"));
			}
		}
		mv.addObject("msg", URLEncoder.encode(msg, "utf-8"));
		mv.addObject("bvo", bvo);
		return mv;
	}

	/**
	 * @Description:发布体验标 for php
	 * @author liuhuan
	 */
	@RequestMapping(value = "/releaseFeelBorrow")
	@ResponseBody
	public Json releaseFeelBorrow(HttpServletRequest request,
			HttpSession session, Borrow borrow) {
		String msg = "";
		String userId = request.getParameter("userId");
		Json j = new Json();
		// TODO 是否限制发布指定用户发布体验标
		if (borrow.getAccount() == null || "".equals(borrow.getAccount())) {
			msg = "借款金额不能为空.";
		} else if (borrow.getApr() == null || "".equals(borrow.getApr())) {
			msg = "借款利率不能为空.";
		} else if (userId == null || "".equals(userId)) {
			msg = "请登录重试.";
		} else if (borrow.getLowestAccount() == null) {
			msg = "最低投标额错误.";
		} else if (borrow.getName() == null || "".equals(borrow.getName())) {
			msg = "借款标题不能为空.";
		} else if (borrow.getContent() == null
				|| "".equals(borrow.getContent())) {
			msg = "借款内容不能为空.";
		} else if (borrow.getTimeLimitDay() == null) {
			msg = "天标天数不能为空.";
		} else {
			msg = borrowService.insertFeelBorrow(borrow,
					Integer.parseInt(userId), request.getRemoteAddr());
			j.setSuccess(true);
		}
		j.setMsg(msg);
		return j;
	}

	/**
	 * @Description: 体验标--投标 for php
	 * @author liuhuan
	 */
	@RequestMapping("tenderBorrow")
	@ResponseBody
	public Json tenderBorrow(TenderBorrowVO tenderBorrowvo,
			HttpServletRequest request, HttpSession session) {
		Json j = new Json();
		User user = userService.selectByPrimaryKey(tenderBorrowvo.getUserId());
		String result = "";
		if (tenderBorrowvo.getMoney() == null
				|| "".equals(tenderBorrowvo.getMoney())) {
			result = "投资金额不能为空.";
		} else if (tenderBorrowvo.getBorrowId() == null
				|| "".equals(tenderBorrowvo.getBorrowId())) {
			result = "标数据不正确.";
		} else if (!user.getPaypassword().equals(
				DigestUtils.md5Hex(tenderBorrowvo.getPaypassword()))) {
			result = "支付密码不正确，请重新输入.";
		} else {
			SuccessTenderVO tendervo = borrowService.updateTenderFeelBorrow(
					tenderBorrowvo, request.getRemoteAddr());
			result = tendervo.getResult();
			if (tendervo.getResult().equals("投资成功。")) {
				j.setSuccess(true);
			}
		}
		j.setMsg(result);
		return j;
	}

	/**
	 * @Description: 体验标满标复审 for php
	 * @author liuhuan
	 */
	@RequestMapping("finalVerifyFeelBorrow")
	@ResponseBody
	public Json finalVerifyFeelBorrow(HttpServletRequest request,
			VerifyVO verifyvo) {
		Json j = new Json();
		if (verifyvo.getBorrowId() == null || "".equals(verifyvo.getBorrowId())) {
			j.setMsg("标数据不正确.");
		} else if (verifyvo.getRepaymentRemark() == null
				|| "".equals(verifyvo.getRepaymentRemark())) {
			j.setMsg("审核备注不能为空.");
		} else if (verifyvo.getStatus() == null
				|| "".equals(verifyvo.getStatus())) {
			j.setMsg("审核状态不能为空.");
		} else {
			j.setMsg(borrowService.updateFinalVerifyFeelBorrow(verifyvo));
		}
		return j;
	}

	/**
	 * 真实标--满标复审
	 * 
	 * @return
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "")
	@RequestMapping("finalVerifyBorrow")
	@ResponseBody
	public Json finalVerifyBorrow(HttpServletRequest request,
			FinalVerifyVO finalVerify) {
		Json j = new Json();
		Integer adminUserId = UserSession.getLoginUserId();
		String result = "";
		if (finalVerify.getBorrowId() == null) {
			result = "标数据不正确.";
		} else if (EmptyUtil.isEmpty(finalVerify.getRemark())) {
			result = "审核备注不能为空.";
		} else if (finalVerify.getStatus() == null) {
			result = "审核状态不能为空.";
		} else {
			finalVerify.setAdminUserId(adminUserId);
			finalVerify.setIp(request.getRemoteAddr());
			result = borrowService.updateFinalVerifyBorrow(finalVerify);

			j.setSuccess(true);
		}
		j.setMsg(result);
		return j;
	}

	/**
	 * @Description:体验标--还款 for php
	 * @param request
	 * @param session
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping("/paymentFeelBorrow")
	@ResponseBody
	public Json paymentFeelBorrow(HttpServletRequest request) {
		Json j = new Json();
		String result = "";
		String repaymentId = request.getParameter("repaymentId");
		String userId = request.getParameter("userId");
		BorrowRepayment repayment = borrowRepaymentService
				.selectByPrimaryKey(Integer.parseInt(repaymentId));
		Borrow borrow = borrowService.selectById(repayment.getBorrowId());
		if (!userId.equals(borrow.getUserId().toString())) {
			result = "该登录用户不是借款用户，请用借款用户登录并还款。";
		} else if (repaymentId == null || "".equals(repaymentId)) {
			result = "标数据错误，还款失败.";
		} else {
			result = borrowService.updateRepayFeelBorrow(repaymentId,
					Integer.parseInt(userId), request.getRemoteAddr());
		}
		j.setMsg(result);
		return j;
	}

	/**
	 * 体验标--待还记录 for php
	 * 
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping("/feelRepaymentListAjax")
	@ResponseBody
	public PageResult<BorrowRepayment> feelRepaymentListAjax(SearchVO searchvo,
			PageSearch pageSearch) throws Exception {
		Date date = null;
		if (EmptyUtil.isNotEmpty(searchvo.getStartTime())) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(searchvo
					.getStartTime() + " 00:00:00");
			searchvo.setStartTime((date.getTime() / 1000) + "");
		}
		if (EmptyUtil.isNotEmpty(searchvo.getEndTime())) {
			date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(searchvo
					.getEndTime() + " 00:00:00");
			searchvo.setEndTime((date.getTime() / 1000) + "");
		}
		PageResult<BorrowRepayment> result = this.paymentList(pageSearch,
				searchvo);
		return result;
	}

	// for java
	@RequestMapping("/feelRepaymentListWeb")
	@ResponseBody
	public PageResult<BorrowRepayment> feelRepaymentListWeb(SearchVO searchvo,
			PageSearch pageSearch) throws Exception {
		searchvo.setUserId(UserSession.getLoginUserId());
		PageResult<BorrowRepayment> result = this.paymentList(pageSearch,
				searchvo);
		return result;
	}

	public PageResult<BorrowRepayment> paymentList(PageSearch pageSearch,
			SearchVO searchvo) {
		User user = userService.selectByPrimaryKey(searchvo.getUserId());
		searchvo.setUserId(user.getUserId());
		pageSearch.setEntity(searchvo);
		PageResult<BorrowRepayment> result = new PageResult<BorrowRepayment>();
		List<BorrowRepayment> borrowRepayments = borrowRepaymentService
				.selectFeelByUserIdStatusListPage(pageSearch);
		result.setRows(borrowRepayments);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * @Description:体验金待收时间 for php
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping("/getUserCollectionTime")
	@ResponseBody
	public BorrowCollection getUserCollectionTime(
			@RequestParam("userId") String userId) {
		BorrowCollection collection = new BorrowCollection();
		BigDecimal waitFeelInterest = borrowCollectionService
				.selectWaitFeelInterest(Integer.parseInt(userId));
		collection.setInterest(waitFeelInterest == null ? "0"
				: waitFeelInterest.toString());// 体验金待收利息
		String repayTime = borrowCollectionService.selectFeelRepayTime(Integer
				.parseInt(userId));
		collection.setRepayTime(repayTime == null ? "" : repayTime);
		return collection;
	}

	/**
	 * @Description:普通标--还款
	 * @return
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "borrowRepayment/repaymentList")
	@RequestMapping("repaymentBorrow")
	@ResponseBody
	public Json repaymentBorrow(HttpServletRequest request,
			HttpSession session, @RequestParam("repaymentId") String repaymentId) {
		Json j = new Json();
		String msg = "";
		if (repaymentId == null || "".equals(repaymentId)) {
			msg = "数据异常.";
		} else if(!canSinaBalanceRepaymentBorrow(repaymentId)){
			msg = "还款失败，新浪余额不足,请联系微积金客服或技术。";
		} else {
			msg = borrowService.updateRepayBorrow(repaymentId,
					request.getRemoteAddr());
		}
		j.setMsg(msg);
		return j;
	}
	
	/**
	 * 新浪余额是否够还款
	 * @param repaymentId
	 * @return
	 * @author louchen 2015-04-02
	 */
	private synchronized Boolean canSinaBalanceRepaymentBorrow(String repaymentId){
		Boolean result = false;
		Map<String, String> map = null;
		BorrowRepayment borrowRepayment = borrowRepaymentService.selectByPrimaryKey(Integer.parseInt(repaymentId));
		try {
			map = querySinaService.doQueryBalance(UserSession.getLoginUserId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (map != null && map.get("success").equals("success") && !map.get("balance").equals("null")) {
			BigDecimal sinaBalance = new BigDecimal(map.get("balance"));
			 if (sinaBalance.compareTo(
						new BigDecimal(borrowRepayment.getRepaymentAccount())) >= 0){
				 result = true;
			 } 
			
		}
		return result;
	}

	/**
	 * @Description:标的详情页（体验标&普通标）
	 * @param id
	 *            标id
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping("/borrowDetail/{id}")
	public ModelAndView borrowDetail(HttpServletRequest request,
			HttpSession session, @PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("borrow/borrowDetail");

		// 查询标的相关图片
		MortgageCarPic pic = new MortgageCarPic();
		pic.setId(id);
		pic.setType("2");
		List<MortgageCarPic> listPic = this.borrowService.selectBorrowPic(pic);
		mv.addObject("listPic", listPic);

		// 借款基本详情
		Borrow borrow = this.borrowService.selectBorrowById(id);

		BorrowRule borrowRule = this.borrowRuleService.selectByPrimaryKey(id);
		mv.addObject("borrowRule", borrowRule);
		mv.addObject("borrow", borrow);
		// borrow详细信息
		Borrow borrowAll = this.borrowService.selectById(id);
		mv.addObject("borrowAll", borrowAll);

		if (EmptyUtil.isEmpty(borrowAll) || borrowAll.getStatus() == 2) {
			return new ModelAndView("error/404");
		}
		if (EmptyUtil.isEmpty(borrowAll.getMortgageId())
				&& EmptyUtil.isEmpty(borrowAll.getMortgageTypeid())
				&& EmptyUtil.isNotEmpty(borrowAll.getContent())) {
			mv.addObject("oldPic", true);
		}

		// 判断登录人的信息
		if (UserSession.getLoginUserId() != null) {
			if (borrowAll.getStatus() == 10) {
				AccountFeel accountFeel = this.accountFeelService
						.selectByUserId(UserSession.getLoginUserId());
				if (accountFeel == null) {
					accountFeel = new AccountFeel();
					accountFeel.setUseMoney(new BigDecimal(0));
				}
				mv.addObject("accountFeel", accountFeel);
			} else {
				ModelAndViewUtil.addAccountToView(mv, this.accountService);
			}

			// 查询用户加息卡
			HikesCard hikesCard = this.hikesCardMapper
					.selectByPrimaryKey(UserSession.getLoginUserId());
			if (hikesCard != null) {
				mv.addObject("hikesCard", hikesCard);
				// 用户总共的加息卡利率
				BigDecimal useRateDouble = hikesCard.getUseRate();
				// 查询平台服务费信息
				InvestorsFee investorsFee = this.investorsFeeService
						.selectByBorrowId(id);
				// 用户得到的标的利息
				BigDecimal expApr = borrow.getApr()
						.subtract(investorsFee.getBfee())
						.subtract(investorsFee.getGfee());
				// 最多可加息
				BigDecimal mostHikes = null;
				if (EmptyUtil.isEmpty(borrowRule)) {// 老标的 vf_borrow_rule没有数据
					mostHikes = new BigDecimal(0);
				} else {
					mostHikes = borrowRule.getMostHikes().subtract(expApr);
				}
				BigDecimal useRate = useRateDouble.doubleValue() > mostHikes
						.doubleValue() ? mostHikes : useRateDouble;
				mv.addObject("useHikesRate", useRate);
			}

		}

		// 此标的投资信息
		Integer tenderCount = this.btmMapper.selectTenderBorrowByIdCount(id);
		mv.addObject("tenderCount", tenderCount);

		// 发标人的个人信息
		Integer borrowUserId = borrowAll.getUserId();// 发标人的userID
		User user = this.userService.selectByPrimaryKey(borrowUserId);
		if (EmptyUtil.isNotEmpty(user)) {
			if (EmptyUtil.isNotEmpty(user.getArea())) {
				user.setArea(areaService.getAreaNameById(Integer.parseInt(user
						.getArea())));
			} else {
				user.setArea("--");
			}
			if (EmptyUtil.isNotEmpty(user.getCity())) {
				user.setCity(areaService.getAreaNameById(Integer.parseInt(user
						.getCity())));
			} else {
				user.setCity("--");
			}
			if (EmptyUtil.isNotEmpty(user.getProvince())) {
				user.setProvince(areaService.getAreaNameById(Integer
						.parseInt(user.getProvince())));
			} else {
				user.setProvince("--");
			}
			user.setBirthday("");
			if (EmptyUtil.isNotEmpty(user.getCardId())) {
				String card = user.getCardId();
				if (card.length() == 18) {
					user.setBirthday(card.substring(6, 10) + "-"
							+ card.substring(10, 12) + "-"
							+ card.substring(12, 14));
				} else if (card.length() == 15) {
					user.setBirthday("19" + card.substring(6, 8) + "-"
							+ card.substring(8, 10) + "-"
							+ card.substring(10, 12));
				}

			}
		}
		mv.addObject("user", user);
		// 借款人详细信息
		UserInfo userInfo = this.userInfoService.selectByUserId(borrowUserId);
		mv.addObject("userInfo", userInfo);

		// 车主信息
		MortgageCar mortgageCar = mortgageCarService
				.selectByPrimaryKey(borrowAll.getMortgageId());
		mv.addObject("mortgageCar", mortgageCar);

		// 查询用户还款详情
		RepaymentSituationVO repaymentSituation = this.borrowRepaymentService
				.selectRepaymentSituationVO(borrowUserId);
		mv.addObject("repaymentSituation", repaymentSituation);

		// 查询用户待还款信息
		PageSearch pagesearch = new PageSearch();
		pagesearch.setPage(1);
		pagesearch.setRows(10);
		pagesearch.setEntity(borrowUserId);
		List<BorrowRepayment> waitRepaymentList = this.borrowRepaymentService
				.selectWaitRepaymentListPage(pagesearch);
		mv.addObject("waitRepaymentList", waitRepaymentList);

		// 根据borrowID查询投资详细
		List<BorrowTender> tenderList = this.borrowTenderService
				.selectTenderByBorrowId(id);
		mv.addObject("tenderList", tenderList);
		mv.addObject("tenderListJSON", JSONArray.toJSON(tenderList));

		return mv;
	}
	/**
	 * 计算预计收益
	 */
	@RequestMapping("/calProfit")
	@ResponseBody
	public Json calProfit(Double money,Integer borrowId,Double useRate){
		Json json = new Json();
		try {
			if(money ==null || borrowId ==null)
				throw new NullPointerException("投资金额为空 或者 标的ID为空");
			
			Borrow borrow = borrowService.selectBorrowById(borrowId);
			if(borrow == null)
				throw new NullPointerException("borrow 信息未查询到！");
			
			Byte style = borrow.getStyle();
			if(style == null)
				throw new NullPointerException("标的还款方式错误！");
			
			Short timeLimit = borrow.getTimeLimit();
			if(timeLimit == null)
				throw new NullPointerException("未查询到是几个月标！");
			
			Fee fee=feeService.selectByTimeLimit((int)timeLimit);
			if(fee == null || fee.getApr() ==null)
				throw new NullPointerException("未查询到标的 利率！");
			
			BigDecimal yearApr = fee.getApr();
			
			//加息
			if(useRate != null)
				yearApr = new BigDecimal( yearApr.doubleValue() +  useRate);

			BigDecimal monthApr  = yearApr.divide(new BigDecimal("12"), 4, BigDecimal.ROUND_UP);
			BigDecimal profit = null;
			//等额本息
			if(style == 0){
				ProfitCalVO vo = new ProfitCalVO();
				vo.setType(1);
				vo.setApr(yearApr);
				vo.setMonth(timeLimit.intValue());
				vo.setType(style.intValue());
				vo.setMoney(new BigDecimal(money));
				List<ProfitCalJsonVO> list = profitCalService.selectProfitCal(vo);
				profit = new BigDecimal(0);
				for (int i = 0; i < list.size(); i++) {
					ProfitCalJsonVO pc = list.get(i);
					profit = profit.add(pc.getMonthInterest());
				}
			}else{
				profit = new BigDecimal(money).divide(new BigDecimal("100"))
						.multiply((monthApr.multiply(new BigDecimal(timeLimit)))) ; 
			}
			json.setMsg(String.valueOf(profit.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()));
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 借款记录查询
	 * 
	 * @param status
	 * @param pageSearch
	 * @return
	 * @throws Exception
	 *             author LiLei 2014年5月19日
	 */
	@NeedSession(returnUrl = "/borrow/myRecord")
	@RequestMapping("/myRecord")
	@ResponseBody
	public ModelAndView recordList(QueryParameterVO vo, PageSearch pageSearch)
			throws Exception {
		ModelAndView mv = new ModelAndView("user/myRecord");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	/**
	 * 借款记录查询AJAX
	 * 
	 * @param status
	 * @param pageSearch
	 * @return
	 * @throws Exception
	 *             author LiLei 2014年5月19日
	 */
	@NeedSession(returnUrl = "/borrow/myRecord")
	@RequestMapping("/myRecordAjax")
	@ResponseBody
	public PageResult<Borrow> repaymentListAjax(QueryParameterVO vo,
			PageSearch pageSearch) throws Exception {
		PageResult<Borrow> result = this.getBorrowByUserIdList(pageSearch, vo);
		return result;
	}

	private PageResult<Borrow> getBorrowByUserIdList(PageSearch pageSearch,
			QueryParameterVO vo) {
		PageResult<Borrow> result = new PageResult<Borrow>();
		try {
			vo.setUserId(UserSession.getLoginUserId());
			ModelAndViewUtil.addQueryTimeVO(vo);
			pageSearch.setEntity(vo);
			List<Borrow> resultList = this.borrowService
					.selectBorrowByUserIdListPage(pageSearch);
			result.setRows(resultList);
			result.setTotal(pageSearch.getTotalResult());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/borrowList")
	@ResponseBody
	public ModelAndView borrowList(QueryParameterVO vo, PageSearch pageSearch)
			throws Exception {
		ModelAndView mv = new ModelAndView("borrow/borrowList");
		BigDecimal yesterdaySumBorrowAccount = borrowTenderService
				.selectYesterdaySumBorrowAccount();
		if (yesterdaySumBorrowAccount == null) {
			yesterdaySumBorrowAccount = new BigDecimal("0.00");
		} else {
			yesterdaySumBorrowAccount.setScale(2);
		}
		mv.addObject("yesterdaySumBorrowAccount", yesterdaySumBorrowAccount);
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	@RequestMapping("/borrowListAjax")
	@ResponseBody
	public PageResult<Borrow> borrowListAjax(QueryParameterVO vo,
			PageSearch pageSearch) throws Exception {
		PageResult<Borrow> result = new PageResult<Borrow>();
		pageSearch.setEntity(vo);
		List<Borrow> borrowList = this.borrowService
				.selectAllBorrowListPage(pageSearch);
		result.setTotal(pageSearch.getTotalResult());
		result.setRows(borrowList);
		return result;
	}

	// 借款协议--微积金
	@RequestMapping(value = "/agreementVfunding/{id}")
	public ModelAndView agreementVfunding(@PathVariable("id") int id) {
		boolean istender = false;
		Borrow borrow = borrowService.selectById(id);
		User user = userService.selectByPrimaryKey(borrow.getUserId());
		List<BorrowTenderVO> tenderList = this.borrowTenderService
				.selectAgreementPage(id);
		if (EmptyUtil.isNotEmpty(UserSession.getLoginUserId())
				&& EmptyUtil.isNotEmpty(tenderList)) {
			for (BorrowTenderVO vo : tenderList) {
				if (vo.getTenderUserId().intValue() == UserSession
						.getLoginUserId().intValue()) {
					istender = true;
				} else if (UserSession.getUserSession().getTypeId() == 40) {
					istender = true;
				}
			}
		}
		ModelAndView mv = null;
		if (EmptyUtil.isNotEmpty(borrow.getMortgageTypeid())
				&& borrow.getMortgageTypeid() == 1) {// 汽车(合作方协议)
			mv = new ModelAndView("borrow/agreementPartner");
		} else if (EmptyUtil.isNotEmpty(borrow.getMortgageTypeid())
				&& borrow.getMortgageTypeid() == 2) {// 微积金协议
			mv = new ModelAndView("borrow/agreement");
		} else {
			mv = new ModelAndView("borrow/agreement");
		}
		mv.addObject("tenderList", tenderList);
		mv.addObject("borrow", borrow);
		mv.addObject("user", user);
		mv.addObject("istender", istender);
		return mv;
	}
}
