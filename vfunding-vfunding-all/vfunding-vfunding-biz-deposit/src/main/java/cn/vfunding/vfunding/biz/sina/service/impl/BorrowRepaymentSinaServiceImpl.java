package cn.vfunding.vfunding.biz.sina.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowCollectionMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowRepaymentMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowCollection;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.exception.SinaException;
import cn.vfunding.vfunding.biz.sina.model.SinaSendLogWithBLOBs;
import cn.vfunding.vfunding.biz.sina.service.IBorrowRepaymentSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.BaseSinaReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateHostingCollectTradeReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateBatchHostingPayTradeSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;

import com.alibaba.fastjson.JSON;

@Service("borrowRepaymentSinaService")
public class BorrowRepaymentSinaServiceImpl implements
		IBorrowRepaymentSinaService {
	//新浪推送service
	@Autowired
	private ISinaSendService sinaSendService;
	//新浪推送日志
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	//新浪推送字典
	@Autowired
	private ISinaDicService sinaDicService;
	//其他相关mapper
	@Autowired
	private BorrowMapper borrowMapper;
	@Autowired
	private BorrowTenderMapper borrowTenderMapper;
	@Autowired
	private BorrowCollectionMapper borrowCollectionMapper;
	@Autowired
	private BorrowRepaymentMapper borrowRepaymentMapper;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");
	
	/**
	 * 推送借款人还款和推送投资人收益
	 * @param repaymentId
	 * @return result
	 * @author louchen 2015-01-26
	 */
	@Override
	public synchronized String doUserRepayBorrowerAndTender(Integer repaymentId)
			throws Exception {
		String result = this.doUserRepayBorrower(repaymentId);
		//推送借款人还款成功后推送投资人收益
		if(EmptyUtil.isNotEmpty(result) && result.equals(SinaMemberParmUtil.success)){
			result = this.doUserRepayTender(repaymentId);
		}
		return result;
	}

	/**
	 * 推送借款人减少资金，新浪接口创建托管代收
	 * @param repaymentId
	 * @return result
	 * @author louchen 2015-01-16
	 */
	@Override
	public synchronized String doUserRepayBorrower(Integer repaymentId) throws Exception {
		if(EmptyUtil.isEmpty(repaymentId)){
			logger.error("#####[sina repaymentId:"+ repaymentId +" 还款(借款人) 异常][参数对象为空]#####");
			throw new Exception("参数对象为空");
		}else{
			if(this.checkRepeatRepayBorrower(repaymentId)){
				logger.error("#####[sina repaymentId:"+ repaymentId +" 还款(借款人) 失败][重复推送消息]#####");
				return SinaMemberParmUtil.success;
			}else{
				CreateHostingCollectTradeSendVO sendVO = this.getCreateHostingCollectTradeSendVO(repaymentId);
				logger.info("*****[sina repaymentId:"+ repaymentId +" 还款(借款人),请求开始]*****");
				String result = this.sendSinaBorrower(sendVO,repaymentId);
				logger.info("*****[sina repaymentId:"+ repaymentId +" 还款(借款人),请求完成]*****");
				return result;
			}
		}
	}
	
	/**
	 * 获取托管代收VO
	 * @param repaymentId
	 * @return
	 */
	private CreateHostingCollectTradeSendVO getCreateHostingCollectTradeSendVO(Integer repaymentId){
		CreateHostingCollectTradeSendVO sendVO = new CreateHostingCollectTradeSendVO();
		BorrowRepayment borrowRepayment = this.borrowRepaymentMapper.selectByPrimaryKey(repaymentId);
		Borrow borrow = borrowMapper.selectById(borrowRepayment.getBorrowId());
		Integer borrowUserId = borrow.getUserId();
		//借款人新浪账户减少资金
		String repaymentYesaccount = borrowRepayment.getRepaymentYesaccount();
		//交易单号
		sendVO.setOut_trade_no(new Date().getTime()+"|"+repaymentId);
		sendVO.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._1002);
		sendVO.setPayer_id(borrowUserId.toString());
		sendVO.setPayer_identity_type(SinaMemberParmUtil.IdentityType.UID);
//		sendVO.setPayer_ip(borrowRepayment.getAddip());
		//借款人是基本账户
		sendVO.setPay_method("balance^" + repaymentYesaccount + "^"+SinaMemberParmUtil.AccountType.BASIC);
		sendVO.setSummary("用户还款后推送借款人");
		return sendVO;
	}
	
	/**
	 * 发送新浪接口_单笔代收借款人
	 * @param sendVO
	 * @return
	 * @throws Exception
	 */
	private String sendSinaBorrower(CreateHostingCollectTradeSendVO sendVO,Integer repaymentId) throws Exception{
		String result = "failed";
		CreateHostingCollectTradeReturnVO returnVO = null;
		//returnVO得到返回结果
		try {
			returnVO = this.sinaSendService.sinaSendMas(sendVO, CreateHostingCollectTradeReturnVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			returnVO = new CreateHostingCollectTradeReturnVO();
			returnVO.setResponse_code("JAVA EXCETPION");
			returnVO.setResponse_message(e.getMessage());
			throw new SinaException(SinaException.SINA_EXCEPTION,e);
		} finally{
			if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
				if (returnVO.getTrade_status().equals(SinaMemberParmUtil.TradeStatus.PAY_FINISHED)) {
					logger.info("*****[sina repaymentId:"+ repaymentId +" 还款(借款人),成功]*****");
					this.sinaSendLogService.insertSuccessSinaLog(this.getRepayBorrowerSinaSendLogId(repaymentId), SinaMemberParmUtil.interfaceName.create_hosting_collect_trade+"|还款^借款人还款", sendVO, returnVO);
					result = SinaMemberParmUtil.success;
				} else {
					logger.error("#####[sina repaymentId:"+ repaymentId + " 还款(借款人) 异常]"
							+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
							+"[arg="+JSON.toJSONString(sendVO)+"]"
							+"[response="+JSON.toJSONString(returnVO)+"]"
							+ "#####");
					this.sinaSendLogService.insertFailedSinaLog(this.getRepayBorrowerSinaSendLogId(repaymentId), SinaMemberParmUtil.interfaceName.create_hosting_collect_trade+"|还款^借款人还款", sendVO, returnVO);
					// 根据Code 获取字典表中的字典信息
					String tradeStatus = sinaDicService
							.dicLoad(SinaMemberParmUtil.TradeStatus.TradeStatus,returnVO.getTrade_status()).getDicMsg();		
					result = "交易状态:" + tradeStatus;
				}
			} else {
				logger.error("#####[sina repaymentId:"+ repaymentId + " 还款(借款人) 异常]"
						+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
				this.sinaSendLogService.insertFailedSinaLog(this.getRepayBorrowerSinaSendLogId(repaymentId), SinaMemberParmUtil.interfaceName.create_hosting_collect_trade+"|还款^借款人还款", sendVO, returnVO);
				result = returnVO.getResponse_message();
			}
		}
		return result;
	}
	
	/**
	 * 推送投资人增加资金，新浪接口创建批量代付
	 * @param repaymentId
	 * @return result
	 * @author louchen 2015-01-16
	 */
	@Override
	public synchronized String doUserRepayTender(Integer repaymentId) throws Exception {
		if(EmptyUtil.isEmpty(repaymentId)){
			logger.error("#####[sina repaymentId:"+ repaymentId +" 还款(投资人) 异常][参数对象为空]#####");
			throw new Exception("参数对象为空");
		}else{
			if(this.checkRepeatRepayTender(repaymentId)){
				logger.error("#####[sina repaymentId:"+ repaymentId +" 还款(投资人) 失败][重复推送消息]#####");
				return SinaMemberParmUtil.success;
			}else{
				CreateBatchHostingPayTradeSendVO  sendVO = this.getCreateBatchHostingPayTradeSendVO(repaymentId);
				logger.info("*****[sina repaymentId:"+ repaymentId +" 还款(投资人),请求开始]*****");
				String result = this.sendSinaTender(sendVO,repaymentId);
				logger.info("*****[sina repaymentId:"+ repaymentId +" 还款(投资人),请求完成]*****");
				return result;
			}
		}
	}
	
	/**
	 * 获取批量付款VO
	 * @param repaymentId
	 * @return
	 */
	private CreateBatchHostingPayTradeSendVO getCreateBatchHostingPayTradeSendVO(Integer repaymentId){
		CreateBatchHostingPayTradeSendVO sendVO = new CreateBatchHostingPayTradeSendVO();
		BorrowRepayment borrowRepayment = this.borrowRepaymentMapper.selectByPrimaryKey(repaymentId);
		String tradeList = "";
		List<BorrowTender> tender_lists = this.borrowTenderMapper.selectListByBorrowId(borrowRepayment.getBorrowId());
		for (BorrowTender tender : tender_lists) {
			BorrowCollection collection = this.borrowCollectionMapper.selectByTenderIdOrder(tender.getId(), borrowRepayment.getOrder());
			BigDecimal repayYesaccount = new BigDecimal(EmptyUtil.isEmpty(collection.getRepayAccount())?"0":collection.getRepayAccount());
			BigDecimal serviceFees = new BigDecimal(EmptyUtil.isEmpty(collection.getServiceFees())?"0":collection.getServiceFees());
			BigDecimal guaranteeFees = new BigDecimal(EmptyUtil.isEmpty(collection.getGuaranteeFees())?"0":collection.getGuaranteeFees());
			BigDecimal zero = new BigDecimal(0);
			//该笔代收还款金额必须大于0元再推给新浪，否则不推送
			if(repayYesaccount.compareTo(zero)>0){
				if(EmptyUtil.isNotEmpty(tradeList)){
					//多条目分隔符号
					tradeList+="$";
				}
				//交易单号
				tradeList+=new Date().getTime() + "|" + collection.getId() + "";
				//收款人id
				tradeList+="~"+tender.getUserId();
				//收款人类型
				tradeList+="~"+SinaMemberParmUtil.IdentityType.UID;
				//账户类型
				tradeList+="~"+SinaMemberParmUtil.AccountType.SAVING_POT;
				//金额,还款总金额+服务费+担保费
				tradeList+="~"+repayYesaccount.add(serviceFees.add(guaranteeFees));
				//服务费+担保费必须大于0元才做分账
				if(serviceFees.add(guaranteeFees).compareTo(zero) > 0){
					//分账信息    转账给微积金商户账号,商户用户类型是EMAIL,账户是BASIC
					tradeList+="~"+tender.getUserId()+"^"+SinaMemberParmUtil.IdentityType.UID+"^"+SinaMemberParmUtil.AccountType.SAVING_POT;
					tradeList+="^"+SinaParamsUtil.getInstance().getVfundingCompanyAccount()
					+"^"+SinaMemberParmUtil.IdentityType.EMAIL
					+"^"+SinaMemberParmUtil.AccountType.BASIC
					+"^"+serviceFees.add(guaranteeFees);
				}else{
					//服务费+担保费0元，分账参数置空
					tradeList+="~";
				}		
				//摘要
				tradeList+="~"+"用户还款后推送投资人[userId="+tender.getUserId()+"]";
			}
		}
		sendVO.setOut_pay_no(new Date().getTime()+"|batch|"+repaymentId);
		sendVO.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2002);
		sendVO.setTrade_list(tradeList);
		return sendVO;
	}
	
	/**
	 * 发送新浪接口_批量代付投资人
	 * @param sendVO
	 * @return
	 * @throws Exception
	 */
	private String sendSinaTender(CreateBatchHostingPayTradeSendVO sendVO,Integer repaymentId) throws Exception{
		String result = "faild";
		BaseSinaReturnVO returnVO = null;
		try {
			returnVO = this.sinaSendService.sinaSendMas(sendVO, BaseSinaReturnVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			returnVO = new BaseSinaReturnVO();
			returnVO.setResponse_code("JAVA EXCETPION");
			returnVO.setResponse_message(e.getMessage());
			throw new SinaException(SinaException.SINA_EXCEPTION,e);			
		} finally{
			if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
				logger.info("*****[sina repaymentId:"+ repaymentId +" 还款(投资人),成功]*****");
				this.sinaSendLogService.insertSuccessSinaLog(this.getRepayTenderSinaSendLogId(repaymentId), SinaMemberParmUtil.interfaceName.create_batch_hosting_pay_trade+"|还款^投资人收益", sendVO, returnVO);
				result = SinaMemberParmUtil.success;
			}else{
				logger.error("#####[sina repaymentId:"+ repaymentId + " 还款(投资人) 异常]"
						+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
				this.sinaSendLogService.insertFailedSinaLog(this.getRepayTenderSinaSendLogId(repaymentId), SinaMemberParmUtil.interfaceName.create_batch_hosting_pay_trade+"|还款^投资人收益", sendVO, returnVO);
				result = returnVO.getResponse_message();
			}	
		}
		return result;
	}

	/**
	 * 是否有还款发送成功的日志_借款人
	 */
	@Override
	public Boolean checkRepeatRepayBorrower(Integer repaymentId)
			throws Exception {
		SinaSendLogWithBLOBs ssl = this.sinaSendLogService
				.selectSuccessLogByOrderNo(this.getRepayBorrowerSinaSendLogId(repaymentId));
		if(EmptyUtil.isEmpty(ssl)){
			return false;
		}else{
			return true;
		}
	}

	/**
	 * 是否有还款发送成功的日志_投资人
	 */
	@Override
	public Boolean checkRepeatRepayTender(Integer repaymentId) throws Exception {
		SinaSendLogWithBLOBs ssl = this.sinaSendLogService
				.selectSuccessLogByOrderNo(this.getRepayTenderSinaSendLogId(repaymentId));
		if(EmptyUtil.isEmpty(ssl)){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 返回LogId
	 * @param repaymentId
	 * @return
	 */
	private String getRepayBorrowerSinaSendLogId(Integer repaymentId){
		return "RepayBorrower|"+repaymentId;
	}
	
	/**
	 * 返回LogId
	 * @param repaymentId
	 * @return
	 */
	private String getRepayTenderSinaSendLogId(Integer repaymentId){
		return "RepayTender|"+repaymentId;
	}
}
