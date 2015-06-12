package cn.vfunding.vfunding.prd.www.system.controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Controller
@RequestMapping(value = { "/useremail" })
public class UserEmailController extends BaseController {
	@Autowired
	private IUserService userService;

	@RequestMapping("/{type}/{username}")
	public ModelAndView toEmailPage(@PathVariable("type") String type,
			@PathVariable("username") String username) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("username", username);
		if (type.equals("realname")) {
			mv.setViewName("useremail/realname");
		} else if (type.equals("birthday")) {
			mv.setViewName("useremail/birthday");
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			UserWithBLOBs u = this.userService.selectByUserName(username);
			String birthday = year + "-" + u.getBirthday();
			mv.addObject("birthday", birthday);
		} else if (type.equals("callback")) {
			mv.setViewName("useremail/callback");
		} else if (type.equals("expire")) {
			mv.setViewName("useremail/expire");
		} else if (type.equals("newuser")) {
			mv.setViewName("useremail/newuser");
		} else if (type.equals("changeaddress")) {
			mv.setViewName("useremail/changeaddress");
		}else if (type.equals("addnewcard")) {
			mv.setViewName("useremail/addnewcard");
		} else if (type.equals("repayment")) {
			mv.setViewName("useremail/repayment");
		} else if (type.equals("accountopen")) {
			mv.setViewName("useremail/accountopen");
		} else if (type.equals("longtimenoinvest")) {
			mv.setViewName("useremail/longtimenoinvest");
		}else if (type.equals("longtimenologin")) {
			mv.setViewName("useremail/longtimenologin");
		}
		return mv;
	}

}
