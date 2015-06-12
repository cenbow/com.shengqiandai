package cn.vfunding.vfunding.biz.common.utils;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.StringUtil;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.service.IAccountService;
import cn.vfunding.vfunding.biz.common.vo.QueryParameterVO;
import cn.vfunding.vfunding.biz.common.vo.SearchVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;
import cn.vfunding.vfunding.biz.system.model.SeoRecordWithBLOBs;

/**
 * 页面参数辅助工具
 * 
 * @author liuyijun
 * 
 */
public class ModelAndViewUtil {

	/**
	 * 添加用户账户信息到页面
	 * 
	 * @param mv
	 *            2014年4月26日 liuyijun
	 */
	public static void addAccountToView(ModelAndView mv,
			IAccountService accountService) {
		createModelAndViewInfo(mv, accountService);
	}

	/**
	 * 创建视图并添加账户信息
	 * 
	 * @param accountService
	 * @param forwardUrl
	 *            需要转向的页面
	 * @return 2014年5月8日 liuyijun
	 */
	public static ModelAndView createModelAndViewAndAddAccountToView(
			IAccountService accountService, String forwardUrl) {
		ModelAndView mv = new ModelAndView(forwardUrl);
		/*
		 * Account account = accountService.selectByUserId(UserSession
		 * .getLoginUserId()); if (EmptyUtil.isNotEmpty(account)) {
		 * mv.addObject("account", account); }
		 */
		createModelAndViewInfo(mv, accountService);
		return mv;
	}

	private static void createModelAndViewInfo(ModelAndView mv,
			IAccountService accountService) {
		Account account = accountService.selectByUserId(UserSession
				.getLoginUserId());
		if (EmptyUtil.isNotEmpty(account)) {
			mv.addObject("account", account);
		}

		int completeNum = 0;
		if (EmptyUtil.isNotEmpty(UserSession.getUserSession())) {
			if (EmptyUtil.isNotEmpty(UserSession.getUserSession()
					.getRealStatus())
					&& UserSession.getUserSession().getRealStatus().equals("1")) {
				completeNum += 1;
			}

			if (EmptyUtil.isNotEmpty(UserSession.getUserSession()
					.getEmailStatus())
					&& UserSession.getUserSession().getEmailStatus()
							.equals("1")) {
				completeNum += 1;
			}

			if (EmptyUtil
					.isNotEmpty(UserSession.getUserSession().getQuestion())
					&& EmptyUtil.isNotEmpty(UserSession.getUserSession()
							.getAnswer())) {
				completeNum += 1;
			}

			if (EmptyUtil.isNotEmpty(UserSession.getUserSession().getPhone())) {
				completeNum += 1;
			}

			if (!UserSession.getUserSession().getPassword()
					.equals(UserSession.getUserSession().getPayPassword())) {
				completeNum += 1;
			}
			// 安全等级
			if (0 <= completeNum && completeNum <= 2) {
				mv.addObject("safeStr", "低");
			}
			if (2 < completeNum && completeNum <= 4) {
				mv.addObject("safeStr", "中");
			}
			if (completeNum == 5) {
				mv.addObject("safeStr", "高");
			}

			mv.addObject("percentStr", StringUtil.getPercent(
					completeNum > 0 ? completeNum : 1, 5d));// 安全百分比
		}
	}

	/**
	 * 添加查询参数
	 * 
	 * @param map
	 * @param start
	 * @param end
	 * @throws Exception
	 *             2014年4月29日 liuyijun
	 */
	public static void addSearchValue(Map<String, Object> map, String start,
			String end) throws Exception {
		String startDate = "";
		String endDate = "";
		if (EmptyUtil.isEmpty(start)) {
			startDate = DateUtil.parseDate(new Date()) + " 00:00:00";
		} else {
			startDate = start + " 00:00:00";
		}
		if (EmptyUtil.isEmpty(end)) {
			endDate = DateUtil.parseDate(new Date()) + " 23:59:59";
		} else {
			endDate = end + " 23:59:59";
		}
		map.put("start", DateUtil.getLongTimeByStringValue(startDate));
		map.put("end", DateUtil.getLongTimeByStringValue(endDate));
	}

	/**
	 * 添加查询参数
	 * 
	 * @param map
	 * @param start
	 * @param end
	 * @throws Exception
	 *             2014年4月29日 liuyijun
	 */
	public static void addQueryTimeVO(QueryParameterVO vo) throws Exception {
		String startDate = vo.getStartTime();
		String endDate = vo.getEndTime();
		if (EmptyUtil.isEmpty(startDate)) {
			startDate = DateUtil.parseDate(new Date()) + " 00:00:00";
		} else {
			startDate = startDate + " 00:00:00";
		}
		if (EmptyUtil.isEmpty(endDate)) {
			endDate = DateUtil.parseDate(new Date()) + " 23:59:59";
		} else {
			endDate = endDate + " 23:59:59";
		}
		vo.setStartTime(DateUtil.getLongTimeByStringValue(startDate).toString());
		vo.setEndTime(DateUtil.getLongTimeByStringValue(endDate).toString());
	}
	
	public static void addSearchTimeVO(SearchVO vo) throws Exception {
		String startDate = vo.getStartTime();
		String endDate = vo.getEndTime();
		if (EmptyUtil.isNotEmpty(startDate)) {
			startDate = startDate + " 00:00:00";
			vo.setStartTime(DateUtil.getLongTimeByStringValue(startDate).toString());
		}
		if (EmptyUtil.isNotEmpty(endDate)) {
			endDate = endDate + " 23:59:59";
			vo.setEndTime(DateUtil.getLongTimeByStringValue(endDate).toString());
		}
	}

	/**
	 * 获取访问者IP地址
	 * 
	 * @param request
	 * @return
	 * @author liuyijun 2014年4月10日
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		
		if(ip.contains(",")){
			String [] ips=ip.split(",");
			ip=ips[ips.length-2];
		}
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}

	/**
	 * 创建项目外部访问的根路径(http://localhost:8001)
	 * 
	 * @param request
	 * @return 2014年5月14日 liuyijun
	 */
	public static String createBaseUrl(HttpServletRequest request) {
		 
		//StringBuffer url = request.getRequestURL();
		///String path = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
		//String path="http://www.vstate.cn";
        String path="http://www.vfunding.cn";
		return path;
	}

	/**
	 * seo关键字公共工具
	 * @return
	 * @author liuhuan
	 */
	public static ModelAndView mvSeoUtil(ModelAndView mv,SeoRecordWithBLOBs record){
		mv.addObject("title", record.getTitle());
		mv.addObject("description", record.getDescription());
		mv.addObject("keywords", record.getKeywords());
		mv.addObject("charset", record.getCharset());
		return mv;
	}
}
