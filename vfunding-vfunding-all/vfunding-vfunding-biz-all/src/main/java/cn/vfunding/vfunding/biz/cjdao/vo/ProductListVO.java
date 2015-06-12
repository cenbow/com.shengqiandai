package cn.vfunding.vfunding.biz.cjdao.vo;

import java.sql.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class ProductListVO extends BaseVO{
	private Integer thirdproductid;// 第三方产品id Y
	
	private String productname;// 产品名称（项目名称） Y
	
	private String companyname;// 机构名称（注和微积金网对接时，companyName=微积金网） Y
	
	private Double startinvestmentmoney;// 起投金额 double类型以元为单位 Y
	
	private Double acceptinvestmentmoney;// 可投金额 double类型以元为单位
	// 注（可投金额变化时，要调用该接口，更新财经道该项目的的此数据） Y
	
	private Date preselltime;// 预售时间 N
	
	private Integer loandeadline;// 借款期限 ;// 整数;// Y
	
	private Integer deadlineunit;// 借款期限时间单位（0：以天为单位，1：为月为单位，2：以年为单位） 默认以月为单位 N
	
	private Double expectedrate;// 预期收益 百分比，Double类型(0.0) Y
	
	private Integer risktype;// 风险类型(1:保本保息，2：保本，3：浮动收益) Y
	
	private Integer incomeway;// 收益方式（还款方式 1：等额本息，2：先息后本,3:还本付息,4:到期一次还本付息） Y
	
	private Double handlingcharge;// 手续费 数字类型 N
	
	private String creditrating;// 信用等级（ a,b,c,d,e 五级 其中a级别最高，e级别最低，
								// 微积金网转换成对应的信用等级） Y
	
	private Date intereststarttime;// 计息开始时间(满标后需同步该时间) N
	
	private Date interestfinishtime;// 计息结束时间(满标后需同步该时间) N
	
	private Integer iscurrent;// 是否是活期 (0:是，1：不是) Y
	
	private Integer isredeem;// 是否可赎回 (0:是，1：不是) Y
	
	private Integer isassignment;// 是否可以转让 (0:是,1：不是) Y
	
	private String md5_value;// md5签名数据 Y

	public Integer getThirdproductid() {
		return thirdproductid;
	}

	public void setThirdproductid(Integer thirdproductid) {
		this.thirdproductid = thirdproductid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Double getStartinvestmentmoney() {
		return startinvestmentmoney;
	}

	public void setStartinvestmentmoney(Double startinvestmentmoney) {
		this.startinvestmentmoney = startinvestmentmoney;
	}

	public Double getAcceptinvestmentmoney() {
		return acceptinvestmentmoney;
	}

	public void setAcceptinvestmentmoney(Double acceptinvestmentmoney) {
		this.acceptinvestmentmoney = acceptinvestmentmoney;
	}

	public Date getPreselltime() {
		return preselltime;
	}

	public void setPreselltime(Date preselltime) {
		this.preselltime = preselltime;
	}

	public Integer getLoandeadline() {
		return loandeadline;
	}

	public void setLoandeadline(Integer loandeadline) {
		this.loandeadline = loandeadline;
	}

	public Integer getDeadlineunit() {
		return deadlineunit;
	}

	public void setDeadlineunit(Integer deadlineunit) {
		this.deadlineunit = deadlineunit;
	}

	public Double getExpectedrate() {
		return expectedrate;
	}

	public void setExpectedrate(Double expectedrate) {
		this.expectedrate = expectedrate;
	}

	public Integer getRisktype() {
		return risktype;
	}

	public void setRisktype(Integer risktype) {
		this.risktype = risktype;
	}

	public Integer getIncomeway() {
		return incomeway;
	}

	public void setIncomeway(Integer incomeway) {
		this.incomeway = incomeway;
	}

	public Double getHandlingcharge() {
		return handlingcharge;
	}

	public void setHandlingcharge(Double handlingcharge) {
		this.handlingcharge = handlingcharge;
	}

	public String getCreditrating() {
		return creditrating;
	}

	public void setCreditrating(String creditrating) {
		this.creditrating = creditrating;
	}

	public Date getIntereststarttime() {
		return intereststarttime;
	}

	public void setIntereststarttime(Date intereststarttime) {
		this.intereststarttime = intereststarttime;
	}

	public Date getInterestfinishtime() {
		return interestfinishtime;
	}

	public void setInterestfinishtime(Date interestfinishtime) {
		this.interestfinishtime = interestfinishtime;
	}

	public Integer getIscurrent() {
		return iscurrent;
	}

	public void setIscurrent(Integer iscurrent) {
		this.iscurrent = iscurrent;
	}

	public Integer getIsredeem() {
		return isredeem;
	}

	public void setIsredeem(Integer isredeem) {
		this.isredeem = isredeem;
	}

	public Integer getIsassignment() {
		return isassignment;
	}

	public void setIsassignment(Integer isassignment) {
		this.isassignment = isassignment;
	}

	public String getMd5_value() {
		return md5_value;
	}

	public void setMd5_value(String md5_value) {
		this.md5_value = md5_value;
	}
	
}
