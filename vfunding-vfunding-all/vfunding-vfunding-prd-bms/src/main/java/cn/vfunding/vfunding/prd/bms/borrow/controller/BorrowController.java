package cn.vfunding.vfunding.prd.bms.borrow.controller;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.MultipartEntityBuilderUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRule;
import cn.vfunding.vfunding.biz.borrow.model.BorrowTender;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCarPic;
import cn.vfunding.vfunding.biz.borrow.model.MortgageType;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRuleService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.borrow.service.IMortgageCarService;
import cn.vfunding.vfunding.biz.borrowMobile.service.IBorrowTenderMobileService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.BorrowVO;
import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.biz.common.vo.TheTrialBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.VerifyBorrowVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.model.Fee;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
import cn.vfunding.vfunding.biz.system.service.IAreaService;
import cn.vfunding.vfunding.biz.system.service.IAttestationService;
import cn.vfunding.vfunding.biz.system.service.IFeeService;
import cn.vfunding.vfunding.biz.system.service.IInvestorsFeeService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserInfoService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

@Controller
@RequestMapping(value = { "/system/borrow", "borrow" })
public class BorrowController extends BaseController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IBorrowTenderService borrowTenderService;
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;
	@Autowired
	private IAreaService areaService;
	@Autowired
	private IBorrowService borrowService;

