package cn.vfunding.vfunding.biz.sina.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.exception.SinaException;
import cn.vfunding.vfunding.biz.sina.model.SinaSendLogWithBLOBs;
import cn.vfunding.vfunding.biz.sina.service.IGiftBoxSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateHostingCollectTradeReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateSingleHostingPayTradeReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateSingleHostingPayTradeSendVO;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;

import com.alibaba.fastjson.JSON;

@Service("giftBoxSinaServiceImpl")
public class GiftBoxSinaServiceImpl implements IGiftBoxSinaService {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISinaSendService sinaSendService;
	
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	
	@Autowired
	private ISinaDicService sinaDicService;
	
	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");

	/**
	 * 请求新浪接口(代收)
	 * @param sendVO
	 * @param actionName
	 * @return
	 * @throws Exception
	 * @author louchen 2015-02-04
	 */
	private String sinaReceiveSend(CreateHostingCollectTradeSendVO sendVO,String actionName,GiftboxMessage gm)
			throws Exception {
		if (sendVO == null) {
			throw new Exception("参数对象为空");
		} else {
			String result ="failed";
			String interfaceName = SinaMemberParmUtil.interfaceName.create_hosting_collect_trade + "|"+actionName;
			CreateHostingCollectTradeReturnVO returnVO = null;
			try {
				returnVO = sinaSendService
						.sinaSendMas(sendVO, CreateHostingCollectTradeReturnVO.class);
			} catch (Exception e) {
				e.printStackTrace();
				returnVO = new CreateHostingCollectTradeReturnVO();
				returnVO.setResponse_code("JAVA EXCEPTION");
				returnVO.setResponse_message(e.getMessage());
				throw new SinaException(SinaException.SINA_EXCEPTION,e);
			} finally{
				if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
					if (returnVO.getTrade_status().equals(SinaMemberParmUtil.TradeStatus.PAY_FINISHED)) {
						logger.info("*****[sina "+SinaParamsUtil.getInstance().getVfundingCompanyAccount()+" 代收 成功]*****");
						sinaSendLogService.insertSuccessSinaLog(this.getGiftHongbaoReceiveSinaSendLogId(gm),interfaceName , sendVO, returnVO);
						result = SinaMemberParmUtil.success;
					} else {
						sinaSendLogService.insertFailedSinaLog(this.getGiftHongbaoReceiveSinaSendLogId(gm),interfaceName , sendVO, returnVO);
						// 根据Code 获取字典表中的字典信息
						String tradeStatus = sinaDicService
								.dicLoad(SinaMemberParmUtil.TradeStatus.TradeStatus,returnVO.getTrade_status()).getDicMsg();
						logger.error("#####[sina "+SinaParamsUtil.getInstance().getVfundingCompanyAccount() +" 代收 异常]"
								+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
								+"[arg="+JSON.toJSONString(sendVO)+"]"
								+"[response="+JSON.toJSONString(returnVO)+"]"
								+"[交易状态="+tradeStatus+"]"
								+ "#####");
						result = tradeStatus;
					}
				} else {
					logger.error("#####[sina "+SinaParamsUtil.getInstance().getVfundingCompanyAccount() +" 代收 异常]"
							+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
							+"[arg="+JSON.toJSONString(sendVO)+"]"
							+"[response="+JSON.toJSONString(returnVO)+"]"
							+ "#####");
					sinaSendLogService.insertFailedSinaLog(this.getGiftHongbaoReceiveSinaSendLogId(gm),interfaceName , sendVO, returnVO);
				}	
			}			
			return result;
		}	
	}
	
	/**
	 * 请求新浪接口(代付)
	 * @param sendVO
	 * @param actionName
	 * @return
	 * @throws Exception
	 * @author louchen 2015-02-04
	 */
	private String sinaPaySend(CreateSingleHostingPayTradeSendVO sendVO,String actionName,Integer userId,GiftboxMessage gm) throws Exception {
		String result = "failed";
		String interfaceName = SinaMemberParmUtil.interfaceName.create_single_hosting_pay_trade + "|"+actionName;
		CreateSingleHostingPayTradeReturnVO returnVO = null;
		try {
			returnVO = sinaSendService.sinaSendMas(sendVO, CreateSingleHostingPayTradeReturnVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			returnVO = new CreateSingleHostingPayTradeReturnVO();
			returnVO.setResponse_code("JAVA EXCEPTION");
			returnVO.setResponse_message(e.getMessage());
			throw new SinaException(SinaException.SINA_EXCEPTION,e);
		} finally{
			if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
				if (returnVO.getTrade_status().equals("PAY_FINISHED")) {
					logger.info("*****[sina "+userId+" 代付  成功]*****");
					sinaSendLogService.insertSuccessSinaLog(this.getGiftHongbaoPaySinaSendLogId(gm),interfaceName , sendVO, returnVO);
					result = SinaMemberParmUtil.success;
				} else {
					sinaSendLogService.insertFailedSinaLog(this.getGiftHongbaoPaySinaSendLogId(gm),interfaceName, sendVO, returnVO);
					// 根据Code 获取字典表中的字典信息
					String tradeStatus = sinaDicService
							.dicLoad("TradeStatus",returnVO.getTrade_status()).getDicMsg();
					logger.error("#####[sina "+userId +" 代付  异常]"
							+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
							+"[arg="+JSON.toJSONString(sendVO)+"]"
							+"[response="+JSON.toJSONString(returnVO)+"]"
							+"[交易状态="+tradeStatus+"]"
							+ "#####");
					result = tradeStatus;
				}
			} else {
				logger.error("#####[sina "+userId +" 代付  异常]"
						+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
				sinaSendLogService.insertSinaLog(this.getGiftHongbaoPaySinaSendLogId(gm), 0, 0, interfaceName,sendVO, returnVO);
			}
		}
		return result;
	}
	
	
	/**
	 * 红包代收SendVO
	 * @param gm
	 * @return
	 * @author louchen 2015-02-04
	 */
	private CreateHostingCollectTradeSendVO getGiftHongBaoReceiveSendVO(GiftboxMessage gm){
		CreateHostingCollectTradeSendVO sendVO = new CreateHostingCollectTradeSendVO();
		sendVO.setOut_trade_no(System.currentTimeMillis()+"|"+gm.getId()); //流水号内部唯一
		sendVO.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._1001); 
		sendVO.setSummary("红包使用-托管代收");
		sendVO.setPayer_id(SinaParamsUtil.getInstance().getVfundingCompanyAccount());//付款人 _微积金邮箱账户
		sendVO.setPayer_identity_type(SinaMemberParmUtil.IdentityType.EMAIL); //付款人_类型
		sendVO.setPay_method("balance^" + gm.getMoney() + "^BASIC"); //支付方式^金额^账户类型
		return sendVO;
	}
	
	/**
	 * 红包代付SendVO
	 * @param gm
	 * @param typeId
	 * @return
	 */
	private CreateSingleHostingPayTradeSendVO getGiftHongBaoPaySendVO(GiftboxMessage gm){
		UserWithBLOBs user = this.userService.selectByPrimaryKey(gm.getReceiveUser());
		CreateSingleHostingPayTradeSendVO sendVO = new CreateSingleHostingPayTradeSendVO();
		sendVO.setOut_trade_no(System.currentTimeMillis()+"|"+gm.getId()); //流水号内部唯一
		sendVO.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2001);
		sendVO.setPayee_identity_id(gm.getReceiveUser().toString());//收款人
		sendVO.setPayee_identity_type(SinaMemberParmUtil.IdentityType.UID);
		if (!user.getTypeId().equals(40)) {// 收款人账户类型
			sendVO.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
		}else{
			sendVO.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
		}
		sendVO.setAmount(gm.getMoney() + "");
		sendVO.setSummary("红包使用-托管代付");
		return sendVO;
	}
	
	
	/**
	 * 返回红包代收SendLog主键
	 * @return
	 * @author louchen 2015-02-04
	 */
	private String getGiftHongbaoReceiveSinaSendLogId(GiftboxMessage gm){
		return "CollectTrade|giftHongBao|"+gm.getId();
	}	
	
	/**
	 * 返回红包代付SendLog主键
	 * @return
	 * @author louchen 2015-02-04
	 */
	private String getGiftHongbaoPaySinaSendLogId(GiftboxMessage gm){
		return "SingleHostingPayTrade|giftHongBao|"+gm.getId();
	}	
	
	/**
	 * 是否有重复红包代收
	 * @author louchen 2015-02-04
	 */
	private Boolean checkRepeatReceiveGiftHongbao(GiftboxMessage gm)	throws Exception {
		SinaSendLogWithBLOBs ssl = this.sinaSendLogService.selectSuccessLogByOrderNo(this.getGiftHongbaoReceiveSinaSendLogId(gm));
		if(EmptyUtil.isEmpty(ssl)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 是否有重复红包代付
	 * @author louchen 2015-02-04
	 */
	private Boolean checkRepeatPayGiftHongbao(GiftboxMessage gm)	throws Exception {
		SinaSendLogWithBLOBs ssl = this.sinaSendLogService.selectSuccessLogByOrderNo(this.getGiftHongbaoPaySinaSendLogId(gm));
		if(EmptyUtil.isEmpty(ssl)){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 红包使用(代收)发送sina请求
	 * @param gm
	 * @return
	 * @author louchen 2015-02-04
	 */
	@Override
	public synchronized String GiftHongbaoUseReceiveSinaSend(GiftboxMessage gm) throws Exception {
		String result = "FAILED";
		if(this.checkRepeatReceiveGiftHongbao(gm)){
			logger.error("#####[sina "+SinaParamsUtil.getInstance().getVfundingCompanyAccount()+" 代收(使用红包id:"+gm.getId()+")  重复请求]#####");
			result = SinaMemberParmUtil.success;
		}else{
			CreateHostingCollectTradeSendVO sendVO = this.getGiftHongBaoReceiveSendVO(gm);
			logger.info("*****[sina "+SinaParamsUtil.getInstance().getVfundingCompanyAccount()+" 代收(使用红包id:"+gm.getId()+")  请求开始]*****");
			result = this.sinaReceiveSend(sendVO,"红包使用",gm);			
			logger.info("*****[sina "+SinaParamsUtil.getInstance().getVfundingCompanyAccount()+" 代收(使用红包id:"+gm.getId()+")  请求完成]*****");
		}
		return result;
	}

	/**
	 * 红包使用(代付)发送sina请求
	 * @param gm
	 * @return
	 * @author louchen 2015-02-04
	 */
	@Override
	public synchronized String GiftHongbaoUsePaySinaSend(GiftboxMessage gm) throws Exception {
		String result = "FAILED";
		if(this.checkRepeatPayGiftHongbao(gm)){
			logger.error("#####[sina "+gm.getReceiveUser()+" 代付(使用红包id:"+gm.getId()+") 重复请求]#####");
			result = SinaMemberParmUtil.success;
		}else{
			CreateSingleHostingPayTradeSendVO sendVO = this.getGiftHongBaoPaySendVO(gm);
			logger.info("*****[sina "+gm.getReceiveUser()+" 代付(使用红包id:"+gm.getId()+") 请求开始]*****");
			result = this.sinaPaySend(sendVO,"红包使用",gm.getReceiveUser(),gm);			
			logger.info("*****[sina "+gm.getReceiveUser()+" 代付(使用红包id:"+gm.getId()+") 请求完成]*****");
		}
		return result;
	}	
	
}
