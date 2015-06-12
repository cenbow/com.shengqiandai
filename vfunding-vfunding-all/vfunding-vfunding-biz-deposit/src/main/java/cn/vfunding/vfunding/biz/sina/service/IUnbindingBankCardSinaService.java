package cn.vfunding.vfunding.biz.sina.service;

import cn.vfunding.vfunding.biz.sina.vo.sends.UnbindingBankCardSendVO;

public interface IUnbindingBankCardSinaService {
	/**
	 * 解绑银行卡
	 * @param ubcs
	 * @return
	 * @author jianwei
	 */
	public String doUnbindingBank(UnbindingBankCardSendVO ubcs) throws Exception;
}
