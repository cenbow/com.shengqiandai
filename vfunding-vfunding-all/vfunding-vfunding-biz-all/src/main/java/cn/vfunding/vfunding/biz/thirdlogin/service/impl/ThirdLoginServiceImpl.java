package cn.vfunding.vfunding.biz.thirdlogin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.thirdlogin.dao.ThirdLoginMapper;
import cn.vfunding.vfunding.biz.thirdlogin.model.ThirdLogin;
import cn.vfunding.vfunding.biz.thirdlogin.service.IThirdLoginService;

@Service("thridLoginService")
public class ThirdLoginServiceImpl implements IThirdLoginService {
	@Autowired
	private ThirdLoginMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ThirdLogin record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(ThirdLogin record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public ThirdLogin selectByPrimaryKey(Integer id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ThirdLogin record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ThirdLogin record) {
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public ThirdLogin selectByLoginAccountAndCategory(ThirdLogin search) {
		return this.mapper.selectByLoginAccountAndCategory(search);
	}

}
