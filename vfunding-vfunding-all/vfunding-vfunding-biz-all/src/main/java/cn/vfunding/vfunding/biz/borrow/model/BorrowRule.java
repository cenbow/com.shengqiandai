package cn.vfunding.vfunding.biz.borrow.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BorrowRule extends  BaseModel{
    private Integer borrowId;

    private BigDecimal lowestAccount;

    private BigDecimal mostAccount;

    private Integer mostTenderCount;

    private Integer appointUser;
    
    private BigDecimal mostHikes;

    private Date addtime;
    
    private Date tenderTime;

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public BigDecimal getLowestAccount() {
        return lowestAccount;
    }

    public void setLowestAccount(BigDecimal lowestAccount) {
        this.lowestAccount = lowestAccount;
    }

    public BigDecimal getMostAccount() {
        return mostAccount;
    }

    public void setMostAccount(BigDecimal mostAccount) {
        this.mostAccount = mostAccount;
    }

    public Integer getMostTenderCount() {
        return mostTenderCount;
    }

    public void setMostTenderCount(Integer mostTenderCount) {
        this.mostTenderCount = mostTenderCount;
    }

    public Integer getAppointUser() {
        return appointUser;
    }

    public void setAppointUser(Integer appointUser) {
        this.appointUser = appointUser;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public BigDecimal getMostHikes() {
		return mostHikes;
	}

	public void setMostHikes(BigDecimal mostHikes) {
		this.mostHikes = mostHikes;
	}

	public Date getTenderTime() {
		return tenderTime;
	}

	public void setTenderTime(Date tenderTime) {
		this.tenderTime = tenderTime;
	}
	
}