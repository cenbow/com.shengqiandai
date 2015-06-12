package cn.vfunding.vfunding.biz.user.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.model.Upfinancial;

public interface UpfinancialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Upfinancial record);

    int insertSelective(Upfinancial record);

    Upfinancial selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Upfinancial record);

    int updateByPrimaryKey(Upfinancial record);

	Upfinancial selectByUserIdAndType(Integer userId);

	List<Upfinancial> selectByListPage(PageSearch pageSearch);
}