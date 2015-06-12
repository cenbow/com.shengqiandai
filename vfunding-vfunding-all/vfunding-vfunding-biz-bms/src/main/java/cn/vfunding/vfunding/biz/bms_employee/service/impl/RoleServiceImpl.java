package cn.vfunding.vfunding.biz.bms_employee.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.easyui.page.utils.Tree;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.bms_employee.service.IRoleService;
import cn.vfunding.vfunding.biz.bms_system.dao.RoleMapper;
import cn.vfunding.vfunding.biz.bms_system.dao.RoleResourceMapper;
import cn.vfunding.vfunding.biz.bms_system.dao.SysResourceMapper;
import cn.vfunding.vfunding.biz.bms_system.model.Role;
import cn.vfunding.vfunding.biz.bms_system.model.RoleResourceKey;
import cn.vfunding.vfunding.biz.bms_system.model.SysResource;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private RoleResourceMapper roleResourceMapper;
	@Autowired
	private SysResourceMapper sysResourceMapper;

	@Override
	public int deleteByPrimaryKey(Integer roleId) {
		return this.roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int insert(Role record) {
		try {
			this.roleMapper.insert(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return record.getRoleId();
	}

	@Override
	public int insertSelective(Role record) {
		this.roleMapper.insertSelective(record);
		return record.getRoleId();
	}

	@Override
	public Role selectByPrimaryKey(Integer roleId) {
		return this.roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public int updateByPrimaryKeySelective(Role record) {
		return this.roleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Role record) {
		return this.roleMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Role> selectRoleListPage(PageSearch page) {
		return this.roleMapper.selectRoleListPage(page);
	}

	@Override
	public boolean exist(Integer roleId) {
		int result = this.roleMapper.exist(roleId);
		return result > 0 ? true : false;
	}

	@Override
	public Role selectRoleAndResourcesByKey(Integer roleId) {
		Role result = this.roleMapper.selectRoleAndResourcesByKey(roleId);
		List<SysResource> resources = result.getResources();
		if (CollectionUtils.isNotEmpty(resources)) {
			boolean b = false;
			String ids = "";
			String names = "";
			for (SysResource sysResource : resources) {
				if (b) {
					ids += ",";
					names += ",";
				} else {
					b = true;
				}
				ids += sysResource.getId();
				names += sysResource.getName();
			}

			result.setResourceIds(ids);
			result.setResourceNames(names);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.vfunding.yache.biz.employee.service.IRoleService#grant(cn.vfunding
	 * .yache.biz.employee.model.Role) 给角色分配资源
	 */
	@Override
	public void grant(Role role) {
		if(EmptyUtil.isNotEmpty(role.getResourceIds())){
			this.roleResourceMapper.deleteByRoleId(role.getRoleId());// 删除权限
			String[] ids=role.getResourceIds().split(",");
			for (String id : ids) {
				RoleResourceKey rs=new RoleResourceKey();
				rs.setResourceId(id);
				rs.setRoleId(role.getRoleId());
				this.roleResourceMapper.insert(rs);
			}
		}
	}

	@Override
	public List<Tree> allRoleTree() {
		List<Tree> l = new ArrayList<Tree>();
		List<Role> roleList = this.roleMapper.allRoleTree();
		for (Role r : roleList) {
			Tree t = new Tree();
			t.setId(String.valueOf(r.getRoleId()));
			t.setText(r.getRoleName());
			l.add(t);
		}
		return l;
	}

	@Override
	public List<Role> selectRoleComboxgridListPage(PageSearch page) {
		return this.roleMapper.selectRoleComboxgridListPage(page);
	}

	@Override
	public List<Role> selectRoleComboxgridList() {
		return this.roleMapper.selectRoleComboxgridList();
	}

}
