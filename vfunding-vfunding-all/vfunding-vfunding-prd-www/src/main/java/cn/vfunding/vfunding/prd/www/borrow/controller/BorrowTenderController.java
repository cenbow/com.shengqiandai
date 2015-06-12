package cn.vfunding.vfunding.prd.www.borrow.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.server.interceptors.NeedSession;
import cn.vfunding.common.framework.utils.beans.BeanUtils;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.common.framework.utils.rest.annotation.RestDescription;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowCollectionService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.borrowMobile.service.IBorrowTenderMobileService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.BorrowTenderVO;
import cn.vfunding.vfunding.biz.common.vo.CollectionTenderUserVO;
import cn.vfunding.vfunding.biz.common.vo.QueryParameterVO;
import cn.vfunding.vfunding.biz.common.vo.SuccessTenderVO;
import cn.vfunding.vfunding.biz.common.vo.TenderBorrowVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.common.module.activemq.message.InvestMessage;
import cn.vfunding.vfunding.prd.www.vo.UserInvestVO;

@Controller
@RequestMapping(value = { "borrowTender", "invest" })
public class BorrowTenderController extends BaseController {

	@Autowired
	private IBorrowTenderService tenderService;

	@Autowired
	private IBorrowCollectionService borrowCollectionService;

	@Autowired
	private IBorrowTenderMobileService borrowTenderMobileService;

	@Autowired
	private IBorrowService borrowService;

	@Autowired
	private IAccountService accountService;

	/**
	 * php端在投资成功后调用的接口
	 * 
	 * @param message
	 */

	@RestDescription("php端在投资成功后调用的接口")
	@RequestMapping(value = "/phpTender")
	@ResponseBody
	public void phpTender(UserInvestVO userInvest) {
		userInvest.setInvestTime(new Date());
		InvestMessage message = BeanUtils.copyBean(userInvest, InvestMessage.class);
		this.tenderService.afterPhpTender(message);
	}

	@RequestMapping(value = "/tenderAction")
	@ResponseBody
	public UserTenderActionResultVO tenderAction(HttpServletRequest request, UserTenderActionVO userVO) {
		UserTenderActionResultVO resultVO = new UserTenderActionResultVO();
		if (UserSession.getUserSession() != null) {
			if (DigestUtils.md5Hex(userVO.getPaypassword()).equals(UserSession.getUserSession().getPayPassword())) {
				Borrow borrow = this.borrowService.selectById(userVO.getBorrowId());
				if (borrow.getStatus() == 1) {
					userVO.setUserType(UserSession.getUserSession().getTypeId());
					userVO.setUserId(UserSession.getUserSession().getUserId());
					userVO.setUserip(ModelAndViewUtil.getIpAddr(request));
					//resultVO = this.tenderService.insertUserTenderAction(userVO);
					resultVO = this.tenderService.userTenderAction(userVO);
				} else if (borrow.getStatus() == 10) {
					TenderBorrowVO tenderInfo = new TenderBorrowVO();
					tenderInfo.setUserId(UserSession.getUserSession().getUserId());
					tenderInfo.setBorrowId(userVO.getBorrowId());
					tenderInfo.setMoney(userVO.getPayMoney());
					tenderInfo.setPaypassword(userVO.getPaypassword());
					SuccessTenderVO result = this.borrowService.updateTenderFeelBorrow(tenderInfo, userVO.getUserip());
					if (result.getResult().equals("投资成功。")) {
						resultVO.setPayMoney(result.getAccount());
						if (result.getAccount().doubleValue() == userVO.getPayMoney().doubleValue()) {
							resultVO.setStatus(1);
						} else if (userVO.getPayMoney().doubleValue() > 0) {
							resultVO.setStatus(2);
						} else {
							resultVO.setStatus(3);
						}
						return resultVO;
					} else {
						throw new BusinessException("8005011", result.getResult());
					}
				} else {
					throw new BusinessException("8005011", "该标不是可投的标.");
				}
			} else {
				throw new BusinessException("8005011", "支付密码错误");
			}
		} else {
			throw new BusinessException("8005011", "登陆后才可投资哦");
		}
		return resultVO;
	}

	/**
	 * @Description:投资记录页面
	 * @return
	 * @author liuhuan
	 * @throws Exception
	 */
	@NeedSession(returnUrl = "/borrowTender/investRecord")
	@RequestMapping(value = "/investRecord")
	public ModelAndView investRecord(PageSearch pageSearch, QueryParameterVO vo) throws Exception {
		ModelAndView mv = new ModelAndView("user/investRecord");
		// 账户信息
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		// 默认查询近一周
		vo.setStartTime(DateUtil.getNextDay(-7, "yyyy-MM-dd"));// 前7天
		vo.setStatus(vo.getStatus() == null ? 1 : vo.getStatus());// 默认已成功项目
		PageResult<BorrowTenderVO> result = this.investRecordListPage(vo, pageSearch);
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		mv.addObject("page", result);
		return mv;
	}

	/**
	 * 投标列表
	 * 
	 * @throws Exception
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "/borrowTender/investRecord")
	@RequestMapping(value = "/investRecordAjax")
	@ResponseBody
	public PageResult<BorrowTenderVO> investRecordAjax(QueryParameterVO vo, PageSearch pageSearch) throws Exception {
		PageResult<BorrowTenderVO> result = this.investRecordListPage(vo, pageSearch);
		return result;
	}

	public PageResult<BorrowTenderVO> investRecordListPage(QueryParameterVO vo, PageSearch pageSearch) throws Exception {
		vo.setUserId(UserSession.getLoginUserId());
		PageResult<BorrowTenderVO> result = new PageResult<BorrowTenderVO>();
		List<BorrowTenderVO> resultList = null;
		pageSearch.setEntity(vo);
		ModelAndViewUtil.addQueryTimeVO(vo);
		resultList = this.tenderService.selectInvestRecordListPage(pageSearch);
		result.setRows(resultList);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	/**
	 * 待收列表
	 * 
	 * @throws Exception
	 * @author liuhuan
	 */
	@NeedSession(returnUrl = "/borrowTender/investRecord")
	@RequestMapping(value = "/collectionRecordAjax")
	@ResponseBody
	public PageResult<CollectionTenderUserVO> collectionRecordAjax(QueryParameterVO vo, PageSearch pageSearch)
			throws Exception {
		PageResult<CollectionTenderUserVO> result = this.collectionRecordListPage(vo, pageSearch);
		return result;
	}

	public PageResult<CollectionTenderUserVO> collectionRecordListPage(QueryParameterVO vo, PageSearch pageSearch)
			throws Exception {
		vo.setUserId(UserSession.getLoginUserId());
		PageResult<CollectionTenderUserVO> result = new PageResult<CollectionTenderUserVO>();
		List<CollectionTenderUserVO> resultList = null;
		pageSearch.setEntity(vo);
		ModelAndViewUtil.addQueryTimeVO(vo);
		resultList = this.borrowCollectionService.selectCollectionRecordListPage(pageSearch);
		result.setRows(resultList);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

	@RequestMapping(value = "/{str}")
	public ModelAndView changeUrl(@PathVariable("str") String str) {
		String s = str.substring(1, str.length());
		ModelAndView mv = new ModelAndView("redirect:/borrow/borrowDetail?id=" + Integer.parseInt(s));
		return mv;
	}
}
