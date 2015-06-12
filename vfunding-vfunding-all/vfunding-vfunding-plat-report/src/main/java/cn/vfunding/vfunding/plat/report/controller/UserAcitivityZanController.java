package cn.vfunding.vfunding.plat.report.controller;

import java.util.List;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.MultipartEntityBuilderUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.report.model.UserActivityZan;
import cn.vfunding.vfunding.biz.report.service.IUserActivityZanSerivce;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;
@Controller
@RequestMapping("/activity/zan")
public class UserAcitivityZanController  extends BaseController{
	
	@Value("${downloadBasePath}")
	private String fileDownloadBasePath;
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserActivityZanSerivce zanService;
	@RequestMapping("/toListPage")
	public ModelAndView toListPage(){
		ModelAndView mv=new ModelAndView("activity-zan/list");
		mv.addObject("fileDownloadBasePath", fileDownloadBasePath);
		return mv;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageResult<UserActivityZan> list(PageSearch search,String userName){
		if(EmptyUtil.isNotEmpty(userName)){
			UserActivityZan entity=new UserActivityZan();
			entity.setUserName(userName);
			search.setEntity(entity);
		}
		
		List<UserActivityZan> list=this.zanService.selectByUserName(search);
		PageResult<UserActivityZan> result=new PageResult<UserActivityZan>();
		result.setRows(list);
		result.setTotal(search.getTotalResult());
		return result;
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public Json add(UserActivityZan record,MultipartHttpServletRequest multipartRequest){
		Json j=new Json();
		try {
			MultipartFile multipartFile = multipartRequest.getFile("files");
			if (EmptyUtil.isNotEmpty(multipartFile)) {
				MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil
						.createMultipartEntityBuilderByMultipartFile(multipartFile);
				this.zanService.savaUserActivityZan(record, multipartEntity);
				j.setSuccess(true);
			} else {
				j.setMsg("附件不可为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/toAddPage")
	public ModelAndView toAddPage(){
		ModelAndView mv=new ModelAndView("activity-zan/add");
		return mv;
	}
	@RequestMapping("/doAffirm/{id}")
	@ResponseBody
	public Json doAffirm(@PathVariable("id") Integer id){
		Json j=new Json ();
		try {
			this.zanService.doAffirm(id);
			j.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/checkUserName/{userName}")
	@ResponseBody
	public Json checkUserName(@PathVariable("userName") String userName){
		Json j =new Json();
		try {
			UserWithBLOBs user=this.userService.selectByUserName(userName);
			if(EmptyUtil.isNotEmpty(user)){
				UserActivityZan zan=this.zanService.selectByUserNameAndLast(userName);
				if(EmptyUtil.isNotEmpty(zan)){
					if(zan.getActivityBonuses()==50){
						j.setMsg("该用户已经增加了50个红包了,不能再增加了!");
					}
				}
				j.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	
}
