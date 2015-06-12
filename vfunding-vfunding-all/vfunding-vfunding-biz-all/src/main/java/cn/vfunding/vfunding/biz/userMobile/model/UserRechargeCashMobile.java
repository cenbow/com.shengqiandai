package cn.vfunding.vfunding.biz.userMobile.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserRechargeCashMobile extends BaseModel  {
	
	@RestAttribute(len = 0, name = "提现金额", remark = "提现金额", notnull = false)
	private BigDecimal total;

	@RestAttribute(len = 0, name = "到账金额", remark = "到账金额", notnull = false)
	private BigDecimal money;
	
	
	@RestAttribute(len = 0, name = "红包", remark = "红包", notnull = false)
	private BigDecimal hongbao;

	@RestAttribute(len = 0, name = "类型", remark = "充值/提现", notnull = false)
	private String actionType;

	@RestAttribute(len = 0, name = "手续费", remark = "手续费", notnull = false)
	private BigDecimal fee;

	@RestAttribute(len = 0, name = "时间", remark = "时间", notnull = false)
	private String addtime;

	@RestAttribute(len = 0, name = "状态", remark = "1:成功,0:失败", notnull = false)
	private Integer status;

	private String statusName;
	
//	private String addtimeStr;
//	
//	
//	
//	
//	public String getAddtimeStr() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		return sdf.format(new Date(Integer.parseInt(addtime)));
//	}
//
//	public void setAddtimeStr(String addtimeStr) {
//		this.addtimeStr = addtimeStr;
//	}

	public String getStatusName() {
		if(this.getStatus() == 1){
			statusName =  "成功";
		}else if(this.getStatus() == 2){
			statusName =  "失败";
		}else{
			statusName =  "处理中";
		}
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getHongbao() {
		return hongbao;
	}

	public void setHongbao(BigDecimal hongbao) {
		this.hongbao = hongbao;
	}

	 
	
}
