package cn.vfunding.vfunding.biz.week.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class WeekTender extends BaseModel{
	  private Integer id;

	    private Integer weekId;

	    private Integer userId;

	    private Integer status;

	    private BigDecimal buyShare;

	    private BigDecimal realbuyShare;

	    private BigDecimal money;

	    private BigDecimal interest;

	    private BigDecimal platformFee;

	    private BigDecimal guaranteeFee;

	    private BigDecimal repaymentAccount;

	    private BigDecimal repaymentYesaccount;

	    private Date repaymentTime;

	    private Date addTime;

	    private String addIp;
	    
	    private Week week;
	    
    public Week getWeek() {
			return week;
		}

		public void setWeek(Week week) {
			this.week = week;
		}

	private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWeekId() {
        return weekId;
    }

    public void setWeekId(Integer weekId) {
        this.weekId = weekId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    
    public BigDecimal getBuyShare() {
		return buyShare;
	}

	public void setBuyShare(BigDecimal buyShare) {
		this.buyShare = buyShare;
	}

	public BigDecimal getRealbuyShare() {
		return realbuyShare;
	}

	public void setRealbuyShare(BigDecimal realbuyShare) {
		this.realbuyShare = realbuyShare;
	}

	public BigDecimal getPlatformFee() {
		return platformFee;
	}

	public void setPlatformFee(BigDecimal platformFee) {
		this.platformFee = platformFee;
	}

	public BigDecimal getGuaranteeFee() {
		return guaranteeFee;
	}

	public void setGuaranteeFee(BigDecimal guaranteeFee) {
		this.guaranteeFee = guaranteeFee;
	}

	public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getRepaymentAccount() {
        return repaymentAccount;
    }

    public void setRepaymentAccount(BigDecimal repaymentAccount) {
        this.repaymentAccount = repaymentAccount;
    }

    public BigDecimal getRepaymentYesaccount() {
        return repaymentYesaccount;
    }

    public void setRepaymentYesaccount(BigDecimal repaymentYesaccount) {
        this.repaymentYesaccount = repaymentYesaccount;
    }

    public Date getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(Date repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp == null ? null : addIp.trim();
    }

	public String getUsername() {
		if(username != null){
			String name = username;
			if (username.length() > 3) {
				name = (username.substring(0, 3) + "***");
			} else {
				name = (username.substring(0, 1) + "***");
			}
			return name;
		}
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}