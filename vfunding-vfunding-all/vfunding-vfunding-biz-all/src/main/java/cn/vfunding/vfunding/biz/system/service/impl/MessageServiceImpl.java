package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.dao.MessageMapper;
import cn.vfunding.vfunding.biz.system.model.Message;
import cn.vfunding.vfunding.biz.system.service.IMessageService;

@Service("messageService")
public class MessageServiceImpl implements IMessageService {

	@Autowired
	private MessageMapper messageMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.messageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Message record) {
		return this.messageMapper.insert(record);
	}

	@Override
	public int insertSelective(Message record) {
		return this.messageMapper.insertSelective(record);
	}

	@Override
	public Message selectByPrimaryKey(Integer id) {
		return this.messageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Message record) {
		return this.messageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Message record) {
		return this.messageMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Message record) {
		return this.messageMapper.updateByPrimaryKey(record);
	}

	@Override
	public Integer selectNoReadCount(Integer receiveUser) {
		return this.messageMapper.selectNoReadCount(receiveUser);
	}

	@Override
	public int updateReadPrimaryKey(Message record) {
		return this.messageMapper.updateReadPrimaryKey(record);
	}

	@Override
	public List<Message> selectMessageByUserListPage(PageSearch pageSearch) {
		return this.messageMapper.selectMessageByUserListPage(pageSearch);
	}

	@Override
	public Integer selectAllCount(Integer receiveUser) {
		return this.messageMapper.selectAllCount(receiveUser);
	}

	@Override
	public int deleteMessage(String ids) {
		if (EmptyUtil.isNotEmpty(ids)) {
			if (ids.contains(",")) {
				String[] dIds = ids.split(",");
				for (String id : dIds) {
					this.messageMapper.deleteByPrimaryKey(Integer.parseInt(id));
				}
			} else {
				this.messageMapper.deleteByPrimaryKey(Integer.parseInt(ids));
			}
		}
		return 0;
	}

	@Override
	public int deleteMessage(String[] ids) {
		if (EmptyUtil.isNotEmpty(ids)) {
			for (String id : ids) {
				this.messageMapper.deleteByPrimaryKey(Integer.parseInt(id));
			}
		}
		return 0;
	}

}
