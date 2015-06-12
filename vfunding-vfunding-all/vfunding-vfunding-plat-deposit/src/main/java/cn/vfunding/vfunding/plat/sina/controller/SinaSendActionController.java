package cn.vfunding.vfunding.plat.sina.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.p2p.p2p.biz.current.model.Current;
import cn.p2p.p2p.biz.current.model.CurrentTender;
import cn.p2p.p2p.biz.current.service.ICurrentService;
import cn.p2p.p2p.biz.current.service.ICurrentTenderService;
import cn.p2p.p2p.biz.current.vo.UserAccountActionResultVO;
import cn.vfunding.vfunding.biz.account.service.IAccountCashService;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.IAccountCashSinaService;
import cn.vfunding.vfunding.biz.sina.service.IAccountRechargeSinaService;
import cn.vfunding.vfunding.biz.sina.service.IAccountTransferSinaService;
import cn.vfunding.vfunding.biz.sina.service.IBorrowRepaymentSinaService;
import cn.vfunding.vfunding.biz.sina.service.IGiftBoxSinaService;
import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISingleHostingPayTradeSinaService;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateSingleHostingPayTradeSendVO;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Controller
@RequestMapping("/sinaSendAction")
public class SinaSendActionController {
	// 日志对象
	private Logger log = Logger.getLogger("sinaSendActionLog");

	// 提现推送新浪service
	@Autowired
	private IAccountCashSinaService accountCashSinaService;

	// 充值推送新浪service
	@Autowired
	private IAccountRechargeSinaService accountRechargeSinaService;

	// 投资推送新浪service
	@Autowired
	private IHostingCollectTradeSinaService borrowTenderSinaService;

	// 还款推送新浪service
	@Autowired
	private IBorrowRepaymentSinaService borrowRepaymentSinaService;

	// 会员管理推送新浪service
	@Autowired
	private ISinaMemberManagerService sinaMemberManagerService;
	// 满标复审
	@Autowired
	private ISingleHostingPayTradeSinaService borrowVerifySinaService;

	@Autowired
	private IAccountCashService accountCashService;
	@Autowired
	private IHostingCollectTradeSinaService hostingCollectTradeSinaService;
	@Autowired
	private ISingleHostingPayTradeSinaService singleHostingPayTradeSinaService;
	// 查询service
	@Autowired
	private IQuerySinaService querySinaService;
	@Autowired
	private IBorrowService borrowService;

