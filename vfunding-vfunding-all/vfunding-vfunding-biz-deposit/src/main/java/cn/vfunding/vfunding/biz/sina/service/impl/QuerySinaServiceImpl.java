package cn.vfunding.vfunding.biz.sina.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryAccountDetailsReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryBalanceReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryFundYieldReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryHostingDepositReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryHostingTradeReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryAccountDetailsSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryBalanceSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryFundYieldSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryHostingDepositSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryHostingTradeSendVO;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;

import com.alibaba.fastjson.JSON;

@Service("querySinaService")
public class QuerySinaServiceImpl implements IQuerySinaService {
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
	private UserMapper userMapper;
	
	//用户
	private User user;
		
	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");
	
	/**
	 * 获取用户新浪账户类型,本地数据库校验
	 * @param userId
	 * @return
	 */
	private String getUserAccountType(Integer userId) {
		String accountType;
		this.user = this.userMapper.selectByPrimaryKey(userId);
		if(this.user.getTypeId().equals(40)){
			//借款人账户是基本账户
			accountType = SinaMemberParmUtil.AccountType.BASIC;
		}else{
			//其他人账户是存钱罐账户
			accountType = SinaMemberParmUtil.AccountType.SAVING_POT;
		}
		return accountType;
	}
	
	/**
	 * 查询用户余额
	 * @param userId
	 * @return result
	 * @author louchen 2015-01-19
	 */
	@Override
	public Map<String,String> doQueryBalance(Integer userId) throws Exception {
		if(EmptyUtil.isEmpty(userId)){
			logger.error("#####[sina "+userId+" 查询用户余额, 参数异常]#####");
			throw new Exception("参数对象为空");
		}else{
			Map<String,String> map = new HashMap<String,String>();
			String accountType = this.getUserAccountType(userId);
			//发送
			String result="faild";
			logger.info("*****[sina "+userId+" 查询用户余额, 请求开始]*****");
			//sendVO拼参数
			QueryBalanceSendVO sendVO = new QueryBalanceSendVO();
			sendVO.setIdentity_id(userId.toString());
			sendVO.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
			sendVO.setAccount_type(accountType);
			//returnVO得到返回结果
			QueryBalanceReturnVO returnVO = this.sinaSendService.sinaSendMgs(sendVO, QueryBalanceReturnVO.class);
			if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
				result = SinaMemberParmUtil.success;
			} else {
				//记录出错日志
				logger.error("#####[sina "+userId+" 查询用户余额, 异常]"
						+"[url="+SinaParamsUtil.getInstance().getMembersUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
			}
			map.put("success", result);
			map.put("msg", returnVO.getResponse_message());
			map.put("balance", returnVO.getBalance());
			map.put("availableBalance", returnVO.getAvailable_balance());
			map.put("bonus", returnVO.getBonus());
			logger.info("*****[sina "+userId+" 查询用户余额, 请求完成]*****");
			return map;	
		}		
	}

