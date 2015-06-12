package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.vfunding.biz.system.dao.RemindTypeMapper;
import cn.vfunding.vfunding.biz.system.model.RemindType;
import cn.vfunding.vfunding.biz.system.service.IRemindTypeService;
@Service("remindTypeService")
public class RemindTypeServiceImpl implements IRemindTypeService {

	@Autowired
	private RemindTypeMapper remindTypeMapper;
	private static List<RemindType> allData;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		allData=null;
		return this.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RemindType record) {
		allData=null;
		return this.remindTypeMapper.insert(record);
	}

	@Override
	public int insertSelective(RemindType record) {
		allData=null;
		return this.remindTypeMapper.insertSelective(record);
	}

	@Override
	public RemindType selectByPrimaryKey(Integer id) {
		return this.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RemindType record) {
		allData=null;
		return this.remindTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RemindType record) {
		allData=null;
		return this.remindTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<RemindType> selectAll() {
		if(NullUtil.isNull(allData) || EmptyUtil.isEmpty(allData)){
			this.init();
		}
		return allData;
	}
	
	private void init(){
		allData=this.remindTypeMapper.selectAll();
	}

}
