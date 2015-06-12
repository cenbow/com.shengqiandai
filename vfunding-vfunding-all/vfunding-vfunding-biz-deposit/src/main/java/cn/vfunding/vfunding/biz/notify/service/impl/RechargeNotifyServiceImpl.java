package cn.vfunding.vfunding.biz.notify.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.notify.service.IRechargeNotifyService;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.util.SinaNotifyUtil;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.notify.RechargeNotifyVO;

@Service("rechargeNotifyService")
public class RechargeNotifyServiceImpl implements IRechargeNotifyService {
	
	//日志对象
	private Logger logger = Logger.getLogger("sinaNotifyActionLog");
	
	//充值service
	@Autowired
	private IAccountRechargeService accountRecharageService;
	
	/**
	 * 异步回调充值
	 * @param request
	 * @return result
	 * @author louchen 2015-01-21
	 */
	@Override
	public String recharge(RechargeNotifyVO vo) throws Exception {
		logger.info("md5Key:"+SinaParamsUtil.getInstance().getKeyMD5());
		logger.info("RSAKey:"+SinaParamsUtil.getInstance().getKeyRSA());
		String result = "FAILED";
		Map<String,String> map = new HashMap<String,String>();
		map.put("deposit_amount", vo.getDeposit_amount());
		map.put("notify_type", vo.getNotify_type());
		map.put("notify_time", vo.getNotify_time());
		map.put("outer_trade_no", vo.getOuter_trade_no());
		map.put("inner_trade_no", vo.getInner_trade_no());
		map.put("deposit_status", vo.getDeposit_status());
		map.put("notify_id", vo.getNotify_id());
		map.put("_input_charset", vo.get_input_charset());
		map.put("sign", vo.getSign());
		map.put("sign_type", vo.getSign_type());
		map.put("version", vo.getVersion());
		logger.info("新浪通知网银充值消息开始");
		String depositStatus = map.get("deposit_status");  //充值状态
		String outerTradeNo = map.get("outer_trade_no");   //流水号
		String depositAmount = map.get("deposit_amount"); //充值金额
		//校验签名
		if(SinaNotifyUtil.checkNotifySign(map)){
			//deposit_amount
			//notify_type
			//notify_time
			//outer_trade_no
			//inner_trade_no
			//deposit_status
			//notify_id
			logger.info("通知状态:"+depositStatus);
			if(depositStatus.equals(SinaMemberParmUtil.SUCCESS)){
				logger.info("新浪通知网银充值消息--流水号【"+outerTradeNo+"】--更新SUCCESS充值记录开始");
				try{
					this.accountRecharageService.sinaReturnUpdateStatus(outerTradeNo,depositAmount,true);
				}catch (Exception e) {
					logger.info(e.getMessage());
				}				
				logger.info("新浪通知网银充值消息--流水号【"+outerTradeNo+"】--更新SUCCESS充值记录完成");
			}else{
				logger.info("新浪通知网银充值消息--流水号【"+outerTradeNo+"】--更新FAILED充值记录开始");
				try{
					this.accountRecharageService.sinaReturnUpdateStatus(outerTradeNo,depositAmount,false);
				}catch (Exception e) {
					logger.info(e.getMessage());
				}				
				logger.info("新浪通知网银充值消息--流水号【"+outerTradeNo+"】--更新FAILED充值记录完成");
			}
			//返回消息状态SUCCESS 表示已接受到新浪请求，无需重复再发送
			result = SinaMemberParmUtil.SUCCESS;
		}else{
			//签名验证失败
			logger.info("新浪通知网银充值消息--流水号【"+outerTradeNo+"】--出错:验证签名失败");
			result = SinaMemberParmUtil.ResponseCode.ILLEGAL_SIGN;
		}
		logger.info("新浪通知网银充值消息完成");
		return result;
		
	}
	
}
