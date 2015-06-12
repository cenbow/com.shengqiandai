package cn.vfunding.vfunding.prd.bms.sinadeposit.syn;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaSendActionLog;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendActionLogService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.User2Sinamember;

public class QuerySinaMoneySyn extends BaseSyn{

	IQuerySinaService querySinaService;
	
	public QuerySinaMoneySyn(ISinaMemberManagerService sinaMemberManagerService,
			User2Sinamember user) {
		super(sinaMemberManagerService, user);
	}
	
	public QuerySinaMoneySyn(User user ,ISinaMemberManagerService sinaMemberManagerService, IQuerySinaService querySinaService){
		super(sinaMemberManagerService, user);
		this.querySinaService = querySinaService;
	}

	public QuerySinaMoneySyn(
			User user,ISinaMemberManagerService sinaMemberManagerService, IQuerySinaService querySinaService ,ISinaSendActionLogService sinaSendActionLogService ) {
		super(sinaMemberManagerService, user);
		this.querySinaService = querySinaService;
		this.sinaSendActionLogService = sinaSendActionLogService;
	}
	
	
	
	public String custom_yes="custom_yes";
	public String custom_no="custom_no";
	
	
	
	protected Map<String,String> map;
	
	@Override
	protected SinaSendActionLog createSinaSendActionLog() {
		SinaSendActionLog ssal = super.createSinaSendActionLog();
		if(map != null){
			ssal.setBalance(getLongValue(map.get("balance")));
			ssal.setAvailableBalance(getLongValue(map.get("availableBalance")));
			String bonus = map.get("bonus");
			String [] bonuses = bonus.split("\\^");
			ssal.setBonus(bonus);
			if(bonuses!=null && bonuses.length == 3){
				ssal.setBonus1(getLongValue(bonuses[0]));
				ssal.setBonus2(getLongValue(bonuses[1]));
				ssal.setBonus3(getLongValue(bonuses[2]));
			}
		}
		return ssal;
	}
	
	private BigDecimal getLongValue(String value){
		BigDecimal result = null;
		if(StringUtils.isNotBlank(value)){
			try {
				result = new BigDecimal(value);
			} catch (NumberFormatException e) {
				e.printStackTrace();
				result = new BigDecimal("0.00");
			}
		}
		return result;
	}
	
	@Override
	public void execute() {
		if(!(user instanceof User2Sinamember))
			return ;
		
		User2Sinamember u2s = (User2Sinamember) user;
		try {
			map = querySinaService.doQueryBalance(u2s.getUserId());
			if(map != null){
				setResultSet(map.get("success"));
			}else{
				setResultSet("error");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected boolean isOK(String responseCode) {
		if(SinaMemberParmUtil.success.equals(responseCode))
			return true;
		return false;
	}

	@Override
	public String operationName() {
		return SinaMemberParmUtil.interfaceName.query_balance;
	}
	
}
