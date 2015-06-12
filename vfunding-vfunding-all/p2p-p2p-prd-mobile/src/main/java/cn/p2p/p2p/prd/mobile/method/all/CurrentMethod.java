package cn.p2p.p2p.prd.mobile.method.all;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.current.model.Current;
import cn.p2p.p2p.biz.current.service.ICurrentRedeemService;
import cn.p2p.p2p.biz.current.service.ICurrentService;
import cn.p2p.p2p.biz.current.service.ICurrentTenderService;
import cn.p2p.p2p.biz.current.vo.UserCurrentAccountVO;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.p2p.p2p.mobile.interceptors.CheckToken;
import cn.p2p.p2p.prd.mobile.method.request.vo.AccountCashVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;
import cn.p2p.p2p.prd.mobile.method.request.vo.PageUtilVO;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.usertoken.service.IUserTokenService;

@Service("currentMethod")
public class CurrentMethod extends BaseController {

	@Autowired
	private ICurrentService currentService;

	@Autowired
	private ICurrentTenderService currentTenderService;

	@Autowired
	private IUserTokenService userTokenService;

	@Autowired
	private ICurrentRedeemService currentRedeemService;

	@Autowired
	private AccountCashMethod accountCashMethod;

	/**
	 * current列表查询
	 * 
	 * @param vo
	 * @param pageSearch
	 * @return
	 * @throws Exception
	 */
	public MobileBaseResponse currentList(PageUtilVO pageUtilVO) throws Exception {
		if (pageUtilVO.getPage() == null || pageUtilVO.getPage() < 1)
			return new MobileBaseResponse("page_fail", "页数不可小于1");
		PageSearch pageSearch = new PageSearch();
		pageSearch.setRows(pageUtilVO.getRows());
		pageSearch.setPage(pageUtilVO.getPage());
		List<Current> currentList = this.currentService.selectCurrentPageList(pageSearch);
		return new MobileBaseResponse(currentList);
	}

	/**
	 * current详情
	 * 
	 * @param vo
	 * @param pageSearch
	 * @return
	 * @throws Exception
	 */
	public MobileBaseResponse currentDetail(GeneralRequestVO generalRequest) throws Exception {
		if (generalRequest.getId() == null || generalRequest.getId() < 0) {
			PageSearch pageSearch = new PageSearch();
			pageSearch.setRows(1);
			pageSearch.setPage(1);
			List<Current> currentList = this.currentService.selectCurrentPageList(pageSearch);
			if (!currentList.isEmpty()) {
				generalRequest.setId(currentList.get(0).getCurrentId());
			} else {
				return new MobileBaseResponse("faid", "暂无活期标的");
			}
		}
		Current current = this.currentService.selectByPrimaryKey(generalRequest.getId());
		return new MobileBaseResponse(current);
	}
	@CheckToken
	public MobileBaseResponse tenderCurrentAction(GeneralRequestVO generalRequest) {
		if (generalRequest.getId() == null || generalRequest.getId() < 1)
			return new MobileBaseResponse("borrowId_fail", "标的编号不正确");
		if (generalRequest.getMoney() == null || generalRequest.getMoney().doubleValue() < 0)
			return new MobileBaseResponse("paymoney_fail", "投资金额不正确");
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		UserCurrentAccountVO vo = new UserCurrentAccountVO();
		vo.setCurrentId(generalRequest.getId());
		vo.setMoney(generalRequest.getMoney());
		vo.setUserip(generalRequest.getOsType());
		vo.setUserId(userId);
		return this.currentTenderService.UserCurrentTender(vo);
	}
	@CheckToken
	public MobileBaseResponse currentRedeem(GeneralRequestVO generalRequest) {
		if (generalRequest.getMoney() == null || generalRequest.getMoney().doubleValue() < 0)
			return new MobileBaseResponse("paymoney_fail", "赎回金额不正确");
		if (generalRequest.getStatus() == null || generalRequest.getStatus()>1)
			return new MobileBaseResponse("paymoney_fail", "操作类型错误");
		UserCurrentAccountVO vo = new UserCurrentAccountVO();
		Integer userId = this.userTokenService.selectUserIdByToken(generalRequest.getToken());
		vo.setMoney(generalRequest.getMoney());
		vo.setUserip(generalRequest.getOsType());
		vo.setUserId(userId);
		MobileBaseResponse res = this.currentRedeemService.userRedeem(vo);
		if (res.getResponseCode().equals("success") && generalRequest.getStatus() == 1) {
			AccountCashVO cashVO = new AccountCashVO();
			cashVO.setToken(generalRequest.getToken());
			cashVO.setMoney(generalRequest.getMoney());
			cashVO.setIsNotPayPassword(1);
			this.accountCashMethod.applyCash(cashVO);
		}
		return res;
	}
}
