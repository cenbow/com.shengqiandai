package cn.vfunding.vfunding.biz.cron.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.cron.service.IUserUnLockService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.system.dao.LockRecordMapper;
import cn.vfunding.vfunding.biz.system.model.LockRecord;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

@Service("userUnLockService")
public class UserUnLockServiceImpl implements IUserUnLockService {

	@Autowired
	private LockRecordMapper lockRecordMapper;

	@Autowired
	private UserMapper userMapper;

	@Value("${user.unlock.time}")
	private String unLockTime;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Override
	public String deleteUserLock() {
		List<LockRecord> list = this.lockRecordMapper.selectByUnlockTime();
		for (LockRecord lr : list) {
			Integer userId = lr.getUserId();
			UserWithBLOBs user = new UserWithBLOBs();
			user.setUserId(userId);
			user.setIslock(0);
			this.userMapper.updateByPrimaryKeySelective(user);
			this.lockRecordMapper.deleteByPrimaryKey(userId);
			user = this.userMapper.selectByPrimaryKey(lr.getUserId());
			if (EmptyUtil.isNotEmpty(user)
					&& EmptyUtil.isNotEmpty(user.getEmail())) {
				this.jmsSenderUtil.sendEmail(user.getEmail(),
						ISendConfigUtil.EMAIL_TEMPLET_ACCOUNTOPEN,
						user.getUsername(), user.getUsername(),
						DateUtil.getDateString(lr.getLockTime(), "yyyy-MM-dd"));
			}
		}

		return unLockTime;
	}
}
