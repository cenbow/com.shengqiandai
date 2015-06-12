package cn.p2p.p2p.prd.bms.current.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.p2p.p2p.biz.current.model.Current;
import cn.p2p.p2p.biz.current.model.CurrentRule;
import cn.p2p.p2p.biz.current.service.ICurrentRuleService;
import cn.p2p.p2p.biz.current.service.ICurrentService;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.page.PageSearch;

@Controller
@RequestMapping("/current")
public class CurrentContorller {

	@Autowired
	private ICurrentService currentService;
	@Autowired
	private ICurrentRuleService currentRuleService;
	/**
	 * 跳转活期标的列表页面
	 * @return
	 */
	@NeedSession
	@RequestMapping("/toCurrentListPage")
	public String toCurrentListPage(){
		return "webpage/current/currentListPage";
	}
	
	@NeedSession
	@RequestMapping("/currentList")
	@ResponseBody
	public PageResult<Current> currentList(PageSearch pageSearch,Current current){
		PageResult<Current> results = new PageResult<Current>();
		pageSearch.setEntity(current);
		List<Current> curentList = this.currentService.selectCurrentListBmsPage(pageSearch);
		results.setRows(curentList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	/**
	 * 跳转添加活期标的页面
	 * @return
	 */
	@NeedSession
	@RequestMapping("/toAddCurrentPage")
	public String toAddCurrentPage(){
		return "webpage/current/addCurrentPage";
	}
	
	/**
	 * 添加活期标的
	 * @return
	 */
	@NeedSession
	@RequestMapping("/addCurrent")
	@ResponseBody
	public Json addCurrent(Current current,CurrentRule currentRule){
		Json j = new Json();
		current.setAddTime(new Date());
		this.currentService.insertCurrent(current, currentRule);
		j.setSuccess(true);
		return j;
	}
	
	
	/**
	 * 跳转审核活期标页面
	 * @return
	 */
	@NeedSession
	@RequestMapping("/toTrialCurrentPage")
	public String toTrialCurrentPage(Model model,Integer currentId){
		Current current = this.currentService.selectByPrimaryKey(currentId);
		model.addAttribute("current", current);
		return "webpage/current/trialCurrentPage";
	}
	
	/**
	 * 审核活期标的
	 * @param model
	 * @param currentId
	 * @return
	 */
	@NeedSession
	@RequestMapping("/trialCurrent")
	@ResponseBody
	public Json trialCurrent(Current current,CurrentRule currentRule){
		Json j = new Json();
		j.setSuccess(this.currentService.trialCurrent(current, currentRule));
		return j;
	}
}
