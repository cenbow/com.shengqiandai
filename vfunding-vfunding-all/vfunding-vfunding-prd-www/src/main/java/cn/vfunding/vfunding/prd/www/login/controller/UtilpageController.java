package cn.vfunding.vfunding.prd.www.login.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;

import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.utils.ModelAndViewUtil;
import cn.vfunding.vfunding.biz.common.vo.ProfitCalJsonVO;
import cn.vfunding.vfunding.biz.common.vo.ProfitCalVO;
import cn.vfunding.vfunding.biz.system.model.Article;
import cn.vfunding.vfunding.biz.system.model.SeoRecordWithBLOBs;
import cn.vfunding.vfunding.biz.system.service.IArticleService;
import cn.vfunding.vfunding.biz.system.service.IProfitCalService;
import cn.vfunding.vfunding.biz.system.service.ISeoRecordService;
import cn.vfunding.vfunding.biz.user.model.UserFrom;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.user.service.IUserFromService;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.biz.userFrom.model.UserFromDic;
import cn.vfunding.vfunding.biz.userFrom.service.IUserFromDicService;
import cn.vfunding.vfunding.prd.www.vo.LaibangwoVO;

@Controller
@RequestMapping(value = { "news", "utilpage" })
public class UtilpageController {

	@Autowired
	private IArticleService articleService;
	@Autowired
	private IProfitCalService profitCalService;
	@Autowired
	private ISeoRecordService seoRecordService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserFromService userFromService;
	@Autowired
	private IUserFromDicService userFromDicService;

