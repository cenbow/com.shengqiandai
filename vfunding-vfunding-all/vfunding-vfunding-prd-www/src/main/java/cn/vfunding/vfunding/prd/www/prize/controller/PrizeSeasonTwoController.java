package cn.vfunding.vfunding.prd.www.prize.controller;

import java.util.ArrayList;
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
import cn.vfunding.vfunding.biz.inviteCode.model.InviteCode;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRule;
import cn.vfunding.vfunding.biz.prize.model.PrizeFromLog;
import cn.vfunding.vfunding.biz.prize.model.PrizeRepository;
import cn.vfunding.vfunding.biz.prize.service.IPrizeFromLogService;
import cn.vfunding.vfunding.biz.prize.service.IPrizeRepositoryService;
import cn.vfunding.vfunding.biz.prize.service.IPrizeSeasonTwoService;
import cn.vfunding.vfunding.biz.prize.vo.ActivityPrizeVO;
import cn.vfunding.vfunding.biz.prize.vo.PrizeRepositoryVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

/**
 * 第二季抽奖活动Controller
 * @author louchen 2015-03-10
 *
 */
@Controller
@RequestMapping( value={"/prize/season2","/activity/prize/season2"})
public class PrizeSeasonTwoController extends BaseController {
	//第二季抽奖service
	@Autowired
	private IPrizeSeasonTwoService prizeSeasonTwoService;
	@Autowired
	private IPrizeRepositoryService prizeRepositoryService;
	@Autowired
	private IPrizeFromLogService prizeFromLogService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IInviteCodeService inviteCodeService;

	@Value("${Season2TurnplateStart}")
	private String season2TurnplateStart;
	
	@Value("${Season2TurnplateEnd}")
	private String season2TurnplateEnd;
	
	
	/**
	 * 活动相关信息
	 * @return
	 */
	@RequestMapping(value="/ajaxActivityInfo")
	@ResponseBody
	public Json ajaxActivityInfo(){
		Json j = new Json();
		Map<String,Object> map = new HashMap<String,Object>();
		List<ActivityPrizeVO> list;
		list= this.prizeSeasonTwoService.selectRegisterAndSetRealNameFromInvite();
		this.putMapForActivityInfo(map,list,"c1",true);
		list= this.prizeSeasonTwoService.selectTenderFromInvite();
		this.putMapForActivityInfo(map,list,"c2",true);
		list= this.prizeSeasonTwoService.selectAccountFromInvite();
		this.putMapForActivityInfo(map,list,"c3",false);
		j.setObj(map);
		j.setSuccess(true); 
		return j;		
	}
	
	/**
	 * 活动相关信息putmap
	 * @return
	 */
	private void putMapForActivityInfo(Map<String,Object> map,List<ActivityPrizeVO> list,String name,Boolean type){
		if(list.size()>0){
			if(type){
				map.put(name, list.get(0).getAllcount());
			}else{
				map.put(name, list.get(0).getAllaccount());
			}
		}else{
			map.put(name, "0");
		}
	}	
	
	/**
	 * 排行榜数据
	 * @return
	 */
	@RequestMapping(value="/dialogTop")
	@ResponseBody
	public Json dialogTop(String type){
		Integer userId = UserSession.getLoginUserId();
		Json j=new Json();
		if(EmptyUtil.isEmpty(type)){
			j.setMsg("参数错误");
		}else{
			if(userId != null){
				Map<String , Object> map = new HashMap<String , Object>();
				List<ActivityPrizeVO> list=null;
				List<ActivityPrizeVO> viewList=null;
				if(type.equals("1")){
					list= this.prizeSeasonTwoService.selectRegisterAndSetRealNameFromInvite();
				}else if(type.equals("2")){
					list= this.prizeSeasonTwoService.selectTenderFromInvite();
				}else if(type.equals("3")){
					list= this.prizeSeasonTwoService.selectAccountFromInvite();
				}				
				viewList = this.getViewList(list);
				map.put("viewList", viewList);
				Map<String,String> userMap = this.selectListIndexByUserId(list , userId , Integer.parseInt(type));
				map.put("userMap", userMap);
				j.setObj(map);
				j.setSuccess(true);
			}else{
				j.setMsg("未登录");
			}
		}		
		return j;
	}
	
