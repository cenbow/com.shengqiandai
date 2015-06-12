package cn.vfunding.vfunding.biz.sina.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.vfunding.vfunding.biz.sina.vo.sends.BindingBankCardSendVO;
/**
 * 绑定银行卡
 * @author jianwei
 * @date 2015年1月20日
 */
public interface IBindingBankCardSinaService {
	public Map<String, Object> doBindingBank(BindingBankCardSendVO bbcs,String accountNo,String bankPhone) throws Exception;
}
