package cn.vfunding.vfunding.biz.sina.service.impl;

import java.net.URLDecoder;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.tools.GsonUtil;
import cn.vfunding.vfunding.biz.sina.tools.SignUtil;
import cn.vfunding.vfunding.biz.sina.tools.Tools;
import cn.vfunding.vfunding.biz.sina.tools.VoToMapUtil;
import cn.vfunding.vfunding.biz.sina.util.SinaParamsUtil;
import cn.vfunding.vfunding.biz.sina.util.SinaSendUtil;
import cn.vfunding.vfunding.biz.sina.util.SinaSignUtil;
import cn.vfunding.vfunding.biz.sina.vo.returns.BaseSinaReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryAccountDetailsReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryBankCardReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BaseSinaSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingVerifySendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateActivateMemberSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryAccountDetailsSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryBankCardSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.SetRealNameSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.UnbindingBankCardSendVO;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

/**
 * 发送新浪接口请求类
 * 
 * @author jianwei
 * @date 2015年1月14日
 * @param <T>
 *            指定返回对象
 */
@Service("sinaSendService")
public class SinaSendServiceImpl implements ISinaSendService {

	/**
	 * 会员接口发送
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public <T extends BaseSinaReturnVO> T sinaSendMgs(Object obj, Class<T> clazz)
			throws Exception {
		String url = SinaParamsUtil.getInstance().getMembersUrl();
		return this.sinaSend(obj, url, clazz);
	}

	/**
	 * 收单类接口
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public <T extends BaseSinaReturnVO> T sinaSendMas(Object obj, Class<T> clazz)
			throws Exception {
		String url = SinaParamsUtil.getInstance().getTradesUrl();
		return this.sinaSend(obj, url, clazz);
	}

	/**
	 * 发送请求
	 * 
	 * @param obj
	 *            传递需要请求的接口VO对象
	 * @return 返回响应的返回对象
	 * @throws Exception
	 */
	private <T> T sinaSend(Object obj, String url, Class<T> clazz)
			throws Exception {
		if (obj instanceof BaseSinaSendVO) {
			// 获取签名
			String sign = SinaSignUtil.getSignMsg(obj);
			BaseSinaSendVO base = (BaseSinaSendVO) obj;
			base.setSign(sign);
			// 发送接口请求,获取返回数据
			String param = Tools.createLinkString(
					VoToMapUtil.voToMap(obj, "send"), true);
			String result = URLDecoder
					.decode(SinaSendUtil.sendPost(url, param),
							base.get_input_charset());
			// 校验返回签名与发送签名是否一致
			if (this.checkSign(result)) {
				return (T) JSON.parseObject(result, clazz);
			} else {
				throw new Exception("签名验证异常");
			}
		}
		return null;
	}

	/**
	 * 签名校验
	 * 
	 * @param result
	 * @return
	 * @throws Exception
	 */
	private boolean checkSign(String result) throws Exception {
		Map<String, String> content = GsonUtil.fronJson2Map(result);
		if (StringUtils.isEmpty(content.get("sign_type")) && !StringUtils.isEmpty(content.get("sign_type"))) {
			String signKey = "";
			if ("MD5".equalsIgnoreCase(content.get("sign_type").toString())) {
				signKey = SinaParamsUtil.getInstance().getKeyMD5();
			} else {
				signKey = SinaParamsUtil.getInstance().getKeyRSA();
			}
			String sign_result = content.get("sign").toString();
			String sign_type_result = content.get("sign_type").toString();
			String _input_charset_result = content.get("_input_charset")
					.toString();
			content.remove("sign");
			content.remove("sign_type");
			content.remove("sign_version");
			String like_result = Tools.createLinkString(content, false);
			if (SignUtil.Check_sign(like_result.toString(), sign_result,
					sign_type_result, signKey, _input_charset_result)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		try {
			SinaSendServiceImpl ss = new SinaSendServiceImpl();
//			SetRealNameSendVO v = new SetRealNameSendVO();
//			v.setRequest_time("20140101000000");
//			v.setIdentity_id("9072");
//			v.setIdentity_type("UID");
//			v.setReal_name("李健伟");
//			v.setCert_no("420381199010190017");
//			v.setCert_type("IC");
//			CreateActivateMemberSendVO cam = new CreateActivateMemberSendVO();
//			cam.setIdentity_id("100199ddfedfaaas");
//			cam.setIdentity_type("UID");
//			cam.setMember_type("1");
//			QueryBankCardSendVO qbcs = new QueryBankCardSendVO();
//			qbcs.setIdentity_id("904");
//			qbcs.setIdentity_type("UID");
//			UnbindingBankCardSendVO ubcs = new UnbindingBankCardSendVO();
//			ubcs.setIdentity_id("904");
//			ubcs.setIdentity_type("UID");
//			ubcs.setCard_id("4542");
//			BindingVerifySendVO bvs = new BindingVerifySendVO();
//			bvs.setIdentity_id("904");
//			bvs.setIdentity_type("UID");
//			bvs.setVerify_type("MOBILE");
//			bvs.setVerify_entity("13671802769");
//			CreateActivateMemberSendVO vo = new CreateActivateMemberSendVO();
//			//设置用户标识信息
//			vo.setIdentity_id(14189+"");
//			//设置用户标识类型
//			vo.setIdentity_type(SinaMemberParmUtil.IdentityType.UID.toString());
//			//设置会员类型
//			vo.setMember_type(SinaMemberParmUtil.MemberType.Personal.toString());
			
			QueryAccountDetailsSendVO qads = new QueryAccountDetailsSendVO();
			qads.setIdentity_id("2777");
			qads.setIdentity_type(SinaMemberParmUtil.IdenType.UID);
			qads.setAccount_type(SinaMemberParmUtil.AccountType.SAVING_POT);
			qads.setStart_time("20150207000001");
			qads.setEnd_time("20150209235959");
			qads.setPage_no(1);
			QueryAccountDetailsReturnVO rv = ss.sinaSendMgs(qads, QueryAccountDetailsReturnVO.class);
			System.out.println(JSON.toJSONString(rv));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
