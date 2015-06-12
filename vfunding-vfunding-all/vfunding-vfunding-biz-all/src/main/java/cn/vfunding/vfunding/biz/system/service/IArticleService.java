package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import org.apache.http.entity.mime.MultipartEntityBuilder;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Article;

public interface IArticleService {

	/**
	 * @Description:查询文章
	 * @param status:文章状态;start:开始查询，默认0开始;size:查询数量
	 * siteId:94:最新动态   95:网站公告  85:媒体报道
	 * @author liuhuan
	 */
	List<Article> selectArticleByStatusCount(Integer siteId,Integer status, Integer start, Integer size);

	
	List<Article> selectArticleListPage(PageSearch pageSearch);
	
	Article selectByPrimaryKey(Integer id);
	
	int insert(Article record);
	
	int deleteByPrimaryKey(Integer id);
	
	int insertSelective(Article record);
	
	int updateByPrimaryKeySelective(Article record);
	
	int updateByPrimaryKeyWithBLOBs(Article record);
	
	int updateByPrimaryKey(Article record);

	/**
	 * @Description:
	 * @param size:大小
	 * @param siteId:108小微资讯
	 * @author liuhuan
	 */
	List<Article> selectHotArticle(int size,int[] siteIds);
	
	String uploadFile(MultipartEntityBuilder multipartEntity);
}
