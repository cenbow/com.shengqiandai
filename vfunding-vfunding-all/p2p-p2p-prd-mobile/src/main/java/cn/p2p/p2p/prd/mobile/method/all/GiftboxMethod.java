package cn.p2p.p2p.prd.mobile.method.all;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.PageUtilVO;
import cn.p2p.p2p.prd.mobile.method.vo.GiftboxIndexVO;
import cn.p2p.p2p.prd.mobile.method.vo.HongbaoPageVO;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.page.PageSearch;
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
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

import com.alibaba.druid.util.StringUtils;

@Service
public class GiftboxMethod {

	@Autowired
	private IUserService userService;

	@Autowired
	private IHikesMessageService hikesMessageService;

	@Autowired
	private IHikesCardService hikesCardService;

	@Autowired
	private IGiftboxMessageService giftboxMessageService;

	@Autowired
	private ICashMessageService cashMessageService;
	@Autowired
	private IUserTokenService userTokenService;
	@Autowired
	private IGiftotherMessageService giftotherMessageService;

	
	/**
	 * 其他奖品详情页面
	 * 
	 * @param id
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse otherDetail(GeneralRequestVO generalRequest) {
		if (generalRequest.getId() == null | generalRequest.getId() < 1)
			return new MobileBaseResponse("otherId_fail", "其他礼品ID不正确");
		GiftotherMessage gm = this.giftotherMessageService.selectByPrimaryKey(generalRequest.getId());
		if (gm == null)
			return new MobileBaseResponse("other_null_fail", "礼品不存在");
		if (gm.getIsLook().equals(0)) {
			gm.setIsLook(1);
			this.giftotherMessageService.updateByPrimaryKeySelective(gm);
		}
		return new MobileBaseResponse(gm);
	}

	/**
	 * 其他奖品列表ajax
	 * 
	 * @param gm
	 * @param ps
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse otherList(GeneralRequestVO generalRequest) {
		Integer userId = userTokenService.selectUserIdByToken(generalRequest.getToken());
		PageSearch ps = new PageSearch();
		GiftotherMessage gm = new GiftotherMessage();
		gm.setReceiveUser(userId);
		ps.setEntity(gm);
		List<GiftotherMessage> list = this.giftotherMessageService.selectGiftboxMessageListPage(ps);
		if (list.isEmpty())
			return new MobileBaseResponse("list_null_fail", "暂无礼品");
		else
			return new MobileBaseResponse(list);
	}
	
	
	/**
	 * 抵扣详情页面
	 * 
	 * @param id
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse dikouDetail(GeneralRequestVO generalRequest) {
		if(generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("dikouId_fail", "抵扣券编号错误");
		CashMessage cm = this.cashMessageService.selectByPrimaryKey(generalRequest.getId());
		if (cm.getIsLook().equals(0)) {
			cm.setIsLook(1);
			this.cashMessageService.updateByPrimaryKeySelective(cm);
		}
		return new MobileBaseResponse(cm);
	}

	/**
	 * 抵扣列表页面
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse dikouPage(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		String cashHongbao = this.getCashHongbao(userId);
		return new MobileBaseResponse(cashHongbao);
	}

	/**
	 * 抵扣列表ajax
	 * 
	 * @param cm
	 * @param ps
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse dikouList(PageUtilVO pageUtil) {
		if(pageUtil.getPage() == null || pageUtil.getPage() < 1)
			return new MobileBaseResponse("page_fail", "页数不可小于1");
		PageSearch ps = new PageSearch();
		CashMessage cm = new CashMessage();
		Integer userId = this.userTokenService.selectUserIdByToken(pageUtil.getToken());
		ps.setRows(pageUtil.getRows());
		ps.setPage(pageUtil.getPage());
		cm.setReceiveUser(userId);
		ps.setEntity(cm);
		List<CashMessage> list = this.cashMessageService.selectCashMessageListPage(ps);
		return new MobileBaseResponse(list);
	}

	/**
	 * 加息详情页面
	 * 
	 * @param id
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse jiaxiDetail(GeneralRequestVO generalRequest) {
		if(generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("jiaxiId_fail", "加息卡编号不正确");
		HikesMessage hm = this.hikesMessageService.selectByPrimaryKey(generalRequest.getId());
		if (hm.getIsLook().equals(0)) {
			hm.setIsLook(1);
			this.hikesMessageService.updateByPrimaryKeySelective(hm);
		}
		return new MobileBaseResponse(hm);
	}

	/**
	 * 加息列表页面
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse jiaxiPage(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		String hikesUseRate = this.getHikesUseRate(userId);
		return new MobileBaseResponse(hikesUseRate);
	}

	/**
	 * 加息卡列表ajax
	 * 
	 * @param hm
	 * @param ps
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse jiaxiList(PageUtilVO pageUtil) {
		if(pageUtil.getPage() == null || pageUtil.getPage() < 1)
			return new MobileBaseResponse("page_fail","页数不可小于1");
		Integer userId = this.userTokenService.selectUserIdByToken(pageUtil.getToken());
		PageSearch ps = new PageSearch();
		ps.setPage(pageUtil.getPage());
		ps.setRows(pageUtil.getRows());
		HikesMessage hm = new HikesMessage();
		hm.setReceiveUser(userId);
		ps.setEntity(hm);
		List<HikesMessage> hmlist = this.hikesMessageService.selectHikesMessageListPage(ps);
		return new MobileBaseResponse(hmlist);
	}

	/**
	 * 红包详情页面
	 * 
	 * @param id
	 * @return
	 * @author louchen 2014-02-05
	 */
	@CheckToken
	public MobileBaseResponse hongbaoDetail(GeneralRequestVO generalRequest) {
		if (generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("hongbaoId_fail", "红包编号不正确");
		GiftboxMessage gm = this.giftboxMessageService.selectByPrimaryKey(generalRequest.getId());
		if (gm.getIsLook().equals(0)) {
			gm.setIsLook(1);
			this.giftboxMessageService.updateByPrimaryKeySelective(gm);
		}
		return new MobileBaseResponse(gm);
	}

	/**
	 * 红包列表页面
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse hongbaoPage(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		String sumUseMoney = this.getGiftBoxSum(userId);
		String sumNoUseMoney = this.getGiftBoxNoUseMoney(userId);
		String sumExpire = this.getGiftBoxSumExpire(userId);
		String sumUsed = this.getGiftBoxSumUsed(userId);
		return new MobileBaseResponse(this.setHbpValue(sumUseMoney, sumNoUseMoney, sumExpire, sumUsed));
	}

	private HongbaoPageVO setHbpValue(String sumUseMoney, String sumNoUseMoney, String sumExpire, String sumUsed) {
		HongbaoPageVO responseObject = new HongbaoPageVO();
		responseObject.setSumUseMoney(sumUseMoney);
		responseObject.setSumNoUseMoney(sumNoUseMoney);
		responseObject.setSumExpire(sumExpire);
		responseObject.setSumUsed(sumUsed);
		return responseObject;
	}
	
	
	
	
	/**
	 * 红包列表ajax
	 * 
	 * @param gm
	 * @param ps
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse hongbaoList(PageUtilVO pageUtil) {
		if (pageUtil.getStatus() == null || pageUtil.getStatus() < 0)
			return new MobileBaseResponse("status_fail", "红包状态错误");
		if (pageUtil.getPage() == null || pageUtil.getPage() < 1)
			return new MobileBaseResponse("page_fail", "页数不可小于1");
		GiftboxMessage gm = new GiftboxMessage();
		PageSearch ps = new PageSearch();
		gm.setStatus(pageUtil.getStatus()); // 缺省未使用
		gm.setReceiveUser(this.userTokenService.selectUserIdByToken(pageUtil.getToken()));
		ps.setEntity(gm);
		List<GiftboxMessage> gmlist = this.giftboxMessageService.selectGiftboxMessageListPage(ps);
		return new MobileBaseResponse(gmlist);
	}

	/**
	 * 礼品盒首页
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse index(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		String sumUseMoney = this.getGiftBoxSum(userId);
		String hikesUseRate = this.getHikesUseRate(userId);
		String cashHongbao = this.getCashHongbao(userId);
		boolean giftFlag = this.getHongbaoUnLookCount(userId) > 0 ? true : false;
		boolean jiaxiFlag = this.getJiaxiUnLookCount(userId) > 0 ? true : false;
		boolean dikouFlag = this.getDikouUnLookCount(userId) > 0 ? true : false;
		boolean otherFlag = this.getOtherUnLookCount(userId) > 0 ? true : false;
		return new MobileBaseResponse(this.setGiValue(sumUseMoney, hikesUseRate, cashHongbao, giftFlag, jiaxiFlag, dikouFlag, otherFlag));
	}
	
	/**
	 * 登录用户的未查看的礼物数量
	 * 
	 * @return
	 */
	private Integer getHongbaoUnLookCount(Integer userId) {
		return this.giftboxMessageService.selectUnLookCountByUserId(userId);
	}

	/**
	 * 登录用户的未查看的礼物数量
	 * 
	 * @return
	 */
	private Integer getJiaxiUnLookCount(Integer userId) {
		return this.hikesCardService.selectUnLookCount(userId);
	}

	/**
	 * 登录用户的未查看的礼物数量
	 * 
	 * @return
	 */
	private Integer getDikouUnLookCount(Integer userId) {
		return this.cashMessageService.selectUnLookCountByUserId(userId);
	}

	/**
	 * 登录用户的未查看的礼物数量
	 * 
	 * @return
	 */
	private Integer getOtherUnLookCount(Integer userId) {
		return this.giftotherMessageService.selectUnLookCountByUserId(userId);
	}
	
	private GiftboxIndexVO setGiValue(String sumUseMoney, String hikesUseRate, String cashHongbao,boolean giftFlag,boolean jiaxiFlag,boolean dikouFlag,boolean otherFlag) {
		GiftboxIndexVO responseObject = new GiftboxIndexVO();
		responseObject.setSumUseMoney(sumUseMoney);
		responseObject.setHikesUseRate(hikesUseRate);
		responseObject.setCashHongbao(cashHongbao);
		responseObject.setOtherGift("");
		responseObject.setGiftFlag(giftFlag);
		responseObject.setHikesFlag(jiaxiFlag);
		responseObject.setCashFlag(dikouFlag);
		responseObject.setOtherFlag(otherFlag);
		return responseObject;
	}

	/**
	 * 使用红包
	 * 
	 * @param giftId
	 * @return
	 */
	@CheckToken
	public MobileBaseResponse useHongbao(GeneralRequestVO generalRequest) {
		if (generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("hongbaoId_fail", "红包编号错误");
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		GiftboxMessage gm = this.giftboxMessageService.selectByPrimaryKey(generalRequest.getId());
		if (gm == null)
			return new MobileBaseResponse("hongbao_null_fail", "红包信息不存在");
		if (!gm.getReceiveUser().equals(userId))
			return new MobileBaseResponse("hongbao_user_fail", "该红包并非此用户的红包");
		User user = this.userService.selectByPrimaryKey(userId);
		if (StringUtils.isEmpty(user.getRealStatus()) || !user.getRealStatus().equals("1"))
			return new MobileBaseResponse("hongbao_real_fail", "请实名认证后再使用红包");
		Json j = new Json();
		try {
			j = this.useGiftTrade(j, gm, userId);
			if (j.getSuccess())
				return new MobileBaseResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new MobileBaseResponse("hongbao_use_fail", "红包使用失败");
	}

	/**
	 * 使用红包(一键使用)
	 * 
	 * @param giftId
	 * @return
	 * @author louchen 2015-02-05
	 */
	@CheckToken
	public MobileBaseResponse useHongbaoAny(GeneralRequestVO generalRequest) {
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		User user = this.userService.selectByPrimaryKey(userId);
		if (StringUtils.isEmpty(user.getRealStatus()) || !user.getRealStatus().equals("1"))
			return new MobileBaseResponse("hongbao_real_fail", "请实名认证后再使用红包");
		Json json = new Json();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		List<GiftboxMessage> gms = this.giftboxMessageService.selectUseMoneyAny(map);
		if (gms.isEmpty())
			return new MobileBaseResponse("hongbaos_null_fail", "无可用红包");
		try {
			for (GiftboxMessage gm : gms) {
				this.useGiftTrade(json, gm, userId);
			}
			json.setSuccess(true);
			return new MobileBaseResponse();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new MobileBaseResponse("useHongbaoAny_fail", "红包使用错误,请稍后再试!");
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
	private Json useGiftTrade(Json json, GiftboxMessage gm, Integer userId) throws Exception {
		json = this.giftboxMessageService.useGiftTrade(gm.getId(), userId);
		return json;
	}

	/**
	 * 红包总金额,可用
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	private String getGiftBoxSum(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("time", new Date());
		String useMoney = giftboxMessageService.selectUseMoney(map);
		if (StringUtils.isEmpty(useMoney)) {
			useMoney = "0.00";
		}
		return useMoney;
	}

	/**
	 * 红包总金额,不可用
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	private String getGiftBoxNoUseMoney(Integer userId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("time", new Date());
		String noUseMoney = giftboxMessageService.selectNoUseMoney(map);
		if (StringUtils.isEmpty(noUseMoney)) {
			noUseMoney = "0.00";
		}
		return noUseMoney;
	}

	/**
	 * 红包过期总金额
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	private String getGiftBoxSumExpire(Integer userId) {
		String SumExpire = giftboxMessageService.selectHongbaoSumExpire(userId);
		if (StringUtils.isEmpty(SumExpire)) {
			SumExpire = "0.00";
		}
		return SumExpire;
	}

	/**
	 * 红包已使用总金额
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	private String getGiftBoxSumUsed(Integer userId) {
		String SumUsed = giftboxMessageService.selectHongbaoSumUsed(userId);
		if (StringUtils.isEmpty(SumUsed)) {
			SumUsed = "0.00";
		}
		return SumUsed;
	}

	/**
	 * 总提现抵扣券
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	private String getCashHongbao(Integer userId) {
		User u = userService.selectByPrimaryKey(userId);
		if (u == null || u.getHongbao() == null) {
			return "0.00";
		} else {
			return String.valueOf(u.getHongbao().doubleValue());
		}
	}

	/**
	 * 加息卡总利息
	 * 
	 * @param hm
	 * @return
	 * @author jianwei
	 */
	private String getHikesUseRate(Integer userId) {
		HikesCard hc = hikesCardService.selectByPrimaryKey(userId);
		if (hc == null) {
			return "0.00";
		} else {
			return String.valueOf(hc.getUseRate().doubleValue());
		}
	}

	/**
	 * 登录用户的未使用的礼物数量
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	private Integer getUnUseCount() {
		return this.giftboxMessageService.selectUnUseCountByUserId(UserSession.getLoginUserId());
	}

	/**
	 * 登录用户的未查看的礼物数量
	 * 
	 * @return
	 * @author louchen 2015-02-05
	 */
	private Integer getUnLookCount() {
		return this.giftboxMessageService.selectUnLookCountByUserId(UserSession.getLoginUserId());
	}

}
