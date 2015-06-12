package cn.vfunding.vfunding.biz.sina.service;

import javax.servlet.http.HttpServletRequest;

import cn.vfunding.vfunding.biz.sina.vo.sends.BindingBankCardAdvanceSendVO;

/**
 * 绑定银行卡推进
 * @author jianwei
 * @date 2015年1月21日
 */
public interface IBindingBankCardAdvanceSinaService {
	/**
	 * 绑定银行卡推进短信验证
	 * @return
	 * @throws Exception
	 */
	public String doBindingBankAdvance(BindingBankCardAdvanceSendVO bbcas,String scId) throws Exception;
}
