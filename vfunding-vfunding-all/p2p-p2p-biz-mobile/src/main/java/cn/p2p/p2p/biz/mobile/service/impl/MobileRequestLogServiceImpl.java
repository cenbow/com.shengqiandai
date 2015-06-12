package cn.p2p.p2p.biz.mobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.dao.MobileRequestLogMapper;
import cn.p2p.p2p.biz.mobile.model.MobileRequestLog;
import cn.p2p.p2p.biz.mobile.service.IMobileRequestLogService;
@Service
public class MobileRequestLogServiceImpl implements IMobileRequestLogService {

	@Autowired
	private MobileRequestLogMapper mobileRequestLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.mobileRequestLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MobileRequestLog record) {
		return this.mobileRequestLogMapper.insertSelective(record);
	}

	@Override
	public MobileRequestLog selectByPrimaryKey(Integer id) {
		return this.mobileRequestLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MobileRequestLog record) {
		return this.mobileRequestLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int selectCountByOrderNo(String orderNo) {
		return this.mobileRequestLogMapper.selectCountByOrderNo(orderNo);
	}

}
