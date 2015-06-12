package cn.vfunding.vfunding.biz.bms_system.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.easyui.page.utils.ResourceType;
import cn.vfunding.common.framework.utils.beans.BeanUtils;
import cn.vfunding.vfunding.biz.bms_system.dao.SysResourceTypeMapper;
import cn.vfunding.vfunding.biz.bms_system.model.SysResourceType;
import cn.vfunding.vfunding.biz.bms_system.service.IResourceTypeService;

@Service("resourceTypeService")
public class ResourceTypeServiceImpl implements IResourceTypeService {

	@Autowired
	private SysResourceTypeMapper mapper;

	@Override
	public List<ResourceType> getResourceTypeList() {
		List<SysResourceType> sList = this.mapper.selectAll();
		List<ResourceType> result = null;

		if (CollectionUtils.isNotEmpty(sList)) {
			result = BeanUtils.copyBeanList(sList, ResourceType.class);
		}

		return result;
	}

}
