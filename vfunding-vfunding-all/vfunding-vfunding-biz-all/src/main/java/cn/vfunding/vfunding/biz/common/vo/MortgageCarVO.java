package cn.vfunding.vfunding.biz.common.vo;

import java.sql.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class MortgageCarVO extends BaseVO{
     private String ownerName;          //物主姓名
     private String name;               //标题
     private String ownerCardId;        //身份证号
     private String ownerMobile;        //手机
     private String ownerAddress;       //籍贯
     private String carStrutNum;        //识别代码
     private String carLicense;         //车牌号
     private String carNumber;          //发动机号
     private Date   registerDate;       //注册日期
     private Date   certificationDate;  //发证日期
     private Date checkYalidDate;       //检验有效日期
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOwnerCardId() {
		return ownerCardId;
	}
	public void setOwnerCardId(String ownerCardId) {
		this.ownerCardId = ownerCardId;
	}
	public String getOwnerMobile() {
		return ownerMobile;
	}
	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}
	public String getOwnerAddress() {
		return ownerAddress;
	}
	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}
	public String getCarStrutNum() {
		return carStrutNum;
	}
	public void setCarStrutNum(String carStrutNum) {
		this.carStrutNum = carStrutNum;
	}
	public String getCarLicense() {
		return carLicense;
	}
	public void setCarLicense(String carLicense) {
		this.carLicense = carLicense;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Date getCertificationDate() {
		return certificationDate;
	}
	public void setCertificationDate(Date certificationDate) {
		this.certificationDate = certificationDate;
	}
	public Date getCheckYalidDate() {
		return checkYalidDate;
	}
	public void setCheckYalidDate(Date checkYalidDate) {
		this.checkYalidDate = checkYalidDate;
	}
     
}
