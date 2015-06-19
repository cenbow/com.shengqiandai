package cn.p2p.p2p.biz.sqdpaylog.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * sqd支付记录表
 * @author huangyuancheng
 *
 */

public class SqdPayLog {
    private Integer payLogId;

    private Integer productId;

    private Integer userId;

    private Integer tradeNo;

    private BigDecimal payMoney;

    private String resultPay;

    private String remark;

    private Date addDate;

    private String addIp;

    public Integer getPayLogId() {
        return payLogId;
    }

    public void setPayLogId(Integer payLogId) {
        this.payLogId = payLogId;
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
        this.resultPay = resultPay == null ? null : resultPay.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
        this.addIp = addIp == null ? null : addIp.trim();
    }
}