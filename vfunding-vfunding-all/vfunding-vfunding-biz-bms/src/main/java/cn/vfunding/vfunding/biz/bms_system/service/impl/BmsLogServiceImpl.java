package cn.vfunding.vfunding.biz.bms_system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_system.dao.BmsLogMapper;
import cn.vfunding.vfunding.biz.bms_system.model.BmsLog;
import cn.vfunding.vfunding.biz.bms_system.service.IBmsLogService;
@Service("bmsLogService")
public class BmsLogServiceImpl implements IBmsLogService {

	@Autowired
	private BmsLogMapper mapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
	   return this.mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(BmsLog record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(BmsLog record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public BmsLog selectByPrimaryKey(Integer id) {
		return this.mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(BmsLog record) {
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(BmsLog record) {
		return this.mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<BmsLog> selectSystemBmsLogListPage(PageSearch search) {
		return this.mapper.selectSystemBmsLogListPage(search);
	}

	/**
	 * 批量删除 日志
	 * @author liuhuan
	 */
	@Override
	public int deleteByIds(int[] ids) {
		for(int i : ids){
			this.deleteByPrimaryKey(i);
		}
		return 1;
	}

	
}
