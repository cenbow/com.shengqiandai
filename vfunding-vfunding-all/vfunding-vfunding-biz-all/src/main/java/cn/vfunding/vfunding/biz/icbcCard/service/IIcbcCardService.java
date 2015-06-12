package cn.vfunding.vfunding.biz.icbcCard.service;


import org.apache.http.entity.mime.MultipartEntityBuilder;

import cn.vfunding.vfunding.biz.common.vo.MessageVO;
import cn.vfunding.vfunding.biz.icbcCard.model.IcbcCard;

public interface IIcbcCardService {
	int deleteByPrimaryKey(Integer id);


    int insertSelective(IcbcCard record);

    IcbcCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(IcbcCard record);
    
    IcbcCard selectByUserId(Integer userId);
    
    /**
	 * @Description:上传身份证图片
	 * @param multipartEntity
	 * @return
	 * @author 
	 * @throws Exception
	 */
	MessageVO idCardUpload(MultipartEntityBuilder multipartEntity,Integer userId,String idCardType);
	
	/**
	 * 增加免手续费使用次数
	 * @param userId
	 *
	 * jianwei
	 */
	void addNoFeeCountOne(Integer userId);
}
