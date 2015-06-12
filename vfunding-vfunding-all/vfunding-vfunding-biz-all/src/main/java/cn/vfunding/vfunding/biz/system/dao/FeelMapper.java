package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.common.vo.FeelVO;
import cn.vfunding.vfunding.biz.system.model.Feel;

public interface FeelMapper {
	
	int insert(Feel record);

	Feel selectByPrimaryKey(Integer id);
    
	List<Feel> selectByUserId(Integer userId);
    
	Feel selectByStatus(Integer status);

	Feel selectByCode(@Param("code")String code);
	
	int updateByPrimaryKey(Feel record);
	
	int updateByPrimaryKeySelective(Feel record);
	
	List<Feel> selectByNoStatus(@Param("feel")Feel feel);
	
	List<Feel> selectAll();

	List<Feel> selectByNo(@Param("startNo")Integer startNo, @Param("endNo")Integer endNo);
	
	List<FeelVO> selectFeelsListPage(PageSearch pageSearch);
}
