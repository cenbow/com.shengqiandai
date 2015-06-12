package cn.vfunding.vfunding.prd.www.vo.cjdao;

import java.sql.Date;

public class ProductbuyVO {
	private String uaccount;// 购买用户在财经道的账号(回传参数) Y

	private String phone;// 手机号码 Y

	private String idcard;// 身份证号 N

	private Integer usertype;// 用户类型，0：新用户，1：老用户 Y

	private Integer companyid;// 机构id(回传参数) Y

	private Integer thirdproductid;// 第三方产品id Y

	private Integer productid;// 产品id (回传参数) N

	private String productname;// 产品名称 Y

	private Date buytime;// 购买日期 格式：yyyy-mm-dd Y

	private Double money;// 购买金额，Double类型 Y

	private Double expectedrate;// 收益率 百分比，Double类型(0.0) Y

	private Double realincome;// 当前收益: 该笔购买的累计总收益 金额Double类型(0.0) Y

	private Integer transactionstatus;// 交易状态，分别有0,1,2 交易状态有如下几种： 投标成功：2
										// 交易失败：1交易成功：0 Y
	private String ordercode;// 订单号 Y

	private Date intereststarttime;// 计息开始时间(满标后需同步该时间) N

	private Date interestfinishtime;// 计息结束时间(满标后需同步该时间) N

	private Double accountbalance;// 持有金额 Y

	private String md5_value;// md5签名数据 Y

	public String getUaccount() {
		return uaccount;
	}

	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Integer companyid) {
		this.companyid = companyid;
	}

	public Integer getThirdproductid() {
		return thirdproductid;
	}

	public void setThirdproductid(Integer thirdproductid) {
		this.thirdproductid = thirdproductid;
	}

	public Integer getProductid() {
		return productid;
	}

	public void setProductid(Integer productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Date getBuytime() {
		return buytime;
	}

	public void setBuytime(Date buytime) {
		this.buytime = buytime;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getExpectedrate() {
		return expectedrate;
	}

	public void setExpectedrate(Double expectedrate) {
		this.expectedrate = expectedrate;
	}

	public Double getRealincome() {
		return realincome;
	}

	public void setRealincome(Double realincome) {
		this.realincome = realincome;
	}

	public Integer getTransactionstatus() {
		return transactionstatus;
	}

	public void setTransactionstatus(Integer transactionstatus) {
		this.transactionstatus = transactionstatus;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
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

	public Double getAccountbalance() {
		return accountbalance;
	}

	public void setAccountbalance(Double accountbalance) {
		this.accountbalance = accountbalance;
	}

	public String getMd5_value() {
		return md5_value;
	}

	public void setMd5_value(String md5_value) {
		this.md5_value = md5_value;
	}

}
