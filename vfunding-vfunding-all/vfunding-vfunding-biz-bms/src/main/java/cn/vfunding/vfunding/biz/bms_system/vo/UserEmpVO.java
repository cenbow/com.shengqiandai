package cn.vfunding.vfunding.biz.bms_system.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

/**
 * 用户账户信息   
 * @author lx
 *
 */
@SuppressWarnings("serial")
public class UserEmpVO extends BaseVO{
	//用户id
    private String userId;
    //用户名
    private String userName;
    //真实姓名
    private String realName ;
    //用户手机
    private String phone ;
    //用户邮箱
    private String email;
    //添加时间
    private String addTime;
    //用户余额
    private String userMoney;
    //用户总额
    private String total;
    //备注
    private String remark;
    //跟踪
    private String track;
    //更换时间
    private String updateDate;
    //更换备注
    private String updateRemark;
    //所属客服
    private String empName;
    //投资总额
    private BigDecimal tenderSum;
    //客服来源
    private String source;
    //介绍人姓名
    private String sourceName;
    
    
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateRemark() {
		return updateRemark;
	}
	public void setUpdateRemark(String updateRemark) {
		this.updateRemark = updateRemark;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public BigDecimal getTenderSum() {
		return tenderSum;
	}
	public void setTenderSum(BigDecimal tenderSum) {
		this.tenderSum = tenderSum;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getUserMoney() {
		return userMoney;
	}
	public void setUserMoney(String userMoney) {
		this.userMoney = userMoney;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTrack() {
		return track;
	}
	public void setTrack(String track) {
		this.track = track;
	}
    
}
