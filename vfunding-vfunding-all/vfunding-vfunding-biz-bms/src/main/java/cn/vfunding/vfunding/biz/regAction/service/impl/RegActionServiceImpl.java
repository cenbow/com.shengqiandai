package cn.vfunding.vfunding.biz.regAction.service.impl;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.bms_employee.service.IEmployeeService;
import cn.vfunding.vfunding.biz.common.vo.CjdVO;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.credit.dao.CreditMapper;
import cn.vfunding.vfunding.biz.credit.model.Credit;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.regAction.service.RegActionService;
import cn.vfunding.vfunding.biz.thirdplat.model.ThirdRelationship;
import cn.vfunding.vfunding.biz.thirdplat.service.IThirdRelationshipService;
import cn.vfunding.vfunding.biz.user.dao.UserAmountMapper;
import cn.vfunding.vfunding.biz.user.dao.UserCacheMapper;
import cn.vfunding.vfunding.biz.user.model.UserAmount;
import cn.vfunding.vfunding.biz.user.model.UserCache;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.module.activemq.message.CjdUserMessage;
import cn.vfunding.vfunding.common.module.activemq.message.UserEmpMessage;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;
@Service
public class RegActionServiceImpl implements RegActionService{

	@Autowired
	@Qualifier("cjdRestInvokerFactory")
	private RestInvokerFactory cjdRestInvokerFactory;
	@Autowired
	@Qualifier("activityRestInvokerFactory")
	private RestInvokerFactory activityRestInvokerFactory;

	@Autowired
	@Qualifier("sinaRestInvokerFactory")
	private RestInvokerFactory sinaRestInvokerFactory;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IThirdRelationshipService thirdRelationshipService;

	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private CreditMapper creditMapper;
	@Autowired
	private HikesCardMapper hikesCardMapper;
	@Autowired
	private UserCacheMapper userCacheMapper;
	@Autowired
	private UserAmountMapper userAmountMapper;

	public void doUserAction(RegisterVO vo) {
		// 注册辅助业务处理
		this.doRegister(vo);
		// 注册相关活动处理
		doSynchroData(vo, "/activityUserAction/userRegist", "register", "activity", vo.getRegisterUserId());
		// 新浪托管同步用户信息
		doSynchroData(vo, "/sinaSendAction/register", "register", "sina", vo.getRegisterUserId());
		// 财经道用户信息同步
		// this.cjdUserRegisterBiz(vo, msg);
	}

	private void doRegister(RegisterVO vo) {

		// 注册后用户账户及积分信息业务处理
		this.doRegisterInfo(vo.getRegisterUserId(), vo.getIp());
		// 分配客服
		UserEmpMessage userEmp = new UserEmpMessage();
		userEmp.setUserId(vo.getRegisterUserId());
		this.employeeService.insertUserEmpByReg(userEmp);
	}

	private void doSynchroData(Object obj, String url, String actionName, String category, Integer userId) {
		String serviceUrl = "";
		try {
			String result = "faild";
			if (category.equals("sina")) {
				serviceUrl = this.sinaRestInvokerFactory.getBaseURL() + url;
				result = this.sinaRestInvokerFactory.getRestInvoker().post(url, obj);
			} else if (category.equals("activity")) {
				serviceUrl = this.activityRestInvokerFactory.getBaseURL() + url;
				result = this.activityRestInvokerFactory.getRestInvoker().post(url, obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检验是否是财经道用户
	 * 
	 * @param ship
	 * @return 2014年12月31日 liuyijun
	 */
	private boolean isCjdUserCheck(ThirdRelationship ship) {
		if (EmptyUtil.isNotEmpty(ship)) {
			Calendar cal = Calendar.getInstance();
			if (EmptyUtil.isEmpty(ship.getUserTracktime())) {
				cal.setTime(new Date());
			} else {
				cal.setTime(ship.getUserTracktime());
			}
			cal.add(Calendar.HOUR, 24);
			if (ship.getUserType() == 0 || (ship.getUserType() == 1 && cal.getTime().after(new Date()))) {
				return true;
			}
		}
		return false;
	}

	public void doRegisterInfo(Integer userId, String registerIp) {
		Account acc = new Account();
		UserAmount amount = new UserAmount();
		UserCache cache = new UserCache();
		HikesCard hikesCard = new HikesCard();
		acc.setUserId(userId);
		amount.setUserId(userId);
		cache.setUserId(userId);
		hikesCard.setUserId(userId);
		if (this.accountMapper.selectByUserId(userId) == null)
			this.accountMapper.insertSelective(acc);

		UserAmount ua = new UserAmount();
		ua.setUserId(userId);
		if (this.userAmountMapper.selectByParam(ua).isEmpty())
			this.userAmountMapper.insertSelective(amount);

		if (userCacheMapper.selectByPrimaryKey(userId) == null)
			this.userCacheMapper.insertSelective(cache);
		if (this.hikesCardMapper.selectByPrimaryKey(userId) == null)
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
		if (creditMapper.selectValueByUserId(userId) == null)
			this.creditMapper.insert(credit);

	}
}
