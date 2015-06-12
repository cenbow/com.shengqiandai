package cn.vfunding.vfunding.prd.www.message.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.hikes.service.IHikesCardService;
import cn.vfunding.vfunding.biz.message.model.CashMessage;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.model.GiftotherMessage;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;
import cn.vfunding.vfunding.biz.message.service.ICashMessageService;
import cn.vfunding.vfunding.biz.message.service.IGiftboxMessageService;
import cn.vfunding.vfunding.biz.message.service.IGiftotherMessageService;
import cn.vfunding.vfunding.biz.message.service.IHikesMessageService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.sina.service.IAccountTransferSinaService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;

import com.alibaba.druid.util.StringUtils;

@Controller
@RequestMapping(value = "/giftbox")
public class GiftboxController extends BaseController {
	@Autowired
	private IGiftboxMessageService giftboxMessageService;
	@Autowired
	private IGiftotherMessageService giftotherMessageService;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IAccountTransferSinaService accountTransferSinaService;
	@Autowired
	private IHikesMessageService hikesMessageService;
	@Autowired
	private ICashMessageService cashMessageService;
	@Autowired
	private IHikesCardService hikesCardService;
	@Autowired
	private IUserService userService;

	/**
	 * 跳转到礼品盒页面
	 * 
	 * @return
	 * @author louchen 2014-12-22
	 */
	@RequestMapping("/boxPage")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public ModelAndView boxPage() {
		ModelAndView mv = new ModelAndView("message/giftboxMessage");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		return mv;
	}

	/**
	 * 礼品其他列表
	 * 
	 * @param gm
	 * @param ps
	 * @return
	 * @author louchen 2015-04-01
	 */
	@RequestMapping("/giftotherList")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public PageResult<GiftotherMessage> giftotherList(GiftotherMessage gm, PageSearch ps) {
		gm.setReceiveUser(UserSession.getLoginUserId());
		ps.setRows(5);
		PageResult<GiftotherMessage> pr = new PageResult<GiftotherMessage>();
		ps.setEntity(gm);
		List<GiftotherMessage> gmlist = this.giftotherMessageService.selectGiftboxMessageListPage(ps);
		pr.setRows(gmlist);
		pr.setTotal(ps.getTotalResult());
		return pr;
	}

	/**
	 * 礼品列表
	 * 
	 * @param gm
	 * @return
	 * @author louchen 2014-12-22
	 */
	@RequestMapping("/boxList")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public PageResult<GiftboxMessage> boxList(GiftboxMessage gm, PageSearch ps) {
		if (EmptyUtil.isEmpty(gm.getStatus())) {
			gm.setStatus(0);
		}
		gm.setReceiveUser(UserSession.getLoginUserId());
		ps.setRows(5);
		PageResult<GiftboxMessage> pr = new PageResult<GiftboxMessage>();
		ps.setEntity(gm);
		List<GiftboxMessage> gmlist = this.giftboxMessageService.selectGiftboxMessageListPage(ps);
		pr.setRows(gmlist);
		pr.setTotal(ps.getTotalResult());
		return pr;
	}

	/**
	 * 加息卡列表
	 * 
	 * @param hm
	 * @return
	 * @author jianwei
	 */
	@RequestMapping("/hikesList")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public PageResult<HikesMessage> hikesList(HikesMessage hm, PageSearch ps, Model model) {
		hm.setReceiveUser(UserSession.getLoginUserId());
		ps.setRows(5);
		PageResult<HikesMessage> pr = new PageResult<HikesMessage>();
		ps.setEntity(hm);
		List<HikesMessage> hmlist = this.hikesMessageService.selectHikesMessageListPage(ps);
		pr.setRows(hmlist);
		pr.setTotal(ps.getTotalResult());
		return pr;
	}

	/**
	 * 加息卡总利息
	 * 
	 * @param hm
	 * @return
	 * @author jianwei
	 */
	@RequestMapping("/hikesUseRate")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public String hikesUseRate() {
		HikesCard hc = hikesCardService.selectByPrimaryKey(UserSession.getLoginUserId());
		if (hc == null || hc.getUseRate() == null) {
			return "0.00";
		} else {
			return String.valueOf(hc.getUseRate().doubleValue());
		}
	}

