package cn.vfunding.vfunding.biz.thirdlogin.service;

import cn.vfunding.vfunding.biz.thirdlogin.model.ThirdLogin;

public interface IThirdLoginService {

	int deleteByPrimaryKey(Integer id);

    int insert(ThirdLogin record);

    int insertSelective(ThirdLogin record);

    ThirdLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ThirdLogin record);

    int updateByPrimaryKey(ThirdLogin record);
	/**
     * 根据登录账号和类型查找信息
     * @param search
     * @return
     * 2014年6月5日
     * liuyijun
     */
    ThirdLogin selectByLoginAccountAndCategory(ThirdLogin search);
}
