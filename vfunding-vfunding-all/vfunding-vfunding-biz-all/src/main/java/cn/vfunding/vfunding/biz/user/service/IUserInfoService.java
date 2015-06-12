package cn.vfunding.vfunding.biz.user.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.EarnedAndWaitOfReturnFeeVO;
import cn.vfunding.vfunding.biz.returns.model.ReturnFeeData;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.user.model.UserInfo;

public interface IUserInfoService {

	int insertSelective(UserInfo record);

	int updateByPrimaryKeySelective(UserInfo record);

	/**
	 * 根据用户Id查询基本信息
	 * 
	 * @param userId
	 * @return 2014年5月8日 liuyijun
	 */
	UserInfo selectByUserId(Integer userId);

	String insertUpFinancialPlanner(UserSession user);

	List<ReturnFeeData>  selectReturnFeeDatailListPage(PageSearch pageSearch);

	EarnedAndWaitOfReturnFeeVO selectEarnedAndWaitOfReturnFee(Integer userId);
    /**
     * 根据id查询出基本信息
     * lx
     * @param userId
     * @return
     */
	UserInfo selectByUser_Id(Integer userId);

}
