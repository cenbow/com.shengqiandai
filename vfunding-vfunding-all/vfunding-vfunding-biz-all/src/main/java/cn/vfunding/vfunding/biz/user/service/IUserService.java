package cn.vfunding.vfunding.biz.user.service;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.common.vo.CjdVO;
import cn.vfunding.vfunding.biz.common.vo.RegisterVO;
import cn.vfunding.vfunding.biz.common.vo.UnionUserBandVO;
import cn.vfunding.vfunding.biz.common.vo.UnionUserRegisterVO;
import cn.vfunding.vfunding.biz.common.vo.UserInfoVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.User2Sinamember;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;
import cn.vfunding.vfunding.common.module.activemq.message.UnionUserRealNameMessage;

public interface IUserService {
	/**
	 * 根据主键查询用户对象
	 * 
	 * @param userId
	 * @return
	 * @author liuyijun
	 */
	UserWithBLOBs selectByPrimaryKey(Integer userId);

	/**
	 * 根据用户名查找用户对象
	 * 
	 * @param userName
	 * @return
	 * @author liuyijun
	 */
	UserWithBLOBs selectByUserName(String userName);

	/**
	 * php版实名认证接口
	 * 
	 * @param record
	 * @param multipartEntity
	 * @return
	 */
	int updateUserCardImage(User record, List<MultipartEntityBuilder> multipartEntitys);

	/**
	 * 测试多图上传
	 * 
	 * @param multipartEntitys
	 * @return
	 * @author liuyijun
	 */
	List<String> testUploadImage(List<MultipartEntityBuilder> multipartEntitys);

	/**
	 * 注册时用户的用户名、邮箱、手机号码唯一性检查
	 * 
	 * @param value
	 * @return 可注册时返回true
	 */
	boolean checkRegister(String value);

	/**
	 * 网站联盟用户同步微积金用户注册
	 * 
	 * @param vo
	 * @author liuyijun
	 */
	void unionUserRegister(UnionUserRegisterVO unionUserRegister);

	/**
	 * 网站联盟用户绑定微积金账户信息
	 * 
	 * @param unionUserBand
	 * @author liuyijun
	 */
	void unionUserBandVfundingUser(UnionUserBandVO unionUserBand);

	/**
	 * app用户注册
	 * 
	 * @param userName
	 * @param password
	 * @param mobile
	 * @author liuyijun
	 */
	@Deprecated
	void mobileRegist(String userName, String password, String mobile, String inviteUserid);

	/**
	 * 注册
	 * 
	 * @param register
	 * @param ip
	 * @param request
	 * @return 新用户ID
	 * @author liuyijun 2014年4月16日
	 */
	@AfterAction(actionName="register")
	Integer register(RegisterVO register, String ip, String refererUrl);
	
	

	/**
	 * 财经道注册接口 type:1-财经道正常注册；2-财经道注册送红包
	 * 
	 * @Description:
	 * @param register
	 * @param ip
	 * @return
	 * @author liuhuan
	 */
	@AfterAction(actionName="register")
	Integer registerForCjd(RegisterVO register, CjdVO vo, String ip, Integer type, String refererUrl) throws Exception;
	/**
	 * 财经道注册辅助业务
	 * @param register
	 * @param cjdvo
	 * @param type
	 * 2015年1月14日
	 * liuyijun
	 */
	void registerForCjdAfter(RegisterVO register, CjdVO cjdvo, Integer type);

	/**
	 * 第三方注册
	 * 
	 * @param register
	 * @param ip
	 * @param account
	 * @param category
	 * @return 2014年6月6日 liuyijun
	 */
	@AfterAction(actionName="register")
	Integer thirdRegister(RegisterVO register, String ip, String account, String category, String refererUrl);

	/**
	 * 根据可登陆值查询用户对象
	 * 
	 * @param introducer
	 * @return
	 * @author liuyijun 2014年4月9日
	 */
	List<UserWithBLOBs> selectByLogin(String loginValue);

	/**
	 * 用户登录成功后更新用户登录记录
	 * 
	 * @param record
	 * @param lastIp
	 * @author liuyijun 2014年4月11日
	 */
	void userLogin(User record, HttpServletRequest request);

	/**
	 * 财经道登录
	 * 
	 * @author liuhuan
	 */
	void userLoginCjdao(User record, String third, HttpServletRequest request);

	/**
	 * 根据ID跟更新UserWithBLOBs
	 * 
	 * @param user
	 * @return author LiLei 2014年4月21日
	 */
	Integer updateByPrimaryKeySelective(UserWithBLOBs user);

	/**
	 * 获取用户二维码
	 * 
	 * @param userId
	 *            用户ID
	 * @param content
	 *            二维码内容
	 * @param logoFile
	 *            logo图片文件
	 * @return 2014年4月21日 liuyijun
	 */
	byte[] getUserQRCode(String content, File logoFile);

