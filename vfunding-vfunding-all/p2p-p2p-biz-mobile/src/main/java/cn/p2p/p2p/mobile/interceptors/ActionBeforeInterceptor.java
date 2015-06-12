package cn.p2p.p2p.mobile.interceptors;

import java.lang.reflect.Method;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseRequest;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.biz.mobile.model.MobileRequestLog;
import cn.p2p.p2p.biz.mobile.model.MobileResponseLog;
import cn.p2p.p2p.biz.mobile.service.IMobileRequestLogService;
import cn.p2p.p2p.biz.mobile.service.IMobileResponseLogService;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.usertoken.model.UserToken;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;

public class ActionBeforeInterceptor implements MethodInterceptor  {

	@Autowired
	private IUserTokenService userTokenService;
	@Autowired
	private IMobileRequestLogService mobileRequestLogService;
	@Autowired
	private IMobileResponseLogService mobileResponseLogService;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		Object obj = null;
		Method method = invocation.getMethod();
		Object[] args = invocation.getArguments();
		ContBefore cb = method.getAnnotation(ContBefore.class);
		if(EmptyUtil.isNotEmpty(cb)){
			obj = invocation.proceed();
			return obj;
		}
		if(args.length > 0){
			MobileBaseRequest request = JSON.parseObject(JSON.toJSONString(args[0]), MobileBaseRequest.class);
			this.saveMobileRequestLog(JSON.toJSONString(args[0]));
			CheckToken action = method.getAnnotation(CheckToken.class);
			if (EmptyUtil.isNotEmpty(action)) {
				MobileBaseResponse response = this.checkUserToken(request.getToken());
				if(!response.getResponseCode().equals("success")){
					obj = response;
				}else{
					obj = invocation.proceed();
				}
			}else{
				obj = invocation.proceed();
			}
			this.saveMobileResponseLog(obj, JSON.toJSONString(args[0]));
		}else{
			obj = invocation.proceed();
		}
		return obj;
	}
	
	private MobileBaseResponse checkUserToken(String token) {
		if (StringUtils.isEmpty(token))
			return new MobileBaseResponse("token_fail", "用户登陆信息校验失败");
		UserToken ut = new UserToken();
		ut.setToken(token);
		ut = userTokenService.selectBySelective(ut);
		if (ut == null)
			return new MobileBaseResponse("token_fail", "用户登陆信息校验失败");
		else
			return new MobileBaseResponse();
	}


	private void saveMobileRequestLog(String requestJson) {
		MobileRequestLog mrl = new MobileRequestLog();
		MobileBaseRequest mbRequest = JSON.parseObject(requestJson, MobileBaseRequest.class);
		mrl.setMethodName(mbRequest.getMethodName());
		mrl.setOrderNo(mbRequest.getOrderNo());
		mrl.setOsType(mbRequest.getOsType());
		mrl.setToken(mbRequest.getToken());
		mrl.setRequestJson(requestJson);
		mrl.setAddtime(new Date());
		this.mobileRequestLogService.insertSelective(mrl);
	}

	private MobileBaseResponse saveMobileResponseLog(Object response, String requestJson) {
		MobileBaseRequest mbRequest = JSON.parseObject(requestJson, MobileBaseRequest.class);
		MobileBaseResponse mbResponse = (MobileBaseResponse) response;
		MobileResponseLog mrl = new MobileResponseLog();
		mrl.setOrderNo(mbRequest.getOrderNo());
		mrl.setResponseJson(JSON.toJSONString(response));
		mrl.setResponseCode(mbResponse.getResponseCode());
		mrl.setResponseMessage(mbResponse.getResponseMessage());
		mrl.setAddtime(new Date());
		this.mobileResponseLogService.insertSelective(mrl);
		return mbResponse;
	}

	
}
