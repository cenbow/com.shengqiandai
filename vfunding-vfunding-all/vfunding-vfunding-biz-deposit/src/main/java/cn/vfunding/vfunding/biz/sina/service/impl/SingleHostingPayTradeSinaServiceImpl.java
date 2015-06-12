package cn.vfunding.vfunding.biz.sina.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.exception.SinaException;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.service.ISingleHostingPayTradeSinaService;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateSingleHostingPayTradeReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateSingleHostingPayTradeSendVO;

import com.alibaba.fastjson.JSON;

@Service("borrowVerifySinaService")
public class SingleHostingPayTradeSinaServiceImpl implements ISingleHostingPayTradeSinaService {
	
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
	
	@Override
	public synchronized String sinaSend(CreateSingleHostingPayTradeSendVO sendVO,String actionName) throws Exception {	
		String result ="failed";
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
					logger.info("*****[sina "+sendVO.getPayee_identity_id()+" 代付 成功]*****");
					//插入接口调用日志
					sinaSendLogService.insertSuccessSinaLog(sendVO.getOut_trade_no() , interfaceName , sendVO, returnVO);
					result = SinaMemberParmUtil.success;
				} else {
					//插入接口调用日志
					sinaSendLogService.insertFailedSinaLog(sendVO.getOut_trade_no()  , interfaceName, sendVO, returnVO);
					// 根据Code 获取字典表中的字典信息
					String tradeStatus = sinaDicService
							.dicLoad("TradeStatus",returnVO.getTrade_status()).getDicMsg();
					logger.error("#####[sina "+sendVO.getPayee_identity_id() +" 代付  异常]"
							+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
							+"[arg="+JSON.toJSONString(sendVO)+"]"
							+"[response="+JSON.toJSONString(returnVO)+"]"
							+"[交易状态="+tradeStatus+"]"
							+ "#####");
					result = tradeStatus;
				}
			} else {
				logger.error("#####[sina "+sendVO.getPayee_identity_id() +" 代付 异常]"
						+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
				//插入接口调用日志
				sinaSendLogService.insertFailedSinaLog(sendVO.getOut_trade_no() , interfaceName,sendVO, returnVO);
				result = returnVO.getResponse_message();
			}
		}
		return result;
	}
	
	@Override
	public String sinaSend(CreateSingleHostingPayTradeSendVO sendVO,
			String logId, String actionName) throws Exception {
		String result ="failed";
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
					logger.info("*****[sina "+sendVO.getPayee_identity_id()+" 代付 成功]*****");
					//插入接口调用日志
					sinaSendLogService.insertSuccessSinaLog(logId , interfaceName , sendVO, returnVO);
					result = SinaMemberParmUtil.success;
				} else {
					//插入接口调用日志
					sinaSendLogService.insertFailedSinaLog(logId , interfaceName, sendVO, returnVO);
					// 根据Code 获取字典表中的字典信息
					String tradeStatus = sinaDicService
							.dicLoad("TradeStatus",returnVO.getTrade_status()).getDicMsg();
					logger.error("#####[sina "+sendVO.getPayee_identity_id() +" 代付  异常]"
							+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
							+"[arg="+JSON.toJSONString(sendVO)+"]"
							+"[response="+JSON.toJSONString(returnVO)+"]"
							+"[交易状态="+tradeStatus+"]"
							+ "#####");
					result = tradeStatus;
				}
			} else {
				logger.error("#####[sina "+sendVO.getPayee_identity_id() +" 代付 异常]"
						+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
				//插入接口调用日志
				sinaSendLogService.insertFailedSinaLog(logId, interfaceName,sendVO, returnVO);
				result = returnVO.getResponse_message();
			}
		}
		return result;
	}
}
