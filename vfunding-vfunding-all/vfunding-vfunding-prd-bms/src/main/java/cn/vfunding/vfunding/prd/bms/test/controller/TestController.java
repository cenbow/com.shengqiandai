package cn.vfunding.vfunding.prd.bms.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.server.interceptors.NeedLogger;

@Controller
@RequestMapping("/test")
public class TestController {

	@NeedLogger(desc="测试")
	@RequestMapping("/aspect")
	@ResponseBody
	public String testAspect(){
		return "testAspect";
	}
}
