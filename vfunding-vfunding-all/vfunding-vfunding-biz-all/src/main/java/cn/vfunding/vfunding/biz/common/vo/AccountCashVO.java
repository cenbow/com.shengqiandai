package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class AccountCashVO extends BaseVO{
	
	private Integer userId;//用户id
	
	private String typeId;//用户类型
	
	private Integer cashId;//提现id

	private BigDecimal money;//提现金额
	
	private String mobileCode;//手机验证码
	
	private String payPassword;//支付密码
	
	private String bankNum;//银行账号
	
	private String bankNo;//银行代码
	
	private String branch; //银行支行
	
	private String[] isHongbao; //是否使用红包
	
	private Integer status;//提现状态 1:成功；2失败
	
	private String remark;//审核备注
	
	private String ip;
	
	private String useHongbao;

	//后台使用VO
	private String userName;
	private String realName;
	private BigDecimal account;//到账金额
	private String addtime;
	private BigDecimal fee;
	private String bankName;
	private String bankcashNo;
	private String bankcashTime;
	private String password;
	private Integer scId;
	
	

	public Integer getScId() {
		return scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBankcashTime() {
		return bankcashTime;
	}

	public void setBankcashTime(String bankcashTime) {
		this.bankcashTime = bankcashTime;
	}

	public String getBankcashNo() {
		return bankcashNo;
	}

	public void setBankcashNo(String bankcashNo) {
		this.bankcashNo = bankcashNo;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(String mobileCode) {
		this.mobileCode = mobileCode;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String[] getIsHongbao() {
		return isHongbao;
	}

	public void setIsHongbao(String[] isHongbao) {
		this.isHongbao = isHongbao;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getCashId() {
		return cashId;
	}

	public void setCashId(Integer cashId) {
		this.cashId = cashId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUseHongbao() {
		return useHongbao;
	}

	public void setUseHongbao(String useHongbao) {
		this.useHongbao = useHongbao;
	}
	
	
}
