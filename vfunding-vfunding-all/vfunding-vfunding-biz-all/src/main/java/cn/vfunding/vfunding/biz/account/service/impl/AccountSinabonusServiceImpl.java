package cn.vfunding.vfunding.biz.account.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.account.dao.AccountSinabonusMapper;
import cn.vfunding.vfunding.biz.account.model.AccountSinabonus;
import cn.vfunding.vfunding.biz.account.service.IAccountSinabonusService;

@Service("accountSinabonusService")
public class AccountSinabonusServiceImpl implements IAccountSinabonusService {

	@Autowired
	private AccountSinabonusMapper accountSinabonusMapper;
	
	@Override
	public int insertSelective(AccountSinabonus record) {
		return accountSinabonusMapper.insertSelective(record);
	}

	@Override
	public AccountSinabonus selectByPrimaryKey(Integer userId) {
		return accountSinabonusMapper.selectByPrimaryKey(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(AccountSinabonus record) {
		return accountSinabonusMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int createUserEmptyData(Integer userId) {
		int result = 0;
		if(userId!=null&&userId>0){
			AccountSinabonus asb = selectByPrimaryKey(userId);
			if(asb==null){
				asb = new AccountSinabonus();
				asb.setUserId(userId);
				asb.setSinaBonusTotal(new BigDecimal(0));
				asb.setSinaBonusPrevious(new BigDecimal(0));
				asb.setUpdatetime(new Date());
				result = insertSelective(asb);
			}
		}		
		return result;
	}

}