package cn.vfunding.vfunding.biz.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.dao.ArticleMapper;
import cn.vfunding.vfunding.biz.system.model.Article;
import cn.vfunding.vfunding.biz.system.service.IArticleService;

@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	
	/**
	 * 文件服务器
	 */
	@Resource(name = "fileRestInvokerFactory")
	private RestInvokerFactory fileRestInvokerFactory;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return articleMapper.deleteByPrimaryKey(id);
	}

	/**
	 * @Description:查询文章
	 * @param status:文章状态;start:开始查询，默认0开始;size:查询数量
	 * siteId:94:最新动态   95:网站公告
	 * @author liuhuan
	 */
	@Override
	public List<Article> selectArticleByStatusCount(Integer siteId,Integer status, Integer start, Integer size) {
		return articleMapper.selectArticleByStatusCount(siteId,status, start, size);
	}

	@Override
	public List<Article> selectArticleListPage(PageSearch pageSearch) {
		return articleMapper.selectArticleListPage(pageSearch);
	}

	@Override
	public Article selectByPrimaryKey(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int insert(Article record) {
		return articleMapper.insert(record);
	}

	@Override
	public int insertSelective(Article record) {
		return articleMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Article record) {
		return articleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(Article record) {
		return articleMapper.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Article record) {
		return articleMapper.updateByPrimaryKey(record);
	}

	/**
	 * @Description:
	 * @param size:大小
	 * @param siteId:108小微资讯,109小微攻略
	 * @author liuhuan
	 */
	@Override
	public List<Article> selectHotArticle(int size,int[] siteIds) {
		return articleMapper.selectHotArticle(size,siteIds);
	}

	@Override
	public String uploadFile(MultipartEntityBuilder multipartEntity) {
		if (EmptyUtil.isNotEmpty(multipartEntity)) {
			String fileId = this.fileRestInvokerFactory.getRestInvoker()
					.postFiles("upload?from=vfunding_bms", multipartEntity);
			return fileId;
		}
		return null;
	}

	
}
