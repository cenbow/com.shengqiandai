package cn.vfunding.vfunding.prd.www.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.common.framework.utils.kaptcha.VerifyCodeUtils;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.common.vo.CjdRegVO;
import cn.vfunding.vfunding.biz.common.vo.CjdVO;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.message.dao.SystemMessageMapper;
import cn.vfunding.vfunding.biz.session.utils.HttpSessionTool;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.service.ILockRecordService;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;
import cn.vfunding.vfunding.biz.thirdplat.service.ICashVolumeService;
import cn.vfunding.vfunding.biz.thirdplat.service.IThirdRelationshipService;
import cn.vfunding.vfunding.biz.user.model.UserCache;
import cn.vfunding.vfunding.biz.user.model.UserType;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserCacheService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.user.service.IUserTypeService;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
import cn.vfunding.vfunding.biz.week.service.IWeekBorrowService;
import cn.vfunding.vfunding.biz.week.service.IWeekService;
import cn.vfunding.vfunding.common.thirdconfig.CjdaoConfig;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping(value = "/cjdao")
public class CjdController extends BaseController {
	@Autowired
	private IUserService userService;
	@Autowired
	private IBorrowService borrowService;
	@Autowired
	private ILockRecordService lockRecordService;
	@Autowired
	private ICashVolumeService cashVolumeService;
	@Autowired
	private IWeekService weekService;
	@Autowired
	private IWeekBorrowService weekBorrowService;

	@Autowired
	private IThirdRelationshipService thirdRelationshipService;


	@Autowired
	private SystemMessageMapper systemMessageMapper;
	
	@Value("${CjDao.Actvity1Start}")
	private String CjDao_ActvityOneStart;
	
	@Value("${CjDao.Actvity1End}")
	private String CjDao_ActvityOneEnd;
	
