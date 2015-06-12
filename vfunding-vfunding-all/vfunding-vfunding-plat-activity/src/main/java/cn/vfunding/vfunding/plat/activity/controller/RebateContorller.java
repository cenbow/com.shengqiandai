package cn.vfunding.vfunding.plat.activity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.useraction_activity.service.IRebateService;

@Controller
@RequestMapping("/rebate")
public class RebateContorller {

	@Autowired
	private IRebateService rebateService;

	@RequestMapping(value = "/internalRebate", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String internalRebateAction(@RequestBody FinalVerifyVO vo) {
		this.rebateService.internalRebate(vo.getBorrowId());
		return "success";
	}

	@RequestMapping(value = "/hikesRebate", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String hikesRebateAction(@RequestBody FinalVerifyVO vo) {
		this.rebateService.hikesRebate(vo.getBorrowId());
		return "success";
	}

	@RequestMapping(value = "/friendRebate", method = RequestMethod.POST, consumes = { "application/json" })
	@ResponseBody
	public String friendRebateAction(@RequestBody FinalVerifyVO vo) {
		this.rebateService.friendRebate(vo.getBorrowId());
		return "success";
	}
}
