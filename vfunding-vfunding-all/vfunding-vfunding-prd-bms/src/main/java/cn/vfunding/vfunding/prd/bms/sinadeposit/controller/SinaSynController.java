//package cn.vfunding.vfunding.prd.bms.sinadeposit.controller;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import cn.vfunding.common.framework.easyui.page.utils.Json;
//import cn.vfunding.common.framework.utils.beans.EmptyUtil;
//import cn.vfunding.vfunding.biz.account.model.Account;
//import cn.vfunding.vfunding.biz.account.service.IAccountService;
//import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
//import cn.vfunding.vfunding.biz.sina.model.SinaSendLogWithBLOBs;
//import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;
//import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
//import cn.vfunding.vfunding.biz.sina.service.ISingleHostingPayTradeSinaService;
//import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
//import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;
//import cn.vfunding.vfunding.biz.sina.vo.sends.CreateSingleHostingPayTradeSendVO;
//import cn.vfunding.vfunding.biz.user.model.User;
//import cn.vfunding.vfunding.biz.user.service.IUserService;
//import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;
//
//import com.alibaba.fastjson.JSON;
//
///**
// * 初始化新浪资金托管数据
// * 
// *
// */
//@Controller
//@RequestMapping(value = "/sinasyn")
//public class SinaSynController {
//	
//	/**
//	 * 日志对象，slf4j下的对象
//	 */
//	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");
//	
//	private final static String baseJspPath = "webpage/sinadeposit";
//	
//	private final static String baseUrl = "/sinasyn";
//	
//	private final static String pullLogId = "initProject|Pull|";
//	
//	private final static String pushLogId = "initProject|Push|";
//	
//	@Autowired
//	private IAccountService accountService;
//	
//	@Autowired 
//	private IHostingCollectTradeSinaService borrowTenderSinaService;
//	
//	@Autowired
//	private ISinaSendLogService sinaSendLogService;
//	
//	@Autowired
//	private IUserService userService;
//	
//	@Autowired
//	private ISingleHostingPayTradeSinaService borrowVerifySinaService;
//	
//	
//	/**
//	 * 跳转到同步数据页面
//	 * 
//	 * @return ModelAndView
//	 * @author louchen 2015-01-29
//	 */
//	@RequestMapping(value = "/synPlatDataPage")
//	@ResponseBody
//	public ModelAndView synPlatDataPage() {
//		ModelAndView mv = new ModelAndView(baseJspPath + "/synPlatData");
//		// 新建权限
//		boolean canInitProject = UserAuthFilter.isPass(baseUrl + "/canInitProject");
//		if (canInitProject) {
//			mv.addObject("canInitProject", canInitProject);
//		}
//		return mv;
//	}
//	
//	
//	/**
//	 * 从企业钱包 一次性代收所有用户的可用余额
//	 * @return
//	 * @throws Exception
//	 */
////	@RequestMapping(value = "/initProjectPullAll")
////	@ResponseBody
////	public Json initProjectPullAll() throws Exception{
////		Json json = new Json();
////		boolean canInitProject = UserAuthFilter.isPass(baseUrl+"/canInitProject");
////		if(canInitProject){
////			if(!checkRepeatPull("All")){
////				BigDecimal sumUseMoney = accountService.selectSumUseMoney();
////				CreateHostingCollectTradeSendVO sendVO = new CreateHostingCollectTradeSendVO();
////				sendVO.setOut_trade_no(System.currentTimeMillis()+""); //流水号内部唯一
////				sendVO.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._1001); 
////				sendVO.setSummary("从企业钱包 一次性代收所有用户的可用余额");
////				sendVO.setPayer_id(SinaParamsUtil.getInstance().getVfundingCompanyAccount());//付款人 _微积金邮箱账户
////				sendVO.setPayer_identity_type(SinaMemberParmUtil.IdentityType.EMAIL); //付款人_类型
////				sendVO.setPay_method("balance^" + sumUseMoney + "^BASIC"); //支付方式^金额^账户类型
////				borrowTenderSinaService.sinaSend(sendVO,pullLogId+"All","从企业钱包 一次性代收所有用户的可用余额");		
////			}		
////			json.setSuccess(true);
////		}else{
////			json.setMsg("您没有初始化项目的权限");
////		}
////		return json;
////	}
//	
//	/**
//	 * 是否重复发送
//	 * @return
//	 * @throws Exception
//	 */
//	private Boolean checkRepeatPull(String id) throws Exception {
//		SinaSendLogWithBLOBs ssl = this.sinaSendLogService.selectSuccessLogByOrderNo(pullLogId+id);
//		if(EmptyUtil.isEmpty(ssl)){
//			return false;
//		}else{
//			return true;
//		}
//	}
//	
//	/**
//	 * 从中间账户代付用户可用余额
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/initProjectPushAll")
//	@ResponseBody
//	public Json initProjectPushAll() throws Exception{
//		Json json = new Json();
//		boolean canInitProject = UserAuthFilter.isPass(baseUrl+"/canInitProject");
//		if(canInitProject){
//			logger.info("=============start====="+new Date()+"======================");
//			List<Account> list = accountService.selectAccountUseMoneyThanZero();
//			for(Account account:list){
//				if(!checkRepeatPush(account.getUserId())){
//					String result = "failed";
//					User user = null;
//					CreateSingleHostingPayTradeSendVO sendVO = new CreateSingleHostingPayTradeSendVO();
//					try {
//						user = this.userService.selectByPrimaryKey(account.getUserId());
//					} catch (Exception e) {
//						logger.error("#####[sina "+account.getUserId() +" 代付  异常]"
//								+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
//								+"[arg="+JSON.toJSONString(sendVO)+"]"
//								+"[errorstatus=rd_user表重复记录]"
//								+ "#####");
//					}
//					if(EmptyUtil.isNotEmpty(user)&&user.getUserId()>0){
//						sendVO.setOut_trade_no(System.currentTimeMillis()+""); //流水号内部唯一
//						sendVO.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2001);
//						sendVO.setPayee_identity_id(user.getUserId().toString());//收款人
//						sendVO.setPayee_identity_type(SinaMemberParmUtil.IdentityType.UID);
//						if (!user.getTypeId().equals(40)) {// 收款人账户类型
//							sendVO.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
//						}else{
//							sendVO.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
//						}
//						sendVO.setAmount(account.getUseMoney().toString());
//						sendVO.setSummary("从中间账户代付用户可用余额");
//						try {
//							result = borrowVerifySinaService.sinaSend(sendVO, pushLogId+account.getUserId(), "从中间账户代付用户可用余额");
//						} catch (Exception e) {
//							logger.error("#####[sina "+account.getUserId() +" 代付  异常]"
//									+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
//									+"[arg="+JSON.toJSONString(sendVO)+"]"
//									+"[errormessage="+e.getMessage()+"]"
//									+ "#####");
//						}			
//					}else{
//						logger.error("#####[sina "+account.getUserId() +" 代付  异常]"
//								+"[url="+SinaParamsUtil.getInstance().getTradesUrl()+"]"
//								+"[arg="+JSON.toJSONString(sendVO)+"]"
//								+"[errorstatus=rd_user表没记录]"
//								+ "#####");
//					}
//				}
//			}	
//			logger.info("=============end====="+new Date()+"======================");
//			json.setSuccess(true);
//		}else{
//			json.setMsg("您没有初始化项目的权限");
//		}
//		return json;
//	}
//	
//	/**
//	 * 是否重复发送
//	 * @param userId
//	 * @return
//	 * @throws Exception
//	 */
//	private Boolean checkRepeatPush(Integer userId) throws Exception {
//		SinaSendLogWithBLOBs ssl = this.sinaSendLogService.selectSuccessLogByOrderNo(pushLogId+userId);
//		if(EmptyUtil.isEmpty(ssl)){
//			return false;
//		}else{
//			return true;
//		}
//	}		
//}