//	@Resource(name = "jms.sender")
//	private JmsSender jmsSender;

	@Autowired
	private IAccountFeelService accountFeelService;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private IBorrowTenderMobileService btmService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IMortgageCarService mortgageCarService;
	@Autowired
	private IInvestorsFeeService investorsFeeService;
	@Autowired
	private IFeeService feeService;
	@Autowired
	private IBorrowRuleService borrowRuleService;
	/**
	 * 转向发标初审页面
	 * 
	 * @return 2014年6月13日 liuyijun
	 */
	@RequestMapping(value = "/theTrialBorrowListPage")
	public ModelAndView theTrialBorrowListPage() {
		ModelAndView mv = new ModelAndView("webpage/borrow/theTrialBorrowList");
		boolean canEdit = UserAuthFilter.isPass("/system/borrow/theTrialBorrow");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		return mv;
	}

	/**
	 * 转向复审页面
	 * 
	 * @return 2014年6月13日 liuyijun
	 */
	@RequestMapping(value = "/finalBorrowListPage")
	public ModelAndView finalBorrowListPage() {
		ModelAndView mv = new ModelAndView("webpage/borrow/finalBorrowList");
		boolean canEdit = UserAuthFilter.isPass("/system/borrow/finalBorrow");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		return mv;
	}

	/**
	 * 转向额度审批页面
	 * 
	 * @return 2014年6月13日 liuyijun
	 */
	@RequestMapping(value = "/userAmountListPage")
	public ModelAndView userAmountListPage() {
		ModelAndView mv = new ModelAndView("webpage/borrow/userAmountList");
		boolean canEdit = UserAuthFilter.isPass("/system/amount/applyAmount");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		return mv;
	}

	/**
	 * 复审列表
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity("/system/borrow/finalBorrowListPage")
	@RequestMapping("/finalBorrowList")
	@ResponseBody
	public PageResult<VerifyBorrowVO> finalBorrowList(PageSearch pageSearch, SearchVO search) {
		PageResult<VerifyBorrowVO> results = new PageResult<VerifyBorrowVO>();

		pageSearch.setEntity(search);

		List<VerifyBorrowVO> finalBorrows = borrowService.selectFinalBorrowListPage(pageSearch);
		results.setRows(finalBorrows);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * 初审列表
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年6月5日
	 */
	@ParentSecurity("/system/borrow/theTrialBorrowListPage")
	@RequestMapping("/theTrialBorrowList")
	@ResponseBody
	public PageResult<TheTrialBorrowVO> TheTrialBorrowList(PageSearch pageSearch, SearchVO search) {
		PageResult<TheTrialBorrowVO> results = new PageResult<TheTrialBorrowVO>();
		pageSearch.setEntity(search);
		List<TheTrialBorrowVO> finalBorrows = borrowService.selectTheTrialListPage(pageSearch);
		results.setRows(finalBorrows);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * 复审详情
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity("/system/borrow/finalBorrowListPage")
	@RequestMapping("/borrowDetail")
	@ResponseBody
	public ModelAndView borrowDetail(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView("webpage/borrow/borrowDetail");
		// 查询标的相关图片
		MortgageCarPic pic = new MortgageCarPic();
		pic.setId(id);
		List<MortgageCarPic> listPic = this.borrowService.selectBorrowPic(pic);
		mv.addObject("listPic", listPic);

		// 借款基本详情
		Borrow borrow = this.borrowService.selectBorrowById(id);
		mv.addObject("borrow", borrow);
		// borrow详细信息
		Borrow borrowAll = this.borrowService.selectById(id);
		mv.addObject("borrowAll", borrowAll);

		// 担保服务费
		InvestorsFee fee = investorsFeeService.selectByBorrowId(borrowAll.getId());
		mv.addObject("fee", fee);

		// 此标的投资次数
		Integer tenderCount = this.btmService.selectTenderBorrowByIdCount(id);
		mv.addObject("tenderCount", tenderCount);
		// 发标人的个人信息
		Integer borrowUserId = borrowAll.getUserId();// 发标人的userID
		User user = this.userService.selectByPrimaryKey(borrowUserId);
		mv.addObject("user", user);

		// 车主信息
		MortgageCar mortgageCar = mortgageCarService.selectByPrimaryKey(borrowAll.getMortgageId());
		mv.addObject("mortgageCar", mortgageCar);

		// 根据borrowID查询投资详细
		List<BorrowTender> tenderList = this.borrowTenderService.selectTenderByBorrowId(id);
		mv.addObject("tenderList", tenderList);
		return mv;
	}

	/**
	 * 复审
	 * 
	 * @author liuhuan
	 */
	@NeedLogger(desc = "复审")
	@RequestMapping("/finalBorrow")
	@ResponseBody
	public Json finalBorrow(HttpServletRequest request, FinalVerifyVO finalVerify) {
		Json j = new Json();
		String result = "";
		if (finalVerify.getBorrowId() == null) {
			result = "标数据不正确.";
		} else if (EmptyUtil.isEmpty(finalVerify.getRemark())) {
			result = "审核备注不能为空.";
		} else if (finalVerify.getStatus() == null) {
			result = "审核状态不能为空.";
		} else {
			finalVerify.setAdminUserId(EmployeeSession.getEmpSessionEmpId());
			finalVerify.setIp(request.getRemoteAddr());
			//判断是否天标
			Borrow borrow = borrowService.selectById(finalVerify.getBorrowId());
			if(borrow.getIsday() == 1){
				result = borrowService.updateFinalVerifyDayBorrow(finalVerify);
			} else {
				result = borrowService.updateFinalVerifyBorrow(finalVerify);
			}
		}
		j.setMsg(result);
		return j;
	}

	/**
	 * 初审详情
	 * 
	 * @param id
	 * @return author LiLei 2014年6月6日
	 */
	@ParentSecurity("/system/borrow/theTrialBorrowListPage")
	@RequestMapping("/theTrialBorrowDetail")
	@ResponseBody
	public ModelAndView theTrialBorrowDetail(@RequestParam("id") Integer id) {
		ModelAndView mv = new ModelAndView("webpage/borrow/theTrialBorrowDetail");
		// 查询标的相关图片
		MortgageCarPic pic = new MortgageCarPic();
		pic.setId(id);
		List<MortgageCarPic> listPic = this.borrowService.selectBorrowPic(pic);
		mv.addObject("listPic", listPic);

		// 借款基本详情
		Borrow borrow = this.borrowService.selectBorrowById(id);
		mv.addObject("borrow", borrow);
		// borrow详细信息
		Borrow borrowAll = this.borrowService.selectById(id);
		mv.addObject("borrowAll", borrowAll);

		// 发标人的个人信息
		Integer borrowUserId = borrowAll.getUserId();// 发标人的userID
		User user = this.userService.selectByPrimaryKey(borrowUserId);
		mv.addObject("user", user);

		// 车主信息
		MortgageCar mortgageCar = mortgageCarService.selectByPrimaryKey(borrowAll.getMortgageId());
		mv.addObject("mortgageCar", mortgageCar);

		// 费率信息
		Fee fee = feeService.selectByTimeLimit(borrow.getTimeLimit().intValue());
		mv.addObject("fee", fee);
		return mv;
	}

	/**
	 * 进行初审
	 * 
	 * @param request
	 * @param borrow
	 * @param investorsFee
	 * @return author LiLei 2014年6月6日
	 */
	@NeedLogger(desc = "初审")
	@RequestMapping("/theTrialBorrow")
	@ResponseBody
	public Json theTrialBorrow(HttpServletRequest request, Borrow borrow, InvestorsFee investorsFee, MortgageCar car,BorrowRule borrowRule) {
		Json j = new Json();
		Borrow b = borrowService.selectById(borrow.getId());
		if (b.getStatus() != 0 && b.getAccount().compareTo(b.getAccountYes()) != 0) {
			j.setMsg("该标不能审核.");
		} else {
			// 活动标判断
			if(EmptyUtil.isNotEmpty(borrow.getBiaoType()) && "huodong".equals(borrow.getBiaoType())){
				b.setIsFast((byte) 0);
				b.setBiaoType(borrow.getBiaoType());
			}
			b.setStatus(borrow.getStatus());
			b.setVerifyRemark(borrow.getVerifyRemark());
			int i = borrowService.updateVerifyBorrow(b, EmployeeSession.getEmpSessionEmpId(), investorsFee, car, request.getRemoteAddr());
			if (i > 0) {
				borrowRule.setAddtime(new Date());
				if(b.getStatus() == 1){
					borrowRuleService.insertSelective(borrowRule);
				}
				j.setSuccess(true);
				j.setMsg("操作成功.");
			} else {
				j.setSuccess(false);
				j.setMsg("系统异常,审核失败.");
			}
		}
		return j;
	}

	/**
	 * 预初审 (主要保存:汽车相关信息、备注等,可以为空)
	 * @return
	 * @author liuhuan
	 */
	@NeedLogger(desc = "预初审")
	@RequestMapping("/saveBorrowForm")
	@ResponseBody
	public Json saveBorrowForm(Borrow borrow,MortgageCar car){
		Json j = new Json();
		int i = borrowService.updateSaveBorrowForm(borrow, car);
		if(i>0){
			j.setSuccess(true);
		} else {
			j.setMsg("操作失败，系统异常");
		}
		return j;
	}
	
	/**
	 * 转向撤标页面
	 * 
	 * @return 2014年6月13日 liuyijun
	 */
	@RequestMapping(value = "/failBorrowListPage")
	public ModelAndView failBorrowListPage() {
		ModelAndView mv = new ModelAndView("webpage/borrow/failBorrowList");
		boolean canEdit = UserAuthFilter.isPass("/system/borrow/failBorrow");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		return mv;
	}

	/**
	 * 待撤标列表
	 * 
	 * @return
	 * @author liuhuan
	 */
	@ParentSecurity("/system/borrow/failBorrowListPage")
	@RequestMapping("/failBorrowList")
	@ResponseBody
	public PageResult<VerifyBorrowVO> failBorrowList(PageSearch pageSearch, SearchVO search) {
		PageResult<VerifyBorrowVO> results = new PageResult<VerifyBorrowVO>();
		pageSearch.setEntity(search);
		List<VerifyBorrowVO> failBorrows = borrowService.failBorrowListPage(pageSearch);
		results.setRows(failBorrows);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * @Description:普通标--撤标处理(可撤：投标中的标)
	 * @param id
	 * @return
	 * @author liuhuan
	 */
	@NeedLogger(desc = "撤标")
	@RequestMapping("/failBorrow")
	@ResponseBody
	public Json failBorrow(HttpServletRequest request, @RequestParam("id") int id) {
		Json j = new Json();
		String msg = "";
		if (id == 0) {
			msg = "标数据异常，撤标失败";
		} else {
			Borrow borrow = borrowService.selectById(id);
			msg = borrowService.updateFailBorrow(borrow, 5, request.getRemoteAddr());
		}
		j.setMsg(msg);
		return j;
	}

	/************************** 贷款管理 ***************************/

	/**
	 * 贷款管理
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/allLoans")
	public ModelAndView borrowSystem() {
		ModelAndView mv = new ModelAndView("webpage/borrow/allBorrowList");
		return mv;
	}

	/**
	 * 后台借款管理列表
	 * 
	 * @author liuhuan
	 * @throws Exception
	 */
	@ParentSecurity("/system/borrow/allLoans")
	@RequestMapping("/allBorrowSystemList")
	@ResponseBody
	public PageResult<VerifyBorrowVO> allBorrowSystemList(PageSearch pageSearch, SearchVO search) throws Exception {
		PageResult<VerifyBorrowVO> results = new PageResult<VerifyBorrowVO>();
		search.setStatus(search.getStatus() == null ? 0 : search.getStatus());
		// 时间转换
		ModelAndViewUtil.addSearchTimeVO(search);
		pageSearch.setEntity(search);
		List<VerifyBorrowVO> failBorrows = borrowService.selectBorrowSystemListPage(pageSearch);
		results.setRows(failBorrows);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/************************** 还款管理 ***************************/
	/**
	 * 还款管理
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/repayment/allRepays")
	public ModelAndView repaymentSystem() {
		ModelAndView mv = new ModelAndView("webpage/borrow/allRepaymentList");
		return mv;
	}

	/**
	 * 后台借款管理列表
	 * 
	 * @author liuhuan
	 * @throws Exception
	 */
	@ParentSecurity(value = { "/system/borrow/repayment/allRepays" })
	@RequestMapping("/allRepaymentSystemList")
	@ResponseBody
	public PageResult<BorrowRepayment> allRepaymentSystemList(PageSearch pageSearch, SearchVO search) throws Exception {
		PageResult<BorrowRepayment> results = new PageResult<BorrowRepayment>();
		search.setStatus(search.getStatus() == null ? null : search.getStatus());
		// 时间转换
		ModelAndViewUtil.addSearchTimeVO(search);
		pageSearch.setEntity(search);
		List<BorrowRepayment> failBorrows = borrowRepaymentService.selectByRepaymentSystemListPage(pageSearch);
		results.setRows(failBorrows);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * 已还借款 页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/repayment/yesRepays")
	public ModelAndView repaymentYesSystem() {
		ModelAndView mv = new ModelAndView("webpage/borrow/repaymentYesNoList");
		mv.addObject("status", 1);
		return mv;
	}

	/**
	 * 未还借款 页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/repayment/noRepays")
	public ModelAndView repaymentNoSystem() {
		ModelAndView mv = new ModelAndView("webpage/borrow/repaymentYesNoList");
		mv.addObject("status", 0);
		return mv;
	}

	/**
	 * 后台借款(已还、未还)管理列表
	 * 
	 * @author liuhuan
	 * @throws Exception
	 */
	@ParentSecurity(value = { "/system/borrow/repayment/yesRepays", "/system/borrow/repayment/noRepays" })
	@RequestMapping("/allRepaymentYesNoSystemList")
	@ResponseBody
	public PageResult<BorrowRepayment> allRepaymentYesNoSystemList(PageSearch pageSearch, SearchVO search) throws Exception {
		PageResult<BorrowRepayment> results = new PageResult<BorrowRepayment>();
		search.setStatus(search.getStatus() == null ? null : search.getStatus());
		// 时间转换
		ModelAndViewUtil.addSearchTimeVO(search);
		pageSearch.setEntity(search);
		List<BorrowRepayment> failBorrows = borrowRepaymentService.selectByRepaymentSystemListPage(pageSearch);
		results.setRows(failBorrows);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	/**
	 * 后台借款协议
	 * @param id
	 * @return
	 * @author louchen
	 * @date February 26,AD2015
	 */
	@RequestMapping(value = "/agreement")
	public ModelAndView agreementVfunding(int borrowId) {
		boolean istender = true;
		Borrow borrow = borrowService.selectById(borrowId);
		Date repaymentTime = new Date(borrow.getRepaymentTime());
		User borrowUser = userService.selectByPrimaryKey(borrow.getUserId());
		List<BorrowTenderVO> tenderList = this.borrowTenderService
				.selectAgreementPage(borrowId);
		ModelAndView mv = null;
		if (EmptyUtil.isNotEmpty(borrow.getMortgageTypeid())
				&& borrow.getMortgageTypeid() == 1) {// 汽车(合作方协议)
			mv = new ModelAndView("webpage/borrow/agreement/agreementPartner");
		} else if (EmptyUtil.isNotEmpty(borrow.getMortgageTypeid())
				&& borrow.getMortgageTypeid() == 2) {// 微积金协议
			mv = new ModelAndView("webpage/borrow/agreement/agreement");
		} else {
			mv = new ModelAndView("webpage/borrow/agreement/agreement");
		}
		mv.addObject("tenderList", tenderList);
		mv.addObject("borrow", borrow);
		mv.addObject("user", borrowUser);
		mv.addObject("istender", istender);
		mv.addObject("repaymentTime", repaymentTime);
		return mv;
	}
	
	/**
	 * 借款协议pdf
	 * @author liuhuan
	 */
	@RequestMapping(value="/agreementPdf/{id}")
	public void agreementPdf(HttpServletResponse response,@PathVariable("id")int id) throws IOException{
		Borrow borrow = borrowService.selectById(id);
		if(borrow.getStatus() == 3){ //复审的标才可以导出
			User user = userService.selectByPrimaryKey(borrow.getUserId());
			List<BorrowTenderVO> tenderList = this.borrowTenderService.selectAgreementPage(id);
			if(EmptyUtil.isNotEmpty(borrow.getMortgageTypeid()) && borrow.getMortgageTypeid() == 1){//汽车(合作方协议)
				createPdf2(response,borrow,user,tenderList);
			} else if(EmptyUtil.isNotEmpty(borrow.getMortgageTypeid()) && borrow.getMortgageTypeid() ==2){//微积金协议
				createPdf(response,borrow,user,tenderList);
			} else { 
				createPdf(response,borrow,user,tenderList);
			}
		}
	}
	
	private void createPdf(HttpServletResponse response,Borrow borrow,User user,List<BorrowTenderVO> tenderList) throws IOException{
		// 创建一个Document对象
        Document document = new Document();  
        response.setContentType("application/pdf");
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        try {  
        	BaseFont bfChinese = BaseFont.createFont("/SIMYOU.TTF", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //	BaseFont bfChinese = BaseFont.createFont(fontsPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        //	BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false);  
            PdfWriter.getInstance(document, ba);
            
            Font title_fontChinese = new Font(bfChinese, 14, Font.BOLD,  Color.BLACK);  //文档标题
            Font bold_fontChinese = new Font(bfChinese, 9, Font.BOLD, Color.BLACK);   //内容标题
            Font small_fontChinese = new Font(bfChinese, 8, Font.NORMAL, Color.BLACK);   //内容
            Font table_fontChinese = new Font(bfChinese, 10, Font.NORMAL, Color.BLACK);  //表格内容
            
            document.open();  // 打开文档，将要写入内容
            
            String fileName = "借款协议（借款人）";
            document.add(new Paragraph(fileName, title_fontChinese));
            document.add(new Paragraph(" ", bold_fontChinese));
            
            String[] title = { "（用户间）债权转让协议" };
            String[] content = { " 本《债权转让协议》（“本协议”）由以下双方于 "+
            		DateUtil.getStringDateByLongDate(Long.parseLong(borrow.getVerifyTime().toString()),"yyyy-MM-dd HH:mm:ss")+" 签订：",  
                    " 借款协议号："+borrow.getVerifyTime()+"      借款人： "+user.getUsername()+"     出借人：详见本协议第一条",
                    "双方根据平等、自愿的原则，就甲方通过由微积金金融信息服务（上海）有限公司（“微积金”）运营管理的微积金网",
                    "（域名为www.vfunding.cn，“微积金网”）平台向乙方转让债权事宜，达成协议如下："};  
            for (String s : title) {  
                document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content) {  
                document.add(new Paragraph(s, small_fontChinese));  
            }
            String[] title1 = { "一、 债权转让" };  
            String[] content1 = { "甲方同意将其通过微积金网平台发放借款形成的以下债权（“标的债权”）转让给乙方，乙方同意受让该债权：",
            "（域名为www.vfunding.cn，“微积金网”）平台向乙方转让债权事宜，达成协议如下："};  
            for (String s : title1) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content1) {  
            	document.add(new Paragraph(s, small_fontChinese));
            }
            
            //表格
            Table aTable = new Table(12);   
            int width[] = {33,33,34,33,33,34,33,33,34,33,33,34};   
            aTable.setWidths(width);   
            aTable.setWidth(95); // 占页面宽度 95%   
            Cell cell = new Cell();   
            cell.setVerticalAlignment(Element.ALIGN_TOP);  
            
            aTable.addCell(new Cell(new Paragraph(" 出借人(id)", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph(" 借款金额", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("借款期限", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("年利率", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("平台服务费率", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("担保服务费率", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("借款开始日", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("借款到期日", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("截止还款日", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("还款本息", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("平台服务费", table_fontChinese)));   
            aTable.addCell(new Cell(new Paragraph("担保服务费", table_fontChinese)));   

            for(BorrowTenderVO vo : tenderList){
				aTable.addCell(new Cell(vo.getTenderUser()));   
				aTable.addCell(new Cell(vo.getAccount()));   
				aTable.addCell(new Cell(vo.getTimeLimit().toString()));   
				aTable.addCell(new Cell(vo.getApr().toString()));   
				aTable.addCell(new Cell(vo.getBfee().toString()));   
				aTable.addCell(new Cell(vo.getGfee().toString()));   
				aTable.addCell(new Cell(DateUtil.getStringDateByLongDate(Long.parseLong(vo.getBorrowStartTime().toString()),"yyyy-MM-dd")));   
				aTable.addCell(new Cell(DateUtil.getStringDateByLongDate(Long.parseLong(vo.getBorrowEndTime().toString()),"yyyy-MM-dd")));   
				aTable.addCell(new Cell(vo.getEachTime()));   
				aTable.addCell(new Cell(vo.getRepaymentAccount()));   
				aTable.addCell(new Cell(vo.getBfeeMoney().toString()));   
				aTable.addCell(new Cell(vo.getGfeeMoney().toString()));   
			}
            document.add(aTable);
            
            
            String[] title2 = { "第二条：借款人" };  
            String[] content2 = { "标的债权的对应借款人（即债务人），以及抵押或质押标的物，已在该标段网页“借款详情”栏内公示，乙方须详细查看并自主决定是否投标。",
            " 鉴于“微积金”保护借款人私密信息的承诺，平台公示时隐藏了部分信息，但在乙方投标完成投资后，有权随时向甲方了解或索取有关借款人的全部信息和借款材料。"};  
            for (String s : title2) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content2) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }
            
            String[] title3 = { "第三条：服务及收费" };  
            String[] content3 = { " “微积金”通过微积金网平台向甲乙各方提供服务，并按照本协议第一条债权列表中注明的费率向乙方收取平台服务费和担保服务费。对此，甲、乙各方均不持任何异议。"};  
            for (String s : title3) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content3) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }
           
            String[] title4 = { "第四条：质押或抵押权的托管" };  
            String[] content4 = { " 依据甲方与借款人的借款协议约定，双方已将质押或抵押车辆（或其他标的）委托第三方进行监督管理（包括但不限于车辆保管、抵押登记、GPS跟踪、质押或抵押权的实现等）",
            		"并支付相应服务费用。乙方作为债权受让人，完全接受甲方与借款人、第三方之间有关质押或抵押的全部约定条款，",
            		"同时并授权由该第三方继续对借款人的质押或抵押车辆实施监督管理（包括但不限于车辆保管、抵押登记、GPS跟踪、质押或抵押权的实现等）。"};  
            for (String s : title4) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content4) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }

            String[] title5 = { "第五条：还款" };  
            String[] content5 = { "1、借款人有义务按照本协议第一条约定的时间和金额按期足额向出借人还款。"
            		,"2、借款人的最后一期还款包含全部剩余本金、利息及其他根据本协议产生的全部费用。"
            		,"3、借款人的每一期还款的还款金额计算公式为：每月须还款总金额=每月须还利息+每月须还本金。"};  
            for (String s : title5) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content5) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }
            
            String[] title6 = { "第六条：逾期还款" };  
            String[] content6 = { "1、若借款人逾期仍未还款,乙方同意并特别授权，“微积金”可通过短信、电话、上门催收等方式对借款人逾期未偿还的本息和罚息进行催收,"
            		,"且“微积金”有权按照未偿还本金千分之八的标准收取催收服务费用。"
            		,"2、乙方授权并支持“微积金”采取以下措施之一项或几项："
            		,"（1）将借款人的有关身份资料及其他个人信息通过本网站“逾期黑名单”等栏目对外公开；"
            		,"（2）将借款人的有关身份资料及其他个人信息正式备案在“不良信用记录”,列入全国个人信用评级体系的黑名单(“不良信用记录”数据将为银行、电信、担保公司"
            		,"  、人才中心等有关机构提供个人不良信用信息)；"
            		,"（3）对借款人采取法律措施,由此所产生的所有法律后果将由借款人来承担。"
            		,"3、“微积金”接受乙方委托和授权，对逾期仍未还款的借款人收取逾期罚息作为催收费用，采取多种方式催收，将借款人的相关信息对外公开或列入"
            		,"“不良信用记录”或采取法律措施等各项行为，均只是“微积金”根据本协议为乙方提供的一种服务，“微积金”对借款本息之最终清偿不承担任何担保或其他法律责任。"
            		," 4、本协议第一条债权列表中的每一出借人（包括乙方）与借款人之间的借款均是相互独立的,一旦借款人逾期未归还借款本息,"
            		,"任何一出借人都有权单独或共同向借款人自行追索或者提起法律诉讼。"
            };  
            for (String s : title6) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content6) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }

            String[] title7 = { " 第七条：债权再转让" };  
            String[] content7 = { "在正常还款期内或遇有借款人逾期当天仍未清偿本息时，乙方可向“微积金”提出债权再转让申请，并不可撤销地特别授权“微积金”全权办理转让事宜，"
            		,"“微积金”据此提供债权再转让的推荐服务，一旦有受让人同意受让并支付转让价款（不超过借款人逾期仍未偿还的借款本息，"
            		,"具体金额视出借人在本网站的会员级别不同而有所不同）后，该受让人即取得乙方在本协议项下的债权。"
            };  
            for (String s : title7) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content7) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }
            String[] title8 = { "第八条：债权转让金的支付和还款" };  
            String[] content8 = { " 1、乙方在完成投标后,即委托“微积金”网站在本协议生效时将相应的债权转让金直接划付至甲方帐户。"
            		," 2、乙方委托并授权“微积金”将借款人每期还款直接划付至乙方的“微积金”个人会员帐户内。"
            		," 3、“微积金”网站接受甲方、乙方或借款人的委托或指令所采取的划付款项行为，所产生的法律后果均由相应委托方自行承担。"
            		," 3、“微积金”网站接受甲方、乙方或借款人的委托或指令所采取的划付款项行为，所产生的法律后果均由相应委托方自行承担。"
            		," 4、若借款人的任何一期还款不足以偿还应还本金、利息和违约金,则出借人（包括乙方）之间同意按照各自出借金额在出借总额中的比例收取还款。"
            		," 5、本协议的履行地为“微积金”网站的住所地(上海市徐汇区)。"
            };  
            for (String s : title8) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content8) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }
            String[] title9 = { "第九条：法律适用和管辖" };  
            String[] content9 = { "本协议的签订、履行、终止、解释均适用中华人民共和国法律,并由协议履行地的上海市徐汇区人民法院管辖。"
            };  
            for (String s : title9) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content9) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }
            
            String[] title10 = { "第十条：特别条款" };  
            String[] content10 = { "1、乙方承诺并保证，用于投资的资金来源合法，并在自己经济承受能力范围之内。",
            		 "2、“微积金”网站仅作为网友之间小额资金互助平台,借款人和出借人均不得利用本网站平台进行信用卡套现、信用卡诈骗、洗钱、不正当或虚假交易等违法活动。"
            		,"否则，出借人、借款人和/或本网站均有权向公安等有关行政机关举报,追究其相关法律责任。"
            };  
            for (String s : title10) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content10) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }

            String[] title11 = { "第十一条：其他" };  
            String[] content11 = { "1、本协议采用电子文本形式制成,并通过站内信的形式发送至协议各方本网站信箱。",
            		"2、本协议自甲方在“微积金”网站发布的转让标的债权审核成功之日及本协议题头标明的签订日起生效,甲方、乙方、“微积金”各执一份,并具同等法律效力。"
            		," 3、在履行本协议过程中，还应遵守本网站的各项规定。"
            		," 4、“微积金”作为本协议当事人之一，根据本协议的规定和网站的其他规定行使各项权利、发出各项通知或采取各项措施。 "
            };  
            for (String s : title11) {  
            	document.add(new Paragraph(s, bold_fontChinese));  
            } 
            for (String s : content11) {  
            	document.add(new Paragraph(s, small_fontChinese));  
            }

        	response.setHeader("Content-disposition", "attachment;filename=\""+ new String(("借款协议书.pdf").getBytes("GBK"),"ISO-8859-1") + "\""); 
        } catch (Exception e) {  
            System.err.println(e.getMessage());  
        }  
        // 关闭打开的文档  
        document.close();  
        
        response.setContentType("application/pdf");   
        response.setContentLength(ba.size());
        ServletOutputStream out = response.getOutputStream();
        ba.writeTo(out);   
        out.flush();
	}
	
	
	private void createPdf2(HttpServletResponse response,Borrow borrow,User user,List<BorrowTenderVO> tenderList) throws IOException{
		// 创建一个Document对象
		String fontsPath = "c://windows//fonts//simsun.ttc,1";
		Document document = new Document();  
		response.setContentType("application/pdf");
		ByteArrayOutputStream ba = new ByteArrayOutputStream();
		try {  
			//BaseFont bfChinese = BaseFont.createFont("c:\\winnt\\fonts\\SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			BaseFont bfChinese = BaseFont.createFont(fontsPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			//BaseFont bfChinese = BaseFont.createFont("STSongStd-Light", "UniGB-UCS2-H", false); 
			PdfWriter.getInstance(document, ba);
			
			Font title_fontChinese = new Font(bfChinese, 14, Font.BOLD,  Color.BLACK);  //文档标题
			Font bold_fontChinese = new Font(bfChinese, 9, Font.BOLD, Color.BLACK);   //内容标题
			Font small_fontChinese = new Font(bfChinese, 8, Font.NORMAL, Color.BLACK);   //内容
			Font table_fontChinese = new Font(bfChinese, 10, Font.NORMAL, Color.BLACK);  //表格内容
			
			document.open();  // 打开文档，将要写入内容
			
			String fileName = "借款协议（借款人）";
			document.add(new Paragraph(fileName, title_fontChinese));
			document.add(new Paragraph(" ", bold_fontChinese));  

			String[] title = { "借款协议书" };
			String[] content = { "借款人（甲方）：  "+user.getUsername(),  
					" 出借人（乙方）：参与投标并同意本协议各项条款的“微积金”个人会员（详见本协议第一条列表）      ",  
					"监管人（丙方）：上海欧美亚二手车经营有限公司",
					"遵照自愿、平等的原则，就甲方通过由微积金金融信息服务（上海）有限公司（“微积金”）运营管理的微积金网（域名为www.vfunding.cn，“微积金网”）",
					"平台向乙方借款事宜，各方当事人于"+DateUtil.getStringDateByLongDate(Long.parseLong(borrow.getVerifyTime().toString()),"yyyy-MM-dd HH:mm:ss") 
					+"通过网上平台点击确认的方式签订本协议，以资共同遵守：",
					"第一条：借款金额、年利率、借款期限",
					"甲方通过微积金网平台发标以实现借款，具体详情见下表。乙方以投标行为实现向甲方出借资金（投标金额即借款金额），并与下表中的“出借人”共同按份享有债权。"};  
			for (String s : title) {  
				document.add(new Paragraph(s, bold_fontChinese));
			} 
			for (String s : content) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			//表格
			Table aTable = new Table(12);
			int width[] = {33,33,34,33,33,34,33,33,34,33,33,34};
			aTable.setWidths(width);
			aTable.setWidth(95); // 占页面宽度 95%
			Cell cell = new Cell();
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			aTable.addCell(new Cell(new Paragraph(" 出借人(id)", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph(" 借款金额", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("借款期限", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("年利率", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("平台服务费率", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("监管服务费率", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("借款开始日", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("借款到期日", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("截止还款日", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("还款本息", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("平台服务费", table_fontChinese)));
			aTable.addCell(new Cell(new Paragraph("监管服务费", table_fontChinese)));
			
			for(BorrowTenderVO vo : tenderList){
				aTable.addCell(new Cell(vo.getTenderUser()));
				aTable.addCell(new Cell(vo.getAccount()));
				aTable.addCell(new Cell(vo.getTimeLimit().toString()));
				aTable.addCell(new Cell(vo.getApr().toString()));
				aTable.addCell(new Cell(vo.getBfee().toString()));
				aTable.addCell(new Cell(vo.getGfee().toString()));
				aTable.addCell(new Cell(DateUtil.getStringDateByLongDate(Long.parseLong(vo.getBorrowStartTime().toString()),"yyyy-MM-dd")));
				aTable.addCell(new Cell(DateUtil.getStringDateByLongDate(Long.parseLong(vo.getBorrowEndTime().toString()),"yyyy-MM-dd"))); 
				aTable.addCell(new Cell(vo.getEachTime()));
				aTable.addCell(new Cell(vo.getRepaymentAccount()));
				aTable.addCell(new Cell(vo.getBfeeMoney().toString()));
				aTable.addCell(new Cell(vo.getGfeeMoney().toString()));
			}
			document.add(aTable);
			
			String[] title2 = { "第二条：借款人" };  
			String[] content2 = { "借款人及抵押或质押标的物，已在该标段网页“借款详情”栏内公示，乙方须详细查看并自主决定是否投标。鉴于“微积金”保护借款人私密信息的承诺，平台公示时隐藏了部分信息，",
			"但在乙方投标完成投资后，有权随时向甲方了解或索取其全部信息和材料。"};  
			for (String s : title2) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content2) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			
			String[] title3 = { "第三条：服务及收费" };  
			String[] content3 = { "“微积金”通过微积金网平台向甲乙各方提供服务，并按照本协议第一条列表中注明的费率向甲乙双方收取平台服务费。"
					,"丙方作为监管人，接受甲乙双方的共同委托和授权，对质押或抵押车辆（或其他标的）或其他形式的担保提供监督管理服务，服务内容包括但不限于车辆保管"
					,"、抵押 登记、GPS跟踪、质押权、抵押权或其他担保权的实现等，并按照本协议第一条列表中注明的费率向甲乙双方收取监管服务费。"
			};  
			for (String s : title3) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content3) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			
			String[] title4 = { "第四条：车辆质押或抵押" };  
			String[] content4 = { "1、为保障乙方债权的实现，甲方自愿提供车辆质押或抵押担保，车辆具体情况参见发标公示。",
					"2、甲方自愿在“微积金”网络平台发标前将质押车辆交由丙方监管，或者将车辆抵押给丙方，乙方对丙方的前期监管或抵押予以追认，即质押（或抵押）"
					,"合同自甲方将车辆交付或抵押给丙方时成立并生效。",
					"3、甲、乙双方共同委托丙方对质押（抵押）车辆进行监管，并向丙方支付监管服务费及其他费用（包括但不限于停车费、GPS设备费等）。",
					"4、甲方同意，乙方特别授权丙方代为行使质押权（抵押权），丙方有权在甲方逾期还款时为乙方利益实现质押权（抵押权），用于支付甲方应向乙方偿付的款项。"
					," 同时，丙方按甲方拖欠借款本息总额的10%向甲方收取一次性额外服务费，该服务费在质押（抵押）车辆变卖（或拍卖、处理等）所得款中优先受偿。",
					"5、在本协议履行期间，乙方特别授权，丙方有权根据甲方的申请审核决定是否允许甲方变更抵押或质押车辆。"};  
			
			for (String s : title4) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content4) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}

			String[] title5 = { "第五条：还款" };  
			String[] content5 = { "1、甲方承诺按照本协议第一条约定的时间和金额按期足额向出借人还款。"
					,"2、甲方的最后一期还款必须包含全部剩余本金、利息及其他根据本协议产生的全部费用。"
					,"3、甲方的每一期还款的还款金额计算公式为：每月须还款总金额=每月须还利息+每月须还本金。"};  
			for (String s : title5) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content5) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			
			String[] title6 = { "第六条：逾期还款" };  
			String[] content6 = { "1、甲方逾期仍未还款的,除向乙方支付正常本息以外,还应每天向“微积金”支付逾期罚息(金额为未偿还本金的千分之八)。"
					,"甲乙双方均同意并认可，“微积 金”网站可通过短信、电话、上门催收等方式对甲方逾期未偿还的本息进行催收,且有权按照前述标准向甲方收取催收服务费用。"
					,"2、甲方逾期还款如造成乙方因此遭受损失(包括但不限于律师费、公证费等)，全部由甲方承担；"
					,"3、甲、乙双方同意并授权，如甲方逾期偿还任何一期还款，“微积金”网站有权采取以下措施之一项或几项："
					,"（1）将甲方的有关身份资料及其他个人信息通过本网站“逾期黑名单”等栏目对外公开；"
					,"（2）将甲方的有关身份资料及其他个人信息正式备案在“不良信用记录”,列入全国个人信用评级体系的黑名单(“不良信用记录”数据将为银行、电信、"
					, "担保公司、人才中心等有关机构提供个人不良信用信息)；（3）对甲方采取法律措施。"
					,"4、“微积金”接受乙方委托和授权，对逾期仍未还款的甲方收取逾期罚息作为催收费用，采取多种方式催收，将甲方的相关信息对外公开或列入“不良信用记录”"
					,"或采取法律措施等各项行为，均只是“微积金”根据本协议为乙方提供的一种服务，“微积金”对借款本息之最终清偿不承担任何担保或其他法律责任。"
					,"5、本协议第一条列表中的每一出借人（包括乙方）与甲方之间的借款均是相互独立的,一旦甲方逾期未归还借款本息,任何一出借人都有权单独或共同向借款人自行追索或者提起法律诉讼。"
					,"6、丙方可为债权人利益随时变卖、拍卖、处理质押（抵押）车辆，以实现质押（抵押）权。"
			};  
			for (String s : title6) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content6) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			
			String[] title7 = { "第七条：债权转让" };  
			String[] content7 = { "甲乙双方均同意，如甲方逾期当天仍未清偿借款本息,则乙方可向“微积金”提出债权转让申请，并不可撤销地特别授权“微积金”全权办理转让事宜，"
					,"“微积金” 据此提供债权转让的推荐服务，一旦有受让人同意受让并支付转让价款（不超过甲方逾期仍未偿还的借款本息，具体金额视乙方在本网站的会员级别不同而有所不 同）后，"
					,"该受让人即取得乙方在本协议项下的债权及相应的质押（抵押）权。"
			};  
			for (String s : title7) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content7) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			String[] title8 = { "第八条：借款的支付和还款" };  
			String[] content8 = { "1、乙方在同意向甲方出借相应款项时,即委托“微积金”在本借款协议生效时将该笔借款直接划付至甲方帐户。"
						,"2、甲方已委托“微积金”将每期还款直接划付至乙方“微积金”个人会员账户。"
						,"3、丙方委托“微积金”将甲方每期还款中的监管服务费直接划付至丙方账户。"
						,"4、“微积金”网站接受甲方、乙方或丙方的委托或指令所采取的划付款项行为，所产生的法律后果均由相应委托方自行承担。"
						,"5、若甲方的任何一期还款不足以偿还应还本金、利息和违约金,则出借人（包括乙方）之间同意按照各自出借金额在出借金额总额中的比例收取还款。"
						,"6、本协议的履行地为“微积金”网站的住所地(上海市徐汇区)。"
			};  
			for (String s : title8) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content8) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			String[] title9 = { "第九条：法律适用和管辖" };  
			String[] content9 = { "本协议的签订、履行、终止、解释均适用中华人民共和国法律,并由协议履行地的上海市徐汇区人民法院管辖。"
			};  
			for (String s : title9) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content9) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			
			String[] title10 = { "第九条：提前到期和提前偿还" };  
			String[] content10 = { "1、若甲方出现如下任何一种情况,则本协议项下的全部借款自动提前到期,甲方在收到本网站发出的借款提前到期通知后，"
					 ,"应立即清偿全部本金、利息、逾期利息及根据本协议产生的其他全部费用："
					 ,"(1)甲方因任何原因逾期支付任何一期还款超过30天的；"
					 ,"(2)甲方的工作单位、职务或住所变更后，未在30天内通知本网站；"
					 ,"(3)甲方发生影响其清偿本协议项下借款的其他不利变化，未在30天内通知本网站。"
					 ,"2、乙方同意,甲方有权提前清偿全部或者部分借款而不支付违约金，但需按约定利率支付利息（借款超过1日不足1个月者利息按足月计算)。"
					 ,"3、无论提前到期或提前偿还，丙方均按照实际监管期限收取监管服务费及其他费用。"
			};  
			for (String s : title10) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content10) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			
			String[] title11 = { "第十条：法律适用和管辖" };  
			String[] content11 = { "本协议的签订、履行、终止、解释均适用中华人民共和国法律,并由协议履行地的上海市徐汇区人民法院管辖。"};  
			for (String s : title11) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content11) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			String[] title12 = { "第十一条：特别条款" };  
			String[] content12 = { "1、甲方保证，借款将用于合法用途，不将所借款项用于任何违法活动(包括但不限于赌博、吸毒、贩毒、卖淫嫖娼等)及一切高风险投资(如证券期货、彩票等)。"
					,"如甲方违反前述保证或有违反前述保证的嫌疑，则乙方有权采取下列措施："
					,"（1）宣布提前收回全部借款；"
					,"（2）向公安等有关行政机关举报,追回此款并追究甲方的法律责任。"
					,"甲方和乙方均同意并授权“微积金”网站采取前述措施。"
					,"2、“微积金”网站仅作为网友之间小额资金互助平台,甲方和乙方均不得利用本网站平台进行信用卡套现、信用卡诈骗、洗钱、不正当或虚假交易等违法活动。"
					,"否则，甲方、乙方和/或本网站有权向公安等有关行政机关举报,追究其相关法律责任。"};  
			for (String s : title12) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content12) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			String[] title13 = { "第十二条：其他" };  
			String[] content13 = { "1、本协议采用电子文本形式制成,并通过站内信的形式发送至协议各方本网站信箱。"
					,"2、本协议自甲方在本网站发布的借款标的审核成功之日及本协议题头标明的签订日起生效,甲方、乙方、丙方、本网站各执一份,并具同等法律效力。"
					,"3、在履行本协议过程中，各方还应遵守本网站的其他各项规定。"
					,"4、“微积金”作为本协议当事人之一，根据本协议的规定和本网站的其他规定行使各项权利、发出各项通知或采取各项措施。"};  
			for (String s : title13) {  
				document.add(new Paragraph(s, bold_fontChinese));  
			} 
			for (String s : content13) {  
				document.add(new Paragraph(s, small_fontChinese));  
			}
			
			response.setHeader("Content-disposition", "attachment;filename=\""+ new String(("借款协议书.pdf").getBytes("GBK"),"ISO-8859-1") + "\""); 
		} catch (Exception e) {  
			System.err.println(e.getMessage());  
		}  
		// 关闭打开的文档  
		document.close();  
		response.setContentType("application/pdf");
		response.setContentLength(ba.size());
		ServletOutputStream out = response.getOutputStream();
		ba.writeTo(out);
		out.flush();
	}	
	
	
	
	/*********************************************20150511后台借款标发布*****************************************************/
	
	
	
	/**
	 * 添加借款标页面
	 * @return
	 */
	@RequestMapping(value = "/addBorrowPage")
	public ModelAndView dayBorrowPage() {
		ModelAndView mv = new ModelAndView("webpage/borrow/addBorrowPage");
		return mv;
	}
	@Autowired
	private IAttestationService attestationService;
	@RequestMapping(value = "/addBorrow")
	@ResponseBody
	public Json addBorrow(Borrow borrow,String username,BorrowVO bvo,MortgageCar mortgageCar,
			HttpServletRequest request,MultipartHttpServletRequest multipartRequest) throws IOException{
		Json j = new Json();
		j.setSuccess(false);
		String result = "";
		if(EmptyUtil.isEmpty(borrow.getAccount())){
			result = "发标金额不能为空";
		} else if(EmptyUtil.isEmpty(borrow.getContent())){
			result = "发标内容不能为空";
		} else if(EmptyUtil.isEmpty(borrow.getName())){
			result = "发标名称不能为空";
		} else if(EmptyUtil.isEmpty(borrow.getApr())){
			result = "发标利率不能为空";
		} else if(EmptyUtil.isEmpty(borrow.getTimeLimit())){
			result = "发标期限不能为空";
		} else if(EmptyUtil.isEmpty(borrow.getUserId())){
			result = "发标人不可为空";
		} else {
			User checkUser = this.userService.selectByPrimaryKey(borrow.getUserId());
			if(checkUser == null){
				result = "该借款人ID不存在";
				j.setMsg(result);
				return j;
			}else if(checkUser.getTypeId() != 40){
				result = "该借款人ID不存在";
				j.setMsg(result);
				return j;
			}
			borrow.setTimeLimitDay(borrow.getTimeLimit().byteValue());
			MultipartEntityBuilder card_pic1 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("card_pic1"));
			MultipartEntityBuilder card_pic2 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("card_pic2"));
			MultipartEntityBuilder car_pic1 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("car_pic1"));
			MultipartEntityBuilder other3 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("other3"));
			MultipartEntityBuilder other2 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("other2"));
			MultipartEntityBuilder other1 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("other1"));
			MultipartEntityBuilder carcard_pic3 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("carcard_pic3"));
			MultipartEntityBuilder car_pic2 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("car_pic2"));
			MultipartEntityBuilder car_pic3 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("car_pic3"));
			MultipartEntityBuilder car_pic4 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("car_pic4"));
			MultipartEntityBuilder carcard_pic1 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("carcard_pic1"));
			MultipartEntityBuilder carcard_pic2 = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartRequest.getFile("carcard_pic2"));
			String card_pic1_ = null;
			if (EmptyUtil.isNotEmpty(card_pic1)&&multipartRequest.getFile("card_pic1").getSize()!=0) {
				card_pic1_ = this.attestationService.uploadAttestation(card_pic1);
			}
			String card_pic2_ = null;
			if (EmptyUtil.isNotEmpty(card_pic2)&&multipartRequest.getFile("card_pic2").getSize()!=0) {
				card_pic2_ = this.attestationService.uploadAttestation(card_pic2);
			}
			String car_pic1_ = null;
			if (EmptyUtil.isNotEmpty(car_pic1)&&multipartRequest.getFile("car_pic1").getSize()!=0) {
				car_pic1_ = this.attestationService.uploadAttestation(car_pic1);
			}
			String other3_ = null;
			if (EmptyUtil.isNotEmpty(other3)&&multipartRequest.getFile("other3").getSize()!=0) {
				other3_ = this.attestationService.uploadAttestation(other3);
			}
			String other2_ = null;
			if (EmptyUtil.isNotEmpty(other2)&&multipartRequest.getFile("other2").getSize()!=0) {
				other2_ = this.attestationService.uploadAttestation(other2);
			}
			String other1_ = null;
			if (EmptyUtil.isNotEmpty(other1)&&multipartRequest.getFile("other1").getSize()!=0) {
				other1_ = this.attestationService.uploadAttestation(other1);
			}
			String carcard_pic3_ = null;
			if (EmptyUtil.isNotEmpty(carcard_pic3)&&multipartRequest.getFile("carcard_pic3").getSize()!=0) {
				carcard_pic3_ = this.attestationService.uploadAttestation(carcard_pic3);
			}
			String car_pic2_ = null;
			if (EmptyUtil.isNotEmpty(car_pic2)&&multipartRequest.getFile("car_pic2").getSize()!=0) {
				car_pic2_ = this.attestationService.uploadAttestation(car_pic2);
			}
			String car_pic3_ = null;
			if (EmptyUtil.isNotEmpty(car_pic3)&&multipartRequest.getFile("car_pic3").getSize()!=0) {
				car_pic3_ = this.attestationService.uploadAttestation(car_pic3);
			}
			String car_pic4_ = null;
			if (EmptyUtil.isNotEmpty(car_pic4)&&multipartRequest.getFile("car_pic4").getSize()!=0) {
				car_pic4_ = this.attestationService.uploadAttestation(car_pic4);
			}
			String carcard_pic1_ = null;
			if (EmptyUtil.isNotEmpty(carcard_pic1)&&multipartRequest.getFile("carcard_pic1").getSize()!=0) {
				carcard_pic1_ = this.attestationService.uploadAttestation(carcard_pic1);
			}
			String carcard_pic2_ = null;
			if (EmptyUtil.isNotEmpty(carcard_pic2)&&multipartRequest.getFile("carcard_pic2").getSize()!=0) {
				carcard_pic2_ = this.attestationService.uploadAttestation(carcard_pic2);
			}
			borrow.setSiteId((short) 1);
