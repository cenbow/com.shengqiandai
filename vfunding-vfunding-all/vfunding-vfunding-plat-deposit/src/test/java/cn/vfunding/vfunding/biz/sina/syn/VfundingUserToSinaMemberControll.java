package cn.vfunding.vfunding.biz.sina.syn;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.user.service.IUserService;

@Controller
@RequestMapping(value = "/syn")
public class VfundingUserToSinaMemberControll {

	@Autowired
	ISinaMemberManagerService sinaMemberManagerService;
	
	@Autowired
	IUserService userService;
	
	static boolean isbreak = false;
	static int threadPoolSize = 10;
	static int threadMax = 20;
	static ExecutorService es = Executors.newFixedThreadPool(threadPoolSize);
	
	@RequestMapping(value = "/member")
	public void syn(){
		List<Vfunding2Sina> callableList=new ArrayList<Vfunding2Sina>();
		for (int i = 0; i < threadMax; i++) {
			Vfunding2Sina v = new Vfunding2Sina(sinaMemberManagerService, userService);
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
				
				//isbreak = true;
				if(result != null){
					System.out.println(result.toString());
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
			//es.shutdown();
			if(!isbreak){
				syn();
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
	
}
