package cn.vfunding.vfunding.biz.cron.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.cron.model.AccountRecord;

public interface AccountRecordMapper {
	int deleteByPrimaryKey(Long id);

	int insert(AccountRecord record);

	int insertSelective(AccountRecord record);

	AccountRecord selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(AccountRecord record);

	int updateByPrimaryKey(AccountRecord record);

	List<AccountRecord> selectUserAccount();

	List<AccountRecord> selectAllRecord(Integer money);

	int updateByUserIdAndMoney(AccountRecord record);
}