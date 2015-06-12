package cn.vfunding.vfunding.prd.bms.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Area;
import cn.vfunding.vfunding.biz.system.service.IAreaService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserInfoService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.user.service.IUserTypeService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping(value = "/system/userMan")
public class UserManController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserInfoService infoService;
	@Autowired
	private IUserTypeService typeService;
	@Autowired
	private IAreaService iAreaService;

	/**
	 * 跳转用户列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userList")
	public ModelAndView userList() {
		ModelAndView mv = new ModelAndView("webpage/user/userListAdd");
		boolean canEdit = UserAuthFilter.isPass("/system/userMan/editorUser");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		return mv;
	}

	/**
	 * 用户列表查询
	 * 
	 * @return
	 */
	@ParentSecurity("/system/userMan/userList")
	@RequestMapping(value = "/findUserList")
	@ResponseBody
	public PageResult<User> findUserList(PageSearch pageSearch,
			String userName, String email, String realName, String typeId,String phone) {
		PageResult<User> result = new PageResult<User>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", userName);
		map.put("email", email);
		map.put("realName", realName);
		map.put("typeId", typeId);
		map.put("phone", phone);
		pageSearch.setEntity(map);
		List<User> users = userService.selectFriendByUserListPage(pageSearch);
		result.setRows(users);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * 根据用户id查出用户信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/editorUser")
	public ModelAndView editorUser(Integer id) {
		ModelAndView mv = new ModelAndView("webpage/user/userEdit");
		User user = userService.selectByPrimaryKey(id);
		List<Area> areas = iAreaService.selectByParentId(0);
		String birthday = user.getBirthday();
		mv.addObject("birthday", birthday);
		mv.addObject("user", user);
		mv.addObject("areas", areas);
		return mv;
	}

	/**
	 * 修改用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@NeedLogger(desc = "修改用户信息")
	@RequestMapping("updateUser")
	@ResponseBody
	public Json updateUser(int id, String sh, String hl, String xl,
			String password, String realname, String sex, String birthday,
			Integer typeId, String inviteUserid, String cardType, String email,
			String qq, String wangwang, String phone, String address,
			String userStatus) throws Exception {
		Json json = new Json();
		User user = userService.selectByPrimaryKey(id);
		if (password != "" || password != null) {
			String userPwd = DigestUtils.md5Hex(password);
			user.setPassword(userPwd);
		}
		if (sh != "" || sh != null && hl != "" || hl != null && xl != ""
				|| xl != null) {
			user.setCardId(sh);
			user.setCardPic1(hl);
			user.setCardPic2(xl);
		}
		user.setUserStatus(userStatus);
		user.setAddress(address);
		user.setBirthday(birthday);
		user.setRealname(realname);
		user.setSex(sex);
		user.setTypeId(typeId);
		user.setInviteUserid(inviteUserid);
		user.setCardType(cardType);
		user.setEmail(email);
		user.setQq(qq);
		user.setWangwang(wangwang);
		user.setPhone(phone);
		int userNum = userService.updateByPrimaryKey(user);
		if (userNum > 0) {
			json.setMsg("修改成功！");
			json.setSuccess(true);
		} else {
			json.setMsg("修改失败！");
		}
		return json;
	}

	/**
	 * 当用户选择一个省的时候显示出底下市的信息
	 * 
	 * @return
	 */
	@ParentSecurity("/system/userMan/userList")
	@RequestMapping("city")
	@ResponseBody
	public List<Area> city(Integer id) {
		List<Area> areas = iAreaService.selectByParentId(id);
		return areas;
	}

}
