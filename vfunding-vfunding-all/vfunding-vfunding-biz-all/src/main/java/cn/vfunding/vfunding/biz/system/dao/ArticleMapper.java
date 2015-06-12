package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Article;

public interface ArticleMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Article record);
	
	int insertSelective(Article record);
	
	Article selectByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(Article record);
	
	int updateByPrimaryKeyWithBLOBs(Article record);
	
	int updateByPrimaryKey(Article record);
	
	List<Article> selectArticleByStatusCount(@Param("siteId")Integer siteId,@Param("status")Integer status,
			@Param("start")Integer start, @Param("size")Integer size);

	List<Article> selectArticleListPage(PageSearch pageSearch);

	List<Article> selectHotArticle(@Param("size")int size,@Param("siteIds") int[] siteIds);
	
}
