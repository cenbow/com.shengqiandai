package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.NullUtil;
import cn.vfunding.vfunding.biz.system.dao.LinkageMapper;
import cn.vfunding.vfunding.biz.system.model.Linkage;
import cn.vfunding.vfunding.biz.system.service.ILinkageService;
@Service("LinkageService")
public class LinkageServiceImpl implements ILinkageService {

	@Autowired
	private LinkageMapper linkageMapper;
	
	private static List<Linkage> allData;
	@Override
	public int deleteByPrimaryKey(Short id) {
		allData=null;
		return this.linkageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Linkage record) {
		allData=null;
		return this.linkageMapper.insert(record);
	}

	@Override
	public int insertSelective(Linkage record) {
        allData=null;
		return this.linkageMapper.insertSelective(record);
	}

	@Override
	public Linkage selectByPrimaryKey(Short id) {
		return this.linkageMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Linkage record) {
		allData=null;
		return this.linkageMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Linkage record) {
		allData=null;
		return this.linkageMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Linkage> selectByTypeId(Integer typeId) {
		List<Linkage> result=new ArrayList<Linkage>();
        if(EmptyUtil.isEmpty(allData) || NullUtil.isNull(allData)){
        	init();
        }
        for (Linkage linkage : allData) {
			if(linkage.getTypeId().intValue()==typeId){
				result.add(linkage);
			}
		}
		return result;
	}
	
	private void init(){
		allData=this.linkageMapper.selectAll();
	}

}
