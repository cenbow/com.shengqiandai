package cn.vfunding.vfunding.biz.bms_employee.model;

import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.bms_system.model.Role;
import cn.vfunding.vfunding.biz.bms_system.model.SysResource;
import cn.vfunding.vfunding.biz.bms_system.model.SysResourceGroup;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class Employee extends BaseModel{
	public Employee() {
		super();
	}

	public Employee(String loginName, String password) {
		super();
		this.empLoginName = loginName;
		this.empPassword = DigestUtils.md5Hex(password);
	}

	private Integer empId;

	private String empName;

	private String empEnName;

	private String empLoginName;

	private String empPassword;

	private Integer empSex;

	private Integer empState;

	private String empTel;

	private String empEmail;

	private Date empBirthday;

	private String empFamilytel;

	private String empFamilyaddr;

	private Integer empDistrictid;

	private Integer empAnewemp;
	private Date empTime;
	private Date empOuttime;
	private Date empDate;

	private List<SysResource> resources;

	private List<SysResourceGroup> resourceGroups;

	private List<Role> roles;
	
	private String addOrEditRoleIds;
	
	private String roleIds;

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName == null ? null : empName.trim();
	}

	public String getEmpEnName() {
		return empEnName;
	}

	public void setEmpEnName(String empEnName) {
		this.empEnName = empEnName == null ? null : empEnName.trim();
	}

	public String getEmpLoginName() {
		return empLoginName;
	}

	public void setEmpLoginName(String empLoginName) {
		this.empLoginName = empLoginName == null ? null : empLoginName.trim();
	}

	public String getEmpPassword() {
		return empPassword;
	}

	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword == null ? null : empPassword.trim();
	}

	public Integer getEmpSex() {
		return empSex;
	}

	public void setEmpSex(Integer empSex) {
		this.empSex = empSex;
	}

	public Integer getEmpState() {
		return empState;
	}

	public void setEmpState(Integer empState) {
		this.empState = empState;
	}

	public String getEmpTel() {
		return empTel;
	}

	public void setEmpTel(String empTel) {
		this.empTel = empTel == null ? null : empTel.trim();
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail == null ? null : empEmail.trim();
	}

	public Date getEmpBirthday() {
		return empBirthday;
	}

	public void setEmpBirthday(Date empBirthday) {
		this.empBirthday = empBirthday;
	}

	public String getEmpFamilytel() {
		return empFamilytel;
	}

	public void setEmpFamilytel(String empFamilytel) {
		this.empFamilytel = empFamilytel == null ? null : empFamilytel.trim();
	}

	public String getEmpFamilyaddr() {
		return empFamilyaddr;
	}

	public void setEmpFamilyaddr(String empFamilyaddr) {
		this.empFamilyaddr = empFamilyaddr == null ? null : empFamilyaddr
				.trim();
	}

	public Integer getEmpDistrictid() {
		return empDistrictid;
	}

	public void setEmpDistrictid(Integer empDistrictid) {
		this.empDistrictid = empDistrictid;
	}

	public Integer getEmpAnewemp() {
		return empAnewemp;
	}

	public void setEmpAnewemp(Integer empAnewemp) {
		this.empAnewemp = empAnewemp;
	}

	public Date getEmpTime() {
		return empTime;
	}

	public void setEmpTime(Date empTime) {
		this.empTime = empTime;
	}

	public Date getEmpOuttime() {
		return empOuttime;
	}

	public void setEmpOuttime(Date empOuttime) {
		this.empOuttime = empOuttime;
	}

	public Date getEmpDate() {
		return empDate;
	}

	public void setEmpDate(Date empDate) {
		this.empDate = empDate;
	}

	public List<SysResource> getResources() {
		return resources;
	}

	public void setResources(List<SysResource> resources) {
		this.resources = resources;
	}

	public List<SysResourceGroup> getResourceGroups() {
		return resourceGroups;
	}

	public void setResourceGroups(List<SysResourceGroup> resourceGroups) {
		this.resourceGroups = resourceGroups;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getRoleIds() {
		String result = "";
		if(EmptyUtil.isNotEmpty(this.roleIds)){
			result=roleIds;
		}else if (EmptyUtil.isNotEmpty(this.roles)) {
			for (Role role : roles) {
				result += role.getRoleId() + ",";
			}
			result = result.substring(0, result.length() - 1);
		}

		return result;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getAddOrEditRoleIds() {
		return addOrEditRoleIds;
	}

	public void setAddOrEditRoleIds(String addOrEditRoleIds) {
		this.addOrEditRoleIds = addOrEditRoleIds;
	}

	public String getRoleNames() {
		String result = "";
		if (EmptyUtil.isNotEmpty(this.roles)) {
			for (Role role : roles) {
				result += role.getRoleName() + ",";
			}
			result = result.substring(0, result.length() - 1);
		}

		return result;
	}

	public String getEmpTimeStr() {
		if (EmptyUtil.isNotEmpty(this.empTime)) {
			return DateUtil.parseDate(empTime);
		}
		return null;
	}
}