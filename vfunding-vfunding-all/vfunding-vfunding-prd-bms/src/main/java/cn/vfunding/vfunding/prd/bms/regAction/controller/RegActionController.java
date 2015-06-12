package cn.vfunding.vfunding.prd.bms.regAction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.regAction.service.RegActionService;

@Controller
@RequestMapping("/regAction")
public class RegActionController {
	@Autowired
	private RegActionService regActionService;
	
	@RequestMapping("/doAction")
	@ResponseBody
	public Json doAction(String userIds){
		Json j = new Json();
		RegisterVO vo = new RegisterVO();
		vo.setIp("reissue");
		vo.setThirdSource("0");
		vo.setRefererUrl("www.kongbai.com");
		String [] ids = userIds.split(",");
		for(String userId : ids){
			vo.setRegisterUserId(Integer.parseInt(userId));
			regActionService.doUserAction(vo);
		}
		return j;
	}
}