	@Autowired
	private ISinaSendLogService sinaSendLogService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ISinaCardService sinaCardService;
	@Autowired
	private IAccountTransferSinaService accountTransferSinaService;
	@Autowired
	private ISinaDicService sinaDicService;
	@Autowired
	private IGiftBoxSinaService giftBoxSinaService;
	@Autowired
	private ICurrentTenderService currentTenderService;
	@Autowired
	private ICurrentService currentService;
	/**
	 * 使用礼品盒红包,代收代付
	 * 
	 * @param gm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doUseGift", method = RequestMethod.POST)
	@ResponseBody
	public String doUseGift(@RequestBody GiftboxMessage gm) throws Exception {
		String result = this.giftBoxSinaService.GiftHongbaoUseReceiveSinaSend(gm);
		if (result.equals(SinaMemberParmUtil.success)) {
			result = this.giftBoxSinaService.GiftHongbaoUsePaySinaSend(gm);
		}
		return result;
	}

	/**
	 * 还款_推送借款人还款和推送投资人收益
	 * 
	 * @param repaymentId
	 * @return
	 * @author louchen 2015-01-26
	 */
	@RequestMapping(value = "/doUserRepayBorrowerAndTender/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String doUserRepayBorrowerAndTenderAction(@PathVariable("id") Integer repaymentId) {
		String result = null;
		try {
			result = this.borrowRepaymentSinaService.doUserRepayBorrowerAndTender(repaymentId);
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 普通(非体验金)还款针对借款人发送新浪接口
	 * 
	 * @param repaymentId
	 * @return
	 * @author louchen 2015-01-16
	 * 
	 */
	@RequestMapping(value = "/doUserRepayBorrower/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String doUserRepayBorrowerAction(@PathVariable("id") Integer repaymentId) {
		String result = null;
		try {
			result = this.borrowRepaymentSinaService.doUserRepayBorrower(repaymentId);
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 普通(非体验金)还款针对投资人发送新浪接口
	 * 
	 * @param repaymentId
	 * @return
	 * @author louchen 2015-01-16
	 * 
	 */
	@RequestMapping(value = "/doUserRepayTender/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String doUserRepayTenderAction(@PathVariable("id") Integer repaymentId) {
		String result = null;
		try {
			result = this.borrowRepaymentSinaService.doUserRepayTender(repaymentId);
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 投资发送新浪接口
	 * 
	 * @param vo
	 * @return
	 * @throws Exception
	 * @author jianwei
	 */
	@RequestMapping(value = "/doBorrowTender")
	@ResponseBody
	public String doBorrowTenderAction(@RequestBody UserTenderActionResultVO vo) {
		String result = null;
		try {
			CreateHostingCollectTradeSendVO ccts = new CreateHostingCollectTradeSendVO();
			ccts.setOut_trade_no(new Date().getTime() + "" + vo.getTenderId());
			ccts.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._1001);
			ccts.setSummary("标的投资");
			ccts.setPayer_id(vo.getUserId().toString());
			ccts.setPayer_identity_type(SinaMemberParmUtil.IdentityType.UID);
			ccts.setPay_method("balance^" + vo.getRealPayMoney() + "^SAVING_POT");
			result = this.borrowTenderSinaService.sinaSend(ccts, "投标");
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Sina 用户创建激活
	 * 
	 * @param vo
	 * @return
	 * 
	 * @author wang.zeyan
	 * @date 2015年1月15日
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public String registerAction(@RequestBody RegisterVO vo) {
		log.info("*****[sina  " + vo.getRegisterUserId() + " 新浪同步会员  接收]");
		String result = null;
		try {
			result = sinaMemberManagerService.createActivateMember(vo);
			if (SinaMemberParmUtil.success.equals(result) || SinaMemberParmUtil.ResponseCode.DUPLICATE_IDENTITY_ID.equals(result)) {
				sinaMemberManagerService.mobileBindVerify(vo.getRegisterUserId());
			}
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		log.info("*****[sina  " + vo.getRegisterUserId() + " 新浪同步会员  完成]");
		return result;
	}

	/**
	 * Sina会员手机绑定认证
	 * 
	 * @param userId
	 * @return
	 * 
	 * @author wang.zeyan
	 * @date 2015年1月20日
	 */

	@RequestMapping(value = "/mobileBindVerify", method = RequestMethod.POST)
	@ResponseBody
	public String mobileBindVerify(String userId) {
		String result = null;
		try {
			result = sinaMemberManagerService.mobileBindVerify(userId);
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 满标复审
	 * 
	 * @return
	 * @author jianwei
	 * @throws Exception
	 */
	@RequestMapping(value = "/doBorrwoVerify", method = RequestMethod.POST)
	@ResponseBody
	public String doBorrwoVerify(@RequestBody FinalVerifyVO verifyVO) {
		String result = null;
		try {
			Borrow bow = borrowService.selectById(verifyVO.getBorrowId());
			CreateSingleHostingPayTradeSendVO cshpt = new CreateSingleHostingPayTradeSendVO();
			cshpt.setOut_trade_no(new Date().getTime() + "" + bow.getId());
			cshpt.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2001);
			cshpt.setPayee_identity_id(bow.getUserId() + "");
			cshpt.setPayee_identity_type(SinaMemberParmUtil.IdentityType.UID);
			cshpt.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
			cshpt.setAmount(bow.getAccount().doubleValue() + "");
			cshpt.setSummary("借款人收款");
			result = this.borrowVerifySinaService.sinaSend(cshpt, "满标复审");
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 活期投资
	 * 
	 * @return
	 * @author jianwei
	 * @throws Exception
	 */
	@RequestMapping(value = "/doCurrentTender", method = RequestMethod.POST)
	@ResponseBody
	public String doCurrentTender(@RequestBody UserAccountActionResultVO vo) {
		String result = null;
		try {
			CreateHostingCollectTradeSendVO ccts = new CreateHostingCollectTradeSendVO();
			ccts.setOut_trade_no(new Date().getTime() + "" + vo.getTenderId());
			ccts.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._1001);
			ccts.setSummary("活期投资代收");
			ccts.setPayer_id(vo.getUserId().toString());
			ccts.setPayer_identity_type(SinaMemberParmUtil.IdentityType.UID);
			ccts.setPay_method("balance^" + vo.getRealPayMoney() + "^SAVING_POT");
			result = this.borrowTenderSinaService.sinaSend(ccts, "活期投资代收");
			if(result.equals("success")){
				CreateSingleHostingPayTradeSendVO cshpt = new CreateSingleHostingPayTradeSendVO();
				cshpt.setOut_trade_no(new Date().getTime() + "" + vo.getTenderId());
				cshpt.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2001);
				cshpt.setPayee_identity_id("10");
				cshpt.setPayee_identity_type(SinaMemberParmUtil.IdentityType.UID);
				cshpt.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
				cshpt.setAmount(vo.getRealPayMoney().doubleValue() + "");
				cshpt.setSummary("活期投资代付");
				result = this.borrowVerifySinaService.sinaSend(cshpt, "活期投资代付");
			}
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 活期赎回
	 * 
	 * @return
	 * @author jianwei
	 * @throws Exception
	 */
	@RequestMapping(value = "/doCurrentRedeem", method = RequestMethod.POST)
	@ResponseBody
	public String doCurrentRedeem(@RequestBody UserAccountActionResultVO vo) {
		String result = null;
		try {
			CreateHostingCollectTradeSendVO ccts = new CreateHostingCollectTradeSendVO();
			ccts.setOut_trade_no(new Date().getTime() + "" + vo.getTenderId());
			ccts.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._1001);
			ccts.setSummary("活期赎回代收");
			ccts.setPayer_id("10");
			ccts.setPayer_identity_type(SinaMemberParmUtil.IdentityType.UID);
			ccts.setPay_method("balance^" + vo.getPayMoney() + "^SAVING_POT");
			result = this.borrowTenderSinaService.sinaSend(ccts, "活期赎回代收");
			if(result.equals("success")){
				CreateSingleHostingPayTradeSendVO cshpt = new CreateSingleHostingPayTradeSendVO();
				cshpt.setOut_trade_no(new Date().getTime() + "" + vo.getTenderId());
				cshpt.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2001);
				cshpt.setPayee_identity_id(vo.getUserId().toString());
				cshpt.setPayee_identity_type(SinaMemberParmUtil.IdentityType.UID);
				cshpt.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
				cshpt.setAmount(vo.getPayMoney().doubleValue() + "");
				cshpt.setSummary("活期赎回代付");
				result = this.borrowVerifySinaService.sinaSend(cshpt, "活期赎回代付");
			}
		} catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace();
		}
		return result;
	}
}
