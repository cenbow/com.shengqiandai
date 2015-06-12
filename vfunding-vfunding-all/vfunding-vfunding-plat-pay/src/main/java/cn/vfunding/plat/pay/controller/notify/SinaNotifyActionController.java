package cn.vfunding.plat.pay.controller.notify;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.biz.rechargeandtender.model.RechargeAndTender;
import cn.p2p.p2p.biz.rechargeandtender.service.IRechargeAndTenderService;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.account.service.IAccountCashService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.notify.service.IRechargeNotifyService;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.model.SinaSendLogWithBLOBs;
import cn.vfunding.vfunding.biz.sina.service.IAccountTransferSinaService;
import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISingleHostingPayTradeSinaService;
import cn.vfunding.vfunding.biz.sina.util.SinaNotifyUtil;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.notify.RechargeNotifyVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateSingleHostingPayTradeSendVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sinaNotifyAction")
public class SinaNotifyActionController {

	/**
	 * 日志对象，slf4j下的对象
	 */
	org.slf4j.Logger logger = LoggerFactory.getLogger("sinaSendActionLog");

	// 充值回调service
	@Autowired
	private IRechargeNotifyService rechargeNotifyService;

	// @Resource(name = "jms.sender")
	// private JmsSender jmsSender;
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ISinaCardService sinaCardService;
	@Autowired
	private IAccountCashService accountCashService;
	@Autowired
	private IHostingCollectTradeSinaService hostingCollectTradeSinaService;
	@Autowired
	private ISingleHostingPayTradeSinaService singleHostingPayTradeSinaService;
	@Autowired
	private IAccountTransferSinaService accountTransferSinaService;
	@Autowired
	private ISinaDicService sinaDicService;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Autowired
	private IRechargeAndTenderService rechargeAndTenderService;

	/**
	 * 新浪通知网银充值消息
	 * 
	 * @param request
	 * @return
	 * @author louchen 2015-01-21
	 * @throws Exception
	 */
	@RequestMapping(value = "/recharge", method = RequestMethod.POST)
	@ResponseBody
	public String recharge(HttpServletRequest request, RechargeNotifyVO vo) throws Exception {
		Map<String, String> map = SinaNotifyUtil.getAllParameters(request);
		vo.set_input_charset(map.get("_input_charset"));
		String str = rechargeNotifyService.recharge(vo);
		try {
		this.rechargeAndTenderService.rechargeTenderAction(vo.getOuter_trade_no(), str);
		} catch (BusinessException e) {
			RechargeAndTender rat =new RechargeAndTender();
			rat.setTradeNo(vo.getOuter_trade_no());
			rat.setAccount(new BigDecimal(0));
			rat.setStatus(1);
			rat.setRemark(e.getMessage());
			this.rechargeAndTenderService.updateByPrimaryKeySelective(rat);
		} 
			
		
		return str;
	}

