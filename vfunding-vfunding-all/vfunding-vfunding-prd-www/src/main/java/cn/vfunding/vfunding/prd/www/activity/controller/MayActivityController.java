package cn.vfunding.vfunding.prd.www.activity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.prize.dao.PrizeMapper;
import cn.vfunding.vfunding.biz.prize.vo.ActivityPrizeVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;

/**
 * 2015年4月活动
 * 
 * @author louchen 2015-03-31
 * 
 */
@Controller()
@RequestMapping("/activity/may")
public class MayActivityController extends BaseController {
	
	//@Value("${MayActivityStart}")
	private String ActivityStart;
	
	//@Value("${MayActivityEnd}")
	private String ActivityEnd;
	
	@Autowired
	private PrizeMapper prizeMapper;
	
	/**
	 * 活动是否过期
	 * @return
	 * @author louchen 2015-03-31
	 */
	private Boolean isActivityExpiration(){
		Date start = DateUtil.getDateToString(ActivityStart,
				"yyyy-MM-dd HH:mm:ss");
		Date end = DateUtil.getDateToString(ActivityEnd,
				"yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
			return false;
		}else{
			return true;
		}
	}
	

	@RequestMapping(value="/dialogTop",method=RequestMethod.POST)
	@ResponseBody
	public Json dialogTop(@RequestParam("type") String type) {
		Integer userId = 0;
		if(UserSession.getUserSession()!=null && UserSession.getUserSession().getUserId()!=null){
			userId = UserSession.getUserSession().getUserId();
		}
		Json j=new Json();
		if(EmptyUtil.isEmpty(type)){
			j.setMsg("参数错误");
		}else{
			if(userId != null){
				Map<String , Object> map = new HashMap<String , Object>();
				List<ActivityPrizeVO> list=null;
				List<ActivityPrizeVO> viewList=null;
				if(type.equals("1")){
					list= null;
				}else if(type.equals("2")){
					list= this.prizeMapper.selectTenderFromInviteForMay();
				}else if(type.equals("3")){
					list= this.prizeMapper.selectAccountFromInviteForMay();
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
		if(userId>0){
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
		}else{
			map.put("index" , "未进入排名");
			map.put("username" , "未登录");
			map.put("result", "0");
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
		int num = 8;
		List result = new ArrayList();
		for (int i = 0; i < srcList.size(); i++) {
			if(i == num)
				break;
			result.add(srcList.get(i));
		}
		return result;
	}
}
