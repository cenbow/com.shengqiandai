package cn.vfunding.vfunding.biz.useraction_activity.service;

import cn.vfunding.vfunding.biz.common.vo.FinalVerifyVO;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.user.model.User;

public interface IUserActionService {


	/**
	 * 注册后相关活动处理
	 * 
	 * @param vo
	 * @return 2015年1月15日 liuyijun
	 */
	String doRegisterBiz(RegisterVO vo);

	/**
	 * 用户投资后相关活动处理
	 * @param vo
	 * @return
	 * 2015年1月16日
	 * liuyijun
	 */
	String doUserTenderBiz(UserTenderActionResultVO vo);

	/**
	 * 验证邮箱相关活动处理
	 * @param user
	 * @return
	 * 2015年1月19日
	 * liuyijun
	 */
	String doEmailVerifyBiz(User record);
	
	/**
	 * 实名认证相关活动
	 * @param user
	 * @return
	 * 2015年1月19日
	 * liuyijun
	 */
	String doRealNameVerifyBiz(User record);
	
	/**
	 * 满标复审
	 * @param verifyVO
	 * @return
	 */
	String doBorrowVerify(FinalVerifyVO verifyVO);

}
