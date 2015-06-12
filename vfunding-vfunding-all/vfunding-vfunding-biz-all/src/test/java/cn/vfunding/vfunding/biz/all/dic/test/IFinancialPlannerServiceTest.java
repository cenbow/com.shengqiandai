package cn.vfunding.vfunding.biz.all.dic.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;

import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.financialPlanner.service.IFinancialPlannerService;
import cn.vfunding.vfunding.biz.financialPlanner.vo.FinancialPlannerVO;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.inviteCode.service.impl.InviteCodeServiceImpl;
import cn.vfunding.vfunding.biz.inviteCode.status.StatusUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaDic;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.user.model.NumberInvitationVO;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:./conf/spring-context.xml" })
public class IFinancialPlannerServiceTest {
	@Autowired
	IFinancialPlannerService financialPlannerService;
	
	@Autowired
	IUserService userService;
	@Autowired
	IInviteCodeService inviteCodeservice;
	
	@Autowired
	IBorrowTenderService borrowTenderService;
	
	@Test
	public void loadDic(){
		//List<FinancialPlannerVO> list = financialPlannerService.returnProfitLeaderboard();
		/*List<NumberInvitationVO> list = financialPlannerService.NumberInvitationLeaderboard();
		System.out.println(list.size());*/
		//System.out.println(StatusUtil.getStatusUpdateValue(1 , 1));
		//System.out.println(inviteCodeservice.updateDialogStatusByPrimaryKey(5, 1));
		
		//System.out.println(borrowTenderService.selectBrrowTenderByUserId(2000).size());
		//System.out.println(borrowTenderService.selectTenderStatusByUserId(5,3).size());
		System.out.println(financialPlannerService.selectByTUserIdAndTenderId(6, 6355).getId());
	}
	
}
