package cn.vfunding.vfunding.biz.common.vo;

import java.util.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.beans.DateUtil;

public class BorrowVO extends BaseVO {

	private String register_Date;
	private String certification_Date;
	private String checkValid_Date;
	
	private String contract_Start;//合同开始时间
	private String contract_End;//合同结束日期
	
	private String typeName;//抵押品种
	
	private String valicode;

	private Integer borrowId;
	private Integer mcId;//车信息id
	private Integer handleType;
	
	/**
	 * 转换时间
	 * @author liuhuan
	 */
	public Date getContract_StartStr(){
		if(this.contract_Start!=null&&this.contract_Start!=""){
			return DateUtil.getDateToString(this.contract_Start, "yyyy-MM-dd");
		}
		return null;
	}
	public Date getContract_EndStr(){
		if(this.contract_End!=null&&this.contract_End!=""){
			return DateUtil.getDateToString(this.contract_End, "yyyy-MM-dd");
		}
		return null;
	}
	
	public String getContract_Start() {
		return contract_Start;
	}

	public void setContract_Start(String contract_Start) {
		this.contract_Start = contract_Start;
	}

	public String getContract_End() {
		return contract_End;
	}

	public void setContract_End(String contract_End) {
		this.contract_End = contract_End;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getMcId() {
		return mcId;
	}

	public void setMcId(Integer mcId) {
		this.mcId = mcId;
	}

	public Integer getHandleType() {
		return handleType;
	}

	public void setHandleType(Integer handleType) {
		this.handleType = handleType;
	}

	public String getRegister_Date() {
		return register_Date;
	}

	public void setRegister_Date(String register_Date) {
		this.register_Date = register_Date;
	}

	public String getCertification_Date() {
		return certification_Date;
	}

	public void setCertification_Date(String certification_Date) {
		this.certification_Date = certification_Date;
	}

	public String getCheckValid_Date() {
		return checkValid_Date;
	}

	public void setCheckValid_Date(String checkValid_Date) {
		this.checkValid_Date = checkValid_Date;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getValicode() {
		return valicode;
	}

	public void setValicode(String valicode) {
		this.valicode = valicode;
	}
}
