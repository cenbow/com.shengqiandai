package cn.vfunding.vfunding.biz.useraction_activity.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.ConverterUtil;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.hikes.dao.HikesCardMapper;
import cn.vfunding.vfunding.biz.hikes.model.HikesCard;
import cn.vfunding.vfunding.biz.message.service.IGiftboxMessageService;
import cn.vfunding.vfunding.biz.returns.dao.InviteFeesMapper;
import cn.vfunding.vfunding.biz.returns.dao.ReturnfeeMapper;
import cn.vfunding.vfunding.biz.returns.model.InviteFees;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.useraction_activity.dao.FriendRebateMapper;
import cn.vfunding.vfunding.biz.useraction_activity.dao.HikesRebateMapper;
import cn.vfunding.vfunding.biz.useraction_activity.dao.InternalRebateMapper;
import cn.vfunding.vfunding.biz.useraction_activity.model.FriendRebate;
import cn.vfunding.vfunding.biz.useraction_activity.model.HikesRebate;
import cn.vfunding.vfunding.biz.useraction_activity.model.InternalRebate;
import cn.vfunding.vfunding.biz.useraction_activity.service.IRebateService;

@Service
public class RebateServerImpl implements IRebateService {

	@Autowired
	private BorrowMapper borrowMapper;

	@Autowired
	private InternalRebateMapper internalMapper;

	@Autowired
	private HikesRebateMapper hikesMapper;

	@Autowired
	private FriendRebateMapper friendRebateMapper;

	@Autowired
	private HikesCardMapper hikesCardMapper;

	@Autowired
	private IGiftboxMessageService giftboxMessageService;

	@Autowired
	private ReturnfeeMapper returnfeeMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private InviteFeesMapper inviteFeesMapper;

	Logger logger = LoggerFactory.getLogger("internalRebateLog");

	@Override
	public void internalRebate(Integer borrowId) {
		// TODO Auto-generated method stub
		Borrow borrow = this.borrowMapper.selectById(borrowId);
		if (borrow.getBiaoType().equals("huodong")) {
			logger.info("活动标的,集团用户不给返利");
		} else {
			List<InternalRebate> internalRebateList = this.internalMapper
					.selectInternalInterest(borrowId);
			// 获取标的月份
			Integer timeLimit = Integer.parseInt(borrow.getTimeLimit()
					.toString());
			BigDecimal rfee = this.returnfeeMapper
					.selectReturnfeeByMonth(timeLimit);
			if (rfee == null) {
				rfee = this.returnfeeMapper.selectReturnfeeByMonth(1000);
			}
			for (InternalRebate internalRebate : internalRebateList) {
				// 当前月份
				BigDecimal currentMonth = internalRebate.getOrder().add(
						new BigDecimal(1));
				BigDecimal money = new BigDecimal(0);
				// 等额本息
				if (borrow.getStyle() == 0 && borrow.getIsday() == 0) {
					money = ConverterUtil.monthlyInterest(internalRebate
							.getAccount(), rfee, new BigDecimal(timeLimit),
							currentMonth);
				} else {
					money = ConverterUtil
							.serviceFee(internalRebate.getInterest(), rfee,
									borrow.getApr());
				}
				this.giftboxMessageService.insertGiftboxMessage(
						"集团用户返利",
						"您投资的标的[<a href='/borrow/borrowDetail/"
								+ borrow.getId() + "' target='_blank'>"
								+ borrow.getName() + "</a>]，获得集团用户投资金额年化"
								+ rfee + "%的收益提升返利" + money
								+ "元。生效后点击“使用”即可充值到您的现金账户。", money,
						internalRebate.getUserId(),
						internalRebate.getRepayTime(),
						internalRebate.getTenderId(), 1);
			}
		}
	}