	/**
	 * 新浪通知提现消息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author jianwei
	 */
	@RequestMapping(value = "/withdrawNotify", method = RequestMethod.POST)
	@ResponseBody
	public String withdrawNotify(HttpServletRequest request) {
		logger.info("*****[sina 提现回调 开始]");
		Map<String, String> map = SinaNotifyUtil.getAllParameters(request);
		try {
			// 根据新浪回调判断回调内容是否正常
			boolean result = SinaNotifyUtil.checkNotifySign(map);
			if (result) {
				// 根据回调订单号拉取日志数据
				SinaSendLogWithBLOBs ssl = sinaSendLogService.selectByPrimaryKey(map.get("outer_trade_no"));
				if (ssl.getStatus().equals(3)) {// 已经处理过的回调任务
					logger.info("*****[sina 提现回调 结束]:[已经处理过该笔订单]");
					return SinaMemberParmUtil.SUCCESS;
				}
				// 根据日志数据中的 发送与返回参数取得用户ID 与 sina银行卡绑定号
				String userId = ((Map<String, String>) JSON.parse(ssl.getArgs())).get("identity_id");
				String sinaCard = ((Map<String, String>) JSON.parse(ssl.getArgs())).get("card_id");
				// 查询出银行卡信息
				SinaCard sc = new SinaCard();
				sc.setSinaCard(sinaCard);
				sc.setStatus("0");
				sc.setUserId(Integer.parseInt(userId));
				List<SinaCard> scs = sinaCardService.selectSelective(sc);
				// 根据用户ID获取用户信息
				User u = userService.selectByPrimaryKey(Integer.parseInt(userId));
				// 判断回调内容是否提现成功
				if (map.get("withdraw_status").equals(SinaMemberParmUtil.SUCCESS)) {
					AccountCash accountCash = JSON.parseObject(map.get("memo").split("\\|")[0], AccountCash.class);
					AccountCashVO cashVO = JSON.parseObject(map.get("memo").split("\\|")[1], AccountCashVO.class);
					logger.info("[sina回调 提现 本地数据处理开始]:[userId:" + u.getUserId() + ",cashId:" + accountCash.getId() + "]");
					// 接口推送成功更新本地数据
					String updateResult = accountCashService.updateTakeCash(accountCash, cashVO);
					logger.info("[sina回调 提现 本地数据处理结束]:[userId:" + u.getUserId() + ",cashId:" + accountCash.getId() + "]");
					if (updateResult.equals("1")) {
						logger.info("[更新本地日志状态开始]:[userId:" + u.getUserId() + ",ssl_order_no:" + ssl.getOrderNo() + "]");
						ssl.setStatus(3);
						sinaSendLogService.updateByPrimaryKeySelective(ssl);
						logger.info("[更新本地日志状态结束]:[userId:" + u.getUserId() + ",ssl_order_no:" + ssl.getOrderNo() + "]");
						// 提现手续费转账
						BigDecimal credited = accountCash.getCredited(); // 提现到账金额
						BigDecimal fee = accountCash.getTotal().subtract(credited);// 手续费
						logger.info("[手续费转账开始]:[中间账户---->微积金商户]:[金额:" + fee.doubleValue() + "]");
						if (fee.doubleValue() > 0) { // 如果有手续费则转账给微积金
							CreateSingleHostingPayTradeSendVO cshpt = new CreateSingleHostingPayTradeSendVO();// 托管代付
							cshpt.setOut_trade_no(new Date().getTime() + "" + SinaParamsUtil.getInstance().getPartnerId());
							cshpt.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2001);
							cshpt.setPayee_identity_id(SinaParamsUtil.getInstance().getVfundingCompanyAccount());
							cshpt.setPayee_identity_type(SinaMemberParmUtil.IdentityType.EMAIL);
							cshpt.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
							cshpt.setAmount(fee.doubleValue() + "");
							cshpt.setSummary("提现手续费");
							singleHostingPayTradeSinaService.sinaSend(cshpt, "提现手续费");
						}
						logger.info("[手续费转账结束]:[中间账户---->微积金商户]:[金额:" + fee.doubleValue() + "]");

						// 发送提现到账提醒短信
						this.jmsSenderUtil.sendSms(u.getPhone(), ISendConfigUtil.SMS_TAKETOCARDSUCCESS, u.getUsername(), scs.get(0).getCardId().substring(scs.get(0).getCardId().length() - 5, scs.get(0).getCardId().length()));

					}
				} else {
					AccountCash accountCash = JSON.parseObject(map.get("memo").split("\\|")[0], AccountCash.class);
					AccountCashVO cashVO = JSON.parseObject(map.get("memo").split("\\|")[1], AccountCashVO.class);
					logger.info("[sina回调 提现失败 本地数据处理开始]:[userId:" + u.getUserId() + ",cashId:" + accountCash.getId() + "]");
					// 接口推送成功更新本地数据
					cashVO.setStatus(2);
					accountCash.setVerifyRemark(map.get("error_message"));
					accountCashService.updateTakeCash(accountCash, cashVO);
					logger.info("[sina回调 提现失败 本地数据处理结束]:[userId:" + u.getUserId() + ",cashId:" + accountCash.getId() + "]");
					logger.info("[更新本地日志状态开始]:[userId:" + u.getUserId() + ",ssl_order_no:" + ssl.getOrderNo() + "]");
					ssl.setStatus(3);
					sinaSendLogService.updateByPrimaryKeySelective(ssl);
					logger.info("[更新本地日志状态结束]:[userId:" + u.getUserId() + ",ssl_order_no:" + ssl.getOrderNo() + "]");

					BigDecimal credited = accountCash.getCredited(); // 提现到账金额
					BigDecimal fee = accountCash.getTotal().subtract(credited);// 手续费
					logger.info("[手续费退回开始]:[中间账户---->用户账户]:[金额:" + fee.doubleValue() + "]");
					if (fee.doubleValue() > 0) { // 如果有手续费则进行退回操作
						CreateSingleHostingPayTradeSendVO cshpt = new CreateSingleHostingPayTradeSendVO();// 托管代付
						cshpt.setOut_trade_no(new Date().getTime() + "" + SinaParamsUtil.getInstance().getPartnerId());
						cshpt.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2001);
						cshpt.setPayee_identity_id(u.getUserId().toString());
						cshpt.setPayee_identity_type(SinaMemberParmUtil.IdentityType.UID);
						if (u.getTypeId() == 40) {
							cshpt.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
						} else {
							cshpt.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
						}
						cshpt.setAmount(fee.doubleValue() + "");
						cshpt.setSummary("提现手续费退回");
						singleHostingPayTradeSinaService.sinaSend(cshpt, "提现手续费退回");
					}
					logger.info("[手续费退回结束]:[中间账户---->用户账户]:[金额:" + fee.doubleValue() + "]");
					String card = scs.get(0).getCardId().substring(scs.get(0).getCardId().length() - 5, scs.get(0).getCardId().length());
					String res = "" + sinaDicService.dicLoad(SinaMemberParmUtil.WithdrawStatus.WithdrawStatus, map.get("withdraw_status")).getDicMsg();
					// 发送提现到账提醒短信
					this.jmsSenderUtil.sendSms(u.getPhone(), ISendConfigUtil.SMS_TAKETOCARDFAILD, u.getUsername(), card, res);
				}
			} else {
				logger.info("*****[sina 提现回调 结束]:[签名校验失败,等待新浪再次回调]");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("*****[sina 提现回调 结束]");
		return SinaMemberParmUtil.SUCCESS;
	}
}
