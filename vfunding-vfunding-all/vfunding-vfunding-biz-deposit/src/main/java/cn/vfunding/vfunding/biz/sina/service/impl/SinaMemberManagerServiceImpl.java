package cn.vfunding.vfunding.biz.sina.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.exception.SinaException;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendLogService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendService;
import cn.vfunding.vfunding.biz.sina.vo.returns.BaseSinaReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryVerifyReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingVerifySendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateActivateMemberSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryVerifySendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.SetRealNameSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.UnbindingVerifySendVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserService;

import com.alibaba.fastjson.JSON;

/**
 * Sina 会员管理服务实现类
 * 
 * @author wang.zeyan
 * @date 2015年1月15日
 * @version 1.0
 */
@Service("sinaMemberManagerService")
public class SinaMemberManagerServiceImpl implements ISinaMemberManagerService {

	private Logger log = LoggerFactory.getLogger("sinaSendActionLog");
	
	/**
	 * Sina发送接口
	 */
	@Autowired
	ISinaSendService sinaSendService;
	
	/**
	 * vfunding 用户接口
	 */
	@Autowired
	IUserService userService;
	
	/**
	 * 操作日志
	 */
	@Autowired
	ISinaSendLogService sinaSendLogService;
	
	
	/**
	 * 根据userId 查询vfunding User信息
	 * @param userId
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月19日
	 */
	private User queryUserById(String userId) throws Exception{
		if(StringUtils.isNotBlank(userId))
			return queryUserById(Integer.parseInt(userId));
		return null;
	}
	
	private User queryUserById(Integer userId) throws Exception{
		UserWithBLOBs user = userService.selectByPrimaryKey(userId);
		return user;
	}

	@Override
	public String createActivateMember(String identityId) throws Exception {
		
		CreateActivateMemberSendVO vo = new CreateActivateMemberSendVO();

		// 设置用户标识信息
		vo.setIdentity_id(identityId);
		// 设置用户标识类型
		vo.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
		// 设置会员类型
		vo.setMember_type(SinaMemberParmUtil.MemberType.Personal);
		// 设置会员扩展信息
		// vo.setExtend_param(null);

		return createActivateMember(vo);
	}
	
