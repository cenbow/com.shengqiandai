package cn.vfunding.vfunding.biz.sina.service;

import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;

/**
 * 托管待收
 * @author jianwei
 * @date 2015年1月20日
 */
public interface IHostingCollectTradeSinaService {
	/**
	 * 代收发送sina请求
	 * @param vo
	 * @return
	 */
	public String sinaSend(CreateHostingCollectTradeSendVO sendVO,String actionName) throws Exception;
	
	/**
	 * 代收发送sina请求 logId
	 * @param sendVO
	 * @param logId
	 * @param actionName
	 * @return
	 * @throws Exception
	 */
	public String sinaSend(CreateHostingCollectTradeSendVO sendVO,String logId,String actionName) throws Exception;
	
}
