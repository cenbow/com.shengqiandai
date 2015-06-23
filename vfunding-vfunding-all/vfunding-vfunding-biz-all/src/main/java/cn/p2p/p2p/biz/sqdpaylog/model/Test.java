package cn.p2p.p2p.biz.sqdpaylog.model;

import java.util.Date;

import cn.vfunding.common.framework.utils.beans.DateUtil;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Date date=new Date();
		//生成当前时间long类型
		long a=DateUtil.getLongTime();
		System.out.println("addtimeLong:"+a);
		System.out.println(DateUtil.getStringDateByLongDate(a));
	}

}
