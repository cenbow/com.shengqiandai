package cn.vfunding.vfunding.biz.cron.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.vfunding.biz.cron.service.IBirthdayUserCronService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

@Service("birthdayUserCronService")
public class BirthdayUserCronServiceImpl implements IBirthdayUserCronService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JmsSenderService jmsSenderUtil;
	
	@Value("${user.birthday.cron.time}")
	private String userBirthdayTime;

	@Override
	public String sendEmailForBirthdayUser() {
		List<User> users = this.userMapper.selectForEmailByBirthday(DateUtil
				.getDateString(new Date(), "MM-dd"));
		for (User user : users) {
			this.jmsSenderUtil.sendEmail(user.getEmail(),
					ISendConfigUtil.EMAIL_TEMPLET_BIRTHDAY, user.getUsername(),
					user.getUsername(),
					DateUtil.getDateString(new Date(), "yyyy-MM-dd"));
		}
		return userBirthdayTime;
	}

}
