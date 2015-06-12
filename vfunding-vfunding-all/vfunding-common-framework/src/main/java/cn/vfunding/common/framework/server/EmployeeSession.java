package cn.vfunding.common.framework.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EmployeeSession implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4146978212801239353L;
	/**
	 * 数据的交互枢纽
	 */
	private static ThreadLocal<EmployeeSession> threadLocalUserSession = new ThreadLocal<EmployeeSession>() ;	
	public static Integer getEmpSessionEmpId(){
		EmployeeSession userSession = threadLocalUserSession.get();
		if(userSession==null) return null;
		return userSession.getEmpId();
	}
	public static void setEmployeeSession(EmployeeSession userSession){
		threadLocalUserSession.set(userSession);
	}
	public static EmployeeSession getUserSession(){
		EmployeeSession userSession = threadLocalUserSession.get();
		return userSession;
	}
	
	private Integer empId;
	
	private String empName;
	
	private String password;
	
	private String loginName;
	/**
	 * 当前登录的部门ID
	 */
	private Integer currentOrgId;
	/**
	 * 当前登录职位信息
	 */
	private String currentPostId;
	
	
	private List<String> acitivitiGroupIds;
	
	private List<String> groupIds;
	
	
	private Map<String,Object> attributes = new HashMap<String,Object>();
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	
	
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
		this.empName = empName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<String> getAcitivitiGroupIds() {
		return acitivitiGroupIds;
	}
	public void setAcitivitiGroupIds(List<String> acitivitiGroupIds) {
		this.acitivitiGroupIds = acitivitiGroupIds;
	}
	public List<String> getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(List<String> groupIds) {
		this.groupIds = groupIds;
	}
	public String getCurrentPostId() {
		return currentPostId;
	}
	public void setCurrentPostId(String currentPostId) {
		this.currentPostId = currentPostId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Integer getCurrentOrgId() {
		return currentOrgId;
	}
	public void setCurrentOrgId(Integer currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	

}
