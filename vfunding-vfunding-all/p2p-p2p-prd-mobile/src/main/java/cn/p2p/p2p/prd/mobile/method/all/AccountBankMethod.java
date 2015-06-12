package cn.p2p.p2p.prd.mobile.method.all;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.mobile.interceptors.ContBefore;
import cn.p2p.p2p.prd.mobile.method.request.vo.AccountBankVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.BindingBankAdvanceVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.method.vo.DeleteBankVO;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountBank;
import cn.vfunding.vfunding.biz.account.service.IAccountBankService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.account.vo.QuickPayAccountBankVO;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaBank;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.service.IBindingBankCardAdvanceSinaService;
import cn.vfunding.vfunding.biz.sina.service.IBindingBankCardSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaBankService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.service.IUnbindingBankCardSinaService;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryBalanceReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingBankCardAdvanceSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingBankCardSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryBalanceSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.UnbindingBankCardSendVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

@Service
public class AccountBankMethod {

	@Autowired
	private IAccountBankService accountBankService;
	@Autowired
	private IUserService userService;

	@Autowired
	private IAccountService accountService;
	@Autowired
	private IBindingBankCardSinaService bindingBankCardSinaService;
	@Autowired
	private IUnbindingBankCardSinaService unbindingBankCardSinaService;
	@Autowired
	private IBindingBankCardAdvanceSinaService bindingBankCardAdvanceSinaService;

	@Autowired
	private ISinaCardService sinaCardService;
	@Autowired
	private ISinaDicService sinaDicService;
	@Autowired
	private ISinaSendService sinaSendService;
	@Autowired
	private JmsSenderService jmsSenderUtil;
	@Autowired
	private IUserTokenService userTokenService;
	@Autowired
	private ISinaBankService sinaBankService;

	/**
	 * 跳转选择银行列表
	 * 
	 * @return
	 */
	@ContBefore
	public MobileBaseResponse sinaBankList() {
		SinaBank sb = new SinaBank();
		sb.setIsQuick("Y");
		List<SinaBank> sbs = sinaBankService.selectSinaBankByParam(sb);
		return new MobileBaseResponse(sbs);
	}

