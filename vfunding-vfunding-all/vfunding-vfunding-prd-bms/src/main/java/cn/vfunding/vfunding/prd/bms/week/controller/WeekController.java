package cn.vfunding.vfunding.prd.bms.week.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.MultipartEntityBuilderUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.dao.MessageMapper;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.system.service.IAttestationService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawn;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawnPic;
import cn.vfunding.vfunding.biz.week.model.WeekRate;
import cn.vfunding.vfunding.biz.week.model.WeekRepayment;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowPawnPicService;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowPawnService;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowService;
import cn.vfunding.vfunding.biz.week.service.IWeekRateService;
import cn.vfunding.vfunding.biz.week.service.IWeekRepaymentService;
import cn.vfunding.vfunding.biz.week.service.IWeekService;
import cn.vfunding.vfunding.biz.week.service.IWeekTenderService;
import cn.vfunding.vfunding.biz.week.service.IWeekUserService;
import cn.vfunding.vfunding.biz.week.vo.SearchRepaymentRecordVO;
import cn.vfunding.vfunding.biz.week.vo.SearchWeekBorrowVO;
import cn.vfunding.vfunding.biz.week.vo.SearchWeekRepaymentVO;
import cn.vfunding.vfunding.biz.week.vo.SearchWeekTenderVO;
import cn.vfunding.vfunding.biz.week.vo.SearchWeekVO;
import cn.vfunding.vfunding.biz.week.vo.TrialBorrowVO;
import cn.vfunding.vfunding.biz.week.vo.WeekRepaymentVO;
import cn.vfunding.vfunding.biz.week.vo.WeekTenderVO;
import cn.vfunding.vfunding.biz.week.vo.WeekUserVO;
import cn.vfunding.vfunding.biz.week.vo.WeekVO;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

import com.alibaba.druid.util.StringUtils;

@Controller
@RequestMapping(value = { "/system/week", "/week" })
public class WeekController extends BaseController {
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private IWeekService weekService;
	@Autowired
	private IWeekBorrowService weekBorrowService;
	@Autowired
	private IWeekBorrowPawnService weekBorrowPawnService;
	@Autowired
	private IWeekBorrowPawnPicService weekBorrowPawnPicService;
	@Autowired
	private IAttestationService attestationService;
	@Autowired
	private IWeekRepaymentService weekRepaymentService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IWeekUserService weekUserService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IWeekTenderService weekTenderService;
	@Autowired
	private IWeekRateService weekRateService;	

	/**
	 * 异常控制
	 * 返回json对象,ajax异步加载出错时输出错误信息
	 * @param runtimeException
	 * @return
	 * @author louchen 2014-12-12
	 */
    @ExceptionHandler(RuntimeException.class)  
    @ResponseBody 
    public Json runtimeExceptionHandler(RuntimeException runtimeException) {  
    	logger.error(runtimeException.getMessage());  
    	Json json = new Json();
    	json.setMsg(runtimeException.getLocalizedMessage());
    	runtimeException.printStackTrace();
        return json;  
    }	
	
	/**
	 * 跳转到计划列表页面
	 * 
	 * @return ModelAndView
	 * @author louchen 2014-12-3
	 */
	@RequestMapping(value = "/weekListPage")
	@ResponseBody
	public ModelAndView weekListPage() {
		ModelAndView mv = new ModelAndView("webpage/week/weekList");
		// 新建权限
		boolean canEdit = UserAuthFilter.isPass("/system/week/addOrEditWeek");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		// 删除权限
		boolean canDelete = UserAuthFilter.isPass("/system/week/deleteWeek");
		mv.addObject("canDelete", canDelete);
		// 当前日期
		// mv.addObject("saleTime", DateUtil.parseDate(new Date()));
		return mv;
	}

