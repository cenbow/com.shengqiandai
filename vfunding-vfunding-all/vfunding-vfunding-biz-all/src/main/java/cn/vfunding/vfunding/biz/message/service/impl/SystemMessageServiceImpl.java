package cn.vfunding.vfunding.biz.message.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.message.dao.SystemMessageMapper;
import cn.vfunding.vfunding.biz.message.model.SystemMessage;
import cn.vfunding.vfunding.biz.message.service.ISystemMessageService;

@Service("systemMessageService")
public class SystemMessageServiceImpl implements ISystemMessageService {
	@Autowired
	private SystemMessageMapper systemMessageMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.systemMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(SystemMessage record) {
		return this.systemMessageMapper.insert(record);
	}

	@Override
	public int insertSelective(SystemMessage record) {
		return this.systemMessageMapper.insertSelective(record);
	}

	@Override
	public SystemMessage selectByPrimaryKey(Integer id) {
		return this.systemMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SystemMessage record) {
		return this.systemMessageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SystemMessage record) {
		return this.systemMessageMapper.updateByPrimaryKey(record);
	}

	public List<SystemMessage> selectListPage(PageSearch pageSearch) {
		return this.systemMessageMapper.selectListPage(pageSearch);
	}

	public Integer selectIsLookCount(SystemMessage systemMessage) {
		return this.systemMessageMapper.selectIsLookCount(systemMessage);
	}

	@Override
	public boolean deleteSystemMessages(String ids) {
		String[] idArray = ids.split(",");
		for (String s : idArray) {
			systemMessageMapper.deleteByPrimaryKey(Integer.parseInt(s));
		}
		return true;
	}
	@Override
	public boolean deleteSystemMessages(String[] ids) {
		String[] idArray = ids;
		for (String s : idArray) {
			systemMessageMapper.deleteByPrimaryKey(Integer.parseInt(s));
		}
		return true;
	}

	@Override
	public Map<String, Integer> updateLookAndSelectCount(
			SystemMessage systemMessage) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		this.updateByPrimaryKeySelective(systemMessage);
		//已读数量
		systemMessage.setIsLook(1);
		map.put("isLookCount", this.selectIsLookCount(systemMessage));
				//未读数量
		systemMessage.setIsLook(0);
		map.put("unIsLookCount", this.selectIsLookCount(systemMessage));
		return map;
	}
}
