package cn.vfunding.vfunding.prd.www.financialPlanner.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.financialPlanner.service.IFinancialPlannerService;
import cn.vfunding.vfunding.biz.financialPlanner.vo.FinancialPlannerVO;
import cn.vfunding.vfunding.biz.financialPlanner.vo.LeaderboardVO;
import cn.vfunding.vfunding.biz.inviteCode.model.InviteCode;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.returns.model.ReturnFeeData;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.model.NumberInvitationVO;
import cn.vfunding.vfunding.biz.user.service.IUserService;

/**
 * 
 * @author wang.zeyan
 * @date 2015年2月28日
 * @version 1.0
 */

@Controller
@RequestMapping(value = { "/financialPlanner", "/fp" })
public class FinancialPlannerController extends BaseController {
	@Autowired
	private IFinancialPlannerService financialPlannerService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IInviteCodeService inviteCodeService;
	@Autowired
	private IUserService userService;
	/**
	 * 跳转理财师页面
	 * 
	 * @return
	 * 
	 *         jianwei
	 */
	@NeedSession(returnUrl = "/fp/toFpPage")
	@RequestMapping("/toFpPage")
	public ModelAndView toFpPage() {
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "user/licaishi");
		DecimalFormat df = new DecimalFormat("0.00");
		// 送返利收益
		BigDecimal sumFees = financialPlannerService.selectSumFeesByUserId(UserSession.getLoginUserId());
		// 上月返利收益
		BigDecimal lastSumFees = financialPlannerService.selectSumFeesLastMonthByUserId(UserSession.getLoginUserId());
		InviteCode ic = inviteCodeService.selectByUserId(UserSession.getLoginUserId());
		mv.addObject("sumFees", df.format(sumFees));
		mv.addObject("lastSumFees", df.format(lastSumFees));
		if (ic != null) {
			mv.addObject("inviteCode", ic.getInviteCode());
		}
		return mv;
	}

	/**
	 * 访问邀请人url
	 * 
	 * @param inviteCode
	 * @param ra
	 * @return
	 * 
	 *         jianwei
	 */
	@RequestMapping("/inviteRegister/{inviteCode}")
	public ModelAndView inviteRegister(@PathVariable("inviteCode") String inviteCode, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView("redirect:/goRegister");
		ra.addAttribute("inviteCode", inviteCode);
		return mv;
	}

	/**
	 * 返利明细页面
	 * @return
	 *
	 * jianwei
	 */
	@NeedSession(returnUrl = "/fp/toFpPage")
	@RequestMapping("/rebateDetail")
	public ModelAndView rebateDetail(){
		ModelAndView mv = ModelAndViewUtil.createModelAndViewAndAddAccountToView(accountService, "fp/rebateDetail");
		Map<String, Object> map = new HashMap<String, Object>();
		DecimalFormat df = new DecimalFormat("0.00");
		BigDecimal oneFees = financialPlannerService.selectSumOneFees(UserSession.getLoginUserId());
		BigDecimal twoFees = financialPlannerService.selectSumTwoFees(UserSession.getLoginUserId());
		BigDecimal threeFees = financialPlannerService.selectSumThreeFees(UserSession.getLoginUserId());
		//送返利收益
		BigDecimal allFees = financialPlannerService.selectSumFeesByUserId(UserSession.getLoginUserId());
		map.put("oneFees", df.format(oneFees));
		map.put("twoFees", df.format(twoFees));
		map.put("threeFees", df.format(threeFees));
		map.put("allFees", df.format(allFees));
		mv.addObject("rebate", map);
		return mv;
	}
	
	/**
	 * 返利明细列表
	 * @param pageSearch
	 * @param status
	 * @return
	 *
	 * jianwei
	 */
	@NeedSession(returnUrl = "/fp/rebateDetail")
	@RequestMapping(value = "/rebateDetailAjax")
	@ResponseBody
	public PageResult<ReturnFeeData> rebateList(PageSearch pageSearch, Integer status) {
		PageResult<ReturnFeeData> result = new PageResult<ReturnFeeData>();
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			ReturnFeeData vo = new ReturnFeeData();
			vo.setUserId(UserSession.getLoginUserId());
			pageSearch.setEntity(vo);
			List<ReturnFeeData> pageList = this.financialPlannerService.selectRebateDatailListPage(pageSearch);
			result.setRows(pageList);
			result.setTotal(pageSearch.getTotalResult());
		}
		return result;
	}

	/**
	 * 邀请人数排行榜
	 * wang.zeyan 2015年3月2日
	 * @return
	 */
	@RequestMapping("/nil")
	@ResponseBody
	public Json NumberInvitationLeaderboard(){
		Integer userId = UserSession.getLoginUserId();
		Json j=new Json();
		if(userId != null){
			List<NumberInvitationVO> list= financialPlannerService.NumberInvitationLeaderboard();
			List<LeaderboardVO> viewList = getViewList(list);
			Map<String,String> userMap = selectListIndexByUserId(list , userId , numberInvitationLeaderboard);
			
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("userMap", userMap);
			map.put("viewList", viewList);
			j.setObj(map);
			j.setSuccess(true);
		}else{
			j.setMsg(" 未登陆！ ");
			j.setSuccess(false);
		}
		return j;
	}
	/**
	 * 返利排行榜
	 * wang.zeyan 2015年2月28日
	 * @return
	 */
	@RequestMapping("/rpl")
	@ResponseBody
	public Json returnProfitLeaderboard(){
		Integer userId = UserSession.getLoginUserId();
		Json j=new Json();
		if(userId != null){
			List<FinancialPlannerVO> list = financialPlannerService.returnProfitLeaderboard();
			List<LeaderboardVO> viewList = getViewList(list);
			Map<String,String> userMap = selectListIndexByUserId(list , userId , returnProfitLeaderboard);
			
			
			Map<String , Object> map = new HashMap<String , Object>();
			map.put("userMap", userMap);
			map.put("viewList", viewList);
			j.setObj(map);
			j.setSuccess(true);
		}else{
			j.setMsg(" 未登陆！ ");
			j.setSuccess(false);
		}
		return j;
	}
	
	/**
	 * 显示给前台的排行榜List
	 * 
	 * wang.zeyan 2015年2月28日
	 * @param srcList
	 * @return
	 */
	private List<LeaderboardVO> getViewList(List<? extends LeaderboardVO> srcList){
		int num = 10;
		List<LeaderboardVO> result = new ArrayList<LeaderboardVO>();
		for (int i = 0; i < srcList.size(); i++) {
			if(i == num)
				break;
			result.add(srcList.get(i));
		}
		return result;
	}
	
	/**
	 * 查询当前用户在List中的信息。
	 * 
	 * wang.zeyan 2015年2月28日
	 * @param srcList
	 * @param userId
	 * @return
	 */
	private static final String index = "index",username="username",feesSum="feesSum",number="number";
	private static final int returnProfitLeaderboard=0,numberInvitationLeaderboard=1;
	private Map<String,String> selectListIndexByUserId(List<? extends LeaderboardVO> srcList , int userId ,int type){
		Map<String,String> map = new HashMap<String,String>();
		map.put(index , "未进入排名");
		map.put(username , UserSession.getUserSession().getUserName());
		map.put(feesSum, "0.00");
		map.put(number, "0");
		for(int i = 0 ; i < srcList.size() ; i++){
			LeaderboardVO vo = srcList.get(i);
			if(vo.getUserId() == userId){
				map.put(index, i+"");
				if(type == returnProfitLeaderboard){
					if(vo instanceof FinancialPlannerVO)
						map.put(feesSum, ((FinancialPlannerVO)vo).getFeesSum());
				}else if(type == numberInvitationLeaderboard){
					if(vo instanceof NumberInvitationVO)
						map.put(number, ((NumberInvitationVO)vo).getNumber()+"");
				}
				break;
			}
			
		}
		return map ;
	}

	
//	@RequestMapping("/getQRCode")
//	public ResponseEntity<byte[]> getUserQRCode() throws Exception {
//		StringBuffer sb = new StringBuffer("");
//		InviteCode ic = inviteCodeService.selectByUserId(UserSession.getLoginUserId());
//		if(ic != null){
//			sb.append("http://m.vfunding.cn/utilPage/fpPage"+ic.getInviteCode());
//		}else{
//			sb.append("http://m.vfunding.cn/utilPage/fpPage");
//		}
//		File logoFile = new File("WebContent/images/qrcoderLogo.jpg");
//		byte[] data = this.userService.getUserQRCode(sb.toString(), logoFile);
//		String contentType = "image/png";
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Content-Type", contentType);
//		headers.add("Cache-Control", "max-age=315360000");
//		headers.add("Last-Modified", DateUtil.parseDateTime(new Date()));
//		return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
//	}
//	
}
