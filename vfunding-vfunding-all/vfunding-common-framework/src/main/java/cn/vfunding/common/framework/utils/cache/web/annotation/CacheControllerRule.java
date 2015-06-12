package cn.vfunding.common.framework.utils.cache.web.annotation;

public class CacheControllerRule {
	
	private String prefixGroup;
	private int validPeriod;
	private int scope;
	public static int paraseValidPeriod(String sValidPeriod){
		int repeats = Integer.parseInt(sValidPeriod.substring(0, sValidPeriod.length()-1));
		if(sValidPeriod.endsWith("m")){
			return repeats*60;
		}else if(sValidPeriod.endsWith("s")){
			return repeats;
		}else if(sValidPeriod.endsWith("h")){
			return repeats*3600;
		}else if(sValidPeriod.endsWith("d")){
			return repeats*3600*24;
		}else{
			throw new RuntimeException("unsupport " + sValidPeriod);
		}
	}
	
	public static CacheControllerRule parse(CacheController cacheController){
		CacheControllerRule cacheControllerParser= new CacheControllerRule();
		String sValidPeriod = cacheController.validPeriod();
		int validPeriod = paraseValidPeriod(sValidPeriod);
		cacheControllerParser.setValidPeriod(validPeriod);
		cacheControllerParser.setScope(cacheController.scope());
		return cacheControllerParser;
	}
	
	public String getPrefixGroup() {
		return prefixGroup;
	}
	public void setPrefixGroup(String prefixGroup) {
		this.prefixGroup = prefixGroup;
	}
	public int getValidPeriod() {
		return validPeriod;
	}
	public void setValidPeriod(int validPeriod) {
		this.validPeriod = validPeriod;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}
	
	
}
