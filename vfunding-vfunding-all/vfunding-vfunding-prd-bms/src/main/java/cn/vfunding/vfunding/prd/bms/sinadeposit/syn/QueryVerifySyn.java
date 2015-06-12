package cn.vfunding.vfunding.prd.bms.sinadeposit.syn;

import cn.vfunding.vfunding.biz.sina.beanUtil.SinaMemberParmUtil;
import cn.vfunding.vfunding.biz.sina.service.ISinaMemberManagerService;
import cn.vfunding.vfunding.biz.sina.service.ISinaSendActionLogService;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryVerifyReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryVerifySendVO;
import cn.vfunding.vfunding.biz.user.model.User;

/**
 * 
 * @author wang.zeyan
 * @date 2015年2月12日
 * @version 1.0
 */
public class QueryVerifySyn extends BaseSyn {

	public QueryVerifySyn(ISinaMemberManagerService sinaMemberManagerService,
			User user) {
		super(sinaMemberManagerService, user);
	}
	
	public QueryVerifySyn(
			ISinaMemberManagerService sinaMemberManagerService, User user, ISinaSendActionLogService sinaSendActionLogService ) {
		super(sinaMemberManagerService, user);
		this.sinaSendActionLogService = sinaSendActionLogService;
	}

	@Override
	public void execute() {
		QueryVerifySendVO vo = new QueryVerifySendVO();
		vo.setIdentity_id(user.getUserId()+"");
		vo.setIdentity_type(SinaMemberParmUtil.IdentityType.UID);
		vo.setVerify_type(SinaMemberParmUtil.VerifyType.MOBILE);
		try {
			QueryVerifyReturnVO result = sinaMemberManagerService.queryVerify(vo);
			if(result == null){
				setResultSet("false");
			}else
				setResultSet(result.getResponse_code());
			
		} catch (Exception e) {
			e.printStackTrace();
			setResultSet(result(e));
		}
	}

	@Override
	protected boolean isOK(String responseCode) {
		if(SinaMemberParmUtil.ResponseCode.APPLY_SUCCESS.equals(responseCode)){
			return true;
		}
		return false;
	}

	@Override
	public String operationName() {
		return SinaMemberParmUtil.interfaceName.query_verify;
	}

}
