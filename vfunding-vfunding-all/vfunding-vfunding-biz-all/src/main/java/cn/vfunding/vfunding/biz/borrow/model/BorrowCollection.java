package cn.vfunding.vfunding.biz.borrow.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BorrowCollection extends  BaseModel{
    private Integer id;

    private Integer siteId;

    private Integer status;

    private Integer order;

    private Integer tenderId;

    private String repayTime;

    private String repayYestime;

    private String repayAccount;

    private String repayYesaccount;

    private String interest;

    private String capital;

    private Integer lateDays;

    private String lateInterest;

    private String addtime;

    private String addip;

    private BigDecimal interestFee;

    private String serviceFees;

    private String guaranteeFees;

    private String returnFees;

    private String inviteFees;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public String getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(String repayTime) {
        this.repayTime = repayTime == null ? null : repayTime.trim();
    }

    public String getRepayYestime() {
        return repayYestime;
    }

    public void setRepayYestime(String repayYestime) {
        this.repayYestime = repayYestime == null ? null : repayYestime.trim();
    }

    public String getRepayAccount() {
        return repayAccount;
    }

    public void setRepayAccount(String repayAccount) {
        this.repayAccount = repayAccount == null ? null : repayAccount.trim();
    }

    public String getRepayYesaccount() {
        return repayYesaccount;
    }

    public void setRepayYesaccount(String repayYesaccount) {
        this.repayYesaccount = repayYesaccount == null ? null : repayYesaccount.trim();
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest == null ? null : interest.trim();
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital == null ? null : capital.trim();
    }

    public Integer getLateDays() {
        return lateDays;
    }

    public void setLateDays(Integer lateDays) {
        this.lateDays = lateDays;
    }

    public String getLateInterest() {
        return lateInterest;
    }

    public void setLateInterest(String lateInterest) {
        this.lateInterest = lateInterest == null ? null : lateInterest.trim();
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip == null ? null : addip.trim();
    }

    public BigDecimal getInterestFee() {
        return interestFee;
    }

    public void setInterestFee(BigDecimal interestFee) {
        this.interestFee = interestFee;
    }

    public String getServiceFees() {
        return serviceFees;
    }

    public void setServiceFees(String serviceFees) {
        this.serviceFees = serviceFees == null ? null : serviceFees.trim();
    }

    public String getGuaranteeFees() {
        return guaranteeFees;
    }

    public void setGuaranteeFees(String guaranteeFees) {
        this.guaranteeFees = guaranteeFees == null ? null : guaranteeFees.trim();
    }

    public String getReturnFees() {
        return returnFees;
    }

    public void setReturnFees(String returnFees) {
        this.returnFees = returnFees == null ? null : returnFees.trim();
    }

    public String getInviteFees() {
        return inviteFees;
    }

    public void setInviteFees(String inviteFees) {
        this.inviteFees = inviteFees == null ? null : inviteFees.trim();
    }
}