package cn.vfunding.vfunding.biz.sina.service.impl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.exception.SinaException;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.service.IAccountRechargeSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.tools.Tools;
import cn.vfunding.vfunding.biz.sina.tools.VoToMapUtil;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.util.SinaSignUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.BaseSinaReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateHostingDepositReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.AdvanceHostingPaySendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingDepositSendVO;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;

import com.alibaba.fastjson.JSON;

@Service("accountRechargeSinaService")
public class AccountRechargeSinaServiceImpl implements
		IAccountRechargeSinaService {
	//新浪推送service
	@Autowired
	private ISinaSendService sinaSendService;
	//新浪推送日志
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	//新浪推送字典
	@Autowired
	private ISinaDicService sinaDicService;
	//新浪银行卡
	@Autowired
	private ISinaCardService sinaCardService;
	//充值service
	@Autowired
	private IAccountRechargeService accountRecharageService;
	
	//其他相关mapper
	@Autowired
	private UserMapper userMapper;
	
	//充值用户
	private User rechargeUser;
	
	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");
	
	/**
	 * 充值_网银支付
	 * @param rec
	 * @param bankCode
	 * @return result
	 * @author louchen 2015-01-19
	 */
	@Override
	public String doUserRechargeByOnlineBank(AccountRecharge rec,String bankCode)
			throws Exception {
		logger.info("*****[sina "+rec.getUserId()+" 充值(网银),流水号 :"+rec.getTradeNo()+", 请求开始]*****");
		if(EmptyUtil.isEmpty(rec)||EmptyUtil.isEmpty(bankCode)){
			logger.error("#####[sina "+rec.getUserId() +" 充值(网银) 异常][参数对象为空]#####");
			throw new Exception("流水号:"+rec.getTradeNo()+",参数对象为空");
		}else{
			//数据校验
			BigDecimal zero = new BigDecimal(0);
			if(EmptyUtil.isEmpty(rec.getMoney())){
				logger.error("#####[sina "+rec.getUserId() +" 充值(网银) 异常][充值金额为空]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",充值金额为空");
			}else if(zero.compareTo(rec.getMoney())>=0){
				logger.error("#####[sina "+rec.getUserId() +" 充值(网银) 异常][充值金额必须大于0元]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",充值金额必须大于0元");
			}else if(rec.getMoney().scale()>2){
				logger.error("#####[sina "+rec.getUserId() +" 充值(网银) 异常][充值金额最多保留两位小数]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",充值金额最多保留两位小数");
			}
			//回调地址
			String returnUrl = SinaParamsUtil.getInstance().getReturnUrlHost()+"/account/sinaRechargeReturn/"+rec.getTradeNo();	
			//通知地址
			String notifyUrl = SinaParamsUtil.getInstance().getNotifyUrlHost()+"/sinaNotifyAction/recharge";	
			//生成sendVO
			CreateHostingDepositSendVO sendVO = this.getSendVO(rec);
			sendVO.setReturn_url(returnUrl);
			sendVO.setNotify_url(notifyUrl);
			//充值列表
			sendVO.setPay_method(SinaMemberParmUtil.PayMethod.OnlineBank+"^"+rec.getMoney()+"^"+bankCode
					+","+SinaMemberParmUtil.CardType.DEBIT+","+SinaMemberParmUtil.CardParam.C); 
			//sendVO签名
			sendVO.setSign(SinaSignUtil.getSignMsg(sendVO));
			//新浪接口地址
			String url = SinaParamsUtil.getInstance().getTradesUrl();
			//日志记录发送参数
			//logger.info("发送参数:"+sendVO.toString());
			//新浪接口地址的参数
			Map<String, String> params = VoToMapUtil.voToMap(sendVO, "send");
			String param = Tools.createLinkString(params, false);
			//新浪接口地址+参数
			String result=url+"?"+param;
			logger.info("*****[sina "+rec.getUserId()+" 充值(网银),流水号 :"+rec.getTradeNo()+", 请求完成]*****");
			return result;			
		}		
	}

	/**
	 * 充值_快捷支付
	 * @param accountCash
	 * @return result
	 * @author louchen 2015-01-19
	 */
	@Override
	public String doUserRechargeByQuickPay(AccountRecharge rec,Integer scId,String bankCode)
			throws Exception {
		return null;
	}	
	
	/**
	 * 充值_绑卡支付
	 * @param rec
	 * @param sc
	 * @return result
	 * @author louchen 2015-01-19
	 */
	@Override
	public Map<String,Object> doUserRechargeByBindingPay(AccountRecharge rec,SinaCard sc) throws Exception {
		logger.info("*****[sina "+rec.getUserId()+" 充值(绑卡),流水号 :"+rec.getTradeNo()+", 请求开始]*****");
		if(EmptyUtil.isEmpty(rec)){
			logger.error("#####[sina "+rec.getUserId() +" 充值(绑卡) 异常][参数对象为空]#####");
			throw new Exception("流水号:"+rec.getTradeNo()+",参数对象为空");
		}else{
			//数据校验
			BigDecimal zero = new BigDecimal(0);
			if(EmptyUtil.isEmpty(rec.getMoney())){
				logger.error("#####[sina "+rec.getUserId() +" 充值(绑卡) 异常][充值金额为空]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",充值金额为空");
			}else if(zero.compareTo(rec.getMoney())>=0){
				logger.error("#####[sina "+rec.getUserId() +" 充值(绑卡) 异常][充值金额必须大于0元]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",充值金额必须大于0元");
			}else if(rec.getMoney().scale()>2){
				logger.error("#####[sina "+rec.getUserId() +" 充值(绑卡) 异常][充值金额最多保留两位小数]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",充值金额最多保留两位小数");
			}else if(EmptyUtil.isEmpty(sc)){
				logger.error("#####[sina "+rec.getUserId() +" 充值(绑卡) 异常][新浪银行卡对象为空]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",新浪银行卡对象为空");
			}else if(EmptyUtil.isEmpty(sc.getSinaCard())){
				logger.error("#####[sina "+rec.getUserId() +" 充值(绑卡) 异常][新浪银行卡sinaCard号为空]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",新浪银行卡sinaCard号为空");
			}else if(EmptyUtil.isEmpty(sc.getUserId())){
				logger.error("#####[sina "+rec.getUserId() +" 充值(绑卡) 异常][新浪银行卡持有人为空]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",新浪银行卡持有人为空");
			}else if(!sc.getUserId().equals(rec.getUserId())){
				logger.error("#####[sina "+rec.getUserId() +" 充值(绑卡) 异常][新浪银行卡持有人与充值账户不是同一人]#####");
				throw new Exception("流水号:"+rec.getTradeNo()+",新浪银行卡持有人与充值账户不是同一人");
			}
			//通知地址
			String notifyUrl = SinaParamsUtil.getInstance().getNotifyUrlHost()+"/sinaNotifyAction/recharge";
			//生成sendVO
			CreateHostingDepositSendVO sendVO = this.getSendVO(rec);
			//set通知地址
			sendVO.setNotify_url(notifyUrl);
			//充值列表
			sendVO.setPay_method(SinaMemberParmUtil.PayMethod.BindingPay+"^"+rec.getMoney()+"^"+sc.getSinaCard());  
			//发送新浪接口
			Map<String,Object> result =  this.sendSinaByBindingPay(sendVO,rec.getUserId());
			logger.info("*****[sina "+rec.getUserId()+" 充值(绑卡),流水号 :"+rec.getTradeNo()+", 请求完成]*****");
			return result;
		}
	}
	
	/**
	 * 获取CreateHostingDepositSendVO
	 * @param rec
	 * @return
	 */
	private CreateHostingDepositSendVO getSendVO(AccountRecharge rec) {
		//基本业务参数
		String accountType = this.getUserAccountType(rec.getUserId());
		//sendVO拼参数
		CreateHostingDepositSendVO sendVO = new CreateHostingDepositSendVO();
		//交易号
		sendVO.setOut_trade_no(rec.getTradeNo());
		//充值ID
		sendVO.setIdentity_id(rec.getUserId().toString());
		//新浪账户ID类型
		sendVO.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
		//新浪账户类型
		sendVO.setAccount_type(accountType);
		///充值金额
		sendVO.setAmount(rec.getMoney().toString());
		//充值ip
//		sendVO.setPayer_ip(rec.getAddip());
		return sendVO;
	}
	
	/**
	 * 创建充值对象
	 * @param userId
	 * 	充值用户
	 * @param money
	 * 	充值金额
	 * @param ip
	 * 	ip地址
	 * @return
	 * @author louchen 2015-01-22
	 */
	public synchronized AccountRecharge getAccountRecharge(Integer userId,String money,String ip,String remark){
		AccountRecharge rec = new AccountRecharge();
		//充值流水号
		rec.setTradeNo(String.valueOf(System.currentTimeMillis()));
		//充值状态,原0,1,2,3后新增的状态，等待充值
		rec.setStatus((byte) 4);
		//充值账户
		rec.setUserId(userId);
		//充值金额
		rec.setMoney(new BigDecimal(money));
		//充值渠道
		rec.setPayment((short) 57); //新浪支付
		//充值类型 线上充值
		rec.setType((byte) 1);
		//充值时间
		rec.setAddtime(Integer.parseInt(DateUtil.getTime()));
		//ip地址
		rec.setAddip(ip);	
		//remark
		rec.setRemark(remark);
		//数据库插入充值记录
		this.accountRecharageService.insertSelective(rec);
		if(ip.equals("手机APP用户")){
			rec.setAddip(null);
		}		
		return rec;
	}
	
	/**
	 * 获取充值用户新浪账户类型,本地数据库校验
	 * @param userId
	 * @return
	 */
	private String getUserAccountType(Integer userId) {
		String accountType;
		this.rechargeUser = this.userMapper.selectByPrimaryKey(userId);
		if(this.rechargeUser.getTypeId().equals(40)){
			//借款人账户是基本账户
			accountType = SinaMemberParmUtil.AccountType.BASIC;
		}else{
			//其他人账户是存钱罐账户
			accountType = SinaMemberParmUtil.AccountType.SAVING_POT;
		}
		return accountType;
	}
	
	/**
	 * 发送新浪接口_绑卡支付
	 * @param sendVO
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	private Map<String,Object> sendSinaByBindingPay(CreateHostingDepositSendVO sendVO,Integer userId) throws Exception{
		String result = "failed";
		CreateHostingDepositReturnVO returnVO = null;
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			returnVO = this.sinaSendService.sinaSendMas(sendVO, CreateHostingDepositReturnVO.class);
		} catch (Exception e) {
			e.printStackTrace();
			returnVO = new CreateHostingDepositReturnVO();
			returnVO.setResponse_code("JAVA EXCETPION");
			returnVO.setResponse_message(e.getMessage());
			throw new SinaException(SinaException.SINA_EXCEPTION,e);
		}finally{
			if (EmptyUtil.isNotEmpty(returnVO) 
					&& EmptyUtil.isNotEmpty(returnVO.getResponse_code())
					&& returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
				logger.info("*****[sina "+userId+" 充值(绑卡) 成功]*****");
				this.sinaSendLogService.insertSuccessSinaLog(sendVO.getOut_trade_no(), SinaMemberParmUtil.interfaceName.create_hosting_deposit+"|绑卡支付", sendVO, returnVO);
				result = SinaMemberParmUtil.success;
				
			}else{
				logger.error("#####[sina "+userId+" 充值(绑卡) 失败]"
						+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
				this.sinaSendLogService.insertFailedSinaLog(sendVO.getOut_trade_no(), SinaMemberParmUtil.interfaceName.create_hosting_deposit+"|绑卡支付", sendVO, returnVO);
				result = returnVO.getResponse_message();
			}			
			map.put("rechargeByBindingPayReturnVO", returnVO);
			map.put("result", result);
		}
		return map;
	}
	
	/**
	 * 发送新浪接口_支付推进
	 * @param sendVO
	 * @param tradeNo
	 * @return
	 * @throws Exception
	 */
	private String sendSinaAdvancePay(AdvanceHostingPaySendVO sendVO,String tradeNo) throws Exception{
		String result = "failed";
		BaseSinaReturnVO returnVO = new BaseSinaReturnVO();
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
				logger.info("*****[sina 充值流水号:"+tradeNo+" 绑卡支付推进  成功]*****");
				this.sinaSendLogService.insertSuccessSinaLog(sendVO.getOut_advance_no(), SinaMemberParmUtil.interfaceName.advance_hosting_pay+"|绑卡支付推进", sendVO, returnVO);
				result = SinaMemberParmUtil.success;
			} else {
				logger.error("#####[sina 充值流水号:"+tradeNo+" 绑卡支付推进 异常]"
						+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
				this.sinaSendLogService.insertFailedSinaLog(sendVO.getOut_advance_no(), SinaMemberParmUtil.interfaceName.advance_hosting_pay+"|绑卡支付推进", sendVO, returnVO);
				result = returnVO.getResponse_message();
			}
		}
		return result;			
	}
	
	/**
	 * 发送新浪接口_通用
	 * @param sendVO
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	private String sendSina(CreateHostingDepositSendVO sendVO) throws Exception{
		String result = "";
		//returnVO得到返回结果
		CreateHostingDepositReturnVO returnVO = this.sinaSendService.sinaSendMas(sendVO, CreateHostingDepositReturnVO.class);
		if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
			if(returnVO.getDeposit_status().equals(SinaMemberParmUtil.PayStatus.SUCCESS)){
				//成功发送日志
				this.sinaSendLogService.insertSuccessSinaLog(sendVO.getOut_trade_no(), SinaMemberParmUtil.interfaceName.create_hosting_deposit, sendVO, returnVO);
				result = SinaMemberParmUtil.success;
			}else{
				//错误发送日志
				this.sinaSendLogService.insertFailedSinaLog(sendVO.getOut_trade_no(), SinaMemberParmUtil.interfaceName.create_hosting_collect_trade, sendVO, returnVO);
				// 根据Code 获取字典表中的字典信息
				String payStatus = sinaDicService
						.dicLoad(SinaMemberParmUtil.PaperType.PaperType,returnVO.getDeposit_status()).getDicMsg();
				result = "支付状态:" + payStatus;
			}
		} else {
			//错误发送日志
			this.sinaSendLogService.insertFailedSinaLog(sendVO.getOut_trade_no(), SinaMemberParmUtil.interfaceName.create_hosting_deposit, sendVO, returnVO);
			result = returnVO.getResponse_message();
		}
		return result;			
	}
	
	/**
	 * 新浪支付推进
	 * @param captcha
	 * @param ticket
	 * @param tradeNo
	 * @return
	 * @throws Exception
	 */
	@Override
	public synchronized String doUserRechargeByBindingPayAdvance(String captcha,String ticket,String tradeNo) throws Exception {
		//拼sendVO
		AdvanceHostingPaySendVO sendVO = new AdvanceHostingPaySendVO();
		sendVO.setOut_advance_no(tradeNo);
		sendVO.setTicket(ticket);
		sendVO.setValidate_code(captcha);
		//发送新浪接口
		String result =  this.sendSinaAdvancePay(sendVO,tradeNo);
		//更新充值记录                                         2015-02-13     已修改成回调后在更新微积金账户
//		AccountRecharge rec = this.accountRecharageService.selectByTradeNo(tradeNo);
//		if(result.equals(SinaMemberParmUtil.success)){
//			this.accountRecharageService.sinaReturnUpdateStatus(rec,rec.getMoney().toString(),true);
//		}else{
//			this.accountRecharageService.sinaReturnUpdateStatus(rec,rec.getMoney().toString(),false);
//		}		
		return result;
	}
	
	/**
	 * 校验绑卡支付推进
	 * @param userId
	 * 	用户id
	 * @param captcha
	 *  验证码
	 * @param tradeNo
	 *  充值流水号
	 * @param request
	 *  session中的rechargeByBindingPayReturnVO
	 */
	@Override
	public String checkRechargeByBindingPayAdvance(Integer userId,String captcha,String tradeNo) throws Exception {
		String result = "failed";
		if(EmptyUtil.isEmpty(captcha)){
			result = "验证码不能为空";				
		}else if(EmptyUtil.isEmpty(tradeNo)){
			result = "流水号不能为空";
		}else if(EmptyUtil.isEmpty(userId)){
			result = "充值账户不能为空";
		}else{
			AccountRecharge rec = this.accountRecharageService.selectByTradeNo(tradeNo);
			if(EmptyUtil.isEmpty(rec)){
				result = "充值申请流水号不存在,充值失败";
			}else if(!rec.getUserId().equals(userId)){
				result = "充值申请人ID不一致";
				try{
					this.accountRecharageService.sinaReturnUpdateStatus(tradeNo, null, false);
				}catch (Exception e) {
					logger.error("#####[sina "+rec.getUserId() +" 绑卡支付推进 异常]["+e.getMessage()+"]#####");
					e.printStackTrace();
				}	
			}else{
				result = "success";
			}
		}
		logger.info("*****校验绑卡支付推进--流水号:"+tradeNo+",校验状态:"+result+"*****");
		return result;
	}

}
