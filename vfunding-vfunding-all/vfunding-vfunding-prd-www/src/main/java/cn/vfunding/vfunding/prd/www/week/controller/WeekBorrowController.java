package cn.vfunding.vfunding.prd.www.week.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawn;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawnPic;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowPawnPicService;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowPawnService;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowService;
import cn.vfunding.vfunding.biz.week.service.IWeekRepaymentService;
import cn.vfunding.vfunding.biz.week.service.IWeekService;
import cn.vfunding.vfunding.biz.week.service.IWeekTenderService;
import cn.vfunding.vfunding.biz.week.vo.SearchWeekRepaymentVO;
import cn.vfunding.vfunding.biz.week.vo.SearchWeekVO;
import cn.vfunding.vfunding.biz.week.vo.WeekBorrowVO;
import cn.vfunding.vfunding.biz.week.vo.WeekRepaymentVO;

@Controller
@RequestMapping(value = "/weekBorrow")
public class WeekBorrowController extends BaseController {
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IWeekService weekService;
	@Autowired
	private IWeekBorrowService weekBorrowService;
	@Autowired
	private IWeekBorrowPawnService weekBorrowPawnService;
	@Autowired
	private IWeekBorrowPawnPicService weekBorrowPawnPicService;
	@Autowired
	private IWeekRepaymentService weekRepaymentService;
	@Autowired
	private IWeekTenderService weekTenderService;
	

	@RequestMapping("/weekBorrowByIdAjax")
	@ResponseBody
	public WeekBorrowVO weekBorrowByIdAjax(Integer id) throws Exception {
		WeekBorrow wb = this.weekBorrowService.selectByPrimaryKey(id);
		WeekBorrowPawn wbp = null;
		List<WeekBorrowPawnPic> listWbpc = null;
		if (wb != null && wb.getId() > 0) {
			wbp = this.weekBorrowPawnService.selectByBorrowId(wb.getId());
		}
		if (wbp != null && wbp.getId() > 0) {
			listWbpc = this.weekBorrowPawnPicService.selectPicByPawnId(wbp
					.getId());
		}
		WeekBorrowVO vo = new WeekBorrowVO();
		vo.setWb(wb);
		vo.setWbp(wbp);
		vo.setListWbpc(listWbpc);
		return vo;
	}

	@RequestMapping("/weekBorrowById")
	@ResponseBody
	public ModelAndView weekBorrowById(Integer id) throws Exception {
		ModelAndView mv = new ModelAndView("week/borrowDetail");
		WeekBorrow wb = this.weekBorrowService.selectByPrimaryKey(id);
		WeekBorrowPawn wbp = null;
		List<WeekBorrowPawnPic> listWbpc = null;
		if (wb != null && wb.getId() > 0) {
			wbp = this.weekBorrowPawnService.selectByBorrowId(wb.getId());
		}
		if (wbp != null && wbp.getId() > 0) {
			listWbpc = this.weekBorrowPawnPicService.selectPicByPawnId(wbp
					.getId());
		}
		WeekBorrowVO vo = new WeekBorrowVO();
		vo.setWb(wb);
		vo.setWbp(wbp);
		vo.setListWbpc(listWbpc);
		mv.addObject("weekBorrowVO", vo);
		return mv;
	}

	/**
	 * 跳转到周盈宝产品认购列表页面
	 * 
	 * @return
	 * @author louchen 2014-12-10
	 */
	@RequestMapping(value = "/borrowList")
	@ResponseBody
	public ModelAndView test() {
		ModelAndView mv = new ModelAndView("week/weekBorrowList");
		return mv;
	}

	/**
	 * 周盈宝列表
	 * 
	 * @param vo
	 * @param pageSearch
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/borrowListAjax")
	@ResponseBody
	public PageResult<Week> borrowListAjax(SearchWeekVO vo,
			PageSearch pageSearch) throws Exception {
		PageResult<Week> result = new PageResult<Week>();
		pageSearch.setEntity(vo);
		List<Week> list = this.weekService.selectWeekForBuyListPage(pageSearch);
		for (Week w : list) {
			SearchWeekRepaymentVO searchWeekRepaymentVO = new SearchWeekRepaymentVO();
			searchWeekRepaymentVO.setStatus(0);
			searchWeekRepaymentVO.setWeekId(w.getId());
			WeekRepaymentVO weekRepaymentVO = this.weekRepaymentService
					.selectSumWeekRepayment(searchWeekRepaymentVO);
			if (EmptyUtil.isNotEmpty(weekRepaymentVO)) {
				w.setWeekIsRepay(false);
			} else {
				w.setWeekIsRepay(true);
			}

		}
		result.setTotal(pageSearch.getTotalResult());
		result.setRows(list);
		return result;
	}

	// 借款协议--微积金
	@RequestMapping(value = "/agreementVfunding/{weekId}")
	public ModelAndView agreementVfunding(@PathVariable("weekId") int weekId) {
		boolean istender = true;
		Week week = weekService.selectByPrimaryKey(weekId);
		User user = userService.selectByPrimaryKey(week.getHolderUser());
		List<BorrowTenderVO> tenderList = this.weekTenderService.selectAgreementPage(weekId);
		if (EmptyUtil.isNotEmpty(UserSession.getLoginUserId())
				&& EmptyUtil.isNotEmpty(tenderList)) {
			for (BorrowTenderVO vo : tenderList) {
				if (vo.getTenderUserId().intValue() == UserSession
						.getLoginUserId().intValue()) {
					istender = true;
				} else if (UserSession.getUserSession().getTypeId() == 40) {
					istender = true;
				}
			}
		}
		ModelAndView mv = new ModelAndView("week/agreement");
		mv.addObject("tenderList", tenderList);
		mv.addObject("week", week);
		mv.addObject("user", user);
		mv.addObject("istender", istender);
		return mv;
	}

}
