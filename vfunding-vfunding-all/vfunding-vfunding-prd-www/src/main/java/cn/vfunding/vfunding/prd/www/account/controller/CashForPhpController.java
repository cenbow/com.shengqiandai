package cn.vfunding.vfunding.prd.www.account.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.account.service.IAccountCashService;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.user.service.IUserService;

/**
 * 提供php提现接口
 *
 */
@Controller
@RequestMapping("cashForPhp")
public class CashForPhpController {

	@Autowired
	private IAccountCashService accountCashService;
	@Autowired
	private IUserService userService;
	

	/**
	 * 提现手续费 for php
	 * param:userid,money
	 * @author liuhuan
	 */
	@RequestMapping(value = "/cashFee")
	@ResponseBody
	public MessageVO cashFee(HttpServletRequest request, AccountCashVO vo){
		MessageVO m = new MessageVO();
		if(EmptyUtil.isNotEmpty(vo.getUserId())&&EmptyUtil.isNotEmpty(vo.getMoney())){
			m = accountCashService.updateCashFeePhp(vo);
		} else {
			m.setMsg("error");
		}
		return m;
	}
	
	/**
	 * 财务放款 gor php 
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping(value = "/takeCash")
	@ResponseBody
	public MessageVO takeCash(HttpServletRequest request, AccountCashVO vo){
		MessageVO m = new MessageVO();
		if(vo.getStatus() != null && vo.getCashId() != null && vo.getRemark() != null){
			AccountCash accountCash = accountCashService.selectByPrimaryKey(vo.getCashId());
			String msg = accountCashService.updateTakeCash(accountCash, vo);
			m.setMsg(msg);
		} else {
			m.setMsg("error");
		}
		return m;
	}
	
	
}