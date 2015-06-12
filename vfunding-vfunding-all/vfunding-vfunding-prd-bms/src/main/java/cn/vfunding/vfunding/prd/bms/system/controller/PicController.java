package cn.vfunding.vfunding.prd.bms.system.controller;

import java.io.IOException;
import java.util.List;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.MultipartEntityBuilderUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Pic;
import cn.vfunding.vfunding.biz.system.service.IAttestationService;
import cn.vfunding.vfunding.biz.system.service.PicService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

/**
 * 
 * @author wang.zeyan
 * @date 2015年3月30日
 * @version 1.0
 */
@Controller
@RequestMapping("/system/pic")
public class PicController {
	
	@Autowired
	private IAttestationService attestationService;
	
	@Autowired
	private PicService picService;
	
	@RequestMapping(value = "/picListPage")
	public ModelAndView picListPage(){
		ModelAndView mv = new ModelAndView("webpage/pic/picList");
		
		boolean addOrEdit = UserAuthFilter.isPass("/system/pic/addPic");
		mv.addObject("addOrEdit", addOrEdit);
		
		boolean canDelete = UserAuthFilter.isPass("/system/pic/delete");
		mv.addObject("canDelete", canDelete);
		return mv;
	}
	
	@ParentSecurity("/system/pic/picListPage")
	@RequestMapping(value = "/picList")
	@ResponseBody
	public PageResult<Pic> picList(PageSearch pageSearch){
		PageResult<Pic> results = new PageResult<Pic>();
		List<Pic> scrollpics = picService.selectListPage(pageSearch);
		results.setRows(scrollpics);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
	@ParentSecurity("/system/pic/picListPage")
	@RequestMapping(value = "/addEditPicPage")
	public ModelAndView addEditPicPage(String picKey){
		
		boolean type = false;
		ModelAndView mv = new ModelAndView("webpage/pic/addEditPic");
		if(picKey != null){
			Pic pic = picService.selectByPrimaryKey(picKey);
			mv.addObject("pic", pic);
			type = true;
		}
		mv.addObject("type", type);
		return mv;
	}

	@NeedLogger(desc="添加或修改pic")
	@RequestMapping(value="/addPic")
	@ResponseBody
	public Json addPic(Pic pic,boolean type,MultipartHttpServletRequest multipartRequest) throws IOException{
		Json j=new Json();
		MultipartFile multipartFile = multipartRequest.getFile("pic");
		MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil.createMultipartEntityBuilderByMultipartFile(multipartFile);
		String picPath = null;
		if(EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize()!=0){
			picPath = this.attestationService.uploadAttestation(multipartEntity);
		}
		int i = 0;
		if(type == false){
			pic.setUrl(picPath);
			i = picService.insertSelective(pic);
		} else {
			pic.setUrl(picPath);
			i = picService.updateByPrimaryKeySelective(pic);
		}
		j.setSuccess( i > 0);
		return j;
	}
	
	@NeedLogger(desc="删除pic")
	@RequestMapping(value="/delete")
	@ResponseBody
	public Json delete(String picKey) throws IOException{
		Json j = new Json();
		int i = picService.deleteByPrimaryKey(picKey);
		j.setSuccess( i > 0 );
		return j;
	}
}
