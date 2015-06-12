package cn.p2p.p2p.prd.mobile.method.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.request.vo.PageUtilVO;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.model.AccountLog;
import cn.vfunding.vfunding.biz.account.service.IAccountLogService;
import cn.vfunding.vfunding.biz.common.vo.QueryParameterVO;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

@Service
public class AccountLogMethod {

	@Autowired
	private IAccountLogService accountLogService;
	@Autowired
	private IUserTokenService userTokenService;
	
	/**
	 * 查询资金记录
	 * @param pageUtil
	 * @return
	 * @throws Exception
	 *
	 * 2015年5月25日
	 * lijianwei
	 */
	@CheckToken
	public MobileBaseResponse getAccountLogList(PageUtilVO pageUtil) throws Exception {
		if(pageUtil.getPage() == null || pageUtil.getPage() < 1)
			return new MobileBaseResponse("page_fail", "页数不可小于1");
		PageSearch pageSearch = new PageSearch();
		QueryParameterVO vo = new QueryParameterVO();
		vo.setUserId(this.userTokenService.selectUserIdByToken(pageUtil.getToken()));
		pageSearch.setEntity(vo);
		List<AccountLog> resultList = this.accountLogService.selectAccountLogListPage(pageSearch);
		return new MobileBaseResponse(resultList);
	}
	
}