	/**
	 * 关于我们
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/aboutUs")
	public ModelAndView aboutUs(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/aboutUs");
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 团队建设
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/managerTeam")
	public ModelAndView ManagerTeam(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/managerTeam");
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 办公环境
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/officeEnvironment")
	public ModelAndView OfficeEnvironment(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/officeEnvironment");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 公告
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/websiteNotice")
	public ModelAndView websiteNotice(PageSearch pageSearch,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/websiteNotice");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", 1);
		map.put("siteId", 95);
		pageSearch.setRows(8);// 显示8条
		pageSearch.setEntity(map);
		// 网站公告
		List<Article> report = articleService.selectArticleListPage(pageSearch);
		PageResult<Article> reportResults = new PageResult<Article>();
		reportResults.setRows(report);
		reportResults.setTotal(pageSearch.getTotalResult());
		mv.addObject("reportResults", reportResults);
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 媒体报道
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/mediaReports")
	public ModelAndView mediaReports(PageSearch pageSearch,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/mediaReports");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteId", 85);
		map.put("status", 1);
		pageSearch.setRows(8);// 显示8条
		pageSearch.setEntity(map);
		// 媒体报道列表
		List<Article> medias = articleService.selectArticleListPage(pageSearch);
		PageResult<Article> mediasResults = new PageResult<Article>();
		mediasResults.setRows(medias);
		mediasResults.setTotal(pageSearch.getTotalResult());
		mv.addObject("mediasResults", mediasResults);
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 联系我们
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/contactUs")
	public ModelAndView contactUs(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/contactUs");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 招贤纳士
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/recruit")
	public ModelAndView recruit(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/recruit");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 平台原理
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/platTheory")
	public ModelAndView platTheory(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/platTheory");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 安全保障
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/safe")
	public ModelAndView safe(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/safe");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 法律声明
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/law")
	public ModelAndView law(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/law");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 监管报告
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/surpise")
	public ModelAndView surpise(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/surpise");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	@RequestMapping(value = "/articleAjaxList")
	@ResponseBody
	public PageResult<Article> articleAjaxList(PageSearch pageSearch,
			@RequestParam("type") int type) {
		PageResult<Article> results = new PageResult<Article>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteId", type);
		map.put("status", 1);
		pageSearch.setRows(8);// 显示8条
		pageSearch.setEntity(map);
		List<Article> articles = articleService
				.selectArticleListPage(pageSearch);
		results.setRows(articles);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * 详情页
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/selectArticleDetail/{id}")
	public ModelAndView selectArticleDetail(@PathVariable("id") int id,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/newsDetails");
		Article article = articleService.selectByPrimaryKey(id);
		mv.addObject("article", article);

		// 关键字
		try {
			List<SeoRecordWithBLOBs> record = seoRecordService
					.selectListByUrl(request.getRequestURL().toString()
							.split("id")[0]);
			if (EmptyUtil.isNotEmpty(record)) {
				ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
			}
		} catch (Exception e) {
		}
		return mv;
	}

	/**
	 * 帮助中心页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/helpCenter")
	public ModelAndView investQuestion(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/investQuestion");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	@RequestMapping(value = "/loanQuestion")
	public ModelAndView loanQuestion(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/loanQuestion");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	@RequestMapping(value = "/accountCenter")
	public ModelAndView accountCenter(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/accountCenter");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	@RequestMapping(value = "/websiteFee")
	public ModelAndView websiteFee(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/websiteFee");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	@RequestMapping(value = "/offenQuestion")
	public ModelAndView offenQuestion(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/offenQuestion");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	@RequestMapping(value = "/wordExplain")
	public ModelAndView wordExplain(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("utilPage/wordExplain");
		// 关键字
		List<SeoRecordWithBLOBs> record = seoRecordService
				.selectListByUrl(request.getRequestURL().toString());
		if (EmptyUtil.isNotEmpty(record)) {
			ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
		}
		return mv;
	}

	/**
	 * 小微资讯 108-小微新闻 109-小微攻略
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = { "/vNewsList/{type}/{page}" })
	public ModelAndView seoList(HttpServletRequest request,
			PageSearch pageSearch, @PathVariable Integer type) {
		ModelAndView mv = new ModelAndView("utilPage/seoList");
		String url = request.getRequestURL().toString();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("siteId", type == 108 ? 108 : type == 109 ? 109 : 1);

		pageSearch.setRows(4);// 显示4条
		pageSearch.setEntity(map);
		// 小微资讯
		List<Article> v = articleService.selectArticleListPage(pageSearch);
		PageResult<Article> article = new PageResult<Article>();
		article.setRows(v);
		article.setTotal(pageSearch.getTotalResult());
		mv.addObject("article", article);
		mv.addObject("page", pageSearch.getPage());
		mv.addObject("type", type == 108 ? 108 : type == 109 ? 109 : 1);

		// 热门内容
		int[] siteIds = new int[] {
				type == 108 ? 108 : type == 109 ? 109 : 108, 109 };
		List<Article> hotArticle = articleService.selectHotArticle(10, siteIds);
		mv.addObject("hotArticle", hotArticle);
		// 热门标签
		StringBuffer mark = new StringBuffer("");
		for (Article a : hotArticle) {
			if (EmptyUtil.isNotEmpty(a.getLittitle())) {
				mark.append(a.getLittitle());
			}
		}
		String[] temp = mark.toString().split(","); // 去重
		Set<String> marks = new HashSet<String>();
		for (String s : temp) {
			marks.add(s);
		}
		mv.addObject("marks", marks);
		// 关键字
		try {
			List<SeoRecordWithBLOBs> record = seoRecordService
					.selectListByUrl(url);
			if (EmptyUtil.isNotEmpty(record)) {
				ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
			}
		} catch (Exception e) {
		}
		return mv;
	}

	/**
	 * 标签搜索
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/search/{page}")
	public ModelAndView search(HttpServletRequest request,
			PageSearch pageSearch, String keyword) {
		ModelAndView mv = new ModelAndView("utilPage/seoList");
		String url = request.getRequestURL().toString();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("littitle", keyword);
		pageSearch.setRows(4);// 显示4条
		pageSearch.setEntity(map);
		// 小微资讯
		List<Article> v = articleService.selectArticleListPage(pageSearch);
		PageResult<Article> article = new PageResult<Article>();
		article.setRows(v);
		article.setTotal(pageSearch.getTotalResult());
		mv.addObject("article", article);
		mv.addObject("page", pageSearch.getPage());
		mv.addObject("keyword", keyword);
		// 热门内容
		int[] siteIds = new int[] { 108, 109 };
		List<Article> hotArticle = articleService.selectHotArticle(10, siteIds);
		mv.addObject("hotArticle", hotArticle);
		// 热门标签
		StringBuffer mark = new StringBuffer("");
		for (Article a : hotArticle) {
			if (EmptyUtil.isNotEmpty(a.getLittitle())) {
				mark.append(a.getLittitle());
			}
		}
		String[] temp = mark.toString().split(","); // 去重
		Set<String> marks = new HashSet<String>();
		for (String s : temp) {
			marks.add(s);
		}
		mv.addObject("marks", marks);

		// 关键字
		try {
			List<SeoRecordWithBLOBs> record = seoRecordService
					.selectListByUrl(url);
			if (EmptyUtil.isNotEmpty(record)) {
				ModelAndViewUtil.mvSeoUtil(mv, record.get(0));
			}
		} catch (Exception e) {
		}
		return mv;
	}

	/**
	 * 咨询详情
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/vNewsDetail/{id}")
	public ModelAndView seoDetail(HttpServletRequest request,
			@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("utilPage/seoDetail");
		// 详情
		Article article = articleService.selectByPrimaryKey(id);
		if (article == null) {
			return new ModelAndView("error/404");
		}
		mv.addObject("article", article);
		// 更新点击量
		article.setHits(article.getHits() + 1);
		articleService.updateByPrimaryKey(article);

		// 热门内容
		int[] siteIds = new int[] { 108, 109 };
		List<Article> hotArticle = articleService.selectHotArticle(10, siteIds);
		mv.addObject("hotArticle", hotArticle);
		// 热门标签
		StringBuffer mark = new StringBuffer("");
		for (Article a : hotArticle) {
			if (EmptyUtil.isNotEmpty(a.getLittitle())) {
				mark.append(a.getLittitle());
			}
		}
		String[] temp = mark.toString().split(","); // 去重
		Set<String> marks = new HashSet<String>();
		for (String s : temp) {
			marks.add(s);
		}
		mv.addObject("marks", marks);
		return mv;
	}

	/**
	 * app页面
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/app")
	public ModelAndView goAppPage() {
		ModelAndView mv = new ModelAndView("utilPage/app");
		return mv;
	}

	/**
	 * 收益计算器页面跳转
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/profitCal")
	public ModelAndView profitCal() {
		ModelAndView mv = new ModelAndView("utilPage/profitCal");
		return mv;
	}

	/**
	 * 收益计算
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/count")
	@ResponseBody
	public List<ProfitCalJsonVO> proficalCount(ProfitCalVO vo) {
		List<ProfitCalJsonVO> jsonVO = this.profitCalService
				.selectProfitCal(vo);
		return jsonVO;
	}

	/**
	 * cpi
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/cpi")
	public ModelAndView cpi() {
		ModelAndView mv = new ModelAndView("utilPage/cpi");
		return mv;
	}

	/**
	 * 身价
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/wealthCal")
	public ModelAndView wealthCal() {
		ModelAndView mv = new ModelAndView("utilPage/wealthCal");
		return mv;
	}

	/**
	 * 收益对比
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/earningsContrast")
	public ModelAndView earningsContrast() {
		ModelAndView mv = new ModelAndView("utilPage/earningsContrast");
		return mv;
	}

	/**
	 * 地图
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/webSiteMap")
	public ModelAndView webSiteMap() {
		ModelAndView mv = new ModelAndView("utilPage/webSiteMap");
		return mv;
	}

	/**
	 * 注册协议
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/regAgreement")
	public ModelAndView agreement() {
		ModelAndView mv = new ModelAndView("utilPage/regAgreement");
		return mv;
	}

	/**
	 * 借款介绍
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/loanRecommend")
	public ModelAndView loanRecommend() {
		ModelAndView mv = new ModelAndView("utilPage/loanRecommend");
		return mv;
	}

	/**
	 * flash集合
	 * 
	 * @return
	 */
	@RequestMapping(value = "/videoList")
	public ModelAndView vedio() {
		ModelAndView mv = new ModelAndView("utilPage/videoList");
		return mv;
	}

