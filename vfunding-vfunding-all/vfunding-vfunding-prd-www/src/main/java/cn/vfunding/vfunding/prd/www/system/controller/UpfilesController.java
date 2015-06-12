package cn.vfunding.vfunding.prd.www.system.controller;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.MultipartEntityBuilderUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.system.model.Upfiles;
import cn.vfunding.vfunding.biz.system.service.IUpfilesService;


@Controller
@RequestMapping("system/upfiles")
public class UpfilesController extends BaseController{

	@Autowired
	private IUpfilesService upService;
	
	@RequestMapping("/upload")
	@ResponseBody
	public Json upfiles(Upfiles upfiles, MultipartHttpServletRequest multipartRequest) throws Exception{
		Json j=new Json();
		MultipartFile multipartFile = multipartRequest.getFile("files");
		if (EmptyUtil.isNotEmpty(multipartFile)
				&& multipartFile.getSize() > 0) {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil
					.createMultipartEntityBuilderByMultipartFile(multipartFile);
			this.upService.insert(upfiles, multipartEntity);
			
			j.setSuccess(true);
		} else {
			j.setMsg("附件不可为空");
		}
		return j;
	}
}
