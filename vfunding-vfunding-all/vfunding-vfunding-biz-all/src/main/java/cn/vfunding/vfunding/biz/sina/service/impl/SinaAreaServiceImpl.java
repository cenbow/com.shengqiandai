package cn.vfunding.vfunding.biz.sina.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sina.dao.SinaAreaMapper;
import cn.vfunding.vfunding.biz.sina.model.SinaArea;
import cn.vfunding.vfunding.biz.sina.service.ISinaAreaService;

@Service("sinaAreaService")
public class SinaAreaServiceImpl implements ISinaAreaService {

	@Autowired
	private SinaAreaMapper sinaAreaMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(SinaArea record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(SinaArea record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SinaArea selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(SinaArea record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(SinaArea record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<SinaArea> selectAreaByParam(SinaArea record) {
		// TODO Auto-generated method stub
		List<SinaArea> sinaAreaList = null;
		if (record.getProvince() == null || record.getProvince().equals("")) {
			sinaAreaList = this.sinaAreaMapper.selectProvince();
		} else {
			sinaAreaList = this.sinaAreaMapper.selectCity(record.getProvince());
		}
		return sinaAreaList;
	}

}