	/**
	 * 财经道请求注册 接口
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "regFromCjd")
	public ModelAndView regFromCjd(CjdRegVO reg, HttpSession httpSession) {
		if (EmptyUtil.isNotEmpty(reg.getThirdproductid())
				&& EmptyUtil.isNotEmpty(reg.getProductid())
				&& EmptyUtil.isNotEmpty(reg.getMoney())) {
			ModelAndView mv = null;
			if (reg.getThirdproductid() >= 500000) {
				Week week = weekService.selectByPrimaryKey(reg
						.getThirdproductid());
				// 周盈宝 对应标的查询
				List<WeekBorrow> weekBorrowList = this.weekBorrowService
						.selectWeekBorrowByWeekId(reg.getThirdproductid());
				// 该用户已存在，跳转登录
				if (userService.selectByUserName(reg.getUaccount()) != null
						|| userService.checkRegister(reg.getPhone()) == false) {
					// session存在直接登录至标详情页
					if (EmptyUtil.isNotEmpty(UserSession.getUserSession())
							&& (UserSession.getUserSession().getPhone()
									.equals(reg.getPhone()))) {
						return new ModelAndView("redirect:/week/weekDetail/"
								+ reg.getThirdproductid());
					}
					HttpSessionTool.doOut(httpSession);

					mv = new ModelAndView("cjd-loginSecond");
					mv.addObject("week", week);
					mv.addObject("weekBorrowList", weekBorrowList);
					mv.addObject("reg", reg);
					return mv;
				}
				mv = new ModelAndView("cjd-regSecond");// 注册投资页面
				// 检测加密是否被篡改
				if (EmptyUtil.isEmpty(reg.getMd5_value())
						|| !reg.getMd5All(CjdaoConfig.getInstance().getKey()).equals(
								reg.getMd5_value())) {
					return new ModelAndView("error/500");
				}
				mv.addObject("week", week);
				mv.addObject("weekBorrowList", weekBorrowList);
				reg.setType(2);
				mv.addObject("reg", reg);
			} else {
				Borrow b = borrowService.selectBorrowById(reg
						.getThirdproductid());
				// 该用户已存在，跳转登录
				if (userService.selectByUserName(reg.getUaccount()) != null
						|| userService.checkRegister(reg.getPhone()) == false) {
					// session存在直接登录至标详情页
					if (EmptyUtil.isNotEmpty(UserSession.getUserSession())
							&& (UserSession.getUserSession().getPhone()
									.equals(reg.getPhone()))) {
						return new ModelAndView(
								"redirect:/borrow/borrowDetail/"
										+ reg.getThirdproductid());
					}
					HttpSessionTool.doOut(httpSession);

					mv = new ModelAndView("cjd-loginSecond");
					mv.addObject("b", b);
					mv.addObject("reg", reg);
					return mv;
				}
				mv = new ModelAndView("cjd-regSecond");// 注册投资页面
				// 检测加密是否被篡改
				if (EmptyUtil.isEmpty(reg.getMd5_value())
						|| !reg.getMd5All(CjdaoConfig.getInstance().getKey()).equals(
								reg.getMd5_value())) {
					return new ModelAndView("error/500");
				}
				mv.addObject("b", b);
				reg.setType(2);
				mv.addObject("reg", reg);
			}
			return mv;
		} else {
			// 该用户已存在，跳转 快速登录
			if (userService.selectByUserName(reg.getUaccount()) != null
					|| userService.checkRegister(reg.getPhone()) == false) {
				// session存在直接登录至标详情页
				if (EmptyUtil.isNotEmpty(UserSession.getUserSession())
						&& UserSession.getUserSession().getPhone()
								.equals(reg.getPhone())) {
					return new ModelAndView("redirect:/user/account");
				}
				HttpSessionTool.doOut(httpSession);
				ModelAndView mv = new ModelAndView("cjd-loginFirst");
				mv.addObject("reg", reg);
				return mv;
			}
			ModelAndView mv = new ModelAndView("cjd-regFirst");// 快速注册页面
			// 检测加密是否被篡改
			if (EmptyUtil.isEmpty(reg.getMd5_value())
					|| !reg.getMd5Reg(CjdaoConfig.getInstance().getKey()).equals(
							reg.getMd5_value())) {
				return new ModelAndView("error/500");
			}
			reg.setType(1);
			mv.addObject("reg", reg);
			return mv;
		}
	}

	/**
	 * 注册
	 * 
	 * @author liuhuan
	 * @throws Exception
	 */
	@RequestMapping(value = "/handleRegFromCjd")
	@ResponseBody
	public Json handleRegFromCjd(CjdRegVO reg, String password2,
			HttpServletRequest request, HttpSession session) throws Exception {
		Json j = new Json();

		if (EmptyUtil.isEmpty(reg.getUsername())) {
			j.setMsg("用户名不能为空.");
		} else if (userService.selectByUserName(reg.getUaccount()) != null) {
			j.setMsg("用户名已存在，请重新输入.");
		} else if (EmptyUtil.isEmpty(reg.getPassword())) {
			j.setMsg("密码不能为空.");
		} else if (!reg.getPassword().equals(password2)) {
			j.setMsg("两次输入密码不一致.");
		} else if (EmptyUtil.isEmpty(reg.getPhone())) {
			j.setMsg("手机号不能为空.");
		} else if (userService.checkRegister(reg.getPhone()) == false) {
			j.setMsg("手机号已存在，请重新输入.");
		} else if (EmptyUtil.isEmpty(reg.getVcode())
				|| !VerifyCodeUtils.check(session, reg.getVcode())) {
			j.setMsg("手机验证码输入有误.");
		} else {
			RegisterVO register = new RegisterVO();
			register.setUserName(reg.getUsername());
			register.setPassword(reg.getPassword());
			register.setMobile(reg.getPhone());

			CjdVO vo = new CjdVO();
			vo.setUaccount(reg.getUaccount());

			Integer userId = this.userService.registerForCjd(register, vo,
					request.getRemoteAddr(), 1, "www.cjdao.com");
			if (EmptyUtil.isEmpty(userId)) {
				j.setMsg("注册失败，请刷新重试.");
				return j;
			}
			// 创建登录session
			UserWithBLOBs user = this.userService.selectByPrimaryKey(userId);
			UserSession userSession = this.createSessionByLoginedUser(user);
			HttpSessionTool.doLogin(request.getSession(), userSession);// session处理
			// 注册验证码处理
			HttpSessionTool.removeRegisteringUserInfo(request.getSession());// 删除session中注册的用户名信息
			HttpSessionTool.removeVerifyCodeInfo(request.getSession());// 删除session中验证码请求记录信息
			VerifyCodeUtils.removeVerifyCode(request.getSession());// 删除session中的验证码信息
			// type: 1跳转个人中心；2跳转指定 标 详情
			if (reg.getType() == 2) {
				j.setSuccess(true);
				if (reg.getThirdproductid() >= 500000) {
					j.setMsg("/week/weekDetail/" + reg.getThirdproductid());
				} else {
					j.setMsg("/borrow/borrowDetail/" + reg.getThirdproductid());
				}
			} else {
				j.setSuccess(true);
				j.setMsg("/user/account");
			}
		}
		return j;
	}

	public static void main(String[] args) throws Exception {

		// 9591455139e79e6ed79b7e71c73d255d
		System.out.println(DigestUtils.md5Hex("ll258538429" + "13774326784"
				+ "2057" + "cjdao_third1122*#"));
		// 13817504655
		// 33b2b6d2e068e16d0705749353969dfa
		String productId = "1";
		// System.out.println(DigestUtils.md5Hex("liuhuan" + "13774326783" +
		// "2073" + "1008" + productId + "50.0" + "1" + "1234"));
		//
		// System.out.println(DigestUtils.md5Hex("moneyqian" + "13817504655" +
		// "340822199003053716" + "0" + "2073" + "737" + "东风日产奇骏抵押借款" +
		// "2014-10-09"
		// + "30000.00" + "0.123" + "1085.40" + "2" + "6305" + "30000.00"));
		//
		// System.out.println(DigestUtils.md5Hex("kcb_001" + "13512154662" +
		// "abc001@v.com" + "123123" + "cjdao"+"1234"));
		//
		// System.out.println(DigestUtils.md5Hex("moneyqian" + "13817504655" +
		// "abc002@v.com" + "123123" + "cjdao"+"1234"));
		//
		// System.out.println(DigestUtils.md5Hex("huanliu" + "14774326783" +
		// "abc004@v.com" + "123123" + "cjdao"+"1234"));

		System.out.println(DigestUtils.md5Hex("moneyqian" + "13817504655"
				+ "123123" + "cjdao" + "1234"));
	}