	/**
	 * 新浪绑定银行卡
	 * 
	 * @return
	 * @author jianwei
	 */
	@CheckToken
	public MobileBaseResponse sinaBindingBank(AccountBankVO accountBank) {
		Integer userId = this.userTokenService.selectUserIdByToken(accountBank.getToken());
		accountBank.setUserId(userId);
		User cuser = this.userService.selectByPrimaryKey(accountBank.getUserId());
		Map<String, Object> map = new HashMap<String, Object>();
		BindingBankCardSendVO bbcs = new BindingBankCardSendVO();
		try {
			bbcs.setBank_account_no(accountBank.getAccount());// 银行卡号
			bbcs.setBank_code(accountBank.getBank());// 银行编号 ICBC等
			bbcs.setCard_attribute(SinaMemberParmUtil.CardParam.C);// 银行卡属性 C 对私
			bbcs.setCard_type(SinaMemberParmUtil.CardType.DEBIT);// 卡类型 借记卡
			bbcs.setCity(accountBank.getCityName());// 城市
			bbcs.setIdentity_id(accountBank.getUserId().toString());// userID
			bbcs.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
			bbcs.setProvince(accountBank.getProvinceName());// 省会
			bbcs.setRequest_no(new Date().getTime() + "" + accountBank.getUserId());// 订单号
			// 是否开通快捷支付
			bbcs.setPhone_no(accountBank.getPhone());// 银行卡预留手机号
			bbcs.setVerify_mode(SinaMemberParmUtil.CardVerify.SIGN);// 认证方式 SIGN
			if (this.checkBindingBank(accountBank.getAccount(), accountBank.getUserId())) {
				map = bindingBankCardSinaService.doBindingBank(bbcs, accountBank.getAccount(), accountBank.getPhone());
				if (map.get("result").toString().equals(SinaMemberParmUtil.success)) {
					accountBank.setUserId(accountBank.getUserId());
					accountBank.setAddip("mobile");
					AccountBank ab = JSON.parseObject(JSON.toJSONString(accountBank), AccountBank.class);
					this.accountBankService.insertSelective(ab);
					if (EmptyUtil.isEmpty(accountBank.getKtkjfs()) || !accountBank.getKtkjfs().equals("Y")) {
						this.jmsSenderUtil.sendSms(cuser.getPhone(), ISendConfigUtil.SMS_ADDBANKCARD, cuser.getUsername());
						if (EmptyUtil.isNotEmpty(cuser.getEmail())) {
							this.jmsSenderUtil.sendEmail(cuser.getEmail(), ISendConfigUtil.EMAIL_TEMPLET_ADDNEWCARD, cuser.getUsername(), cuser.getUsername());
						}
					}
					return new MobileBaseResponse(map);
				} else {
					return new MobileBaseResponse("AccountBank_fail", map.get("result").toString());
				}
			} else {
				map = bindingBankCardSinaService.doBindingBank(bbcs, accountBank.getAccount(), accountBank.getPhone());
				if (map.get("result").toString().equals(SinaMemberParmUtil.success)) {
					return new MobileBaseResponse(map);
				} else {
					return new MobileBaseResponse("AccountBank_fail", map.get("result").toString());
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			return new MobileBaseResponse("AccountBank_fail", "绑定银行卡异常,请联系客服!");
		}
	}

	/**
	 * 新浪绑定银行卡推进
	 * 
	 * @return
	 * @author jianwei
	 */
	@CheckToken
	public MobileBaseResponse sinaBindingBankAdvance(BindingBankAdvanceVO bindingBankAdvanceVO) {
		Integer userId = this.userTokenService.selectUserIdByToken(bindingBankAdvanceVO.getToken());
		bindingBankAdvanceVO.setId(userId);
		User cuser = this.userService.selectByPrimaryKey(bindingBankAdvanceVO.getId());
		String result = "";
		BindingBankCardAdvanceSendVO bbcas = new BindingBankCardAdvanceSendVO();
		if (!StringUtils.isEmpty(bindingBankAdvanceVO.getTicket())) {
			bbcas.setTicket(bindingBankAdvanceVO.getTicket());
			bbcas.setValid_code(bindingBankAdvanceVO.getVerifCode());
			try {
				if (bindingBankAdvanceVO.getScId() != null || bindingBankAdvanceVO.getScId() > 0) {
					result = bindingBankCardAdvanceSinaService.doBindingBankAdvance(bbcas, bindingBankAdvanceVO.getScId().toString());
				}
				if (result.equals(SinaMemberParmUtil.success)) {
					// 发送添加卡短信
					this.jmsSenderUtil.sendSms(cuser.getPhone(), ISendConfigUtil.SMS_ADDBANKCARD, cuser.getUsername());
					if (EmptyUtil.isNotEmpty(cuser.getEmail())) {
						this.jmsSenderUtil.sendEmail(cuser.getEmail(), ISendConfigUtil.EMAIL_TEMPLET_ADDNEWCARD, cuser.getUsername(), cuser.getUsername());
					}
				} else {
					return new MobileBaseResponse("BankAdvance_fail", "验证码校验失败,请重新获取验证码.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return new MobileBaseResponse("BankAdvance_fail", "请获取验证码后再进行绑定");
		}
		return new MobileBaseResponse();
	}

	/**
	 * 删除银行卡
	 * 
	 * @param id
	 * @return 2014年5月8日 liuyijun
	 */
	@CheckToken
	public MobileBaseResponse deleteBank(DeleteBankVO deleteBankVO) {
		Integer userId = this.userTokenService.selectUserIdByToken(deleteBankVO.getToken());
		AccountBank accountBank = this.accountBankService.selectByPrimaryKey(deleteBankVO.getAccountId());
		if (EmptyUtil.isNotEmpty(accountBank)) {
			// 删除银行卡后解绑sina方的银行卡,并将本地储存的sina银行卡信息状态修改为解绑
			SinaCard sc = new SinaCard();
			sc.setCardId(accountBank.getAccount());
			sc.setStatus("0");
			sc.setUserId(userId);
			List<SinaCard> scs = sinaCardService.selectSelective(sc);
			// 验证余额
			if (!scs.isEmpty()) {
				SinaCard s = scs.get(0);
				if (s.getIsVerified().equals("Y")) {
					QueryBalanceSendVO qbs = new QueryBalanceSendVO();
					qbs.setIdentity_id(userId.toString());
					qbs.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
					if (userService.selectByPrimaryKey(userId).getTypeId().equals(40)) {
						qbs.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
					} else {
						qbs.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
					}
					try {
						QueryBalanceReturnVO qbr = sinaSendService.sinaSendMgs(qbs, QueryBalanceReturnVO.class);
						if (Double.parseDouble(qbr.getBalance()) > 0) {
							return new MobileBaseResponse("deleteBank_fail", "用户余额不为0,不可删除已开通快捷支付的卡");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			if (!scs.isEmpty()) {
				sc = scs.get(0);
				if (StringUtils.isEmpty(sc.getSinaCard())) {
					this.accountBankService.deleteByPrimaryKey(deleteBankVO.getAccountId());
					sc.setStatus("1");
					sinaCardService.updateByPrimaryKeySelective(sc);
				} else {
					UnbindingBankCardSendVO ubcs = new UnbindingBankCardSendVO();
					ubcs.setCard_id(sc.getSinaCard());// 新浪银行卡编号
					ubcs.setIdentity_id(userId.toString());// 用户ID
					ubcs.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
					try {
						String result = unbindingBankCardSinaService.doUnbindingBank(ubcs);
						if (result.equals(SinaMemberParmUtil.success)) {
							this.accountBankService.deleteByPrimaryKey(deleteBankVO.getAccountId());
							sc.setStatus("1");
							sinaCardService.updateByPrimaryKeySelective(sc);
							return new MobileBaseResponse();
						} else {
							return new MobileBaseResponse("deleteBank_fail", result);
						}
					} catch (Exception e) {
						e.printStackTrace();
						return new MobileBaseResponse("deleteBank_fail", "删除银行卡异常,请联系客服!");
					}
				}
			} else {
				this.accountBankService.deleteByPrimaryKey(deleteBankVO.getAccountId());
			}
		}
		return new MobileBaseResponse();
	}

	/**
	 * 校验银行卡是否已经绑定
	 * 
	 * @param accountNo
	 * @return
	 */
	public boolean checkBindingBank(String accountNo, Integer userId) {
		SinaCard sc = new SinaCard();
		sc.setCardId(accountNo);
		sc.setStatus("0");
		sc.setUserId(userId);
		List<SinaCard> scs = sinaCardService.selectSelective(sc);
		List<AccountBank> abs = this.accountBankService.selectByAccount(accountNo);
		if (!scs.isEmpty() && scs.get(0) != null && !abs.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 我的银行卡页面跳转
	 * 
	 * @return jianwei
	 */
	@CheckToken
	public MobileBaseResponse myBank(GeneralRequestVO requestVO) {
		Integer userId = this.userTokenService.selectUserIdByToken(requestVO.getToken());
		List<AccountBank> banks = this.accountBankService.selectByUserId(userId);
		List<AccountBank> bankTemp = new ArrayList<AccountBank>();
		for (AccountBank ab : banks) {
			if (StringUtils.isEmpty(ab.getKtkjfs()) || StringUtils.isEmpty(ab.getScId())) {
				bankTemp.add(ab);
			}
		}
		banks.removeAll(bankTemp);
		return new MobileBaseResponse(banks);
	}

	/**
	 * 绑定银行卡查询 按权重排序
	 * 
	 * @param vo
	 * @return
	 * @author louchen 2015-02-02
	 */
	public MobileBaseResponse myQuickPayCards(GeneralRequestVO requestVO) {
		List<SinaCard> cards = this.sinaCardService.selectQuickPayByUserId(requestVO.getId().toString());
		List<QuickPayAccountBankVO> cardsVO = new ArrayList<QuickPayAccountBankVO>();
		for (SinaCard sc : cards) {
			QuickPayAccountBankVO cardVO = new QuickPayAccountBankVO();
			List<AccountBank> abs = accountBankService.selectByAccount(sc.getCardId());
			if (!abs.isEmpty()) {
				cardVO.setAccountId(abs.get(0).getId());
			}
			cardVO.setScId(sc.getScId());
			cardVO.setCardId(sc.getCardId());
			cardVO.setBank(sc.getBank());
			cardVO.setBankPhone(sc.getBankPhone());
			cardVO.setWeight(sc.getWeight());
			cardsVO.add(cardVO);
		}
		return new MobileBaseResponse(cardsVO);
	}

	/**
	 * 校验是否绑定银行卡
	 * 
	 * @param requestVO
	 * @return
	 */
	public MobileBaseResponse checkQuickCount(GeneralRequestVO requestVO) {
		Integer userId = this.userTokenService.selectUserIdByToken(requestVO.getToken());
		List<AccountBank> banks = this.accountBankService.selectByUserId(userId);
		int kjcount = 0;
		for (AccountBank ab : banks) {
			if (ab.getKtkjfs().equals("Y")) {
				kjcount = 1;
				break;
			}
		}
		if (kjcount == 1) {
			return new MobileBaseResponse("checkQuick_fail", "已存在快捷支付卡,不可再次绑卡");
		}
		return new MobileBaseResponse();
	}
}
