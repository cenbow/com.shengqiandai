package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.vfunding.biz.system.dao.LinksTypeMapper;
import cn.vfunding.vfunding.biz.system.model.LinksType;
import cn.vfunding.vfunding.biz.system.service.ILinksTypeService;

@Service("linksTypeService")
public class LinksTypeServiceImpl implements ILinksTypeService {

	@Autowired
	private LinksTypeMapper linksTypeMapper; 
	
	
	private static List<LinksType> allData;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		allData=null;
		return this.linksTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(LinksType record) {
		allData=null;
		return this.linksTypeMapper.insert(record);
	}

	@Override
	public int insertSelective(LinksType record) {
		allData=null;
		return this.linksTypeMapper.insertSelective(record);
	}

	@Override
	public LinksType selectByPrimaryKey(Integer id) {
		return this.linksTypeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(LinksType record) {
		allData=null;
		return this.linksTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(LinksType record) {
		allData=null;
		return this.linksTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<LinksType> selectAll() {
		if(EmptyUtil.isEmpty(allData) || NullUtil.isNull(allData)){
			this.init();
		}
		return allData;
	}

	private void init(){
		allData=this.linksTypeMapper.selectAll();
	}
}
