package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.vfunding.biz.system.dao.LinkageTypeMapper;
import cn.vfunding.vfunding.biz.system.model.LinkageType;
import cn.vfunding.vfunding.biz.system.service.ILinkageTypeService;
@Service("linkageTypeService")
public class LinkageTypeServiceImpl implements ILinkageTypeService {

	@Autowired
	private LinkageTypeMapper linkageTypeMapper;
	
	private static List<LinkageType> allData;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		allData=null;
		return this.linkageTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LinkageType record) {
		allData=null;
		return this.linkageTypeMapper.insert(record);
	}

	@Override
	public int insertSelective(LinkageType record) {
		allData=null;
		return this.linkageTypeMapper.insertSelective(record);
	}

	@Override
	public LinkageType selectByPrimaryKey(Integer id) {
		return this.linkageTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LinkageType record) {
		allData=null;
		return this.linkageTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LinkageType record) {
		allData=null;
		return this.linkageTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LinkageType> selectAll() {
		if(EmptyUtil.isEmpty(allData) || NullUtil.isNull(allData)){
			this.init();
		}
		return allData;
	}

	private void init(){
		allData=this.linkageTypeMapper.selectAll();
	}
}
