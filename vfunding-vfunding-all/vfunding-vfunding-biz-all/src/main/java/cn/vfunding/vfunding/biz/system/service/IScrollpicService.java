package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Scrollpic;
import cn.vfunding.vfunding.biz.system.model.ScrollpicMobile;

public interface IScrollpicService {

	int deleteByPrimaryKey(Short id);

	int insert(Scrollpic record);

	int insertSelective(Scrollpic record);

	Scrollpic selectByPrimaryKey(Short id);

	int updateByPrimaryKeySelective(Scrollpic record);

	int updateByPrimaryKey(Scrollpic record);

	List<Scrollpic> selectByTypeId(Integer typeId);

	/**
	 * @Description:根据状态查询
	 * @param status
	 *            :状态 size：大小
	 * @author liuhuan
	 */
	List<Scrollpic> selectScrollPicByStatus(Integer status,Integer typeId, Integer size);

	/**
	 * 根据类型Id查询(缓存查询)
	 * 
	 * @param typeId
	 *            类型ID
	 * @param size
	 *            查询的条数
	 * @return
	 * @author liuyijun 2014年4月14日
	 */
	List<Scrollpic> selectScrollPicByTypes(Integer typeId, Integer size);

	/**
	 * 手机端
	 * 
	 * @param pageSearch
	 * @return author LiLei 2014年4月21日
	 */
	List<ScrollpicMobile> selectScrollPicByTypeMobile(Integer typeId);
	
	/**
	 * 后台列表
	 * @return
	 * @author liuhuan
	 */
	List<Scrollpic> selectScrollpicListPage(PageSearch pageSearch);
	
	
	Integer selectMaxOrderByStatus(Integer status,Integer typeId);
}
