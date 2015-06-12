package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.dao.PicMapper;
import cn.vfunding.vfunding.biz.system.model.Pic;
import cn.vfunding.vfunding.biz.system.service.PicService;

@Service
public class PicServiceImpl implements PicService {
	
	@Autowired
	PicMapper picMapper;

	@Override
	public int deleteByPrimaryKey(String pickey) {

		return picMapper.deleteByPrimaryKey(pickey);
	}

	@Override
	public int insert(Pic record) {

		return picMapper.insert(record);
	}

	@Override
	public int insertSelective(Pic record) {

		return picMapper.insertSelective(record);
	}

	@Override
	public Pic selectByPrimaryKey(String pickey) {

		return picMapper.selectByPrimaryKey(pickey);
	}

	@Override
	public int updateByPrimaryKeySelective(Pic record) {

		return picMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Pic record) {

		return picMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Pic> selectListPage(PageSearch pageSearch) {

		return picMapper.selectListPage(pageSearch);
	}

}
