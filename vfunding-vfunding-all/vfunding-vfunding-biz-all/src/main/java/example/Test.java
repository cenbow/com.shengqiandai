package example;

import java.rmi.RemoteException;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.注册序列号
		try {
			int i = SingletonClient.getClient().registEx("073989");
			if(i==0){
				System.out.println("注册序列号成功！");
			}else{
				System.out.println("未知异常:"+i);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		//2.注册企业信息
		try {

			int a = SingletonClient.getClient().registDetailInfo("生钱袋", "hyc", "01058750425",
					"15000395573", "205140040@qq.com", "01058750500", "浦东祖冲之路", "056900");
			
			//System.out.println("testRegistDetailInfo:" + a);
			if(a==0){
				System.out.println("注册企业信息成功！");
			}else{
				System.out.println("未知异常:"+a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//3.查询余额
		try {
			double a = SingletonClient.getClient().getBalance();
			System.out.println("序列号余额:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//4.查询每条短信的费用
		try {
			double a = SingletonClient.getClient().getEachFee();
			System.out.println("发送每条短信的费用:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//5.发送短信
		try {
			int i = SingletonClient.getClient().sendSMS(new String[] { "15000395573" }, "内容", "",5);// 带扩展码
			//System.out.println("testSendSMS=====" + i);
			if(i==0){
				System.out.println("发送短信成功！");
			}else if(i==-127){
				System.out.println("发送失败,计费失败0余额");
			}
			
			else{
				System.out.println("未知异常:"+i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//6.测试充值
		try {
			int a = SingletonClient.getClient().chargeUp("充值卡卡号", "密码");
			if(a==0){
				System.out.println("充值成功！");
			}else{
				System.out.println("未知异常:"+a);
			}
			//System.out.println("testChargeUp:" + a);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
