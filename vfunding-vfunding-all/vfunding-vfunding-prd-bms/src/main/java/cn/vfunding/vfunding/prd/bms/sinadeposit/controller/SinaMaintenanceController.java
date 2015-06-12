package cn.vfunding.vfunding.prd.bms.sinadeposit.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaBonusService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

/**
 * 手动补单CONTROLLER
 * 
 *
 */
@Controller
@RequestMapping(value = "/sinamaintenance")
public class SinaMaintenanceController {
	
	private final static String baseUrl = "/sinamaintenance";
	
	@Autowired
	private IQuerySinaService querySinaService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAccountService accountServiceImpl;
	
	@Autowired
	private IAccountRechargeService accountRechargeService;
	
	@Autowired
	private ISinaBonusService sinaBonusService;
		
	/**
	 * 网银在线充值，新浪那边成功但没回调微积金，微积金账户没有增加资金，手动执行回调。
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/repairRecharge")
	@ResponseBody
	public Json repairRecharge(String condition) throws Exception{
		Json json = new Json();
		boolean canRepairRecharge = UserAuthFilter.isPass(baseUrl+"/canRepairRecharge");
		if(canRepairRecharge){
			AccountRecharge rec = this.accountRechargeService.selectByTradeNo(condition);
			if(EmptyUtil.isNotEmpty(rec) && rec.getUserId()>0){
				Map<String,String> result = this.querySinaService.doQueryDeposit(rec);
				if(result.get("success").equals("success")){
					//获取返回参数
					String depositList = result.get("depositList");
					String[] arr = depositList.split("\\^");
					String sinaDepositSuccess = arr[2];
					BigDecimal sinaDepositMoney = new BigDecimal(arr[1]);
					//新浪必须充值成功
					if(sinaDepositSuccess.equals("SUCCESS")||sinaDepositSuccess.equals("success")){
						//微积金充必须值status=4,等待新浪回调状态
						if(rec.getStatus().equals((byte)4)){
							//新浪充值金额必须等于微积金充值金额
							if(rec.getMoney().equals(sinaDepositMoney)){
								try {
									this.accountRechargeService.sinaReturnUpdateStatus(rec,sinaDepositMoney.toString(),true);	
									 json.setSuccess(true);	
									 json.setMsg("手动补单成功");
								} catch (Exception e) {
									json.setMsg(e.getMessage());
								}
							}else{
								json.setMsg("两边充值金额不一致，无法补单");
							}
						}else{
							json.setMsg("微积金充值状态必须是等待新浪回调才能手动补单,当前充值状态:"+rec.getStatus()); 
						}
					}else{
						json.setMsg("新浪充值状态没有成功无法补单");
					}
				 }else{
					 //新浪接口错误消息
					 json.setMsg(result.get("msg"));
				 }
			}else{
				json.setMsg("流水单号:"+condition+"查询不到充值记录");
			}
		}else{
			json.setMsg("您没有补单充值的权限");
		}
		return json;
	}
	
	/**
	 * 创建每天存钱罐结算数据
	 * @return
	 * @author louchen 2015-03-27
	 */
	@RequestMapping(value = "/createSinaBonusData")
	@ResponseBody
	public Boolean createSinaBonusData(){
		return sinaBonusService.loadCreateBaseSinaSettlementData();
	}
	
	/**
	 * 同步每天存钱罐结算数据
	 * @return
	 * @author louchen 2015-03-27
	 */
	@RequestMapping(value = "/syncSinaBonusData")
	@ResponseBody
	public Boolean syncSinaBonusData(){
		return sinaBonusService.loadSyncSinaSettlementData();
	}
}
