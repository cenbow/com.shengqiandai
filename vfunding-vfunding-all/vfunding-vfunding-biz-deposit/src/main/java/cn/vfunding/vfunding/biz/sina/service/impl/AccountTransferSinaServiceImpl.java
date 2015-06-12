package cn.vfunding.vfunding.biz.sina.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.IAccountTransferSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateHostingTransferReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingTransferSendVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
@Service("accountTransferSinaService")
public class AccountTransferSinaServiceImpl implements
		IAccountTransferSinaService {
	@Autowired
	private ISinaSendService sinaSendService;
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	@Autowired
	private ISinaDicService sinaDicService;
	@Autowired
	private IUserService userService;
	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");
	
	@Override
	public String doAccpintTransfer(String payerId,String payeeId,BigDecimal amount) throws Exception {
		String result = "";
		logger.info("构建转账接口发送对象");
		CreateHostingTransferSendVO chts = new CreateHostingTransferSendVO();
		chts.setOut_trade_no(new Date().getTime() +""+payerId);
		chts.setPayer_identity_id(payerId);
		chts.setPayer_identity_type(SinaMemberParmUtil.IdentityType.UID);
		chts.setPayer_account_type(SinaMemberParmUtil.AccountType.BASIC);
		chts.setPayee_identity_id(payeeId);
		chts.setPayee_identity_type(SinaMemberParmUtil.IdentityType.UID);
		User u = userService.selectByPrimaryKey(Integer.parseInt(payeeId));
		if(u.getTypeId().equals(40) || u == null){
			chts.setPayee_account_type(SinaMemberParmUtil.AccountType.BASIC);
		}else{
			chts.setPayee_account_type(SinaMemberParmUtil.AccountType.SAVING_POT);
		}
		chts.setAmount(String.valueOf(amount.doubleValue()));
		chts.setSummary("提现手续费");
		logger.info("转账接口对象构建完成:["+JSON.toJSONString(chts)+"]");
		logger.info("开始请求新浪接口");
		CreateHostingTransferReturnVO chtr = sinaSendService.sinaSendMas(chts, CreateHostingTransferReturnVO.class);
		logger.info("新浪接口请求完成,响应内容为:["+JSON.toJSONString(chtr)+"]");
		if(chtr.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)){
			if(chtr.getTrade_status().equals(SinaMemberParmUtil.SUCCESS)){
				result = SinaMemberParmUtil.success;
				sinaSendLogService.insertSinaLog(chts.getOut_trade_no(), 2, 0, SinaMemberParmUtil.interfaceName.create_hosting_transfer, chts, chtr);
			}else{
				result = "新浪转账接口处理状态:"+sinaDicService.dicLoad(SinaMemberParmUtil.TradeStatus.TradeStatus, chtr.getTrade_status());
				sinaSendLogService.insertSinaLog(chts.getOut_trade_no(), 0, 0, SinaMemberParmUtil.interfaceName.create_hosting_transfer, chts, chtr);
			}
		}else{
			result = chtr.getResponse_message();
			sinaSendLogService.insertSinaLog(chts.getOut_trade_no(), 0, 0, SinaMemberParmUtil.interfaceName.create_hosting_transfer, chts, chtr);
		}
		return result;
	}

}
