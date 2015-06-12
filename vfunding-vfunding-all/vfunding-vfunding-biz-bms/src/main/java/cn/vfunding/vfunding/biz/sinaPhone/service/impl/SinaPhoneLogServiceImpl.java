package cn.vfunding.vfunding.biz.sinaPhone.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sinaPhone.dao.SinaPhoneLogMapper;
import cn.vfunding.vfunding.biz.sinaPhone.model.SinaPhoneLog;
import cn.vfunding.vfunding.biz.sinaPhone.service.ISinaPhoneLogService;

@Service("sinaPhoneLogService")
public class SinaPhoneLogServiceImpl implements ISinaPhoneLogService{
	@Autowired
	private SinaPhoneLogMapper sinaPhoneLogMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sinaPhoneLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(SinaPhoneLog record) {
		// TODO Auto-generated method stub
		return sinaPhoneLogMapper.insertSelective(record);
	}

	@Override
	public SinaPhoneLog selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return sinaPhoneLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SinaPhoneLog record) {
		// TODO Auto-generated method stub
		return sinaPhoneLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<SinaPhoneLog> selectUserIdByPhone() {
		// TODO Auto-generated method stub
		return sinaPhoneLogMapper.selectUserIdByPhone();
	}

}
