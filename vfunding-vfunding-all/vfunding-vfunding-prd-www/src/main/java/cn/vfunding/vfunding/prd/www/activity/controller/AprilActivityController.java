package cn.vfunding.vfunding.prd.www.activity.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.achievement.model.Achievement;
import cn.vfunding.vfunding.biz.achievement.model.AchievementLog;
import cn.vfunding.vfunding.biz.achievement.service.IAchievementLogService;
import cn.vfunding.vfunding.biz.achievement.service.IAchievementService;
import cn.vfunding.vfunding.biz.activity.service.IActivityLotteryService;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.session.utils.UserSession;

/**
 * 2015年4月活动
 * 
 * @author louchen 2015-03-31
 * 
 */
@Controller()
@RequestMapping("/activity/april")
public class AprilActivityController extends BaseController {
	
	@Value("${FifteenAprilActivityStart}")
	private String fifteenAprilActivityStart;
	
	@Value("${FifteenAprilActivityEnd}")
	private String fifteenAprilActivityEnd;
	
	@Autowired
	private IActivityLotteryService activityLotteryService;
	
	@Autowired
	private IAchievementLogService achievementLogService;
	
	@Autowired
	private IAchievementService achievementService;
	
	@Autowired
	private BorrowTenderMapper tenderMapper;
	
