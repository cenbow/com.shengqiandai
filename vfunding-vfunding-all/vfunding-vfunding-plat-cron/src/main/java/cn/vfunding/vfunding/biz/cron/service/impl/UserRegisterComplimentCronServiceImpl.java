package cn.vfunding.vfunding.biz.cron.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.cron.service.IUserRegisterComplimentCronService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

@Service("userRegisterComplimentCronService")
public class UserRegisterComplimentCronServiceImpl implements
		IUserRegisterComplimentCronService {

	@Autowired
	private IUserService userService;
	@Value("${userRegisterCompliment.cron.time}")
	private String userRegisterComplimentTime;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Override
	public String userRegisterCompliment() {
		List<User> users = this.userService.selectByRegisterCompliment();
		for (User user : users) {
			if (EmptyUtil.isNotEmpty(user.getPhone())) {
				/**
				 * 发送注册问候短信
				 */
				this.jmsSenderUtil.sendSms(user.getPhone(),
						ISendConfigUtil.SMS_REGISTER_COMPLIMENT,user.getUsername());
			}
		}
		return userRegisterComplimentTime;
	}

}
