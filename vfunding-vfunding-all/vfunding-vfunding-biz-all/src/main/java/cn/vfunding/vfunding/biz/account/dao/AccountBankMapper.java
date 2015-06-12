package cn.vfunding.vfunding.biz.account.dao;

import java.util.List;
import java.util.Map;

import cn.vfunding.vfunding.biz.account.model.AccountBank;

public interface AccountBankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AccountBank record);

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
    
    
    /**
     * 查询用户已绑定的银行信息
     * @param userId
     * @return
     * 2014年5月9日
     * liuyijun
     */
    List<AccountBank> selectBindByUserId(Integer userId);
    
    /**
     * 新:根据银行卡号查询
     * @param account
     * @return
     *
     * jianwei
     */
    AccountBank selectByAccountNew(Map<String, String> pram);
}