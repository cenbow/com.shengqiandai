package cn.vfunding.vfunding.biz.sina.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.service.IUnbindingBankCardSinaService;
import cn.vfunding.vfunding.biz.sina.vo.returns.BaseSinaReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.UnbindingBankCardSendVO;

import com.alibaba.fastjson.JSON;
@Service("unbindingBankCardSinaService")
public class UnbindingBankCardSinaServiceImpl implements
		IUnbindingBankCardSinaService {
	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("sinaSendActionLog");
	@Autowired
	private ISinaSendService sinaSendService;
	@Autowired
	private ISinaSendLogService sinaSendLogService;
	@Autowired
	private ISinaDicService sinaDicService;
	@Autowired
	private ISinaCardService sinaCardService;
	@Override
	public String doUnbindingBank(UnbindingBankCardSendVO ubcs) throws Exception {
		logger.info("*****[sina "+ubcs.getIdentity_id()+" 解绑银行卡 开始]");
		String result = "";
		logger.info("接口请求");
		BaseSinaReturnVO bsr = null;
		try {
			bsr =  sinaSendService.sinaSendMgs(ubcs, BaseSinaReturnVO.class);
		} catch (Exception e) {
			logger.info("#####[sina "+ubcs.getIdentity_id()+" 解绑银行卡 失败内容]:["+e.getMessage()+"]");
			sinaSendLogService.insertSinaLog(ubcs.getIdentity_id(), 0, 0, SinaMemberParmUtil.interfaceName.unbinding_bank_card+"|解绑银行卡", ubcs, bsr);
			logger.info("*****[sina "+ubcs.getIdentity_id()+" 解绑银行卡 结束]");
			throw e;
		}
		logger.info("新浪接口请求完成");
		if(bsr.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)){
			result = SinaMemberParmUtil.success;
			SinaCard sc = new SinaCard();
			sc.setStatus("1");
			sc.setSinaCard(ubcs.getCard_id());
			sinaCardService.updateByPrimaryKeySelective(sc);
			sinaSendLogService.insertSinaLog(ubcs.getIdentity_id(), 2, 0, SinaMemberParmUtil.interfaceName.unbinding_bank_card+"|解绑银行卡", ubcs, bsr);
		}else{
			result = bsr.getResponse_message();
			sinaSendLogService.insertSinaLog(ubcs.getIdentity_id(), 0, 0, SinaMemberParmUtil.interfaceName.unbinding_bank_card+"|解绑银行卡", ubcs, bsr);
			logger.info("#####[sina "+ubcs.getIdentity_id()+" 解绑银行卡 失败内容]:["+result+"]");
		}
		logger.info("*****[sina "+ubcs.getIdentity_id()+" 解绑银行卡 结束]");
		return result;
	}

}
