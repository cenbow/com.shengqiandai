package cn.vfunding.vfunding.biz.sina.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.exception.SinaException;
import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateHostingCollectTradeReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;

import com.alibaba.fastjson.JSON;

@Service("borrowTenderSinaService")
public class HostingCollectTradeSinaServiceImpl implements IHostingCollectTradeSinaService {
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
	 * 重载发送
	 * @param sendVO
	 * @param actionName
	 * @return
	 * @throws Exception
	 */
	@Override
	public synchronized String sinaSend(CreateHostingCollectTradeSendVO sendVO,String actionName)
			throws Exception {	
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
					logger.info("*****[sina "+sendVO.getPayer_id()+" 代收 成功]*****");
					sinaSendLogService.insertSuccessSinaLog(sendVO.getOut_trade_no(),interfaceName , sendVO, returnVO);
					result = SinaMemberParmUtil.success;
				} else {
					sinaSendLogService.insertFailedSinaLog(sendVO.getOut_trade_no(),interfaceName , sendVO, returnVO);
					String tradeStatus = sinaDicService
							.dicLoad(SinaMemberParmUtil.TradeStatus.TradeStatus,returnVO.getTrade_status()).getDicMsg();
					logger.error("#####[sina "+sendVO.getPayer_id() +" 代收 异常]"
							+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
							+"[arg="+JSON.toJSONString(sendVO)+"]"
							+"[response="+JSON.toJSONString(returnVO)+"]"
							+"[交易状态="+tradeStatus+"]"
							+ "#####");
					result = tradeStatus;
				}
			} else {
				logger.error("#####[sina "+sendVO.getPayer_id() +" 代收 异常]"
						+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
				sinaSendLogService.insertFailedSinaLog(sendVO.getOut_trade_no(),interfaceName , sendVO, returnVO);
				result = returnVO.getResponse_message();
			}	
		}			
		return result;		
	}

	@Override
	public String sinaSend(CreateHostingCollectTradeSendVO sendVO,
			String logId, String actionName) throws Exception {
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
					logger.info("*****[sina "+sendVO.getPayer_id()+" 代收 成功]*****");
					sinaSendLogService.insertSuccessSinaLog(logId,interfaceName , sendVO, returnVO);
					result = SinaMemberParmUtil.success;
				} else {
					sinaSendLogService.insertFailedSinaLog(logId,interfaceName , sendVO, returnVO);
					String tradeStatus = sinaDicService
							.dicLoad(SinaMemberParmUtil.TradeStatus.TradeStatus,returnVO.getTrade_status()).getDicMsg();
					logger.error("#####[sina "+sendVO.getPayer_id() +" 代收 异常]"
							+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
							+"[arg="+JSON.toJSONString(sendVO)+"]"
							+"[response="+JSON.toJSONString(returnVO)+"]"
							+"[交易状态="+tradeStatus+"]"
							+ "#####");
					result = tradeStatus;
				}
			} else {
				logger.error("#####[sina "+sendVO.getPayer_id() +" 代收 异常]"
						+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
				sinaSendLogService.insertFailedSinaLog(logId,interfaceName , sendVO, returnVO);
				result = returnVO.getResponse_message();
			}	
		}			
		return result;	
	}
	
}
