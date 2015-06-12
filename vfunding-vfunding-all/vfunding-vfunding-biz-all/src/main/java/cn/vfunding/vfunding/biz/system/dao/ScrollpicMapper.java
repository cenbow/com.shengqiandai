package cn.vfunding.vfunding.biz.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Scrollpic;
import cn.vfunding.vfunding.biz.system.model.ScrollpicMobile;

public interface ScrollpicMapper {
	int deleteByPrimaryKey(Short id);

	int insert(Scrollpic record);

	int insertSelective(Scrollpic record);

	Scrollpic selectByPrimaryKey(Short id);

	int updateByPrimaryKeySelective(Scrollpic record);

	int updateByPrimaryKey(Scrollpic record);

	/**
	 * 加载所有数据
	 * 
	 * @return
	 * @author liuyijun
	 */
	List<Scrollpic> selectAll();

	List<Scrollpic> selectScrollPicByStatus(@Param("status") Integer status,@Param("typeId") Integer typeId,
			@Param("size") Integer size);

	/**
	 * 手机端
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年4月21日
	 */
	List<ScrollpicMobile> selectScrollPicByTypeMobile(Integer typeId);

	/**
	 * 后台列表
	 * @author liuhuan
	 */
	List<Scrollpic> selectScrollpicListPage(PageSearch pageSearch);

	Integer selectMaxOrderByStatus(@Param("status")Integer status, @Param("typeId")Integer typeId);
}