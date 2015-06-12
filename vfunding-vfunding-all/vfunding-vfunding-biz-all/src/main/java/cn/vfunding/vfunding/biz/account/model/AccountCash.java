package cn.vfunding.vfunding.biz.account.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;
@SuppressWarnings("serial")
public class AccountCash extends BaseModel{
    private Integer id;

    private Integer userId;

    private Byte status;

    private String account;

    private String bank;

    private String branch;

    private BigDecimal total;

    private BigDecimal credited;

    private BigDecimal fee;

    private Integer verifyUserid;

    private Integer verifyTime;

    private String verifyRemark;

    private Integer addtime;

    private String addip;

    private BigDecimal hongbao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    

    public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getCredited() {
        return credited;
    }

    public void setCredited(BigDecimal credited) {
        this.credited = credited;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getVerifyUserid() {
        return verifyUserid;
    }

    public void setVerifyUserid(Integer verifyUserid) {
        this.verifyUserid = verifyUserid;
    }

    public Integer getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Integer verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getVerifyRemark() {
        return verifyRemark;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark == null ? null : verifyRemark.trim();
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip == null ? null : addip.trim();
    }

    public BigDecimal getHongbao() {
        return hongbao;
    }

    public void setHongbao(BigDecimal hongbao) {
        this.hongbao = hongbao;
    }
}