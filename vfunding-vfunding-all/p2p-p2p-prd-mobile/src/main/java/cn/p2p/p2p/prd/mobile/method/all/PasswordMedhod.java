package cn.p2p.p2p.prd.mobile.method.all;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.request.vo.PasswordVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

import com.alibaba.druid.util.StringUtils;

@Service
public class PasswordMedhod {

	@Autowired
	private IUserTokenService userTokenService;
	@Autowired
	private IUserService userService;
	@Autowired
	private PhoneVerifyCodeMethod phoneVerifyCodeMethod;


	/**
	 * 设置支付密码
	 * 
	 * @param passwordVO
	 * @return
	 * 
	 *         2015年5月21日 lijianwei
	 */
	@CheckToken
	public MobileBaseResponse savePayPassword(PasswordVO passwordVO) {
		if (StringUtils.isEmpty(passwordVO.getNewPassword()))
			return new MobileBaseResponse("password_fail", "新密码不可为空");
		if (StringUtils.isEmpty(passwordVO.getRePassword()))
			return new MobileBaseResponse("password_fail", "确认密码不可为空");
		if (!passwordVO.getNewPassword().equals(passwordVO.getRePassword()))
			return new MobileBaseResponse("password_fail", "两次密码不一致");
		User user = new User();
		user.setUserId(this.userTokenService.selectUserIdByToken(passwordVO.getToken()));
		user.setPaypassword(passwordVO.getNewPassword());
		this.userService.updatePayPassword(user);
		return new MobileBaseResponse();
	}

	/**
	 * 修改密码 忘记密码
	 * 
	 * @param passwordVO
	 * @return
	 * 
	 *         2015年5月25日 lijianwei
	 */
	@CheckToken
	public MobileBaseResponse checkCodeAndChangePassword(PasswordVO passwordVO) {
		if (StringUtils.isEmpty(passwordVO.getNewPassword()))
			return new MobileBaseResponse("password_fail", "密码不可为空");
		if (passwordVO.getPasswordType().equals("1") && StringUtils.isEmpty(passwordVO.getRePassword()))
			return new MobileBaseResponse("password_fail", "确认支付密码不可为空");
		if (passwordVO.getPasswordType().equals("1") && !passwordVO.getNewPassword().equals(passwordVO.getRePassword()))
			return new MobileBaseResponse("password_fail", "两次支付密码不一致");
		if (StringUtils.isEmpty(passwordVO.getPhone()) || passwordVO.getPhone().length() > 11 || passwordVO.getPhone().length() < 11)
			return new MobileBaseResponse("phone_fail", "手机号错误");
		if (StringUtils.isEmpty(passwordVO.getVerifCode()))
			return new MobileBaseResponse("code_fail", "验证码不可为空");
		if (StringUtils.isEmpty(passwordVO.getCardIdCheck()) || passwordVO.getCardIdCheck().length() > 6)
			return new MobileBaseResponse("card6_fail", "请输入正确的身份证后六位");
		if (this.phoneVerifyCodeMethod.checkVerifyCode(passwordVO.getPhone(), passwordVO.getVerifCode())) {
			User u = this.userService.selectByPrimaryKey(this.userTokenService.selectUserIdByToken(passwordVO.getToken()));
			if (!StringUtils.isEmpty(u.getCardId()) && u.getCardId().substring(u.getCardId().length() - 6, u.getCardId().length()).equals(passwordVO.getCardIdCheck())) {
				User user = new User();
				user.setUserId(u.getUserId());
				if (passwordVO.getPasswordType().equals("0")) {
					user.setPassword(passwordVO.getNewPassword());
					this.userService.updateLoginPassword(user);
				} else if (passwordVO.getPasswordType().equals("1")) {
					user.setPaypassword(passwordVO.getNewPassword());
					this.userService.updatePayPassword(user);
				}
			} else {
				return new MobileBaseResponse("card6_fail", "身份证后六位不正确");
			}
		} else {
			return new MobileBaseResponse("code_fail", "验证码错误");
		}
		return new MobileBaseResponse();
	}
}
