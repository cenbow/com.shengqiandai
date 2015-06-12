package cn.vfunding.vfunding.prd.bms.sinadeposit.controller;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.IQuerySinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendActionLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryVerifyReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryVerifySendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.UnbindingVerifySendVO;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.prd.bms.sinadeposit.syn.MemberCheck;
import cn.vfunding.vfunding.prd.bms.sinadeposit.syn.Result;
import cn.vfunding.vfunding.prd.bms.sinadeposit.syn.Vfunding2Sina;

@Controller
@RequestMapping(value = "/syn")
public class VfundingUserToSinaMemberControll {
	
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");

	@Autowired
	ISinaMemberManagerService sinaMemberManagerService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IQuerySinaService querySinaService;
	
	@Autowired
	ISinaSendActionLogService sinaSendActionLogService;
	@Autowired
	ISinaSendService sinaSendService;
	
	
	static boolean isbreak = false;
	static int threadPoolSize = 1;
	//static int threadMax = 20;
	//static ExecutorService es = Executors.newFixedThreadPool(threadPoolSize);
	
	@RequestMapping(value = "/selectUsersByBalanceOrRed")
	public void selectUsersByBalanceOrRed(){
		ExecutorService es = Executors.newFixedThreadPool(threadPoolSize);
		List<MemberCheck> callableList=new ArrayList<MemberCheck>();
		for (int i = 0; i < threadPoolSize; i++) {
			MemberCheck v = new MemberCheck(sinaMemberManagerService, userService, querySinaService ,sinaSendActionLogService);
			callableList.add(v);
		}
		List<Future<Result>> futureList=new ArrayList<Future<Result>>();
		for (int i = 0; i < callableList.size(); i++) {
			Future<Result> f = es.submit(callableList.get(i));
			futureList.add(f);
		}
		try {
			for (int i = 0; i < futureList.size(); i++) {
				Result result = futureList.get(i).get();
				if(result != null){
					logger.info((result.toString()));
					if(result.is_break())
						isbreak = true;
				}else{
					isbreak = true;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally{
			if(!isbreak){
				selectUsersByBalanceOrRed();
			}else{
				// //线程池不再接收新的任务，但是会继续执行完工作队列中现有的任务
				es.shutdown();
				//等待关闭线程池，每次等待的超时时间为60秒
	            while(!es.isTerminated()){
	            	try {
						es.awaitTermination(60,TimeUnit.SECONDS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	            }
			}
		}
	}
	
//	static boolean isbreak = false;
//	static int threadPoolSize = 1;
//	static int threadMax = 1;
//	static ExecutorService es = Executors.newFixedThreadPool(threadPoolSize);
//	
//	@RequestMapping(value = "/member")
//	@ResponseBody
//	public Json syn(){
//		
//		Json json = new Json();
//		
//		List<Vfunding2Sina> callableList=new ArrayList<Vfunding2Sina>();
//		for (int i = 0; i < threadMax; i++) {
//			Vfunding2Sina v = new Vfunding2Sina(sinaMemberManagerService, userService);
//			callableList.add(v);
//		}
//		List<Future<Result>> futureList=new ArrayList<Future<Result>>();
//		for (int i = 0; i < callableList.size(); i++) {
//			Future<Result> f = es.submit(callableList.get(i));
//			futureList.add(f);
//		}
//		try {
//			for (int i = 0; i < futureList.size(); i++) {
//				Result result = futureList.get(i).get();
//				if(result != null){
//					logger.info((result.toString()));
//					if(result.is_break())
//						isbreak = true;
//				}else{
//					isbreak = true;
//				}
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			e.printStackTrace();
//		} finally{
//			//es.shutdown();
//			if(!isbreak){
//				syn();
//			}else{
//				// //线程池不再接收新的任务，但是会继续执行完工作队列中现有的任务
//				es.shutdown();
//				//等待关闭线程池，每次等待的超时时间为60秒
//	            while(!es.isTerminated()){
//	            	try {
//						es.awaitTermination(60,TimeUnit.SECONDS);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//	            }
//	            json.setMsg("运行结束！");
//			}
//		}
//		return json;
//	}
	
	@RequestMapping(value = "/setRealName")
	@ResponseBody
	public Json setRealName(String userId){
		Json json = new Json();
		String result = null;
		try {
			result = sinaMemberManagerService.setRealName(userId);
		} catch (Exception e) {
			e.printStackTrace();
			result=e.getMessage();
		}
		json.setMsg(result);
		return json;
	}
	
	@RequestMapping(value = "/mobileBindVerify")
	@ResponseBody
	public Json mobileBindVerify(String userId){
		Json json = new Json();
		String result = null;
		try {
			result = sinaMemberManagerService.mobileBindVerify(userId);
		} catch (Exception e) {
			e.printStackTrace();
			result=e.getMessage();
		}
		json.setMsg(result);
		return json;
	}
	
	@RequestMapping(value = "/createActivateMember")
	@ResponseBody
	public Json createActivateMember(String userId){
		Json json = new Json();
		String result = null;
		try {
			result = sinaMemberManagerService.createActivateMember(userId);
		} catch (Exception e) {
			e.printStackTrace();
			result=e.getMessage();
		}
		json.setMsg(result);
		return json;
	}
	
	
	@RequestMapping(value = "/unbindingVerify")
	@ResponseBody
	public Json unbindingVerify(String userId){
		Json json = new Json();
		String result = null;
		try {
			UnbindingVerifySendVO vo = new UnbindingVerifySendVO();
			vo.setIdentity_id(userId);
			vo.setIdentity_type("UID");
			vo.setVerify_type("MOBILE");
			result = sinaMemberManagerService.unbindingVerify(vo);
		} catch (Exception e) {
			e.printStackTrace();
			result=e.getMessage();
		}
		json.setMsg(result);
		return json;
	}
	
	@RequestMapping(value = "/searchMobileDeatil")
	@ResponseBody
	public Json searchMobileDeatil(String userId) throws Exception{
		Json j = new Json();
		QueryVerifySendVO qv = new QueryVerifySendVO();
		qv.setIdentity_id(userId);
		qv.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
		qv.setVerify_type(SinaMemberParmUtil.VerifyType.MOBILE);
		qv.setIs_mask("N");
		QueryVerifyReturnVO qvr = sinaSendService.sinaSendMgs(qv, QueryVerifyReturnVO.class);
		j.setObj(qvr);
		j.setSuccess(true);
		return j;
	}
	
	
}
