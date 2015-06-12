package cn.vfunding.vfunding.prd.www.borrow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRepayment;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.QueryParameterVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;

@Controller
@RequestMapping("/borrowRepayment")
public class BorrowRepaymentController extends BaseController {

	@Autowired
	private IBorrowRepaymentService borrowRepayService;
	@Autowired
	private IAccountService accountService;

	/**
	 * 还款列表
	 * 
	 * @param status
	 * @param pageSearch
	 * @return
	 * @throws Exception
	 *             author LiLei 2014年5月19日
	 */
	@NeedSession(returnUrl = "/borrowRepayment/myRepay")
	@RequestMapping("/myRepay")
	@ResponseBody
	public ModelAndView repaymentList(QueryParameterVO vo, PageSearch pageSearch) throws Exception {
		ModelAndView mv = new ModelAndView("user/myRepay");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	@NeedSession(returnUrl = "/borrowRepayment/myRepay")
	@RequestMapping("/myRepayAjax")
	@ResponseBody
	public PageResult<BorrowRepayment> repaymentListAjax(QueryParameterVO vo, PageSearch pageSearch) throws Exception {
		PageResult<BorrowRepayment> result = this.getRepaymentList(pageSearch, vo);
		return result;
	}

	private PageResult<BorrowRepayment> getRepaymentList(PageSearch pageSearch, QueryParameterVO vo) throws Exception {
		vo.setUserId(UserSession.getLoginUserId());
		PageResult<BorrowRepayment> result = new PageResult<BorrowRepayment>();
		List<BorrowRepayment> resultList = null;
		pageSearch.setEntity(vo);
		resultList = this.borrowRepayService.selectByUserIdStatusListPage(pageSearch);
		result.setRows(resultList);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

}
