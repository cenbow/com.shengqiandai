package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class MessageVO extends BaseVO{

	private Integer id;
	
	private String msg;
	
	private String msg2;

	
	public String getMsg2() {
		return msg2;
	}

	public void setMsg2(String msg2) {
		this.msg2 = msg2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
