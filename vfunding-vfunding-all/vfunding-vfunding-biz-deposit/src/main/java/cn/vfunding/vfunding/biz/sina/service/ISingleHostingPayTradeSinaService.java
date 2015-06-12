package cn.vfunding.vfunding.biz.sina.service;

import cn.vfunding.vfunding.biz.sina.vo.sends.CreateSingleHostingPayTradeSendVO;

/**
 * 托管代付
 * @author jianwei
 * @date 2015年1月16日
 */
public interface ISingleHostingPayTradeSinaService {
	
	/**
	 * 托管代付
	 * @param sendVO
	 * @param actionName
	 * @return
	 * @throws Exception
	 */
	public String sinaSend(CreateSingleHostingPayTradeSendVO sendVO,String actionName) throws Exception;
	
	/**
	 * 托管代付 logId
	 * @param sendVO
	 * @param logId
	 * @param actionName
	 * @return
	 * @throws Exception
	 */
	public String sinaSend(CreateSingleHostingPayTradeSendVO sendVO,String logId,String actionName) throws Exception;
}
