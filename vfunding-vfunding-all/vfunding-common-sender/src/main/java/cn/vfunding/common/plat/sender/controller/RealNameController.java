package cn.vfunding.common.plat.sender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.plat.sender.service.IRealNameService;
import cn.vfunding.common.plat.sender.vo.RealNameVO;

@Controller
@RequestMapping(value = "")
public class RealNameController {

	@Autowired
	private IRealNameService realNameService;

	@RequestMapping(value = "/realname/{name}/{cardId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean realname(@PathVariable("name") String name,
			@PathVariable("cardId") String cardId) throws Exception {
		RealNameVO vo = new RealNameVO(name, cardId);
		return this.realNameService.realName(vo);
	}

}
