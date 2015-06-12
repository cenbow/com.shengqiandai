package cn.vfunding.common.framework.utils.http;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public class RestInvokerFactory implements InitializingBean{
	
	private String baseURL;

	private List<RestInvoker> restInvokerList = new ArrayList<RestInvoker>();
	
	
//	private String strategy;
	int i= -1 ;
	public synchronized RestInvoker getRestInvoker(){
		if( (i+1) == restInvokerList.size() ){
			i = 0 ;
		}else{
			i++;
		}
		return restInvokerList.get(i);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		if(baseURL == null){
			throw new RuntimeException("baseURL property must be set");
		}
		String[] urls = baseURL.split(",");
		for(String url : urls){
			i++;
			RestInvoker restInvoker  = new RestInvoker ();
			restInvoker.setBaseURL(url);
			restInvokerList.add(restInvoker);
		}
	}
	
	
	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	
}
