package cn.vfunding.vfunding.prd.www.borrow.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRepaymentService;
import cn.vfunding.vfunding.biz.common.vo.SuccessTenderVO;

@Controller
@RequestMapping("/borrowCollection")
public class BorrowCollectionController extends BaseController {

	@Autowired
	private IAccountFeelService accountFeelService;
	@Autowired
	private IBorrowRepaymentService borrowRepaymentService;
	/**
	 * @Description:查询后台体验标还款剩余体验金
	 * @return
	 * @author liuhuan
	 */
	@RequestMapping("/selectRemaindAccountFeel")
	@ResponseBody
	public SuccessTenderVO selectRemaindAccountFeel(@RequestParam("userId")String userId){
		SuccessTenderVO vo = new SuccessTenderVO();
		//总额-待还本息
		AccountFeel accountFeel = accountFeelService.selectByUserId(Integer.parseInt(userId));
		BigDecimal repayAccount = borrowRepaymentService.selectRepayAccountByUserId(Integer.parseInt(userId));
		try {
			vo.setAccount(accountFeel.getTotal().subtract(repayAccount==null?new BigDecimal("0"):repayAccount));
		} catch (Exception e) {
		}
		return vo;
	}

}