	@Override
	public void hikesRebate(Integer borrowId) {
		// TODO Auto-generated method stub
		Borrow borrow = this.borrowMapper.selectById(borrowId);
		List<HikesRebate> hikesRebateList = this.hikesMapper
				.selectHikesInterest(borrowId);
		// 获取标的月份
		Integer timeLimit = Integer.parseInt(borrow.getTimeLimit().toString());

		Map<Integer, BigDecimal> map = new HashMap<Integer, BigDecimal>();
		// 等额本息
		for (HikesRebate hikesRebate : hikesRebateList) {
			// 当前月份
			BigDecimal currentMonth = hikesRebate.getOrder().add(
					new BigDecimal(1));
			BigDecimal money = new BigDecimal(0);
			if (borrow.getStyle() == 0 && borrow.getIsday() == 0) {
				money = ConverterUtil.monthlyInterest(hikesRebate.getAccount(),
						hikesRebate.getHikesRate(), new BigDecimal(timeLimit),
						currentMonth);
			} else {
				money = ConverterUtil.serviceFee(hikesRebate.getInterest(),
						hikesRebate.getHikesRate(), borrow.getApr());
			}
			this.giftboxMessageService.insertGiftboxMessage(
					"加息卡返利",
					"您使用加息卡投资的标的[[<a href='/borrow/borrowDetail/"
							+ borrow.getId() + "' target='_blank'>"
							+ borrow.getName() + "</a>]，获得年化"
							+ hikesRebate.getHikesRate() + "%的收益提升，返利" + money
							+ "元。生效后点击“使用”即可充值到您的现金账户。", money,
					hikesRebate.getUserId(), hikesRebate.getRepayTime(),
					hikesRebate.getTenderId(), 2);
			map.put(hikesRebate.getUserId(), hikesRebate.getHikesRate());

		} // 更新加息卡余额
			// 查询用户加息卡
		for (Integer key : map.keySet()) {
			HikesCard hikesCard = this.hikesCardMapper.selectByPrimaryKey(key);
			hikesCard.setUserId(key);
			hikesCard.setNoUseRate(hikesCard.getNoUseRate().subtract(
					map.get(key)));
			hikesCardMapper.updateByPrimaryKeySelective(hikesCard);
		}

	}

