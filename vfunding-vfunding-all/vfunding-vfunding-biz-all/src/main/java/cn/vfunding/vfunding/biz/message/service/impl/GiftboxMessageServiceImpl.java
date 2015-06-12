package cn.vfunding.vfunding.biz.message.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.beanutil.AccountLogUtil;
import cn.vfunding.vfunding.biz.account.dao.AccountLogMapper;
import cn.vfunding.vfunding.biz.account.dao.AccountMapper;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.message.dao.GiftboxMessageMapper;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.message.service.IGiftboxMessageService;

@Service("giftboxMessageService")
public class GiftboxMessageServiceImpl implements IGiftboxMessageService {

	@Autowired
	private GiftboxMessageMapper giftboxMessageMapper;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private AccountLogMapper accountLogMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.giftboxMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(GiftboxMessage record) {
		return this.giftboxMessageMapper.insert(record);
	}

	@Override
	public int insertSelective(GiftboxMessage record) {
		return this.giftboxMessageMapper.insertSelective(record);
	}

	@Override
	public GiftboxMessage selectByPrimaryKey(Integer id) {
		return this.giftboxMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(GiftboxMessage record) {
		return this.giftboxMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(GiftboxMessage record) {
		return this.giftboxMessageMapper.updateByPrimaryKey(record);
	}

	/**
	 * 礼品列表
	 * 
	 * @param pageSearch
	 * @return
	 * @author louchen 2014-12-22
	 */
	@Override
	public List<GiftboxMessage> selectGiftboxMessageListPage(PageSearch pageSearch) {
		return this.giftboxMessageMapper.selectGiftboxMessageListPage(pageSearch);
	}

	/**
	 * 未使用的礼品总数
	 * 
	 * @param userId
	 * @return
	 * @author louchen 2014-12-22
	 */
	@Override
	public Integer selectUnUseCountByUserId(Integer userId) {
		List<GiftboxMessage> list = this.giftboxMessageMapper.selectUnUseByUserId(userId);
		return list.size();
	}

	/**
	 * 更新礼品查看状态
	 * 
	 * @param record
	 * @return
	 * @author louchen 2014-12-22
	 */
	@Override
	public int updateGiftIsLook(GiftboxMessage record) {
		if (EmptyUtil.isNotEmpty(record)) {
			// 已查看
			record.setIsLook(1);
			return this.updateByPrimaryKeySelective(record);
		} else {
			return 0;
		}

	}

	/**
	 * 使用礼品--校验数据是否正确
	 * 
	 * @return
	 * @author louchen 2014-12-22
	 */
	private Json useGiftCheck(Integer userId, GiftboxMessage gm) {
		Json json = new Json();
		if (EmptyUtil.isNotEmpty(userId) && EmptyUtil.isNotEmpty(gm) && EmptyUtil.isNotEmpty(gm.getReceiveUser()) && userId.equals(gm.getReceiveUser())) {
			if (EmptyUtil.isNotEmpty(gm.getStatus()) && gm.getStatus().equals(0)) {
				if (EmptyUtil.isNotEmpty(gm.getType()) && gm.getType() >= 0 && gm.getType() <= 3) {
					/**
					 * 重新计算生效时间 例子 原生效时间2015-04-13 15:14:10 新生效时间2015-04-13
					 * 00:00:00
					 */
					Calendar newTakeTime = Calendar.getInstance();
					newTakeTime.setTime(gm.getTakeTime());
					newTakeTime.set(Calendar.HOUR_OF_DAY, 00);
					newTakeTime.set(Calendar.MINUTE, 00);
					newTakeTime.set(Calendar.SECOND, 00);
					if (newTakeTime.getTime().compareTo(new Date()) < 0) {
						if (gm.getLoseTime().compareTo(new Date()) > 0) {
							json.setSuccess(true);
						} else {
							json.setMsg("已超过失效时间(" + DateUtil.parseDateTime(gm.getLoseTime()) + "),礼品不能领取。");
							gm.setStatus(2);
							this.updateByPrimaryKeySelective(gm);
						}
					} else {
						json.setMsg("还没到生效时间(" + DateUtil.parseDateTime(newTakeTime.getTime()) + ")，礼品不能领取。");
					}
				} else {
					json.setMsg("礼品类型不正确");
				}
			} else {
				if (gm.getStatus().equals(1)) {
					json.setMsg("礼品已领取。");
				} else if (gm.getStatus().equals(2)) {
					json.setMsg("礼品已过期。");
				} else {
					json.setMsg("礼品状态不正确。");
				}
			}
		} else {
			json.setMsg("您不是礼品持有者!");
		}
		return json;
	}

	private Json useGift(GiftboxMessage gm) {
		Json json = new Json();
		try {
			if (this.useGiftMethod(gm)) {
				json.setSuccess(true);
				json.setMsg("礼品领取成功。");
			} else {
				json.setMsg("礼品领取失败。");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 使用礼品
	 * 
	 * @param gm
	 * @return
	 * @author louchen 2014-12-22
	 */
	private Boolean useGiftMethod(GiftboxMessage gm) {
		// 使用红包
		// if(gm.getType().equals(0)){
		// 增加账户资金
		return this.updateAccountUsedMoney(gm);
		// }
		// //使用现金券
		// else if(gm.getType().equals(1)){
		// //代开发
		// }
		// //使用提现券
		// else if(gm.getType().equals(2)){
		// //代开发
		// }
		// //使用其他奖品
		// else if(gm.getType().equals(3)){
		// //代开发
		// }
		// return null;
	}

	/**
	 * 增加账户资金和增加资金记录
	 * 
	 * @param gm
	 * @return
	 */
	private Boolean updateAccountUsedMoney(GiftboxMessage gm) {
		Account userAccount = this.accountMapper.selectByUserId(gm.getReceiveUser());
		if (EmptyUtil.isNotEmpty(userAccount) && userAccount.getId() > 0) {
			userAccount.setUseMoney(EmptyUtil.isNotEmpty(userAccount.getUseMoney()) ? userAccount.getUseMoney().add(gm.getMoney()) : gm.getMoney());
			userAccount.setTotal(EmptyUtil.isNotEmpty(userAccount.getTotal()) ? userAccount.getTotal().add(gm.getMoney()) : gm.getMoney());
			AccountLog userAccountLog = AccountLogUtil.createLogForGift(gm, userAccount);
			this.accountMapper.updateByPrimaryKeySelective(userAccount);
			this.accountLogMapper.insertSelective(userAccountLog);
			this.updateUsedStatus(gm);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 更新礼品使用记录
	 * 
	 * @param gm
	 * @author louchen 2014-12-22
	 */
	private void updateUsedStatus(GiftboxMessage gm) {
		gm.setStatus(1);
		gm.setIsLook(1);
		gm.setUpdateTime(new Date());
		this.giftboxMessageMapper.updateByPrimaryKeySelective(gm);
	}

	/**
	 * 未查看的礼品总数
	 * 
	 * @param userId
	 * @return
	 * @author louchen 2014-12-22
	 */
	@Override
	public Integer selectUnLookCountByUserId(Integer userId) {
		List<GiftboxMessage> list = this.giftboxMessageMapper.selectUnLookByUserId(userId);
		return list.size();
	}

	@Override
	public List<GiftboxMessage> selectAll(PageSearch pageSearch) {
		return this.giftboxMessageMapper.selectAllListPage(pageSearch);
	}

	@Override
	public void insertGiftboxMessage(String title, String content, BigDecimal money, Integer userId, Date taskTime, Integer tenderId, Integer type) {
		GiftboxMessage giftboxMessage = new GiftboxMessage();
		giftboxMessage.setStatus(0);
		giftboxMessage.setIsLook(0);
		giftboxMessage.setType(type);
		giftboxMessage.setTitle(title);
		giftboxMessage.setContent(content);
		giftboxMessage.setMoney(money);
		giftboxMessage.setTenderId(tenderId);
		giftboxMessage.setSendUser(0);
		giftboxMessage.setReceiveUser(userId);
		giftboxMessage.setTakeTime(taskTime);
		giftboxMessage.setLoseTime(DateUtil.getNextDayTime(taskTime, 30));
		giftboxMessage.setAddtime(new Date());
		giftboxMessage.setAddip("127.0.0.1");
		this.giftboxMessageMapper.insertSelective(giftboxMessage);
	}

	@Override
	public String selectUseMoney(Map<String, Object> map) {
		return giftboxMessageMapper.selectUseMoney(map);
	}

	@Override
	public String selectNoUseMoney(Map<String, Object> map) {
		return giftboxMessageMapper.selectNoUseMoney(map);
	}

	@Override
	public List<GiftboxMessage> selectUseMoneyAny(Map<String, Object> map) {
		return giftboxMessageMapper.selectUseMoneyAny(map);
	}

	@Override
	public synchronized Json useGiftTrade(Integer giftId, Integer loginUserId) throws Exception {
		Json json = new Json();
//		json.setSuccess(false);
//		json.setMsg("暂停使用");
//		return json;
		//TODO
		GiftboxMessage gm = this.selectByPrimaryKey(giftId);
		json = this.useGiftCheck(loginUserId, gm);
		if (json.getSuccess()) {
			json = this.useGift(gm);
			json.setSuccess(true);

		}
		return json;
	}

	@Override
	public String selectHongbaoSumUsed(Integer userId) {
		return this.giftboxMessageMapper.selectHongbaoSumUsed(userId);
	}

	@Override
	public String selectHongbaoSumExpire(Integer userId) {
		return this.giftboxMessageMapper.selectHongbaoSumExpire(userId);
	}

	@Override
	public List<GiftboxMessage> selectHongbaoExpireByStatus(Integer status) {
		return this.giftboxMessageMapper.selectHongbaoExpireByStatus(status);
	}

	@Override
	public List<GiftboxMessage> selectByLose() {
		return this.giftboxMessageMapper.selectByLose();
	}

}
