package cn.p2p.p2p.prd.mobile.method.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.ContBefore;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.utils.DESCipher;
import cn.p2p.p2p.prd.mobile.utils.VerificationCodeCache;
import cn.p2p.p2p.prd.mobile.vo.LoginedUserInfo;
import cn.p2p.p2p.prd.mobile.vo.VerificationCodeMapVo;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.rest.annotation.RestDescription;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.inviteCode.model.InviteCode;
import cn.vfunding.vfunding.biz.inviteCode.service.IInviteCodeService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.VerifyCodeService;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

@Service
public class LoginMethod {
	@Autowired
	private IUserService userService;
	@Autowired
	private IInviteCodeService inviteCodeService;
	@Autowired
	private IUserTokenService userTokenService;
	@Autowired
	private JmsSenderService jmsSenderUtil;
	@Value("${send.sms.status}")
	private Integer smsStatus;

	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private PhoneVerifyCodeMethod phoneVerifyCodeMethod;

	private static DESCipher des = new DESCipher();

	/**
	 * 用户注册时检查用户名、邮箱、手机等信息的唯一性
	 * 
	 * @param value
	 * @return
	 * @author liuyijun
	 */
	@RestDescription("用户注册检查用户名、手机、邮箱唯一性,返回true表示该值可以注册")
	public boolean checkRegister(String value) {
		return this.userService.checkRegister(value);
	}
	@ContBefore
	public MobileBaseResponse registerCheckUsername(GeneralRequestVO generalRequest) {
		if (!StringUtils.isEmpty(generalRequest.getVerifValue())) {
			if (this.checkRegister(generalRequest.getVerifValue()))
				return new MobileBaseResponse();
			else
				return new MobileBaseResponse("username_fail", "用户名已存在或格式不正确");

		} else {
			return new MobileBaseResponse("username_null_fail", "用户名不可为空");
		}
	}
	@ContBefore
	public MobileBaseResponse registerCheckPhone(GeneralRequestVO generalRequest) {
		if (!StringUtils.isEmpty(generalRequest.getVerifValue())) {
			if (this.checkRegister(generalRequest.getVerifValue()))
				return new MobileBaseResponse();
			else
				return new MobileBaseResponse("phone_fail", "手机号已存在或格式不正确");
		} else {
			return new MobileBaseResponse("phone_null_fail", "手机号不可为空");
		}
	}
	@ContBefore
	public MobileBaseResponse registerCheckMail(GeneralRequestVO generalRequest) {
		if (!StringUtils.isEmpty(generalRequest.getVerifValue())) {
			if (this.checkRegister(generalRequest.getVerifValue()))
				return new MobileBaseResponse();
			else
				return new MobileBaseResponse("mail_fail", "Email已存在或格式不正确");

		} else {
			return new MobileBaseResponse("mail_null_fail", "Email不可为空");
		}
	}

	@RestDescription("用户注册,注册成功后自动登录")
	public MobileBaseResponse register(GeneralRequestVO generalRequest) {
		generalRequest.setVerifValue(generalRequest.getPhone());
		MobileBaseResponse phoneCheck = this.registerCheckPhone(generalRequest);
		if (!phoneCheck.getResponseCode().equals("success")) {
			return phoneCheck;
		}
		boolean b=this.phoneVerifyCodeMethod.checkVerifyCode(generalRequest.getPhone(), generalRequest.getVerifCode());
		System.out.println("b:"+b);
		if (b!=true) {
			return new MobileBaseResponse("code_fail", "验证码错误");
		}
		if (EmptyUtil.isNotEmpty(generalRequest.getInviteUserid())) {
			InviteCode ic = inviteCodeService.selectByCode(generalRequest.getInviteUserid());
			if (ic == null) {
				return new MobileBaseResponse("inviteCode_null_fail", "无此邀请人");
			}
		}
		RegisterVO vo = new RegisterVO();
		vo.setMobile(generalRequest.getPhone());
		vo.setPassword(generalRequest.getPassword());
		vo.setUserName(generalRequest.getPhone());
		vo.setIntroducer(generalRequest.getInviteUserid());
		this.userService.register(vo, generalRequest.getOsType(), null);
		return this.login(generalRequest);
	}

	@RestDescription("用户登录")
	public MobileBaseResponse login(GeneralRequestVO generalRequest) {
		List<UserWithBLOBs> listuser = this.userService.selectByLogin(generalRequest.getPhone());
		if (EmptyUtil.isEmpty(listuser)) {
			return new MobileBaseResponse("user_null_fail", "无此用户");
		} else if (listuser.size() > 1) {
			return new MobileBaseResponse("user_fail", "该账户状态出现异常,请联系");
		} else {
			UserWithBLOBs user = listuser.get(0);
			String userPwd = generalRequest.getPassword();
			if (EmptyUtil.isNotEmpty(user.getIslock()) && user.getIslock().intValue() == 1) {// 用户被锁定，不能登录
				return new MobileBaseResponse("user_lock", "该账户被锁定，请联系客服");
			} else if (EmptyUtil.isNotEmpty(user.getStatus()) && user.getStatus().intValue() == 0) {
				return new MobileBaseResponse("user_fail", "该账户状态为不可用，请联系客服");
			} else {
				if (user.getPassword().equals(userPwd)) {
					LoginedUserInfo userInfo = new LoginedUserInfo();
					userInfo.setUserId(user.getUserId());
					userInfo.setBonuses(EmptyUtil.isEmpty(user.getHongbao()) ? 0 : user.getHongbao().intValue());
					userInfo.setMobile(user.getPhone());
					userInfo.setPassword(user.getPassword());
					userInfo.setPaypassword(user.getPaypassword());
					userInfo.setUserName(user.getPhone());
					String token = this.userTokenService.selectTokenByUserId(user.getUserId());
					userInfo.setToken(token);
					return new MobileBaseResponse(userInfo);
				} else {
					return new MobileBaseResponse("password_fail", "密码错误");
				}
			}
		}
	}

}
