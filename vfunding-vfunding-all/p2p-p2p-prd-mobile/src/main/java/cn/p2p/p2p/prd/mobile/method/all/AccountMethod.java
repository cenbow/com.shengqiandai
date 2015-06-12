package cn.p2p.p2p.prd.mobile.method.all;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.request.vo.PageUtilVO;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowCollectionService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.CollectionTenderUserVO;
import cn.vfunding.vfunding.biz.common.vo.QueryParameterVO;
import cn.vfunding.vfunding.biz.userMobile.model.UserRechargeCashMobile;
import cn.vfunding.vfunding.biz.userMobile.service.IUserMobileService;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

@Service
public class AccountMethod {

	@Autowired
	private IUserTokenService userTokenService;
	@Autowired
	private IUserMobileService userMobileService;
	@Autowired
	private IBorrowTenderService tenderService;
	@Autowired
	private IBorrowCollectionService borrowCollectionService;
	/**
	 * 查询充值提现记录
	 * 
	 * @param pageSearch
	 * @param type
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 *             author LiLei 2014年5月19日
	 */
	@CheckToken
	public MobileBaseResponse getListUserRechargeCash(PageUtilVO pageUtil) throws Exception {
		if(pageUtil.getPage() == null || pageUtil.getPage() < 1)
			return new MobileBaseResponse("page_fail", "页数不可小于1");
		PageSearch pageSearch = new PageSearch();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", this.userTokenService.selectUserIdByToken(pageUtil.getToken())); 
		pageSearch.setPage(pageUtil.getPage());
		pageSearch.setRows(pageUtil.getRows());
		pageSearch.setEntity(map);
		List<UserRechargeCashMobile> resultList = this.userMobileService.selectRechargeCashByUserIdListPage(pageSearch);
		return new MobileBaseResponse(resultList);
	}
	
	/**
	 * 查询投资记录
	 * @param vo
	 * @param pageSearch
	 * @return
	 * @throws Exception
	 */
	@CheckToken
	public MobileBaseResponse investRecordListPage(PageUtilVO pageUtil) throws Exception {
		if(pageUtil.getPage() == null || pageUtil.getPage() < 1)
			return new MobileBaseResponse("page_fail", "页数不可小于1");
		PageSearch pageSearch = new PageSearch();
		QueryParameterVO vo = new QueryParameterVO();
		vo.setUserId(this.userTokenService.selectUserIdByToken(pageUtil.getToken()));
		pageSearch.setPage(pageUtil.getPage());
		pageSearch.setRows(pageUtil.getRows());
		pageSearch.setEntity(vo);
		List<BorrowTenderVO> resultList = this.tenderService.selectInvestRecordListPage(pageSearch);
		return new MobileBaseResponse(resultList);
	}
	
	/**
	 * 查询待收记录
	 * @param pageUtil
	 * @return
	 * @throws Exception
	 */
	@CheckToken
	public MobileBaseResponse collectionRecordListPage(PageUtilVO pageUtil)
			throws Exception {
		if(pageUtil.getPage() == null || pageUtil.getPage() < 1)
			return new MobileBaseResponse("page_fail", "页数不可小于1");
		PageSearch pageSearch = new PageSearch();
		QueryParameterVO vo = new QueryParameterVO();
		vo.setUserId(this.userTokenService.selectUserIdByToken(pageUtil.getToken()));
		pageSearch.setPage(pageUtil.getPage());
		pageSearch.setRows(pageUtil.getRows());
		pageSearch.setEntity(vo);
		List<CollectionTenderUserVO> resultList = this.borrowCollectionService.selectCollectionRecordListPage(pageSearch);
		return new MobileBaseResponse(resultList);
	}
	
}
