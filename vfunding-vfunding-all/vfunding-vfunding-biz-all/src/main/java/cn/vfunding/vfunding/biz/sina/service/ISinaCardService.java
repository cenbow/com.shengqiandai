package cn.vfunding.vfunding.biz.sina.service;

import java.util.List;

import cn.vfunding.vfunding.biz.sina.model.SinaCard;
/**
 * 新浪绑定卡信息记录
 * @author jianwei
 * @date 2015年1月19日
 */
public interface ISinaCardService {
	int deleteByPrimaryKey(String sinaCard);

    int insertSelective(SinaCard record);

    SinaCard selectByPrimaryKey(Integer scId);

    int updateByPrimaryKeySelective(SinaCard record);
    
    List<SinaCard> selectSelective(SinaCard sc);
    
    List<SinaCard> selectByUserId(String userId);
    
    /**
     * 所有符合快捷支付的银行卡,按权重排序
     * @param userId
     * @return
     */
    List<SinaCard> selectQuickPayByUserId(String userId);
    
    /**
     * 1.更新个人所有银行卡的权重
     * 2.传参的个人银行卡权重变更为1
     * 3.其余这个人的所有银行卡权重变更为0
     * @param sc
     * @return
     */
    SinaCard updateQuickPayCardWeight(SinaCard sc) throws Exception;
}