	/**
	 * 注册送红包接口
	 * 
	 * @author liuhuan
	 * @throws Exception
	 */
	@RequestMapping(value = "/regSendCash")
	@ResponseBody
	public void regSendCash(CjdRegVO reg, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		StringBuffer str = new StringBuffer();
		CjdVO result = new CjdVO();
		String msg = "";
//		if (true) {
//			msg = "活动已结束";
//			result.setCode("1");
//		} 
		if (EmptyUtil.isEmpty(reg.getUaccount())) {
			msg = "用户名不能为空.";
			result.setCode("1");
		} else if (StringUtil.isChinese(reg.getUaccount())) {
			msg = "用户名格式不正确.";
			result.setCode("1");
		} else if (reg.getUaccount().length() < 6
				|| reg.getUaccount().length() > 18) {
			msg = "用户名长度不正确.";
			result.setCode("1");
		} else if (!userService.checkRegister(reg.getUaccount())) {
			msg = "用户名已存在.";
			result.setCode("2");
		} else if (EmptyUtil.isEmpty(reg.getPhone())) {
			msg = "手机号不能为空.";
			result.setCode("1");
		} else if (!userService.checkRegister(reg.getPhone())) {
			msg = "手机号已存在.";
			result.setCode("2");
		} 
//		else if (EmptyUtil.isEmpty(reg.getEmail())) { msg = "邮箱不能为空.";
//		  result.setCode("1"); 
//		} else if (EmptyUtil.isNotEmpty(reg.getEmail())
//				&& !userService.checkRegister(reg.getEmail())) {
//			msg = "邮箱已存在.";
//			result.setCode("2");
//		} 
		else if (EmptyUtil.isEmpty(reg.getPassword())) {
			msg = "密码不能为空.";
			result.setCode("1");
		} else if (EmptyUtil.isEmpty(reg.getSource())) {
			msg = "来源不能为空.";
			result.setCode("1");
		} else if (EmptyUtil.isEmpty(reg.getMd5_value())
				|| !reg.getMd5Cash(CjdaoConfig.getInstance().getKey()).equals(reg.getMd5_value())) {
			msg = "签名错误.";
			result.setCode("1");
		} else {
			RegisterVO register = new RegisterVO();
			register.setUserName(reg.getUaccount());
			register.setPassword(reg.getPassword());
			register.setMobile(reg.getPhone());
			register.setEmail(reg.getEmail());
			register.setCjd(true);
			CjdVO vo = new CjdVO();
			vo.setUaccount(reg.getUaccount());
			Integer userId = this.userService.registerForCjd(register, vo,
					request.getRemoteAddr(), 3, "www.cjdao.com");
			if (EmptyUtil.isEmpty(userId)) {
				msg = "注册失败，系统异常.";
				result.setCode("1");
			} else {
				msg = "成功";
				result.setCode("0");
			}
		}
		result.setMessage(msg);
		str.append("cjdaoCallback(");
		str.append(JSON.toJSONString(result));
		str.append(")");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(str.toString());

	}

	@Autowired
	private IUserTypeService typeService;
	@Autowired
	private IUserCacheService userCacheService;

	/**
	 * 根据已登录的用户创建登录session对象
	 * 
	 * @param user
	 * @return 2014年4月25日 liuyijun
	 */
	public UserSession createSessionByLoginedUser(UserWithBLOBs user) {
		if (EmptyUtil.isNotEmpty(user)) {
			UserSession userSession = new UserSession();
			if (EmptyUtil.isNotEmpty(user.getEmail())) {
				userSession.setEmail(user.getEmail());
			}
			// userSession.setUserId(userId);
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
			if (EmptyUtil.isNotEmpty(user.getTypeId())) {
				UserType type = this.typeService.selectByPrimaryKey(user
						.getTypeId());
				if (EmptyUtil.isNotEmpty(type)
						&& EmptyUtil.isNotEmpty(type.getName())) {
					userSession.setTypeName(type.getName());
				}
			}
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

			// 查询第三方用户信息
			ThirdRelationship tr = this.thirdRelationshipService
					.selectByUserIdType(user.getUserId(), null);
			if (EmptyUtil.isNotEmpty(tr) && tr.getId() > 0) {
				userSession.setIsThirdUser(1);
			} else {
				userSession.setIsThirdUser(0);
			}

			return userSession;
		} else {
			return null;
		}
	}

}
