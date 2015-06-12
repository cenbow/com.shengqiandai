package cn.vfunding.vfunding.biz.mq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowAutoMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.model.BorrowAuto;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.mq.service.IBorrowAutoService;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

@Service
public class BorrowAutoServiceImpl implements IBorrowAutoService {

	@Autowired
	private BorrowAutoMapper borrowAutoMapper;
	@Autowired
	private BorrowMapper borrowMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IBorrowTenderService borrowTenderService;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Override
	public BorrowAuto selectByPrimaryKey(Integer id) {
		return borrowAutoMapper.selectByPrimaryKey(id);
	}

	@Override
	public BorrowAuto selectBorrowAutoByUserId(Integer userId) {
		return borrowAutoMapper.selectBorrowAutoByUserId(userId);
	}

	/**
	 * 设置自动投标 return 1:开启自动投标；0关闭自动投标
	 * 
	 * @author liuhuan
	 */
	@Override
	public int updateBorrowAuto(BorrowAuto auto) {
		BorrowAuto userAuto = borrowAutoMapper.selectBorrowAutoByUserId(auto
				.getUserId());
		if (userAuto == null) {// 插入
			userAuto = new BorrowAuto();
			userAuto.setTenderAccount(auto.getTenderAccount());
			userAuto.setStatus(auto.getStatus());
			if (auto.getTimelimitMonthFirst() != null
					&& auto.getTimelimitMonthLast() != null) {
				userAuto.setTimelimitStatus(1);
				userAuto.setTimelimitMonthFirst(auto.getTimelimitMonthFirst());
				userAuto.setTimelimitMonthLast(auto.getTimelimitMonthLast());
			} else {
				userAuto.setTimelimitStatus(0);
			}
			if (auto.getAprFirst() != null && auto.getAprLast() != null) {
				userAuto.setAprStatus(true);
				userAuto.setAprFirst(auto.getAprFirst());
				userAuto.setAprLast(auto.getAprLast());
			} else {
				userAuto.setAprStatus(false);
			}
			if (auto.getBorrowStyle().intValue() == -1) {
				userAuto.setBorrowStyleStatus(0);
			} else {
				userAuto.setBorrowStyleStatus(1);
				userAuto.setBorrowStyle(auto.getBorrowStyle());
			}
			userAuto.setAwardStatus(false);
			userAuto.setFastStatus(true);
			userAuto.setXinStatus(true);
			userAuto.setJinStatus(true);
			userAuto.setUserId(auto.getUserId());
			userAuto.setAddtime(Integer.parseInt(DateUtil.getTime()));
			borrowAutoMapper.insertSelective(userAuto);

		} else { // 更新
			userAuto.setTenderAccount(auto.getTenderAccount());
			userAuto.setStatus(auto.getStatus());
			if (auto.getTimelimitMonthFirst() != null
					&& auto.getTimelimitMonthLast() != null) {
				userAuto.setTimelimitMonthFirst(auto.getTimelimitMonthFirst());
				userAuto.setTimelimitMonthLast(auto.getTimelimitMonthLast());
				userAuto.setTimelimitStatus(1);
			} else {
				userAuto.setTimelimitStatus(0);
			}
			if (auto.getAprFirst() != null && auto.getAprLast() != null) {
				userAuto.setAprStatus(true);
				userAuto.setAprFirst(auto.getAprFirst());
				userAuto.setAprLast(auto.getAprLast());
			} else {
				userAuto.setAprStatus(false);
			}
			if (auto.getBorrowStyle().intValue() == -1) {
				userAuto.setBorrowStyleStatus(0);
			} else {
				userAuto.setBorrowStyleStatus(1);
				userAuto.setBorrowStyle(auto.getBorrowStyle());
			}
			userAuto.setAwardStatus(false);
			userAuto.setFastStatus(true);
			userAuto.setXinStatus(true);
			userAuto.setJinStatus(true);
			userAuto.setUserId(auto.getUserId());
			userAuto.setAddtime(Integer.parseInt(DateUtil.getTime()));
			borrowAutoMapper.updateByPrimaryKeySelective(userAuto);
		}
		if (userAuto.getStatus()) {
			// 发送设置成功短信
			String username = null;
			if (EmptyUtil
					.isNotEmpty(UserSession.getUserSession().getUserName())) {
				username = UserSession.getUserSession().getUserName();
			} else {
				UserWithBLOBs u = this.userMapper.selectByPrimaryKey(userAuto
						.getUserId());
				if (EmptyUtil.isNotEmpty(u)
						&& EmptyUtil.isNotEmpty(u.getUsername())) {
					username = u.getUsername();
				}
			}
			if (EmptyUtil.isNotEmpty(username)) {
				this.jmsSenderUtil.sendSms(UserSession.getUserSession().getPhone(),
						ISendConfigUtil.SMS_SETAUTOINVEST, username);
			}

		}
		return userAuto.getStatus() ? 1 : 0;
	}

}