	/**
	 * flash1
	 * 
	 * @return
	 */
	@RequestMapping(value = "/video1")
	public ModelAndView video1() {
		ModelAndView mv = new ModelAndView("utilPage/video1-play");
		return mv;
	}

	/**
	 * flash2
	 * 
	 * @return
	 */
	@RequestMapping(value = "/video2")
	public ModelAndView video2() {
		ModelAndView mv = new ModelAndView("utilPage/video2-play");
		return mv;
	}

	/**
	 * flash3
	 * 
	 * @return
	 */
	@RequestMapping(value = "/video3")
	public ModelAndView video3() {
		ModelAndView mv = new ModelAndView("utilPage/video3-play");
		return mv;
	}

	/**
	 * jinzhizhu
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jinzhizhu")
	public ModelAndView jinzhizhu() {
		ModelAndView mv = new ModelAndView("utilPage/goldSpriter");
		return mv;
	}

	/**
	 * newOnLine
	 * 
	 * @return
	 */
	@RequestMapping(value = "/newOnLine")
	public ModelAndView newOnline() {
		ModelAndView mv = new ModelAndView("utilPage/newOnLine");
		return mv;
	}

	/**
	 * oumeiya
	 * 
	 * @return
	 */
	@RequestMapping(value = "/oumeiya")
	public ModelAndView oumeiya() {
		ModelAndView mv = new ModelAndView("utilPage/oumeiya");
		return mv;
	}

