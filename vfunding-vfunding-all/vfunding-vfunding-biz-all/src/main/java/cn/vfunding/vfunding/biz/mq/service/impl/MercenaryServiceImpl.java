package cn.vfunding.vfunding.biz.mq.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.ConverterUtil;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowCollectionMapper;
import cn.vfunding.vfunding.biz.borrow.dao.BorrowMapper;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.BorrowCollection;
import cn.vfunding.vfunding.biz.mq.dao.MercenaryMapper;
import cn.vfunding.vfunding.biz.mq.model.Mercenary;
import cn.vfunding.vfunding.biz.mq.service.IMercenaryService;
import cn.vfunding.vfunding.biz.returns.dao.InviteFeesMapper;
import cn.vfunding.vfunding.biz.returns.dao.ReturnfeeMapper;
import cn.vfunding.vfunding.biz.returns.model.InviteFees;
import cn.vfunding.vfunding.biz.returns.model.Returnfee;
import cn.vfunding.vfunding.biz.user.dao.UserMapper;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.common.module.activemq.message.MercenaryMessage;

@Service("mercenaryService")
public class MercenaryServiceImpl implements IMercenaryService {

	@Autowired
	private MercenaryMapper mercenaryMapper;

	@Autowired
	private InviteFeesMapper inviteFeesMapper;

	@Autowired
	private BorrowCollectionMapper borrowCollectionMapper;

	@Autowired
	private BorrowMapper borrowMapper;

	@Autowired
	private ReturnfeeMapper returnfeeMapper;

	@Autowired
	private UserMapper userMapper;

