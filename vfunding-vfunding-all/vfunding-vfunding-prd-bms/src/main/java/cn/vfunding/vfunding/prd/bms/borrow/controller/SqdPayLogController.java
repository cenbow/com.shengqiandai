package cn.vfunding.vfunding.prd.bms.borrow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.p2p.p2p.biz.sqdpaylog.model.SqdPayLog;
import cn.p2p.p2p.biz.sqdpaylog.service.ISqdPayLogService;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;

import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;
@Controller
@RequestMapping(value = { "/system/sqdPayLog", "sqdPayLog" })
public class SqdPayLogController extends BaseController{
	/**
	 * sqd支付记录controller
	 */
	@Autowired
	private ISqdPayLogService sqdPayLogService;
	
	/**
	 * toList
	 * 
	 * @author huangyuancheng
	 * @return 
	 */
	@RequestMapping(value = "/listPayLogPage")
	public ModelAndView listPayLogPage() {
		ModelAndView mv = new ModelAndView("webpage/sqdpaylog/sqdPayLog");
		boolean canEdit = UserAuthFilter.isPass("/system/sqdPayLog/listPayLog");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		return mv;
	}
	
	/**
	 * 查询所有支付记录
	 * 
	 * @param pageSearch
	 * @return 
	 */
	/**
	 * 初审列表
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年6月5日
	 */
	@ParentSecurity("/system/sqdPayLog/listPayLogPage")
	@RequestMapping("/listPayLog")
	@ResponseBody
	public PageResult<SqdPayLog> listPayLog(PageSearch pageSearch, SearchVO search) {
		
		PageResult<SqdPayLog> results = new PageResult<SqdPayLog>();
		pageSearch.setEntity(search);
		List<SqdPayLog> payLogList = sqdPayLogService.selectAllPayLog(pageSearch);
		
		results.setRows(payLogList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	
}
