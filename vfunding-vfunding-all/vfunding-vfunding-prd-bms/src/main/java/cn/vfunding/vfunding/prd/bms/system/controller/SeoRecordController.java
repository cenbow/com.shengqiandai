package cn.vfunding.vfunding.prd.bms.system.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.SeoRecord;
import cn.vfunding.vfunding.biz.system.model.SeoRecordWithBLOBs;
import cn.vfunding.vfunding.biz.system.service.ISeoRecordService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping("/system/seo")
public class SeoRecordController {
	
	@Autowired
	private ISeoRecordService seoRecordService;
	
	/**
	 * 转向 seo 页面
	 * @author liuhuan
	 */
	@RequestMapping(value = "/seoListPage")
	public ModelAndView seoListPage(){
		ModelAndView mv = new ModelAndView("webpage/system/seoRecordList");
		boolean addOrEdit = UserAuthFilter.isPass("/system/seo/addSeoRecord");
		boolean canDelete = UserAuthFilter.isPass("/system/seo/delete");
		mv.addObject("addOrEdit", addOrEdit);
		mv.addObject("canDelete", canDelete);
		return mv;
	}
	/**
	 * 列表
	 * @author liuhuan
	 */
	@ParentSecurity("/system/seo/seoListPage")
	@RequestMapping(value = "/seoList")
	@ResponseBody
	public PageResult<SeoRecord> seoList(PageSearch pageSearch,SeoRecord seo){
		PageResult<SeoRecord> results = new PageResult<SeoRecord>();
		pageSearch.setEntity(seo);
		List<SeoRecord> seoRecords = seoRecordService.selectSystemSeoRecordListPage(pageSearch);
		results.setRows(seoRecords);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	/**
	 * 转向 新增|编辑  页面
	 * @author liuhuan
	 */
	@ParentSecurity("/system/seo/seoListPage")
	@RequestMapping(value = "/addSeoRecordPage")
	public ModelAndView addSeoRecordPage(Integer id){
		ModelAndView mv = new ModelAndView("webpage/system/addEditSeo");
		if(EmptyUtil.isNotEmpty(id)){ //编辑页面
			SeoRecordWithBLOBs record = seoRecordService.selectByPrimaryKey(id);
			mv.addObject("record", record);
			mv.addObject("status", true);//status-true修改；false添加
		} else { // 新增页面
			mv.addObject("status", false);//status-true修改；false添加
		}
		return mv;
	}
	
	@NeedLogger(desc="添加或修改seo关键字")
	@RequestMapping(value="/addSeoRecord")
	@ResponseBody
	public Json addSeoRecord(SeoRecordWithBLOBs record,boolean status){
		Json j = new Json();
		int i = 0;
		Date time = new Date();
		//status-true修改；false添加
		if(status){
			record.setUpdatetime(time);
			i = seoRecordService.updateByPrimaryKeySelective(record);
		} else {
			record.setUpdatetime(time);
			record.setUrl(record.getUrl().startsWith("http://")?record.getUrl():"http://"+record.getUrl());
			i = seoRecordService.insert(record);
		}
		if(i>0){
			j.setSuccess(true);
		}
		return j;
	}
	
	/**
	 * 校验url唯一性
	 * @author liuhuan
	 */
	@UnSecurity
	@UnSession
	@RequestMapping(value="/checkUniqueUrl")
	@ResponseBody
	public boolean checkUniqueUrl(String url){
		url = url.startsWith("http://")?url:"http://"+url;
		if(EmptyUtil.isNotEmpty(seoRecordService.selectUniqueByUrl(url))){
			return false;
		}
		return true;
	}
	
	@NeedLogger(desc="删除seo关键字")
	@RequestMapping(value="/delete")
	@ResponseBody
	public Json delete(Integer id) throws IOException{
		Json j = new Json();
		int i = seoRecordService.deleteByPrimaryKey(id);
		if(i>0){
			j.setSuccess(true);
		} j.setMsg("dfsdfsd");
		return j;
	}
}