	/**
	 * 总提现券
	 * 
	 * @param hm
	 * @return
	 * @author jianwei
	 */
	@RequestMapping("/cashHongbao")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public String cashHongbao() {
		User u = userService.selectByPrimaryKey(UserSession.getLoginUserId());
		if (u == null || u.getHongbao() == null) {
			return "0.00";
		} else {
			return String.valueOf(u.getHongbao().doubleValue());
		}
	}

	/**
	 * 总红包
	 * 
	 * @param hm
	 * @return
	 * @author jianwei
	 */
	@RequestMapping("/giftBoxSum")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public String giftBoxSum() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", UserSession.getLoginUserId());
		map.put("time", new Date());
		String useMoney = giftboxMessageService.selectUseMoney(map);
		if (StringUtils.isEmpty(useMoney)) {
			return "0.00";
		} else {
			return useMoney;
		}
	}

	/**
	 * 总红包
	 * 
	 * @param hm
	 * @return
	 * @author jianwei
	 */
	@RequestMapping("/giftBoxNoUseMoney")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public String giftBoxNoUseMoney() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", UserSession.getLoginUserId());
		map.put("time", new Date());
		String useMoney = giftboxMessageService.selectNoUseMoney(map);
		if (StringUtils.isEmpty(useMoney)) {
			return "0.00";
		} else {
			return useMoney;
		}
	}

	/**
	 * 提现券列表
	 * 
	 * @param cm
	 * @return
	 * @author jianwei
	 */
	@RequestMapping("/cashList")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public PageResult<CashMessage> cashList(CashMessage cm, PageSearch ps) {
		cm.setReceiveUser(UserSession.getLoginUserId());
		ps.setRows(5);
		PageResult<CashMessage> pr = new PageResult<CashMessage>();
		ps.setEntity(cm);
		List<CashMessage> cmlist = this.cashMessageService.selectCashMessageListPage(ps);
		pr.setRows(cmlist);
		pr.setTotal(ps.getTotalResult());
		return pr;
	}

	/**
	 * 登录用户的未使用的礼物数量
	 * 
	 * @return
	 * @author louchen 2014-12-22
	 */
	@RequestMapping("/unUseCount")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public Integer unUseCount() {
		return this.giftboxMessageService.selectUnUseCountByUserId(UserSession.getLoginUserId());
	}

	/**
	 * 登录用户的未使用的礼物数量
	 * 
	 * @return
	 * @author louchen 2014-12-22
	 */
	@RequestMapping("/unLookCount")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public Integer unLookCount() {
		return this.giftboxMessageService.selectUnLookCountByUserId(UserSession.getLoginUserId());
	}

	/**
	 * 更新礼品盒查看状态
	 * 
	 * @param giftId
	 * @return
	 * @author louchen 2015-03-03
	 */
	@RequestMapping("/updateIsLook")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public Json updateIsLook(@RequestParam(value = "id", required = true) Integer id, @RequestParam(value = "type", required = false) String type) {
		Json json = new Json();
		if (EmptyUtil.isEmpty(type)) {
			GiftboxMessage gm = this.giftboxMessageService.selectByPrimaryKey(id);
			if (EmptyUtil.isNotEmpty(gm) && gm.getReceiveUser().equals(UserSession.getLoginUserId()) && gm.getIsLook().equals(0)) {
				this.giftboxMessageService.updateGiftIsLook(gm);
				json.setSuccess(true);
			}
		} else if (type.equals("jiaxi")) {
			HikesMessage hm = this.hikesMessageService.selectByPrimaryKey(id);
			if (EmptyUtil.isNotEmpty(hm) && hm.getReceiveUser().equals(UserSession.getLoginUserId()) && hm.getIsLook().equals(0)) {
				hm.setIsLook(1);
				this.hikesMessageService.updateByPrimaryKeySelective(hm);
				json.setSuccess(true);
			}
		} else if (type.equals("tixian")) {
			CashMessage cm = this.cashMessageService.selectByPrimaryKey(id);
			if (EmptyUtil.isNotEmpty(cm) && cm.getReceiveUser().equals(UserSession.getLoginUserId()) && cm.getIsLook().equals(0)) {
				cm.setIsLook(1);
				this.cashMessageService.updateByPrimaryKeySelective(cm);
				json.setSuccess(true);
			}
		} else if (type.equals("other")) {
			GiftotherMessage gm = this.giftotherMessageService.selectByPrimaryKey(id);
			if (EmptyUtil.isNotEmpty(gm) && gm.getReceiveUser().equals(UserSession.getLoginUserId()) && gm.getIsLook().equals(0)) {
				this.giftotherMessageService.updateGiftIsLook(gm);
				json.setSuccess(true);
			}
		}
		return json;
	}

	/**
	 * 使用礼品
	 * 
	 * @param giftId
	 * @return
	 * @author louchen 2014-12-22
	 */
	@RequestMapping("/useGift")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public Json useGift(@RequestParam(value = "giftId", required = true) Integer giftId) {
		Json json = new Json();
			if (!StringUtils.isEmpty(UserSession.getUserSession().getRealStatus()) && UserSession.getUserSession().getRealStatus().equals("1")) {
				try {
					json = this.useGiftTrade(json, giftId);
				} catch (Exception e) {
					e.printStackTrace();
					json.setMsg("红包使用错误,请稍后再试!");
				}
			} else {
				json.setMsg("请实名认证后再使用红包");
				json.setSuccess(false);
			}
		return json;
	}

	/**
	 * 使用礼品
	 * 
	 * @param giftId
	 * @return
	 * @author louchen 2014-12-22
	 */
	@RequestMapping("/useGiftAny")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public Json useGiftAny() {
		Json json = new Json();
		json.setSuccess(false);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", UserSession.getLoginUserId());
		map.put("time", new Date());
		List<GiftboxMessage> gms = this.giftboxMessageService.selectUseMoneyAny(map);
		try {
			if (!StringUtils.isEmpty(UserSession.getUserSession().getRealStatus()) && UserSession.getUserSession().getRealStatus().equals("1")) {
				if (!gms.isEmpty()) {
					for (GiftboxMessage gm : gms) {
						json = this.useGiftTrade(json, gm.getId());
					}
					if(StringUtils.isEmpty(json.getMsg())){
						json.setMsg("暂无红包可使用");
					}
					json.setSuccess(true);
				} else {
					json.setSuccess(false);
					json.setMsg("无可用红包");
				}
			} else {
				json.setMsg("请实名认证后再使用红包");
				json.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("红包使用错误,请稍后再试!");
		}
		return json;
	}

	/**
	 * 获取登录用户可用余额
	 * 
	 * @return
	 * @author louchen 2014-12-24
	 */
	@RequestMapping("/getUserAccountUseMoneyAjax")
	@ResponseBody
	@NeedSession(returnUrl = "/giftbox/boxPage")
	public BigDecimal getUserAccountUseMoney() {
		Account account = accountService.selectByUserId(UserSession.getLoginUserId());
		if (EmptyUtil.isNotEmpty(account) && account.getId() > 0) {
			return account.getUseMoney();
		} else {
			return new BigDecimal(0);
		}

	}

	/**
	 * 使用礼品红包
	 * 
	 * @param json
	 * @param gm
	 * @return
	 * @throws Exception
	 * @author louchen 2015-02-05
	 */
	private Json useGiftTrade(Json json, Integer giftId) throws Exception {
		GiftboxMessage gm = this.giftboxMessageService.selectByPrimaryKey(giftId);
		Long nowDate = new Date().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		Date takeTime = sdf.parse(sdf.format(gm.getTakeTime()));
		Date loseTime = sdf.parse(sdf.format(gm.getLoseTime()));
		if(nowDate < takeTime.getTime() || nowDate > loseTime.getTime()){
			json.setMsg("该红包未到使用时间");
			return json;
		}
		json = this.giftboxMessageService.useGiftTrade(giftId, UserSession.getLoginUserId());
		return json;
	}
}
