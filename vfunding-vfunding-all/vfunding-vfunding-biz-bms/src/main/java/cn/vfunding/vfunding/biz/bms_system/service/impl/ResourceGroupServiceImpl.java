package cn.vfunding.vfunding.biz.bms_system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.bms_system.dao.SysResourceGroupMapper;
import cn.vfunding.vfunding.biz.bms_system.model.SysResourceGroup;
import cn.vfunding.vfunding.biz.bms_system.service.IResourceGroupService;

@Service("resourceGroupService")
public class ResourceGroupServiceImpl implements IResourceGroupService {

	@Autowired
	private SysResourceGroupMapper groupMapper;
	@Override
	public List<SysResourceGroup> selectAll() {
		return this.groupMapper.selectAll();
	}

}
