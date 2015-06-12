package cn.vfunding.vfunding.biz.userFrom.service;

import cn.vfunding.vfunding.biz.userFrom.model.UserFromDic;

public interface IUserFromDicService {
	int deleteByPrimaryKey(Integer id);


    int insertSelective(UserFromDic record);

    UserFromDic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFromDic record);
    UserFromDic selectByFromNo(String fromNo);
}
