package cn.vfunding.vfunding.prd.bms.user.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.easyui.page.utils.PageResult;
import cn.vfunding.common.framework.exception.BusinessException;
import cn.vfunding.common.framework.server.EmployeeSession;
import cn.vfunding.common.framework.server.interceptors.NeedLogger;
import cn.vfunding.common.framework.server.interceptors.ParentSecurity;
import cn.vfunding.common.framework.server.interceptors.UnSecurity;
import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.beans.ImageUtil;
import cn.vfunding.common.framework.utils.http.MultipartEntityBuilderUtil;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Article;
import cn.vfunding.vfunding.biz.system.service.IArticleService;
import cn.vfunding.vfunding.biz.system.service.IAttestationService;
import cn.vfunding.vfunding.prd.bms.utils.UserAuthFilter;

@Controller
@RequestMapping(value = "/system/article")
public class ArticleController {
	@Autowired
	private IArticleService articleService;

	/**
	 * 转向文章列表页
	 * 
	 * @author liuhuan
	 */
	@RequestMapping(value = "/articleListPage")
	public ModelAndView articleListPage() {
		ModelAndView mv = new ModelAndView("webpage/article/articleList");
		boolean canEdit = UserAuthFilter.isPass("/system/article/addArticle");
		if (canEdit) {
			mv.addObject("canEdit", canEdit);
		}
		boolean canDelete = UserAuthFilter.isPass("/system/article/delete");
		mv.addObject("canDelete", canDelete);
		return mv;
	}

	/**
	 * 管理文章列表
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity("/system/article/articleListPage")
	@RequestMapping(value = "/articleList")
	@ResponseBody
	public PageResult<Article> articleList(PageSearch pageSearch, Integer siteId) {
		PageResult<Article> results = new PageResult<Article>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("siteId", siteId);
		pageSearch.setEntity(map);
		List<Article> articles = articleService
				.selectArticleListPage(pageSearch);
		results.setRows(articles);
		results.setTotal(pageSearch.getTotalResult());
		return results;
	}

	/**
	 * 转向添加新文章or编辑文章 没传id：为新增；传入id为更新
	 * 
	 * @author liuhuan
	 */
	@ParentSecurity("/system/article/articleListPage")
	@RequestMapping(value = "/addArticlePage")
	public ModelAndView addArticlePage(Integer id) {
		ModelAndView mv = new ModelAndView("webpage/article/addArticle");
		boolean type = false;
		if (id != null && id > 0) {// update
			type = true;
			Article article = articleService.selectByPrimaryKey(id);
			mv.addObject("article", article);

			if (EmptyUtil.isNotEmpty(article.getLittitle())) {
				try {
					mv.addObject("mark1", article.getLittitle().split(",")[0]);
					mv.addObject(
							"mark2",
							article.getLittitle().split(",").length >= 2 ? article
									.getLittitle().split(",")[1] : "");
					mv.addObject(
							"mark3",
							article.getLittitle().split(",").length >= 3 ? article
									.getLittitle().split(",")[2] : "");
				} catch (Exception e) {
					throw new BusinessException("8005011", "文章标签截取错误");
				}
			}

		} else {
			type = false;
		}
		mv.addObject("type", type);
		return mv;
	}

	@Autowired
	private IAttestationService attestationService;

	/**
	 * 添加新文章 type:true更新；false新增
	 * 
	 * @author liuhuan
	 * @throws IOException
	 */
	@NeedLogger(desc = "修改文章")
	@RequestMapping(value = "/addArticle")
	@ResponseBody
	public Json addArticle(Article article, HttpServletRequest request,
			MultipartHttpServletRequest multipartRequest, boolean type,
			String mark1, String mark2, String mark3) throws IOException {
		Json j = new Json();
		article.setUserId(EmployeeSession.getEmpSessionEmpId());
		int i = 0;

		MultipartFile multipartFile = multipartRequest.getFile("pic");
		MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil
				.createMultipartEntityBuilderByMultipartFile(multipartFile);

		String picPath = null;
		if (EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize() != 0) {
			picPath = this.attestationService
					.uploadAttestation(multipartEntity);
			if (article.getSiteId() == 85) {
				article.setLitpic(picPath);
			}
		}

		article.setLittitle((EmptyUtil.isNotEmpty(mark1.trim()) ? (mark1 + ",")
				: "")
				+ (EmptyUtil.isNotEmpty(mark2.trim()) ? (mark2 + ",") : "")
				+ (EmptyUtil.isNotEmpty(mark3.trim()) ? (mark3 + ",") : ""));
		if (type) {
			i = articleService.updateByPrimaryKeySelective(article);
		} else {
			article.setAddip(request.getRemoteAddr());
			article.setAddtime(DateUtil.getTime());
			i = articleService.insertSelective(article);
		}
		if (i > 0) {
			j.setMsg("1");
			j.setSuccess(true);
		} else {
			j.setMsg("-1");
		}
		return j;
	}

	@NeedLogger(desc = "删除文章")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Json delete(Integer id) throws IOException {
		Json j = new Json();
		int i = articleService.deleteByPrimaryKey(id);
		if (i > 0) {
			j.setSuccess(true);
		}
		return j;
	}

	@UnSecurity
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public Json uploadFile(MultipartHttpServletRequest multipartRequest)
			throws Exception {
		Json j = new Json();
		MultipartFile multipartFile = multipartRequest.getFile("files");
		if (EmptyUtil.isNotEmpty(multipartFile) && multipartFile.getSize() > 0) {
			MultipartEntityBuilder multipartEntity = MultipartEntityBuilderUtil
					.createMultipartEntityBuilderByMultipartFile(multipartFile);
			String fileId = this.articleService.uploadFile(multipartEntity);
			j.setSuccess(true);
			if (ImageUtil.isImage(fileId.substring(fileId.indexOf(".") + 1))) {
				j.setObj("/ori/" + fileId);
			} else {
				j.setObj("/downloadFile/" + fileId);
			}

		}
		return j;
	}

	public static void main(String[] args) {

	}
}
