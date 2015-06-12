package cn.vfunding.vfunding.biz.sina.model;

import cn.vfunding.vfunding.common.model.BaseModel;

public class SinaDic extends BaseModel{
	
	
    private String dicCode;

    private String dicMsg;

    private String dicType;

    public String getDicCode() {
        return dicCode;
    }

    public void setDicCode(String dicCode) {
        this.dicCode = dicCode == null ? null : dicCode.trim();
    }

    public String getDicMsg() {
        return dicMsg;
    }

    public void setDicMsg(String dicMsg) {
        this.dicMsg = dicMsg == null ? null : dicMsg.trim();
    }

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType == null ? null : dicType.trim();
    }
}