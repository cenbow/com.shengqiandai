package cn.vfunding.vfunding.common.sendconfig;

public interface ISendConfigUtil {

	/**
	 * 注册问候短信key
	 */
	static final String SMS_REGISTER_COMPLIMENT="sms.register.compliment";
	
	/**
	 * 发现新用户key
	 */
	static final String EMAIL_REGISTER_NEW="email.register.new";
	
	
	/**
	 * 注册验证码短信key
	 */
	static final String SMS_REGISTER_CHECK="sms.register.check";
	
	
	/**
	 * 闲置金额提醒短信key
	 */
	static final String SMS_LEAVEUNUSEDMONEY="sms.leaveUnusedMoney";
	
	/**
	 * 红包礼品即将过期短信key
	 */
	static final String SMS_GIFTBOXHONGBAOEXPIRE="sms.giftboxHongbaoExpire";
	
	/**
	 * 提醒申请短信key
	 */
	static final String SMS_TACKCASH_CHECK="sms.tackcash.check";
	
	/**
	 * 找回支付密码短信key
	 */
	static final String SMS_FINDPAYPWD="sms.findpaypwd";
	
	/**
	 * 修改手机短信key
	 */
	static final String SMS_MODIFYPHONE="sms.modifyphone";
	
	/**
	 * 添加或验证手机时短信key
	 */
	static final String SMS_CHECKPHONE="sms.checkphone";
	
	/**
	 * 更改登录密码时短信key
	 */
	static final String SMS_FINDPWD="sms.findpwd";
	
	/**
	 * 新浪提现申请成功短信key
	 */
	static final String SMS_TAKECASH_SINA_SUCCESS="sms.takecash.sina.success";
	
	
	
	
	/**
	 * 提现申请失败短信key
	 */
	static final String SMS_TAKECASH_FAILD="sms.takecash.faild";
	
	/**
	 * 满标复审时自动投标用户通知短信key
	 */
	static final String SMS_VERIFYBORROW_AUTOINVEST="sms.verifyborrow.autoinvest";
	
	/**
	 * 满标复审时用户通知短信key
	 */
	static final String SMS_VERIFYBORROW="sms.verifyborrow";
	
	/**
	 * 添加银行卡提醒短信key
	 */
	static final String SMS_ADDBANKCARD="sms.addbankcard";
	
	/**
	 * 设置自动投标提醒短信key
	 */
	static final String SMS_SETAUTOINVEST="sms.setautoinvest";
	
	/**
	 * 账户锁定短信key
	 */
	static final String SMS_LOCKED="sms.locked";
	
	/**
	 * 还款到账提醒短信key
	 */
	static final String SMS_REPAYMENT="sms.repayment";
	
	
	/**
	 * 多月标还款到账提醒短信key
	 */
	static final String SMS_REPAYMENT_MONTH="sms.repayment.month";
	
	/**
	 * 用户提现金额到账提醒短信key
	 */
	static final String SMS_TAKETOCARDSUCCESS="sms.taketocardsuccess";
	
	/**
	 * 用户提现金额到账失败提醒短信key
	 */
	static final String SMS_TAKETOCARDFAILD="sms.taketocardfaild";
	
	
	
	
	/**
	 * 实名认证后邮件key
	 */
	static final String EMAIL_TEMPLET_REALNAME="email.templet.realname";
	
	
	/**
	 * 生日祝福邮件key
	 */
	static final String EMAIL_TEMPLET_BIRTHDAY="email.templet.birthday";
	
	/**
	 * 用户回访邮件key
	 */
	static final String EMAIL_TEMPLET_CALLBACK="email.templet.callback";
	
	
	/**
	 * 新/即将失效礼券/红包邮件key
	 */
	static final String EMAIL_TEMPLET_EXPIRE="email.templet.expire";
	
	
	/**
	 * 发现新用户邮件key
	 */
	static final String EMAIL_TEMPLET_NEWUSER="email.templet.newuser";
	
	/**
	 * 添加新的银行卡邮件key
	 */
	static final String EMAIL_TEMPLET_ADDNEWCARD="email.templet.addnewcard";
	
	
	/**
	 * 收货地址变更邮件key
	 */
	static final String EMAIL_TEMPLET_CHANGEADDRESS="email.templet.changeaddress";
	

	/**
	 * 还款确认信邮件key
	 */
	static final String EMAIL_TEMPLET_REPAYMENT="email.templet.repayment";
	

	/**
	 * 账号解锁邮件邮件key
	 */
	static final String EMAIL_TEMPLET_ACCOUNTOPEN="email.templet.accountopen";
	

	/**
	 * 长时间没有投资/闲置key
	 */
	static final String EMAIL_TEMPLET_LONGTIMENOINVEST="email.templet.longtimenoinvest";
	
	
	/**
	 * 只注册验证邮箱的新用户key
	 */
	static final String EMAIL_TEMPLET_LONGTIMENOLOGIN="email.templet.longtimenologin";
	
	/**
	 * app新用户首投短信
	 */
	static final String APP_FIRST_BORROW_TENDER="app.first.borrow.tender";
	
}
