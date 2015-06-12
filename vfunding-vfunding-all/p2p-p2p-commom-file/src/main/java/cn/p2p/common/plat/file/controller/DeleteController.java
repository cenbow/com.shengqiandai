package cn.p2p.common.plat.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.p2p.common.plat.file.service.IOperationService;
import cn.vfunding.common.framework.utils.mvc.BaseController;

@Controller
@RequestMapping("")
public class DeleteController extends BaseController{

	@Autowired
	private IOperationService operationService;

	@RequestMapping(value = "/delete/{fileName:.*}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteFile(@PathVariable("fileName") String fileName) {
		this.operationService.deleteFile(fileName);
		return "success";
	}
}
