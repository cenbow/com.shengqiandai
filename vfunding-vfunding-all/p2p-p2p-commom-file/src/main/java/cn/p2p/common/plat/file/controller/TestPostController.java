package cn.p2p.common.plat.file.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.p2p.common.plat.file.model.TestModel;
import cn.vfunding.common.framework.utils.mvc.BaseController;

@Controller
@RequestMapping("")
public class TestPostController extends BaseController{
   
	@RequestMapping(value="/testPost",method=RequestMethod.POST,consumes={"application/json"})//,consumes={"application/json"}
	@ResponseBody
	public String testPost(@RequestBody TestModel model) throws IOException{//TestModel model,,HttpServletRequest request
		
		return "ok";
	}
}
