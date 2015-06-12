package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.vfunding.biz.system.dao.FlagMapper;
import cn.vfunding.vfunding.biz.system.model.Flag;
import cn.vfunding.vfunding.biz.system.service.IFlagService;
@Service("flagService")
public class FlagServiceImpl implements IFlagService {

	@Autowired
	private FlagMapper flagMapper;
	
	private static List<Flag> allData;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		allData=null;
		return this.flagMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Flag record) {
		allData=null;
		return this.flagMapper.insert(record);
	}

	@Override
	public int insertSelective(Flag record) {
		allData=null;
		return this.insertSelective(record);
	}

	@Override
	public Flag selectByPrimaryKey(Integer id) {
		return this.flagMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Flag record) {
		allData=null;
		return this.flagMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Flag record) {
		allData=null;
		return this.updateByPrimaryKey(record);
	}

	@Override
	public List<Flag> selectAll() {
		if(EmptyUtil.isEmpty(allData) || NullUtil.isNull(allData)){
			init();
		}
		return allData;
	}
	
	
	private void init(){
		allData=this.flagMapper.selectAll();
	}

}
