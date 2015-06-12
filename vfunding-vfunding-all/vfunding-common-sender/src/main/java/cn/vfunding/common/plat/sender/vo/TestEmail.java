package cn.vfunding.common.plat.sender.vo;

import cn.vfunding.common.plat.sender.properties.util.EmailTempletPropertiesUtil;

public class TestEmail {

	
	public static void main(String[] args) {
		String a=EmailTempletPropertiesUtil.getValue("email.templet.header");
		System.out.println(a);
	}
}
