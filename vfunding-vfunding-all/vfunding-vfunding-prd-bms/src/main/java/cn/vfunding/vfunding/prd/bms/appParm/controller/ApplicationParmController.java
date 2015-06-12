package cn.vfunding.vfunding.prd.bms.appParm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;

import cn.vfunding.vfunding.biz.system.model.ApplicationParm;
import cn.vfunding.vfunding.biz.system.model.DataGridVO;
import cn.vfunding.vfunding.biz.system.service.ApplicationParmService;

@Controller
@RequestMapping(value = "/appParm")
public class ApplicationParmController {

	@Autowired
	private ApplicationParmService service;

	@RequestMapping(value = "/list")
	@ResponseBody
	public List<ApplicationParm> appParmList() {
		return service.queryAll();
	}

	@RequestMapping(value = "/listPage")
	public ModelAndView applicationParmListPage() {
		ModelAndView mv = new ModelAndView(
				"webpage/appParm/applicationParmList");

		return mv;
	}

	@RequestMapping(value = "/saveDataGrid")
	@ResponseBody
	public String saveDataGrid(@RequestBody DataGridVO<JSONObject> dgvo) {

		return String.valueOf(service.saveDataGrid(dgvo));
	}
}
