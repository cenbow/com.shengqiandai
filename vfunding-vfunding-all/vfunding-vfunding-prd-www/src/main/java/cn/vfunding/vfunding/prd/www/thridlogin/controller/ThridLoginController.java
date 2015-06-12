package cn.vfunding.vfunding.prd.www.thridlogin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.session.utils.HttpSessionTool;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.model.UserTrack;
import cn.vfunding.vfunding.biz.system.service.IUserTrackService;
import cn.vfunding.vfunding.biz.thirdlogin.model.ThirdLogin;
import cn.vfunding.vfunding.biz.thirdlogin.service.IThirdLoginService;
import cn.vfunding.vfunding.biz.user.model.UserCache;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserCacheService;
import cn.vfunding.vfunding.biz.user.service.IUserService;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.qzone.UserInfoBean;

@Controller
@RequestMapping("/thirdLogin")
public class ThridLoginController extends BaseController {

	@Autowired
	private IThirdLoginService thridLoginService;

	@Autowired
	private IUserCacheService userCacheService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserTrackService trackService;

	/**
	 * 第三方授权成功后转向注册页面
	 * 
	 * @return 2014年6月6日 liuyijun
	 */
	@RequestMapping("/thirdBind")
	public ModelAndView thirdBind(
			@RequestParam(value = "qqNickname", required = false) String qqNickname) {
		ModelAndView mv = new ModelAndView("thirdBind");
		if (EmptyUtil.isNotEmpty(qqNickname)) {
			mv.addObject("qqNickname", qqNickname);
		}
		return mv;
	}

	/**
	 * 第三方授权成功后转向绑定页面
	 * 
	 * @return 2014年6月6日 liuyijun
	 */
	@RequestMapping("/thirdRegister")
	public ModelAndView thirdRegister(
			@RequestParam(value = "qqNickname", required = false) String qqNickname) {
		ModelAndView mv = new ModelAndView("thirdRegister");
		if (EmptyUtil.isNotEmpty(qqNickname)) {
			mv.addObject("qqNickname", qqNickname);
		}
		return mv;
	}

	/**
	 * 新浪微博登录
	 * 
	 * @throws WeiboException
	 *             2014年6月6日 liuyijun
	 */
	@RequestMapping("/sinaLogin")
	public ModelAndView toSinaLogin() throws WeiboException {
		ModelAndView mv = new ModelAndView();
		Oauth oauth = new Oauth();
		BareBonesBrowserLaunch.openURL(oauth.authorize("code","","all"));
		String url = "redirect:https://api.weibo.com/oauth2/authorize?client_id="
				+ "3691833547"
				+ "&redirect_uri="
				+ "http://www.vfunding.cn/thirdLogin/sinaCallBack"
				+ "&response_type=code&state=&scope=all";
		mv.setViewName(url);
		return mv;
	}
	
//	@RequestMapping("/sinaLogin")
//	@ResponseBody
//	public void toSinaLogin() throws WeiboException {
//		Oauth oauth = new Oauth();
//		BareBonesBrowserLaunch.openURL(oauth.authorize("code","","all"));
//	}

	/**
	 * 新浪登录回调地址
	 * 
	 * @param code
	 * @return
	 * @throws Exception
	 *             2014年6月6日 liuyijun
	 */
	@RequestMapping("/sinaCallBack")
	public ModelAndView sinaCallBack(@RequestParam(value = "code") String code,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Oauth oauth = new Oauth();
		AccessToken token ;
		try {
			token = oauth.getAccessTokenByCode(code);
			this.doThirdLogin(token.getUid(), "sina", request, mv);
		} catch (WeiboException e) {
			mv.setViewName("redirect:http://www.vfunding.cn");
		}
		return mv;
	}

	@RequestMapping("/qqLogin")
	public void qqLogin(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			response.sendRedirect(new com.qq.connect.oauth.Oauth()
					.getAuthorizeURL(request));
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
	}