	/**
	 * 更新密码保护问题
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	int updateQuestion(User record);

	/**
	 * java版实名认证
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	@AfterAction(actionName="realNameVerfiy")
	int updateRealName(User record);

	/**
	 * 视频认证申请
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	int updateVideo(User record);

	/**
	 * 现场认证申请
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	int updateScene(User record);

	/**
	 * 修改登录密码
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	int updateLoginPassword(User record);

	/**
	 * 修改支付密码
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	int updatePayPassword(User record);

	/**
	 * 修改手机
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	int updatePhone(User record);

	/**
	 * 修改邮箱
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	int updateEmail(User record);

	/**
	 * 添加邮箱
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	int addEmail(User record);

	/**
	 * 邮箱认证
	 * 
	 * @param record
	 * @return 2014年4月24日 liuyijun
	 */
	@AfterAction(actionName="emailVerfiy")
	int checkEmail(User record);

	/**
	 * 注册时查询介绍人信息
	 * 
	 * @param value
	 * @return 2014年4月24日 liuyijun
	 */
	List<UserWithBLOBs> selectIntroducer(String value);

	/**
	 * 保存用户基本信息
	 * 
	 * @param userInfo
	 * @return 2014年5月8日 liuyijun
	 */
	int saveUserInfo(UserInfoVO userInfo);

	/**
	 * 我的好友
	 * 
	 * @param pageSearch
	 * @return 2014年5月17日 liuyijun
	 */
	List<User> selectFriendByUserIdListPage(PageSearch pageSearch);

	/**
	 * 处理注册时其他相关的数据
	 * 
	 * @param userId
	 * @param registerIp
	 *            2014年5月19日 liuyijun
	 */
	void doRegisterInfo(Integer userId, String registerIp);

	/**
	 * 根据身份证号码查询用户
	 * 
	 * @param cardId
	 * @return 2014年4月24日 liuyijun
	 */
	List<UserWithBLOBs> selectByCardId(String cardId);

	/**
	 * 网站联盟实名认证同步微积金用户实名认证
	 * 
	 * @param message
	 *            2014年5月22日 liuyijun
	 */
	void unionUserRealName(UnionUserRealNameMessage message);

	/**
	 * 检测用户名的合法性
	 * 
	 * @param userName
	 * @return 2014年6月10日 liuyijun
	 */
	boolean checkUserNameIsLegal(String userName);

	/**
	 * 用户申请VIP
	 * 
	 * @param account
	 * @param j
	 * @param useHongbao
	 * @param vipPrice
	 *            2014年6月14日 liuyijun
	 */
	void becomeVIP(Account account, Json j, String useHongbao, Integer vipPrice);

	/**
	 * 用户选择头像
	 * 
	 * @param user
	 *            2014年6月21日 liuyijun
	 */
	void updateHeadPic(UserWithBLOBs user);

	/**
	 * 查询用户列表
	 * 
	 * @param pageSearch
	 * @return lx
	 */
	List<User> selectFriendByUserListPage(PageSearch pageSearch);

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 * @return
	 */
	int updateByPrimaryKey(User user);

	/**
	 * 锁定用户
	 * 
	 * @param user
	 *            2014年7月2日 liuyijun
	 */
	void lockUser(UserWithBLOBs user);

	/**
	 * 退出
	 * 
	 * @param userId
	 *            2014年7月2日 liuyijun
	 */
	void doOut(Integer userId, HttpSession httpSession);


	/**
	 * 获取用户红包
	 * @param userId
	 * @return
	 * 2015年1月19日
	 * liuyijun
	 */
	BigDecimal  getUserHongbao(Integer userId);
	
	/**
	 * 查询所有用户
	 * @param pageSearch
	 * @return
	 *
	 * @author wang.zeyan
	 * @date 2015年1月26日
	 */
	List<User> selectAllUsersByListPage(PageSearch pageSearch);
	
	/**
	 * 查询余额大于0 或者有红包的用户
	 * wang.zeyan 2015年2月12日
	 * @param pageSearch
	 * @return
	 */
	List<User2Sinamember> selectAllUsersByBalanceOrRedListPage(PageSearch pageSearch);

	/**
     * 根据userId 获得邀请人数 数量
     * wang.zeyan 2015年3月3日
     * @param inviteId
     * @return
     */
	int getInviteCount(Integer userId);
	
	/**
	 * 根据userId 获得他所邀请的人的userId集合
	 * wang.zeyan 2015年3月4日
	 * @return
	 */
	List<User> getInviteAllUser(Integer userId);
	
	/**
	 * 根据userId 获得第一个邀请人
	 * 
	 * wang.zeyan 2015年3月4日
	 * @param userId
	 * @return
	 */
	User getInviteFirstUser(Integer userId);
	
	/**
     * 查询需要发送注册问候短信的用户
     * @return
     */
    List<User> selectByRegisterCompliment();
    
    
    /**
     * 查询长时间没有投资/闲置用户
     * @param today
     * @return
     */
    List<User> selectNoTenderUser(String today);
    /**
     * 查询只注册验证邮箱的新用户
     * @param days
     * @return
     */
    List<User> selectNoLoginUser(Integer days);
}
