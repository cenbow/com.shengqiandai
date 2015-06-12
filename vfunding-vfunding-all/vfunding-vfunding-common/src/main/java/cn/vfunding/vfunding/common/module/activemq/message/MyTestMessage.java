package cn.vfunding.vfunding.common.module.activemq.message;

import java.util.Map;

@SuppressWarnings("serial")
public class MyTestMessage extends BaseMessage {

	public MyTestMessage(){
		super();
	}
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