	/**
	 * QQ登录回调地址
	 * 
	 * @return 2014年6月6日 liuyijun
	 * @throws QQConnectException
	 */
	@RequestMapping("/qqCallBack")
	public ModelAndView qqCallBack(HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			QQConnectException {
		ModelAndView mv = new ModelAndView();
		com.qq.connect.javabeans.AccessToken accessTokenObj = (new com.qq.connect.oauth.Oauth())
				.getAccessTokenByRequest(request);
		String accessToken = null;
		String openID = null;

		if (accessTokenObj.getAccessToken().equals("")) {
			throw new BusinessException("8005029", "QQ登录授权信息错误");
		} else {
			accessToken = accessTokenObj.getAccessToken();
			
			OpenID openIDObj = new OpenID(accessToken);
			openID = openIDObj.getUserOpenID();
			UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
			if (EmptyUtil.isNotEmpty(qzoneUserInfo)) {
				UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
				if (EmptyUtil.isNotEmpty(userInfoBean)) {
					mv.addObject("qqNickname", userInfoBean.getNickname());
				}
			}
			this.doThirdLogin(openID, "qq", request, mv);
		}

		return mv;
	}

	/**
	 * 第三方登录后进行账户绑定
	 * 
	 * @param value
	 * @param password
	 * @param session
	 * @return 2014年6月6日 liuyijun
	 */
	@RequestMapping("/thirdBindUser")
	public ModelAndView thirdBindUser(String username, String password,
			HttpSession session) {
		ModelAndView mv = new ModelAndView("thirdBind");
		mv.addObject("value", username);
		mv.addObject("password", password);
		String account = (String) session
				.getAttribute("thirdLoginRegisterUserAccount");
		String category = (String) session
				.getAttribute("thirdLoginRegisterCategory");

		if (EmptyUtil.isNotEmpty(account) && EmptyUtil.isNotEmpty(category)) {
			List<UserWithBLOBs> users = this.userService
					.selectByLogin(username);
			if (EmptyUtil.isNotEmpty(users) && users.size() == 1) {
				if (DigestUtils.md5Hex(password).equals(
						users.get(0).getPassword())) {
					ThirdLogin search = new ThirdLogin(account, category);
					ThirdLogin login = this.thridLoginService
							.selectByLoginAccountAndCategory(search);
					if (EmptyUtil.isNotEmpty(login)) {
						login.setUserId(users.get(0).getUserId());
						this.thridLoginService.updateByPrimaryKey(login);
						UserSession userSession = this
								.createSessionByLoginedUser(users.get(0));
						HttpSessionTool.doLogin(session, userSession);
						mv.setViewName("redirect:/index");
					} else {
						mv.addObject("errorMsg", "第三方登录记录信息错误");
					}

				} else {
					mv.addObject("errorMsg", "密码错误");
				}
			} else {
				mv.addObject("errorMsg", "登录用户信息错误");
			}

		} else {
			mv.addObject("errorMsg", "第三方登录信息错误");
		}

		return mv;
	}

	/**
	 * 第三方登录通用方法
	 * 
	 * @param account
	 * @param category
	 * @param session
	 * @param mv
	 *            2014年6月7日 liuyijun
	 */
	private void doThirdLogin(String account, String category,
			HttpServletRequest request, ModelAndView mv) {
		ThirdLogin search = new ThirdLogin(account, category);
		ThirdLogin login = this.thridLoginService
				.selectByLoginAccountAndCategory(search);
		String lastIp =ModelAndViewUtil.getIpAddr(request);
		if (EmptyUtil.isNotEmpty(login)) {
			login.setLastLogin(new Date());
			if (EmptyUtil.isNotEmpty(login.getUserId())
					&& login.getUserId() != 0) {// 已经绑定用户了
				// 创建Session信息登录 转向首页
				mv.setViewName("redirect:/index");
				UserWithBLOBs user = this.userService.selectByPrimaryKey(login
						.getUserId());
				if (EmptyUtil.isNotEmpty(user)) {
					UserTrack track = this.trackService
							.selectByLastLogin(new UserTrack(user.getUserId()
									.toString(), "site"));
					if (EmptyUtil.isNotEmpty(track)
							&& EmptyUtil.isEmpty(track.getOutTime())) {
						if(!lastIp.equals(user.getLastip())){
							throw new BusinessException("8005032");
						}
					}
					
					if (EmptyUtil.isNotEmpty(user.getIslock())
							&& user.getIslock().intValue() == 1) {
						throw new BusinessException("8005031",
								"您的微积金账号已锁定，请联系客服");
					} else {
						UserSession userSession = this
								.createSessionByLoginedUser(user);
						this.userService.userLogin(user,request);
						HttpSessionTool.doLogin(request.getSession(),
								userSession);
					}

				} else {
					throw new BusinessException("8005028", "微博登录绑定的用户信息错误");
				}
			} else {
				// 转向第三方注册页面
				this.toThirdRegisterMethod(request.getSession(),
						search.getLoginAccount(), mv, category);
			}
			this.thridLoginService.updateByPrimaryKey(login);
		} else {
			// 新增登录信息
			search.setLastLogin(new Date());
			this.thridLoginService.insert(search);
			// 转向第三方注册页面
			this.toThirdRegisterMethod(request.getSession(),
					search.getLoginAccount(), mv, category);
		}
	}

	/**
	 * 转向第三方登录注册页面前处理
	 * 
	 * @param session
	 * @param account
	 * @param mv
	 * 2014年6月6日 liuyijun
	 */
	private void toThirdRegisterMethod(HttpSession session, String account,
			ModelAndView mv, String category) {
		session.setAttribute("thirdLoginRegisterUserAccount", account);
		session.setAttribute("thirdLoginRegisterCategory", category);
		mv.setViewName("redirect:/thirdLogin/thirdRegister");
	}

	/**
	 * 根据已登录的用户创建登录session对象
	 * 
	 * @param user
	 * @return 2014年4月25日 liuyijun
	 */
	private UserSession createSessionByLoginedUser(UserWithBLOBs user) {
		if (EmptyUtil.isNotEmpty(user)) {
			UserSession userSession = new UserSession();
			if (EmptyUtil.isNotEmpty(user.getEmail())) {
				userSession.setEmail(user.getEmail());
			}
			userSession.setPhone(user.getPhone());
			userSession
					.setPhoneStatus(EmptyUtil.isEmpty(user.getPhoneStatus()) ? "0"
							: user.getPhoneStatus());
			userSession.setPassword(user.getPassword());
			userSession
					.setPayPassword(EmptyUtil.isEmpty(user.getPaypassword()) ? user
							.getPassword() : user.getPaypassword());
			userSession.setUserId(user.getUserId());
			userSession.setUserName(user.getUsername());

			userSession
					.setEmailStatus(EmptyUtil.isEmpty(user.getEmailStatus()) ? "0"
							: user.getEmailStatus());
			userSession
					.setRealStatus(EmptyUtil.isEmpty(user.getRealStatus()) ? "0"
							: user.getRealStatus());
			userSession.setVideoStatus(user.getVideoStatus());
			userSession.setSceneStatus(user.getSceneStatus());
			if (EmptyUtil.isNotEmpty(user.getCardId())) {
				userSession.setCardId(user.getCardId());
			}

			if (EmptyUtil.isNotEmpty(user.getRealname())) {
				userSession.setRealName(user.getRealname());
			}
			userSession.setTypeId(user.getTypeId());
			if (EmptyUtil.isNotEmpty(user.getQuestion())) {
				userSession.setQuestion(user.getQuestion());
			}

			if (EmptyUtil.isNotEmpty(user.getAnswer())) {
				userSession.setAnswer(user.getAnswer());
			}

			UserCache cache = this.userCacheService.selectBaseInfoByUserId(user
					.getUserId());
			if (EmptyUtil.isNotEmpty(cache)
					&& EmptyUtil.isNotEmpty(cache.getVipStatus())) {
				userSession.setVipStatus(cache.getVipStatus());
			}

			if (EmptyUtil.isNotEmpty(user.getSex())
					&& user.getSex().equals("1")) {
				userSession.setSex("1");
			} else {
				userSession.setSex("0");
			}
			if (EmptyUtil.isNotEmpty(user.getAddress())) {
				userSession.setAddress(user.getAddress());
			}
			userSession
					.setUserIcon(EmptyUtil.isNotEmpty(user.getUserIcon()) ? user
							.getUserIcon() : null);

			return userSession;
		} else {
			return null;
		}
	}
	
}
