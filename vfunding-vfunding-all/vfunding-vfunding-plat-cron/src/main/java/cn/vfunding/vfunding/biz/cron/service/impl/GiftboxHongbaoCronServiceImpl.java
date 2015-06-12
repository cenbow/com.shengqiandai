package cn.vfunding.vfunding.biz.cron.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.cron.service.IGiftboxHongbaoCronService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.message.dao.GiftboxMessageMapper;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

@Service("giftboxHongbaoCronService")
public class GiftboxHongbaoCronServiceImpl implements
		IGiftboxHongbaoCronService {

	/**
	 * 礼品盒红包mapper
	 */
	@Autowired
	private GiftboxMessageMapper giftboxMessageMapper;

	/**
	 * 扫描器执行时间
	 */
	@Value("${giftboxHongbao.cron.time}")
	private String giftboxHongbaoCronTime;

	@Value("${giftboxHongbao.cron.remind.time}")
	private String giftboxHongbaoRemindCronTime;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	/**
	 * 扫描过期红包并更新状态=2
	 * 
	 * @return
	 * @author louchen 2015-02-06
	 */
	@Override
	public String updateGiftboxHongbaoExpireStatus() {
		// 当前时间
		Date updatetime = new Date();
		// 状态是未过期未使用,但实际已经超过失效时间的红包
		List<GiftboxMessage> gms = this.giftboxMessageMapper
				.selectHongbaoExpireByStatus(0);
		for (GiftboxMessage gm : gms) {
			gm.setStatus(2); // 已过期
			gm.setIsLook(1); // 已查看
			gm.setUpdateTime(updatetime);
			this.giftboxMessageMapper.updateByPrimaryKeySelective(gm);
		}
		return giftboxHongbaoCronTime;
	}

	@Override
	public String sendMessageByGiftboxHongbaoExpire() {
		List<GiftboxMessage> gms = this.giftboxMessageMapper.selectByLose();
		for (GiftboxMessage giftboxMessage : gms) {
			UserWithBLOBs currentUser = this.userMapper
					.selectByPrimaryKey(giftboxMessage.getReceiveUser());
			if (EmptyUtil.isNotEmpty(currentUser)
					&& EmptyUtil.isNotEmpty(currentUser.getPhone())) {
				/**
				 * 发送红包即将过期提醒短信
				 */
				this.jmsSenderUtil.sendSms(currentUser.getPhone(),
						ISendConfigUtil.SMS_GIFTBOXHONGBAOEXPIRE,
						currentUser.getUsername(), giftboxMessage.getTitle(),
						giftboxMessage.getMoney().toString());
			}

			if (EmptyUtil.isNotEmpty(currentUser)
					&& EmptyUtil.isNotEmpty(currentUser.getEmail())) {
				/**
				 * 发送红包即将过期提醒邮件
				 */
				this.jmsSenderUtil.sendEmail(currentUser.getEmail(),
						ISendConfigUtil.EMAIL_TEMPLET_EXPIRE,
						currentUser.getUsername(), currentUser.getUsername(),
						giftboxMessage.getTitle());
			}
		}
		return giftboxHongbaoRemindCronTime;
	}

}
