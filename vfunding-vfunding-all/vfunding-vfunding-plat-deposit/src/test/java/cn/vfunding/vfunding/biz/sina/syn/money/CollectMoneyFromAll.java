package cn.vfunding.vfunding.biz.sina.syn.money;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaSendLogWithBLOBs;
import cn.vfunding.vfunding.biz.sina.service.IHostingCollectTradeSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;

/**
 * 从企业钱包 一次性代收所有用户的可用余额
 * @author Administrator
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
public class CollectMoneyFromAll {
	
	private final static String logId = "initProject|Pull|All";
	
	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");
	
	@Autowired
	private IAccountService accountService;
	
	@Autowired 
	private IHostingCollectTradeSinaService borrowTenderSinaService;
	
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	
	/**
	 * 推送数据
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception{
		if(!checkRepeat()){
			BigDecimal sumUseMoney = accountService.selectSumUseMoney();
			CreateHostingCollectTradeSendVO sendVO = new CreateHostingCollectTradeSendVO();
			sendVO.setOut_trade_no(System.currentTimeMillis()+""); //流水号内部唯一
			sendVO.setOut_trade_code(SinaMemberParmUtil.OutTradeCode._1001); 
			sendVO.setSummary("从企业钱包 一次性代收所有用户的可用余额");
			sendVO.setPayer_id(SinaParamsUtil.getInstance().getVfundingCompanyAccount());//付款人 _微积金邮箱账户
			sendVO.setPayer_identity_type(SinaMemberParmUtil.IdentityType.EMAIL); //付款人_类型
			sendVO.setPay_method("balance^" + sumUseMoney + "^BASIC"); //支付方式^金额^账户类型
			borrowTenderSinaService.sinaSend(sendVO,logId,"从企业钱包 一次性代收所有用户的可用余额");
		}
	}
	
	/**
	 * 是否已成功发送
	 * @return
	 * @throws Exception
	 */
	private Boolean checkRepeat() throws Exception {
		SinaSendLogWithBLOBs ssl = this.sinaSendLogService.selectSuccessLogByOrderNo(logId);
		if(EmptyUtil.isEmpty(ssl)){
			return false;
		}else{
			return true;
		}
	}
}
