package cn.vfunding.vfunding.biz.sina.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;
import cn.vfunding.vfunding.biz.sina.service.IBindingBankCardSinaService;
import cn.vfunding.vfunding.biz.sina.service.ISinaCardService;
import cn.vfunding.vfunding.biz.sina.service.ISinaDicService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.vo.returns.BaseSinaReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.BindingBankCardReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingBankCardSendVO;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

@Service("bindingBankCardSinaService")
public class BindingBankCardSinaServiceImpl implements
		IBindingBankCardSinaService {
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
	public Map<String, Object> doBindingBank(BindingBankCardSendVO bbcs,
			String accountNo, String bankPhone) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("*****[sina " + bbcs.getIdentity_id() + " 绑卡 开始]");
		logger.info("新浪接口请求开始");
		BindingBankCardReturnVO bbcr = null;
		try {
			bbcr = sinaSendService.sinaSendMgs(bbcs,
					BindingBankCardReturnVO.class);
			logger.info("新浪接口请求完成");
			if(bbcr.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.BANK_INFO_VERIFY_FAILED)){
				Integer scid = checkBindingBank(accountNo,Integer.parseInt(bbcs.getIdentity_id()));
				if(scid != 0){
					SinaCard sc = sinaCardService.selectByPrimaryKey(scid);
					if(!StringUtils.isEmpty(sc.getIsVerified())){
						bbcs.setPhone_no(null);
						bbcs.setVerify_mode(null);
						bbcs.setRequest_no(new Date().getTime()+""+bbcs.getIdentity_id());
						BindingBankCardReturnVO bsr = sinaSendService.sinaSendMgs(bbcs,
								BindingBankCardReturnVO.class);
						if(bsr.getResponse_code().equals(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)){
							logger.info("更新前sc:"+JSON.toJSONString(sc));
							sc.setSinaCard(bsr.getCard_id());
							sinaCardService.updateByPrimaryKeySelective(sc);
							logger.info("更新后sc:"+JSON.toJSONString(sc));
						}
						logger.info("bsr:"+JSON.toJSONString(bsr));
					}
				}
			}
			if (bbcr.getResponse_code().equals(
					SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS)) {
				map.put("result", SinaMemberParmUtil.success);
				// 保存绑定的银行卡信息
				Integer scid = checkBindingBank(accountNo,Integer.parseInt(bbcs.getIdentity_id()));
				if (scid == 0) {
					SinaCard sc = new SinaCard();
					sc.setCardId(accountNo);
					sc.setAddTime(new Date());
					sc.setIsVerified(bbcr.getIs_verified());
					sc.setSinaCard(bbcr.getCard_id());
					sc.setStatus("0");
					sc.setUserId(Integer.parseInt(bbcs.getIdentity_id()));
					sc.setProvince(bbcs.getProvince());
					sc.setCity(bbcs.getCity());
					sc.setBankPhone(bankPhone);
					sc.setBank(bbcs.getBank_code());
					sinaCardService.insertSelective(sc);
					scid = sc.getScId();
				} else {
					SinaCard sc = sinaCardService.selectByPrimaryKey(scid);
					sc.setCardId(accountNo);
					sc.setAddTime(new Date());
					sc.setIsVerified(bbcr.getIs_verified());
					sc.setSinaCard(bbcr.getCard_id());
					sc.setStatus("0");
					sc.setUserId(Integer.parseInt(bbcs.getIdentity_id()));
					sc.setProvince(bbcs.getProvince());
					sc.setCity(bbcs.getCity());
					sc.setBankPhone(bankPhone);
					sc.setBank(bbcs.getBank_code());
					sc.setScId(scid);
					sinaCardService.updateByPrimaryKeySelective(sc);
				}
				// 保存推进内容
				if (!StringUtils.isEmpty(bbcr.getTicket())) {
					map.put("bbcr", bbcr);
					map.put("scId", scid);
				}
				sinaSendLogService.insertSinaLog(bbcs.getRequest_no(), 2, 0,
						SinaMemberParmUtil.interfaceName.binding_bank_card
								+ "|绑定银行卡", bbcs, bbcr);
			} else {
				if (bbcr.getResponse_message().equals("银行卡bin校验失败")) {
					map.put("result", "银行卡格式不正确");
				} else {
					map.put("result", bbcr.getResponse_message());
				}
				sinaSendLogService.insertSinaLog(bbcs.getRequest_no(), 0, 0,
						SinaMemberParmUtil.interfaceName.binding_bank_card
								+ "|绑定银行卡", bbcs, bbcr);
				logger.info("#####[sina " + bbcs.getIdentity_id()
						+ " 绑卡 失败内容]:[" + bbcr.getResponse_message() + "]");
			}
		} catch (Exception e) {
			logger.info("#####[sina " + bbcs.getIdentity_id() + " 绑卡 失败内容]:["
					+ e.getMessage() + "]");
			sinaSendLogService.insertSinaLog(bbcs.getRequest_no(), 0, 0,
					SinaMemberParmUtil.interfaceName.binding_bank_card
							+ "|绑定银行卡", bbcs, bbcr);
			logger.info("*****[sina " + bbcs.getIdentity_id() + " 绑卡 结束]");
		}
		logger.info("*****[sina " + bbcs.getIdentity_id() + " 绑卡 结束]");
		return map;
	}


	public Integer checkBindingBank(String accountNo,Integer userId) {
		SinaCard sc = new SinaCard();
		sc.setCardId(accountNo);
		sc.setStatus("0");
		sc.setUserId(userId);
		List<SinaCard> scs = sinaCardService.selectSelective(sc);
		if (!scs.isEmpty() && scs.get(0) != null) {
			return scs.get(0).getScId();
		} else {
			return 0;
		}
	}
}