	/**
	 * obverser
	 * 
	 * @return
	 */
	@RequestMapping(value = "/obverser")
	public ModelAndView obverser() {
		ModelAndView mv = new ModelAndView("utilPage/obverser");
		return mv;
	}

	/**
	 * tyj
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tyj")
	public ModelAndView tyj() {
		ModelAndView mv = new ModelAndView("utilPage/tyj");
		return mv;
	}

	@RequestMapping(value = "/toPage/{pageName}")
	public ModelAndView toPage(@PathVariable("pageName") String pageName,
			HttpSession session, String source, HttpServletRequest request) {
		String thirdSource = "";
		if (session.getAttribute("thirdSource") != null) {
			thirdSource = session.getAttribute("thirdSource")
					.toString();
		}
		UserFromDic ufd = this.userFromDicService.selectByFromNo(thirdSource);
		if(ufd != null){
			if (pageName.equals(ufd.getKeywords())) {
				if (CheckAgent(request.getHeader("user-agent"))) {
					return new ModelAndView("redirect:"+ufd.getWeiToUrl()+"?thirdSource="+thirdSource);
				}
			}
		}
		ModelAndView mv = new ModelAndView("utilPage/" + pageName + "");
		if (!StringUtils.isEmpty(source)) {
			session.setAttribute("thirdSource", source);
		}
		return mv;
	}

	@RequestMapping(value = "/toPageAjax/{pageName}")
	public ModelAndView toPageAjax(@PathVariable("pageName") String pageName) {
		ModelAndView mv = new ModelAndView("utilPage/" + pageName + "");
		return mv;
	}

	/**
	 * 用户 第三方平台 存放session信息
	 */
	@RequestMapping(value = "/thirdPlat")
	public ModelAndView thirdPlatSession(HttpServletRequest request,
			String source, String sn) {
		ModelAndView mv = new ModelAndView();
		String fromNo = source.trim();
		UserFromDic ufd = userFromDicService.selectByFromNo(fromNo);
		if(ufd == null){
			mv.setViewName("redirect:/");
		}else{
			request.getSession().setAttribute("thirdSource", ufd.getFromNo());
			request.getSession().setAttribute("thirdSn", sn);
			if(EmptyUtil.isNotEmpty(source)){
				mv.setViewName("redirect:"+ufd.getToUrl()+"?source="+source);
			}else{
				mv.setViewName("redirect:"+ufd.getToUrl());
			}
		}
		
//		request.getSession().setAttribute("thirdSource", source);
//		request.getSession().setAttribute("thirdSn", sn);
//		if (source.equals("1")) {// 来帮我
//			mv.setViewName("redirect:/utilpage/thirdPass");
//		} else if (source.equals("2")) {// jj斗地主
//			mv.setViewName("redirect:/utilpage/toPage/vf-zhuoluye");
//		} else if (source.equals("3")) {// 微财富推广
//			mv.setViewName("redirect:/utilpage/toPage/vf-zhuoluye");
//		} else if (source.equals("4")) {// 微财富EDM
//			mv.setViewName("redirect:/utilpage/toPage/vf-zhuoluye");
//		} else if (source.equals("5")) {// 搜狐
//			mv.setViewName("redirect:/utilpage/toPage/vf-zhuoluye");
//		} else if (source.equals("6")) {// 搜房网
//			mv.setViewName("redirect:/utilpage/toPage/vf-zhuoluye");
//		} else if (source.equals("7")) {// 微财富基金广告
//			mv.setViewName("redirect:/utilpage/toPage/vf-zhuoluye");
//		} else if (source.equals("8")) {// 酒仙网
//			mv.setViewName("redirect:/utilpage/toPage/vf-zhuoluye");
//		} else if (source.equals("9")) {// 零距离
//			mv.setViewName("redirect:/utilpage/toPage/vf-zhuoluye");
//		} else if (source.equals("10")) {// 新浪扶翼
//			mv.setViewName("redirect:/utilpage/toPage/vf-zhuoluye");
//		} else {
//			
//		}
		
		return mv;
	}
	@RequestMapping(value = "/thirdPass")
	public String thirdPass(HttpSession session){
		session.invalidate();
		return "error/404";
	}