	/**
	 * 存钱罐基金收益率查询
	 * @author louchen 2015-01-20
	 */
	@Override
	public String doQueryFundYield() throws Exception {
		//logger.info("用户查询新浪接口存钱罐基金收益率开始");
		//拼sendVO
		QueryFundYieldSendVO sendVO = new QueryFundYieldSendVO();
		sendVO.setFund_code(SinaParamsUtil.getInstance().getFundCode());
		//logger.info("发送请求,对象内容:"+JSON.toJSONString(sendVO));
		//returnVO得到返回结果
		QueryFundYieldReturnVO returnVO = this.sinaSendService.sinaSendMas(sendVO, QueryFundYieldReturnVO.class);
		//logger.info("请求完毕,获得响应内容:"+JSON.toJSONString(returnVO));
		if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
			return returnVO.getYield_list();
		} 		
		//logger.info("用户查询新浪接口存钱罐基金收益率完成");
		return null;
	}
	
	/**
	 * 查询充值状态
	 */
	@Override
	public Map<String, String> doQueryDeposit(AccountRecharge rec) throws Exception {
		if(EmptyUtil.isEmpty(rec)){
			logger.error("#####[sina "+rec.getTradeNo()+" 查询充值状态, 参数异常]#####");
			throw new Exception("参数对象为空");
		}else{
			Map<String,String> map = new HashMap<String,String>();
			String accountType = this.getUserAccountType(rec.getUserId());
			//发送
			String result="faild";
			logger.info("*****[sina "+rec.getTradeNo()+" 查询充值状态, 请求开始]*****");
			//sendVO拼参数
			QueryHostingDepositSendVO sendVO = new QueryHostingDepositSendVO();
			sendVO.setIdentity_id(rec.getUserId().toString());
			sendVO.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
			sendVO.setAccount_type(accountType);
			sendVO.setOut_trade_no(rec.getTradeNo());
			//returnVO得到返回结果
			QueryHostingDepositReturnVO returnVO = this.sinaSendService.sinaSendMas(sendVO, QueryHostingDepositReturnVO.class);
			if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
				result = SinaMemberParmUtil.success;
			} else{
				//记录出错日志
				logger.error("#####[sina "+rec.getTradeNo()+" 查询充值状态, 异常]"
						+"[url="+SinaParamsUtil.getInstance().getMembersUrl()+"]"
						+"[arg="+JSON.toJSONString(sendVO)+"]"
						+"[response="+JSON.toJSONString(returnVO)+"]"
						+ "#####");
			}
			map.put("success", result);
			map.put("msg", returnVO.getResponse_message());
			map.put("depositList", returnVO.getDeposit_list());
			map.put("pageNo", returnVO.getPage_no());
			map.put("pageSize", returnVO.getPage_size());
			map.put("totalItem", returnVO.getTotal_item());
			logger.info("*****[sina "+rec.getTradeNo()+" 查询充值状态, 请求完成]*****");
			return map;	
		}		
	}

	/**
	 * 查询托管交易
	 */
	@Override
	public Map<String, String> doQueryHostingTrade(QueryHostingTradeSendVO sendVO) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		String result="faild";
		logger.info("*****[sina 查询托管交易, 请求开始]*****");
		//returnVO得到返回结果
		QueryHostingTradeReturnVO returnVO = this.sinaSendService.sinaSendMas(sendVO, QueryHostingTradeReturnVO.class);
		if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
			result = SinaMemberParmUtil.success;
		} else{
			//记录出错日志
			logger.error("#####[sina 查询托管交易, 异常]"
					+"[url="+SinaParamsUtil.getInstance().getMembersUrl()+"]"
					+"[arg="+JSON.toJSONString(sendVO)+"]"
					+"[response="+JSON.toJSONString(returnVO)+"]"
					+ "#####");
		}
		map.put("success", result);
		map.put("msg", returnVO.getResponse_message());
		map.put("tradeList", returnVO.getTrade_list());
		map.put("pageNo", returnVO.getPage_no());
		map.put("pageSize", returnVO.getPage_size());
		map.put("totalItem", returnVO.getTotal_item());
		logger.info("*****[sina 查询托管交易, 请求完成]*****");
		return map;	
	}

	@Override
	public Map<String, String> doQueryAccountDetails(
			QueryAccountDetailsSendVO sendVO) throws Exception {
		Map<String,String> map = new HashMap<String,String>();
		String result="faild";
		logger.info("*****[sina 查询收支明细, 请求开始]*****");
		//returnVO得到返回结果
		QueryAccountDetailsReturnVO returnVO = this.sinaSendService.sinaSendMgs(sendVO, QueryAccountDetailsReturnVO.class);
		if (returnVO.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
			result = SinaMemberParmUtil.success;
		} else{
			//记录出错日志
			logger.error("#####[sina 查询收支明细, 异常]"
					+"[url="+SinaParamsUtil.getInstance().getMembersUrl()+"]"
					+"[arg="+JSON.toJSONString(sendVO)+"]"
					+"[response="+JSON.toJSONString(returnVO)+"]"
					+ "#####");
		}
		map.put("success", result);
		map.put("msg", returnVO.getResponse_message());
		map.put("detailList", returnVO.getDetail_list());
		map.put("pageNo", returnVO.getPage_no());
		map.put("pageSize", returnVO.getPage_size());
		map.put("totalItem", returnVO.getTotal_item());
		map.put("totalIncome", returnVO.getTotal_income());
		map.put("totalPayout", returnVO.getTotal_payout());
		logger.info("*****[sina 查询收支明细, 请求完成]*****");
		return map;	
	}

	
	private static String CacheFundYieldResult;
	private static long firstDayStartTime;
	private static long secondDayStartTime;
	@Override
	public Map<String, Object> doQueryFundYieldDayCache() throws Exception {
		
		long currentTime = System.currentTimeMillis();
		
		
		Map<String,Object> map = null;
		String result = null;
		//程序第一次进入时                   
		if((firstDayStartTime == 0 && secondDayStartTime ==0)  || 
				//如果当前时间大于第二天开始时间，时间更换，重新从sina获得 余额生息七日年化收益率 
				(currentTime > secondDayStartTime) ||
				CacheFundYieldResult == null){
			
			//初始化第一天开始时间  例:    2015-03-31 00:00:00.000
			firstDayStartTime = getFirstDayStartTime();
			//初始化第二天开始时间  例:    2015-04-01 00:00:00.000
			secondDayStartTime = getSecondDayStartTime();
			//获取余额生息七日年化收益率 
			result = doQueryFundYield();
			//缓存数据结果
			CacheFundYieldResult = result;
		}
		//当前时间大于第一天开始时间 小于第二天开始时间  不从sina获取,返回缓存数据
		if(currentTime >= firstDayStartTime && currentTime < secondDayStartTime ){
			
			result = CacheFundYieldResult;
		}
		
		if(EmptyUtil.isNotEmpty(result)){
			String[] array = result.split("\\|");
			if(array.length > 0){
				String content = array[0];
				String[] c = content.split("\\^");
				
				if(c.length > 0 && c.length >=2){
					String dateStr = c[0];
					String formatDate = dateStr.substring(0,4)+"-"+dateStr.substring(4,6)+"-"+dateStr.substring(6);
					Calendar day = Calendar.getInstance();
					day.set(Calendar.YEAR, Integer.parseInt(dateStr.substring(0,4)));
					day.set(Calendar.MONTH, (Integer.parseInt(dateStr.substring(4,6))+1) );
					day.set(Calendar.DATE, Integer.parseInt(dateStr.substring(6)));
					//day.set(Calendar.HOUR, 0);
					//day.set(Calendar.MINUTE, 0);
					//day.set(Calendar.SECOND, 0);
					//day.set(Calendar.MILLISECOND, 0);
					Date date = day.getTime();
					long time = day.getTimeInMillis();
					
					
					String yield = c[1];
					String servenDayOfYearYieldsStr = yield+"%";
					
					
					map = new HashMap<String, Object>();
					map.put("dateString", formatDate);
					map.put("date", date);
					map.put("time", time);
					map.put("yield", yield);
					map.put("servenDayOfYearYieldsStr", servenDayOfYearYieldsStr);
				}
			}
		}
		return map;
	}
	private static long getFirstDayStartTime(){
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTimeInMillis();
	}
	
	private static long getSecondDayStartTime(){
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.DATE, (todayStart.get(Calendar.DATE)+1));
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTimeInMillis();
	}
}
