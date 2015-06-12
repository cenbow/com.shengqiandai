package cn.vfunding.vfunding.prd.bms.sinadeposit.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.IBorrowRepaymentSinaService;
import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.service.ISingleHostingPayTradeSinaService;
import cn.vfunding.vfunding.biz.sina.vo.returns.CreateSingleHostingPayTradeReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateSingleHostingPayTradeSendVO;
import cn.vfunding.vfunding.biz.sinaBalance.model.SinaBalanceLog;
import cn.vfunding.vfunding.biz.sinaBalance.service.ISinaBalanceLogService;
import cn.vfunding.vfunding.biz.sinaResend.model.SinaResendLog;
import cn.vfunding.vfunding.biz.sinaResend.service.ISinaResendLogService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;
import cn.vfunding.vfunding.plat.mq.useraction.service.IUserActionService;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/sinaCP")
public class SinaCollectPayController {
	@Autowired
	private ISinaSendService sinaSendService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IHostingCollectTradeSinaService hostingCollectTradeSinaService;
	@Autowired
	private ISingleHostingPayTradeSinaService singleHostingPayTradeSinaService;
	@Autowired
	private ISinaBalanceLogService sinaBalanceLogService;
	@Autowired
	private ISinaResendLogService sinaResendLogService;
	@Autowired
	private IBorrowRepaymentSinaService borrowRepaymentSinaService;

	@RequestMapping("/toResendPage")
	public String toResendPage() {
		return "webpage/sinadeposit/sinaResend";
	}

