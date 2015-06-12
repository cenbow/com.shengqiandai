package cn.vfunding.vfunding.biz.sina.service;

public interface ISinaBonusService {

	 /**
     * 创建新浪存钱罐结算数据
     * @return 
     * @author louchen 2015-03-25
     */
    Boolean loadCreateBaseSinaSettlementData();
    
    /**
     * 同步新浪存钱罐结算数据到微积金账户余额
     * @return
     * @author louchen 2015-03-25
     */
    Boolean loadSyncSinaSettlementData();
	
    /**
     * 同步所有用户的存钱罐收益
     * @return
     * @author louchen 2015-03-26
     */
	Boolean executeAll();
}
