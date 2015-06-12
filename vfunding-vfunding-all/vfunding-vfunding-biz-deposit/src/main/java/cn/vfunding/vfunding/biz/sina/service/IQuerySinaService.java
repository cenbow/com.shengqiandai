package cn.vfunding.vfunding.biz.sina.service;

import java.util.Map;

import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryAccountDetailsSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryHostingTradeSendVO;

public interface IQuerySinaService {
	
	/**
	 * 查询用户余额
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	Map<String,String> doQueryBalance(Integer userId) throws Exception;
	
	/**
	 * 查询充值记录
	 * @param rec
	 * @return
	 * @throws Exception
	 */
	Map<String,String> doQueryDeposit(AccountRecharge rec) throws Exception;
	
	
	/**
	 * 查询托管交易
	 * @return
	 * @throws Exception
	 */
	Map<String,String> doQueryHostingTrade(QueryHostingTradeSendVO sendVO) throws Exception;
	
	/**
	 * 查询收支明细交易
	 * @return
	 * @throws Exception
	 */
	Map<String,String> doQueryAccountDetails(QueryAccountDetailsSendVO sendVO) throws Exception;
	
	
	/**
	 * 查询存钱罐利率
	 * @return
	 * @throws Exception
	 */
	String doQueryFundYield() throws Exception;
	
	/**
	 * 查询存钱罐利率当日缓存
	 * wang.zeyan 2015年3月31日
	 * @return
	 * @throws Exception
	 */
	Map<String,Object> doQueryFundYieldDayCache() throws Exception;
}
