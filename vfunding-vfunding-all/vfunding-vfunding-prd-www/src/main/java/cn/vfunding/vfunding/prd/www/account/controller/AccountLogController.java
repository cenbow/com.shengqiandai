package cn.vfunding.vfunding.prd.www.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.account.service.IAccountLogService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.QueryParameterVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;

@Controller
@RequestMapping("accountLog")
public class AccountLogController {

	@Autowired
	private IAccountLogService accountLogService;
	@Autowired
	private IAccountService accountService;
	
	@NeedSession(returnUrl = "/accountLog/accountLogList")
	@RequestMapping(value = "accountLogList")
	public ModelAndView selectAccountLogList(PageSearch pageSearch, QueryParameterVO vo) throws Exception{
		ModelAndView mv = new ModelAndView("account/accountRecord");
		//默认查询近一周
		vo.setStartTime(DateUtil.getNextDay(-7, "yyyy-MM-dd"));//前7天
		PageResult<AccountLog> result = this.getAccountLogList(pageSearch, vo);
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		mv.addObject("page", result);
		return mv;
	}
	@NeedSession(returnUrl = "accountLog/accountLogList")
	@RequestMapping(value = "/ajaxListPage")
	@ResponseBody
	public PageResult<AccountLog> selectAccountLogAjaxListPage(QueryParameterVO vo, PageSearch pageSearch) throws Exception{
		PageResult<AccountLog> result = this.getAccountLogList(pageSearch, vo);
		return result;
	}
	private PageResult<AccountLog> getAccountLogList(PageSearch pageSearch, QueryParameterVO vo) throws Exception {
		vo.setUserId(UserSession.getLoginUserId());
		PageResult<AccountLog> result = new PageResult<AccountLog>();
		List<AccountLog> resultList = null;
		pageSearch.setEntity(vo);
		ModelAndViewUtil.addQueryTimeVO(vo);
		resultList = this.accountLogService.selectAccountLogListPage(pageSearch);
		result.setRows(resultList);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}
}