	@Override
	public String createActivateMember(CreateActivateMemberSendVO vo) throws Exception {
		if (vo == null) {
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.SINA_CREATEACTIVATEMEMBER_ISNULL);
		}
		if (StringUtils.isBlank(vo.getIdentity_id()) || StringUtils.isBlank(vo.getIdentity_type())) {
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.SINA_CREATEACTIVATEMEMBER_IDENTITYID_OR_IDENTITYTYPE_ISNULL);
		}
		log.info("*****[sina  "+vo.getIdentity_id()+" 创建激活会员 请求参数]" + JSON.toJSONString(vo));
		log.info("*****[sina  "+vo.getIdentity_id()+" 创建激活会员  开始]");
		
		BaseSinaReturnVO resultVO = null;
		String result = null;
		try {
			resultVO = sinaSendService.sinaSendMgs(vo, BaseSinaReturnVO.class);
		} catch (Exception e) {
			log.error("#####[sina  "+vo.getIdentity_id()+" 创建激活会员  异常]");
			throw new SinaException(SinaException.SINA_EXCEPTION , e);
		}finally{
			result = result2Response(resultVO);
			sinaSendLogService.insertSinaLog(vo.getIdentity_id(),  responseCode2status(result) , 0, SinaMemberParmUtil.interfaceName.create_activate_member, vo, resultVO);
			log.info("*****[sina  "+vo.getIdentity_id()+" 创建激活会员  结束]");
			
		}
		return result;
	}

	/**
	 * 
	 * @param responseCode
	 * @return
	 *
	 * @author wang.zeyan
	 * @date 2015年1月15日
	 */

	@Override
	public String createActivateMember(RegisterVO register) throws Exception {

		if (register == null) {
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.REGISTERVO_ISNULL);
		}
		return createActivateMember(register.getRegisterUserId() +"");
	}

	/**
	 * 根据返回结果判断 最终返回
	 * 
	 * @param vo
	 * @return
	 *
	 * @author wang.zeyan
	 * @date 2015年1月15日
	 */
	private String result2Response(BaseSinaReturnVO vo) {
		if(vo == null)
			return "Response is null.Exception";
		
		if (SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS.equals(vo.getResponse_code()))
			return SinaMemberParmUtil.success;

		//return vo.getResponse_message();
		return vo.getResponse_code();
	}
	
	private int responseCode2status(String responseCode){
		if(SinaMemberParmUtil.success.equals(responseCode))
			return 1;
		return 0;
	}

	@Override
	public String setRealName(SetRealNameSendVO requestVO) throws Exception {
		if(requestVO == null){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.SET_REALNAME_REQUEST_ISNULL);
		}
		if(StringUtils.isBlank(requestVO.getIdentity_id()) || 
				   StringUtils.isBlank(requestVO.getIdentity_type()) || 
				   StringUtils.isBlank(requestVO.getReal_name()) || 
				   StringUtils.isBlank(requestVO.getCert_type()) || 
				   StringUtils.isBlank(requestVO.getCert_no())){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.SET_REALNAME_INCOMPLETE_INFO);
		}
		
		log.info("*****[sina  "+requestVO.getIdentity_id()+" 实名认证  请求参数]" + JSON.toJSONString(requestVO));
		log.info("*****[sina "+requestVO.getIdentity_id()+" 实名认证   开始 ]");
		
		BaseSinaReturnVO resultVO = null;
		String result = null;
		try {
			resultVO = sinaSendService.sinaSendMgs(requestVO, BaseSinaReturnVO.class);
		} catch (Exception e) {
			log.error("#####[sina  "+requestVO.getIdentity_id()+" 实名认证  异常]");
			throw new SinaException(SinaException.SINA_SET_REALNAME_EXCEPTION , e);
		} finally{
			result = result2Response(resultVO);
			sinaSendLogService.insertSinaLog(requestVO.getIdentity_id(), responseCode2status(result) , 0, SinaMemberParmUtil.interfaceName.set_real_name, requestVO, resultVO);
			log.info("*****[sina "+requestVO.getIdentity_id()+" 实名认证  结束   ]");
		}
		
		return result;
	}
	
	public String setRealName(Integer userId) throws Exception{

		return setRealName(queryUserById(userId));
	}
	
	public String setRealName(String userId) throws Exception{
		
		return setRealName(queryUserById(userId));
	}
	
	public String setRealName(User user) throws Exception{
		if(user == null)
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.V_USER_ISNULL);
		
		SetRealNameSendVO vo = new SetRealNameSendVO();
		//设置用户标识信息
		vo.setIdentity_id(user.getUserId()+"");
		//设置用户标识类型
		vo.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
		//设置真实姓名
		vo.setReal_name(user.getRealname());
		//设置证件类型
		vo.setCert_type(SinaMemberParmUtil.CertType.IC);
		//设置证件号码
		vo.setCert_no(user.getCardId());
		//是否认证
		vo.setNeed_confirm(SinaMemberParmUtil.Y);
		
		return setRealName(vo);
	}

	@Override
	public String bindingVerify(BindingVerifySendVO vo) throws Exception {
		if(vo == null){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.SINA_VERIFT_REQUEST_ISNULL);
		}
		if(StringUtils.isBlank(vo.getIdentity_id()) ||
				StringUtils.isBlank(vo.getIdentity_type()) ||
				StringUtils.isBlank(vo.getVerify_type())   ||
				StringUtils.isBlank(vo.getVerify_entity())){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.SINA_VERIFT_INCOMPLETE_INFO);
		}
		log.info("*****[sina  "+vo.getIdentity_id()+" 绑定认证  请求参数]" + JSON.toJSONString(vo));
		log.info("*****[sina  "+vo.getIdentity_id()+" 绑定认证  开始]");
		
		BaseSinaReturnVO resultVO = null;
		String result = null;
		try {
			resultVO = sinaSendService.sinaSendMgs(vo, BaseSinaReturnVO.class);
		} catch (Exception e) {
			log.error("#####[sina  "+vo.getIdentity_id()+" 绑定认证  异常]");
			throw new SinaException(SinaException.SINA_EXCEPTION , e);
		} finally{
			result = result2Response(resultVO);
			sinaSendLogService.insertSinaLog(vo.getIdentity_id(), responseCode2status(result), 0, SinaMemberParmUtil.interfaceName.binding_verify, vo, resultVO);
			log.info("*****[sina  "+vo.getIdentity_id()+" 绑定认证  结束]");
		}
		return result;
	}

	@Override
	public String mailBindVerify(BindingVerifySendVO vo) throws Exception {
		if(vo == null){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.SINA_VERIFT_REQUEST_ISNULL);
		}
		vo.setVerify_type(SinaMemberParmUtil.VerifyType.EMAIL);
		
		return bindingVerify(vo);
	}

	@Override
	public String mailBindVerify(User user) throws Exception {
		
		BindingVerifySendVO vo = createBindingVerifySendVO(user);
		vo.setVerify_entity(user.getEmail());
		
		return mailBindVerify(vo);
	}

	@Override
	public String mailBindVerify(Integer userId) throws Exception {
		
		return mailBindVerify(queryUserById(userId));
	}

	@Override
	public String mailBindVerify(String userId) throws Exception {
		
		return mailBindVerify(queryUserById(userId));
	}

	private BindingVerifySendVO createBindingVerifySendVO(User user) throws Exception{
		if(user == null)
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.V_USER_ISNULL);
		BindingVerifySendVO vo = new BindingVerifySendVO();
		vo.setIdentity_id(user.getUserId()+"");
		vo.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
		return vo;
	}
	
	@Override
	public String mobileBindVerify(BindingVerifySendVO vo) throws Exception {
		
		if(vo == null){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.SINA_VERIFT_REQUEST_ISNULL);
		}
		vo.setVerify_type(SinaMemberParmUtil.VerifyType.MOBILE);
		
		return bindingVerify(vo);
	}

	@Override
	public String mobileBindVerify(User user) throws Exception {
		
		BindingVerifySendVO vo = createBindingVerifySendVO(user);
		vo.setVerify_entity(user.getPhone());
		return mobileBindVerify(vo);
	}
	

	@Override
	public String mobileBindVerify(Integer userId) throws Exception {
		
		return mobileBindVerify(queryUserById(userId));
	}

	@Override
	public String mobileBindVerify(String userId) throws Exception {
		
		return mobileBindVerify(queryUserById(userId));
	}

	@Override
	public String unbindingVerify(UnbindingVerifySendVO vo) throws Exception {
		if(vo == null){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.UN_SINA_VERIFT_REQUEST_ISNULL);
		}
		if(StringUtils.isBlank(vo.getIdentity_id()) ||
				StringUtils.isBlank(vo.getIdentity_type()) ||
				StringUtils.isBlank(vo.getVerify_type())){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.UN_SINA_VERIFT_INCOMPLETE_INFO);
		}
		log.info("*****[sina  "+vo.getIdentity_id()+" 解除绑定认证  请求参数]" + JSON.toJSONString(vo));
		log.info("*****[sina  "+vo.getIdentity_id()+" 解除绑定认证  开始]");
		
		
		String result = null;
		BaseSinaReturnVO resultVO = null;
		try {
			resultVO = sinaSendService.sinaSendMgs(vo, BaseSinaReturnVO.class);
		} catch (Exception e) {
			log.error("#####[sina  "+vo.getIdentity_id()+" 解除绑定认证  异常]");
			throw new SinaException(SinaException.SINA_EXCEPTION , e);
		} finally{
			result = result2Response(resultVO);
			sinaSendLogService.insertSinaLog(vo.getIdentity_id(), responseCode2status(result), 0, SinaMemberParmUtil.interfaceName.unbinding_verify, vo, resultVO);
			log.info("*****[sina  "+vo.getIdentity_id()+" 解除绑定认证  结束]");
		}
		return result;
		
	}

	@Override
	public String mailUnbindVerify(UnbindingVerifySendVO vo) throws Exception {
		
		if(vo == null)
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.UN_SINA_VERIFT_REQUEST_ISNULL);
		vo.setVerify_type(SinaMemberParmUtil.VerifyType.EMAIL);
		
		return unbindingVerify(vo);
	}

	@Override
	public String mailUnBindVerify(User user) throws Exception {

		return mailUnbindVerify(createUnbindingVerifySendVO(user));
	}
	
	private UnbindingVerifySendVO createUnbindingVerifySendVO(User user) throws Exception{
		if(user == null)
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.V_USER_ISNULL);
		
		UnbindingVerifySendVO vo = new UnbindingVerifySendVO();
		vo.setIdentity_id(user.getUserId()+"");
		vo.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
		return vo;
	}

	@Override
	public String mailUnBindVerify(Integer userId) throws Exception {

		return mailUnBindVerify(queryUserById(userId));
	}

	@Override
	public String mailUnBindVerify(String userId) throws Exception {

		return mailUnBindVerify(queryUserById(userId));
	}

	@Override
	public String mobileUnBindVerify(UnbindingVerifySendVO vo) throws Exception {
		
		if(vo == null)
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.UN_SINA_VERIFT_REQUEST_ISNULL);
		vo.setVerify_type(SinaMemberParmUtil.VerifyType.MOBILE);
		
		return unbindingVerify(vo);
	}

	@Override
	public String mobileUnBindVerify(User user) throws Exception {

		return mobileUnBindVerify(createUnbindingVerifySendVO(user));
	}

	@Override
	public String mobileUnBindVerify(Integer userId) throws Exception {

		return mobileUnBindVerify(queryUserById(userId));
	}

	@Override
	public String mobileUnBindVerify(String userId) throws Exception {
	
		return mobileUnBindVerify(queryUserById(userId));
	}

	@Override
	public QueryVerifyReturnVO queryVerify(QueryVerifySendVO vo) throws Exception {
		if(vo == null){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.QUERY_SINA_VERIFT_REQUEST_ISNULL);
		}
		if(StringUtils.isBlank(vo.getIdentity_id()) ||
				StringUtils.isBlank(vo.getIdentity_type()) ||
				StringUtils.isBlank(vo.getVerify_type())){
			throw new NullPointerException(SinaMemberParmUtil.CustomResponseCode.QUERY_SINA_VERIFT_REQUEST_ISNULL);
		}
		log.info("*****[sina  "+vo.getIdentity_id()+" 查询认证  请求参数]" + JSON.toJSONString(vo));
		log.info("*****[sina  "+vo.getIdentity_id()+" 查询认证 开始]");
		
		
		QueryVerifyReturnVO resultVO = null;
		try {
			resultVO = sinaSendService.sinaSendMgs(vo, QueryVerifyReturnVO.class);
			log.info(JSON.toJSONString(resultVO));
		} catch (Exception e) {
			log.error("#####[sina  "+vo.getIdentity_id()+" 查询认证  异常]");
			throw new SinaException(SinaException.SINA_EXCEPTION , e);
		} finally{
			sinaSendLogService.insertSinaLog(vo.getIdentity_id(), 2, 0, SinaMemberParmUtil.interfaceName.query_verify, vo, resultVO);
			log.info("*****[sina  "+vo.getIdentity_id()+" 查询认证  结束]");
		}
		return resultVO;
	}
	
}
