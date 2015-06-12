package cn.vfunding.vfunding.biz.system.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BiaoType extends  BaseModel{
    private Integer id;

    private String biaoTypeName;

    private Byte available;

    private Byte passwordModel;

    private Byte dayModel;

    private Byte autoVerify;

    private Byte autoFullVerify;

    private Integer minAmount;

    private Integer maxAmount;

    private BigDecimal minInterestRate;

    private BigDecimal maxInterestRate;

    private Byte advanceTime;

    private Byte advanceScope;

    private Byte advanceVipScope;

    private BigDecimal lateInterestRate;

    private BigDecimal borrowFeeRateStart;

    private Byte borrowFeeRateStartMonthNum;

    private BigDecimal borrowFeeRate;

    private BigDecimal borrowDayFeeRate;

    private BigDecimal interestFeeRate;

    private BigDecimal frostRate;

    private BigDecimal advanceRate;

    private BigDecimal advanceVipRate;

    private BigDecimal lateCustomerInterestRate;

    private Byte lateInterestScope;

    private Byte maxTenderTimes;

    private String showName;

    private BigDecimal extractRate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBiaoTypeName() {
        return biaoTypeName;
    }

    public void setBiaoTypeName(String biaoTypeName) {
        this.biaoTypeName = biaoTypeName == null ? null : biaoTypeName.trim();
    }

    public Byte getAvailable() {
        return available;
    }

    public void setAvailable(Byte available) {
        this.available = available;
    }

    public Byte getPasswordModel() {
        return passwordModel;
    }

    public void setPasswordModel(Byte passwordModel) {
        this.passwordModel = passwordModel;
    }

    public Byte getDayModel() {
        return dayModel;
    }

    public void setDayModel(Byte dayModel) {
        this.dayModel = dayModel;
    }

    public Byte getAutoVerify() {
        return autoVerify;
    }

    public void setAutoVerify(Byte autoVerify) {
        this.autoVerify = autoVerify;
    }

    public Byte getAutoFullVerify() {
        return autoFullVerify;
    }

    public void setAutoFullVerify(Byte autoFullVerify) {
        this.autoFullVerify = autoFullVerify;
    }

    public Integer getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Integer minAmount) {
        this.minAmount = minAmount;
    }

    public Integer getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Integer maxAmount) {
        this.maxAmount = maxAmount;
    }

    public BigDecimal getMinInterestRate() {
        return minInterestRate;
    }

    public void setMinInterestRate(BigDecimal minInterestRate) {
        this.minInterestRate = minInterestRate;
    }

    public BigDecimal getMaxInterestRate() {
        return maxInterestRate;
    }

    public void setMaxInterestRate(BigDecimal maxInterestRate) {
        this.maxInterestRate = maxInterestRate;
    }

    public Byte getAdvanceTime() {
        return advanceTime;
    }

    public void setAdvanceTime(Byte advanceTime) {
        this.advanceTime = advanceTime;
    }

    public Byte getAdvanceScope() {
        return advanceScope;
    }

    public void setAdvanceScope(Byte advanceScope) {
        this.advanceScope = advanceScope;
    }

    public Byte getAdvanceVipScope() {
        return advanceVipScope;
    }

    public void setAdvanceVipScope(Byte advanceVipScope) {
        this.advanceVipScope = advanceVipScope;
    }

    public BigDecimal getLateInterestRate() {
        return lateInterestRate;
    }

    public void setLateInterestRate(BigDecimal lateInterestRate) {
        this.lateInterestRate = lateInterestRate;
    }

    public BigDecimal getBorrowFeeRateStart() {
        return borrowFeeRateStart;
    }

    public void setBorrowFeeRateStart(BigDecimal borrowFeeRateStart) {
        this.borrowFeeRateStart = borrowFeeRateStart;
    }

    public Byte getBorrowFeeRateStartMonthNum() {
        return borrowFeeRateStartMonthNum;
    }

    public void setBorrowFeeRateStartMonthNum(Byte borrowFeeRateStartMonthNum) {
        this.borrowFeeRateStartMonthNum = borrowFeeRateStartMonthNum;
    }

    public BigDecimal getBorrowFeeRate() {
        return borrowFeeRate;
    }

    public void setBorrowFeeRate(BigDecimal borrowFeeRate) {
        this.borrowFeeRate = borrowFeeRate;
    }

    public BigDecimal getBorrowDayFeeRate() {
        return borrowDayFeeRate;
    }

    public void setBorrowDayFeeRate(BigDecimal borrowDayFeeRate) {
        this.borrowDayFeeRate = borrowDayFeeRate;
    }

    public BigDecimal getInterestFeeRate() {
        return interestFeeRate;
    }

    public void setInterestFeeRate(BigDecimal interestFeeRate) {
        this.interestFeeRate = interestFeeRate;
    }

    public BigDecimal getFrostRate() {
        return frostRate;
    }

    public void setFrostRate(BigDecimal frostRate) {
        this.frostRate = frostRate;
    }

    public BigDecimal getAdvanceRate() {
        return advanceRate;
    }

    public void setAdvanceRate(BigDecimal advanceRate) {
        this.advanceRate = advanceRate;
    }

    public BigDecimal getAdvanceVipRate() {
        return advanceVipRate;
    }

    public void setAdvanceVipRate(BigDecimal advanceVipRate) {
        this.advanceVipRate = advanceVipRate;
    }

    public BigDecimal getLateCustomerInterestRate() {
        return lateCustomerInterestRate;
    }

    public void setLateCustomerInterestRate(BigDecimal lateCustomerInterestRate) {
        this.lateCustomerInterestRate = lateCustomerInterestRate;
    }

    public Byte getLateInterestScope() {
        return lateInterestScope;
    }

    public void setLateInterestScope(Byte lateInterestScope) {
        this.lateInterestScope = lateInterestScope;
    }

    public Byte getMaxTenderTimes() {
        return maxTenderTimes;
    }

    public void setMaxTenderTimes(Byte maxTenderTimes) {
        this.maxTenderTimes = maxTenderTimes;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName == null ? null : showName.trim();
    }

    public BigDecimal getExtractRate() {
        return extractRate;
    }

    public void setExtractRate(BigDecimal extractRate) {
        this.extractRate = extractRate;
    }
}