	/**
	 * 活动是否过期
	 * @return
	 * @author louchen 2015-03-31
	 */
	private Boolean isActivityExpiration(){
		Date start = DateUtil.getDateToString(fifteenAprilActivityStart,
				"yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(fifteenAprilActivityEnd,
				"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 我的成就列表
	 * @return
	 */
	@NeedSession(returnUrl="/activity/april/myAchievementPage")
	@RequestMapping("/myAchievementPage")
	public ModelAndView myAchievementPage() {
		ModelAndView mv = new ModelAndView();
		if(UserSession.getUserSession()!=null){
			mv.setViewName("activity/aprilAchievement");
			BigDecimal aprilTenderTotal = tenderMapper.myTotalTenderAccountForApril(UserSession.getUserSession().getUserId());
			BigDecimal conditionTotal = new BigDecimal(0);
			int top = 0;
			int bottom = 0;
			AchievementLog al1 = achievementLogService.selectByAchievementIdAndUserId(1, UserSession.getUserSession().getUserId());
			AchievementLog al2 = achievementLogService.selectByAchievementIdAndUserId(2, UserSession.getUserSession().getUserId());
			AchievementLog al3 = achievementLogService.selectByAchievementIdAndUserId(3, UserSession.getUserSession().getUserId());
			AchievementLog al4 = achievementLogService.selectByAchievementIdAndUserId(4, UserSession.getUserSession().getUserId());
			AchievementLog al5 = achievementLogService.selectByAchievementIdAndUserId(5, UserSession.getUserSession().getUserId());
			AchievementLog al6 = achievementLogService.selectByAchievementIdAndUserId(6, UserSession.getUserSession().getUserId());
			AchievementLog al7 = achievementLogService.selectByAchievementIdAndUserId(7, UserSession.getUserSession().getUserId());
			AchievementLog al8 = achievementLogService.selectByAchievementIdAndUserId(8, UserSession.getUserSession().getUserId());
			Achievement a1 = achievementService.selectByPrimaryKey(1);
			Achievement a2 = achievementService.selectByPrimaryKey(2);
			Achievement a3 = achievementService.selectByPrimaryKey(3);
			Achievement a4 = achievementService.selectByPrimaryKey(4);
			Achievement a5 = achievementService.selectByPrimaryKey(5);
			Achievement a6 = achievementService.selectByPrimaryKey(6);
			Achievement a7 = achievementService.selectByPrimaryKey(7);
			Achievement a8 = achievementService.selectByPrimaryKey(8);
			if(EmptyUtil.isEmpty(al1)){
				top=0;
				bottom=0;
				conditionTotal = a1.getCondition();
			}else if(EmptyUtil.isEmpty(al2)){
				top=20;
				bottom=0;
				conditionTotal = a2.getCondition();
			}else if(EmptyUtil.isEmpty(al3)){
				top=48;
				bottom=0;
				conditionTotal = a3.getCondition();
			}else if(EmptyUtil.isEmpty(al4)){
				top=75;
				bottom=0;
				conditionTotal = a4.getCondition();
			}else if(EmptyUtil.isEmpty(al5)){
				top=100;
				bottom=1;
				conditionTotal = a5.getCondition();
			}else if(EmptyUtil.isEmpty(al6)){
				top=100;
				bottom=25;
				conditionTotal = a6.getCondition();
			}else if(EmptyUtil.isEmpty(al7)){
				top=100;
				bottom=54;
				conditionTotal = a7.getCondition();
			}else if(EmptyUtil.isEmpty(al8)){
				top=100;
				bottom=82;
				conditionTotal = a8.getCondition();
			}else{
				top=100;
				bottom=100;
				conditionTotal = a8.getCondition();
			}
			if(EmptyUtil.isNotEmpty(al1)){
				mv.addObject("al1",al1);
			}
			if(EmptyUtil.isNotEmpty(al2)){
				mv.addObject("al2",al2);
			}
			if(EmptyUtil.isNotEmpty(al3)){
				mv.addObject("al3",al3);
			}
			if(EmptyUtil.isNotEmpty(al4)){
				mv.addObject("al4",al4);
			}
			if(EmptyUtil.isNotEmpty(al5)){
				mv.addObject("al5",al5);
			}
			if(EmptyUtil.isNotEmpty(al6)){
				mv.addObject("al6",al6);
			}
			if(EmptyUtil.isNotEmpty(al7)){
				mv.addObject("al7",al7);
			}
			if(EmptyUtil.isNotEmpty(al8)){
				mv.addObject("al8",al8);
			}
			mv.addObject("aprilTenderTotal",aprilTenderTotal.setScale(0,BigDecimal.ROUND_DOWN));
			mv.addObject("conditionTotal",conditionTotal.setScale(0,BigDecimal.ROUND_DOWN));
			mv.addObject("substractTotal",(conditionTotal.subtract(aprilTenderTotal)).setScale(0,BigDecimal.ROUND_DOWN));
			mv.addObject("top", top);
			mv.addObject("bottom",bottom);
		}else{
			mv.setViewName("notFound");
		}
		return mv;
	}
		
	/**
	 * 领取成就奖励
	 * @param achievementId
	 * @return
	 * @author louchen 2015-04-01
	 */
	@NeedSession(returnUrl="/activity/april/myAchievementPage")
	@RequestMapping(value="/pickAchievement",method=RequestMethod.POST)
	@ResponseBody
	public Json pickAchievement(@RequestParam("achievementId") Integer achievementId) {
		Json json = new Json();
		if(!isActivityExpiration()){
			if(UserSession.getUserSession()!=null){
				if(UserSession.getUserSession().getUserId()!=null && UserSession.getUserSession().getUserId()>0 ){
					AchievementLog al = achievementLogService.selectByAchievementIdAndUserId(achievementId, UserSession.getUserSession().getUserId());
					if(EmptyUtil.isNotEmpty(al) && al.getId()>0 
							&& al.getStatus()!=null && !al.getStatus().equals(1) 
							&& al.getUserId().equals(UserSession.getUserSession().getUserId())){
						int i = achievementLogService.pickAchievementLog(achievementId, UserSession.getUserSession().getUserId());
						if(i>0){
							json.setMsg("领取成就奖励成功");
							json.setSuccess(true);
						}else{
							json.setMsg("领取成就奖励失败");
						}
					}else{
						json.setMsg("参数异常"); 
					}
				}else{
					json.setMsg("当前用户ID异常");
				}			
			}else{
				json.setMsg("未登录或登录已过期");
			}
		}else{
			json.setMsg("活动还没开始或者活动已过期");
		}
		return json;
	}
}
