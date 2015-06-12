package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.system.dao.JmsLogMapper;
import cn.vfunding.vfunding.biz.system.model.JmsLog;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;

@Service("jmsLogService")
public class JmsLogServiceImpl implements IJmsLogService {

	@Autowired
	private JmsLogMapper mapper;

	@Override
	public int insert(JmsLog record) {
		return this.mapper.insert(record);
	}

	@Override
	public JmsLog selectByPrimaryKey(String jmsId) {
		return this.mapper.selectByPrimaryKey(jmsId);
	}

	@Override
	public int updateByPrimaryKeySelective(JmsLog record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<JmsLog> selectByLose() {
		return this.mapper.selectByLose();
	}

	@Override
	public boolean canActionByJmsId(String id) {
		Integer status = this.mapper.selectStatusByJmsId(id);
		if (EmptyUtil.isEmpty(status) || status.intValue() == 0) {
			return true;
		}
		return false;
	}

}
