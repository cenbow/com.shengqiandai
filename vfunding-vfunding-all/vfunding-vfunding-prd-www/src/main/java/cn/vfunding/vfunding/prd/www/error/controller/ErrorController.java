package cn.vfunding.vfunding.prd.www.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/error")
public class ErrorController {
	@RequestMapping(value = "/requestError", method = RequestMethod.GET)
	public ModelAndView error() {
		ModelAndView mv = new ModelAndView("error/requestError");
		return mv;
	}

}