//			borrow.setUserId(15); //TODO 发标用户
			borrow.setStatus((byte) 0);
			borrow.setAddtime(Integer.parseInt(DateUtil.getTime()));
			borrow.setAccountYes(BigDecimal.ZERO);
			borrow.setAddip(request.getRemoteAddr());
			
			if(EmptyUtil.isNotEmpty(borrow.getIsday()) && borrow.getIsday() == 1){
				borrow.setBiaoType("tian");
			} else {
				borrow.setBiaoType("fast");
			}
			UserSession user = new UserSession();
			user.setUserId(borrow.getUserId());
			MortgageType mortgagetype = new MortgageType();
			mortgagetype.setTypeName(bvo.getTypeName());
			mortgageCar.setRegisterDate(DateUtil.getDateToString(
					bvo.getRegister_Date(), "yyyy-MM-dd"));
			mortgageCar.setCertificationDate(DateUtil.getDateToString(
					bvo.getCertification_Date(), "yyyy-MM-dd"));
			mortgageCar.setCheckValidDate(DateUtil.getDateToString(
					bvo.getCheckValid_Date(), "yyyy-MM"));
			borrow.setAddip(request.getRemoteAddr());
			
			borrowService.insert8jieBorrow(user, borrow, bvo, mortgagetype, mortgageCar, request,
					card_pic1_,card_pic2_,car_pic1_, other3_,other2_,other1_, carcard_pic3_,
					car_pic2_,car_pic3_,car_pic4_,carcard_pic1_,carcard_pic2_);
			j.setSuccess(true);
		}
		j.setMsg(result);
		return j;
	}
	
	
}
