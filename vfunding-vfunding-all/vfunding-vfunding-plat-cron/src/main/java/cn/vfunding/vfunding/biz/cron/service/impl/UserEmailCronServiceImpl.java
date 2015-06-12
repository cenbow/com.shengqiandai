package cn.vfunding.vfunding.biz.cron.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.vfunding.biz.cron.service.IUserEmailCronService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;
@Service("userEmailCronService")
public class UserEmailCronServiceImpl implements IUserEmailCronService {

	@Autowired
	private IUserService userService;
	@Autowired
	private JmsSenderService jmsSenderUtil;
	
	
	@Value("${sendEmailForNoTender.time}")
	private String sendEmailForNoTenderTime;
	
	@Value("${sendEmailForNoLogin.time}")
	private String sendEmailForNoLoginTime;
	@Override
	public String sendEmailForNoTender() {
		String day=DateUtil.getAfterDate(new Date(), -14, "yyyy-MM-dd");
		List<User> users=this.userService.selectNoTenderUser(day);
		for (User user : users) {
			this.jmsSenderUtil.sendEmail(user.getEmail(), ISendConfigUtil.EMAIL_TEMPLET_LONGTIMENOINVEST, user.getUsername(),user.getUsername());
		}
		return sendEmailForNoTenderTime;
	}

	@Override
	public String sendEmailForNoLogin() {
		List<User> users7=this.userService.selectNoLoginUser(7);
		for (User user : users7) {
			this.jmsSenderUtil.sendEmail(user.getEmail(), ISendConfigUtil.EMAIL_TEMPLET_LONGTIMENOLOGIN, user.getUsername(),user.getUsername(),"7天");
		}
		
		List<User> users10=this.userService.selectNoLoginUser(10);
		for (User user : users10) {
			this.jmsSenderUtil.sendEmail(user.getEmail(), ISendConfigUtil.EMAIL_TEMPLET_LONGTIMENOLOGIN, user.getUsername(),user.getUsername(),"10天");
		}
		
		List<User> users15=this.userService.selectNoLoginUser(15);
		for (User user : users15) {
			this.jmsSenderUtil.sendEmail(user.getEmail(), ISendConfigUtil.EMAIL_TEMPLET_LONGTIMENOLOGIN, user.getUsername(),user.getUsername(),"15天");
		}
		
		List<User> users20=this.userService.selectNoLoginUser(20);
		for (User user : users20) {
			this.jmsSenderUtil.sendEmail(user.getEmail(), ISendConfigUtil.EMAIL_TEMPLET_LONGTIMENOLOGIN, user.getUsername(),user.getUsername(),"20天");
		}
		return sendEmailForNoLoginTime;
	}
	
	

}
