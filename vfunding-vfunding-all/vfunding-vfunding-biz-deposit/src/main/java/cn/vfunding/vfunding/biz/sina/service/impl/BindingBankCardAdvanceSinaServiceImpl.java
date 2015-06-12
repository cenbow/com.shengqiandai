package cn.vfunding.vfunding.biz.sina.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.service.IBindingBankCardAdvanceSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.vo.returns.BindingBankCardAdvanceReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingBankCardAdvanceSendVO;

import com.alibaba.fastjson.JSON;

@Service("bindingBankCardAdvanceSinaService")
public class BindingBankCardAdvanceSinaServiceImpl implements IBindingBankCardAdvanceSinaService {
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
	public String doBindingBankAdvance(BindingBankCardAdvanceSendVO bbcas,String scId) throws Exception {
		logger.info("*****[sina "+scId+" 绑卡推进 开始]");
		String result = "";
		logger.info("发送新浪接口请求");
		SinaCard sc = null;
		sc = sinaCardService.selectByPrimaryKey(Integer.valueOf(scId));
		BindingBankCardAdvanceReturnVO bbcar = null;
		try {
			bbcar = sinaSendService.sinaSendMgs(bbcas,
					BindingBankCardAdvanceReturnVO.class);
		} catch (Exception e) {
			logger.info("#####[sina "+scId+" 绑卡推进 失败内容]:["+e.getMessage()+"]");
			sinaSendLogService.insertSinaLog(sc.getUserId().toString(), 0, 0,
					SinaMemberParmUtil.interfaceName.binding_bank_card_advance + "|绑定银行卡推进", bbcas,
					bbcar);
			logger.info("*****[sina "+scId+" 绑卡推进 结束]");
			throw e;
		}
		logger.info("新浪接口请求完成");
		if (bbcar.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
			result = SinaMemberParmUtil.success;
			// 更新绑定的银行卡信息
			if(sc.getBank().equals("GDB") || sc.getBank().equals("CMBC")||sc.getBank().equals("CMB")||sc.getBank().equals("HXB")||sc.getBank().equals("SZPAB")||sc.getBank().equals("SPDB")){
				sc.setIsVerified("Y");
			}else{
				sc.setIsVerified(bbcar.getIs_verified());
			}
			sc.setSinaCard(bbcar.getCard_id());
			sinaCardService.updateByPrimaryKeySelective(sc);
			sinaSendLogService.insertSinaLog(sc.getUserId().toString(), 2, 0,
					SinaMemberParmUtil.interfaceName.binding_bank_card_advance + "|绑定银行卡推进", bbcas,
					bbcar);
		} else {
			result = bbcar.getResponse_message();
			sinaSendLogService.insertSinaLog(sc.getUserId().toString(), 0, 0,
					SinaMemberParmUtil.interfaceName.binding_bank_card_advance + "|绑定银行卡推进", bbcas,
					bbcar);
			logger.info("#####[sina "+scId+" 绑卡推进 失败内容]:["+result+"]");
		}
		logger.info("*****[sina "+scId+" 绑卡推进 结束]");
		return result;
	}

}
