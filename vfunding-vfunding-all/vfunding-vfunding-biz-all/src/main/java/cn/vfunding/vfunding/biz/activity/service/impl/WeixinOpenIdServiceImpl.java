package cn.vfunding.vfunding.biz.activity.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.activity.dao.WeixinOpenIdMapper;
import cn.vfunding.vfunding.biz.activity.model.WeixinOpenId;
import cn.vfunding.vfunding.biz.activity.service.IWeixinOpenIdService;

@Service
public class WeixinOpenIdServiceImpl implements IWeixinOpenIdService {

	@Autowired
	private WeixinOpenIdMapper openIdMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(WeixinOpenId record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(WeixinOpenId record) {
		// TODO Auto-generated method stub
		record.setAddtime(new Date());
		return this.openIdMapper.insertSelective(record);
	}

	@Override
	public WeixinOpenId selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.openIdMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WeixinOpenId record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(WeixinOpenId record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public WeixinOpenId selectByOpenid(String openid) {
		// TODO Auto-generated method stub
		return this.openIdMapper.selectByOpenid(openid);
	}

	@Override
	public WeixinOpenId selectByPhone(String phone) {
		// TODO Auto-generated method stub
		return this.openIdMapper.selectByPhone(phone);
	}

}