	@RequestMapping("/cp")
	@ResponseBody
	public Json sinaCP(String cpType, String argJson, Integer repaymentId) {
		Json j = new Json();
		Map<String, String> checkReturnMap = null;
		if (!StringUtils.isEmpty(argJson)) {
			checkReturnMap = this.checkNo(argJson);
			if (checkReturnMap.get("is").equals("true")) {
				j.setMsg("请勿重复执行订单请求");
				return j;
			}
		}
		try {
			if (cpType.equals("oneCollect")) { // 待收
				CreateHostingCollectTradeSendVO ccts = JSON.parseObject(argJson, CreateHostingCollectTradeSendVO.class);
				ccts.setOut_trade_no(new Date().getTime() + "|" + ccts.getOut_trade_no().split("|")[1]);
				ccts.setRequest_time(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				ccts.setSummary("待收手动补单");
				String result = hostingCollectTradeSinaService.sinaSend(ccts, "待收手动补单");
				if (!result.equals(SinaMemberParmUtil.success)) {
					sinaResendLogService.deleteByPrimaryKey(Integer.parseInt(checkReturnMap.get("srlId")));
				}
				j.setMsg(result);
			} else if (cpType.equals("onePay")) { // 单笔代付

				CreateSingleHostingPayTradeSendVO cshpts = JSON.parseObject(argJson, CreateSingleHostingPayTradeSendVO.class);
				cshpts.setOut_trade_no(new Date().getTime() + "|" + cshpts.getOut_trade_no().split("|")[1]);
				cshpts.setRequest_time(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
				cshpts.setSummary("单笔代付手动补单");
				String result = singleHostingPayTradeSinaService.sinaSend(cshpts, "单笔代付手动补单");
				if (!result.equals(SinaMemberParmUtil.success)) {
					sinaResendLogService.deleteByPrimaryKey(Integer.parseInt(checkReturnMap.get("srlId")));
				}
				j.setMsg(result);
			} else { // 批量代付
				this.borrowRepaymentSinaService.doUserRepayTender(repaymentId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	public Map<String, String> checkNo(String argJson) {
		Map<String, String> jsonMap = (Map<String, String>) JSON.parse(argJson);
		Map<String, String> returnMap = new HashMap<String, String>();
		SinaResendLog srl = new SinaResendLog();
		if (!StringUtils.isEmpty(jsonMap.get("out_trade_no"))) {
			srl.setOutTradeNo(jsonMap.get("out_trade_no"));
		} else if (!StringUtils.isEmpty(jsonMap.get("out_pay_no"))) {
			srl.setOutPayNo(jsonMap.get("out_pay_no"));
		}
		List<SinaResendLog> srls = sinaResendLogService.selectBySelective(srl);
		if (srls.isEmpty()) {
			sinaResendLogService.insertSelective(srl);
			returnMap.put("is", "false");
			returnMap.put("srlId", srl.toString());
			return returnMap;
		} else {
			returnMap.put("is", "true");
			return returnMap;
		}
	}

	@RequestMapping("/onePay")
	@ResponseBody
	public Json onePay(Integer userId, String amount) {
		Json j = new Json();
		User u = userService.selectByPrimaryKey(userId);
		CreateSingleHostingPayTradeSendVO cshpts = new CreateSingleHostingPayTradeSendVO();
		cshpts.setOut_trade_no(new Date().getTime() + "" + userId);
		cshpts.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._2002);
		cshpts.setPayee_identity_id(userId + "");
		cshpts.setPayee_identity_type(SinaMemberParmUtil.IdenType.UID);
		if (u.getTypeId() == 40) {
			cshpts.setAccount_type(SinaMemberParmUtil.AccountType.BASIC);
		} else {
			cshpts.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
		}
		cshpts.setAmount(amount);
		cshpts.setSummary("手动还款");
		CreateSingleHostingPayTradeReturnVO cshptr;
		try {
			cshptr = sinaSendService.sinaSendMas(cshpts, CreateSingleHostingPayTradeReturnVO.class);
			if (cshptr.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
				if (cshptr.getTrade_status().equals(SinaMemberParmUtil.SUCCESS)) {
					j.setObj(cshptr);
					j.setSuccess(true);
				}
			} else {
				j.setObj(cshptr);
				j.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	/**
	 * 跑新浪余额
	 * 
	 * @return
	 */
	@RequestMapping("/selectTotelNotZero")
	@ResponseBody
	public Json selectTotelNotZero() {
		this.sinaBalanceLogService.deleteAll();
		Json j = new Json();
		List<SinaBalanceLog> sbs = sinaBalanceLogService.selectTotelNotZeroId();
		String errorId = "出错id:";
		List<List<SinaBalanceLog>> listSbs = this.listSplit(sbs, 10);
		for (List<SinaBalanceLog> list : listSbs) {
			this.sinaBalanceLogService.querySinaBalanceAsync(list);
		}
		return j;
	}

	private List<List<SinaBalanceLog>> listSplit(List<SinaBalanceLog> sbs, int splitCount) {
		List<List<SinaBalanceLog>> listSbs = new ArrayList<List<SinaBalanceLog>>();
		int listIndex = 0;
		int listSize = sbs.size();
		int fenshu = sbs.size() / splitCount;
		int yushu = 0;
		if (listSize % splitCount > 0) {
			yushu = listSize % splitCount;
		}
		for (int j = 0; j < splitCount; j++) {
			List<SinaBalanceLog> sbslist = new ArrayList<SinaBalanceLog>();
			int length = fenshu + listIndex;
			for (int i = listIndex; i < length; i++) {
				listIndex = i;
				sbslist.add(sbs.get(i));
			}
			listIndex += 1;
			listSbs.add(sbslist);
		}
		if (yushu > 0) {
			List<SinaBalanceLog> sbslist = new ArrayList<SinaBalanceLog>();
			int length = yushu + listIndex;
			for (int i = listIndex; i < length; i++) {
				listIndex = i;
				sbslist.add(sbs.get(i));
			}
			listSbs.add(sbslist);
		}
		return listSbs;
	}

	@Autowired
	private IUserActionService userActionService;

	/**
	 * 补发投资
	 * 
	 * @return
	 */
	@RequestMapping("/reUserAction")
	@ResponseBody
	public Json reUserAction(String actionName, String methodArgs, String methodReturn, String messageId) {
		Json json = new Json();
		AfterActionMessage msg = new AfterActionMessage();
		msg.setMessageId(messageId);
		msg.setActionName(actionName);
		msg.setMethodReturn(JSON.parseObject(methodReturn, UserTenderActionResultVO.class));
		this.userActionService.doUserAction(msg);
		return json;
	}

}
