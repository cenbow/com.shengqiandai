package cn.vfunding.vfunding.biz.user.dao;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.model.NumberInvitationVO;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.biz.user.model.User2Sinamember;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserWithBLOBs record);

    int insertSelective(UserWithBLOBs record);

    UserWithBLOBs selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserWithBLOBs record);

    int updateByPrimaryKey(User record);
    
    /**
     * 根据身份证号码查询用户
     * @param cardId
     * @return
     * 2014年4月24日
     * liuyijun
     */
    List<UserWithBLOBs> selectByCardId(String cardId);
    /**
     * 更新用户类型
     * @param record
     * @return
     */
    int updateUserType(User record);
    
    int updateUserCardImage(User record);
    
    UserWithBLOBs selectByUserName(String userName);
    /**
     * 注册时用户的用户名、邮箱、手机号码唯一性检查
     * @param value
     * @return 查询到的数量
     * @author liuyijun
     */
    int  checkRegister(String value);
    /**
     * 根据用户名查询用户ID
     * @param userName
     * @return userId
     * @author liuyijun
     */
    int selectUserIdByUserName(String userName);
    
    
    /**
     * 根据可登陆值查询用户对象
     * @param value
     * @return
     * @author liuyijun 
     * 2014年4月9日
     */
    List<UserWithBLOBs>  selectByLogin(String value);
    
    /**
     * 更新登录信息
     * @param record
     * @return
     * @author liuyijun 
     * 2014年4月11日
     */
    int updateLoginInfo(User record);
    
    /**
     * 更新密码保护问题
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int updateQuestion(User record);
    
    /**
     * java版实名认证
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int updateRealName(User record);
    
    
    /**
     * 视频认证申请
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int updateVideo(User record);
    
    /**
     * 现场认证申请
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int updateScene(User record);
    
    /**
     * 修改登录密码
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int updateLoginPassword(User record);
    
    /**
     * 修改支付密码
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int updatePayPassword(User record);
    
    /**
     * 修改手机
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int updatePhone(User record);
    
    /**
     * 修改邮箱
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int updateEmail(User record);
    
    /**
     * 添加邮箱
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int addEmail(User record);
    
    /**
     * 邮箱认证
     * @param record
     * @return
     * 2014年4月24日
     * liuyijun
     */
    int checkEmail(User record);
    
    /**
     * 注册时查询介绍人信息
     * @param value
     * @return
     * 2014年4月24日
     * liuyijun
     */
    List<UserWithBLOBs> selectIntroducer(String value);
    
    /**
     * 用户保存基本信息
     * @param record
     * @return
     * 2014年5月8日
     * liuyijun
     */
    int saveInfo(User record);
    
    /**
     * 我的好友
     * @param userId
     * @return
     * 2014年5月17日
     * liuyijun
     */
    List<User> selectFriendByUserIdListPage(PageSearch pageSearch);
    /**
     * 查询用户管理列表
     * @param pageSearch
     * @return
     * @author lx
     */
    List<User>  selectFriendByUserListPage(PageSearch pageSearch);
    
    /**
     * 根据用户ID查询红包
     * @param userId
     * @return
     * 2015年1月19日
     * liuyijun
     */
    BigDecimal selectHongbaoByUserId(Integer userId);
    
    /**
     * 查询用户
     * @param pageSearch
     * @return
     *
     * @author wang.zeyan
     * @date 2015年1月26日
     */
    List<User> selectUsersByListPage(PageSearch pageSearch);
    
    /**
	 * 查询余额大于0 或者有红包的用户
	 * wang.zeyan 2015年2月12日
	 * @param pageSearch
	 * @return
	 */
	List<User2Sinamember> selectAllUsersByBalanceOrRedListPage(PageSearch pageSearch);
    /**
     * 查询可发送邮件的所有用户
     * @return
     */
    List<User> selectForEmail();
    
    /**
     * 邀请人数排行榜
     * wang.zeyan 2015年3月2日
     * @return
     */
    List<NumberInvitationVO> selectNumberInvitationLeaderboard();
    
    /**
     * 根据userId 获得邀请人数 数量
     * wang.zeyan 2015年3月3日
     * @param inviteId
     * @return
     */
    int getInviteCount(Integer userId);
    
    /**
	 * 根据userId 获得第一个邀请人
	 * 
	 * wang.zeyan 2015年3月4日
	 * @param userId
	 * @return
	 */
    User getInviteFirstUser(Integer userId);
    
    /**
     * 根据userId 获得 邀请人 userId 集合
     * wang.zeyan 2015年3月4日
     * @param userId
     * @return
     */
    List<User> selectInviteAllUser(Integer userId);
    /**
     * 查询需要发送注册问候短信的用户
     * @return
     */
    List<User> selectByRegisterCompliment();
    /**
     * 查询当天生气且能发送邮件的用户
     * @return
     */
    List<User> selectForEmailByBirthday(String today);
    
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