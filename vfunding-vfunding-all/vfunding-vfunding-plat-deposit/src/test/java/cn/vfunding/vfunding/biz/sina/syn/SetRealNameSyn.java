package cn.vfunding.vfunding.biz.sina.syn;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.user.model.User;

/**
 * 实名认证同步
 * @author wang.zeyan
 * @date 2015年2月2日
 * @version 1.0
 */
public class SetRealNameSyn extends BaseSyn {

	
	public SetRealNameSyn(ISinaMemberManagerService sinaMemberManagerService,
			User user) {
		super(sinaMemberManagerService, user);
	}

	@Override
	public void execute() {
		try {
			String  result = null;
			if(isSetRealStatus())
				result = sinaMemberManagerService.setRealName(user);
			else
				result = "未实名认证！";
			setResultSet(result);
		} catch (Exception e) {
			//e.printStackTrace();
			setResultSet(result(e));
		}
	}
	
	private boolean isSetRealStatus(){
		if("1".equals(user.getRealStatus())){
			return true;
		}
		return false;
	}
	
	@Override
	public String operationName() {
		return SinaMemberParmUtil.interfaceName.set_real_name;
	}
	
	@Override
	public boolean isOK(String responseCode) {
		if(!isSetRealStatus())
			return true;
		if(SinaMemberParmUtil.success.equals(responseCode) 
				||  SinaMemberParmUtil.ResponseCode.DUPLICATE_VERIFY.equals(responseCode)){
			return true;
		}
		return false;
	}

}
