package cn.vfunding.vfunding.biz.system.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.vfunding.biz.system.dao.ScrollpicTypeMapper;
import cn.vfunding.vfunding.biz.system.model.ScrollpicType;
import cn.vfunding.vfunding.biz.system.service.IScrollpicTypeService;
@Service("scrollpicTypeService")
public class ScrollpicTypeServiceImpl implements IScrollpicTypeService {

	@Autowired
	private ScrollpicTypeMapper scrollpicTypeMapper;
	/**
	 * 缓存数据
	 */
	private List<ScrollpicType> allData;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
        allData=null;
		return this.scrollpicTypeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ScrollpicType record) {
		allData=null;
		return this.scrollpicTypeMapper.insert(record);
	}

	@Override
	public int insertSelective(ScrollpicType record) {
		allData=null;
		return this.scrollpicTypeMapper.insertSelective(record);
	}

	@Override
	public ScrollpicType selectByPrimaryKey(Integer id) {
		 return this.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ScrollpicType record) {
		allData=null;
		return this.scrollpicTypeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ScrollpicType record) {
		allData=null;
		return this.scrollpicTypeMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<ScrollpicType> selectAll() {
		if(NullUtil.isNull(allData) || EmptyUtil.isEmpty(allData)){
			this.init();
		}
		return allData;
	}
	/**
	 * 初始化缓存数据
	 * 
	 * @author liuyijun
	 */
	private void init(){
		allData=this.scrollpicTypeMapper.selectAll();
	}

}
