package cn.vfunding.vfunding.biz.bms_system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.vfunding.common.framework.easyui.page.utils.Resource;
import cn.vfunding.common.framework.easyui.page.utils.Tree;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.bms_system.dao.SysResourceMapper;
import cn.vfunding.vfunding.biz.bms_system.model.SysResource;
import cn.vfunding.vfunding.biz.bms_system.service.IResourceService;
@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private SysResourceMapper resourceMapper;

	@SuppressWarnings("unchecked")
	@Override
	public List<Tree> tree(EmployeeSession eSession, Integer resourceGroupId) throws Exception {
		List<SysResource> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		if (eSession != null) {
			l = (List<SysResource>) eSession.getAttributes().get("resources");
			if (CollectionUtils.isNotEmpty(l)) {
				for (SysResource r : l) {
						Tree tree = new Tree();
						if (resourceGroupId != null) {
							if (r.getGroupId().equals(resourceGroupId)) {
								this.createTrees(tree, r, lt, true);
							}
						} else {
							this.createTrees(tree, r, lt, true);
						}
				}
			}
		}
		return lt;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tree> employeeAllTree() throws Exception {
		EmployeeSession eSession = EmployeeSession.getUserSession();
		List<SysResource> l = null;
		List<Tree> lt = new ArrayList<Tree>();
		if (eSession != null) {
			l = (List<SysResource>) eSession.getAttributes().get("resources");

			if (CollectionUtils.isNotEmpty(l)) {
				for (SysResource r : l) {
					Tree tree = new Tree();
					this.createTrees(tree, r, lt, false);
				}
			}
		}
		return lt;
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		int result = 0;
		if (EmptyUtil.isNotEmpty(id)) {
			List<SysResource> cList = this.resourceMapper.selectByParentId(id);
			if (EmptyUtil.isNotEmpty(cList)) {
				for (SysResource sysResource : cList) {
					int cResult = this.resourceMapper
							.deleteByPrimaryKey(sysResource.getId());
					if (cResult > 0)
						result++;
					List<SysResource> sysResourceList = this
							.selectByParentId(sysResource.getId());
					if (EmptyUtil.isNotEmpty(sysResourceList)) {
						for (SysResource sysResource2 : sysResourceList) {// 递归删除
							result += this.deleteByPrimaryKey(sysResource2
									.getId());
						}
					}

				}
			}
			result += this.resourceMapper.deleteByPrimaryKey(id);
		}
		return result;
	}

	@Override
	public int insert(SysResource record) {
		return this.resourceMapper.insert(record);
	}

	@Override
	public int insertSelective(SysResource record) {
		return this.resourceMapper.insertSelective(record);
	}

	@Override
	public SysResource selectByPrimaryKey(String id) {
		return this.resourceMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(SysResource record) {
		return this.resourceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysResource record) {
		int result = 0;
		try {
			result = this.resourceMapper.updateByPrimaryKey(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Tree> allTree() throws Exception {
		List<SysResource> l = this.resourceMapper.selectResourceList();
		List<Tree> lt = new ArrayList<Tree>();

		if (CollectionUtils.isNotEmpty(l)) {
			for (SysResource r : l) {
				Tree tree = new Tree();
				this.createTrees(tree, r, lt, false);
			}
		}
		return lt;
	}

	@Override
	public List<Resource> treeGrid() throws Exception {
		List<SysResource> l = this.resourceMapper
				.selectResourceListByTreeGrid();
		List<Resource> lr = new ArrayList<Resource>();
		for (SysResource sysResource : l) {
			Resource r = new Resource();
			BeanUtils.copyProperties(sysResource, r);
			if (sysResource.getResourceType() != null) {
				r.setTypeId(sysResource.getResourceType().getId().toString());
				r.setTypeName(sysResource.getResourceType().getName());
			}
			if (sysResource.getResourceGroup() != null) {
				r.setGroupId(sysResource.getResourceGroup().getId());
				r.setGroupName(sysResource.getResourceGroup().getName());
			}
			if (StringUtils.hasText(r.getPid())) {
				SysResource parent = this.resourceMapper.selectByPrimaryKey(r
						.getPid());
				if (parent != null) {
					r.setPname(parent.getName());
				}
			}
			lr.add(r);
		}
		return lr;
	}

	/**
	 * 根据资源创建树
	 * 
	 * @param tree
	 * @param r
	 * @param lt
	 * @param isSelect
	 *            是否过滤选择只是菜单类型
	 * @throws Exception 
	 */
	private void createTrees(Tree tree, SysResource r, List<Tree> lt,
			boolean isSelect) throws Exception {
		if (isSelect) {
			if (r.getResourcetypeId().intValue() == 1) { // 过滤选择菜单类型的资源
				createTreesMethod(tree, r, lt);
			}
		} else {
			createTreesMethod(tree, r, lt);
		}
	}

	private void createTreesMethod(Tree tree, SysResource r, List<Tree> lt) throws Exception {
		BeanUtils.copyProperties(r, tree);
		if (r.getPid() != null) {
			tree.setPid(r.getPid());
		}
		tree.setText(r.getName());
		tree.setIconCls(r.getIcon());
		Map<String, Object> attr = new HashMap<String, Object>();
		attr.put("url", r.getUrl());
		tree.setAttributes(attr);
		lt.add(tree);
	}

	
	@Override
	public List<SysResource> selectByParentId(String parentId) {
		return this.resourceMapper.selectByParentId(parentId);
	}

	

}
