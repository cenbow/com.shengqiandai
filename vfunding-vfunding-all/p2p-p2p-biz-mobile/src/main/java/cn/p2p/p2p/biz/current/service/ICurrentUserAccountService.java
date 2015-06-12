package cn.p2p.p2p.biz.current.service;

import cn.p2p.p2p.biz.current.model.CurrentUserAccount;

public interface ICurrentUserAccountService {
    int deleteByPrimaryKey(Integer userId);

    int insertSelective(CurrentUserAccount record);

    CurrentUserAccount selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(CurrentUserAccount record);

}