	/**
	 * 当前用户在list中的位置
	 * @param srcList
	 * @param userId
	 * @param type
	 * @return
	 */
	private Map<String,String> selectListIndexByUserId(List<? extends ActivityPrizeVO> srcList , Integer userId ,Integer type){
		Map<String,String> map = new HashMap<String,String>();
		map.put("index" , "未进入排名");
		map.put("username" , UserSession.getUserSession().getUserName());
		map.put("result", "0");
		for(int i = 0 ; i < srcList.size() ; i++){
			ActivityPrizeVO vo = srcList.get(i);
			if(vo.getInviteUserid().equals(userId)){
				map.put("index", i+1+"");
				if(type.equals(3)){
					map.put("result",vo.getAllaccount().toString());
				}else{
					map.put("result",vo.getAllcount().toString());
				}
				break;
			}
		}
		return map ;
	}
	
	
	/**
	 * 取N条数据
	 * @param srcList
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getViewList(List srcList){
		int num = 5;
		List result = new ArrayList();
		for (int i = 0; i < srcList.size(); i++) {
			if(i == num)
				break;
			result.add(srcList.get(i));
		}
		return result;
	}
	
	/**
	 * 跳转到抽奖页面
	 * @param f
	 * @param request
	 * @return
	 * @date 2015-03-11
	 * @author louchen
	 */
	@RequestMapping(value="/toChoosePage")
	@ResponseBody
	public ModelAndView toChoosePage(Integer f,HttpServletRequest request){
		String inviteCode = "";
		if(EmptyUtil.isNotEmpty(f)){
			UserWithBLOBs share = this.userMapper.selectByPrimaryKey(f);
			if(EmptyUtil.isNotEmpty(share)){
				InviteCode ic = inviteCodeService.selectByUserId(f);
				if(EmptyUtil.isNotEmpty(ic)){
					inviteCode = ic.getInviteCode();
				}
			}
		}
		ModelAndView mv = new ModelAndView("utilPage/MarchActivity");
		mv.addObject("inviteCode",inviteCode);
		return mv;
	}
	
	/**
	 * 我的中奖记录
	 * @return 
	 * @date 2015-03-11
	 * @author louchen 
	 */
	@RequestMapping(value="/myPrizeChooseList")
	@ResponseBody
	@NeedSession(returnUrl = "/prize/season2/toChoosePage")
	public List<PrizeRepositoryVO> myPrizeChooseList() {
		//我的中奖列表,4条
		PrizeRepository search = new PrizeRepository();
		search.setChooseUser((Integer)(UserSession.getLoginUserId()));
		search.setStartIndex(0);
		search.setEndIndex(4);
		List<PrizeRepositoryVO> myPrizeChooseList = this.prizeRepositoryService.selectMyPrizeChooseForSeasonTwo(search);
		return myPrizeChooseList;
	}
	
	/**
	 * 滚动条抽奖记录
	 * @return
	 * @date 2015-03-11
	 * @author louchen
	 */
	@RequestMapping(value="/prizeChooseList")
	@ResponseBody
	public List<PrizeRepositoryVO> prizeChooseList() {
		//滚动条抽奖记录，20条
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("startIndex", 0);
		map.put("endIndex", 20);
		List<PrizeRepositoryVO> prizeChooseList = this.prizeRepositoryService.selectPrizeChooseForSeasonTwo(map);
		return prizeChooseList;
	}
	
	/**
	 * 增加分享链接次数
	 * @param f
	 * @param request
	 * @return
	 * @date 2015-03-11
	 * @author louchen
	 */
	@RequestMapping(value="/shareLink")
	@ResponseBody
	public Json shareLink(Integer f,HttpServletRequest request){
		Json json = new Json();
		PrizeFromLog search;
		List<PrizeFromLog> list;
		Date start = DateUtil.getDateToString(season2TurnplateStart, "yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(season2TurnplateEnd, "yyyy-MM-dd HH:mm:ss");
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
	 * @param request
	 * @return
	 * @date 2015-03-11
	 * @author louchen
	 */
	@RequestMapping(value="/choose")
	@ResponseBody
	@NeedSession(returnUrl = "/prize/season2/toChoosePage")
	public Json choose(HttpServletRequest request){
		Json json = new Json();
		Date start = DateUtil.getDateToString(season2TurnplateStart, "yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(season2TurnplateEnd, "yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if(now.compareTo(start)<0){
			json.setMsg("2015年3月抽奖活动还未开始");
			return json;
		}else if(now.compareTo(end)>0){
			json.setMsg("2015年3月抽奖活动已经结束");
			return json;
		}else{
			return this.prizeSeasonTwoService.choosePrize(UserSession.getUserSession(),request,"season2");
		}
	}
	
	/**
	 * 获取当前登录人的抽奖次数
	 * @return
	 * @date 2015-03-11
	 * @author louchen 
	 */
	@RequestMapping(value="/getUserPrizeChooseRule")
	@ResponseBody
	@NeedSession(returnUrl = "/prize/season2/toChoosePage")
	public Map<String,Object> getUserPrizeChooseRule(){
		Date start = DateUtil.getDateToString(season2TurnplateStart, "yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(season2TurnplateEnd, "yyyy-MM-dd HH:mm:ss");
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
				 this.prizeSeasonTwoService.addUserPrizeChooseCount(UserSession.getLoginUserId(),3);
			}		
			//返回次数
			pcr = this.prizeSeasonTwoService.addUserPrizeChooseCount(UserSession.getLoginUserId(),1);
		}else{
			pcr = this.prizeSeasonTwoService.getUserPrizeChooseRule(UserSession.getLoginUserId());
		}	
		map.put("count",pcr.getChooseCount()-pcr.getChooseRealityCount());
		map.put("shareId", UserSession.getLoginUserId());
		return map;
	}	
	
	
}
