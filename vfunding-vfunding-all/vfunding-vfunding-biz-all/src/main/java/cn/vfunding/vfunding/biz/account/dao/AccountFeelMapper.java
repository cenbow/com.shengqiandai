package cn.vfunding.vfunding.biz.account.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;
import cn.vfunding.vfunding.biz.common.vo.FeelVO;

public interface AccountFeelMapper {
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

	List<FeelVO> selectFeelsListPage(PageSearch pageSearch);
	List<FeelVO> selectRepayFeelBorrowListPage(PageSearch pageSearch);
}