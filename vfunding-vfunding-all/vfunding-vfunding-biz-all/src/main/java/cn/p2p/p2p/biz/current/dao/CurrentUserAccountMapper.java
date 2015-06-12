package cn.p2p.p2p.biz.current.dao;

import java.util.List;

import cn.p2p.p2p.biz.current.model.CurrentUserAccount;

public interface CurrentUserAccountMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(CurrentUserAccount record);

    int insertSelective(CurrentUserAccount record);

    CurrentUserAccount selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(CurrentUserAccount record);

    int updateByPrimaryKey(CurrentUserAccount record);
    
    List<CurrentUserAccount> selectAllCurentAccount();
}