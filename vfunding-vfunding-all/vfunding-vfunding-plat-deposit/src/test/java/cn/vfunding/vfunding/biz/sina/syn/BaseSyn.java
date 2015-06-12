package cn.vfunding.vfunding.biz.sina.syn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.user.model.User;

/**
 * 数据同步父类
 * @author wang.zeyan
 * @date 2015年2月2日
 * @version 1.0
 */
public abstract class BaseSyn {
	
	protected List<BaseSyn> list = new ArrayList<BaseSyn>();
	
	protected ISinaMemberManagerService sinaMemberManagerService;
	
	protected User user;
	
	private Object resultSet;
	
	private BaseSyn(){}
	
	public BaseSyn(User user){
		this.user = user;
	}
	
	public void setSuccessor(BaseSyn ... syns){
		for(BaseSyn syn : syns)
			list.add(syn);
	}
	
	public abstract void execute();
	
	protected abstract boolean isOK(String responseCode);
	
	/**
	 * 
	 * wang.zeyan 2015年2月3日
	 * @return
	 */
	public Map<String , SynResult> doHandler(){
		
		Map<String,SynResult> propertyMap = new HashMap<String , SynResult>();
		execute();
		propertyMap.put(operationName() , new SynResult(isOK() , result()));
		
		if(isOK()) for(BaseSyn syn : list) propertyMap.putAll(syn.doHandler());
		
		return propertyMap;
	}
	
	/**
	 * 根据结果集是否继续执行
	 * wang.zeyan 2015年2月2日
	 * @return
	 */
	public boolean isOK(){
		String responseCode = getResponseCode();
		if(responseCode != null &&  !"".equals(responseCode))
			return isOK(responseCode);
		return false;
	}
	
	protected String getResponseCode(){
		String responseCode = null;
		if(resultSet != null)
			responseCode = resultSet.toString();
		return responseCode;
	}

	/**
	 * 操作名称
	 * wang.zeyan 2015年2月2日
	 * @return
	 */
	public abstract String operationName();
	
	public String result(){

		return getResultSet() == null ? null : getResultSet().toString();
	}
	
	public String result(Exception e){

		return "Exception:"+e.getMessage();
	}
	
	public BaseSyn(ISinaMemberManagerService sinaMemberManagerService, User user) {
		super();
		this.sinaMemberManagerService = sinaMemberManagerService;
		this.user = user;
	}

	public ISinaMemberManagerService getSinaMemberManagerService() {
		return sinaMemberManagerService;
	}

	public void setSinaMemberManagerService(
			ISinaMemberManagerService sinaMemberManagerService) {
		this.sinaMemberManagerService = sinaMemberManagerService;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Object getResultSet() {
		return resultSet;
	}

	public void setResultSet(Object resultSet) {
		this.resultSet = resultSet;
	}
	
	
}
