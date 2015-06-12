package cn.vfunding.vfunding.prd.bms.sinadeposit.syn;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.spy.memcached.ops.GetOperation;
import cn.vfunding.vfunding.biz.sina.model.SinaSendActionLog;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendActionLogService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.User2Sinamember;

/**
 * 数据同步父类
 * @author wang.zeyan
 * @date 2015年2月2日
 * @version 1.0
 */
public abstract class BaseSyn {
	
	public final static String SET_REALNAME_STATUS_EXCEPTION = "vfunding setRealName status=0";
	
	protected List<BaseSyn> list = new ArrayList<BaseSyn>();
	
	protected ISinaMemberManagerService sinaMemberManagerService;
	
	protected ISinaSendActionLogService sinaSendActionLogService;
	
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
		try {
			execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			insertSinaActionLog();
		}
		propertyMap.put(operationName() , new SynResult(isOK() , result()));
		
		if(isOK()) for(BaseSyn syn : list) propertyMap.putAll(syn.doHandler());
		
		return propertyMap;
	}
	
	protected void insertSinaActionLog(){
		try {
			this.sinaSendActionLogService.insertSelective(createSinaSendActionLog());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	protected SinaSendActionLog createSinaSendActionLog(){
		SinaSendActionLog ssal = new SinaSendActionLog();
		ssal.setUserId(user.getUserId());
		ssal.setAction(this.operationName());
		ssal.setResponsemsg(getResponseCode());
		ssal.setAddtime(new Date());
		return ssal;
	}

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
