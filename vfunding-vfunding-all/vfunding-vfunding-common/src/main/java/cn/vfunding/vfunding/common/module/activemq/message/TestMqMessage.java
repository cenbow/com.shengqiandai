package cn.vfunding.vfunding.common.module.activemq.message;


@SuppressWarnings("serial")
public class TestMqMessage extends BaseMessage {

	public TestMqMessage(){
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
