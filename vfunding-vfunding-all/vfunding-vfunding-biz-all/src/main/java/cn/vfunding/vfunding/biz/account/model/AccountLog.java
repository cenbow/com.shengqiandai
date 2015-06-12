package cn.vfunding.vfunding.biz.account.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

public class AccountLog extends BaseModel {

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private Integer id;

	private Integer userId;

	private String type;

	private BigDecimal total;

	private BigDecimal money;

	private BigDecimal useMoney;

	private BigDecimal noUseMoney;

	private BigDecimal collection;

	private Integer toUser;

	private String remark;

	private Integer borrowId;

	private Integer addtime;

	private String addip;

	private String addtimeStr;

	private String typeStr;

	public String getTypeStr() {
		if (this.type.equals("recharge")) {
			return "充值";
		}
		if (this.type.equals("tender")) {
			return "投资";
		}
		if (this.type.equals("cash")) {
			return "提现";
		}
		if (this.type.equals("collection")) {
			return "回款";
		}
		if (this.type.equals("recharge_fee")) {
			return "提现手续费";
		}
		if (this.type.equals("recharge_success")) {
			return "提现成功";
		}
		if (this.type.equals("recharge_false")) {
			return "提现失败";
		}
		if (this.type.equals("invest_false")) {
			return "投资失败回款";
		}
		if (this.type.equals("borrow_success")) {
			return "借款入帐";
		}
		if (this.type.equals("cash_frost")) {
			return "提现冻结";
		}
		if (this.type.equals("invest_flow")) {
			return "流标退款";
		}
		if (this.type.equals("margin")) {
			return "保证金";
		}
		if (this.type.equals("repayment") || this.type.equals("invest_repayment")) {
			return "还款";
		}
		if (this.type.equals("tender_mange")) {
			return "利息管理费用";
		}
		if (this.type.equals("invest")) {
			return "扣除冻结款";
		}
		if (this.type.equals("ticheng")) {
			return "提成";
		}
		if (this.type.equals("sinaBonus_addmoney")) {
			return "余额生息";
		}
		if (this.type.equals("gift_addmoney")) {
			return "使用红包";
		}
		return this.type;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public String getAddtimeStr() {
		return sdf.format(new Date(addtime));
	}

	public void setAddtimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getUseMoney() {
		return useMoney;
	}

	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}

	public BigDecimal getNoUseMoney() {
		return noUseMoney;
	}

	public void setNoUseMoney(BigDecimal noUseMoney) {
		this.noUseMoney = noUseMoney;
	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

	public Integer getToUser() {
		return toUser;
	}

	public void setToUser(Integer toUser) {
		this.toUser = toUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
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
}