package cn.p2p.p2p.biz.account.model;

import java.math.BigDecimal;
import java.util.Date;

public class InterestDays {
	private Integer id;

	private Integer userId;

	private Integer type;

	private BigDecimal interest;

	private String typeStr;

	private Date interestDate;

	private Date addtime;

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Date getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(Date interestDate) {
		this.interestDate = interestDate;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getTypeStr() {
		if (type != null) {
			if (type == 0) {
				return "定期宝生息";
			} else if (type == 2) {
				return "存钱罐生息";
			} else if (type == 1) {
				return "活期宝生息";
			}
		}
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

}