package cn.vfunding.vfunding.prd.bms.sinadeposit.syn;
import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendActionLogService;
import cn.vfunding.vfunding.biz.user.model.User;


/**
 * 创建会员同步
 * @author wang.zeyan
 * @date 2015年2月2日
 * @version 1.0
 */
public class CreateActivateMemberSyn extends BaseSyn {

	public CreateActivateMemberSyn(
			ISinaMemberManagerService sinaMemberManagerService, User user) {
		super(sinaMemberManagerService, user);
	}
	
	public CreateActivateMemberSyn(
			ISinaMemberManagerService sinaMemberManagerService, User user, ISinaSendActionLogService sinaSendActionLogService ) {
		super(sinaMemberManagerService, user);
		this.sinaSendActionLogService = sinaSendActionLogService;
	}

	@Override
	public void execute() {
		try {
			String  result = sinaMemberManagerService.createActivateMember(user.getUserId() + "");
			setResultSet(result);
		} catch (Exception e) {
			//e.printStackTrace();
			setResultSet(result(e));
		}
	}
	
	
	@Override
	public String operationName() {
		return SinaMemberParmUtil.interfaceName.create_activate_member;
	}
	
	@Override
	public boolean isOK(String responseCode) {
		if(SinaMemberParmUtil.success.equals(responseCode) 
				|| SinaMemberParmUtil.ResponseCode.DUPLICATE_IDENTITY_ID.equals(responseCode)){
			return true;
		}
		return false;
	}
	
}
