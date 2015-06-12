package cn.vfunding.vfunding.prd.www.prize.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRule;
import cn.vfunding.vfunding.biz.prize.model.PrizeFromLog;
import cn.vfunding.vfunding.biz.prize.model.PrizeRepository;
import cn.vfunding.vfunding.biz.prize.service.IPrizeFromLogService;
import cn.vfunding.vfunding.biz.prize.service.IPrizeRepositoryService;
import cn.vfunding.vfunding.biz.prize.service.IPrizeService;
import cn.vfunding.vfunding.biz.prize.vo.PrizeRepositoryVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

/**
 * 第一季抽奖活动Controller
 * @author louchen 2014-12-16
 *
 */
@Controller
@RequestMapping( value={"/prize/season1","/activity/prize/season1"})
public class PrizeSeasonOneController extends BaseController {
	@Autowired
	private IPrizeService prizeService;
	@Autowired
	private IPrizeRepositoryService prizeRepositoryService;
	@Autowired
	private IPrizeFromLogService prizeFromLogService;
	@Autowired
	private UserMapper userMapper;

	@Value("${AnniversarySeason1TurnplateStart}")
	private String anniversarySeason1TurnplateStart;
	
	@Value("${AnniversarySeason1TurnplateEnd}")
	private String anniversarySeason1TurnplateEnd;
	
	/**
	 * 跳转到抽奖页面
	 * @return
	 */
	@RequestMapping(value="/toChoosePage")
	@ResponseBody
	public ModelAndView toChoosePage(Integer f,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("utilPage/anniversary");
		return mv;
	}
	
	/**
	 * 我的中奖记录
	 * @return 
	 * @author louchen 2014-12-29
	 */
	@RequestMapping(value="/myPrizeChooseList")
	@ResponseBody
	@NeedSession(returnUrl = "/prize/season1/toChoosePage")
	public List<PrizeRepositoryVO> myPrizeChooseList() {
		//我的中奖列表,4条
		PrizeRepository search = new PrizeRepository();
		search.setChooseUser((Integer)(UserSession.getLoginUserId()));
		search.setStartIndex(0);
		search.setEndIndex(4);
		List<PrizeRepositoryVO> myPrizeChooseList = this.prizeRepositoryService.selectMyPrizeChoose(search);
		return myPrizeChooseList;
	}
	
	/**
	 * 滚动条抽奖记录
	 * @return
	 * @author louchen 2014-12-29
	 */
	@RequestMapping(value="/prizeChooseList")
	@ResponseBody
	public List<PrizeRepositoryVO> prizeChooseList() {
		//滚动条抽奖记录，20条
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startIndex", 0);
		map.put("endIndex", 20);
		List<PrizeRepositoryVO> prizeChooseList = this.prizeRepositoryService.selectPrizeChoose(map);
		return prizeChooseList;
	}
	
	/**
	 * 增加分享链接次数
	 * @return
	 */
	@RequestMapping(value="/shareLink")
	@ResponseBody
	public Json shareLink(Integer f,HttpServletRequest request){
		Json json = new Json();
		PrizeFromLog search;
		List<PrizeFromLog> list;
		Date start = DateUtil.getDateToString(anniversarySeason1TurnplateStart, "yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(anniversarySeason1TurnplateEnd, "yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		//分享链接增加次数(并且在活动有效期内)
		if(now.compareTo(start)>=0 && now.compareTo(end)<=0 && EmptyUtil.isNotEmpty(f)){
			//分享人id必须有效
			UserWithBLOBs share = this.userMapper.selectByPrimaryKey(f);
			if(EmptyUtil.isNotEmpty(share)&&share.getUserId()>0){
				search = new PrizeFromLog();
				search.setAddSource(ModelAndViewUtil.getIpAddr(request));
				search.setAddDate(DateUtil.getDateToString(DateUtil.parseDate(new Date()), "yyyy-MM-dd"));
				list = this.prizeFromLogService.selectByCondition(search);
				//当前登录人的ip或者手机识别号今日是否已存在
				if(list.size()==0){
					PrizeFromLog pfl = new PrizeFromLog();
					pfl.setFromUser(f);
					pfl.setAddSource(ModelAndViewUtil.getIpAddr(request));
					pfl.setAddDate(DateUtil.getDateToString(DateUtil.parseDate(new Date()), "yyyy-MM-dd"));
					this.prizeFromLogService.insertSelective(pfl);
				}
			}
		}
		return json;
	}
	
	/**
	 * 抽奖
	 * @return
	 * @author louchen 2014-12-16
	 */
	@RequestMapping(value="/choose")
	@ResponseBody
	@NeedSession(returnUrl = "/prize/season1/toChoosePage")
	public Json choose(HttpServletRequest request){
		Json json = new Json();
		Date start = DateUtil.getDateToString(anniversarySeason1TurnplateStart, "yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(anniversarySeason1TurnplateEnd, "yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if(now.compareTo(start)<0){
			json.setMsg("一周年庆抽奖活动还未开始");
			return json;
		}else if(now.compareTo(end)>0){
			json.setMsg("一周年庆抽奖活动已经结束");
			return json;
		}else{
			return this.prizeService.choosePrize(UserSession.getUserSession(),request,"season1");
		}
	}
	
	/**
	 * 获取当前登录人的抽奖次数
	 * @return
	 * @author louchen 2014-12-18
	 */
	@RequestMapping(value="/getUserPrizeChooseRule")
	@ResponseBody
	@NeedSession(returnUrl = "/prize/season1/toChoosePage")
	public Map<String,Object> getUserPrizeChooseRule(){
		Date start = DateUtil.getDateToString(anniversarySeason1TurnplateStart, "yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(anniversarySeason1TurnplateEnd, "yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		PrizeChooseRule pcr;
		Map<String,Object> map = new HashMap<String,Object>();
		//在活动有效期内
		if(now.compareTo(start)>=0 && now.compareTo(end)<=0){
			//今日分享次数>=5次，今日抽奖次数+1(并且在活动有效期内)
			PrizeFromLog search = new PrizeFromLog();
			search.setFromUser(UserSession.getLoginUserId());
			search.setAddDate(DateUtil.getDateToString(DateUtil.parseDate(new Date()), "yyyy-MM-dd"));
			List<PrizeFromLog> list = this.prizeFromLogService.selectByCondition(search);
			if(list.size()>=5){
				 this.prizeService.addUserPrizeChooseCount(UserSession.getLoginUserId(),3);
			}		
			//返回次数
			pcr = this.prizeService.addUserPrizeChooseCount(UserSession.getLoginUserId(),1);
		}else{
			pcr = this.prizeService.getUserPrizeChooseRule(UserSession.getLoginUserId());
		}	
		map.put("count",pcr.getChooseCount()-pcr.getChooseRealityCount());
		map.put("shareId", UserSession.getLoginUserId());
		return map;
	}	
	
	
}
