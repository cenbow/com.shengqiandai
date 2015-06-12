package cn.vfunding.vfunding.biz.cron.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.cron.service.IUserTrackCronService;
import cn.vfunding.vfunding.biz.system.service.IUserTrackService;
@Service("userTrackCronService")
public class UserTrackCronServiceImpl implements IUserTrackCronService {

	@Value("${outuser.time}")
	private String outUserTime;
	/**
	 * 用户退出的秒数，类似session的过期时间
	 */
	@Value("${outuser.second}")
	private Integer outSecond;
	@Autowired
	private IUserTrackService userTrackService;

	@Override
	public String outUserService() {
        this.userTrackService.userAutoOut(outSecond);
		return outUserTime;
	}

}
