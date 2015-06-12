package cn.vfunding.vfunding.biz.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.dao.CashMessageMapper;
import cn.vfunding.vfunding.biz.message.model.CashMessage;
import cn.vfunding.vfunding.biz.message.service.ICashMessageService;
@Service("cashMessageService")
public class CashMessageServiceImpl implements ICashMessageService {

	@Autowired
	private CashMessageMapper cashMessageMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return cashMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(CashMessage record) {
		return cashMessageMapper.insertSelective(record);
	}

	@Override
	public CashMessage selectByPrimaryKey(Integer id) {
		return cashMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(CashMessage record) {
		return cashMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<CashMessage> selectCashMessageListPage(PageSearch pageSearch) {
		return cashMessageMapper.selectCashMessageListPage(pageSearch);
	}

	@Override
	public Integer selectUnLookCountByUserId(Integer userId) {
		List<CashMessage> list = this.cashMessageMapper.selectUnLookByUserId(userId);
		return list.size();
	}

}
