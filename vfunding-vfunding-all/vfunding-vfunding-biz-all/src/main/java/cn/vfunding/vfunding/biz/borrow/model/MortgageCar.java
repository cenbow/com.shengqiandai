package cn.vfunding.vfunding.biz.borrow.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class MortgageCar extends  BaseModel{
    private Integer id;

    private String ownerName;

    private String ownerCardid;

    private String ownerMobile;

    private String ownerAddress;

    private String carNumber;

    private Date certificationDate;

    private Date registerDate;

    private Date checkValidDate;

    private String carLicense;

    private String carStrutNum;

    private Date addTime;
    
    private String brand;
    
    private BigDecimal buyMoney;
    
    private BigDecimal assessMoney;
    
    public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public BigDecimal getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(BigDecimal buyMoney) {
		this.buyMoney = buyMoney;
	}

	public BigDecimal getAssessMoney() {
		return assessMoney;
	}

	public void setAssessMoney(BigDecimal assessMoney) {
		this.assessMoney = assessMoney;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    public String getOwnerCardid() {
        return ownerCardid;
    }

    public void setOwnerCardid(String ownerCardid) {
        this.ownerCardid = ownerCardid == null ? null : ownerCardid.trim();
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile == null ? null : ownerMobile.trim();
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress == null ? null : ownerAddress.trim();
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber == null ? null : carNumber.trim();
    }

    public Date getCertificationDate() {
        return certificationDate;
    }

    public void setCertificationDate(Date certificationDate) {
    	this.certificationDate = certificationDate;
    }
    
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
    	this.registerDate = registerDate;
    }

    public Date getCheckValidDate() {
        return checkValidDate;
    }

    public void setCheckValidDate(Date checkValidDate) {
    	this.checkValidDate = checkValidDate;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense == null ? null : carLicense.trim();
    }

    public String getCarStrutNum() {
        return carStrutNum;
    }

    public void setCarStrutNum(String carStrutNum) {
        this.carStrutNum = carStrutNum == null ? null : carStrutNum.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
		this.addTime = addTime;
    }
}