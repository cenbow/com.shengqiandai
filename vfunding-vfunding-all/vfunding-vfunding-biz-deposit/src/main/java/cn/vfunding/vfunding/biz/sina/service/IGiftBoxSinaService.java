package cn.vfunding.vfunding.biz.sina.service;

import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.vfunding.biz.message.model.GiftboxMessage;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateHostingCollectTradeSendVO;

/**
 * 托管待收
 * @author jianwei
 * @date 2015年1月20日
 */
public interface IGiftBoxSinaService {
	
	/**
	 * 红包使用(代收)发送sina请求
	 * @param gm
	 * @return
	 * @author louchen 2015-02-04
	 */
	@NeedLock("/locks/giftHongbaoUseReceiveSinaSend")
	public String GiftHongbaoUseReceiveSinaSend(GiftboxMessage gm) throws Exception;
	
	/**
	 * 红包使用(代付)发送sina请求
	 * @param gm
	 * @return
	 * @author louchen 2015-02-04
	 */
	@NeedLock("/locks/giftHongbaoUsePaySinaSend")
	public String GiftHongbaoUsePaySinaSend(GiftboxMessage gm) throws Exception;
	
}
