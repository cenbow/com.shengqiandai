package cn.p2p.p2p.prd.mobile.method.request.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseRequest;

public class GeneralRequestVO extends MobileBaseRequest {
	
	/**
	 * 标的id,新浪绑定银行卡id,抵扣券id,其他礼品id,红包id,加息卡id,站内信id,系统消息id
	 */
	private Integer id; //标的id,新浪绑定银行卡id,抵扣券id,其他礼品id,红包id,加息卡id,站内信id,系统消息id
	/**
	 * 获取验证码手机号,注册手机号
	 */
	private String phone;//获取验证码手机号,注册手机号
	/**
	 * 加密后手机号
	 */
	private String desPhone; //加密后手机号
	/**
	 * 登陆用户名
	 */
	private String userName; //登陆用户名
	/**
	 * 登陆用户密码
	 */
	private String password; //登陆用户密码
	/**
	 * 支付密码
	 */
	private String paypassword; //支付密码
	/**
	 * 充值金额,投资金额,各种金额
	 */
	private BigDecimal money; //充值金额,投资金额,各种金额
	/**
	 * 用户输入验证码
	 */
	private String verifCode; //用户输入验证码
	/**
	 * 注册系统生成验证码
	 */
	private String systemCode; //注册系统生成验证码
	/**
	 * 新浪短信推进ticket
	 */
	private String ticket; //新浪短信推进ticket
	/**
	 * 充值流水号,订单号
	 */
	private String outTradeNo; //充值流水号,订单号
	/**
	 * 
	 */
	private String chargeType;//
	/**
	 * 注册的校验手机号,用户名,邮箱
	 */
	private String verifValue; //注册的校验手机号,用户名,邮箱
	/**
	 * 删除站内信批量id
	 */
	private String messageIds;//删除站内信批量id
	/**
	 * 删除系统消息批量id
	 */
	private String systemMessageIds;//删除系统消息批量id
	/**
	 * 红包状态
	 */
	private Integer status;//红包状态,各种状态
	/**
	 * 身份证姓名
	 */
	private String realName; //身份证姓名
	/**
	 * 身份证号
	 */
	private String realCard; //身份证号
	/**
	 * 邀请码
	 */
	private String inviteUserid; //邀请码
	/**
	 * 是否使用加息卡
	 */
	private Integer isUseHikes; //是否使用加息卡
	
	private String hongbao; //是否提现抵扣券
	
	
	private BigDecimal tenderMoney;//充值后并投资的金额
	
	private Integer tenderBorrowId;//充值后并投资的标的ID
	
	private Integer tenderType;//充值后并投资的标的类型
	
	private Integer scId;//新浪绑定银行卡记录ID
	
	private String dateTime;
	
	
	/**
	 * sqd支付记录相关
	 * @return
	 */
		//支付记录产品id

	    private Integer productId;
	    //支付记录用户id
	    private Integer userId;
	    //支付记录订单id
	    private Integer tradeNo;
	    //支付金额
	    private BigDecimal payMoney;
	    //连连支付返回的支付结果
	    private String resultPay;
	    //备注
	    private String remark;
	    //支付记录添加日期
	    private Date addDate;
	    //支付记录添加ip
	    private String addIp;
	    
	
	public Integer getScId() {
		return scId;
	}
	public void setScId(Integer scId) {
		this.scId = scId;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	/**
	 * @return the hongbao
	 */
	public String getHongbao() {
		return hongbao;
	}
	/**
	 * @param hongbao the hongbao to set
	 */
	public void setHongbao(String hongbao) {
		this.hongbao = hongbao;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDesPhone() {
		return desPhone;
	}
	public void setDesPhone(String desPhone) {
		this.desPhone = desPhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPaypassword() {
		return paypassword;
	}
	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getVerifCode() {
		return verifCode;
	}
	public void setVerifCode(String verifCode) {
		this.verifCode = verifCode;
	}
	public String getSystemCode() {
		return systemCode;
	}
	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getVerifValue() {
		return verifValue;
	}
	public void setVerifValue(String verifValue) {
		this.verifValue = verifValue;
	}
	public String getMessageIds() {
		return messageIds;
	}
	public void setMessageIds(String messageIds) {
		this.messageIds = messageIds;
	}
	public String getSystemMessageIds() {
		return systemMessageIds;
	}
	public void setSystemMessageIds(String systemMessageIds) {
		this.systemMessageIds = systemMessageIds;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getRealCard() {
		return realCard;
	}
	public void setRealCard(String realCard) {
		this.realCard = realCard;
	}
	public String getInviteUserid() {
		return inviteUserid;
	}
	public void setInviteUserid(String inviteUserid) {
		this.inviteUserid = inviteUserid;
	}
	public Integer getIsUseHikes() {
		return isUseHikes;
	}
	public void setIsUseHikes(Integer isUseHikes) {
		this.isUseHikes = isUseHikes;
	}
	public BigDecimal getTenderMoney() {
		return tenderMoney;
	}
	public void setTenderMoney(BigDecimal tenderMoney) {
		this.tenderMoney = tenderMoney;
	}
	public Integer getTenderBorrowId() {
		return tenderBorrowId;
	}
	public void setTenderBorrowId(Integer tenderBorrowId) {
		this.tenderBorrowId = tenderBorrowId;
	}
	public Integer getTenderType() {
		return tenderType;
	}
	public void setTenderType(Integer tenderType) {
		this.tenderType = tenderType;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTradeNo() {
		return tradeNo;
	}
	public void setTradeNo(Integer tradeNo) {
		this.tradeNo = tradeNo;
	}
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	public String getResultPay() {
		return resultPay;
	}
	public void setResultPay(String resultPay) {
		this.resultPay = resultPay;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public String getAddIp() {
		return addIp;
	}
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}
	
	
}