	@Override
	public void friendRebate(Integer borrowId) {
		// TODO Auto-generated method stub
		List<FriendRebate> friendRebateList = this.friendRebateMapper
				.selectFriendInterest(borrowId);
		Borrow borrow = this.borrowMapper.selectById(borrowId);
		BigDecimal currentMonth;
		// 用于存放不重复的tenderID,用于更新tender表里的invitefees
		Set<Integer> tenderIdSet = new HashSet<Integer>();
		InviteFees invitefees = new InviteFees();
		for (FriendRebate friendRebate : friendRebateList) {
			currentMonth = friendRebate.getOrder().add(new BigDecimal(1));
			// 把tenderId放进map里
			tenderIdSet.add(friendRebate.getTenderId());
			// 实例返利表的model
			invitefees = new InviteFees();
			invitefees.setbId(borrowId);
			invitefees.setTenderId(friendRebate.getTenderId());
			invitefees.setCollectionId(friendRebate.getCid());
			invitefees.setAddip("127.0.0.1");
			invitefees.setAddtime(DateUtil.getTime());
			invitefees.setRepaymentTime(Long.toString(friendRebate
					.getRepayTime().getTime() / 1000));
			// 获得第一层邀请人的ID
			Integer firstUserID = friendRebate.getInviteUserid();
			// 获得第一层邀请人的级别
			Integer firstTypeId = friendRebate.getTypeId();
			// 三层的返利利率
			BigDecimal firstInternalFees = new BigDecimal("1.08");
			BigDecimal firstOutsideFees = new BigDecimal("0.72");
			BigDecimal secondFees = new BigDecimal("0.09");
			BigDecimal thirdFees = new BigDecimal("0.09");
			// 三层的返利金额
			BigDecimal firstInternalMoney = new BigDecimal(0);
			BigDecimal firstOutsideMoney = new BigDecimal(0);
			BigDecimal secondMoney = new BigDecimal(0);
			BigDecimal thirdMoney = new BigDecimal(0);
			// 计算返利利息
			if (borrow.getStyle() == 0 && borrow.getIsday() == 0) {
				firstInternalMoney = ConverterUtil.monthlyInterest(
						friendRebate.getAccount(), firstInternalFees,
						new BigDecimal(borrow.getTimeLimit()), currentMonth);
				firstOutsideMoney = ConverterUtil.monthlyInterest(
						friendRebate.getAccount(), firstOutsideFees,
						new BigDecimal(borrow.getTimeLimit()), currentMonth);
				secondMoney = ConverterUtil.monthlyInterest(friendRebate
						.getAccount(), secondFees,
						new BigDecimal(borrow.getTimeLimit()), currentMonth);
				thirdMoney = ConverterUtil.monthlyInterest(friendRebate
						.getAccount(), thirdFees,
						new BigDecimal(borrow.getTimeLimit()), currentMonth);
			} else {
				firstInternalMoney = ConverterUtil.serviceFee(
						friendRebate.getInterest(), firstInternalFees,
						borrow.getApr());
				firstOutsideMoney = ConverterUtil.serviceFee(
						friendRebate.getInterest(), firstOutsideFees,
						borrow.getApr());
				secondMoney = ConverterUtil
						.serviceFee(friendRebate.getInterest(), secondFees,
								borrow.getApr());
				thirdMoney = ConverterUtil.serviceFee(
						friendRebate.getInterest(), thirdFees, borrow.getApr());
			}
			// 返利最低1分钱
			if (firstInternalMoney.doubleValue() < 0.01) {
				firstInternalMoney = new BigDecimal("0.01");
			}
			if (firstOutsideMoney.doubleValue() < 0.01) {
				firstOutsideMoney = new BigDecimal("0.01");
			}
			if (secondMoney.doubleValue() < 0.01) {
				secondMoney = new BigDecimal("0.01");
			}
			if (thirdMoney.doubleValue() < 0.01) {
				thirdMoney = new BigDecimal("0.01");
			}
			if (firstTypeId == 31 || firstTypeId == 27) {
				// 集团用户 ,只返一层
				this.giftboxMessageService.insertGiftboxMessage("平台理财师返利",
						"亲爱的用户：您邀请的好友已经完成投资，您获得" + firstInternalMoney
								+ "元返利。生效后点击使用即充值到账户余额中", firstInternalMoney,
						firstUserID, new Date(), friendRebate.getTenderId(), 3);
				invitefees.setTuserId(firstUserID);
				invitefees.setTfees(firstInternalMoney.toString());
			} else {
				this.giftboxMessageService.insertGiftboxMessage("平台理财师返利",
						"亲爱的用户：您邀请的好友已经完成投资，您获得" + firstOutsideMoney
								+ "元返利。生效后点击使用即充值到账户余额中", firstOutsideMoney,
						firstUserID, new Date(), friendRebate.getTenderId(), 3);
				invitefees.setTuserId(firstUserID);
				invitefees.setTfees(firstOutsideMoney.toString());
				// 获取第一层的用户信息
				UserWithBLOBs firstUser = this.userMapper
						.selectByPrimaryKey(firstUserID);
				// 判读是否有第二层关系
				if (EmptyUtil.isNotEmpty(firstUser.getInviteUserid())
						&& Integer.parseInt(firstUser.getInviteUserid()) > 0) {
					// 获得第二层邀请人ID
					Integer secondUserId = Integer.parseInt(firstUser
							.getInviteUserid());
					// 获得第二层邀请人信息
					UserWithBLOBs secondUser = this.userMapper
							.selectByPrimaryKey(secondUserId);
					// 获得第二层邀请人的级别
					Integer secondTypeId = secondUser.getTypeId();
					// 集团用户,只反到当层
					this.giftboxMessageService.insertGiftboxMessage("平台理财师返利",
							"亲爱的用户：您的二层好友已经完成投资，您获得" + secondMoney
									+ "元返利。生效后点击使用即充值到账户余额中", secondMoney,
							secondUserId, new Date(),
							friendRebate.getTenderId(), 3);
					invitefees.setGuserId(secondUserId);
					invitefees.setGfees(secondMoney.toString());
					// 第二层如果是内部用户,就跳过本次循环
					if (secondTypeId == 27 || secondTypeId == 31) {
						continue;
					}
					// 判读是否有第三层关系
					if (EmptyUtil.isNotEmpty(secondUser.getInviteUserid())
							&& Integer.parseInt(secondUser.getInviteUserid()) > 0) {
						// 获得第三层邀请人的ID
						Integer thirdUserId = Integer.parseInt(secondUser
								.getInviteUserid());
						this.giftboxMessageService.insertGiftboxMessage(
								"平台理财师返利", "亲爱的用户：您的三层好友已经完成投资，您获得"
										+ thirdMoney + "元返利。生效后点击使用即充值到账户余额中",
								thirdMoney, thirdUserId, new Date(),
								friendRebate.getTenderId(), 3);
						invitefees.setSuserId(thirdUserId);
						invitefees.setSfees(thirdMoney.toString());
					}
				}
			}
			this.inviteFeesMapper.insertSelective(invitefees);
		}
	}

}
