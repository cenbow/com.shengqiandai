package cn.vfunding.vfunding.biz.user.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.user.model.UserAmountapply;
import cn.vfunding.vfunding.biz.user.model.UserAmountapplyWithBLOBs;

public interface UserAmountapplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserAmountapplyWithBLOBs record);

    int insertSelective(UserAmountapplyWithBLOBs record);

    UserAmountapplyWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAmountapplyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(UserAmountapplyWithBLOBs record);

    int updateByPrimaryKey(UserAmountapply record);
    
    /**
     * @Description:多参数查询
     * @param record
     * @return
     * @author liuhuan
     */
    List<UserAmountapplyWithBLOBs> selectByParam(UserAmountapply record);

    /**
     * 后台审核列表
     * @author liuhuan
     */
	List<UserAmountapply> selectAmountApplyListPage(PageSearch pageSearch);
}