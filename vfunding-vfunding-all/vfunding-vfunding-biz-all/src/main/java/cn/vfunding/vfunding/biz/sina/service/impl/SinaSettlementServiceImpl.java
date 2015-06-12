package cn.vfunding.vfunding.biz.sina.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.account.dao.AccountSinabonusMapper;
import cn.vfunding.vfunding.biz.account.model.AccountSinabonus;
import cn.vfunding.vfunding.biz.sina.dao.SinaSettlementMapper;
import cn.vfunding.vfunding.biz.sina.model.SinaSettlement;
import cn.vfunding.vfunding.biz.sina.service.ISinaSettlementService;

@Service("sinaSettlementService")
public class SinaSettlementServiceImpl implements ISinaSettlementService {
	
	@Autowired
	private SinaSettlementMapper sinaSettlementMapper;
	
	@Autowired
	private AccountSinabonusMapper accountSinabonusMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sinaSettlementMapper.deleteByPrimaryKey(id);
	}

//	@Override
//	public int insert(SinaSettlement record) {
//		return sinaSettlementMapper.insert(record);
//	}

	@Override
	public int insertSelective(SinaSettlement record) {
		return sinaSettlementMapper.insertSelective(record);
	}

	@Override
	public SinaSettlement selectByPrimaryKey(Integer id) {
		return sinaSettlementMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SinaSettlement record) {
		return sinaSettlementMapper.updateByPrimaryKeySelective(record);
	}

//	@Override
//	public int updateByPrimaryKey(SinaSettlement record) {
//		return sinaSettlementMapper.updateByPrimaryKey(record);
//	}

	@Override
	public List<Integer> needSyncBonusUserIds() {
		return sinaSettlementMapper.needSyncBonusUserIds();
	}

	@Override
	public SinaSettlement createBaseData(Integer userId,String balance_str,String availableBalance_str,String bonus_str) {
		BigDecimal sinaBalance = new BigDecimal(0);
		BigDecimal sinaAvailableBalance = new BigDecimal(0);
		BigDecimal sinaBonusYesterday = new BigDecimal(0);
		BigDecimal sinaBonusTotal = new BigDecimal(0);
		String[] bonus = bonus_str.split("\\^");
		if (!balance_str.equals("null")) {
			sinaBalance = new BigDecimal(balance_str);
		}
		if (!availableBalance_str.equals("null")) {
			sinaAvailableBalance = new BigDecimal(availableBalance_str);
		}
		if (!bonus[0].equals("null")) {
			sinaBonusYesterday = new BigDecimal(bonus[0]);
		}
		if (!bonus[2].equals("null")) {
			sinaBonusTotal = new BigDecimal(bonus[2]);
		}
		SinaSettlement entity = new SinaSettlement();
		entity.setUserId(userId);
		entity.setSinaBalance(sinaBalance);
		entity.setSinaAvailableBalance(sinaAvailableBalance);
		entity.setSinaBonusYesterday(sinaBonusYesterday);
		entity.setSinaBonusTotal(sinaBonusTotal);
		entity.setStatus(0);
		entity.setSyncBonusRule(0);
		entity.setAddtime(new Date());
		entity.setSyncBonusStatus(0);
		insertSelective(entity);
		return entity;
	}

	@Override
	public SinaSettlement createErrorBaseData(Integer userId) {
		SinaSettlement entity = new SinaSettlement();
		entity.setUserId(userId);
		entity.setStatus(1);
		entity.setAddtime(new Date());
		return entity;
	}
	
	@Override
	public List<SinaSettlement> selectNeedSyncDataBySyncStatus() {
		return sinaSettlementMapper.selectNeedSyncDataBySyncStatus();
	}

	@Override
	public BigDecimal selectSinaBonusHistoryTotalByUserId(Integer userId) {
		AccountSinabonus asb = accountSinabonusMapper.selectByPrimaryKey(userId);
		if(asb==null){
			return new BigDecimal(0);
		}else{
			return asb.getSinaBonusTotal();
		}
	}

	@Override
	public int saveSinaBonus(SinaSettlement entity) {
		AccountSinabonus asb = accountSinabonusMapper.selectByPrimaryKey(entity.getUserId());
		if(asb==null){
			asb = new AccountSinabonus();
			asb.setUserId(entity.getUserId());
			asb.setSinaBonusTotal(new BigDecimal(0));
			asb.setSinaBonusPrevious(entity.getSyncBonusRealmoney());
			asb.setSinaBonusTotal(asb.getSinaBonusTotal().add(entity.getSyncBonusRealmoney()));
			asb.setUpdatetime(new Date());
			return accountSinabonusMapper.insertSelective(asb);
		}else{
			asb.setSinaBonusPrevious(entity.getSyncBonusRealmoney());
			asb.setSinaBonusTotal(asb.getSinaBonusTotal().add(entity.getSyncBonusRealmoney()));
			asb.setUpdatetime(new Date());
			return accountSinabonusMapper.updateByPrimaryKeySelective(asb);
		}
	}
	
}
