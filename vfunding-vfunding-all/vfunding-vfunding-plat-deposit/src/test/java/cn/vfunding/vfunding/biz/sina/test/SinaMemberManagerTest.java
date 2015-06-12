//package cn.vfunding.vfunding.biz.sina.test;
//
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.fastjson.JSON;
//
//import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
//import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
//import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
//import cn.vfunding.vfunding.biz.sina.vo.returns.QueryVerifyReturnVO;
//import cn.vfunding.vfunding.biz.sina.vo.sends.CreateActivateMemberSendVO;
//import cn.vfunding.vfunding.biz.sina.vo.sends.QueryVerifySendVO;
//import cn.vfunding.vfunding.biz.sina.vo.sends.SetRealNameSendVO;
//
///**
// * 
// * @author wang.zeyan
// * @date 2015年1月16日
// * @version 1.0
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:spring-context.xml")
//public class SinaMemberManagerTest {
//
//	@Autowired
//	ISinaMemberManagerService sinaMemberManagerService;
//	
//	
//	private String userId = "9057";
//	@Test
//	public void test(){
//		
//		try {
//			//createActivateMember();
//			//setRealNameByUserId();
//			//setRealName();
//			//mailBindVerify();
//			//mobileBindVerify();
//			//mailUnBindVerify();
//			//mobileUnBindVerify();
//			queryVerify();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	public void createActivateMember() throws Exception{
//		CreateActivateMemberSendVO vo = new CreateActivateMemberSendVO();
//		//设置用户标识信息
//		vo.setIdentity_id(14189+"");
//		//设置用户标识类型
//		vo.setIdentity_type(SinaMemberParmUtil.IdentityType.UID.toString());
//		//设置会员类型
//		vo.setMember_type(SinaMemberParmUtil.MemberType.Personal.toString());
//		
//		String result = sinaMemberManagerService.createActivateMember(vo);
//		System.out.println(result);
//		
//	}
//	
//	//@Test
//	public void setRealNameByUserId() throws Exception{
//		String result = sinaMemberManagerService.setRealName(userId);
//		System.out.println(result);
//	}
//	//@Test
//	public void setRealName() throws Exception{
//		SetRealNameSendVO vo = new SetRealNameSendVO();
//		//设置用户标识信息
//		vo.setIdentity_id(userId);
//		//设置用户标识类型
//		vo.setIdentity_type(SinaMemberParmUtil.IdentityType.UID.toString());
//		//设置真实姓名
//		vo.setReal_name("姚波");   	
//		//设置证件类型
//		vo.setCert_type(SinaMemberParmUtil.CertType.IC);
//		//设置证件号码
//		vo.setCert_no("370681197606174010");
//		//是否认证
//		vo.setNeed_confirm(SinaMemberParmUtil.Y);
//		String result = sinaMemberManagerService.setRealName(vo);
//		System.out.println(result);
//	}
//	
//	public void mobileBindVerify() throws Exception{
//		String result = sinaMemberManagerService.mobileBindVerify(userId);
//		System.out.println(result);
//	}
//	
//	public void mailBindVerify() throws Exception{
//		String result = sinaMemberManagerService.mailBindVerify(userId);
//		System.out.println(result);
//	}
//	
//	public void mobileUnBindVerify() throws Exception{
//		String result = sinaMemberManagerService.mobileUnBindVerify(userId);
//		System.out.println(result);
//	}
//	
//	public void mailUnBindVerify() throws Exception{
//		String result = sinaMemberManagerService.mailUnBindVerify(userId);
//		System.out.println(result);
//	}
//	
//	public void queryVerify() throws Exception {
//		QueryVerifySendVO vo = new QueryVerifySendVO();
//		vo.setIdentity_id(userId);
//		vo.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
//		vo.setVerify_type(SinaMemberParmUtil.VerifyType.MOBILE);
//		QueryVerifyReturnVO result = sinaMemberManagerService.queryVerify(vo);
//		System.out.println(JSON.toJSONString(result));
//	}
//}
