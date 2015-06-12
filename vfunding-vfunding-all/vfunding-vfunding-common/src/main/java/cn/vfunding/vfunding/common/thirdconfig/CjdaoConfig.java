package cn.vfunding.vfunding.common.thirdconfig;

import cn.vfunding.common.framework.utils.beans.WorkerPropertiesUtil;

/**
 * 财经道配置类
 * @author louchen 2015-03-04
 *
 */
public class CjdaoConfig {
	
	private String key;
	
	private String companyid;
	
	private String borrowListUrl;
	
	private String tenderUrl;
	
	private String regUrl;
	
	private String CjDaoActvityOneStart;
	
	private String CjDaoActvityOneEnd;
	
	private static CjdaoConfig instance = null;

	private CjdaoConfig(){}
	
	public static CjdaoConfig getInstance() {
		if(instance==null){
			syncInit();
		}		
		return instance;
	}

	private synchronized static void syncInit(){
		if(instance==null){
			String environment = WorkerPropertiesUtil.getValue("CjDao.environment");
			instance = new CjdaoConfig();
			if(environment.equals("DEBUG")){
				instance.key="1234";
				instance.companyid="2073";
				instance.borrowListUrl="http://test.cjdao.com/p2p/saveproduct";
				instance.tenderUrl="http://test.cjdao.com/productbuy/saveproduct";
				instance.regUrl="http://test.cjdao.com/productbuy/reginfo";
				instance.CjDaoActvityOneStart="2015-04-21 00:00:00";
				instance.CjDaoActvityOneEnd="2015-05-28 23:59:59";
			}else if(environment.equals("FORMAL")){
				//正式环境配置
				instance.key="cjdao_third1122*#";
				instance.companyid="2057";
				instance.borrowListUrl="http://service.cjdao.com/p2p/saveproduct";
				instance.tenderUrl="http://service.cjdao.com/productbuy/saveproduct";
				instance.regUrl="http://service.cjdao.com/productbuy/reginfo";
				instance.CjDaoActvityOneStart="2015-04-28 00:00:00";
				instance.CjDaoActvityOneEnd="2015-05-28 23:59:59";
			}
		}
	}

	public String getKey() {
		return key;
	}

	public String getCompanyid() {
		return companyid;
	}

	public String getBorrowListUrl() {
		return borrowListUrl;
	}

	public String getTenderUrl() {
		return tenderUrl;
	}

	public String getRegUrl() {
		return regUrl;
	}

	public String getCjDaoActvityOneStart() {
		return CjDaoActvityOneStart;
	}

	public void setCjDaoActvityOneStart(String cjDaoActvityOneStart) {
		CjDaoActvityOneStart = cjDaoActvityOneStart;
	}

	public String getCjDaoActvityOneEnd() {
		return CjDaoActvityOneEnd;
	}

	public void setCjDaoActvityOneEnd(String cjDaoActvityOneEnd) {
		CjDaoActvityOneEnd = cjDaoActvityOneEnd;
	}	
	
	
}
