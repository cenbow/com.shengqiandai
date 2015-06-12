package cn.vfunding.vfunding.prd.bms.system.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.Resource;
import cn.vfunding.common.framework.easyui.page.utils.ResourceType;
import cn.vfunding.common.framework.easyui.page.utils.Tree;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSecurity;
import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.common.framework.utils.http.HttpSessionUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.vfunding.biz.bms_system.model.SysResource;
import cn.vfunding.vfunding.biz.bms_system.model.SysResourceGroup;
import cn.vfunding.vfunding.biz.bms_system.service.IResourceGroupService;
import cn.vfunding.vfunding.biz.bms_system.service.IResourceService;
import cn.vfunding.vfunding.biz.bms_system.service.IResourceTypeService;
import cn.vfunding.vfunding.biz.enums.YesAndNoEnum;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IResourceTypeService typeService;
	@Autowired
	private IResourceGroupService groupService;

	/**
	 * 转向资源列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/toListPage")
	public ModelAndView toListPage() {
		ModelAndView mv = new ModelAndView("webpage/system/resourcesList");
		boolean canAddOrEdit = UserAuthFilter
				.isPass("/resource/addOrEdit");
		boolean canDelete = UserAuthFilter
				.isPass("/resource/delete");
		mv.addObject("canAddOrEdit", canAddOrEdit);
		mv.addObject("canDelete", canDelete);
		return mv;
	}

	/**
	 * 转向资源添加或修改页面
	 * 
	 * @param resourceId
	 * @return
	 * @throws Exception
	 */
	@UnSecurity
	@RequestMapping("/toAddOrEditPage")
	public ModelAndView toAddOrEditPage(
			@RequestParam(value = "resourceId", required = false) String resourceId)
			throws Exception {
		ModelAndView mv = new ModelAndView("webpage/system/resourceAddOrEdit");
		if (StringUtils.hasText(resourceId)) {
			SysResource r = resourceService.selectByPrimaryKey(resourceId);
			if (r != null) {
				Resource rs = new Resource();
				BeanUtils.copyProperties(rs, r);
				rs.setGroupId(r.getResourceGroup().getId());
				rs.setTypeId(r.getResourceType().getId().toString());
				mv.addObject("resource", rs);
			}

		}

		List<ResourceType> typeList = typeService.getResourceTypeList();
		List<SysResourceGroup> groupList = groupService.selectAll();
		mv.addObject("typeList", typeList);
		mv.addObject("groupList", groupList);
		mv.addObject("yesnos", YesAndNoEnum.values());
		boolean canAdd = UserAuthFilter.isPass("ffdfe");
		boolean canEdit = UserAuthFilter.isPass("ffdfe");
		mv.addObject("canAddResource", canAdd);
		mv.addObject("canEdit", canEdit);
		return mv;
	}

	/**
	 * 获得资源树(资源类型为菜单类型) 通过用户ID判断，他能看到的资源
	 * 
	 * @param groupId
	 *            菜單組ID
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@UnSecurity
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(@RequestParam("groupId") Integer groupId,
			HttpSession session) throws Exception {
		EmployeeSession eSession = (EmployeeSession) HttpSessionUtil
				.getAuthInfo(session);
		return resourceService.tree(eSession, groupId);
	}

	/**
	 * 获得当前用户拥有的所有资源树
	 * 
	 * @return
	 * @throws Exception 
	 */
	@UnSecurity
	@RequestMapping("/employeeAllTree")
	@ResponseBody
	public List<Tree> employeeAllTree() throws Exception {
		return this.resourceService.employeeAllTree();
	}

	

	/**
	 * 获得所有资源树
	 * 
	 * @return
	 * @throws Exception 
	 */
	@UnSecurity
	@RequestMapping("/allTree")
	@ResponseBody
	public List<Tree> allTree() throws Exception {
		return resourceService.allTree();
	}

	@ParentSecurity("/resource/toListPage")
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Resource> treeGrid() throws Exception {
		return resourceService.treeGrid();
	}

	@NeedLogger(desc="添加或修改资源")
	@RequestMapping(value = "/addOrEdit", method = RequestMethod.POST)
	@ResponseBody
	public Json addOrEdit(Resource resource) {
		Json j = new Json();
		try {
			SysResource sr = new SysResource();
			if (StringUtils.hasText(resource.getId())) {
				createSysResource(sr, resource);
				this.resourceService.updateByPrimaryKey(sr);
				j.setAction("edit");
			} else {
				resource.setId(ObjectId.get());
				createSysResource(sr, resource);
				this.resourceService.insertSelective(sr);
				resource.setId(sr.getId());
				j.setAction("add");
			}
			j.setObj(resource);
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 删除资源，包括他的子资源
	 * 
	 * @param id
	 * @return
	 */
	@NeedLogger(desc="删除资源")
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(@RequestParam("id") String id) {
		Json j = new Json();
		int result = this.resourceService.deleteByPrimaryKey(id);
		if (result > 0) {
			j.setSuccess(true);
			j.setObj(result);
		}
		return j;
	}

	/**
	 * 模型转换
	 * 
	 * @param sr
	 * @param resource
	 * @throws Exception
	 */
	private void createSysResource(SysResource sr, Resource resource)
			throws Exception {
		BeanUtils.copyProperties(sr, resource);
		sr.setGroupId(resource.getGroupId());
		sr.setResourcetypeId(new Integer(resource.getTypeId()));

	}

}
