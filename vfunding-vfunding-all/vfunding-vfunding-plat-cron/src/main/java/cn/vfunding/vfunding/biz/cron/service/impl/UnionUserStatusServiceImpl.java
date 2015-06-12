package cn.vfunding.vfunding.biz.cron.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.borrow.model.UserInvestMoneyVO;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.cron.service.IUnionUserStatusCronService;
import cn.vfunding.vfunding.biz.user.model.CleanOldUserVO;
import cn.vfunding.vfunding.biz.user.model.UserFromUnion;
import cn.vfunding.vfunding.biz.user.model.UserUnion;
import cn.vfunding.vfunding.biz.user.service.IUserFromUnionService;
import cn.vfunding.vfunding.biz.user.service.IUserUnionService;

@Service("unionUserStatusService")
public class UnionUserStatusServiceImpl implements IUnionUserStatusCronService {

	@Autowired
	IUserUnionService userUnionService;
	@Autowired
	IUserFromUnionService userFromUnionService;
	@Autowired
	IBorrowTenderService borrowTenderService;
	
	@Value("${checkToStopUnionUser.time}")
	private String checkToStopUnionUserTime;
	
	@Value("${checkReviveUnionUser.time}")
	private String checkReviveUnionUserTime;
	
	@Value("${checkClearOldUserAndRevive.time}")
	private String checkClearOldUserAndReviveTime;
	
	
	@Override
	public String stopUnionUser() {
        List<UserUnion> unionUsers=this.userUnionService.selectCanProfitUser();
        for (UserUnion userUnion : unionUsers) {
        	//查询该网站联盟用户带来的最后一个注册用户信息
			UserFromUnion fromUnion=this.userFromUnionService.selectByUnionUserIdAndLastRegist(userUnion.getUnionUserId());
			if(EmptyUtil.isNotEmpty(fromUnion)){
				//计算当前时间和该用户的注册时间差
				int days=(int) DateUtil.getBetweenDays(fromUnion.getInsertTime(), new Date());
				if(days>=60){  //表示该用户在连续60天内没有介绍新用户
					if(!EmptyUtil.isEmpty(userUnion.getUserId())){ //表示该网站联盟用户在微积金已经注册过了
						//查询该用户60天内的自投金额
						UserInvestMoneyVO vo=new UserInvestMoneyVO(60,userUnion.getUserId()) ;
						Integer investMoney=this.borrowTenderService.selectInvestMoneyByUserIdAndDays(vo);
						if(EmptyUtil.isEmpty(investMoney) || investMoney.intValue()<20000){
							changeUnionUserStatusAndReviveNum(userUnion,"N",false);
						}										
					}else{ //表示该网站联盟用户60天内没有介绍新用户，且没有在微积金注册，满足终止结算的条件，将其状态更改为不可分利状态
						changeUnionUserStatusAndReviveNum(userUnion,"N",false);
					}
				}
			}
			
		}
        return checkToStopUnionUserTime;
	}

	
	@Override
	public String reviveUnionUser() {
		List<UserUnion> unionUsers=this.userUnionService.selectCanRevive();
		for (UserUnion userUnion : unionUsers) {
        	//查询该网站联盟用户带来的最后一个注册用户信息
			UserFromUnion fromUnion=this.userFromUnionService.selectByUnionUserIdAndLastRegist(userUnion.getUnionUserId());
			if(EmptyUtil.isNotEmpty(fromUnion)){
				//计算当前时间和该用户的注册时间差
				int days=(int) DateUtil.getBetweenDays(fromUnion.getInsertTime(), new Date());
				if(days>=60){  //表示该用户没有介绍新用户
					if(EmptyUtil.isNotEmpty(userUnion.getUserId())){ //表示该网站联盟用户在微积金已经注册过了
						//查询该用户60天内的自投金额
						UserInvestMoneyVO vo=new UserInvestMoneyVO(60,userUnion.getUserId()) ;
						Integer investMoney=this.borrowTenderService.selectInvestMoneyByUserIdAndDays(vo);
						if(EmptyUtil.isNotEmpty(investMoney) || investMoney.intValue()>20000){
							changeUnionUserStatusAndReviveNum(userUnion,"Y",true);
						}										
					}
				}else{					
					changeUnionUserStatusAndReviveNum(userUnion,"Y",true);
				}
			}
		}
		return checkReviveUnionUserTime;
	}
	
	
	@Override
	public String clearOldUserAndRevive() {
		List<UserUnion> unionUsers=this.userUnionService.selectNotRevive();
		for (UserUnion userUnion : unionUsers) {
        	//查询该网站联盟用户带来的最后一个注册用户信息
			UserFromUnion fromUnion=this.userFromUnionService.selectByUnionUserIdAndLastRegist(userUnion.getUnionUserId());
			if(EmptyUtil.isNotEmpty(fromUnion)){
				//计算当前时间和该用户的注册时间差
				int days=(int) DateUtil.getBetweenDays(fromUnion.getInsertTime(), new Date());
				if(days<60){  //表示该用户介绍新用户了
					changeUnionUserStatusAndReviveNum(userUnion,"Y",false);//复活
					CleanOldUserVO vo =new CleanOldUserVO(userUnion.getUnionUserId(),userUnion.getLastReviveTime());					
					this.userFromUnionService.cleanOldUserByUnionUser(vo);//处理以前的用户状态
				}
			}
		}	
		return checkClearOldUserAndReviveTime;		
	}
	
	
	/**
	 * 更新网站联盟用户的分利状态为
	 * @param userUnion
	 * @param status(Y、N)
	 */
	private void changeUnionUserStatusAndReviveNum(UserUnion userUnion,String status,boolean changeReviveNum){
		if(status.toUpperCase().equals("Y") || status.toUpperCase().equals("N")){
			userUnion.setStatus(status);
			if(status.toUpperCase().equals("Y")){
				if(changeReviveNum){
					if(EmptyUtil.isEmpty(userUnion.getReviveNum())){
						userUnion.setReviveNum(1);
					}else{
						userUnion.setReviveNum(userUnion.getReviveNum()+1);
					}
				}
				userUnion.setLastReviveTime(new Date());//设置复活时间
			}
			this.userUnionService.updateByPrimaryKeySelective(userUnion);
		}
	}
	
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int days=(int) DateUtil.getBetweenDays(f.parse("2014-01-01 10:10:10"), new Date());
		System.out.println(days);
	}


	
}
