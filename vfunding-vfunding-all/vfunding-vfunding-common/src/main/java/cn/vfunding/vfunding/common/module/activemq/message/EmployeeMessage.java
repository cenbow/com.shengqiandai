package cn.vfunding.vfunding.common.module.activemq.message;


@SuppressWarnings("serial")
public class EmployeeMessage extends BaseMessage {

	public EmployeeMessage(){
		super();
	}
	private Integer empId;

	private String empName;

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

}
