package cn.vfunding.vfunding.biz.message.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.dao.HikesMessageMapper;
import cn.vfunding.vfunding.biz.message.model.HikesMessage;
import cn.vfunding.vfunding.biz.message.service.IHikesMessageService;
@Service("hikesMessageService")
public class HikesMessageServiceImpl implements IHikesMessageService {

	@Autowired
	private HikesMessageMapper hikesMessageMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return hikesMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(HikesMessage record) {
		return hikesMessageMapper.insertSelective(record);
	}

	@Override
	public HikesMessage selectByPrimaryKey(Integer id) {
		return hikesMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(HikesMessage record) {
		return hikesMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<HikesMessage> selectHikesMessageListPage(PageSearch pageSearch) {
		return hikesMessageMapper.selectHikesMessageListPage(pageSearch);
	}

	@Override
	public Integer selectUnLookCountByUserId(Integer userId) {
		List<HikesMessage> list = this.hikesMessageMapper.selectUnLookByUserId(userId);
		return list.size();
	}

}
