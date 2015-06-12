package cn.vfunding.vfunding.biz.utilweixin.service;

import org.springframework.web.servlet.ModelAndView;

import cn.vfunding.vfunding.biz.utilweixin.model.WeixinToken;

public interface IWeixinTokenService {
    int deleteByPrimaryKey(Integer id);

    int insert(WeixinToken record);

    int insertSelective(WeixinToken record);

    WeixinToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeixinToken record);

    int updateByPrimaryKey(WeixinToken record);
    
	/**
	 * 微信sdk写入ModelAndView
	 * @param mv ModelAndView
	 * @param url 分享链接
	 */
    void InjectWeiXinConfig(ModelAndView mv,String url);    
}