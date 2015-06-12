package cn.vfunding.vfunding.prd.bms.giftbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.service.IGiftboxMessageService;
import cn.vfunding.vfunding.biz.trial.vo.GiftboxSearchVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Controller
@RequestMapping("/giftboxBms")
public class GiftboxController {
	@Autowired
	private IGiftboxMessageService giftboxMessageService;
	@Autowired
	private IUserService userService;
	
	
	@RequestMapping("/giftboxListPage")
	public ModelAndView giftboxListPage(){
		return new ModelAndView("webpage/giftbox/giftboxListPage");
	}
	
	@RequestMapping("/giftboxList")
	@ResponseBody
	public PageResult<GiftboxMessage> giftboxList(GiftboxSearchVO giftboxSearchVO,PageSearch pageSearch){
		PageResult<GiftboxMessage> results = new PageResult<GiftboxMessage>();
		pageSearch.setEntity(giftboxSearchVO);
		List<GiftboxMessage> gmList = giftboxMessageService.selectAll(pageSearch);
		if (!gmList.isEmpty()) {
			for (GiftboxMessage gm : gmList) {
				User u = userService.selectByPrimaryKey(gm.getReceiveUser());
				if(u != null){
					gm.setReceiveUserName(u.getUsername());
				}
			}
		}
		results.setRows(gmList);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}
	
}
