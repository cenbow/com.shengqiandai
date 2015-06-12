package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class CjdVO extends BaseVO{

	private String cjdAfterReg; //注册后推送接口
	
	private String cjdKey;
	
	private String companyid;
	
	private String uaccount; //财经道用户名
	
	private String code;//返回结果状态
	
	private String message;//返回结果消息

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUaccount() {
		return uaccount;
	}

	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}

	public String getCjdAfterReg() {
		return cjdAfterReg;
	}

	public void setCjdAfterReg(String cjdAfterReg) {
		this.cjdAfterReg = cjdAfterReg;
	}

	public String getCjdKey() {
		return cjdKey;
	}

	public void setCjdKey(String cjdKey) {
		this.cjdKey = cjdKey;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
}