	@Override
	public void insertMercenaryInterest(MercenaryMessage mercenaryMessage) {
		Integer borrowId = mercenaryMessage.getBorrowId();
		String ip = mercenaryMessage.getIp();
		List<Mercenary> mercenaryList = this.mercenaryMapper.selectMercenaryInterest(borrowId);
		Borrow borrow = this.borrowMapper.selectById(borrowId);
		List<Returnfee> returnfeeList = this.returnfeeMapper.selectAll();
		Map<Integer, BigDecimal> returnMap = new HashMap<Integer, BigDecimal>();
		// 设定一个为0的BigDecimal
		BigDecimal zero = new BigDecimal(0);

		// 得到标的类型
		Byte style = borrow.getStyle();
		// 得到标的期限
		BigDecimal month = new BigDecimal(borrow.getTimeLimit());
		// 把返利利率放在map里
		for (Returnfee returnfee : returnfeeList) {
			returnMap.put(returnfee.getUserType(), returnfee.getCommissionFee());
		}
		returnMap.put(2, zero);
		BigDecimal apr = borrow.getApr();

		InviteFees invitefees = null;

		// 用于存放不重复的tenderID,用于更新tender表里的invitefees
		Set<Integer> tenderIdSet = new HashSet<Integer>();
		if (style == 0) {
			// 当前第几月
			BigDecimal currentMonth = new BigDecimal(1);
			for (Mercenary mercenary : mercenaryList) {
				currentMonth = mercenary.getOrder().add(new BigDecimal(1));
				// 把tenderId放进map里
				tenderIdSet.add(mercenary.getTenderId());
				// 得到投标人的投资金额
				BigDecimal account = mercenary.getAccount();
				// 三层的返利利息
				BigDecimal tfees = zero;
				BigDecimal gfees = zero;
				BigDecimal sfees = zero;
				// 实例返利表的model
				invitefees = new InviteFees();
				invitefees.setbId(borrowId);
				invitefees.setTenderId(mercenary.getTenderId());
				invitefees.setCollectionId(mercenary.getCid());
				invitefees.setAddip(ip);
				invitefees.setAddtime(DateUtil.getTime());
				invitefees.setRepaymentTime(mercenary.getRepayTime());

				// 获得第一层邀请人的ID
				Integer firstUserID = mercenary.getInviteUserid();
				// 获得第一层邀请人的级别
				Integer firstTypeId = mercenary.getTypeId();
				// 第一层不为内部理财师 和不为首席理财师
				if (firstTypeId != 31 && firstTypeId != 30) {
					BigDecimal fReturn = returnMap.get(firstTypeId);
					if (fReturn.compareTo(zero) > 0) {
						// 设置第一层的分润
						invitefees.setTuserId(firstUserID);
						// 计算返利利息
						tfees = ConverterUtil.monthlyInterest(account, fReturn, month, currentMonth);
						// 设置第一层返利利息
						invitefees.setTfees(tfees.toString());
					}
					// 判读是否有第二层关系
					UserWithBLOBs firstUser = this.userMapper.selectByPrimaryKey(firstUserID);
					if (EmptyUtil.isNotEmpty(firstUser.getInviteUserid())
							&& Integer.parseInt(firstUser.getInviteUserid()) > 0) {
						// 获得第二层邀请人ID
						Integer secondUserId = Integer.parseInt(firstUser.getInviteUserid());
						// 获得第二层邀请人信息
						UserWithBLOBs secondUser = this.userMapper.selectByPrimaryKey(secondUserId);
						// 获得第二层邀请人的级别
						Integer secondTypeId = secondUser.getTypeId();
						// 获得第二层能得到的分润利率
						BigDecimal fsReturn = zero;
						if ((secondTypeId == 29 || secondTypeId == 30) && secondTypeId > firstTypeId) {
							fsReturn = returnMap.get(secondTypeId).subtract(fReturn);
						}
						if (fsReturn.compareTo(zero) > 0) {
							// 设置第二层的分润
							invitefees.setGuserId(secondUserId);
							// 计算返利利息
							gfees = ConverterUtil.monthlyInterest(account, fsReturn, month, currentMonth);
							// 设置第二层返利利息
							invitefees.setGfees(gfees.toString());
						}
						// 判读是否有第三层关系
						if (EmptyUtil.isNotEmpty(secondUser.getInviteUserid())
								&& Integer.parseInt(secondUser.getInviteUserid()) > 0) {
							// 获得第三层邀请人的ID
							Integer thirdUserId = Integer.parseInt(secondUser.getInviteUserid());
							UserWithBLOBs thirdUser = this.userMapper.selectByPrimaryKey(thirdUserId);
							// 获得第三层邀请人的级别
							Integer thirdTypeId = thirdUser.getTypeId();
							// 获得第三层能得到的分润利率
							BigDecimal fstReturn = zero;
							if (thirdTypeId == 30) {
								fstReturn = returnMap.get(thirdTypeId).subtract(fsReturn).subtract(fReturn);
							}
							if (fstReturn.compareTo(zero) > 0) {
								// 设置第三层的分润
								invitefees.setSuserId(thirdUserId);
								sfees = ConverterUtil.monthlyInterest(account, fstReturn, month, currentMonth);
								invitefees.setSfees(sfees.toString());
							}
						}
					}
				} else {
					// 计算第一层返利
					tfees = ConverterUtil.monthlyInterest(account, returnMap.get(firstTypeId), month, currentMonth);
					invitefees.setTuserId(firstUserID);
					// 返利完成
					invitefees.setTfees(tfees.toString());
				}
				this.inviteFeesMapper.insertSelective(invitefees);
				// 更新collection的返利利息信息
				BorrowCollection bc = new BorrowCollection();
				bc.setId(mercenary.getCid());
				// 计算一条collection所需返的利息
				BigDecimal collectionInvite = tfees.add(gfees).add(sfees);
				bc.setInviteFees(collectionInvite.toString());
				this.borrowCollectionMapper.updateByPrimaryKeySelective(bc);
			}
			for (Integer tid : tenderIdSet) {
				this.mercenaryMapper.updateTenderInviteFees(tid);
			}
		} else {
			for (Mercenary mercenary : mercenaryList) {
				// 把tenderId放进map里
				tenderIdSet.add(mercenary.getTenderId());
				// 三层的返利利息
				BigDecimal tfees = zero;
				BigDecimal gfees = zero;
				BigDecimal sfees = zero;
				// 实例返利表的model
				invitefees = new InviteFees();
				invitefees.setbId(borrowId);
				invitefees.setTenderId(mercenary.getTenderId());
				invitefees.setCollectionId(mercenary.getCid());
				invitefees.setAddip(ip);
				invitefees.setAddtime(DateUtil.getTime());
				invitefees.setRepaymentTime(mercenary.getRepayTime());

				// 获得第一层邀请人的ID
				Integer firstUserID = mercenary.getInviteUserid();
				// 获得第一层邀请人的级别
				Integer firstTypeId = mercenary.getTypeId();
				// 第一层不为内部理财师 和不为首席理财师
				if (firstTypeId != 31 && firstTypeId != 30) {
					BigDecimal fReturn = returnMap.get(firstTypeId);
					if (returnMap.get(firstTypeId).compareTo(zero) > 0) {
						// 设置第一层的分润
						invitefees.setTuserId(firstUserID);
						// 计算返利利息
						tfees = ConverterUtil.serviceFee(mercenary.getInterest(), fReturn, apr);
						// 设置第一层返利利息
						invitefees.setTfees(tfees.toString());
					}
					// 查询是否有第二层关系
					UserWithBLOBs firstUser = this.userMapper.selectByPrimaryKey(firstUserID);
					if (EmptyUtil.isNotEmpty(firstUser.getInviteUserid())
							&& Integer.parseInt(firstUser.getInviteUserid()) > 0) {
						// 获得第二层邀请人的ID
						Integer secondUserId = Integer.parseInt(firstUser.getInviteUserid());
						UserWithBLOBs secondUser = this.userMapper.selectByPrimaryKey(secondUserId);
						// 获得第二层邀请人的级别
						Integer secondTypeId = secondUser.getTypeId();
						// 获得第二层能得到的分润利率
						BigDecimal fsReturn = zero;
						if ((secondTypeId == 29 || secondTypeId == 30) && secondTypeId > firstTypeId) {
							fsReturn = returnMap.get(secondTypeId).subtract(fReturn);
						}
						if (fsReturn.compareTo(zero) > 0) {
							// 设置第二层的分润
							invitefees.setGuserId(secondUserId);
							// 计算返利利息
							gfees = ConverterUtil.serviceFee(mercenary.getInterest(), fsReturn, apr);
							// 设置第二层返利利息
							invitefees.setGfees(gfees.toString());
						}
						// 查询是否有第三层关系
						if (EmptyUtil.isNotEmpty(secondUser.getInviteUserid())
								&& Integer.parseInt(secondUser.getInviteUserid()) > 0) {
							// 获得第三层邀请人的ID
							Integer thirdUserId = Integer.parseInt(secondUser.getInviteUserid());
							UserWithBLOBs thirdUser = this.userMapper.selectByPrimaryKey(thirdUserId);
							// 获得第三层邀请人的级别
							Integer thirdTypeId = thirdUser.getTypeId();
							// 获得第三层能得到的分润利率
							BigDecimal fstReturn = zero;
							if (thirdTypeId == 30) {
								fstReturn = returnMap.get(thirdTypeId).subtract(fsReturn).subtract(fReturn);
							}
							if (fstReturn.compareTo(zero) > 0) {
								// 设置第三层的分润
								invitefees.setSuserId(thirdUserId);
								sfees = ConverterUtil.serviceFee(mercenary.getInterest(), fstReturn, apr);
								invitefees.setSfees(sfees.toString());
							}
						}
					}
				} else {
					// 计算第一层返利
					tfees = ConverterUtil.serviceFee(mercenary.getInterest(), returnMap.get(firstTypeId), apr);
					invitefees.setTuserId(firstUserID);
					// 返利完成
					invitefees.setTfees(tfees.toString());
				}
				this.inviteFeesMapper.insertSelective(invitefees);
				// 更新collection的返利利息信息
				BorrowCollection bc = new BorrowCollection();
				bc.setId(mercenary.getCid());
				// 计算一条collection所需返的利息
				BigDecimal collectionInvite = tfees.add(gfees).add(sfees);
				bc.setInviteFees(collectionInvite.toString());
				this.borrowCollectionMapper.updateByPrimaryKeySelective(bc);
			}
			for (Integer tid : tenderIdSet) {
				this.mercenaryMapper.updateTenderInviteFees(tid);
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		boolean flag = new BigDecimal(1).subtract(new BigDecimal(1.1)).compareTo(new BigDecimal(0)) > 0;
		System.out.println(flag);
	}
}