	/**
	 * 计划管理列表
	 * 
	 * @param pageSearch
	 * @param search
	 * @return
	 * @throws Exception
	 * @author louchen 2014-12-3
	 */
	@ParentSecurity("/system/week/weekListPage")
	@RequestMapping(value = "/weekList")
	@ResponseBody
	public PageResult<WeekVO> weekList(PageSearch pageSearch,
			SearchWeekVO searchVO) throws Exception {
		PageResult<WeekVO> results = new PageResult<WeekVO>();
		// 时间转换
		if (EmptyUtil.isNotEmpty(searchVO.getSaleTime())) {
			searchVO.setSaleTime(searchVO.getSaleTime() + " 00:00:00");
		}
		if (EmptyUtil.isNotEmpty(searchVO.getExpireTime())) {
			searchVO.setExpireTime(searchVO.getExpireTime() + " 23:59:59");
		}
		pageSearch.setEntity(searchVO);
		List<WeekVO> weekList = this.weekService.selectWeekVOListPage(pageSearch);
		results.setRows(weekList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * 跳转到新增或编辑计划页面
	 * 
	 * @param weekVO
	 * @return
	 * @author louchen 2014-12-3
	 */
	@RequestMapping(value = "/addOrEditWeekPage")
	@ResponseBody
	public ModelAndView addOrEditWeekPage(WeekVO weekVO) {
		ModelAndView mv = new ModelAndView("webpage/week/addOrEditWeek");
		if (EmptyUtil.isEmpty(weekVO.getId())) {
			this.editDefaultWeekPage(mv, weekVO);
		} else {
			this.editModifyWeekPage(mv, weekVO);
		}
		return mv;
	}

	/**
	 * 初始化编辑页面数据库的值
	 * 
	 * @param mv
	 * @param weekVO
	 * @author louchen 2014-12-4
	 */
	private void editModifyWeekPage(ModelAndView mv, WeekVO weekVO) {
		Week week = this.weekService.selectByPrimaryKey(weekVO.getId());
		WeekRate weekRate = this.weekRateService.selectByPrimaryKey(weekVO.getId());
		mv.addObject("week", week);
		mv.addObject("weekRate", weekRate);
		mv.addObject("formatSaleTime", DateUtil.getDateString(week.getSaleTime(), "yyyy-MM-dd HH:mm:ss"));
		mv.addObject("formatExpireTime", DateUtil.getDateString(week.getExpireTime(), "yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * 初始化新增页面的默认值
	 * 
	 * @param mv
	 * @param weekVO
	 * @author louchen 2014-12-4
	 */
	private void editDefaultWeekPage(ModelAndView mv, WeekVO weekVO) {
		weekVO.setName("周盈宝X期");
		weekVO.setTimeLimit(7);
		weekVO.setSharePrice(new BigDecimal(1));
		weekVO.setBuyBase(100);
		weekVO.setSingleMin(100);
		mv.addObject("week", weekVO);
	}

	/**
	 * 新增或编辑计划
	 * 
	 * @param weekVO
	 * @return Json
	 * @author louchen 2014-12-3
	 */
	@RequestMapping(value = "/addOrEditWeek")
	@ResponseBody
	public Json addOrEditWeek(Week week,String saleTime,String expireTime,WeekRate weekRate) {	
		week.setSaleTime(DateUtil.getDateToString(saleTime,"yyyy-MM-dd HH:mm:ss"));
		week.setExpireTime(DateUtil.getDateToString(expireTime,"yyyy-MM-dd HH:mm:ss"));
		return this.weekService.saveWeek(week,weekRate);
	}

	/**
	 * 删除计划
	 * 
	 * @param weekVO
	 * @return
	 * @author louchen 2014-12-3
	 */
	@RequestMapping(value = "/deleteWeek")
	@ResponseBody
	public Json deleteWeek(WeekVO weekVO) {
		Json json = new Json();
		return json;
	}

	/**
	 * 跳转到标的列表页面
	 * 
	 * @param id
	 * @return ModelAndView
	 * @author louchen 2014-12-4
	 */
	@RequestMapping(value = "/weekBorrowListPage")
	@ResponseBody
	public ModelAndView weekBorrowListPage(
			@RequestParam(value = "weekid", required = false) String weekid) {
		if (EmptyUtil.isEmpty(weekid)) {
			throw new BusinessException("8005012", "weekid为空，错误的请求");
		}
		ModelAndView mv = new ModelAndView("webpage/week/weekBorrowList");
		// 新建权限
		boolean canEdit = UserAuthFilter
				.isPass("/system/week/addOrEditWeekBorrow");
		mv.addObject("canEdit", canEdit);
		// 删除权限
		boolean canDelete = UserAuthFilter
				.isPass("/system/week/deleteWeekBorrow");
		mv.addObject("canDelete", canDelete);
		// weekid
		mv.addObject("weekId", weekid);
		// weekname
		Week week = this.weekService.selectByPrimaryKey(new Integer(weekid));
		mv.addObject("weekName", week.getName());
		return mv;
	}

	/**
	 * 标的列表
	 * 
	 * @param pageSearch
	 * @param searchVO
	 * @return
	 * @throws Exception
	 * @author louchen
	 */
	@ParentSecurity("/system/week/weekBorrowListPage")
	@RequestMapping(value = "/weekBorrowList")
	@ResponseBody
	public PageResult<WeekBorrow> weekBorrowList(PageSearch pageSearch,
			SearchWeekBorrowVO searchVO) throws Exception {
		PageResult<WeekBorrow> results = new PageResult<WeekBorrow>();
		pageSearch.setEntity(searchVO);
		List<WeekBorrow> weekList = this.weekBorrowService
				.selectWeekBorrowByWeekIdListPage(pageSearch);
		results.setRows(weekList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	/**
	 * 跳转到标的新增或编辑页面
	 * @param id
	 * @return
	 * @author louchen 2014-12-4
	 */
	@RequestMapping(value = "/addOrEditWeekBorrowPage")
	@ResponseBody
	public ModelAndView addOrEditWeekBorrowPage(Integer id,@RequestParam(value = "weekId", required = false) Integer weekid
			,HttpSession httpSession) {
		if (EmptyUtil.isEmpty(weekid)) {
			throw new BusinessException("8005012", "weekid为空，错误的请求");
		}
		ModelAndView mv = new ModelAndView("webpage/week/addOrEditWeekBorrow");
		if (EmptyUtil.isEmpty(id)) {
			editDefaultWeekBorrowPage(mv);
		} else {
			editModifyWeekBorrowPage(mv, id,httpSession);
		}
		mv.addObject("weekId", weekid);
		return mv;
	}
	
	/**
	 * 标的编辑页面
	 * @param mv
	 * @param id
	 */
	private void editModifyWeekBorrowPage(ModelAndView mv, Integer id,HttpSession httpSession) {
		WeekBorrow weekBorrow = this.weekBorrowService.selectByPrimaryKey(id);
		mv.addObject("weekBorrow", weekBorrow);
		mv.addObject("formatContractStart", DateUtil.getDateString(weekBorrow.getContractStart(), "yyyy-MM-dd"));
		mv.addObject("formatContractEnd", DateUtil.getDateString(weekBorrow.getContractEnd(), "yyyy-MM-dd"));
		WeekBorrowPawn weekBorrowPawn = this.weekBorrowPawnService.selectByBorrowId(weekBorrow.getId());
		mv.addObject("weekBorrowPawn",weekBorrowPawn);
		if(EmptyUtil.isNotEmpty(weekBorrowPawn) && weekBorrowPawn.getId()>0){
			//jsp图片地址
			WeekBorrowPawnPic wbpp = new WeekBorrowPawnPic();
			wbpp.setPawnId(weekBorrowPawn.getId());
			wbpp.setType("0");
			List<WeekBorrowPawnPic> cardpic1 = this.weekBorrowPawnPicService.selectPicListByEntity(wbpp);
			wbpp.setType("1");
			List<WeekBorrowPawnPic> cardpic2 = this.weekBorrowPawnPicService.selectPicListByEntity(wbpp);
			wbpp.setType("2");
			List<WeekBorrowPawnPic> carpic = this.weekBorrowPawnPicService.selectPicListByEntity(wbpp);
			wbpp.setType("3");
			List<WeekBorrowPawnPic> carcardpic = this.weekBorrowPawnPicService.selectPicListByEntity(wbpp);
			wbpp.setType("4");
			List<WeekBorrowPawnPic> otherpic = this.weekBorrowPawnPicService.selectPicListByEntity(wbpp);
			mv.addObject("cardpic1",cardpic1);
			mv.addObject("cardpic2",cardpic2);
			mv.addObject("carpic",carpic);
			mv.addObject("carcardpic",carcardpic);
			mv.addObject("otherpic",otherpic);
			//存图片id到session
			this.removePicSession(httpSession);
			this.savePicSession(httpSession, carpic,"carpic_old");
			this.savePicSession(httpSession, carcardpic,"carcardpic_old");
			this.savePicSession(httpSession, otherpic,"otherpic_old");					
		}
	}
	
	/**
	 * 存储图片session
	 * @param httpSession
	 * @param pics
	 * @param attribute
	 * @author louchen 2014-12-8
	 */
	private void savePicSession(HttpSession httpSession
			,List<WeekBorrowPawnPic> pics
			,String attribute) {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(WeekBorrowPawnPic pic:pics){
			ids.add(pic.getId());
		}
		httpSession.setAttribute(attribute, ids);
	}
	
	/**
	 * 标的新增页面默认值
	 * @param mv
	 */
	private void editDefaultWeekBorrowPage(ModelAndView mv) {
		WeekBorrow weekBorrow = new WeekBorrow();
		weekBorrow.setTimeLimit(7);
		mv.addObject("weekBorrow",weekBorrow);
	}
	
	/**
	 * 新增或编辑标的
	 * 
	 * @param weekVO
	 * @return Json
	 * @author louchen 2014-12-8
	 */
	@RequestMapping(value = "/addOrEditWeekBorrow")
	@ResponseBody
	public Json addOrEditWeekBorrow(@RequestParam(value = "weekId", required = false) Integer weekId
			,Integer weekBorrowId,Integer weekBorrowPawnId
			,WeekBorrow weekBorrow,WeekBorrowPawn weekBorrowPawn
			,HttpSession httpSession) {
		Json json = new Json();
		if (EmptyUtil.isEmpty(weekId)) {
			json.setMsg("weekid为空，错误的请求");
			return json;
		}		
		weekBorrow.setId(weekBorrowId);
		weekBorrowPawn.setId(weekBorrowPawnId);
		this.weekBorrowService.saveWeekBorrow(weekBorrow);
		this.weekBorrowPawnService.saveWeekBorrowPawn(weekBorrow, weekBorrowPawn);	
		this.weekBorrowPawnPicService.saveWeekBorrowPawnPic(httpSession, weekBorrowPawn);
		this.removePicSession(httpSession);
		json.setSuccess(true);
		return json;
	}
	
	/**
	 * 清除图片session
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value="/removePicSession")
	@ResponseBody
	public Json removePicSession(HttpSession httpSession) {
		//清除图片session
		Json json = new Json();
		httpSession.removeAttribute("card_pic1");
		httpSession.removeAttribute("card_pic2");
		httpSession.removeAttribute("car_pic1");
		httpSession.removeAttribute("car_pic2");
		httpSession.removeAttribute("car_pic3");
		httpSession.removeAttribute("car_pic4");
		httpSession.removeAttribute("carcard_pic1");
		httpSession.removeAttribute("carcard_pic2");
		httpSession.removeAttribute("carcard_pic3");
		httpSession.removeAttribute("other_pic1");
		httpSession.removeAttribute("carpic_old");
		httpSession.removeAttribute("carcardpic_old");
		httpSession.removeAttribute("otherpic_old");
		json.setSuccess(true);
		return json;
	}

	/**
	 * 短期理财计划发标上传图片
	 * 
	 * @param multipartRequest
	 * @param attestation
	 * @return
	 * @throws Exception
	 * @author louchen 2014-12-8
	 */
	@RequestMapping(value = "/weekBorrowUploadAttestation", method = RequestMethod.POST)
	@ResponseBody
	public Json weekBorrowUploadAttestation(
			MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, String width, String height, String name)
			throws Exception {
		Json j = new Json();
		MultipartFile multipartFile = multipartRequest.getFile("files");
		if (EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize() > 0) {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil
					.createMultipartEntityBuilderByMultipartFile(multipartFile);
			MessageVO vo = this.attestationService.WeekborrowUploadAttestation(
					multipartEntity);
			multipartRequest.getSession().setAttribute(name, vo.getId());
			j.setSuccess(true);
		}
		return j;
	}
	
	/**
	 * 跳转到审核计划列表
	 * @return
	 * @author louchen 2014-12-9
	 */
	@RequestMapping(value = "/trialWeekListPage")
	@ResponseBody
	public ModelAndView trialWeekListPage(){
		ModelAndView mv = new ModelAndView("webpage/week/trialWeekList");
		// 审核权限
		boolean canTrial = UserAuthFilter.isPass("/system/week/trialWeek");
		if (canTrial) {
			mv.addObject("canTrial", canTrial);
		}
		return mv;
	}
	
	/**
	 * 审核计划列表
	 * @param pageSearch
	 * @param searchVO
	 * @return
	 * @throws Exception
	 * @author louchen 2014-12-9
	 */
	@ParentSecurity("/system/week/trialWeekListPage")
	@RequestMapping(value = "/trailWeekList")
	@ResponseBody
	public PageResult<WeekVO> trailWeekList(PageSearch pageSearch,
			SearchWeekVO searchVO) throws Exception {
		PageResult<WeekVO> results = new PageResult<WeekVO>();
		// 时间转换
		if (EmptyUtil.isNotEmpty(searchVO.getSaleTime())) {
			searchVO.setSaleTime(searchVO.getSaleTime() + " 00:00:00");
		}
		if (EmptyUtil.isNotEmpty(searchVO.getExpireTime())) {
			searchVO.setExpireTime(searchVO.getExpireTime() + " 23:59:59");
		}
		pageSearch.setEntity(searchVO);
		List<WeekVO> weekList = this.weekService.selectWeekVOListPage(pageSearch);
		results.setRows(weekList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	/**
	 * 还款计划列表
	 * @return
	 * @author louchen 2014-12-9
	 */
	@RequestMapping(value = "/repaymentWeekListPage")
	@ResponseBody
	public ModelAndView repaymentWeekListPage(){
		ModelAndView mv = new ModelAndView("webpage/week/repaymentWeekList");
		//还款权限
		boolean canRepay = UserAuthFilter.isPass("/system/week/repaymentWeek");
		if (canRepay) {
			mv.addObject("canRepay", canRepay);
		}
		//查询今日，明日和未来七天还款总额
		String today = DateUtil.parseDate(new Date());
		String tomorrow = DateUtil.parseDate(DateUtil.getAfterDateAsDate(new Date(), 1));
		String future = DateUtil.parseDate(DateUtil.getAfterDateAsDate(new Date(), 7));
		mv.addObject("today", today);
		mv.addObject("tomorrow", tomorrow);
		mv.addObject("future", future);
		this.setWeekRepaymentVO(mv,"todayWeekRepayment",today,today);
		this.setWeekRepaymentVO(mv,"tomorrowWeekRepayment",tomorrow,tomorrow);
		this.setWeekRepaymentVO(mv,"futureWeekRepayment",tomorrow,future);	
		//绑定的前台账号
		WeekUserVO weekUser = this.weekUserService.selectWeekUserByEmpId(EmployeeSession.getEmpSessionEmpId());
		if(EmptyUtil.isNotEmpty(weekUser.getWeekUser()) && weekUser.getWeekUser().getId()>0){
			if(EmptyUtil.isNotEmpty(weekUser.getUser()) && weekUser.getUser().getUserId()>0){
				mv.addObject("user", weekUser.getUser());
			}else{
				mv.addObject("userMsg","无前台账号.!");
			}
			if(EmptyUtil.isNotEmpty(weekUser.getAccount()) && weekUser.getAccount().getId()>0){
				mv.addObject("account", weekUser.getAccount());
			}else{
				mv.addObject("accountMsg", "无前台账号.!");
			}
		}else{
			mv.addObject("userMsg","前后台账号未绑定.!");
			mv.addObject("accountMsg", "前后台账号未绑定.!");
		}
		return mv;
	}
	
	/**
	 * 还款计划列表
	 * @param pageSearch
	 * @param searchVO
	 * @return
	 * @throws Exception
	 */
	@ParentSecurity("/system/week/repaymentWeekListPage")
	@RequestMapping(value = "/repaymentWeekList")
	@ResponseBody
	public PageResult<WeekRepaymentVO> repaymentWeekList(PageSearch pageSearch,
			SearchWeekRepaymentVO searchVO) throws Exception {
		PageResult<WeekRepaymentVO> results = new PageResult<WeekRepaymentVO>();
		pageSearch.setEntity(searchVO);
		List<WeekRepaymentVO> weekRepaymentList = this.weekRepaymentService.selectWeekRepaymentGroupByWeekIdListPage(pageSearch);
		results.setRows(weekRepaymentList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	/**
	 * 根据参数获取显示VO
	 * @param mv
	 * @param attrName
	 * @param start
	 * @param end
	 * @author louchen 2014-12-9
	 */
	private void setWeekRepaymentVO(ModelAndView mv,String attrName,
			String start,String end) {
		SearchWeekRepaymentVO search = new SearchWeekRepaymentVO();
		search.setStatus(new Integer(0));
		search.setStartTime(start);
		search.setEndTime(end);
		WeekRepaymentVO wrVO = this.weekRepaymentService.selectSumWeekRepayment(search);
		mv.addObject(attrName,wrVO);
	}
	
	/**
	 * 还款
	 * @param repaymentId
	 * @param repaymentTime
	 * @return
	 * @author louchen 2014-12-10
	 */
	@RequestMapping(value="/repayment",method=RequestMethod.POST)
	@ResponseBody
	public Json repayment(@RequestParam(value="repaymentId",required = true) Integer repaymentId
			,@RequestParam(value="repaymentTime",required = true) String repaymentTime
			,@RequestParam(value="repaymentUser",required = false) Integer repaymentUser
			,HttpServletRequest request){
		Json json = new Json();
		//代付账户发送站内信
		if(EmptyUtil.isNotEmpty(repaymentUser)){
			return this.insertRepaymentMessage(repaymentId,repaymentUser);	
		}
		//当前绑定用户直接付款
		else{
			WeekUserVO weekUser = this.weekUserService.selectWeekUserByEmpId(EmployeeSession.getEmpSessionEmpId());
			if(EmptyUtil.isNotEmpty(weekUser.getWeekUser()) && weekUser.getWeekUser().getId()>0){
				if(EmptyUtil.isNotEmpty(weekUser.getUser()) && weekUser.getUser().getUserId()>0){
					UserSession user = new UserSession();
					user.setUserId(weekUser.getUser().getUserId());
					user.setAddress(request.getRemoteAddr());
					try{
						return this.weekRepaymentService.updateRepayWeek(repaymentId,user,request.getRemoteAddr());
					}catch(Exception e){
						e.printStackTrace();
					}				
				}else{
					json.setMsg("无前台账号.!");
				}
			}else{
				json.setMsg("前后台账号未绑定.!");
			}			
		}
		return json;
	}
	
	/**
	 * 发送还款站内信
	 * @param repaymentId
	 * @param receiveUser
	 * @return
	 * @author louchen 2014-12-16
	 */
	private Json insertRepaymentMessage(Integer repaymentId,Integer receiveUser){
		Json json = new Json();
		WeekRepayment weekRepayment = this.weekRepaymentService.selectByPrimaryKey(repaymentId);
		if(EmptyUtil.isNotEmpty(weekRepayment) && weekRepayment.getId()>0 
				&& EmptyUtil.isNotEmpty(receiveUser) && receiveUser>0 ){
			Week week = this.weekService.selectByPrimaryKey(weekRepayment.getWeekId());
			Message message = new Message();
			message.setSentUser(0);
			message.setReceiveUser(receiveUser);
			message.setName("周盈宝代还款");
			message.setContent(week.getName()+"--"+weekRepayment.getRepaymentAccount()
					+"元--"+DateUtil.getDateString(weekRepayment.getRepaymentTime(), "yyyy-MM-dd HH:mm:ss")
					+",<a href='javascript:repayWeekRepayment(\""+repaymentId+"\")' style='color:blue;'>请代付</a>");
			message.setStatus(0);
			message.setType("system");
			message.setSented(null);
			message.setDeltype(0);
			message.setAddtime(DateUtil.getTime());
			message.setAddip("127.0.0.1");
			this.messageMapper.insert(message);
			json.setMsg("他人账户代付，已发送站内信.");		
			json.setSuccess(true);
		}else{
			json.setMsg("repaymentId信息有误.");		
		}
		return json;
	}
	
	/**
	 * 还款详情
	 * @param repaymentId
	 * @param repaymentTime
	 * @return
	 * @author louchen 2014-12-15
	 */
	@RequestMapping(value="/repaymentDetail",method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView repaymentDetail(@RequestParam(value="repaymentId",required = true) Integer repaymentId
			,@RequestParam(value="repaymentTime",required = true) String repaymentTime){
		ModelAndView mv = new ModelAndView("webpage/week/repaymentDetail");
		//绑定的前台账号可用余额
		WeekUserVO weekUser = this.weekUserService.selectWeekUserByEmpId(EmployeeSession.getEmpSessionEmpId());
		if(EmptyUtil.isNotEmpty(weekUser.getWeekUser()) && weekUser.getWeekUser().getId()>0){
			if(EmptyUtil.isNotEmpty(weekUser.getUser()) && weekUser.getUser().getUserId()>0){
				mv.addObject("user", weekUser.getUser());
			}else{
				mv.addObject("userMsg","无前台账号.!");
			}
			if(EmptyUtil.isNotEmpty(weekUser.getAccount()) && weekUser.getAccount().getId()>0){
				mv.addObject("account", weekUser.getAccount());
			}else{
				mv.addObject("accountMsg", "无前台账号.!");
			}
		}else{
			mv.addObject("userMsg","前后台账号未绑定.!");
			mv.addObject("accountMsg", "前后台账号未绑定.!");
		}
		WeekRepayment weekRepayment = this.weekRepaymentService.selectByPrimaryKey(repaymentId);
		Week week = this.weekService.selectByPrimaryKey(weekRepayment.getWeekId());
		mv.addObject("week",week);
		mv.addObject("weekRepayment",weekRepayment);
		return mv;
	}
	
	
	
	/**
	 * 跳转到投资人列表
	 * @return
	 * @author louchen 2014-12-10
	 */
	@RequestMapping(value = "/weekTenderListPage")
	@ResponseBody
	public ModelAndView weekTenderListPage(SearchWeekTenderVO searchVO) {
		ModelAndView mv = new ModelAndView("webpage/week/weekTenderList");
		mv.addObject("weekId",searchVO.getWeekId());
		mv.addObject("repaymentTime",searchVO.getRepaymentTime());
		return mv;
	}
	
	/**
	 * 投资人列表
	 * @param pageSearch
	 * @param searchVO
	 * @return
	 * @throws Exception
	 * @author louchen 2014-12-10
	 */
	@ParentSecurity("/system/week/weekTenderListPage")
	@RequestMapping(value = "/weekTenderList")
	@ResponseBody
	public PageResult<WeekTenderVO> weekTenderList(PageSearch pageSearch,
			SearchWeekTenderVO searchVO) throws Exception {
		PageResult<WeekTenderVO> results = new PageResult<WeekTenderVO>();
		pageSearch.setEntity(searchVO);
		List<WeekTenderVO> weekList = this.weekTenderService.selectWeekTenderByWeekIdAndRepaymentTimeListPage(pageSearch);
		results.setRows(weekList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}	
	
	/**
	 * 预览详情
	 * 
	 * @param id
	 * @return
	 */
	@ParentSecurity("/system/week/weekList")
	@RequestMapping(value = "/weekDetail")
	@ResponseBody
	public ModelAndView weekDetail(@RequestParam("id") Integer id,String type) {
		if (EmptyUtil.isEmpty(id)) {
			throw new BusinessException("8005012", "weekid为空，错误的请求");
		}
		ModelAndView mv = null;
		//根据请求Type类型确定加载数据后跳转的页面
		if(type.equals("perview")){
			mv = new ModelAndView("webpage/week/previewWeekDetail");
		}else if(type.equals("trial")){
			mv = new ModelAndView("webpage/week/trialWeekDetail");
		}
		Week week = weekService.selectByWeekPreview(id);
		mv.addObject("week", week);
		return mv;
	}

	/**
	 * 提交审核，改变status 与 save_status状态
	 */
	@RequestMapping(value = "/weekSubTrial")
	@ResponseBody
	public Json weekSubTrial(Week week) {
		Json j = new Json();
		int i = weekService.weekSubTrial(week);
		if (i > 0) {
			j.setSuccess(true);
			j.setMsg("操作成功.");
		} else {
			j.setSuccess(false);
			j.setMsg("系统异常,审核失败.");
		}
		return j;
	}

	/**
	 * 保存审核信息，更改week 与 borrow 状态 saveStatus
	 * 
	 * @param week
	 * @param wbs
	 * @param trialStatus
	 *            "save" 保存 "back"打回 "ok" 审核成功
	 * @return
	 */
	@ParentSecurity("/system/week/previewWeekDetail")
	@RequestMapping(value = "/trialWeek")
	@ResponseBody
	public Json trialWeek(Week week, TrialBorrowVO tbv, String trialStatus) {
		Json j = new Json();
		week.setVerifyUser(EmployeeSession.getEmpSessionEmpId());
		week.setVerifyTime(new Date());
		//根据提交状态，ok 是审核通过，将活动的 status 与 saveStatus 字段设置为相同
		if (trialStatus.equals("ok")) { 
			week.setStatus(week.getSaveStatus());
			week.setVerifyRemark("");
			week.setPublishTime(new Date());
		} else if (trialStatus.equals("back")) { //back 为打回审核 将 status 与 saveStatus设置为2 审核失败
			week.setStatus(2);
			week.setSaveStatus(2);
		}
		//为save时，直接更新saveStatus状态即可
		j = weekService.updateWeekStatus(week, tbv, trialStatus);
		return j;
	}
	
	/**
	 * 跳转到还款记录列表
	 * 
	 * @return ModelAndView
	 * @author jianwei
	 */
	@RequestMapping(value = "/repaymentRecordListPage")
	@ResponseBody
	public ModelAndView RepaymentRecordListPage() {
		ModelAndView mv = new ModelAndView("webpage/week/repaymentRecordList");
		return mv;
	}
	
	/**
	 * 还款记录列表
	 * 
	 * @param pageSearch
	 * @param search
	 * @return
	 * @throws Exception
	 */
	@ParentSecurity("/system/week/repaymentRecordListPage")
	@RequestMapping(value = "/repaymentRecordList")
	@ResponseBody
	public PageResult<WeekRepayment> RepaymentRecordList(PageSearch pageSearch,
			SearchRepaymentRecordVO searchRepaymentRecordVO,SearchWeekVO searchWeekVO) throws Exception {
		PageResult<WeekRepayment> results = new PageResult<WeekRepayment>();
		if(!StringUtils.isEmpty(searchWeekVO.getName())){
			//根据名称模糊查询出 计划
			pageSearch.setEntity(searchWeekVO);
			List<Week> wList = weekService.selectWeekListPage(pageSearch);
			//将计划ID提取，设置到查询条件中
			searchRepaymentRecordVO.setWeekIds(wList);
		}
		//状态是已还款
		searchRepaymentRecordVO.setStatus(1);
		pageSearch.setEntity(searchRepaymentRecordVO);
		List<WeekRepayment> weekRepayments = this.weekRepaymentService.selectWeekRepaymentListPage(pageSearch);
		results.setRows(weekRepayments);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	

}
