package cn.vfunding.vfunding.biz.account.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;
/**
 * 体验金账户
 * @author liuhuan
 */
@SuppressWarnings("serial")
public class AccountFeel extends BaseModel{
    private Integer id;

    private Integer userId;

    private BigDecimal total; // 体验金总额

    private BigDecimal useMoney; // 可用体验金

    private BigDecimal noUseMoney; // 冻结体验金

    private BigDecimal collection; // 待收体验金
    
    private String other; // 预留字段

    
    public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
}