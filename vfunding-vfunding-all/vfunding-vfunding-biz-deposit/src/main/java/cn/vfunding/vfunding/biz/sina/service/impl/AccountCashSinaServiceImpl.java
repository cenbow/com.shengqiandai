package cn.vfunding.vfunding.biz.sina.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.service.IAccountCashSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateHostingWithdrawReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingWithdrawSendVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

import com.alibaba.fastjson.JSON;

@Service("accountCashSinaService")
public class AccountCashSinaServiceImpl implements IAccountCashSinaService {
	// 新浪推送service
	@Autowired
	private ISinaSendService sinaSendService;
	// 新浪推送日志
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	// 新浪推送字典
	@Autowired
	private ISinaDicService sinaDicService;
	@Autowired
	private ISinaCardService sinaCardService;
	@Autowired
	private IUserService userService;

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");

	@Override
	public String doUserTakeCash(AccountCash accountCash, AccountCashVO cashVO) throws Exception {
		logger.info("*****[sina " + accountCash.getUserId() + "  提现   开始]");
		if (EmptyUtil.isEmpty(accountCash)) {
			throw new Exception("参数对象为空");
		} else {
			// 基础信息
			BigDecimal credited = accountCash.getCredited(); // 提现到账金额
			Integer userId = accountCash.getUserId();
			// 查询在新浪绑定的银行卡编号
			SinaCard sc = new SinaCard();
			sc.setUserId(userId);// 用户ID
			sc.setCardId(accountCash.getAccount());// 银行卡号
			sc.setStatus("0");// 绑定的卡
			List<SinaCard> scs = sinaCardService.selectSelective(sc);
			// 发送接送
			String result = "faild";
			// sendVO拼参数
			CreateHostingWithdrawSendVO chws = new CreateHostingWithdrawSendVO();
			chws.setOut_trade_no(new Date().getTime() + "");
			chws.setIdentity_id(userId.toString());
			chws.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
			chws.setNotify_url(SinaParamsUtil.getInstance().getNotifyUrlHost() + "/sinaNotifyAction/withdrawNotify");// 测试地址
			chws.setMemo(JSON.toJSONString(accountCash) + "|" + JSON.toJSONString(cashVO));
			User u = userService.selectByPrimaryKey(userId);
			if (u.getTypeId().equals(40)) {
				chws.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
			} else {
				chws.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
			}
			chws.setAmount(String.valueOf(credited.doubleValue()));
			// 设置银行卡编号
			if (scs.isEmpty()) {
				chws.setCard_id("");// TODO sina 银行卡编号在数据库中还未增加
			} else {
				chws.setCard_id(scs.get(0).getSinaCard());
			}
			logger.info("发送请求");
			CreateHostingWithdrawReturnVO chwr = null;
			try {
				chwr = sinaSendService.sinaSendMas(chws, CreateHostingWithdrawReturnVO.class);

				logger.info("请求完毕");
				// 判断接口请求状态
				if (chwr.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
					// 接口请求成功,判断提现业务处理状态
					if (chwr.getWithdraw_status().equals(SinaMemberParmUtil.WithdrawStatus.PROCESSING)) {
						sinaSendLogService.insertSinaLog(chws.getOut_trade_no(), 2, 0, SinaMemberParmUtil.interfaceName.create_hosting_withdraw, chws, chwr);
						result = SinaMemberParmUtil.success;
					} else {
						result = "新浪提现接口处理状态:" + sinaDicService.dicLoad(SinaMemberParmUtil.WithdrawStatus.WithdrawStatus, chwr.getWithdraw_status());
						logger.info("#####[sina " + accountCash.getUserId() + " 提现 失败]:[" + result + "]");
					}
				} else {
					sinaSendLogService.insertSinaLog(chws.getOut_trade_no(), 0, 0, SinaMemberParmUtil.interfaceName.create_hosting_withdraw, chws, chwr);
					result = chwr.getResponse_message();
					logger.info("#####[sina " + accountCash.getUserId() + " 提现 失败]:[" + result + "]");
				}
			} catch (Exception e) {
				logger.info("#####[sina " + accountCash.getUserId() + " 提现 失败]:[" + e.getMessage() + "]");
				sinaSendLogService.insertSinaLog(chws.getOut_trade_no(), 0, 0, SinaMemberParmUtil.interfaceName.create_hosting_withdraw, chws, chwr);
				logger.info("*****[sina " + accountCash.getUserId() + " 提现 结束]");
			}
			logger.info("*****[sina " + accountCash.getUserId() + " 提现 结束]");
			return result;
		}
	}

}
