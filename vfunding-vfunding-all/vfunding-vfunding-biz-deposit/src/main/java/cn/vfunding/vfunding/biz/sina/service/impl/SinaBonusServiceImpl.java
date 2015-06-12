package cn.vfunding.vfunding.biz.sina.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.account.beanutil.AccountLogUtil;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.account.service.IAccountLogService;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.sina.model.SinaSettlement;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaBonusService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSettlementService;

@Service("sinaBonusService")
public class SinaBonusServiceImpl implements ISinaBonusService {

	@Autowired
	private ISinaSettlementService sinaSettlementService;

	@Autowired
	private IQuerySinaService querySinaService;

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IAccountLogService accountLogService;

	/**
	 * 创建新浪存钱罐结算数据
	 * 
	 * @return
	 * @author louchen 2015-03-25
	 */
	@Override
	public Boolean loadCreateBaseSinaSettlementData() {
		Boolean result = false;
		List<Integer> userIds = sinaSettlementService.needSyncBonusUserIds();
		if (userIds.size() > 0) {
			for (Integer userId : userIds) {
				try {
					createSingleBaseSinaSettlementData(userId);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			result = true;
		}
		return result;
	}

	/**
	 * 创建个人结算数据
	 * 
	 * @param userId
	 * @author louchen 2015-03-25
	 */
	private void createSingleBaseSinaSettlementData(Integer userId)	throws Exception {
		if (userId.intValue() > 0) {
			Map<String, String> map = null;
			try {
				map = querySinaService.doQueryBalance(userId);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (map != null && map.get("success").equals("success")) {
				sinaSettlementService.createBaseData(userId,
						map.get("balance"), map.get("availableBalance"),
						map.get("bonus"));
			}else{
				sinaSettlementService.createErrorBaseData(userId);
			}
		}
	}

	/**
	 * 同步新浪存钱罐结算数据到微积金账户余额
	 * 
	 * @return
	 * @author louchen 2015-03-25
	 */
	@Override
	public Boolean loadSyncSinaSettlementData() {
		Boolean result = false;
		List<SinaSettlement> list = sinaSettlementService.selectNeedSyncDataBySyncStatus();
		if(list.size()>0){
			for (SinaSettlement needSyncEntity : list) {
				try {
					syncSingleSinaSettlementData(needSyncEntity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			result = true;
		}
		return result;
	}

	/**
	 * 同步昨日生息金额至个人账户
	 * 
	 * @param entity
	 * @author louchen 2015-03-25
	 */
	private void syncSingleSinaSettlementData(SinaSettlement entity) throws Exception {
		if (entity != null) {
			if (entity.getSyncBonusStatus() != null
				&& entity.getSyncBonusStatus().equals(0)
				&& entity.getStatus() != null
				&& entity.getStatus().equals(0)) {
					BigDecimal zero = new BigDecimal(0);
					BigDecimal bonusHistoryTotal = sinaSettlementService.selectSinaBonusHistoryTotalByUserId(entity.getUserId());
					BigDecimal syncBonusRealmoney = new BigDecimal(0);
					Integer syncBonusRule=-1;
					if(bonusHistoryTotal.compareTo(zero) == 0){
						//同步一共存钱罐收益
						syncBonusRule = 1;
						syncBonusRealmoney = entity.getSinaBonusTotal();
					}else if(bonusHistoryTotal.compareTo(zero) > 0 
							&& (entity.getSinaBonusTotal().subtract(bonusHistoryTotal)).compareTo(entity.getSinaBonusYesterday()) == 0 ){
						//同步今日存钱罐收益
						syncBonusRule = 0;
						syncBonusRealmoney = entity.getSinaBonusYesterday();
					}else if(bonusHistoryTotal.compareTo(zero) > 0 
							&& (entity.getSinaBonusTotal().subtract(bonusHistoryTotal)).compareTo(zero) > 0 ){
						//同步差异存钱罐收益
						syncBonusRule = 2;
						syncBonusRealmoney = entity.getSinaBonusTotal().subtract(bonusHistoryTotal);
					}else{
						//无需同步存钱罐收益
						syncBonusRealmoney = null;
					}					
					if (syncBonusRealmoney!=null && syncBonusRealmoney.compareTo(zero) > 0) {
						entity.setSyncBonusRule(syncBonusRule);
						entity.setSyncBonusRealmoney(syncBonusRealmoney);
						addMoney(entity);
					} else {
						entity.setSyncBonusStatus(-1);
						entity.setSyncBonusRule(-1);
						sinaSettlementService.updateByPrimaryKeySelective(entity);
					}
			}
		}
	}
	
	/**
	 * 增加金额,更新account和新增accountLog
	 * @param needSyncEntity
	 * @throws Exception
	 * @author louchen 2015-03-25
	 */
	private void addMoney(SinaSettlement entity) throws Exception{
		Account userAccount = accountService.selectByUserId(entity.getUserId());
		BigDecimal beforeUseMoney = EmptyUtil.isNotEmpty(userAccount.getUseMoney())?userAccount.getUseMoney():new BigDecimal(0);
		userAccount.setUseMoney(EmptyUtil.isNotEmpty(userAccount
				.getUseMoney()) ? userAccount.getUseMoney().add(
						entity.getSyncBonusRealmoney())
				: entity.getSyncBonusRealmoney());
		userAccount.setTotal(EmptyUtil.isNotEmpty(userAccount
				.getTotal()) ? userAccount.getTotal().add(
						entity.getSyncBonusRealmoney())
				: entity.getSyncBonusRealmoney());
		AccountLog userAccountLog = AccountLogUtil.createLogForSinaBonus(entity,userAccount);
		accountService.updateByPrimaryKeySelective(userAccount);
		accountLogService.insertSelective(userAccountLog);
		entity.setSyncBonusStatus(1);
		entity.setSyncBonusDate(new Date());
		entity.setSyncBonusBeforeVfundingUsemoney(beforeUseMoney);
		entity.setSyncBonusAfterVfundingUsemoney(userAccount.getUseMoney());
		sinaSettlementService.updateByPrimaryKeySelective(entity);
		sinaSettlementService.saveSinaBonus(entity);
	}

    /**
     * 同步所有用户的存钱罐收益
     * @return
     * @author louchen 2015-03-26
     */
	@Override
	public Boolean executeAll() {
		if(loadCreateBaseSinaSettlementData()){
			return loadSyncSinaSettlementData();
		}
		return false;
	}
}
