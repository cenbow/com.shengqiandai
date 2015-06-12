package cn.vfunding.vfunding.prd.bms.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_system.model.BmsLog;
import cn.vfunding.vfunding.biz.bms_system.service.IBmsLogService;
import cn.vfunding.vfunding.biz.common.vo.LogVO;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping("/system/log")
public class SystemLogController {

	@Autowired
	private IBmsLogService bmsLogService;
	
	/**
	 * 审计日志 页面
	 * @author liuhuan
	 */
	@RequestMapping(value = "/bmsLogListPage")
	public ModelAndView systemLogListPage(){
		ModelAndView mv = new ModelAndView("webpage/system/bmsLogList");
		boolean canDelete = UserAuthFilter.isPass("/system/log/deleteByIds");
		mv.addObject("canDelete", canDelete);
		return mv;
	}
	
	/**
	 * 审计日志
	 * @author liuhuan
	 */
	@ParentSecurity("/system/log/bmsLogListPage")
	@RequestMapping(value = "/bmsLogList")
	@ResponseBody
	public PageResult<BmsLog> bannerList(PageSearch pageSearch, LogVO logvo){
		PageResult<BmsLog> results = new PageResult<BmsLog>();
		//时间转换
		if(EmptyUtil.isNotEmpty(logvo.getStartTime())){
			logvo.setStartTime(logvo.getStartTime()+" 00:00:00");
		}
		if(EmptyUtil.isNotEmpty(logvo.getEndTime())){
			logvo.setEndTime(logvo.getEndTime()+" 23:59:59");
		}
		pageSearch.setEntity(logvo);
		List<BmsLog> bmsLogs = bmsLogService.selectSystemBmsLogListPage(pageSearch);
		results.setRows(bmsLogs);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	/**
	 * 批量删除 日志
	 * @author liuhuan
	 */
	@NeedLogger(desc = "删除日志")
	@RequestMapping(value = "deleteByIds")
	@ResponseBody
	public Json deleteByIds(@RequestParam(value = "ids[]")int[] ids){
		Json j = new Json();
		if(ids.length<=0){
			j.setMsg("请选择删除数据");
			return j;
		}
		int i = bmsLogService.deleteByIds(ids);
		if(i > 0){
			j.setSuccess(true);
		} else {
			j.setMsg("删除失败，系统异常");
		}
		return j;
	}
	
}
