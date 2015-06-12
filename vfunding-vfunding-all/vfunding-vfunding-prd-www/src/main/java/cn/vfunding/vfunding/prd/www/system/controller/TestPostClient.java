package cn.vfunding.vfunding.prd.www.system.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.account.service.IAccountRechargeService;
import cn.vfunding.vfunding.biz.borrow.service.ITestLockService;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.test.service.IVfTestService;
import cn.vfunding.vfunding.common.module.activemq.message.TestMessage;

@Controller
@RequestMapping("/test")
public class TestPostClient {

	@Autowired
	private ITestLockService testLockService;

	@Autowired
	private IAccountRechargeService accountRechargeService;

	@Resource(name = "fileRestInvokerFactory")
	private RestInvokerFactory fileRestInvokerFactory;

	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Autowired
	private IVfTestService testService;

	@RequestMapping(value = "/testPost", method = RequestMethod.POST)
	@ResponseBody
	public String testPost(UserTest test) {
		/*
		 * List<NameValuePair> nvps=new ArrayList<NameValuePair>(); nvps.add(new
		 * BasicNameValuePair("userName", test.getUserName())); nvps.add(new
		 * BasicNameValuePair("password", test.getPassword()));
		 */
		this.fileRestInvokerFactory.getRestInvoker().post("/testPost", test);
		return "OK";
	}

	@RequestMapping(value = "/testMQ", method = RequestMethod.GET)
	@ResponseBody
	public String testMQ() throws Exception {
//		for (int i = 0; i < 500; i++) {
//			TestMessage ms = new TestMessage();
//			ms.setName("liuyijun" + i);
//			long start = System.currentTimeMillis();
//			// this.sender.sendAsynchronousMessage(ms);
//			this.jmsSenderUtil.asynSendSystemJms(ms);
//			long end = System.currentTimeMillis();
//			System.out.println("time is:" + (end - start));
//			Thread.sleep(1000);
//		}

		return "testSendTempletEmail";
		
	}

}
