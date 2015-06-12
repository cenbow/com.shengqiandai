package cn.vfunding.vfunding.common.jmssender;

import java.io.Serializable;

public class SenderObjBuilder {

	public final static String JMS_SYSTEM = "system";

	public final static String JMS_USERACTION = "useraction";

	public static JmsSenderObj buildJmsSenderObj(String content,
			Class<? extends Serializable> messageCls,String destinationName) {
		JmsSenderObj obj = new JmsSenderObj();
		obj.setJmsDestinationName(destinationName);
		obj.setInstanceJSON(content);
		obj.setMessageCls(messageCls);
		return obj;
	}
	
}
