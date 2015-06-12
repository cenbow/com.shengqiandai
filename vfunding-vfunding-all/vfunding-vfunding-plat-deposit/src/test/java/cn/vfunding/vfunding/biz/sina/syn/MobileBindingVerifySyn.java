package cn.vfunding.vfunding.biz.sina.syn;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.user.model.User;

/**
 * 手机认证同步
 * @author wang.zeyan
 * @date 2015年2月2日
 * @version 1.0
 */
public class MobileBindingVerifySyn extends BaseSyn {

	
	public MobileBindingVerifySyn(
			ISinaMemberManagerService sinaMemberManagerService, User user) {
		super(sinaMemberManagerService, user);
	}

	@Override
	public void execute() {
		try {
			String  result = sinaMemberManagerService.mobileBindVerify(user);
			setResultSet(result);
		} catch (Exception e) {
			//e.printStackTrace();
			setResultSet(result(e));
		}
	}
	
	@Override
	public String operationName() {
		return SinaMemberParmUtil.interfaceName.binding_verify;
	}

	@Override
	public boolean isOK(String responseCode) {
		if(SinaMemberParmUtil.success.equals(responseCode) 
				|| SinaMemberParmUtil.ResponseCode.DUPLICATE_VERIFY.equals(responseCode)){
			return true;
		}
		return false;
	}
}
