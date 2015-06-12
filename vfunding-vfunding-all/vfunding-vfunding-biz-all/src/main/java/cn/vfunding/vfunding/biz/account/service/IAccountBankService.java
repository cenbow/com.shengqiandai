package cn.vfunding.vfunding.biz.account.service;

import java.util.List;
import java.util.Map;

import cn.vfunding.vfunding.biz.account.model.AccountBank;
import cn.vfunding.vfunding.biz.sina.model.SinaCard;

public interface IAccountBankService {
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入数据
	 * @param record
	 * @return 自增长ID
	 */
    int insert(AccountBank record);
    /**
	 * 插入数据
	 * @param record
	 * @return 自增长ID
	 */
    int insertSelective(AccountBank record);

    AccountBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AccountBank record);

    int updateByPrimaryKey(AccountBank record);
    
    /** 
     * 根据用户ID查询
     * @param userId
     * @return
     * 2014年4月24日
     * liuyijun
     */
    List<AccountBank> selectByUserId(Integer userId);
    
    /**
     * 根据账号查询
     * @param account
     * @return
     * 2014年5月8日
     * liuyijun
     */
    List<AccountBank> selectByAccount(String account);
    
    /**绑定银行卡
     * @param accountBank
     * @return
     * 2014年5月9日
     * liuyijun
     */
    int bindBankCard(AccountBank accountBank);
    
    
    /**
     * 查询用户已绑定的银行信息
     * @param userId
     * @return
     * 2014年5月9日
     * liuyijun
     */
    List<AccountBank> selectBindByUserId(Integer userId);
    
    
    /**
     * 添加并绑定银行卡
     * @param accountBank
     * @return 返回新添加的Id
     * 2014年5月9日
     * liuyijun
     */
    int addAndBind(AccountBank accountBank);
    

    /**
     * 新:根据银行卡号查询
     * @param account
     * @return
     *
     * jianwei
     */
    AccountBank selectByAccountNew(Map<String, String> pram);
}
