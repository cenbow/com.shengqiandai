package cn.vfunding.vfunding.prd.www.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class UploadAttestationVO extends BaseVO {

	private String remark;
	private String typeId;
	private String vcode;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	
	
}
