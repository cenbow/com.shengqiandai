package cn.vfunding.vfunding.biz.system.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.system.model.Pic;

/**
 * 
 * @author wang.zeyan
 * @date 2015年3月30日
 * @version 1.0
 */
public interface PicService {

	int deleteByPrimaryKey(String pickey);

    int insert(Pic record);

    int insertSelective(Pic record);

    Pic selectByPrimaryKey(String pickey);

    int updateByPrimaryKeySelective(Pic record);

    int updateByPrimaryKey(Pic record);
    
    List<Pic> selectListPage(PageSearch pageSearch);
}