	/**
	 * 用户 第三方平台 存放session信息
	 */
	@RequestMapping(value = "/userStatus")
	@ResponseBody
	public LaibangwoVO userStatus(Integer userId) {
		LaibangwoVO vo = new LaibangwoVO();
		UserWithBLOBs user = this.userService.selectByPrimaryKey(userId);
		UserFrom uf = this.userFromService.selectByUserId(userId);
		if (uf != null) {
			vo.setUserLevel(1);
			vo.setSn(uf.getFromUrl());
		}
		if (user != null && user.getRealStatus() != null
				&& user.getRealStatus().equals("1")) {
			vo.setUid(user.getUserId());
			vo.setUserLevel(2);
			vo.setTime(DateUtil.getStringDateByLongDate(
					new Long(user.getAddtime()), "yyyy-MM-dd HH:mm:ss"));
		}
		return vo;
	}

	private String[] keywords = { "Android", "iPhone", "iPod", "iPad",
			"Windows Phone", "MQQBrowser" };

	private boolean CheckAgent(String agent) {
		boolean flag = false;
		// 排除 Windows 桌面系统
		if (agent != null
				&& !agent.contains("Windows NT")
				|| (agent != null && agent.contains("Windows NT") && agent
						.contains("compatible; MSIE 9.0;"))) {
			// 排除 苹果桌面系统
			if (!agent.contains("Windows NT") && !agent.contains("Macintosh")) {
				for (String item : keywords) {
					if (agent.contains(item)) {
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}
}
