package cn.vfunding.vfunding.prd.bms.article.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.model.UserFrom;
import cn.vfunding.vfunding.biz.user.service.IUserFromService;

@Controller
@RequestMapping(value = "/userFrom")
public class UserFromController {
	@Autowired
	private IUserFromService userFromService;

	/**
	 * 用户来源列表
	 * 
	 * @author lilei
	 */
	@RequestMapping(value = "/toListPage")
	public ModelAndView articleListPage() {
		ModelAndView mv = new ModelAndView("webpage/userfrom/userFromList");
		// boolean canEdit =
		// UserAuthFilter.isPass("/userFrom/userFromInfoListPage");
		// if (canEdit) {
		// mv.addObject("canEdit", canEdit);
		// }
		// boolean canDelete = UserAuthFilter.isPass("/system/article/delete");
		// mv.addObject("canDelete", canDelete);
		return mv;
	}

	@ParentSecurity("/userFrom/toListPage")
	@RequestMapping(value = "/userFromListPage")
	@ResponseBody
	public PageResult<UserFrom> articleList(PageSearch pageSearch, String startTime, String endTime) {
		PageResult<UserFrom> results = new PageResult<UserFrom>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		pageSearch.setEntity(map);
		List<UserFrom> userFrom = userFromService.selectFromUserInfoListPage(pageSearch);
		results.setRows(userFrom);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

}
