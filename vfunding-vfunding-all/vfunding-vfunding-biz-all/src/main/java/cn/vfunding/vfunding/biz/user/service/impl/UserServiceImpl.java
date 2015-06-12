package cn.vfunding.vfunding.biz.user.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.p2p.p2p.biz.current.dao.CurrentAccountRuleMapper;
import cn.p2p.p2p.biz.current.dao.CurrentRedeemRuleMapper;
import cn.p2p.p2p.biz.current.dao.CurrentUserAccountMapper;
import cn.p2p.p2p.biz.current.model.CurrentAccountRule;
import cn.p2p.p2p.biz.current.model.CurrentRedeemRule;
import cn.p2p.p2p.biz.current.model.CurrentUserAccount;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.common.framework.utils.qrcode.QRCoderUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.service.IAccountFeelService;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowRepaymentMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowTenderMapper;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.CjdVO;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.common.vo.UnionUserBandVO;
import cn.vfunding.vfunding.biz.common.vo.UnionUserRegisterVO;
import cn.vfunding.vfunding.biz.common.vo.UserInfoVO;
import cn.vfunding.vfunding.biz.credit.dao.CreditMapper;
import cn.vfunding.vfunding.biz.credit.model.Credit;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.inviteCode.model.InviteCode;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.message.beanutil.MessageUtil;
import cn.vfunding.vfunding.biz.message.dao.SystemMessageMapper;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.dao.LockRecordMapper;
import cn.vfunding.vfunding.biz.system.dao.TaskMapper;
import cn.vfunding.vfunding.biz.system.dao.ThirdSynRecordMapper;
import cn.vfunding.vfunding.biz.system.dao.UserTaskMapper;
import cn.vfunding.vfunding.biz.system.dao.UserTrackMapper;
import cn.vfunding.vfunding.biz.system.model.LockRecord;
import cn.vfunding.vfunding.biz.system.model.Task;
import cn.vfunding.vfunding.biz.system.model.UserTrack;
import cn.vfunding.vfunding.biz.thirdlogin.dao.ThirdLoginMapper;
import cn.vfunding.vfunding.biz.thirdlogin.model.ThirdLogin;
import cn.vfunding.vfunding.biz.thirdplat.dao.CashVolumeMapper;
import cn.vfunding.vfunding.biz.thirdplat.dao.ThirdRelationshipMapper;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;
import cn.vfunding.vfunding.biz.user.dao.UserAmountMapper;
import cn.vfunding.vfunding.biz.user.dao.UserCacheMapper;
import cn.vfunding.vfunding.biz.user.dao.UserFromUnionMapper;
import cn.vfunding.vfunding.biz.user.dao.UserInfoMapper;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.dao.UserUnionMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.User2Sinamember;
import cn.vfunding.vfunding.biz.user.model.UserAmount;
import cn.vfunding.vfunding.biz.user.model.UserCache;
import cn.vfunding.vfunding.biz.user.model.UserInfo;
import cn.vfunding.vfunding.biz.user.model.UserUnion;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.usertoken.model.UserToken;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;
import cn.vfunding.vfunding.biz.week.dao.WeekRepaymentMapper;
import cn.vfunding.vfunding.biz.week.dao.WeekTenderMapper;
import cn.vfunding.vfunding.common.bean.util.BirthdayUtil;
import cn.vfunding.vfunding.common.module.activemq.message.RegisterMessage;
import cn.vfunding.vfunding.common.module.activemq.message.UnionUserRealNameMessage;
import cn.vfunding.vfunding.common.module.activemq.message.UserEmpMessage;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;
import cn.vfunding.vfunding.common.thirdconfig.CjdaoConfig;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Autowired
	private ThirdSynRecordMapper thirdSynRecordMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private UserAmountMapper userAmountMapper;
	@Autowired
	private UserCacheMapper userCacheMapper;
	@Autowired
	private UserUnionMapper userUnionMapper;

	@Autowired
	private UserFromUnionMapper userFromUnionMapper;
	@Autowired
	private CreditMapper creditMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private IAccountFeelService accountFeelService;
	@Autowired
	private TaskMapper taskMapper;
	@Autowired
	private UserTaskMapper userTaskMapper;
	@Autowired
	private ThirdLoginMapper thirdLoginMapper;

	@Autowired
	private HikesCardMapper hikesCardMapper;

	@Autowired
	private SystemMessageMapper systemMessageMapper;

	// @Resource(name = "jms.sender.union")
	// private JmsSender jmsSenderUnion;
	@Autowired
	private LockRecordMapper lockRecordMapper;
	@Autowired
	private UserTrackMapper trackMapper;
	@Autowired
	private ThirdRelationshipMapper thirdRelationshipMapper;
	@Autowired
	private BorrowRepaymentMapper borrowRepaymentMapper;
	@Autowired
	private BorrowTenderMapper borrowTenderMapper;
	@Autowired
	private CashVolumeMapper cashVolumeMapper;
	@Autowired
	private IInviteCodeService inviteCodeService;
	@Autowired
	private WeekRepaymentMapper weekRepaymentMapper;
	@Autowired
	private WeekTenderMapper weekTenderMapper;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Autowired
	private IUserTokenService userTokenService;

	/**
	 * 文件服务器
	 */
	@Resource(name = "fileRestInvokerFactory")
	private RestInvokerFactory fileRestInvokerFactory;

	@Override
	public UserWithBLOBs selectByPrimaryKey(Integer userId) {
		return this.userMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateUserCardImage(User record, List<MultipartEntityBuilder> multipartEntitys) {
		if (EmptyUtil.isNotEmpty(multipartEntitys) && multipartEntitys.size() == 2) {
			String picId1 = this.fileRestInvokerFactory.getRestInvoker().postFiles("upload?from=vfunding_www&width=600&heigth=336", multipartEntitys.get(0));

			String picId2 = this.fileRestInvokerFactory.getRestInvoker().postFiles("upload?from=vfunding_www&width=600&heigth=336", multipartEntitys.get(1));
			record.setCardPic1(picId1);
			record.setCardPic2(picId2);
			record.setRealStatus("2");// 改变状态
			return this.userMapper.updateUserCardImage(record);
		}
		return 0;
	}

	@Override
	public List<String> testUploadImage(List<MultipartEntityBuilder> multipartEntitys) {
		List<String> list = new ArrayList<String>();
		if (EmptyUtil.isNotEmpty(multipartEntitys) && multipartEntitys.size() == 2) {
			String picId1 = this.fileRestInvokerFactory.getRestInvoker().postFiles("upload?from=vfunding_www&width=600&heigth=336", multipartEntitys.get(0));
			list.add(picId1);
			String picId2 = this.fileRestInvokerFactory.getRestInvoker().postFiles("upload?from=vfunding_www&width=600&heigth=336", multipartEntitys.get(1));
			list.add(picId2);
		}
		return list;
	}

	@Override
	public UserWithBLOBs selectByUserName(String userName) {
		return this.userMapper.selectByUserName(userName);
	}

	/**
	 * 注册时用户的用户名、邮箱、手机号码唯一性检查
	 * 
	 * @param value
	 * @return 可注册时返回true
	 */
	@Override
	public boolean checkRegister(String value) {
		int result = 0;
		if (this.checkUserNameIsLegal(value) && !StringUtil.isChinese(value)) {
			result = this.userMapper.checkRegister(value);
		} else {
			return false;
		}
		return result > 0 ? false : true;
	}

	@Override
	public void unionUserRegister(UnionUserRegisterVO vo) {
		UserWithBLOBs user = new UserWithBLOBs();
		user.setEmail(vo.getUnionEmail());
		user.setSex(vo.getSex());
		user.setUsername(vo.getUnionUserName());
		user.setPassword(vo.getUnionPassword());
		user.setPhone(vo.getUnionMobile());
		int userId = this.baseInsertUser(user);
		UserUnion userUnion = new UserUnion();
		userUnion.setStatus("Y");
		userUnion.setUnionUserId(vo.getUnionUserId());
		userUnion.setUserId(userId);
		userUnion.setReviveNum(0);
		this.userUnionMapper.insert(userUnion);
	}

	/**
	 * 添加用户的Base方法
	 * 
	 * @param record
	 * @return 新增用户的ID
	 */
	private int baseInsertUser(UserWithBLOBs record) {
		Task task = this.taskMapper.selectByPrimaryKey(1);
		if (EmptyUtil.isNotEmpty(task)) {
			if (DateUtil.checkLess(new Date(), task.getEndDate())) {// 有效期检测
				if (EmptyUtil.isNotEmpty(record.getHongbao())) {
					record.setHongbao(record.getHongbao().add(BigDecimal.valueOf(task.getHongbao())));
				} else {
					record.setHongbao(BigDecimal.valueOf(task.getHongbao()));
				}
			} else {// 不在有效期内
				record.setHongbao(new BigDecimal(0));
			}
		} else {// 没有活动任务
			record.setHongbao(new BigDecimal(0));
		}
		record.setIslock(0);
		record.setStatus(1);
		record.setTypeId(2);
		record.setAvatarStatus(0);
		record.setPhoneStatus("1");
		record.setVideoStatus(0);
		record.setSceneStatus(0);
		record.setInviteMoney("0");
		record.setIsPhone(0);
		record.setConnectOpenid("");
		record.setForumKey("");
		record.setEncryption("");
		record.setUserStatus("regemail");
		record.setForumAccredit(false);
		record.setMemberlevel(0);
		record.setAddtime(DateUtil.getTime());
		record.setPaypassword(record.getPassword());
		record.setLasttime(DateUtil.getTime());
		record.setLogintime(new Integer(DateUtil.getTime()));
		this.userMapper.insert(record);
		int userId = record.getUserId();
		// 注册相关信息的MQ
		RegisterMessage ms = new RegisterMessage();
		ms.setUserId(userId);
		ms.setRegisterIp(record.getAddip());
		this.jmsSenderUtil.asynSendSystemJms(ms);

		// 分配客服
		UserEmpMessage userEmp = new UserEmpMessage();
		userEmp.setUserId(userId);
		this.jmsSenderUtil.asynSendSystemJms(userEmp);
		return userId;
	}

	@Autowired
	private CurrentUserAccountMapper currentUserAccountMapper;
	
	@Autowired
	private CurrentAccountRuleMapper currentAccountRuleMapper;
	
	@Autowired
	private CurrentRedeemRuleMapper currentRedeemRuleMapper;
	
	/**
	 * 注册辅助表业务
	 */
	@Override
	public void doRegisterInfo(Integer userId, String registerIp) {
		Account acc = new Account();
		UserAmount amount = new UserAmount();
		UserCache cache = new UserCache();
		HikesCard hikesCard = new HikesCard();
		acc.setUserId(userId);
		amount.setUserId(userId);
		cache.setUserId(userId);
		hikesCard.setUserId(userId);
		this.accountMapper.insertSelective(acc);
		this.userAmountMapper.insertSelective(amount);
		this.userCacheMapper.insertSelective(cache);
		this.hikesCardMapper.insertSelective(hikesCard);
		// 处理用户积分模块数据
		Credit credit = new Credit();
		if (EmptyUtil.isNotEmpty(registerIp)) {
			credit.setAddip(registerIp);
			credit.setUpdateip(registerIp);
		}
		credit.setAddtime(new Integer(DateUtil.getTime()));
		credit.setOpUser(userId);
		credit.setUserId(userId);
		credit.setValue(0);
		credit.setUpdatetime(DateUtil.getTime());
		this.creditMapper.insert(credit);
		//添加用户活期相关信息
		CurrentUserAccount cua = new CurrentUserAccount();
		cua.setUserId(userId);
		this.currentUserAccountMapper.insertSelective(cua);
		CurrentAccountRule car = new CurrentAccountRule();
		car.setMostHoldMoney(new BigDecimal(30000));
		car.setUserId(userId);
		this.currentAccountRuleMapper.insertSelective(car);
		CurrentRedeemRule crr =new CurrentRedeemRule();
		crr.setUserId(userId);
		this.currentRedeemRuleMapper.insertSelective(crr);
	}

	@Override
	public void unionUserBandVfundingUser(UnionUserBandVO unionUserBand) {
		UserUnion userUnion = this.userUnionMapper.selectByPrimaryKey(unionUserBand.getUnionUserId());

		if (EmptyUtil.isNotEmpty(userUnion)) {
			List<UserWithBLOBs> users = this.userMapper.selectByCardId(unionUserBand.getBindCardId());
			if (EmptyUtil.isNotEmpty(users) && users.size() == 1) {
				Integer oldUserId = userUnion.getUserId();
				userUnion.setUserId(users.get(0).getUserId());
				this.userUnionMapper.updateByPrimaryKey(userUnion);

				this.accountMapper.deleteByUserId(oldUserId);
				this.userAmountMapper.deleteByUserId(oldUserId);
				this.userCacheMapper.deleteByPrimaryKey(oldUserId);
				this.creditMapper.deleteByPrimaryKey(oldUserId);
				this.userMapper.deleteByPrimaryKey(oldUserId);

			}

		}
	}

	@Override
	public void mobileRegist(String userName, String password, String mobile, String inviteUserid) {
		RegisterVO vo = new RegisterVO();
		vo.setUserName(userName);
		vo.setPassword(password);
		vo.setMobile(mobile);
		vo.setIntroducer(inviteUserid);
		vo.setIp("app");
		this.registerBase(vo);
	}

	@Override
	public Integer register(RegisterVO register, String ip, String refererUrl) {
		register.setIp(ip);
		register.setRefererUrl(refererUrl);
		return this.registerBase(register);
	}

	/**
	 * 注册的核心业务
	 * 
	 * @param register
	 * @return 2015年1月14日 liuyijun
	 */
	private Integer registerBase(RegisterVO register) {
		UserWithBLOBs user = new UserWithBLOBs();
		user.setUsername(register.getMobile());
		user.setAddip(register.getIp());
		user.setLastip(register.getIp());
		if (EmptyUtil.isNotEmpty(register.getIntroducer())) {// 介绍人不为空
			// List<UserWithBLOBs> listUser = this.userMapper
			// .selectByLogin(register.getIntroducer());
			// if (EmptyUtil.isNotEmpty(listUser) && listUser.size() > 0) {
			// user.setInviteUserid(listUser.get(0).getUserId().toString());//
			// 添加介绍人信息
			// }
			this.setUserInviteUser(register.getIntroducer(), user);
		}
		user.setPassword(register.getPassword());
		user.setPhone(register.getMobile());
		if (EmptyUtil.isNotEmpty(register.getFromType())) {
			user.setFromType(register.getFromType());
		}
		user.setHongbao(new BigDecimal(0));
		user.setIslock(0);
		user.setStatus(1);
		user.setTypeId(2);
		user.setAvatarStatus(0);
		user.setPhoneStatus("1");
		user.setVideoStatus(0);
		user.setSceneStatus(0);
		user.setInviteMoney("0");
		user.setIsPhone(0);
		user.setConnectOpenid("");
		user.setForumKey("");
		user.setEncryption("");
		user.setUserStatus("regemail");
		user.setForumAccredit(false);
		user.setMemberlevel(0);
		user.setAddtime(DateUtil.getTime());
		user.setLasttime(DateUtil.getTime());
		user.setLogintime(new Integer(DateUtil.getTime()));
		this.userMapper.insert(user);
		register.setRegisterUserId(user.getUserId());
		// 创建邀请码
		this.createInviteCode(user.getUserId(), user.getAddtime());
		// 创建用户token
		this.createToken(user.getUserId());
		return user.getUserId();
	}

	/**
	 * 创建邀请码
	 * 
	 * @param userId
	 * @param addtime
	 * 
	 *            jianwei
	 */
	public void createInviteCode(Integer userId, String addtime) {
		// 设置邀请码
		InviteCode ic = new InviteCode();
		ic.setDialogStatus(0);
		ic.setUserId(userId);
		String code = addtime + userId;
		ic.setInviteCode(code.substring(code.length() - 8));
		inviteCodeService.insertSelective(ic);
	}

	/**
	 * 创建token
	 * 
	 * @param userId
	 *
	 *            jianwei
	 */
	public void createToken(Integer userId) {
		UserToken ut = new UserToken();
		ut.setUserId(userId);
		ut.setToken(DigestUtils.md5Hex(String.valueOf(userId)));
		ut.setAddtime(new Date());
		userTokenService.insertSelective(ut);
	}

	/**
	 * 设置邀请人
	 * 
	 * @param inviteCode
	 * @param user
	 * 
	 *            jianwei
	 */
	public void setUserInviteUser(String inviteCode, UserWithBLOBs user) {
		InviteCode ic = inviteCodeService.selectByCode(inviteCode);
		if (ic != null) {
			user.setInviteUserid(ic.getUserId().toString());
		}
	}

	@Override
	public Integer registerForCjd(RegisterVO register, CjdVO cjdvo, String ip, Integer type, String refererUrl) throws Exception {
		register.setIp(ip);
		register.setRefererUrl(refererUrl);
		register.setCjd(true);
		return this.registerBase(register);
	}

	@Override
	public void registerForCjdAfter(RegisterVO register, CjdVO cjdvo, Integer type) {
		// 生成关联
		ThirdRelationship relation = new ThirdRelationship();
		relation.setFromType("1");
		relation.setAddTime(new Date());
		relation.setVid(register.getRegisterUserId());
		relation.setThirdName(cjdvo.getUaccount());
		relation.setVname(register.getUserName());
		relation.setUserType(0); // 新用户
		relation.setUserTracktime(new Date());
		thirdRelationshipMapper.insertSelective(relation);
		// cjdao注册送红包
		// if (type == 2) {
		// CashVolume cash = new CashVolume();
		// cash.setAddtime(new Date());
		// cash.setMoney(new BigDecimal("30"));
		// cash.setRemark("cjdao注册赠送现金卷30元");
		// cash.setUserId(register.getRegisterUserId());
		// cash.setStatus(0);
		// cashVolumeMapper.insertSelective(cash);
		// }
		// cjdao注册送10元红包的系统消息
		if (type == 3) {
			try {
				// 发送系统消息,财经道新注册用户获取10元红包(需要充值并投资)
				sendSystemMessageForCjdRegister(register.getRegisterUserId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送系统消息,财经道新注册用户获取10元红包(需要充值并投资)
	 * 
	 * @param userId
	 * @return
	 * @author louchen 2015-04-21
	 * @throws ParseException
	 */
	private String sendSystemMessageForCjdRegister(Integer userId) throws ParseException {
		// System.out.println("sendSystemMessageForCjdRegister:"+userId);
		String result = "faild";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("start: " + CjdaoConfig.getInstance().getCjDaoActvityOneStart());
		System.out.println("end:" + CjdaoConfig.getInstance().getCjDaoActvityOneEnd());
		Date start = sdf.parse(CjdaoConfig.getInstance().getCjDaoActvityOneStart());
		Date end = sdf.parse(CjdaoConfig.getInstance().getCjDaoActvityOneEnd());
		Date now = new Date();
		// System.out.println("start:"+start.toString());
		// System.out.println("end:"+end.toString());
		// System.out.println("now:"+now.toString());
		if (now.compareTo(start) >= 0 && now.compareTo(end) <= 0) {
			if (userId != null && userId > 0) {
				SystemMessage sm = MessageUtil.getMessageFactory().createSystemMessageForCjdRegister(userId);
				// System.out.println("sm title:"+sm.getTitle());
				// System.out.println("sm user:"+sm.getReceiveUser());
				systemMessageMapper.insertSelective(sm);
				result = "success";
			}
		}
		return result;
	}

	@Override
	public Integer thirdRegister(RegisterVO register, String ip, String account, String category, String refererUrl) {
		register.setIp(ip);
		register.setRefererUrl(refererUrl);
		Integer userId = this.registerBase(register);
		// 处理第三方关联信息
		ThirdLogin search = new ThirdLogin(account, category);
		ThirdLogin login = this.thirdLoginMapper.selectByLoginAccountAndCategory(search);
		if (EmptyUtil.isNotEmpty(login)) {
			if (EmptyUtil.isEmpty(login.getUserId()) || login.getUserId() == 0) {
				login.setUserId(userId);
				this.thirdLoginMapper.updateByPrimaryKey(login);
			}
		}
		return userId;
	}

	@Override
	public List<UserWithBLOBs> selectByLogin(String loginValue) {
		return this.userMapper.selectByLogin(loginValue);
	}

	@Override
	public void userLogin(User record, HttpServletRequest request) {
		String lastIp = ModelAndViewUtil.getIpAddr(request);
		record.setLastip(lastIp);
		record.setLasttime(String.valueOf(System.currentTimeMillis() / 1000));
		this.userMapper.updateLoginInfo(record);
		this.lockRecordMapper.deleteByPrimaryKey(record.getUserId());
		UserTrack track = new UserTrack();
		track.setLoginIp(lastIp);
		track.setLoginTime(String.valueOf(System.currentTimeMillis() / 1000));
		track.setLastReqTime(EmptyUtil.isNotEmpty(track.getLoginTime()) ? track.getLoginTime() : String.valueOf(System.currentTimeMillis() / 1000));
		track.setLoginType("site");
		track.setUserId(record.getUserId().toString());
		track.setSessionId(request.getSession().getId());
		this.trackMapper.insertSelective(track);

	}

	/**
	 * 财经道登录处理
	 * 
	 * @author liuhuan
	 */
	@Override
	public void userLoginCjdao(User record, String third, HttpServletRequest request) {
		String lastIp = ModelAndViewUtil.getIpAddr(request);
		record.setLastip(lastIp);
		record.setLasttime(String.valueOf(System.currentTimeMillis() / 1000));
		this.userMapper.updateLoginInfo(record);
		this.lockRecordMapper.deleteByPrimaryKey(record.getUserId());
		UserTrack track = new UserTrack();
		track.setLoginIp(lastIp);
		track.setLoginTime(String.valueOf(System.currentTimeMillis() / 1000));
		track.setLastReqTime(EmptyUtil.isNotEmpty(track.getLoginTime()) ? track.getLoginTime() : String.valueOf(System.currentTimeMillis() / 1000));
		track.setLoginType("site");
		track.setUserId(record.getUserId().toString());
		track.setSessionId(request.getSession().getId());
		this.trackMapper.insertSelective(track);

		// 处理ThirdRelationship
		ThirdRelationship relation = thirdRelationshipMapper.selectByUserIdType(record.getUserId(), 1);
		if (EmptyUtil.isNotEmpty(relation)) {
			if (relation.getUserType() == 1) { // 0新用户，1老用户
				relation.setUserTracktime(new Date());
				thirdRelationshipMapper.updateByPrimaryKeySelective(relation);
			} else {
				// nothing
			}
		} else {
			relation = new ThirdRelationship();
			relation.setFromType("1");
			relation.setAddTime(new Date());
			relation.setThirdName(third);
			relation.setUserTracktime(new Date());
			relation.setVname(record.getUsername());
			relation.setVid(record.getUserId());
			relation.setUserType(1);
			thirdRelationshipMapper.insertSelective(relation);
		}

	}

	@Override
	public Integer updateByPrimaryKeySelective(UserWithBLOBs user) {
		return this.userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public byte[] getUserQRCode(String content, File logoFile) {
		return QRCoderUtil.encodeOut(content, logoFile).toByteArray();
	}

	@Override
	public int updateQuestion(User record) {
		return this.userMapper.updateQuestion(record);
	}

	@Override
	public int updateRealName(User record) {
		record.setBirthday(BirthdayUtil.getBirthdyByCardId(record.getCardId()));
		int result = this.userMapper.updateRealName(record);
		return result;
	}

	@Override
	public int updateVideo(User record) {
		return this.userMapper.updateVideo(record);
	}

	@Override
	public int updateScene(User record) {
		return this.userMapper.updateScene(record);
	}

	@Override
	public int updateLoginPassword(User record) {
		return this.userMapper.updateLoginPassword(record);
	}

	@Override
	public int updatePayPassword(User record) {
		return this.userMapper.updatePayPassword(record);
	}

	@Override
	public int updatePhone(User record) {
		return this.userMapper.updatePhone(record);
	}

	@Override
	public int updateEmail(User record) {
		return this.userMapper.updateEmail(record);
	}

	@Override
	public int addEmail(User record) {
		return this.userMapper.addEmail(record);
	}

	@Override
	public int checkEmail(User record) {
		return this.userMapper.checkEmail(record);
	}

	@Override
	public List<UserWithBLOBs> selectIntroducer(String value) {
		return this.userMapper.selectIntroducer(value);
	}

	@Override
	public int saveUserInfo(UserInfoVO userInfo) {
		if (!(userInfo.getAddress().equals(UserSession.getUserSession().getAddress()) && userInfo.getSex().equals(UserSession.getUserSession().getSex()))) {
			UserWithBLOBs user = new UserWithBLOBs();
			user.setUserId(userInfo.getUserId());
			user.setSex(userInfo.getSex());
			user.setAddress(userInfo.getAddress());
			this.userMapper.saveInfo(user);
		}

		UserInfo info = this.userInfoMapper.selectByUserId(userInfo.getUserId());
		if (EmptyUtil.isEmpty(info)) {
			info = new UserInfo();
			info.setUserId(userInfo.getUserId());
		}
		info.setMarry(userInfo.getMarry());
		info.setEducation(userInfo.getEducation());
		info.setEducationSchool(userInfo.getEducationSchool());
		info.setCompanyType(userInfo.getCompanyType());
		info.setCompanyIndustry(userInfo.getCompanyIndustry());
		info.setIncome(userInfo.getIncome());
		info.setCompanyOffice(userInfo.getCompanyOffice());
		if (EmptyUtil.isNotEmpty(info.getId())) {
			this.userInfoMapper.updateByPrimaryKeySelective(info);
		} else {
			this.userInfoMapper.insertSelective(info);
		}
		UserSession.getUserSession().setSex(userInfo.getSex());
		UserSession.getUserSession().setAddress(userInfo.getAddress());
		return 0;
	}

	@Override
	public List<User> selectFriendByUserIdListPage(PageSearch pageSearch) {
		return this.userMapper.selectFriendByUserIdListPage(pageSearch);
	}

	@Override
	public List<UserWithBLOBs> selectByCardId(String cardId) {
		return this.userMapper.selectByCardId(cardId);
	}

	@Override
	public void unionUserRealName(UnionUserRealNameMessage message) {
		UserUnion userUnion = this.userUnionMapper.selectByPrimaryKey(message.getUnionUserId());
		if (EmptyUtil.isNotEmpty(userUnion) && EmptyUtil.isNotEmpty(userUnion.getUserId())) {
			User user = this.userMapper.selectByPrimaryKey(userUnion.getUserId());
			if (EmptyUtil.isNotEmpty(user)) {
				if (!user.getRealStatus().equals("1")) {
					user.setRealname(message.getRealName());
					user.setRealStatus("1");
					user.setCardId(message.getCardId());
					this.userMapper.updateRealName(user);
				}
			}
		}

	}

	@Override
	public boolean checkUserNameIsLegal(String userName) {
		boolean result = true;
		if (EmptyUtil.isEmpty(userName) || userName.equalsIgnoreCase("test") || userName.equalsIgnoreCase("vfunding") || userName.equalsIgnoreCase("admin") || userName.toLowerCase().contains("test") || userName.toLowerCase().contains("vfunding") || userName.toLowerCase().contains("admin") || userName.toLowerCase().contains("root") || EmptyUtil.isEmpty(userName)) {
			result = false;
		}
		return result;
	}

	@Override
	public void becomeVIP(Account account, Json j, String useHongbao, Integer vipPrice) {
		BigDecimal hongbao = this.userMapper.selectHongbaoByUserId(UserSession.getLoginUserId());
		if (hongbao.add(account.getUseMoney()).intValue() > vipPrice) {

			BigDecimal newHongbao = new BigDecimal(0);
			if (useHongbao.equals("yes")) {
				int shengyu = vipPrice - hongbao.intValue();
				if (shengyu > 0) {
					account.setUseMoney(account.getUseMoney().subtract(new BigDecimal(shengyu)));
					account.setTotal(account.getTotal().subtract(new BigDecimal(shengyu)));
				} else if (shengyu < 0) {
					newHongbao = hongbao.subtract(new BigDecimal(vipPrice));
				}
			} else {
				int shengyu = vipPrice - account.getUseMoney().intValue();
				if (shengyu > 0) {
					account.setUseMoney(new BigDecimal(0));
					account.setTotal(new BigDecimal(0));
					newHongbao = hongbao.subtract(new BigDecimal(shengyu));
				} else if (shengyu < 0) {
					account.setUseMoney(account.getUseMoney().subtract(new BigDecimal(vipPrice)));
					account.setTotal(account.getTotal().subtract(new BigDecimal(vipPrice)));
				} else {
					account.setUseMoney(new BigDecimal(0));
					account.setTotal(new BigDecimal(0));
				}
			}

			UserWithBLOBs user = new UserWithBLOBs();
			user.setUserId(UserSession.getLoginUserId());
			user.setHongbao(newHongbao);
			this.accountMapper.updateByPrimaryKey(account);
			user.setHongbao(newHongbao);
			this.userMapper.updateByPrimaryKeySelective(user);
			UserCache cache = new UserCache();
			cache.setUserId(user.getUserId());
			cache.setVipStatus(1);
			this.userCacheMapper.updateByPrimaryKeySelective(cache);

			if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
				UserSession.getUserSession().setVipStatus(1);
			}

		} else {
			j.setMsg("您的余额不足");
		}

	}

	@Override
	public void updateHeadPic(UserWithBLOBs user) {
		this.userMapper.updateByPrimaryKeySelective(user);
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			UserSession.getUserSession().setUserIcon(user.getUserIcon());
		}
	}

	@Override
	public List<User> selectFriendByUserListPage(PageSearch pageSearch) {
		return userMapper.selectFriendByUserListPage(pageSearch);
	}

	@Override
	public int updateByPrimaryKey(User user) {
		return userMapper.updateByPrimaryKey(user);
	}

	@Override
	public void lockUser(UserWithBLOBs user) {
		UserWithBLOBs upUser = new UserWithBLOBs();
		upUser.setUserId(user.getUserId());
		upUser.setIslock(1);
		this.userMapper.updateByPrimaryKeySelective(upUser);
		LockRecord record = this.lockRecordMapper.selectByPrimaryKey(user.getUserId());
		if (EmptyUtil.isNotEmpty(record)) {
			record.setErrorNum(0);
			record.setLockTime(new Date());
			record.setUnlockTime(DateUtil.getAfterDateAsDate(new Date(), 1));
			this.lockRecordMapper.updateByPrimaryKey(record);
		} else {
			record = new LockRecord();
			if (EmptyUtil.isNotEmpty(user.getEmail())) {
				record.setEmail(user.getEmail());
			}
			if (EmptyUtil.isNotEmpty(user.getPhone())) {
				record.setPhone(user.getPhone());
			}
			if (EmptyUtil.isNotEmpty(user.getUsername())) {
				record.setUsername(user.getUsername());
			}
			record.setUserId(user.getUserId());
			record.setLockTime(new Date());
			record.setUnlockTime(DateUtil.getAfterDateAsDate(new Date(), 1));
			this.lockRecordMapper.insert(record);
		}
		if (EmptyUtil.isNotEmpty(user.getUsername())) {
			// 发送锁定短信
			this.jmsSenderUtil.sendSms(user.getPhone(), ISendConfigUtil.SMS_LOCKED, user.getUsername());
		}
	}

	@Override
	public void doOut(Integer userId, HttpSession httpSession) {
		// UserTrack track=this.trackMapper.selectByLastLogin(new
		// UserTrack(userId.toString(),"site"));
		List<UserTrack> tracks = this.trackMapper.selectBySessionId(httpSession.getId());
		if (EmptyUtil.isNotEmpty(tracks)) {
			for (UserTrack userTrack : tracks) {
				if (EmptyUtil.isEmpty(userTrack.getOutTime())) {
					userTrack.setOutTime(String.valueOf(System.currentTimeMillis() / 1000));
					this.trackMapper.updateByPrimaryKey(userTrack);
				}
			}
		}
	}

	@Override
	public BigDecimal getUserHongbao(Integer userId) {
		BigDecimal result = null;
		result = this.userMapper.selectHongbaoByUserId(userId);
		if (EmptyUtil.isEmpty(result)) {
			result = new BigDecimal("0.00");
		}
		return result;
	}

	@Override
	public List<User> selectAllUsersByListPage(PageSearch pageSearch) {
		return userMapper.selectUsersByListPage(pageSearch);
	}

	@Override
	public List<User2Sinamember> selectAllUsersByBalanceOrRedListPage(PageSearch pageSearch) {
		return userMapper.selectAllUsersByBalanceOrRedListPage(pageSearch);
	}

	@Override
	public int getInviteCount(Integer userId) {

		return userMapper.getInviteCount(userId);
	}

	@Override
	public User getInviteFirstUser(Integer userId) {

		return userMapper.getInviteFirstUser(userId);
	}

	@Override
	public List<User> getInviteAllUser(Integer userId) {

		return userMapper.selectInviteAllUser(userId);
	}

	@Override
	public List<User> selectByRegisterCompliment() {
		return this.userMapper.selectByRegisterCompliment();
	}

	@Override
	public List<User> selectNoTenderUser(String today) {
		return this.userMapper.selectNoTenderUser(today);
	}

	@Override
	public List<User> selectNoLoginUser(Integer days) {
		return this.userMapper.selectNoLoginUser(days);
	}
}
