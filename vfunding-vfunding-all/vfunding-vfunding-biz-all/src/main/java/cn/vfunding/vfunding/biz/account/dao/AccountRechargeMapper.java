package cn.vfunding.vfunding.biz.account.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountRecharge;
import cn.vfunding.vfunding.biz.common.vo.AccountRechargeVO;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;

public interface AccountRechargeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(AccountRecharge record);

	int insertSelective(AccountRecharge record);

	AccountRecharge selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AccountRecharge record);

	/**
	 * 用户是否充值过
	 * @author liuhuan
	 */
	int selectUserIsRecharge(Integer userId);

	/**
	 * 根据用户ID、时间分页查询充值信息
	 * 
	 * @param search
	 * @return 2014年4月24日 liuyijun
	 */
	List<UserRechargeCashMobile> selectByUserIdRechargeListPage(
			PageSearch search);

	/**
	 * 根据用户ID、时间分页查询提现信息
	 * @param search
	 * @return author LiLei 2014年5月5日
	 */
	List<UserRechargeCashMobile> selectByUserIdCashListPage(PageSearch search);
	
	/**
     * 财务审核列表
     * @author liuhuan
     */
	List<AccountRechargeVO> selectWaitApplyListPage(PageSearch search);

    int updateByPrimaryKey(AccountRecharge record);
    
    /**
     * @Description:用户15天内充值总额
     * @param userId
     * @return
     * @author liuhuan
     */
    String selectCount15daysByUserId(Integer userId);
    /**
     * 用户历史充值总额
     * @param userId
     * @return
     */
    String selectCountHistoryByUserId(Integer userId); 
    
    /**
     * 更新状态
     * @param record
     * @return
     * 2014年4月30日
     * liuyijun
     */
    int updateStatus(AccountRecharge record);
    
    /**
     * 根据订单号 
     * @param tradeNo
     * @return
     * 2014年4月30日
     * liuyijun
     */
    AccountRecharge selectByTradeNo(String tradeNo);
    /**
     * 根据用户id查询用户充值记录
     * @param search
     * @param userId
     * @return
     * lx
     */
    List<AccountRecharge> selectByIdListPage(PageSearch search);

}