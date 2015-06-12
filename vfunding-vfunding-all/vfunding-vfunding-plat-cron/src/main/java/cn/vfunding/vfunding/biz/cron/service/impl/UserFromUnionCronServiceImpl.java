package cn.vfunding.vfunding.biz.cron.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.borrow.model.UserInvestMoneyVO;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.cron.service.IUserFromUnionCronService;
import cn.vfunding.vfunding.biz.user.model.UserFromUnion;
import cn.vfunding.vfunding.biz.user.service.IUserFromUnionService;
@Service("userFromUnionCronService")
public class UserFromUnionCronServiceImpl implements IUserFromUnionCronService {

	@Autowired
	IUserFromUnionService userFromUnionService;
	@Autowired
	IBorrowTenderService borrowTenderService;
	
	
	@Value("${updateUserPeriods.time}")
	private String updateUserPeriodsTime;
	
	@Override
	public String updatePeriods() {
		//查询所有需要更新期数的用户信息
		List<UserFromUnion> userFromUnions=this.userFromUnionService.selectByNeedChangePeriods();
		for (UserFromUnion userFromUnion : userFromUnions) {
			//计算当前时间和该用户最后一次改变期数的时间差
			int days=(int) DateUtil.getBetweenDays(userFromUnion.getUpdatePeriodsTime(), new Date());
			if(days>=30){
				if(userFromUnion.getPeriods().intValue()==1){//如果期数为1，此时必须自动更新到2					
					updatePeriodsMethod(userFromUnion,2);
				}else{ //期数为2
					//查询该用户 days 天内的自投金额
					UserInvestMoneyVO vo=new UserInvestMoneyVO(days,userFromUnion.getUserId()) ;
					Integer investMoney=this.borrowTenderService.selectInvestMoneyByUserIdAndDays(vo);
					if(EmptyUtil.isNotEmpty(investMoney) && investMoney.intValue()!=0){ //表示该用户在此期间投资过
						updatePeriodsMethod(userFromUnion,3);
					}
				}

			}			
		}
		return updateUserPeriodsTime;
	}
	
	
	private void updatePeriodsMethod(UserFromUnion userFromUnion,int num){
		userFromUnion.setPeriods(num);
		userFromUnion.setUpdatePeriodsTime(new Date());
		this.userFromUnionService.updateByPrimaryKeySelective(userFromUnion);
	}

}
