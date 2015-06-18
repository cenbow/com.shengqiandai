package cn.vfunding.vfunding.biz.borrow.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class Borrow extends BaseModel {

	private Integer id;

	private Short siteId;

	private Integer userId;

	private String name;

	private Byte status;

	private Integer order;

	private Integer hits;

	private String litpic;

	private String flag;

	private Byte isVouch;

	private String type;

	private Integer viewType;

	private String vouchAward;

	private String vouchUser;

	private String vouchAccount;

	private Integer vouchTimes;

	private String source;

	private String publish;

	private String customer;

	private String numberId;

	private Integer verifyUser;

	private Integer verifyTime;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private String verifyTimeStr;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private String verifyTimeStr1;

	private String verifyRemark;

	private Integer repaymentUser;

	private BigDecimal forstAccount;

	private BigDecimal repaymentAccount;

	private BigDecimal monthlyRepayment;

	private BigDecimal repaymentYesaccount;

	private BigDecimal repaymentYesinterest;

	private Integer repaymentTime;

	private String repaymentRemark;

	private Integer successTime;

	private Integer endTime;

	private BigDecimal paymentAccount;

	private String eachTime;

	private String use;

	private Short timeLimit;

	private Byte style;

	private BigDecimal account;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private String accountStr;

	private BigDecimal accountYes;

	private Short tenderTimes;

	private Date tenderTime;

	private BigDecimal apr;

	private BigDecimal lowestAccount;

	private BigDecimal mostAccount;

	private Integer validTime;

	private Byte award;

	private BigDecimal partAccount;

	private String funds;

	private String isFalse;

	private Byte openAccount;

	private Byte openBorrow;

	private Byte openTender;

	private Byte openCredit;

	private Integer addtime;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private String addtimeStr;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private String addtimeStr1;

	private String addip;

	private Byte isMb;

	private Byte isFast;

	private Byte isJin;

	private String pwd;

	private Byte isday;

	private Byte timeLimitDay;

	private Byte danbao;

	private Byte isKuai;

	private Byte isLz;

	private String biaoType;

	private BigDecimal borrowFee;

	private Byte isXin;

	private Integer mortgageTypeid;

	private Integer mortgageId;

	private String content;

	private BigDecimal expectApr;

	private String tenderEndtime;

	private Date contractStart;

	private Date contractEnd;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private Boolean borrowIsRepay;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private Integer completeInteger;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private String completePercent;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private String litpicPath;

	/**
	 * 发送cjd接口时候 fastjson转换需要这个成员
	 */
	private String sortName;
	
	
	/**
	 * 
	 * 新增产品相关属性
	 */
	
	
	private Integer productType;//产品类型
	
	private Integer productGroup;//产品系列
	
	private Integer jyStatus;//交易状态
	
	private Integer qxDate;//起息日
	
	private Integer hkType;//还款类型
	
	private BigDecimal ljMoney;//累加金额
	
	private Integer yyTags;//运营标签
	  
	private Integer bzTags;//标准便签
	  
	private String  buyButtonName;//购买按钮名称
	
	

	public Date getContractStart() {
		return contractStart;
	}

	public void setContractStart(Date contractStart) {
		this.contractStart = contractStart;
	}

	public Date getContractEnd() {
		return contractEnd;
	}

	public void setContractEnd(Date contractEnd) {
		this.contractEnd = contractEnd;
	}

	public String getTenderEndtime() {
		return tenderEndtime;
	}

	public void setTenderEndtime(String tenderEndtime) {
		this.tenderEndtime = tenderEndtime;
	}

	public Integer getMortgageTypeid() {
		return mortgageTypeid;
	}

	public void setMortgageTypeid(Integer mortgageTypeid) {
		this.mortgageTypeid = mortgageTypeid;
	}

	public Integer getMortgageId() {
		return mortgageId;
	}

	public void setMortgageId(Integer mortgageId) {
		this.mortgageId = mortgageId;
	}

	public BigDecimal getExpectApr() {
		return expectApr;
	}

	public void setExpectApr(BigDecimal expectApr) {
		this.expectApr = expectApr;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getSiteId() {
		return siteId;
	}

	public void setSiteId(Short siteId) {
		this.siteId = siteId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public String getSortName() {
		if (StringUtil.isLen(name, 10)) {
			return StringUtil.intercept(name, 8);
		} else {
			return name;
		}
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	private String statusStr;

	public String getStatusStr() {
		/*String statusStr = "";
		if (tenderTime != null && status == 1) {
			if (tenderTime.after(new Date())) {
				statusStr = "预告";
			}
		} else if (status == 1) {
			statusStr = "热销";
		} else if (status == 3 && repaymentAccount.doubleValue() > repaymentYesaccount.doubleValue()) {
			statusStr = "还款中";
		} else if (status == 3 && repaymentAccount.doubleValue() == repaymentYesaccount.doubleValue()) {
			statusStr = "已还款";
		}*/
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public Byte getStatus() {

		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public String getLitpic() {
		return litpic;
	}

	public String getLitpicPath() {
		if (EmptyUtil.isNotEmpty(litpic)) {
			if (litpic.startsWith("http")) {
				return litpic;
			} else {
				return "http://file.8jielicai.com/ori/" + litpic;
			}
		} else {
			return litpic;
		}
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic == null ? null : litpic.trim();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag == null ? null : flag.trim();
	}

	public Byte getIsVouch() {
		return isVouch;
	}

	public void setIsVouch(Byte isVouch) {
		this.isVouch = isVouch;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Integer getViewType() {
		return viewType;
	}

	public void setViewType(Integer viewType) {
		this.viewType = viewType;
	}

	public String getVouchAward() {
		return vouchAward;
	}

	public void setVouchAward(String vouchAward) {
		this.vouchAward = vouchAward == null ? null : vouchAward.trim();
	}

	public String getVouchUser() {
		return vouchUser;
	}

	public void setVouchUser(String vouchUser) {
		this.vouchUser = vouchUser == null ? null : vouchUser.trim();
	}

	public String getVouchAccount() {
		return vouchAccount;
	}

	public void setVouchAccount(String vouchAccount) {
		this.vouchAccount = vouchAccount == null ? null :

		vouchAccount.trim();
	}

	public Integer getVouchTimes() {
		return vouchTimes;
	}

	public void setVouchTimes(Integer vouchTimes) {
		this.vouchTimes = vouchTimes;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}

	public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish == null ? null : publish.trim();
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer == null ? null : customer.trim();
	}

	public String getNumberId() {
		return numberId;
	}

	public void setNumberId(String numberId) {
		this.numberId = numberId == null ? null : numberId.trim();
	}

	public Integer getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(Integer verifyUser) {
		this.verifyUser = verifyUser;
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
		this.verifyRemark = verifyRemark == null ? null :

		verifyRemark.trim();
	}

	public Integer getRepaymentUser() {
		return repaymentUser;
	}

	public void setRepaymentUser(Integer repaymentUser) {
		this.repaymentUser = repaymentUser;
	}

	public BigDecimal getForstAccount() {
		return forstAccount;
	}

	public void setForstAccount(BigDecimal forstAccount) {
		this.forstAccount = forstAccount;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getMonthlyRepayment() {
		return monthlyRepayment;
	}

	public void setMonthlyRepayment(BigDecimal monthlyRepayment) {
		this.monthlyRepayment = monthlyRepayment;
	}

	public BigDecimal getRepaymentYesaccount() {

		return repaymentYesaccount;
	}

	public void setRepaymentYesaccount(BigDecimal repaymentYesaccount) {
		this.repaymentYesaccount = repaymentYesaccount;
	}

	public BigDecimal getRepaymentYesinterest() {
		return repaymentYesinterest;
	}

	public void setRepaymentYesinterest(BigDecimal repaymentYesinterest) {
		this.repaymentYesinterest = repaymentYesinterest;
	}

	public Integer getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(Integer repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public String getRepaymentRemark() {
		return repaymentRemark;
	}

	public void setRepaymentRemark(String repaymentRemark) {
		this.repaymentRemark = repaymentRemark == null ? null :

		repaymentRemark.trim();
	}

	public Integer getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(Integer successTime) {
		this.successTime = successTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getPaymentAccount() {
		return paymentAccount;
	}

	public void setPaymentAccount(BigDecimal paymentAccount) {
		this.paymentAccount = paymentAccount;
	}

	public String getEachTime() {
		return eachTime;
	}

	public void setEachTime(String eachTime) {
		this.eachTime = eachTime == null ? null : eachTime.trim();
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use == null ? null : use.trim();
	}

	public Short getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Short timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Byte getStyle() {
		return style;
	}

	public void setStyle(Byte style) {
		this.style = style;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public String getAccountStr() {
		return StringUtil.getFinanceString(this.account.doubleValue());
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 * 
	 * @param accountStr
	 */
	public void setAccountStr(String accountStr) {
		this.accountStr = accountStr;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(BigDecimal accountYes) {
		this.accountYes = accountYes;
	}

	public Short getTenderTimes() {
		return tenderTimes;
	}

	public void setTenderTimes(Short tenderTimes) {
		this.tenderTimes = tenderTimes;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
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

	public Integer getValidTime() {
		return validTime;
	}

	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}

	public Byte getAward() {
		return award;
	}

	public void setAward(Byte award) {
		this.award = award;
	}

	public BigDecimal getPartAccount() {
		return partAccount;
	}

	public void setPartAccount(BigDecimal partAccount) {
		this.partAccount = partAccount;
	}

	public String getFunds() {
		return funds;
	}

	public void setFunds(String funds) {
		this.funds = funds == null ? null : funds.trim();
	}

	public String getIsFalse() {
		return isFalse;
	}

	public void setIsFalse(String isFalse) {
		this.isFalse = isFalse == null ? null : isFalse.trim();
	}

	public Byte getOpenAccount() {
		return openAccount;
	}

	public void setOpenAccount(Byte openAccount) {
		this.openAccount = openAccount;
	}

	public Byte getOpenBorrow() {
		return openBorrow;
	}

	public void setOpenBorrow(Byte openBorrow) {
		this.openBorrow = openBorrow;
	}

	public Byte getOpenTender() {
		return openTender;
	}

	public void setOpenTender(Byte openTender) {
		this.openTender = openTender;
	}

	public Byte getOpenCredit() {
		return openCredit;
	}

	public void setOpenCredit(Byte openCredit) {
		this.openCredit = openCredit;
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

	public Byte getIsMb() {
		return isMb;
	}

	public void setIsMb(Byte isMb) {
		this.isMb = isMb;
	}

	public Byte getIsFast() {
		return isFast;
	}

	public void setIsFast(Byte isFast) {
		this.isFast = isFast;
	}

	public Byte getIsJin() {
		return isJin;
	}

	public void setIsJin(Byte isJin) {
		this.isJin = isJin;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public Byte getIsday() {
		return isday;
	}

	public void setIsday(Byte isday) {
		this.isday = isday;
	}

	public Byte getTimeLimitDay() {
		return timeLimitDay;
	}

	public void setTimeLimitDay(Byte timeLimitDay) {
		this.timeLimitDay = timeLimitDay;
	}

	public Byte getDanbao() {
		return danbao;
	}

	public void setDanbao(Byte danbao) {
		this.danbao = danbao;
	}

	public Byte getIsKuai() {
		return isKuai;
	}

	public void setIsKuai(Byte isKuai) {
		this.isKuai = isKuai;
	}

	public Byte getIsLz() {
		return isLz;
	}

	public void setIsLz(Byte isLz) {
		this.isLz = isLz;
	}

	public String getBiaoType() {
		return biaoType;
	}

	public void setBiaoType(String biaoType) {
		this.biaoType = biaoType == null ? null : biaoType.trim();
	}

	public BigDecimal getBorrowFee() {
		return borrowFee;
	}

	public void setBorrowFee(BigDecimal borrowFee) {
		this.borrowFee = borrowFee;
	}

	public Byte getIsXin() {
		return isXin;
	}

	public void setIsXin(Byte isXin) {
		this.isXin = isXin;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getCompletePercent() {
		if (accountYes != null && account != null) {
			BigDecimal a = accountYes.divide(account, 20, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN);
			return a.toString() + "%";
		}
		return null;
	}

	public boolean getBorrowIsRepay() {
		if (repaymentAccount != null && repaymentYesaccount != null) {
			return repaymentAccount.doubleValue() <

			repaymentYesaccount.doubleValue() + 1 ? true : false;
		}
		return false;
	}

	public Integer getCompleteInteger() {

		Integer result = 0;
		String completeString = this.getCompletePercent();
		if (completeString != null) {
			completeString = completeString.substring(0, completeString.indexOf("."));
			Integer complete = new Integer(completeString);
			if (complete == 0) {
				result = 0;
			} else if (complete >= 1 && complete <= 5) {
				result = 5;
			} else if (complete >= 6 && complete <= 10) {
				result = 10;
			} else if (complete >= 11 && complete <= 15) {
				result = 15;
			} else if (complete >= 16 && complete <= 20) {
				result = 20;
			} else if (complete >= 21 && complete <= 25) {
				result = 25;
			} else if (complete >= 26 && complete <= 30) {
				result = 30;
			} else if (complete >= 31 && complete <= 35) {
				result = 35;
			} else if (complete >= 36 && complete <= 40) {
				result = 40;
			} else if (complete >= 41 && complete <= 45) {
				result = 45;
			} else if (complete >= 46 && complete <= 50) {
				result = 50;
			} else if (complete >= 51 && complete <= 55) {
				result = 55;
			} else if (complete >= 56 && complete <= 60) {
				result = 60;
			} else if (complete >= 61 && complete <= 65) {
				result = 65;
			} else if (complete >= 66 && complete <= 70) {
				result = 70;
			} else if (complete >= 71 && complete <= 75) {
				result = 75;
			} else if (complete >= 76 && complete <= 80) {
				result = 80;
			} else if (complete >= 81 && complete <= 85) {
				result = 85;
			} else if (complete >= 86 && complete <= 90) {
				result = 90;
			} else if (complete >= 91 && complete <= 95) {
				result = 95;
			} else if (complete >= 96 && complete <= 100) {
				result = 100;
			}
		}

		return result;
	}

	public static void main(String[] args) {
		double a = 20.00;
		double b = 30.00;
		double resl = a / b;
		System.out.println(resl);
	}

	public String getAddTimeStr() {
		if (EmptyUtil.isNotEmpty(addtime)) {
			return DateUtil.getStringDateByLongDate

			(addtime.longValue(), DateUtil.DATESHOWFORMAT);
		}
		return null;
	}

	public String getVerifyTimeStr() {
		if (EmptyUtil.isNotEmpty(verifyTime)) {
			return DateUtil.getStringDateByLongDate

			(verifyTime.longValue(), DateUtil.DATESHOWFORMAT);
		}
		return null;
	}

	public String getAddTimeStr1() {
		if (EmptyUtil.isNotEmpty(addtime)) {
			return DateUtil.getStringDateByLongDate

			(addtime.longValue(), DateUtil.DATETIMESHOWFORMAT);
		}
		return null;
	}

	public String getVerifyTimeStr1() {
		if (EmptyUtil.isNotEmpty(verifyTime)) {
			return DateUtil.getStringDateByLongDate

			(verifyTime.longValue(), DateUtil.DATETIMESHOWFORMAT);
		}
		return null;
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 */
	public void setAddTimeStr(String addtimeStr) {
		this.addtimeStr = addtimeStr;
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 */
	public void setAddTimeStr1(String addtimeStr1) {
		this.addtimeStr1 = addtimeStr1;
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 */
	public void setVerifyTimeStr(String verifyTimeStr) {
		this.verifyTimeStr = verifyTimeStr;
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 */
	public void setVerifyTimeStr1(String verifyTimeStr1) {
		this.verifyTimeStr1 = verifyTimeStr1;
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 */
	public void setBorrowIsRepay(Boolean borrowIsRepay) {
		this.borrowIsRepay = borrowIsRepay;
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 */
	public void setCompleteInteger(Integer completeInteger) {
		this.completeInteger = completeInteger;
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 */
	public void setCompletePercent(String completePercent) {
		this.completePercent = completePercent;
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 */
	public void setLitpicPath(String litpicPath) {
		this.litpicPath = litpicPath;
	}

	/**
	 * 发送cjd接口时候 fastjson转换需要这个方法
	 */
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public Date getTenderTime() {
		return tenderTime;
	}

	public void setTenderTime(Date tenderTime) {
		this.tenderTime = tenderTime;
	}

	//产品相关
	
	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getProductGroup() {
		return productGroup;
	}

	public void setProductGroup(Integer productGroup) {
		this.productGroup = productGroup;
	}

	public Integer getJyStatus() {
		return jyStatus;
	}

	public void setJyStatus(Integer jyStatus) {
		this.jyStatus = jyStatus;
	}

	public Integer getQxDate() {
		return qxDate;
	}

	public void setQxDate(Integer qxDate) {
		this.qxDate = qxDate;
	}

	public Integer getHkType() {
		return hkType;
	}

	public void setHkType(Integer hkType) {
		this.hkType = hkType;
	}

	public BigDecimal getLjMoney() {
		return ljMoney;
	}

	public void setLjMoney(BigDecimal ljMoney) {
		this.ljMoney = ljMoney;
	}

	public Integer getYyTags() {
		return yyTags;
	}

	public void setYyTags(Integer yyTags) {
		this.yyTags = yyTags;
	}

	public Integer getBzTags() {
		return bzTags;
	}

	public void setBzTags(Integer bzTags) {
		this.bzTags = bzTags;
	}

	public String getBuyButtonName() {
		return buyButtonName;
	}

	public void setBuyButtonName(String buyButtonName) {
		this.buyButtonName = buyButtonName;
	}


	
	
	
	
}