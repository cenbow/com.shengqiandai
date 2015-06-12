package cn.vfunding.vfunding.prd.www.week.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.utils.mvc.BaseController;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowTenderService;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionResultVO;
import cn.vfunding.vfunding.biz.common.vo.UserTenderActionVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.week.model.WeekTender;
import cn.vfunding.vfunding.biz.week.service.IWeekService;
import cn.vfunding.vfunding.biz.week.service.IWeekTenderService;
import cn.vfunding.vfunding.biz.week.vo.HoldingAssetsVO;
import cn.vfunding.vfunding.biz.week.vo.UserTenderWeekActionResultVO;
import cn.vfunding.vfunding.biz.week.vo.UserTenderWeekActionVO;

@Controller
@RequestMapping(value = "/weekTender")
public class WeekTenderController extends BaseController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IWeekService weekService;
	@Autowired
	private IBorrowTenderService borrowTenderService;

	@Autowired
	private IWeekTenderService weekTenderService;

	@RequestMapping("/weekTenderListAjax")
	@ResponseBody
	public PageResult<WeekTender> borrowListAjax(Integer weekId,
			PageSearch pageSearch) throws Exception {
		PageResult<WeekTender> result = new PageResult<WeekTender>();
		WeekTender wt = new WeekTender();
		wt.setWeekId(weekId);
		pageSearch.setEntity(wt);
		List<WeekTender> weekTenderList = this.weekTenderService
				.selectWeekTenderListPage(pageSearch);
		result.setTotal(pageSearch.getTotalResult());
		result.setRows(weekTenderList);
		return result;
	}

	@RequestMapping(value = "/tenderAction")
	@ResponseBody
	public UserTenderActionResultVO tenderAction(
			HttpServletRequest request, UserTenderActionVO vo) {
		UserTenderActionResultVO resultVO = new UserTenderActionResultVO();
		vo.setTenderType("week"); 
		resultVO.setTenderType("week");
		if (UserSession.getUserSession() != null) {
			if (DigestUtils.md5Hex(vo.getPaypassword()).equals(
					UserSession.getUserSession().getPayPassword())) {
				vo.setUserType(UserSession.getUserSession().getTypeId());
				vo.setUserId(UserSession.getUserSession().getUserId());
				vo.setUserip(ModelAndViewUtil.getIpAddr(request));
				//resultVO = this.weekTenderService.insertWeekTender(userVO);
				resultVO = this.borrowTenderService.userTenderAction(vo);
			} else {
				throw new BusinessException("8005011", "支付密码错误");
			}
		} else {
			throw new BusinessException("8005011", "登陆后才可投资哦");
		}
		return resultVO;
	}

	/**
	 * 查询个人周应宝下持有资产与利息
	 * @return
	 */
	@RequestMapping("/holdingAssets")
	public ModelAndView holdingAssets() {
		Integer userId = UserSession.getLoginUserId();
		ModelAndView mv = new ModelAndView("week/holdingAssets");
		ModelAndViewUtil.addAccountToView(mv, this.accountService);
		HoldingAssetsVO hav = weekTenderService
				.selectHoldingAssetsByUserId(userId);
		if(hav == null){
			hav = new HoldingAssetsVO();
		}
		mv.addObject("assets", hav);
		return mv;
	}

	/**
	 * 查询个人周盈宝投资记录
	 * @param pageSearch
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/assetsListAjax")
	@ResponseBody
	public PageResult<WeekTender> assetsListAjax(
			PageSearch pageSearch,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end) throws Exception {
		PageResult<WeekTender> result = new PageResult<WeekTender>();
		Integer userId = UserSession.getLoginUserId();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("start", start);
		map.put("end", end);
		pageSearch.setEntity(map);
		List<WeekTender> wtList = weekTenderService.selectByUserId(pageSearch);
		for(WeekTender wt : wtList){
			wt.setWeek(weekService.selectByPrimaryKey(wt.getWeekId()));
		}
		result.setRows(wtList);
		result.setTotal(pageSearch.getTotalResult());
		return result;
	}

}
