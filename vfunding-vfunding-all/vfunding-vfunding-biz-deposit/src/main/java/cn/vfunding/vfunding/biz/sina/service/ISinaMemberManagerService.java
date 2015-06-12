package cn.vfunding.vfunding.biz.sina.service;


import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.sina.vo.returns.QueryVerifyReturnVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.BindingVerifySendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.CreateActivateMemberSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.QueryVerifySendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.SetRealNameSendVO;
import cn.vfunding.vfunding.biz.sina.vo.sends.UnbindingVerifySendVO;
import cn.vfunding.vfunding.biz.user.model.User;

/**
 * Sina会员管理接口
 * 
 * @author wang.zeyan
 * @date 2015年1月15日
 * @version 1.0
 */
public interface ISinaMemberManagerService {

	/**
	 * 创建激活Sina会员
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月15日
	 */
	String  createActivateMember(CreateActivateMemberSendVO vo) throws Exception;
	
	/**
	 * 创建激活Sina会员 根据vfunding会员信息组建
	 * @param register
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月15日
	 */
	String createActivateMember(RegisterVO register) throws Exception;
	
	/**
	 * 
	 * @param identityId
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月26日
	 */
	String createActivateMember(String identityId) throws Exception;
	
	/**
	 * 设置Sina实名认证
	 * @param requestVO
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月16日
	 */
	String setRealName(SetRealNameSendVO requestVO) throws Exception;
	
	/**
	 * 设置Sina实名认证根据vfunding rd_user user_id
	 * @param userId
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月16日
	 */
	String setRealName(Integer userId) throws Exception;
	String setRealName(String userId) throws Exception;
	
	/**
	 * 设置Sina实名认证根据vfunding rd_user
	 * @param user
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月16日
	 */
	String setRealName(User user) throws Exception;
	
	/**
	 * 绑定认证信息
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月19日
	 */
	String bindingVerify(BindingVerifySendVO vo) throws Exception;
	
	/**
	 * 查询认证信息
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月22日
	 */
	QueryVerifyReturnVO queryVerify(QueryVerifySendVO vo) throws Exception;
	
	/**
	 * 邮箱认证
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月19日
	 */
	String mailBindVerify(BindingVerifySendVO vo) throws Exception;
	
	String mailBindVerify(User user) throws Exception;
	
	String mailBindVerify(Integer userId) throws Exception;
	
	String mailBindVerify(String userId) throws Exception;
	
	/**
	 * 移动端认证
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月19日
	 */
	String mobileBindVerify(BindingVerifySendVO vo) throws Exception;
	
	String mobileBindVerify(User user) throws Exception;
	
	String mobileBindVerify(Integer userId) throws Exception;
	
	String mobileBindVerify(String userId) throws Exception;
	
	/**
	 * 解绑认证信息
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月19日
	 */
	String unbindingVerify(UnbindingVerifySendVO vo) throws Exception;
	
	/**
	 * 解绑邮箱认证
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月19日
	 */
	String mailUnbindVerify(UnbindingVerifySendVO vo) throws Exception;
	
	String mailUnBindVerify(User user) throws Exception;
	
	String mailUnBindVerify(Integer userId) throws Exception;
	
	String mailUnBindVerify(String userId) throws Exception;
	
	/**
	 * 解绑移动端认证
	 * @param vo
	 * @return
	 * @throws Exception
	 *
	 * @author wang.zeyan
	 * @date 2015年1月19日
	 */
	String mobileUnBindVerify(UnbindingVerifySendVO vo) throws Exception;
	
	String mobileUnBindVerify(User user) throws Exception;
	
	String mobileUnBindVerify(Integer userId) throws Exception;
	
	String mobileUnBindVerify(String userId) throws Exception;
}
