package cn.vfunding.vfunding.biz.sina.model;

import java.math.BigDecimal;
import java.util.Date;

public class SinaSettlement {
    private Integer id;

    private Integer userId;

    private BigDecimal sinaBalance;

    private BigDecimal sinaAvailableBalance;

    private BigDecimal sinaBonusYesterday;

    private BigDecimal sinaBonusTotal;

    private Integer status;

    private Date addtime;

    private Integer syncBonusRule;

    private Integer syncBonusStatus;

    private Date syncBonusDate;

    private BigDecimal syncBonusBeforeVfundingUsemoney;

    private BigDecimal syncBonusAfterVfundingUsemoney;
    
    private BigDecimal syncBonusRealmoney;

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

    public BigDecimal getSinaBalance() {
        return sinaBalance;
    }

    public void setSinaBalance(BigDecimal sinaBalance) {
        this.sinaBalance = sinaBalance;
    }

    public BigDecimal getSinaAvailableBalance() {
        return sinaAvailableBalance;
    }

    public void setSinaAvailableBalance(BigDecimal sinaAvailableBalance) {
        this.sinaAvailableBalance = sinaAvailableBalance;
    }

    public BigDecimal getSinaBonusYesterday() {
        return sinaBonusYesterday;
    }

    public void setSinaBonusYesterday(BigDecimal sinaBonusYesterday) {
        this.sinaBonusYesterday = sinaBonusYesterday;
    }

    public BigDecimal getSinaBonusTotal() {
        return sinaBonusTotal;
    }

    public void setSinaBonusTotal(BigDecimal sinaBonusTotal) {
        this.sinaBonusTotal = sinaBonusTotal;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Integer getSyncBonusRule() {
        return syncBonusRule;
    }

    public void setSyncBonusRule(Integer syncBonusRule) {
        this.syncBonusRule = syncBonusRule;
    }

    public Integer getSyncBonusStatus() {
        return syncBonusStatus;
    }

    public void setSyncBonusStatus(Integer syncBonusStatus) {
        this.syncBonusStatus = syncBonusStatus;
    }

    public Date getSyncBonusDate() {
        return syncBonusDate;
    }

    public void setSyncBonusDate(Date syncBonusDate) {
        this.syncBonusDate = syncBonusDate;
    }

    public BigDecimal getSyncBonusBeforeVfundingUsemoney() {
        return syncBonusBeforeVfundingUsemoney;
    }

    public void setSyncBonusBeforeVfundingUsemoney(BigDecimal syncBonusBeforeVfundingUsemoney) {
        this.syncBonusBeforeVfundingUsemoney = syncBonusBeforeVfundingUsemoney;
    }

    public BigDecimal getSyncBonusAfterVfundingUsemoney() {
        return syncBonusAfterVfundingUsemoney;
    }

    public void setSyncBonusAfterVfundingUsemoney(BigDecimal syncBonusAfterVfundingUsemoney) {
        this.syncBonusAfterVfundingUsemoney = syncBonusAfterVfundingUsemoney;
    }

	public BigDecimal getSyncBonusRealmoney() {
		return syncBonusRealmoney;
	}

	public void setSyncBonusRealmoney(BigDecimal syncBonusRealmoney) {
		this.syncBonusRealmoney = syncBonusRealmoney;
	}
    
    
}