package cn.vfunding.vfunding.biz.account.service;

import java.math.BigDecimal;
import java.util.List;

import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;
import cn.vfunding.vfunding.biz.account.model.AccountFeelLog;
import cn.vfunding.vfunding.biz.common.vo.FeelVO;
import cn.vfunding.vfunding.biz.system.model.Feel;
import cn.vfunding.vfunding.biz.user.model.User;

public interface IAccountFeelService {

	int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据
     * @param record
     * @return 自增长ID
     */
    int insert(AccountFeel record);

    /**
     * 插入数据
     * @param record
     * @return 自增长ID
     */
    int insertSelective(AccountFeel record);

    AccountFeel selectByPrimaryKey(Integer id);
    /**
     * 根据用户ID查询账户信息
     * @param userId
     * @return
     */
    AccountFeel selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(AccountFeel record);

    int updateByPrimaryKey(AccountFeel record);
    
    /**
     * 根据用户ID跟新
     * @param record
     * @return
     */
    int updateByUserId(AccountFeel record);
    
    /**
     * 增加体验金 并写入日志
     * @param feel
     * @param feelLog
     * @param status 0:插入  1：更新
     * @return
     * @author liuhuan
     */
    String insertFeelLog(AccountFeel accountFeel, AccountFeelLog feelLog, Feel feel, Integer status);
    
    /**
     * @Description:给新用户派发体验券
     * @param userId
     * @param account:体验券面值
     * @return
     * @author liuhuan
     */
    @NeedLock
    String updateSendCodeForNewuser(Integer userId,BigDecimal account, String ip);
    /**
     * @Description:线上申请体验券
     * @return
     * @author liuhuan
     */
    String updateSendCodeForOnline(String type,BigDecimal account,Integer size);

    /**
     * @Description:提现充值
     * @param user
     * @param feelCode
     * @param ip
     * @return
     * @author liuhuan
     */
    @NeedLock
	String updateRechargeFeel(User user, String feelCode,String ip);

	List<FeelVO> selectFeelsListPage(PageSearch pageSearch);

}
